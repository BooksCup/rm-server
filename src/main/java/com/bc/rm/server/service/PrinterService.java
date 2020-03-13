package com.bc.rm.server.service;

import com.bc.rm.server.entity.printer.Printer;
import com.bc.rm.server.entity.printer.PrinterConfig;

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
}

