package com.bc.rm.server.entity.econtract.result;

/**
 * 电子合同基础返回类
 *
 * @param <T> 泛型返回实体
 * @author zhou
 */
public class ApiBaseResult<T> {
    private String code;
    private String message;
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
