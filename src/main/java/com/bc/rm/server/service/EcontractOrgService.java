package com.bc.rm.server.service;

import com.bc.rm.server.entity.econtract.EcontractOrg;
import com.bc.rm.server.entity.econtract.EcontractToken;
import com.github.pagehelper.PageInfo;

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
     * 修改电子合同机构账号
     *
     * @param econtractToken accessToken
     * @param econtractOrg   机构账号
     * @return 机构账号
     */
    EcontractOrg updateEcontractOrgByOrgId(EcontractToken econtractToken, EcontractOrg econtractOrg);

    /**
     * 查询机构账号(按照机构ID查询)
     *
     * @param econtractToken accessToken
     * @param orgId          机构ID
     * @return 机构账号
     */
    EcontractOrg getOrgByOrgId(EcontractToken econtractToken, String orgId);

    /**
     * 按照机构账号ID注销机构账号
     *
     * @param econtractToken token
     * @param orgId          机构账号ID
     * @return true:删除成功 false:删除失败
     */
    boolean deleteOrgByOrgId(EcontractToken econtractToken, String orgId);

    /**
     * 删除/注销机构账号(按照第三方机构ID注销)
     *
     * @param econtractToken   token
     * @param thirdPartyUserId 第三方机构ID
     * @return true:删除成功 false:删除失败
     */
    boolean deleteOrgByThirdPartyUserId(EcontractToken econtractToken, String thirdPartyUserId);

    /**
     * 获取电子合同机构账户列表
     *
     * @param pageNum  当前分页
     * @param pageSize 每个分页大小
     * @return 电子合同机构账户分页列表
     */
    PageInfo<EcontractOrg> getEcontractOrgListByPageInfo(int pageNum, int pageSize);
}
