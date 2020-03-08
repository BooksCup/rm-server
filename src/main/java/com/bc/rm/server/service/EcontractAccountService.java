package com.bc.rm.server.service;

import com.bc.rm.server.entity.econtract.EcontractAccount;

/**
 * 电子合同个人账号
 *
 * @author zhou
 */
public interface EcontractAccountService {
    /**
     * 新增电子合同个人账号
     *
     * @param econtractAccount 电子合同个人账号
     */
    EcontractAccount createEcontractAccount(EcontractAccount econtractAccount);
}
