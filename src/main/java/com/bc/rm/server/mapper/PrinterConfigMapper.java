package com.bc.rm.server.mapper;

import com.bc.rm.server.entity.printer.PrinterConfig;

/**
 * 打印机配置
 *
 * @author zhou
 */
public interface PrinterConfigMapper {
    /**
     * 获取打印机配置
     *
     * @return 打印机配置
     */
    PrinterConfig getPrinterConfig();
}
