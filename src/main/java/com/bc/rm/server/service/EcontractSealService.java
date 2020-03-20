package com.bc.rm.server.service;

import com.bc.rm.server.entity.econtract.EcontractSeal;
import com.bc.rm.server.entity.econtract.EcontractToken;
import com.bc.rm.server.entity.econtract.result.SealResultList;

/**
 * 印章
 *
 * @author zhou
 */
public interface EcontractSealService {
    /**
     * 创建个人模板印章
     *
     * @param econtractToken token
     * @param econtractSeal  印章
     * @return 个人模板印章
     */
    EcontractSeal createSealPersonalTemplate(EcontractToken econtractToken, EcontractSeal econtractSeal);

    /**
     * 创建企业模板印章
     *
     * @param econtractToken token
     * @param econtractSeal  印章
     * @return 企业模板印章
     */
    EcontractSeal createSealOfficialTemplate(EcontractToken econtractToken, EcontractSeal econtractSeal);

    /**
     * 查询个人/机构所有印章
     *
     * @param accountType    账号类型 "0":个人账号  "1":机构账号
     * @param econtractToken token
     * @param accountId      个人/机构账号ID
     * @param offset         分页起始位置
     * @param size           单页数量
     * @return 个人/机构所有印章
     */
    SealResultList getSeals(String accountType, EcontractToken econtractToken, String accountId, Integer offset, Integer size);

    /**
     * 删除个人/企业印章
     *
     * @param accountType    账号类型 "0":个人账号  "1":机构账号
     * @param econtractToken token
     * @param accountId      个人/机构账号ID
     * @param sealId         印章ID
     * @return true:删除成功 false:删除失败
     */
    boolean deleteSeal(String accountType, EcontractToken econtractToken, String accountId, String sealId);
}
