package com.bc.rm.server.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bc.rm.server.cons.Constant;
import com.bc.rm.server.entity.econtract.EcontractAccount;
import com.bc.rm.server.entity.econtract.EcontractOrg;
import com.bc.rm.server.entity.econtract.EcontractToken;
import com.bc.rm.server.entity.econtract.result.Account;
import com.bc.rm.server.entity.econtract.result.ApiBaseResult;
import com.bc.rm.server.mapper.EcontractAccountMapper;
import com.bc.rm.server.service.BaseService;
import com.bc.rm.server.service.EcontractAccountService;
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
 * 电子合同个人账号
 *
 * @author zhou
 */
@Service("econtractAccountService")
public class EcontractAccountServiceImpl extends BaseService implements EcontractAccountService {
    private static final Logger logger = LoggerFactory.getLogger(EcontractAccountServiceImpl.class);

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
    public EcontractAccount updateEcontractAccount(EcontractToken econtractToken, EcontractAccount econtractAccount) throws Exception {
        String eSignHost = Constant.E_SIGN_BASE_URL;
        String path = "/v1/accounts/" + econtractAccount.getId();

        // 消息头，主要包含鉴权信息
        Map<String, String> headerMap = createHeader(econtractToken);

        // 查询参数
        Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);

        // 消息体
        Map<String, String> bodyMap = createAccountBody(econtractAccount);
        String body = JSON.toJSONString(bodyMap);

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
        } else {
            econtractAccount.setSuccessFlag(false);
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
     * 查询个人账号(按照第三方用户ID查询)
     *
     * @param econtractToken   accessToken
     * @param thirdPartyUserId 第三方用户ID
     * @return 个人账号
     */
    @Override
    public Account getAccountByThirdPartyUserId(EcontractToken econtractToken, String thirdPartyUserId) {
        String eSignHost = Constant.E_SIGN_BASE_URL;
        String path = "/v1/accounts/getByThirdId";
        try {
            Map<String, String> headerMap = createHeader(econtractToken);
            Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            queryMap.put("thirdPartyUserId", thirdPartyUserId);

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
     * 删除/注销个人账号(按照账号ID注销)
     *
     * @param econtractToken accessToken
     * @param accountId      账号ID
     * @return true:删除成功  false:删除失败
     */
    @Override
    public boolean deleteAccountByAccountId(EcontractToken econtractToken, String accountId) {
        boolean deleteFlag;
        String eSignHost = Constant.E_SIGN_BASE_URL;
        String path = "/v1/accounts/" + accountId;
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
                econtractAccountMapper.deleteEcontractAccountByAccountId(accountId);

                deleteFlag = true;
            } else {
                deleteFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("deleteAccountByAccountId error: " + e.getMessage());
            deleteFlag = false;
        }
        return deleteFlag;
    }

    /**
     * 删除/注销个人账号(按照第三方用户ID注销)
     *
     * @param econtractToken   accessToken
     * @param thirdPartyUserId 第三方用户ID
     * @return true:删除成功  false:删除失败
     */
    @Override
    public boolean deleteAccountByThirdPartyUserId(EcontractToken econtractToken, String thirdPartyUserId) {
        boolean deleteFlag;
        String eSignHost = Constant.E_SIGN_BASE_URL;
        String path = "/v1/accounts/deleteByThirdId";
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
                econtractAccountMapper.deleteEcontractAccountByThirdPartyUserId(thirdPartyUserId);

                deleteFlag = true;
            } else {
                deleteFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("deleteAccountByThirdPartyUserId error: " + e.getMessage());
            deleteFlag = false;
        }
        return deleteFlag;

    }

    /**
     * 设置签署密码
     *
     * @param econtractToken token
     * @param accountId      账号ID
     * @param password       密码
     * @return true:设置成功  false:设置失败
     */
    @Override
    public boolean setSignPwd(EcontractToken econtractToken, String accountId, String password) {
        boolean setFlag;
        String eSignHost = Constant.E_SIGN_BASE_URL;
        String path = "/v1/accounts/" + accountId + "/setSignPwd";
        try {
            Map<String, String> headerMap = createHeader(econtractToken);
            Map<String, String> queryMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);

            Map<String, String> bodyMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
            bodyMap.put("password", password);
            String body = JSON.toJSONString(bodyMap);

            HttpResponse response = HttpUtil.doPost(eSignHost, path, headerMap, queryMap, body);
            String result = EntityUtils.toString(response.getEntity(), "utf-8");
            logger.info("result: " + result);
            TypeReference<ApiBaseResult<?>> typeReference = new TypeReference<ApiBaseResult<?>>() {
            };
            ApiBaseResult<?> apiBaseResult = JSON.parseObject(result, typeReference);
            logger.info("code: " + apiBaseResult.getCode());
            logger.info("message: " + apiBaseResult.getMessage());
            if (Constant.E_CONTRACT_SUCCESS_RESULT_CODE.equals(apiBaseResult.getCode())) {
                logger.info("data: " + apiBaseResult.getData());
                // 修改成功更新db
                Map<String, String> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);
                paramMap.put("accountId", accountId);
                paramMap.put("password", password);
                econtractAccountMapper.updateSignPwd(paramMap);

                setFlag = true;
            } else {
                setFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("setSignPwd error: " + e.getMessage());
            setFlag = false;
        }
        return setFlag;
    }

    /**
     * 获取电子合同个人账户列表
     *
     * @param pageNum  当前分页
     * @param pageSize 每个分页大小
     * @return 电子合同个人账户分页列表
     */
    @Override
    public PageInfo<EcontractAccount> getEcontractAccountListByPageInfo(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        Map<String, String> paramMap = new HashMap<>(Constant.DEFAULT_HASH_MAP_CAPACITY);

        List<EcontractAccount> econtractAccountList =
                econtractAccountMapper.getEcontractAccountListByParams(paramMap);
        return new PageInfo<>(econtractAccountList);
    }

    /**
     * 查询电子合同个人账户列表
     *
     * @param keyword 关键字
     * @return 电子合同个人账户列表
     */
    @Override
    public List<EcontractAccount> searchEcontractAccount(String keyword) {
        return econtractAccountMapper.searchEcontractAccount(keyword);
    }
}
