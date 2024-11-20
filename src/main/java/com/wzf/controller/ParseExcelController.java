package com.wzf.controller;

import com.wzf.service.ExcelImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author WuZhongfei
 * @date 2024年11月19日 15:55
 */
@RestController
public class ParseExcelController {

    @Autowired
    private ExcelImportService excelImportService;
    @RequestMapping("/getParseExcelData/{fileName}")
    public String getParseExcelData(@PathVariable String fileName) throws Exception {
        return excelImportService.parseExcel(fileName);
    }
    @RequestMapping("/getParseExcelDataFromFile")
    public String getParseExcelDataOther() throws Exception {
        String fileName = "E:\\program_WorkSpace\\JBF-test\\src\\file\\abc.xls";
        return excelImportService.parseExcel(fileName);
    }
}
