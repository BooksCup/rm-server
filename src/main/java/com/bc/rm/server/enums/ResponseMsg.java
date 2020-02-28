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
    DELETE_USER_ERROR("DELETE_USER_ERROR", "删除用户失败");

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
