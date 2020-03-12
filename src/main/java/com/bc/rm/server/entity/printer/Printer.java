package com.bc.rm.server.entity.printer;

import com.bc.rm.server.util.CommonUtil;

/**
 * 打印机
 *
 * @author zhou
 */
public class Printer {
    private String id;

    /**
     * 打印机编号SN
     */
    private String sn;

    /**
     * 打印机识别码KEY(存于底部标签)
     */
    private String key;

    /**
     * 打印机名称，如地址、店铺名等，便于管理
     */
    private String name;

    /**
     * 流量卡号码，适用于GPRS打印机，便于充值，sim卡背面20位数字
     */
    private String dataCardNo;

    private String createTime;

    private String apiResultCode;
    private String apiResultMessage;

    public Printer() {

    }

    public Printer(String sn, String key, String name, String dataCardNo) {
        this.id = CommonUtil.generateId();
        this.sn = sn;
        this.key = key;
        this.name = name;
        this.dataCardNo = dataCardNo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataCardNo() {
        return dataCardNo;
    }

    public void setDataCardNo(String dataCardNo) {
        this.dataCardNo = dataCardNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getApiResultCode() {
        return apiResultCode;
    }

    public void setApiResultCode(String apiResultCode) {
        this.apiResultCode = apiResultCode;
    }

    public String getApiResultMessage() {
        return apiResultMessage;
    }

    public void setApiResultMessage(String apiResultMessage) {
        this.apiResultMessage = apiResultMessage;
    }
}
