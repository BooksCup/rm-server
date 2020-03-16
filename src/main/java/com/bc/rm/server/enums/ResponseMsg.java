package com.bc.rm.server.enums;

/**
 * 返回消息
 *
 * @author zhou
 */
public enum ResponseMsg {
    /**
     * 接口返回信息
     */
    UPDATE_USER_SUCCESS("UPDATE_USER_SUCCESS", "编辑用户成功"),
    UPDATE_USER_ERROR("UPDATE_USER_ERROR", "编辑用户失败"),

    DELETE_USER_SUCCESS("DELETE_USER_SUCCESS", "删除用户成功"),
    DELETE_USER_ERROR("DELETE_USER_ERROR", "删除用户失败"),

    UPDATE_SPRINT_SUCCESS("UPDATE_SPRINT_SUCCESS", "编辑迭代成功"),
    UPDATE_SPRINT_ERROR("UPDATE_SPRINT_ERROR", "编辑迭代失败"),

    DELETE_SPRINT_SUCCESS("DELETE_SPRINT_SUCCESS", "删除迭代成功"),
    DELETE_SPRINT_ERROR("DELETE_SPRINT_ERROR", "删除迭代失败"),

    DELETE_BACKLOG_SUCCESS("DELETE_BACKLOG_SUCCESS", "删除待办事项成功"),
    DELETE_BACKLOG_ERROR("DELETE_BACKLOG_ERROR", "删除待办事项失败"),

    DELETE_E_CONTRACT_ACCOUNT_SUCCESS("DELETE_E_CONTRACT_ACCOUNT_SUCCESS", "删除电子合同账户成功"),
    DELETE_E_CONTRACT_ACCOUNT_ERROR("DELETE_E_CONTRACT_ACCOUNT_ERROR", "删除电子合同账户失败"),

    SET_SIGN_PWD_SUCCESS("SET_SIGN_PWD_SUCCESS", "设置签署密码成功"),
    SET_SIGN_PWD_ERROR("SET_SIGN_PWD_ERROR", "设置签署密码失败"),;

    ResponseMsg(String responseCode, String responseMessage) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
    }

    private String responseCode;
    private String responseMessage;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }
}
