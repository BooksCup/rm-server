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

    /**
     * 按照机构账号ID注销机构账号
     *
     * @param econtractToken token
     * @param orgId          机构账号ID
     * @return true:删除成功 false:删除失败
     */
    boolean deleteOrgByOrgId(EcontractToken econtractToken, String orgId);
}
