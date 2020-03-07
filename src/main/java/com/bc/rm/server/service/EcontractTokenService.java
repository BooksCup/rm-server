package com.bc.rm.server.service;

import com.bc.rm.server.entity.econtract.EcontractToken;

import java.util.List;

/**
 * 电子合同token
 *
 * @author zhou
 */
public interface EcontractTokenService {

    /**
     * 调用第三方服务 生成token
     *
     * @param econtractToken token入参
     * @return 带有token和有效时间的完整token bean
     */
    EcontractToken generateToken(EcontractToken econtractToken);

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
