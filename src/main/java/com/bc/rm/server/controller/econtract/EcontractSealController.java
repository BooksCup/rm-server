package com.bc.rm.server.controller.econtract;

import com.bc.rm.server.entity.econtract.EcontractAccount;
import com.bc.rm.server.entity.econtract.EcontractSeal;
import com.bc.rm.server.entity.econtract.EcontractToken;
import com.bc.rm.server.service.EcontractSealService;
import com.bc.rm.server.service.EcontractTokenService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

}
