package com.bc.rm.server.service;

import com.bc.rm.server.entity.econtract.EcontractOrg;
import com.bc.rm.server.entity.econtract.EcontractToken;

/**
 * 电子合同机构
 *
 * @author zhou
 */
public interface EcontractOrgService {

    /**
     * 创建机构账号
     *
     * @param econtractToken token
     * @param econtractOrg   机构账号
     * @return 机构账号
     */
    EcontractOrg createEcontractOrg(EcontractToken econtractToken, EcontractOrg econtractOrg);
}
