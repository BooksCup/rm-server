package com.bc.rm.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bc.rm.server.cons.Constant;
import com.bc.rm.server.entity.econtract.EcontractAccount;
import com.bc.rm.server.entity.econtract.EcontractToken;
import com.bc.rm.server.entity.econtract.result.Account;
import com.bc.rm.server.entity.econtract.result.ApiBaseResult;
import com.bc.rm.server.mapper.EcontractAccountMapper;
import com.bc.rm.server.service.EcontractAccountService;
import com.bc.rm.server.util.HttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 电子合同个人账号
 *
 * @author zhou
 */
@Service("econtractAccountService")
public class EcontractAccountServiceImpl implements EcontractAccountService {
    private static final Logger logger = LoggerFactory.getLogger(EcontractTokenServiceImpl.class);

    @Resource
    private EcontractAccountMapper econtractAccountMapper;

    /**
     * 新增电子合同个人账号
     *
     * @param econtractToken   token
     * @param econtractAccount 个人账号
     * @return 个人账号
     */
    @Override
    public EcontractAccount createEcontractAccount(EcontractToken econtractToken, EcontractAccount econtractAccount) {
        String eSignHost = Constant.E_SIGN_BASE_URL;
        String path = "/v1/accounts/createByThirdPartyUserId";

        // 消息头，主要包含鉴权信息
        Map<String, String> headerMap = createHeader(econtractToken);

        // 查询参数
        Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);

        // 消息体
        Map<String, String> bodyMap = createAccountBody(econtractAccount);
        String body = JSON.toJSONString(bodyMap);

        try {
            HttpResponse response = HttpUtil.doPost(eSignHost, path, headerMap, queryMap, body);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("result: " + result);

            TypeReference<ApiBaseResult<Account>> typeReference = new TypeReference<ApiBaseResult<Account>>() {
            };
            ApiBaseResult<Account> apiBaseResult = JSON.parseObject(result, typeReference);
            logger.info("code: " + apiBaseResult.getCode());
            logger.info("message: " + apiBaseResult.getMessage());

            econtractAccount.setApiResultCode(apiBaseResult.getCode());
            econtractAccount.setApiResultMessage(apiBaseResult.getMessage());

            if (Constant.E_CONTRACT_SUCCESS_RESULT_CODE.equals(apiBaseResult.getCode())) {
                logger.info("accountId: " + apiBaseResult.getData().getAccountId());
                econtractAccount.setId(apiBaseResult.getData().getAccountId());
                econtractAccountMapper.addEcontractAccount(econtractAccount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return econtractAccount;
    }

    /**
     * 修改电子合同个人账号
     *
     * @param econtractToken   accessToken
     * @param econtractAccount 个人账号
     * @return 个人账号
     */
    @Override
    public EcontractAccount updateEcontractAccount(EcontractToken econtractToken, EcontractAccount econtractAccount) {
        String eSignHost = Constant.E_SIGN_BASE_URL;
        String path = "/v1/accounts/" + econtractAccount.getId();

        // 消息头，主要包含鉴权信息
        Map<String, String> headerMap = createHeader(econtractToken);

        // 查询参数
        Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);

        // 消息体
        Map<String, String> bodyMap = createAccountBody(econtractAccount);
        String body = JSON.toJSONString(bodyMap);

        try {
            HttpResponse response = HttpUtil.doPut(eSignHost, path, headerMap, queryMap, body);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("result: " + result);

            TypeReference<ApiBaseResult<Account>> typeReference = new TypeReference<ApiBaseResult<Account>>() {
            };
            ApiBaseResult<Account> apiBaseResult = JSON.parseObject(result, typeReference);
            logger.info("code: " + apiBaseResult.getCode());
            logger.info("message: " + apiBaseResult.getMessage());

            econtractAccount.setApiResultCode(apiBaseResult.getCode());
            econtractAccount.setApiResultMessage(apiBaseResult.getMessage());

            if (Constant.E_CONTRACT_SUCCESS_RESULT_CODE.equals(apiBaseResult.getCode())) {
                econtractAccount.setSuccessFlag(true);
                econtractAccountMapper.updateEcontractAccount(econtractAccount);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return econtractAccount;
    }

    /**
     * 查询个人账号(按照账号ID查询)
     *
     * @param econtractToken accessToken
     * @param accountId      账号ID
     * @return 个人账号
     */
    @Override
    public Account getAccountByAccountId(EcontractToken econtractToken, String accountId) {
        String eSignHost = Constant.E_SIGN_BASE_URL;
        String path = "/v1/accounts/" + accountId;
        try {
            Map<String, String> headerMap = createHeader(econtractToken);
            Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            HttpResponse response = HttpUtil.doGet(eSignHost, path, headerMap, queryMap);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("result: " + result);

            TypeReference<ApiBaseResult<Account>> typeReference = new TypeReference<ApiBaseResult<Account>>() {
            };
            ApiBaseResult<Account> apiBaseResult = JSON.parseObject(result, typeReference);
            logger.info("code: " + apiBaseResult.getCode());
            logger.info("message: " + apiBaseResult.getMessage());

            if (Constant.E_CONTRACT_SUCCESS_RESULT_CODE.equals(apiBaseResult.getCode())) {
                return apiBaseResult.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Account();
    }


    /**
     * 创建请求头
     *
     * @param econtractToken accessToken
     * @return 请求头map
     */
    private Map<String, String> createHeader(EcontractToken econtractToken) {
        Map<String, String> headerMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        headerMap.put("Content-Type", "application/json");
        headerMap.put("X-Tsign-Open-App-Id", econtractToken.getAppId());
        headerMap.put("X-Tsign-Open-Token", econtractToken.getContent());
        return headerMap;
    }

    /**
     * 创建个人账号请求体
     *
     * @param econtractAccount 个人账号
     * @return 个人账号请求体map
     */
    private Map<String, String> createAccountBody(EcontractAccount econtractAccount) {
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
}
