package com.bc.rm.server.controller.printer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bc.rm.server.cons.Constant;
import com.bc.rm.server.entity.econtract.result.ApiBaseResult;
import com.bc.rm.server.entity.printer.Printer;
import com.bc.rm.server.entity.printer.PrinterConfig;
import com.bc.rm.server.entity.printer.PrinterOrder;
import com.bc.rm.server.entity.printer.result.PrinterApiBaseResult;
import com.bc.rm.server.entity.printer.result.PrinterResult;
import com.bc.rm.server.service.PrinterService;
import com.bc.rm.server.util.HttpUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 打印机
 *
 * @author zhou
 */
@RestController
@RequestMapping("/printers")
public class PrinterController {
    private static final Logger logger = LoggerFactory.getLogger(PrinterController.class);

    @Resource
    private PrinterService printerService;

    /**
     * 添加打印机
     *
     * @param sn         打印机编号SN
     * @param key        打印机识别码KEY(存于底部标签)
     * @param name       打印机名称，如地址、店铺名等，便于管理
     * @param dataCardNo 流量卡号码
     * @return ResponseEntity<Printer>
     */
    @ApiOperation(value = "添加打印机", notes = "添加打印机")
    @PostMapping(value = "")
    public ResponseEntity<Printer> addPrinter(
            @RequestParam String sn,
            @RequestParam String key,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String dataCardNo) {
        ResponseEntity<Printer> responseEntity;
        Printer printer = new Printer(sn, key, name, dataCardNo);
        try {
            PrinterConfig printerConfig = printerService.getPrinterConfig();
            String host = Constant.FEI_E_YUN_PRINTER_URL;
            Map<String, String> headerMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            queryMap.put("user", printerConfig.getUser());
            String stime = String.valueOf(System.currentTimeMillis() / 1000);
            queryMap.put("stime", stime);
            queryMap.put("sig", signature(printerConfig.getUser(), printerConfig.getUkey(), stime));
            queryMap.put("apiname", "Open_printerAddlist");
            String printContent = sn + "#" + key + "#" + name + "#" + dataCardNo;
            queryMap.put("printerContent", printContent);

            Map<String, String> bodyMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);

            HttpResponse response = HttpUtil.doPost(host, null, headerMap, queryMap, bodyMap);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("result: " + result);

            TypeReference<PrinterApiBaseResult<PrinterResult>> typeReference = new TypeReference<PrinterApiBaseResult<PrinterResult>>() {
            };
            PrinterApiBaseResult<PrinterResult> apiBaseResult = JSON.parseObject(result, typeReference);
            logger.info("ret: " + apiBaseResult.getRet());
            logger.info("msg: " + apiBaseResult.getMsg());
            logger.info("serverExecutedTime: " + apiBaseResult.getServerExecutedTime());
            printer.setApiResultCode(apiBaseResult.getRet());

            String[] ok = apiBaseResult.getData().getOk();
            String[] no = apiBaseResult.getData().getNo();
            logger.info("addPrinter successNum: " + ok.length + ", errorNum: " + no.length);

            // 批量处理的话okList和noList都可能会有值
            // 单条处理只会出现一个分支
            if (null != ok && ok.length > 0) {
                responseEntity = new ResponseEntity<>(printer, HttpStatus.OK);
                // 持久化到db
                printerService.addPrinter(printer);
            } else {
                // 截取错误信息
                String noMsg = no[0];
                logger.info("noMsg: " + noMsg);
                String errorMsg;
                try {
                    errorMsg = noMsg.split("#")[3].split(" ")[1];
                } catch (Exception e) {
                    errorMsg = "";
                    e.printStackTrace();
                }
                printer.setApiResultMessage(errorMsg);
                responseEntity = new ResponseEntity<>(printer, HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("addPrinter error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(printer, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 标签机打印订单
     *
     * @param printerSn 打印机编号SN
     * @param content   打印内容
     * @param times     打印次数
     * @return ResponseEntity<PrinterApiBaseResult>
     */
    @ApiOperation(value = "标签机打印订单", notes = "标签机打印订单")
    @PostMapping(value = "/{printerSn}/label")
    public ResponseEntity<PrinterApiBaseResult<String>> printLabelMsg(
            @PathVariable String printerSn,
            @RequestParam String content,
            @RequestParam(required = false, defaultValue = "1") String times) {
        ResponseEntity<PrinterApiBaseResult<String>> responseEntity;
        PrinterOrder printerOrder = new PrinterOrder(printerSn, content, times);
        try {
            PrinterConfig printerConfig = printerService.getPrinterConfig();
            String host = Constant.FEI_E_YUN_PRINTER_URL;
            Map<String, String> headerMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            queryMap.put("user", printerConfig.getUser());
            String stime = String.valueOf(System.currentTimeMillis() / 1000);
            queryMap.put("stime", stime);
            queryMap.put("sig", signature(printerConfig.getUser(), printerConfig.getUkey(), stime));
            queryMap.put("apiname", "Open_printLabelMsg");

            queryMap.put("sn", printerSn);
            queryMap.put("content", content);
            queryMap.put("times", times);

            Map<String, String> bodyMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);

            HttpResponse response = HttpUtil.doPost(host, null, headerMap, queryMap, bodyMap);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("result: " + result);
            TypeReference<PrinterApiBaseResult<String>> typeReference = new TypeReference<PrinterApiBaseResult<String>>() {
            };
            PrinterApiBaseResult<String> apiBaseResult = JSON.parseObject(result, typeReference);
            String orderNo = apiBaseResult.getData();
            logger.info("orderNo: " + orderNo);

            printerOrder.setRetCode(apiBaseResult.getRet());
            printerOrder.setRetMessage(apiBaseResult.getMsg());
            printerOrder.setNo(orderNo);

            responseEntity = new ResponseEntity<>(apiBaseResult, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("printLabelMsg error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(new PrinterApiBaseResult<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 生成签名字符串
     *
     * @param user  user
     * @param ukey  ukey
     * @param stime 时间戳
     * @return 签名字符串
     */
    private static String signature(String user, String ukey, String stime) {
        String sig = DigestUtils.sha1Hex(user + ukey + stime);
        return sig;
    }
}
