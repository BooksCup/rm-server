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
     * 查询个人所有印章
     *
     * @param econtractToken token
     * @param accountId      账号ID
     * @param offset         分页起始位置
     * @param size           单页数量
     * @return 个人所有印章
     */
    SealResultList getPersonalSeals(EcontractToken econtractToken, String accountId, Integer offset, Integer size);

    /**
     * 查询机构所有印章
     *
     * @param econtractToken token
     * @param orgId          机构ID
     * @param offset         分页起始位置
     * @param size           单页数量
     * @return 机构所有印章
     */
    SealResultList getOfficialSeals(EcontractToken econtractToken, String orgId, Integer offset, Integer size);
}