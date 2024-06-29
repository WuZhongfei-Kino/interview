package com.wzf.service;

import org.springframework.core.env.Environment;

public interface ConsumerService {
    public String getFruitTotal(Environment environment, String consumerName, int appleNum, int strawNum, int mangoNum);
}
