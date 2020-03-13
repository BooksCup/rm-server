package com.bc.rm.server.mapper;

import com.bc.rm.server.entity.printer.Printer;

/**
 * 打印机
 *
 * @author zhou
 */
public interface PrinterMapper {
    /**
     * 添加打印机
     *
     * @param printer 打印机
     */
    void addPrinter(Printer printer);
}
