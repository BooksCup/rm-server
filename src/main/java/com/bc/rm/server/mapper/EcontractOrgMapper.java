package com.bc.rm.server.mapper;

import com.bc.rm.server.entity.econtract.EcontractAccount;
import com.bc.rm.server.entity.econtract.EcontractOrg;
import com.bc.rm.server.entity.econtract.EcontractToken;

import java.util.List;
import java.util.Map;


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

    /**
     * 按照机构ID修改机构
     *
     * @param econtractOrg 机构
     */
    void updateEcontractOrgByOrgId(EcontractOrg econtractOrg);

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

    /**
     * 获取电子合同机构账户列表
     *
     * @param paramMap 参数map
     * @return 电子合同机构账户列表
     */
    List<EcontractOrg> getEcontractOrgListByParams(Map<String, String> paramMap);
}
