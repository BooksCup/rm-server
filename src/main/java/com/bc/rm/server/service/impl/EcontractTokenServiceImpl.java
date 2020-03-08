package com.bc.rm.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bc.rm.server.cons.Constant;
import com.bc.rm.server.entity.econtract.EcontractToken;
import com.bc.rm.server.entity.econtract.result.AccessToken;
import com.bc.rm.server.entity.econtract.result.ApiBaseResult;
import com.bc.rm.server.mapper.EcontractTokenMapper;
import com.bc.rm.server.service.EcontractTokenService;
import com.bc.rm.server.util.CommonUtil;
import com.bc.rm.server.util.HttpUtil;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电子合同token
 *
 * @author zhou
 */
@Service("econtractTokenService")
public class EcontractTokenServiceImpl implements EcontractTokenService {

    private static final Logger logger = LoggerFactory.getLogger(EcontractTokenServiceImpl.class);

    @Resource
    private EcontractTokenMapper econtractTokenMapper;

    /**
     * 调用第三方服务 生成token
     *
     * @param econtractToken token入参
     * @return 带有token和有效时间的完整token bean
     */
    @Override
    public EcontractToken generateToken(EcontractToken econtractToken) {

        String eSignHost = Constant.E_SIGN_BASE_URL;
        String path = "/v1/oauth2/access_token";
        Map<String, String> headerMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
        queryMap.put("appId", econtractToken.getAppId());
        queryMap.put("secret", econtractToken.getSecret());
        queryMap.put("grantType", econtractToken.getGrantType());

        try {
            HttpResponse response = HttpUtil.doGet(eSignHost, path, headerMap, queryMap);

            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("result: " + result);

            TypeReference<ApiBaseResult<AccessToken>> typeReference = new TypeReference<ApiBaseResult<AccessToken>>() {
            };
            ApiBaseResult<AccessToken> apiBaseResult = JSON.parseObject(result, typeReference);
            logger.info("code: " + apiBaseResult.getCode());
            logger.info("message: " + apiBaseResult.getMessage());

            econtractToken.setApiResultCode(apiBaseResult.getCode());
            econtractToken.setApiResultMessage(apiBaseResult.getMessage());

            if (Constant.E_CONTRACT_SUCCESS_RESULT_CODE.equals(apiBaseResult.getCode())) {
                econtractToken.setSuccessFlag(true);
                logger.info("expiresIn: " + apiBaseResult.getData().getExpiresIn());
                logger.info("token: " + apiBaseResult.getData().getToken());
                logger.info("refreshToken: " + apiBaseResult.getData().getRefreshToken());
                econtractToken.setContent(apiBaseResult.getData().getToken());
                econtractToken.setExpiresIn(apiBaseResult.getData().getExpiresIn());
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                econtractToken.setExpiryTime(CommonUtil.formatTimeStamp(econtractToken.getExpiresIn(), formatter));
            } else {
                econtractToken.setSuccessFlag(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return econtractToken;
    }

    /**
     * 获取accessToken(从DB)
     *
     * @return accessToken
     */
    @Override
    public EcontractToken getAccessTokenFromDB() {
        List<EcontractToken> econtractTokenList = getEcontractTokenList();
        if (CollectionUtils.isEmpty(econtractTokenList)) {
            return null;
        } else {
            return econtractTokenList.get(0);
        }
    }


    /**
     * 新增token
     *
     * @param econtractToken token
     */
    @Override
    public void addEcontractToken(EcontractToken econtractToken) {
        econtractTokenMapper.addEcontractToken(econtractToken);
    }

    /**
     * 获取token列表
     *
     * @return token列表
     */
    @Override
    public List<EcontractToken> getEcontractTokenList() {
        return econtractTokenMapper.getEcontractTokenList();
    }

    /**
     * 修改token
     *
     * @param econtractToken token
     */
    @Override
    public void updateEcontractToken(EcontractToken econtractToken) {
        econtractTokenMapper.updateEcontractToken(econtractToken);
    }

}
