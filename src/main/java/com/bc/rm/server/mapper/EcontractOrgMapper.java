package com.bc.rm.server.mapper;

import com.bc.rm.server.entity.econtract.EcontractOrg;
import com.bc.rm.server.entity.econtract.EcontractToken;


/**
 * 电子合同机构
 *
 * @author zhou
 */
public interface EcontractOrgMapper {
    /**
     * 新增电子合同机构
     *
     * @param econtractOrg 电子合同机构
     */
    void addEcontractOrg(EcontractOrg econtractOrg);

    void updateEcontractOrgByOrgId( EcontractOrg econtractOrg);

    /**
     * 按照机构账号ID注销机构账号
     *
     * @param orgId 机构账号ID
     */
    void deleteOrgByOrgId(String orgId);

    /**
     * 删除/注销机构账号(按照第三方机构ID注销)
     *
     * @param thirdPartyUserId 第三方机构ID
     */
    void deleteOrgByThirdPartyUserId(String thirdPartyUserId);
}
