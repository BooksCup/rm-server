package com.bc.rm.server.service;

import com.bc.rm.server.entity.econtract.EcontractToken;

import java.util.List;

public interface EcontractTokenService {

    EcontractToken generateToken(EcontractToken econtractToken);
    void addEcontractToken(EcontractToken econtractToken);

    List<EcontractToken> getEcontractTokenList();

    void updateEcontractToken(EcontractToken econtractToken);
}
