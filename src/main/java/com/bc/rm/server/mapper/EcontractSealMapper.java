package com.bc.rm.server.mapper;

import com.bc.rm.server.entity.econtract.EcontractSeal;


/**
 * 印章
 *
 * @author zhou
 */
public interface EcontractSealMapper {
    /**
     * 新增印章
     *
     * @param econtractSeal 印章
     */
    void addEcontractSeal(EcontractSeal econtractSeal);
}
