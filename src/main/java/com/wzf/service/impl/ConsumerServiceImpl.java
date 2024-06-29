package com.wzf.service.impl;

import com.wzf.service.ConsumerService;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ConsumerServiceImpl implements ConsumerService {
    @Override
    public String getFruitTotal(Environment environment, String consumerName, int appleNum, int strawNum, int mangoNum) {
        String result = "";
        //先判断顾客名称是否存在
        if (StringUtils.isEmpty(consumerName)) return result = "请先选择顾客";
        //再判断这些水果的斤数是否为正数
        if (appleNum < 0 && strawNum < 0 && mangoNum < 0 ) return result = "所选的水果斤数须为正数";
        if (appleNum < 0 && appleNum % 1 != 0) return result = "所选的苹果斤数须为正整数";
        if (strawNum < 0 && strawNum % 1 != 0) return result = "所选的草莓斤数须为正整数";
        if (mangoNum < 0 && mangoNum % 1 != 0) return result = "所选的芒果斤数须为正整数";
        consumerGetTotal(environment, consumerName, appleNum, strawNum, mangoNum );

        return result;
    }

    public static String consumerGetTotal(Environment environment, String consumerName, int appleNum, int strawNum, int mangoNum) {
        Double total = 0d;
        String result = "顾客："+consumerName+",";
        //获取水果信息
        String appleName = environment.getProperty("fruit.apple.name");
        Double applePrice = Double.valueOf(environment.getProperty("fruit.apple.price"));
        result += "水果名称:"+appleName +",水果价格:"+ applePrice +"\n";
        String strawName = environment.getProperty("fruit.strawberry.name");
        Double strawPrice = Double.valueOf(environment.getProperty("fruit.strawberry.price"));
        result += "水果名称:"+strawName +",水果价格:"+ strawPrice +"\n";
        String mangoName = environment.getProperty("fruit.mango.name");
        Double mangoPrice = Double.valueOf(environment.getProperty("fruit.mango.price"));

        total += appleNum == 0 ? 0 : appleNum * applePrice;
        //根据顾客名称选择对应的结算方式
        switch (consumerName){
            case "A":
                total += strawNum == 0 ? 0 : strawNum * strawPrice;
                result += "总价:"+ total;
                break;
            case "B":
                total += strawNum == 0 ? 0 : strawNum * strawPrice ;
                result += "水果名称:"+mangoName +",水果价格:"+ mangoName +"\n";
                total += mangoNum == 0 ? 0 : mangoNum * mangoPrice;
                result += "总价:"+ total;
                break;
            case "C":
                total += strawNum == 0 ? 0 : strawNum * (strawPrice * 0.8);
                result += "水果名称:"+mangoName +",水果价格:"+ mangoName +"\n";
                total += mangoNum == 0 ? 0 : mangoNum * mangoPrice;
                result += "总价:"+ total;
                break;
            case "D":
                total += strawNum == 0 ? 0 : strawNum * strawPrice;
                result += "水果名称:"+mangoName +",水果价格:"+ mangoName +"\n";
                total += mangoNum == 0 ? 0 : mangoNum * mangoPrice;
                total = total > 100 ? total - 10 : total;
                result += "总价:"+ total;
                break;
        }
        return result;
    }
}
