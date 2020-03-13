package com.bc.rm.server.service.impl;

import com.bc.rm.server.entity.printer.Printer;
import com.bc.rm.server.entity.printer.PrinterConfig;
import com.bc.rm.server.entity.printer.PrinterOrder;
import com.bc.rm.server.mapper.PrinterConfigMapper;
import com.bc.rm.server.mapper.PrinterMapper;
import com.bc.rm.server.mapper.PrinterOrderMapper;
import com.bc.rm.server.service.PrinterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 打印机
 *
 * @author zhou
 */
@Service("printerService")
public class PrinterServiceImpl implements PrinterService {
    @Resource
    private PrinterConfigMapper printerConfigMapper;

    @Resource
    private PrinterMapper printerMapper;

    @Resource
    private PrinterOrderMapper printerOrderMapper;

    /**
     * 获取打印机配置
     *
     * @return 打印机配置
     */
    @Override
    public PrinterConfig getPrinterConfig() {
        return printerConfigMapper.getPrinterConfig();
    }

    /**
     * 添加打印机
     *
     * @param printer 打印机
     */
    @Override
    public void addPrinter(Printer printer) {
        printerMapper.addPrinter(printer);
    }

    /**
     * 新增打印订单
     *
     * @param printerOrder 打印订单
     */
    @Override
    public void addPrinterOrder(PrinterOrder printerOrder) {
        printerOrderMapper.addPrinterOrder(printerOrder);
    }

}
