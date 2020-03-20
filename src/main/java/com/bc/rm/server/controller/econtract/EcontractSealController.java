package com.bc.rm.server.controller.econtract;

import com.bc.rm.server.entity.econtract.EcontractSeal;
import com.bc.rm.server.entity.econtract.EcontractToken;
import com.bc.rm.server.entity.econtract.result.SealResultList;
import com.bc.rm.server.enums.ResponseMsg;
import com.bc.rm.server.service.EcontractSealService;
import com.bc.rm.server.service.EcontractTokenService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 印章
 *
 * @author zhou
 */
@RestController
@RequestMapping("/econtractSeal")
public class EcontractSealController {

    private static final Logger logger = LoggerFactory.getLogger(EcontractOrgController.class);

    @Resource
    private EcontractTokenService econtractTokenService;

    @Resource
    private EcontractSealService econtractSealService;


    /**
     * 创建个人模板印章
     *
     * @param accountId 用户ID
     * @param alias     印章别名
     * @param color     印章颜色，RED-红色， BLUE-蓝色，BLACK-黑色
     * @param height    印章高度, 默认95px
     * @param width     印章宽度, 默认95px
     * @param type      模板类型
     * @return ResponseEntity<EcontractSeal>
     */
    @ApiOperation(value = "创建个人模板印章", notes = "创建个人模板印章")
    @PostMapping(value = "/personaltemplate")
    public ResponseEntity<EcontractSeal> createSealPersonalTemplate(
            @RequestParam String accountId,
            @RequestParam String alias,
            @RequestParam String color,
            @RequestParam Integer height,
            @RequestParam Integer width,
            @RequestParam String type) {
        ResponseEntity<EcontractSeal> responseEntity;
        EcontractSeal econtractSeal = new EcontractSeal(
                accountId, alias, color, height, width, type);
        try {
            EcontractToken econtractToken = econtractTokenService.getAccessTokenFromDB();
            econtractSeal = econtractSealService.createSealPersonalTemplate(econtractToken, econtractSeal);
            if (StringUtils.isEmpty(econtractSeal.getSealId())) {
                return new ResponseEntity<>(econtractSeal, HttpStatus.BAD_REQUEST);
            }
            responseEntity = new ResponseEntity<>(econtractSeal, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("createSealPersonalTemplate error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(econtractSeal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 创建机构模板印章
     *
     * @param orgId   机构id
     * @param alias   印章别名
     * @param color   印章颜色，RED-红色，BLUE-蓝色，BLACK-黑色
     * @param height  印章高度，默认159px
     * @param width   印章宽度，默认159px
     * @param htext   横向文，可设置0-8个字，企业名称超出25个字后，不支持设置横向文
     * @param qtext   下弦文，可设置0-20个字，企业企业名称超出25个字后，不支持设置下弦文
     * @param type    模板类型，TEMPLATE_ROUND-圆章，TEMPLATE_OVAL-椭圆章
     * @param central 中心图案类型，STAR-圆形有五角星，NONE-圆形无五角星
     * @return ResponseEntity<EcontractSeal>
     */
    @ApiOperation(value = "创建机构模板印章", notes = "创建机构模板印章")
    @PostMapping(value = "/officialtemplate")
    public ResponseEntity<EcontractSeal> createSealOfficialTemplate(
            @RequestParam String orgId,
            @RequestParam String alias,
            @RequestParam String color,
            @RequestParam Integer height,
            @RequestParam Integer width,
            @RequestParam String htext,
            @RequestParam String qtext,
            @RequestParam String type,
            @RequestParam String central) {
        ResponseEntity<EcontractSeal> responseEntity;
        EcontractSeal econtractSeal = new EcontractSeal(
                orgId, alias, color, height, width, htext, qtext, type, central);
        try {
            EcontractToken econtractToken = econtractTokenService.getAccessTokenFromDB();
            econtractSeal = econtractSealService.createSealOfficialTemplate(econtractToken, econtractSeal);
            if (StringUtils.isEmpty(econtractSeal.getSealId())) {
                return new ResponseEntity<>(econtractSeal, HttpStatus.BAD_REQUEST);
            }
            responseEntity = new ResponseEntity<>(econtractSeal, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("createSealOfficialTemplate error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(econtractSeal, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 查询个人/机构所有印章
     *
     * @param accountType 账号类型 "0":个人账号  "1":机构账号
     * @param accountId   个人/机构账号ID
     * @param offset      分页起始位置
     * @param size        单页数量
     * @return 个人/机构所有印章
     */
    @ApiOperation(value = "查询个人/机构所有印章", notes = "查询个人/机构所有印章")
    @GetMapping(value = "")
    public ResponseEntity<SealResultList> getSeals(
            @RequestParam String accountType,
            @RequestParam String accountId,
            @RequestParam(required = false, defaultValue = "1") Integer offset,
            @RequestParam(required = false, defaultValue = "10") Integer size) {
        ResponseEntity<SealResultList> responseEntity;
        try {
            EcontractToken econtractToken = econtractTokenService.getAccessTokenFromDB();
            SealResultList sealResultList = econtractSealService.getSeals(accountType, econtractToken, accountId, offset, size);
            responseEntity = new ResponseEntity<>(sealResultList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getOfficialSeals error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(new SealResultList(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 删除个人/企业印章
     *
     * @param accountType 账号类型 "0":个人账号  "1":机构账号
     * @param sealId      印章ID
     * @param accountId   个人/机构账号ID
     * @return ResponseEntity<String>
     */
    @ApiOperation(value = "删除个人/企业印章", notes = "删除个人/企业印章")
    @DeleteMapping(value = "/{sealId}")
    public ResponseEntity<String> deleteSeal(
            @RequestParam String accountType,
            @PathVariable String sealId,
            @RequestParam String accountId) {
        ResponseEntity<String> responseEntity;
        try {
            EcontractToken econtractToken = econtractTokenService.getAccessTokenFromDB();
            // 调用api删除个人/企业印章
            econtractSealService.deleteSeal(accountType, econtractToken, accountId, sealId);
            responseEntity = new ResponseEntity<>(ResponseMsg.DELETE_E_CONTRACT_SEAL_SUCCESS.getResponseCode(),
                    HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(ResponseMsg.DELETE_E_CONTRACT_SEAL_ERROR.getResponseCode(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
