package com.bc.rm.server.service;

import com.bc.rm.server.entity.econtract.EcontractSeal;
import com.bc.rm.server.entity.econtract.EcontractToken;

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
     * @param econtractToken token
     * @param econtractSeal 印章
     * @return 企业模板印章
     */
    EcontractSeal createSealOfficialTemplate(EcontractToken econtractToken, EcontractSeal econtractSeal);
}
