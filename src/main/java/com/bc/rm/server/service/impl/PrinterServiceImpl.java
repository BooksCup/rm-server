package com.bc.rm.server.service.impl;

import com.bc.rm.server.entity.printer.Printer;
import com.bc.rm.server.mapper.PrinterMapper;
import com.bc.rm.server.service.PrinterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 打印机
 * @author zhou
 */
@Service("printerService")
public class PrinterServiceImpl implements PrinterService {
    @Resource
    private PrinterMapper printerMapper;

    public void addPrinter(Printer printer){
        printerMapper.addPrinter(printer);
    }

}
