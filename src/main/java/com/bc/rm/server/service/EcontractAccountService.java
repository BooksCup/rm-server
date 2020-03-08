package com.bc.rm.server.service;

import com.bc.rm.server.entity.econtract.EcontractAccount;
import com.bc.rm.server.entity.econtract.EcontractToken;

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
     */
    EcontractAccount createEcontractAccount(EcontractToken econtractToken, EcontractAccount econtractAccount);
}
