package com.bc.rm.server.mapper;

import com.bc.rm.server.entity.printer.Printer;
import com.bc.rm.server.entity.printer.PrinterOrder;

/**
 * 打印订单
 *
 * @author zhou
 */
public interface PrinterOrderMapper {
    /**
     * 新增打印订单
     *
     * @param printerOrder 打印订单
     */
    void addPrinterOrder(PrinterOrder printerOrder);
}
