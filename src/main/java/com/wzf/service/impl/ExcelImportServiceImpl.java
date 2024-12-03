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

    private String tableName = "business_config";
    @Override
    public String parseExcel(String fileName) throws Exception {
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

    //根据路径生成 FileInputStream字节流
        FileInputStream inputStream = new FileInputStream(new File(fileName));
        final List<List<Object>> listByExcel = ExcelUtils.getListByExcel(inputStream, fileName);
        final List<Object> excelData = getExcelData(listByExcel);
        System.out.println(excelData);
//        Map<String, Object> paramsMap = new HashMap<>();
        BusinessConfig businessConfig = null;
        List<BusinessConfig> businessConfigList = new ArrayList<>();
        Integer valueInt = null;
        Integer relationIdValueInt = null;
        Integer defaultValueInt = null;
        String relationName = null;
        String valueString = null;
        for (Object object: excelData) {
//            System.out.println(object);
            //取一行的数据
            List<Map<String, Object>> rowMap = (List<Map<String, Object>>) object;
            for (Map<String, Object> lineMap: rowMap) {
                //取一行的数据
                for (Map.Entry<String, Object> entry : lineMap.entrySet()) {
                    switch (entry.getKey()){
                        case "bsiness_seq":
                            Double bsinessSeqValue = Double.valueOf(entry.getValue().toString());
                            valueInt = bsinessSeqValue.intValue();
                            continue;
//                            break;
                        case "relation_id":
                            Double relationIdValue = Double.valueOf(entry.getValue().toString());
                            relationIdValueInt = relationIdValue.intValue();
                            continue;
//                            break;
                        case "default_value":
                            Double defaultValueDouble = Double.valueOf(entry.getValue().toString());
                            defaultValueInt = defaultValueDouble.intValue();
                            continue;
//                            break;
                        case "relation_name":
                            relationName = String.valueOf(entry.getValue());
                            continue;
//                            break;
                        case "value_string":
                            valueString = entry.getValue().toString();
                            continue;
//                            break;
                        default:
                    }
                }
            }
//            paramsMap = (Map<String, Object>) object.getClass().newInstance();
//            List<Object> objectList = (List<Object>) object;
//            for (Object lineObject: objectList) {
//                List<Object> line = (List<Object>) lineObject;
//                for (Object objectData: line){
//                    Class<?> execelClass = objectData.getClass();
//                    //遍历列数据
//                    for (Field field: execelClass.getDeclaredFields()) {
//                        field.setAccessible(true);
//                        final String fieldName = field.getName();
//                        final Object fieldValue = field.get(object);
//                        paramsMap.put(fieldName, fieldValue);
//                    }
//                }
//
//            }
            businessConfig = new BusinessConfig();
            businessConfig.setBusinessSeq(valueInt);
            businessConfig.setRelationId(relationIdValueInt);
            businessConfig.setDefaultValue(defaultValueInt);
            businessConfig.setRelationName(relationName);
            businessConfig.setValueString(valueString);
            //把一行数据放入list中
            businessConfigList.add(businessConfig);
            //数据初始化
            valueInt = null;
            relationIdValueInt = null;
            defaultValueInt = null;
            relationName = null;
            valueString = null;


            //清空map
            // paramsMap.clear();

        }
        //生成sql
        String generateUpdateSql = generateUpdateSql(tableName, businessConfigList);
        return generateUpdateSql;
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

    public static class BusinessConfig{
        //bsiness_seq
        private Integer businessSeq;
        //relation_id
        private Integer relationId;
        //default_value
        private Integer defaultValue;
        //relation_name
        private String relationName;
        //value_string
        private String valueString;

        public Integer getBusinessSeq() {
            return businessSeq;
        }

        public void setBusinessSeq(Integer businessSeq) {
            this.businessSeq = businessSeq;
        }

        public Integer getRelationId() {
            return relationId;
        }

        public void setRelationId(Integer relationId) {
            this.relationId = relationId;
        }

        public Integer getDefaultValue() {
            return defaultValue;
        }

        public void setDefaultValue(Integer defaultValue) {
            this.defaultValue = defaultValue;
        }

        public String getRelationName() {
            return relationName;
        }

        public void setRelationName(String relationName) {
            this.relationName = relationName;
        }

        public String getValueString() {
            return valueString;
        }

        public void setValueString(String valueString) {
            this.valueString = valueString;
        }
    }

    private String generateUpdateSql(String tableName, List<BusinessConfig> businessConfigList){
        String sql = "";
        for (BusinessConfig businessConfig: businessConfigList){
            sql += "\n";
            sql += "update " + tableName + " set ";
            sql += "`default_value` = " + businessConfig.getDefaultValue() + ", ";
            sql += "`relation_name` = '" + businessConfig.getRelationName() + "' ";
//            sql += "`update_time` = now() ";
            sql += "where ";
            sql += "`bsiness_seq` = " + businessConfig.getBusinessSeq() ;
//            sql += "`bsiness_seq` = " + businessConfig.getBusinessSeq() + " AND " ;
//            sql += "`relation_id` = " + businessConfig.getRelationId() + " AND ";
//            sql += "`value_string` = '"+ businessConfig.getValueString() + "' ";
            sql += ";\n";
        }
//        System.out.println(sql);
        return sql;
    }
}
