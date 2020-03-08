package com.bc.rm.server.mapper;

import com.bc.rm.server.entity.econtract.EcontractAccount;

/**
 * 电子合同个人账号
 *
 * @author zhou
 */
public interface EcontractAccountMapper {
    /**
     * 新增电子合同个人账号
     *
     * @param econtractAccount 电子合同个人账号
     */
    void addEcontractAccount(EcontractAccount econtractAccount);
}
