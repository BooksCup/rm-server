package com.bc.rm.server.service;

import com.bc.rm.server.entity.econtract.EcontractAccount;
import com.bc.rm.server.entity.econtract.EcontractToken;
import com.bc.rm.server.entity.econtract.result.Account;

/**
 * 电子合同个人账号
 *
 * @author zhou
 */
public interface EcontractAccountService {
    /**
     * 新增电子合同个人账号
     *
     * @param econtractToken   token
     * @param econtractAccount 个人账号
     * @return 个人账号
     */
    EcontractAccount createEcontractAccount(EcontractToken econtractToken, EcontractAccount econtractAccount);

    /**
     * 修改电子合同个人账号
     *
     * @param econtractToken   accessToken
     * @param econtractAccount 个人账号
     * @return 个人账号
     */
    EcontractAccount updateEcontractAccount(EcontractToken econtractToken, EcontractAccount econtractAccount);

    /**
     * 查询个人账号(按照账号ID查询)
     *
     * @param econtractToken accessToken
     * @param accountId      账号ID
     * @return 个人账号
     */
    Account getAccountByAccountId(EcontractToken econtractToken, String accountId);

    /**
     * 查询个人账号(按照第三方用户ID查询)
     *
     * @param econtractToken   accessToken
     * @param thirdPartyUserId 第三方用户ID
     * @return 个人账号
     */
    Account getAccountByThirdPartyUserId(EcontractToken econtractToken, String thirdPartyUserId);

    /**
     * 删除/注销个人账号(按照账号ID注销)
     *
     * @param econtractToken accessToken
     * @param accountId      账号ID
     * @return true:删除成功  false:删除失败
     */
    boolean deleteAccountByAccountId(EcontractToken econtractToken, String accountId);

    /**
     * 删除/注销个人账号(按照第三方用户ID注销)
     *
     * @param econtractToken   accessToken
     * @param thirdPartyUserId 第三方用户ID
     * @return true:删除成功  false:删除失败
     */
    boolean deleteAccountByThirdPartyUserId(EcontractToken econtractToken, String thirdPartyUserId);
}
