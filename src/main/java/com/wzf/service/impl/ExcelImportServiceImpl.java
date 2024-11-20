package com.wzf.service.impl;

import com.wzf.service.ExcelImportService;
import com.wzf.utils.ExcelUtils;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WuZhongfei
 * @date 2024年11月19日 15:58
 */
@Service
public class ExcelImportServiceImpl implements ExcelImportService {
    @Override
    public String parseExcel(String fileName) throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    //根据路径生成 FileInputStream字节流
        FileInputStream inputStream = new FileInputStream(new File(fileName));
        final List<List<Object>> listByExcel = ExcelUtils.getListByExcel(inputStream, fileName);
        final List<Object> excelData = getExcelData(listByExcel);
        System.out.println(excelData);
        for (Object object: excelData) {
            System.out.println(object);
        }
        return null;
    }

    public List<Object> getExcelData(List list){
        List<Object> result = new ArrayList<>();
        //定义Excel第一行的属性
        List<Object> firstRows = null;
        //获取第一行属性 放入firstRows中。
        if(list != null && list.size() > 0){
            firstRows = (List<Object>) list.get(0);
        }
        //遍历除第一行以外的Excel表格中的值
        for (int i = 1; i < list.size(); i++) {
            //rows是某一行，i = 1 为第二行，	i = 2 为第三行
            List<Object> rows = (List<Object>) list.get(i);
            //数据库数据的实体类


            List<Map<String, Object>> rowDataList = new ArrayList<>();//存放一行的数据
            //遍历这一行所有的值
            for (int j = 0; j < rows.size(); j++){
                //某一行的某一列 j为列的坐标
                String cellVal = (String) rows.get(j);

                Map<String, Object> lineData = new HashMap<>();//存放表头和数据的对应的值
                lineData.put(String.valueOf(firstRows.get(j)), cellVal);
                rowDataList.add(lineData);

            }
            Object t = rowDataList;
            result.add(t);
        }
        return result;
    }
}
