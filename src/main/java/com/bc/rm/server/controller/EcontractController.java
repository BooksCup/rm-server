package com.bc.rm.server.controller;

import com.bc.rm.server.entity.econtract.EcontractToken;
import com.bc.rm.server.service.EcontractTokenService;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 电子合同
 *
 * @author zhou
 */
@RestController
@RequestMapping("/econtract")
public class EcontractController {

    private static final Logger logger = LoggerFactory.getLogger(ModuleController.class);

    @Resource
    private EcontractTokenService econtractTokenService;

    @ApiOperation(value = "生成token", notes = "生成token")
    @PostMapping(value = "/token")
    public ResponseEntity<String> addEcontractToken(
            @RequestParam String appId,
            @RequestParam String secret,
            @RequestParam String grantType) {
        ResponseEntity<String> responseEntity;
        try {
            EcontractToken econtractToken = new EcontractToken(appId, secret, grantType);
            // 调用api获取token
            econtractToken = econtractTokenService.generateToken(econtractToken);

            List<EcontractToken> econtractTokenList = econtractTokenService.getEcontractTokenList();
            if (CollectionUtils.isEmpty(econtractTokenList)) {
                econtractTokenService.addEcontractToken(econtractToken);
            } else {
                econtractToken.setId(econtractTokenList.get(0).getId());
                econtractTokenService.updateEcontractToken(econtractToken);
            }
            responseEntity = new ResponseEntity<>("", HttpStatus.OK);
        } catch (Exception e) {
            logger.error("addEcontractToken error: " + e.getMessage());
            responseEntity = new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @ApiOperation(value = "获取token", notes = "获取token")
    @GetMapping(value = "/token")
    public ResponseEntity<EcontractToken> getEcontractToken() {
        ResponseEntity<EcontractToken> responseEntity;
        EcontractToken econtractToken;
        try {
            List<EcontractToken> econtractTokenList = econtractTokenService.getEcontractTokenList();
            if (CollectionUtils.isEmpty(econtractTokenList)) {
                econtractToken = new EcontractToken();
            } else {
                econtractToken = econtractTokenList.get(0);
            }
            responseEntity = new ResponseEntity<>(econtractToken, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getEcontractToken error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(new EcontractToken(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
