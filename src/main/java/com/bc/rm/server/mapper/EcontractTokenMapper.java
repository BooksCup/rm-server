package com.bc.rm.server.mapper;

import com.bc.rm.server.entity.econtract.EcontractToken;

import java.util.List;

/**
 * 电子合同
 *
 * @author zhou
 */
public interface EcontractTokenMapper {
    void addEcontractToken(EcontractToken econtractToken);

    List<EcontractToken> getEcontractTokenList();

    void updateEcontractToken(EcontractToken econtractToken);
}
