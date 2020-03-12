package com.bc.rm.server.entity.printer.result;

/**
 * 打印机相关接口返回
 *
 * @author zhou
 */
public class PrinterResult {
    private String[] ok;
    private String[] no;

    public String[] getOk() {
        return ok;
    }

    public void setOk(String[] ok) {
        this.ok = ok;
    }

    public String[] getNo() {
        return no;
    }

    public void setNo(String[] no) {
        this.no = no;
    }
}
