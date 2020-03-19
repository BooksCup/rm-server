package com.bc.rm.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bc.rm.server.cons.Constant;
import com.bc.rm.server.entity.econtract.EcontractSeal;
import com.bc.rm.server.entity.econtract.EcontractToken;
import com.bc.rm.server.entity.econtract.result.ApiBaseResult;
import com.bc.rm.server.entity.econtract.result.SealResultList;
import com.bc.rm.server.mapper.EcontractSealMapper;
import com.bc.rm.server.service.BaseService;
import com.bc.rm.server.service.EcontractSealService;
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
 * 印章
 *
 * @author zhou
 */
@Service("econtractSealService")
public class EcontractSealServiceImpl extends BaseService implements EcontractSealService {
    private static final Logger logger = LoggerFactory.getLogger(EcontractSealServiceImpl.class);

    @Resource
    private EcontractSealMapper econtractSealMapper;

    /**
     * 创建个人模板印章
     *
     * @param econtractToken token
     * @param econtractSeal  印章
     * @return 个人模板印章
     */
    @Override
    public EcontractSeal createSealPersonalTemplate(EcontractToken econtractToken, EcontractSeal econtractSeal) {
        String eSignHost = Constant.E_SIGN_BASE_URL;
        String path = "/v1/accounts/" + econtractSeal.getAccountId() + "/seals/personaltemplate";

        // 消息头，主要包含鉴权信息
        Map<String, String> headerMap = createHeader(econtractToken);

        // 查询参数
        Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);

        // 消息体
        Map<String, String> bodyMap = createOfficialTemplateBody(econtractSeal);
        String body = JSON.toJSONString(bodyMap);

        try {
            HttpResponse response = HttpUtil.doPost(eSignHost, path, headerMap, queryMap, body);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("result: " + result);

            TypeReference<ApiBaseResult<EcontractSeal>> typeReference = new TypeReference<ApiBaseResult<EcontractSeal>>() {
            };
            ApiBaseResult<EcontractSeal> apiBaseResult = JSON.parseObject(result, typeReference);
            logger.info("code: " + apiBaseResult.getCode());
            logger.info("message: " + apiBaseResult.getMessage());

            econtractSeal.setApiResultCode(apiBaseResult.getCode());
            econtractSeal.setApiResultMessage(apiBaseResult.getMessage());

            // 个人账户
            econtractSeal.setAccountType(Constant.SEAL_ACCOUNT_TYPE_PERSONAL);
            // 通过模板创建
            econtractSeal.setCreateType(Constant.SEAL_CREATE_TYPE_TEMPLATE);

            handleApiResult(apiBaseResult, econtractSeal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return econtractSeal;
    }

    /**
     * 创建企业模板印章
     *
     * @param econtractToken token
     * @param econtractSeal  印章
     * @return 企业模板印章
     */
    @Override
    public EcontractSeal createSealOfficialTemplate(EcontractToken econtractToken, EcontractSeal econtractSeal) {
        String eSignHost = Constant.E_SIGN_BASE_URL;
        String path = "/v1/organizations/" + econtractSeal.getOrgId() + "/seals/officialtemplate";

        // 消息头，主要包含鉴权信息
        Map<String, String> headerMap = createHeader(econtractToken);

        // 查询参数
        Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);

        // 消息体
        Map<String, String> bodyMap = createOfficialTemplateBody(econtractSeal);
        String body = JSON.toJSONString(bodyMap);

        try {
            HttpResponse response = HttpUtil.doPost(eSignHost, path, headerMap, queryMap, body);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("result: " + result);

            TypeReference<ApiBaseResult<EcontractSeal>> typeReference = new TypeReference<ApiBaseResult<EcontractSeal>>() {
            };
            ApiBaseResult<EcontractSeal> apiBaseResult = JSON.parseObject(result, typeReference);
            logger.info("code: " + apiBaseResult.getCode());
            logger.info("message: " + apiBaseResult.getMessage());

            econtractSeal.setApiResultCode(apiBaseResult.getCode());
            econtractSeal.setApiResultMessage(apiBaseResult.getMessage());

            // 企业账户
            econtractSeal.setAccountType(Constant.SEAL_ACCOUNT_TYPE_OFFICIAL);
            // 通过模板创建
            econtractSeal.setCreateType(Constant.SEAL_CREATE_TYPE_TEMPLATE);

            handleApiResult(apiBaseResult, econtractSeal);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return econtractSeal;
    }

    /**
     * 查询个人所有印章
     *
     * @param econtractToken token
     * @param accountId      账号ID
     * @param offset         分页起始位置
     * @param size           单页数量
     * @return 个人所有印章
     */
    @Override
    public SealResultList getPersonalSeals(EcontractToken econtractToken, String accountId, Integer offset, Integer size) {
        String eSignHost = Constant.E_SIGN_BASE_URL;
        String path = "/v1/accounts/" + accountId + "/seals";

        // 消息头，主要包含鉴权信息
        Map<String, String> headerMap = createHeader(econtractToken);

        // 查询参数
        Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        queryMap.put("offset", String.valueOf(offset));
        queryMap.put("size", String.valueOf(size));

        try {
            HttpResponse response = HttpUtil.doGet(eSignHost, path, headerMap, queryMap);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("result: " + result);

            TypeReference<ApiBaseResult<SealResultList>> typeReference = new TypeReference<ApiBaseResult<SealResultList>>() {
            };
            ApiBaseResult<SealResultList> apiBaseResult = JSON.parseObject(result, typeReference);
            logger.info("code: " + apiBaseResult.getCode());
            logger.info("message: " + apiBaseResult.getMessage());
            if (Constant.E_CONTRACT_SUCCESS_RESULT_CODE.equals(apiBaseResult.getCode())) {
                return apiBaseResult.getData();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return new SealResultList();
    }


    private void handleApiResult(ApiBaseResult<EcontractSeal> apiBaseResult, EcontractSeal econtractSeal) {
        if (Constant.E_CONTRACT_SUCCESS_RESULT_CODE.equals(apiBaseResult.getCode())) {
            // 创建成功插入db
            logger.info("sealId: " + apiBaseResult.getData().getSealId());
            econtractSeal.setSealId(apiBaseResult.getData().getSealId());
            econtractSeal.setFileKey(apiBaseResult.getData().getFileKey());
            econtractSeal.setUrl(apiBaseResult.getData().getUrl());

            econtractSealMapper.addEcontractSeal(econtractSeal);
        }
    }
}
