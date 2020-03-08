package com.bc.rm.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bc.rm.server.cons.Constant;
import com.bc.rm.server.entity.econtract.EcontractAccount;
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
     * @param econtractAccount 电子合同个人账号
     */
    @Override
    public EcontractAccount createEcontractAccount(EcontractAccount econtractAccount) {
        String eSignHost = Constant.E_SIGN_BASE_URL;
        String path = "/v1/accounts/createByThirdPartyUserId";
        Map<String, String> headerMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        headerMap.put("Content-Type", "application/json");
        headerMap.put("X-Tsign-Open-App-Id", "4438775919");
        headerMap.put("X-Tsign-Open-Token", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJnSWQiOiI4N2I4YmJhNGY2N2U0ZjRiODQ3Njc2M2FmNTRjZGYxYSIsImFwcElkIjoiNDQzODc3NTkxOSIsIm9JZCI6ImJiZDNlYTExZWY0ZDQyNmI4NTYzNDhmYjg1MDYxM2ZmIiwidGltZXN0YW1wIjoxNTgzODAzODk1Njg3fQ.9bhGSW6jyCLElByznRAiqSxOAequ9589mL1vmbdDaMU");
        Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        Map<String, String> bodyMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        bodyMap.put("thirdPartyUserId", econtractAccount.getThirdPartyUserId());
        bodyMap.put("name", econtractAccount.getName());
        bodyMap.put("idType", econtractAccount.getIdType());
        bodyMap.put("idNumber", econtractAccount.getIdNumber());
        bodyMap.put("mobile", econtractAccount.getMobile());
        bodyMap.put("mail", econtractAccount.getMail());
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
}
