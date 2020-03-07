package com.bc.rm.server.mapper;

import com.bc.rm.server.entity.econtract.EcontractToken;

import java.util.List;

/**
 * 电子合同token
 *
 * @author zhou
 */
public interface EcontractTokenMapper {
    /**
     * 新增token
     *
     * @param econtractToken token
     */
    void addEcontractToken(EcontractToken econtractToken);

    /**
     * 获取token列表
     *
     * @return token列表
     */
    List<EcontractToken> getEcontractTokenList();

    /**
     * 修改token
     *
     * @param econtractToken token
     */
    void updateEcontractToken(EcontractToken econtractToken);
}
