package com.bc.rm.server.controller.econtract;

import com.bc.rm.server.entity.econtract.EcontractOrg;
import com.bc.rm.server.entity.econtract.EcontractToken;
import com.bc.rm.server.enums.ResponseMsg;
import com.bc.rm.server.service.EcontractOrgService;
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
 * 电子合同机构账号
 *
 * @author zhou
 */
@RestController
@RequestMapping("/econtractOrg")
public class EcontractOrgController {

    private static final Logger logger = LoggerFactory.getLogger(EcontractOrgController.class);

    @Resource
    private EcontractTokenService econtractTokenService;

    @Resource
    private EcontractOrgService econtractOrgService;

    /**
     * 创建个人账号
     *
     * @param thirdPartyUserId 机构唯一标识，可传入第三方平台机构id、企业证件号、企业邮箱等如果设置则作为账号唯一性字段，相同id不可重复创建。
     *                         （个人用户与机构的唯一标识不可重复）
     * @param creator          创建人个人账号id（调用个人账号创建接口返回的accountId）
     * @param name             机构名称（非实名签署时必填）
     * @param idType           证件类型，默认CRED_ORG_USCC
     * @param idNumber         证件号（非实名签署时必填）
     * @param orgLegalIdNumber 企业法人证件号
     * @param orgLegalName     企业法人名称
     * @return ResponseEntity<EcontractAccount>
     */
    @ApiOperation(value = "创建机构账号", notes = "创建机构账号")
    @PostMapping(value = "")
    public ResponseEntity<EcontractOrg> addEcontractOrg(
            @RequestParam String thirdPartyUserId,
            @RequestParam String creator,
            @RequestParam String name,
            @RequestParam String idType,
            @RequestParam String idNumber,
            @RequestParam String orgLegalIdNumber,
            @RequestParam String orgLegalName) {
        ResponseEntity<EcontractOrg> responseEntity;

        EcontractOrg econtractOrg = new EcontractOrg(
                thirdPartyUserId, creator, name, idType, idNumber, orgLegalIdNumber, orgLegalName);

        try {
            EcontractToken econtractToken = econtractTokenService.getAccessTokenFromDB();
            econtractOrg = econtractOrgService.createEcontractOrg(econtractToken, econtractOrg);
            if (StringUtils.isEmpty(econtractOrg.getOrgId())) {
                return new ResponseEntity<>(econtractOrg, HttpStatus.BAD_REQUEST);
            }
            // 调用api创建电子合同个人账号
            responseEntity = new ResponseEntity<>(econtractOrg, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("addEcontractOrg error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(econtractOrg, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 删除/注销机构账号(按照账号ID删除)
     *
     * @param orgId 机构账号ID
     * @return ResponseEntity<String>
     */
    @ApiOperation(value = "删除/注销机构账号(按照账号ID删除)", notes = "删除/注销机构账号(按照账号ID删除)")
    @DeleteMapping(value = "/{orgId}")
    public ResponseEntity<String> deleteAccountByAccountId(@PathVariable String orgId) {
        ResponseEntity<String> responseEntity;
        try {
            EcontractToken econtractToken = econtractTokenService.getAccessTokenFromDB();
            // 调用api删除电子合同机构账号
            econtractOrgService.deleteOrgByOrgId(econtractToken, orgId);
            responseEntity = new ResponseEntity<>(ResponseMsg.DELETE_E_CONTRACT_ORG_SUCCESS.getResponseCode(),
                    HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(ResponseMsg.DELETE_E_CONTRACT_ORG_ERROR.getResponseCode(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}