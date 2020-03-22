package com.bc.rm.server.controller.econtract;

import com.bc.rm.server.entity.econtract.EcontractAccount;
import com.bc.rm.server.entity.econtract.EcontractToken;
import com.bc.rm.server.entity.econtract.result.Account;
import com.bc.rm.server.enums.ResponseMsg;
import com.bc.rm.server.service.EcontractAccountService;
import com.bc.rm.server.service.EcontractTokenService;
import com.bc.rm.server.util.CommonUtil;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 电子合同个人账号
 *
 * @author zhou
 */
@RestController
@RequestMapping("/econtractAccount")
public class EcontractAccountController {

    private static final Logger logger = LoggerFactory.getLogger(EcontractAccountController.class);

    @Resource
    private EcontractTokenService econtractTokenService;

    @Resource
    private EcontractAccountService econtractAccountService;

    /**
     * 创建个人账号
     *
     * @param thirdPartyUserId 用户唯一标识，可传入第三方平台的个人用户id、证件号、手机号、邮箱等，
     *                         如果设置则作为账号唯一性字段，相同信息不可重复创建。（个人用户与机构的唯一标识不可重复）
     * @param name             姓名（非实名签署时必填）
     * @param idType           证件类型，默认CRED_PSN_CH_IDCARD
     * @param idNumber         证件号（非实名签署时必填）
     * @param mobile           手机号码，默认空，手机号为空时无法使用短信意愿认证
     * @param email            邮箱地址，默认空
     * @return ResponseEntity<EcontractAccount>
     */
    @ApiOperation(value = "创建个人账号", notes = "创建个人账号")
    @PostMapping(value = "")
    public ResponseEntity<EcontractAccount> addEcontractAccount(
            @RequestParam String thirdPartyUserId,
            @RequestParam String name,
            @RequestParam String idType,
            @RequestParam String idNumber,
            @RequestParam String mobile,
            @RequestParam String email) {
        ResponseEntity<EcontractAccount> responseEntity;
        EcontractAccount econtractAccount = new EcontractAccount(
                thirdPartyUserId, name, idType, idNumber, mobile, email);
        econtractAccount.setCreateTime(CommonUtil.now());
        logger.info("[addEcontractAccount], data: " + econtractAccount);
        try {
            EcontractToken econtractToken = econtractTokenService.getAccessTokenFromDB();
            econtractAccount = econtractAccountService.createEcontractAccount(econtractToken, econtractAccount);
            if (StringUtils.isEmpty(econtractAccount.getId())) {
                return new ResponseEntity<>(econtractAccount, HttpStatus.BAD_REQUEST);
            }
            // 调用api创建电子合同个人账号
            responseEntity = new ResponseEntity<>(econtractAccount, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("addEcontractAccount error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(econtractAccount, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 修改个人账号
     *
     * @param accountId        个人账号ID
     * @param thirdPartyUserId 用户唯一标识，可传入第三方平台的个人用户id、证件号、手机号、邮箱等，
     *                         如果设置则作为账号唯一性字段，相同信息不可重复创建。（个人用户与机构的唯一标识不可重复）
     * @param name             姓名（非实名签署时必填）
     * @param idType           证件类型，默认CRED_PSN_CH_IDCARD
     * @param idNumber         证件号（非实名签署时必填）
     * @param mobile           手机号码，默认空，手机号为空时无法使用短信意愿认证
     * @param email            邮箱地址，默认空
     * @return ResponseEntity<EcontractAccount>
     */
    @ApiOperation(value = "修改个人账号", notes = "修改个人账号")
    @PutMapping(value = "/{accountId}")
    public ResponseEntity<EcontractAccount> updateEcontractAccount(
            @PathVariable String accountId,
            @RequestParam String thirdPartyUserId,
            @RequestParam String name,
            @RequestParam String idType,
            @RequestParam String idNumber,
            @RequestParam String mobile,
            @RequestParam String email) {
        ResponseEntity<EcontractAccount> responseEntity;
        EcontractAccount econtractAccount = new EcontractAccount(
                thirdPartyUserId, name, idType, idNumber, mobile, email);
        try {
            econtractAccount.setId(accountId);
            EcontractToken econtractToken = econtractTokenService.getAccessTokenFromDB();
            econtractAccount = econtractAccountService.updateEcontractAccount(econtractToken, econtractAccount);
            // 调用api修改电子合同个人账号
            responseEntity = new ResponseEntity<>(econtractAccount, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("updateEcontractAccount error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(econtractAccount, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 查询个人账号(按照账号ID查询)
     *
     * @param accountId 个人账号ID
     * @return ResponseEntity<Account>
     */
    @ApiOperation(value = "按照ID查询个人账号", notes = "按照ID查询个人账号")
    @GetMapping(value = "/{accountId}")
    public ResponseEntity<Account> getAccountByAccountId(@PathVariable String accountId) {
        ResponseEntity<Account> responseEntity;
        try {
            EcontractToken econtractToken = econtractTokenService.getAccessTokenFromDB();
            // 调用api查询电子合同个人账号
            Account account = econtractAccountService.getAccountByAccountId(econtractToken, accountId);
            responseEntity = new ResponseEntity<>(account, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getAccountByAccountId error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(new Account(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 查询个人账号(按照第三方用户ID查询)
     *
     * @param thirdPartyUserId 第三方平台的用户ID
     * @return ResponseEntity<Account>
     */
    @ApiOperation(value = "按照第三方用户ID查询", notes = "按照第三方用户ID查询")
    @GetMapping(value = "/getByThirdId")
    public ResponseEntity<Account> getAccountByThirdPartyUserId(@RequestParam String thirdPartyUserId) {
        ResponseEntity<Account> responseEntity;
        try {
            EcontractToken econtractToken = econtractTokenService.getAccessTokenFromDB();
            // 调用api查询电子合同个人账号
            Account account = econtractAccountService.getAccountByThirdPartyUserId(econtractToken, thirdPartyUserId);
            responseEntity = new ResponseEntity<>(account, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("getAccountByThirdPartyUserId error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(new Account(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    /**
     * 删除个人账号(按照账号ID删除)
     *
     * @param accountId 个人账号ID
     * @return ResponseEntity<String>
     */
    @ApiOperation(value = "按照账号ID删除/注销个人账号", notes = "按照账号ID删除/注销个人账号")
    @DeleteMapping(value = "/{accountId}")
    public ResponseEntity<String> deleteAccountByAccountId(@PathVariable String accountId) {
        ResponseEntity<String> responseEntity;
        try {
            logger.info("[deleteAccountByAccountId] accountId: " + accountId);
            EcontractToken econtractToken = econtractTokenService.getAccessTokenFromDB();
            // 调用api删除电子合同个人账号
            econtractAccountService.deleteAccountByAccountId(econtractToken, accountId);
            responseEntity = new ResponseEntity<>(ResponseMsg.DELETE_E_CONTRACT_ACCOUNT_SUCCESS.getResponseCode(),
                    HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(ResponseMsg.DELETE_E_CONTRACT_ACCOUNT_ERROR.getResponseCode(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 删除/注销个人账号(按照第三方用户ID注销)
     *
     * @param thirdPartyUserId 第三方用户ID
     * @return ResponseEntity<String>
     */
    @ApiOperation(value = "按照第三方用户ID删除/注销个人账号", notes = "按照第三方用户ID删除/注销个人账号")
    @DeleteMapping(value = "/deleteByThirdId")
    public ResponseEntity<String> deleteAccountByThirdPartyUserId(@RequestParam String thirdPartyUserId) {
        ResponseEntity<String> responseEntity;
        try {
            EcontractToken econtractToken = econtractTokenService.getAccessTokenFromDB();
            // 调用api删除电子合同个人账号
            econtractAccountService.deleteAccountByThirdPartyUserId(econtractToken, thirdPartyUserId);
            responseEntity = new ResponseEntity<>(ResponseMsg.DELETE_E_CONTRACT_ACCOUNT_SUCCESS.getResponseCode(),
                    HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(ResponseMsg.DELETE_E_CONTRACT_ACCOUNT_ERROR.getResponseCode(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 设置签署密码
     *
     * @param accountId 账号ID
     * @param password  签署密码
     * @return ResponseEntity<String>
     */
    @ApiOperation(value = "设置签署密码", notes = "设置签署密码")
    @PostMapping(value = "/{accountId}/setSignPwd")
    public ResponseEntity<String> setSignPwd(@PathVariable String accountId, @RequestParam String password) {
        ResponseEntity<String> responseEntity;
        try {
            EcontractToken econtractToken = econtractTokenService.getAccessTokenFromDB();
            // 调用api设置签署密码
            boolean setFlag = econtractAccountService.setSignPwd(econtractToken, accountId, password);
            if (setFlag) {
                // 设置成功
                responseEntity = new ResponseEntity<>(ResponseMsg.SET_SIGN_PWD_SUCCESS.getResponseCode(),
                        HttpStatus.OK);
            } else {
                // 设置失败
                responseEntity = new ResponseEntity<>(ResponseMsg.SET_SIGN_PWD_ERROR.getResponseCode(),
                        HttpStatus.BAD_REQUEST);
            }

        } catch (Exception e) {
            responseEntity = new ResponseEntity<>(ResponseMsg.SET_SIGN_PWD_ERROR.getResponseCode(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 获取电子合同个人账户列表
     *
     * @param page  当前分页
     * @param limit 每个分页大小
     * @return ResponseEntity
     */
    @ApiOperation(value = "获取电子合同个人账户列表", notes = "获取电子合同个人账户列表")
    @GetMapping(value = "")
    public ResponseEntity<PageInfo<EcontractAccount>> getEcontractAccountList(
            @RequestParam Integer page,
            @RequestParam Integer limit) {
        logger.info("page: " + page + ", limit:" + limit);
        PageInfo<EcontractAccount> pageInfo = econtractAccountService.getEcontractAccountListByPageInfo(page, limit);
        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }
}
