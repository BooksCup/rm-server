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

    Account getAccountByAccountId(EcontractToken econtractToken, String accountId);
}
