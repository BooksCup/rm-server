package com.bc.rm.server.mapper;

import com.bc.rm.server.entity.econtract.EcontractAccount;

import java.util.Map;

/**
 * 电子合同个人账号
 *
 * @author zhou
 */
public interface EcontractAccountMapper {
    /**
     * 新增电子合同个人账号
     *
     * @param econtractAccount 电子合同个人账号
     */
    void addEcontractAccount(EcontractAccount econtractAccount);

    /**
     * 修改电子合同个人账号
     *
     * @param econtractAccount 电子合同个人账号
     */
    void updateEcontractAccount(EcontractAccount econtractAccount);

    /**
     * 删除/注销个人账号(按照账号ID注销)
     *
     * @param accountId 账号ID
     */
    void deleteEcontractAccountByAccountId(String accountId);

    /**
     * 删除/注销个人账号(按照第三方用户ID注销)
     *
     * @param thirdPartyUserId 第三方用户ID
     */
    void deleteEcontractAccountByThirdPartyUserId(String thirdPartyUserId);

    /**
     * 修改签署密码
     *
     * @param paramMap 参数map
     */
    void updateSignPwd(Map<String, String> paramMap);
}
