package com.bc.rm.server.entity.printer.result;

/**
 * 打印机api基础返回类
 *
 * @param <T> 泛型返回实体
 * @author zhou
 */
public class PrinterApiBaseResult<T> {
    private String msg;
    private String ret;
    private Long serverExecutedTime;
    private T data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getRet() {
        return ret;
    }

    public void setRet(String ret) {
        this.ret = ret;
    }

    public Long getServerExecutedTime() {
        return serverExecutedTime;
    }

    public void setServerExecutedTime(Long serverExecutedTime) {
        this.serverExecutedTime = serverExecutedTime;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
