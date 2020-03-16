package com.bc.rm.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bc.rm.server.cons.Constant;
import com.bc.rm.server.entity.econtract.EcontractOrg;
import com.bc.rm.server.entity.econtract.EcontractToken;
import com.bc.rm.server.entity.econtract.result.ApiBaseResult;
import com.bc.rm.server.mapper.EcontractOrgMapper;
import com.bc.rm.server.service.BaseService;
import com.bc.rm.server.service.EcontractOrgService;
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
 * 电子合同机构
 * orgId:e0b6e399887940c384149c06ebd6557e
 *
 * @author zhou
 */
@Service("econtractOrgService")
public class EcontractOrgServiceImpl extends BaseService implements EcontractOrgService {

    private static final Logger logger = LoggerFactory.getLogger(EcontractOrgServiceImpl.class);

    @Resource
    private EcontractOrgMapper econtractOrgMapper;

    /**
     * 创建机构账号
     *
     * @param econtractToken token
     * @param econtractOrg   机构账号
     * @return 机构账号
     */
    @Override
    public EcontractOrg createEcontractOrg(EcontractToken econtractToken, EcontractOrg econtractOrg) {
        String eSignHost = Constant.E_SIGN_BASE_URL;
        String path = "/v1/organizations/createByThirdPartyUserId";

        // 消息头，主要包含鉴权信息
        Map<String, String> headerMap = createHeader(econtractToken);

        // 查询参数
        Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);

        // 消息体
        Map<String, String> bodyMap = createOrgBody(econtractOrg);
        String body = JSON.toJSONString(bodyMap);

        try {
            HttpResponse response = HttpUtil.doPost(eSignHost, path, headerMap, queryMap, body);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("result: " + result);

            TypeReference<ApiBaseResult<EcontractOrg>> typeReference = new TypeReference<ApiBaseResult<EcontractOrg>>() {
            };
            ApiBaseResult<EcontractOrg> apiBaseResult = JSON.parseObject(result, typeReference);
            logger.info("code: " + apiBaseResult.getCode());
            logger.info("message: " + apiBaseResult.getMessage());

            econtractOrg.setApiResultCode(apiBaseResult.getCode());
            econtractOrg.setApiResultMessage(apiBaseResult.getMessage());

            if (Constant.E_CONTRACT_SUCCESS_RESULT_CODE.equals(apiBaseResult.getCode())) {
                // 创建成功插入db
                logger.info("orgId: " + apiBaseResult.getData().getOrgId());
                econtractOrg.setOrgId(apiBaseResult.getData().getOrgId());

                econtractOrgMapper.addEcontractOrg(econtractOrg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return econtractOrg;
    }
}
