package com.bc.rm.server.controller.printer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bc.rm.server.cons.Constant;
import com.bc.rm.server.entity.printer.Printer;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    private static final String USER = "BooksCup@163.com";
    private static final String UKEY = "1";

    @Resource
    private PrinterService printerService;


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
            String host = Constant.FEI_E_YUN_PRINTER_URL;
            Map<String, String> headerMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            queryMap.put("user", USER);
            String stime = String.valueOf(System.currentTimeMillis() / 1000);
            queryMap.put("stime", stime);
            queryMap.put("sig", signature(USER, UKEY, stime));
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


    @ApiOperation(value = "添加打印机", notes = "添加打印机")
    @PostMapping(value = "/label")
    public ResponseEntity<String> printLabelMsg() {
        ResponseEntity<String> responseEntity;
        try {

            String content;
            content = "<TEXT x=\"100\"  y=\"50\" r=\"90\">http://www.dzist.com</QR>";


            String host = Constant.FEI_E_YUN_PRINTER_URL;
            String path = "";
            Map<String, String> headerMap = new HashMap<>();
            Map<String, String> queryMap = new HashMap<>();
            queryMap.put("user", USER);
            String stime = String.valueOf(System.currentTimeMillis() / 1000);
            queryMap.put("stime", stime);
            queryMap.put("sig", signature(USER, UKEY, stime));
            queryMap.put("apiname", "Open_printLabelMsg");


            queryMap.put("sn", "960201040");
            queryMap.put("content", content);
            queryMap.put("times", "1");

            Map<String, String> bodyMap = new HashMap<>();

            HttpResponse response = HttpUtil.doPost(host, path, headerMap, queryMap, bodyMap);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("result: " + result);

            responseEntity = new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    //生成签名字符串
    private static String signature(String USER, String UKEY, String STIME) {
        String s = DigestUtils.sha1Hex(USER + UKEY + STIME);
        return s;
    }
}
