package com.bc.rm.server.entity.printer;

import com.bc.rm.server.util.CommonUtil;

/**
 * 打印订单
 *
 * @author zhou
 */
public class PrinterOrder {
    private String id;
    private String no;
    private String printerSn;
    private String content;
    private String retCode;
    private String retMessage;
    private String times;
    private String createTime;

    public PrinterOrder() {

    }

    public PrinterOrder(String printerSn, String content, String times) {
        this.id = CommonUtil.generateId();
        this.printerSn = printerSn;
        this.content = content;
        this.times = times;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getPrinterSn() {
        return printerSn;
    }

    public void setPrinterSn(String printerSn) {
        this.printerSn = printerSn;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getRetCode() {
        return retCode;
    }

    public void setRetCode(String retCode) {
        this.retCode = retCode;
    }

    public String getRetMessage() {
        return retMessage;
    }

    public void setRetMessage(String retMessage) {
        this.retMessage = retMessage;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
