package com.bc.rm.server.service;

import com.bc.rm.server.entity.printer.Printer;
import com.bc.rm.server.entity.printer.PrinterConfig;
import com.bc.rm.server.entity.printer.PrinterOrder;

/**
 * 打印机
 *
 * @author zhou
 */
public interface PrinterService {
    /**
     * 获取打印机配置
     *
     * @return 打印机配置
     */
    PrinterConfig getPrinterConfig();

    /**
     * 添加打印机
     *
     * @param printer 打印机
     */
    void addPrinter(Printer printer);

    /**
     * 新增打印订单
     *
     * @param printerOrder 打印订单
     */
    void addPrinterOrder(PrinterOrder printerOrder);
}

