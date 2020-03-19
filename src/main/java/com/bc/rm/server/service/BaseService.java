package com.bc.rm.server.service;

import com.bc.rm.server.cons.Constant;
import com.bc.rm.server.entity.econtract.EcontractAccount;
import com.bc.rm.server.entity.econtract.EcontractOrg;
import com.bc.rm.server.entity.econtract.EcontractSeal;
import com.bc.rm.server.entity.econtract.EcontractToken;

import java.util.HashMap;
import java.util.Map;

/**
 * 基础业务类
 *
 * @author zhou
 */
public class BaseService {
    /**
     * 创建请求头
     *
     * @param econtractToken accessToken
     * @return 请求头map
     */
    protected Map<String, String> createHeader(EcontractToken econtractToken) {
        Map<String, String> headerMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        headerMap.put("Content-Type", "application/json");
        headerMap.put("X-Tsign-Open-App-Id", econtractToken.getAppId());
        headerMap.put("X-Tsign-Open-Token", econtractToken.getContent());
        return headerMap;
    }

    /**
     * 创建机构请求体
     *
     * @param econtractOrg 机构
     * @return 机构请求体map
     */
    protected Map<String, String> createOrgBody(EcontractOrg econtractOrg) {
        // 消息体
        Map<String, String> bodyMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);

        bodyMap.put("thirdPartyUserId", econtractOrg.getThirdPartyUserId());
        bodyMap.put("creator", econtractOrg.getCreator());
        bodyMap.put("name", econtractOrg.getName());
        bodyMap.put("idType", econtractOrg.getIdType());
        bodyMap.put("idNumber", econtractOrg.getIdNumber());
        bodyMap.put("orgLegalIdNumber", econtractOrg.getOrgLegalIdNumber());
        bodyMap.put("orgLegalName", econtractOrg.getOrgLegalName());
        return bodyMap;
    }

    /**
     * 创建个人账号请求体
     *
     * @param econtractAccount 个人账号
     * @return 个人账号请求体map
     */
    protected Map<String, String> createAccountBody(EcontractAccount econtractAccount) {
        // 消息体
        Map<String, String> bodyMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        bodyMap.put("thirdPartyUserId", econtractAccount.getThirdPartyUserId());
        bodyMap.put("name", econtractAccount.getName());
        bodyMap.put("idType", econtractAccount.getIdType());
        bodyMap.put("idNumber", econtractAccount.getIdNumber());
        bodyMap.put("mobile", econtractAccount.getMobile());
        bodyMap.put("email", econtractAccount.getEmail());
        return bodyMap;
    }

    /**
     * 创建个人模板印章请求体
     *
     * @param econtractSeal 印章
     * @return 个人模板印章请求体map
     */
    protected Map<String, String> createPersonalTemplateBody(EcontractSeal econtractSeal) {
        // 消息体
        Map<String, String> bodyMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        bodyMap.put("alias", econtractSeal.getAlias());
        bodyMap.put("color", econtractSeal.getColor());
        bodyMap.put("height", econtractSeal.getHeight() + "");
        bodyMap.put("type", econtractSeal.getType());
        bodyMap.put("width", econtractSeal.getWidth() + "");
        return bodyMap;
    }

    /**
     * 创建机构模板印章请求体
     *
     * @param econtractSeal 印章
     * @return 机构模板印章请求体map
     */
    protected Map<String, String> createOfficialTemplateBody(EcontractSeal econtractSeal) {
        // 消息体
        Map<String, String> bodyMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        bodyMap.put("alias", econtractSeal.getAlias());
        bodyMap.put("color", econtractSeal.getColor());
        bodyMap.put("height", econtractSeal.getHeight() + "");
        bodyMap.put("type", econtractSeal.getType());
        bodyMap.put("width", econtractSeal.getWidth() + "");
        bodyMap.put("htext", econtractSeal.getHtext());
        bodyMap.put("qtext", econtractSeal.getQtext());
        bodyMap.put("central", econtractSeal.getCentral());
        return bodyMap;
    }
}
