package com.wzf.controller;

import com.wzf.config.FruitConfig;
import com.wzf.model.Fruit;
import com.wzf.service.ConsumerTotalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 1、有一家超市，出售苹果和草莓。其中苹果8元/斤，草莓13元/斤。
 * 现在顾客A 在超市购买了若干斤苹果和草莓，需要计算一共多少钱？
 * 请编写函数，对于 A 购买的水果斤数（水果斤数为大于等于 0的整数），计算并返回所购买商品的总价。
 * 2、超市增加了一种水果芒果，其定价为 20元/斤。
 * 现在顾客 B 在超市购买了若干斤苹果、草莓和芒果，需计算一共需要多少钱？
 * 请编写函数，对于 B 购买的水果斤数（水果斤数为大于等于 0的整数），计算并返回所购买商品的总价。
 * 3、超市做促销活动，草莓限时打 8 折。
 * 现在顾客C在超市购买了若干斤苹果、草莓和芒果，需计算一共需要多少钱？
 * 请编写函数，对于C购买的水果斤数（水果斤数为大于等于 0的整数），计算并返回所购买商品的总价。
 * 4、促销活动效果明显，超市继续加大促销力度，购物满 100减10块。
 * 现在顾客 D 在超市购买了若干斤苹果、草莓和芒果，需计算一共需要多少钱？
 * 请编写函数，对于 D 购买的水果斤数（水果斤数为大于等于 0的整数），计算并返回所购买商品的总价。
 */
@RestController
public class ConsumerTotalController {
    @Autowired
    private Environment environment;

    @Autowired
    private FruitConfig fruitConfig;

    @Autowired
    private ConsumerTotalService consumerTotalService;

//    @RequestMapping("/findFruits")
//    public List<Fruit> getParam(){
//        return fruitConfig.getFruitList(environment);
//    }


    /**
     * 获取水果总价
     * @param consumerName 顾客名
     * @param appleNum 水果斤数
     * @param strawNum 草莓斤数
     * @param mangoNum 芒果斤数
     * @return
     */
//    @RequestMapping("/getFruitTotal/{consumerName}/{appleNum}/{strawNum}/{mangoNum}")
//    public String getFruitTotal(@PathVariable String consumerName, @PathVariable int appleNum, @PathVariable int strawNum, @PathVariable int mangoNum){
//        return consumerTotalService.getFruitTotal(environment, consumerName, appleNum, strawNum, mangoNum);
//    }
}
