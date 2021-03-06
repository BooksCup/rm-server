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
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 电子合同机构
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

    /**
     * 修改电子合同机构账号
     *
     * @param econtractToken accessToken
     * @param econtractOrg   机构账号
     * @return 机构账号
     */
    @Override
    public EcontractOrg updateEcontractOrgByOrgId(EcontractToken econtractToken, EcontractOrg econtractOrg) {
        String eSignHost = Constant.E_SIGN_BASE_URL;
        String path = "/v1/organizations/" + econtractOrg.getOrgId();

        // 消息头，主要包含鉴权信息
        Map<String, String> headerMap = createHeader(econtractToken);

        // 查询参数
        Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);

        // 消息体
        Map<String, String> bodyMap = createOrgBody(econtractOrg);
        String body = JSON.toJSONString(bodyMap);

        try {
            HttpResponse response = HttpUtil.doPut(eSignHost, path, headerMap, queryMap, body);
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
                econtractOrg.setSuccessFlag(true);
                econtractOrgMapper.updateEcontractOrgByOrgId(econtractOrg);
            } else {
                econtractOrg.setSuccessFlag(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return econtractOrg;
    }

    /**
     * 查询机构账号(按照机构ID查询)
     *
     * @param econtractToken accessToken
     * @param orgId          机构ID
     * @return 机构账号
     */
    @Override
    public EcontractOrg getOrgByOrgId(EcontractToken econtractToken, String orgId) {
        String eSignHost = Constant.E_SIGN_BASE_URL;
        String path = "/v1/organizations/" + orgId;
        try {
            Map<String, String> headerMap = createHeader(econtractToken);
            Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            HttpResponse response = HttpUtil.doGet(eSignHost, path, headerMap, queryMap);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("result: " + result);

            TypeReference<ApiBaseResult<EcontractOrg>> typeReference = new TypeReference<ApiBaseResult<EcontractOrg>>() {
            };
            ApiBaseResult<EcontractOrg> apiBaseResult = JSON.parseObject(result, typeReference);
            logger.info("code: " + apiBaseResult.getCode());
            logger.info("message: " + apiBaseResult.getMessage());

            if (Constant.E_CONTRACT_SUCCESS_RESULT_CODE.equals(apiBaseResult.getCode())) {
                return apiBaseResult.getData();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new EcontractOrg();
    }

    /**
     * 按照机构账号ID注销机构账号
     *
     * @param econtractToken token
     * @param orgId          机构账号ID
     * @return true:删除成功 false:删除失败
     */
    @Override
    public boolean deleteOrgByOrgId(EcontractToken econtractToken, String orgId) {
        boolean deleteFlag;
        String eSignHost = Constant.E_SIGN_BASE_URL;
        String path = "/v1/organizations/" + orgId;
        try {
            Map<String, String> headerMap = createHeader(econtractToken);
            Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            HttpResponse response = HttpUtil.doDelete(eSignHost, path, headerMap, queryMap);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("result: " + result);
            TypeReference<ApiBaseResult<?>> typeReference = new TypeReference<ApiBaseResult<?>>() {
            };
            ApiBaseResult<?> apiBaseResult = JSON.parseObject(result, typeReference);
            logger.info("code: " + apiBaseResult.getCode());
            logger.info("message: " + apiBaseResult.getMessage());
            if (Constant.E_CONTRACT_SUCCESS_RESULT_CODE.equals(apiBaseResult.getCode())) {
                // api删除成功
                // 删除db里的数据
                econtractOrgMapper.deleteOrgByOrgId(orgId);

                deleteFlag = true;
            } else {
                deleteFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("deleteOrgByOrgId error: " + e.getMessage());
            deleteFlag = false;
        }
        return deleteFlag;
    }

    /**
     * 删除/注销机构账号(按照第三方机构ID注销)
     *
     * @param econtractToken   token
     * @param thirdPartyUserId 第三方机构ID
     * @return true:删除成功 false:删除失败
     */
    @Override
    public boolean deleteOrgByThirdPartyUserId(EcontractToken econtractToken, String thirdPartyUserId) {
        boolean deleteFlag;
        String eSignHost = Constant.E_SIGN_BASE_URL;
        String path = "/v1/organizations/deleteByThirdId";
        try {
            Map<String, String> headerMap = createHeader(econtractToken);

            Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            queryMap.put("thirdPartyUserId", thirdPartyUserId);

            HttpResponse response = HttpUtil.doDelete(eSignHost, path, headerMap, queryMap);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("result: " + result);
            TypeReference<ApiBaseResult<?>> typeReference = new TypeReference<ApiBaseResult<?>>() {
            };
            ApiBaseResult<?> apiBaseResult = JSON.parseObject(result, typeReference);
            logger.info("code: " + apiBaseResult.getCode());
            logger.info("message: " + apiBaseResult.getMessage());
            if (Constant.E_CONTRACT_SUCCESS_RESULT_CODE.equals(apiBaseResult.getCode())) {
                // api删除成功
                // 删除db里的数据
                econtractOrgMapper.deleteOrgByThirdPartyUserId(thirdPartyUserId);

                deleteFlag = true;
            } else {
                deleteFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("deleteOrgByThirdPartyUserId error: " + e.getMessage());
            deleteFlag = false;
        }
        return deleteFlag;
    }

    /**
     * 获取电子合同机构账户列表
     *
     * @param pageNum  当前分页
     * @param pageSize 每个分页大小
     * @return 电子合同机构账户分页列表
     */
    @Override
    public PageInfo<EcontractOrg> getEcontractOrgListByPageInfo(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Map<String, String> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);

        List<EcontractOrg> econtractOrgList =
                econtractOrgMapper.getEcontractOrgListByParams(paramMap);
        return new PageInfo<>(econtractOrgList);
    }
}
