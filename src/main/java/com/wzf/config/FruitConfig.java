package com.wzf.config;

import com.wzf.model.Fruit;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class FruitConfig {
    public List<Fruit> getFruitList(Environment environment){
        List<Fruit> result = new ArrayList<>();
        Fruit fruit = new Fruit();
        fruit.setId(Long.valueOf(environment.getProperty("fruit.apple.id")));
        fruit.setName(environment.getProperty("fruit.apple.name"));
        fruit.setPrice(Double.valueOf(environment.getProperty("fruit.apple.id")));
        result.add(fruit);

        fruit.setId(Long.valueOf(environment.getProperty("fruit.strawberry.id")));
        fruit.setName(environment.getProperty("fruit.strawberry.name"));
        fruit.setPrice(Double.valueOf(environment.getProperty("fruit.strawberry.id")));
        result.add(fruit);

        fruit.setId(Long.valueOf(environment.getProperty("fruit.mango.id")));
        fruit.setName(environment.getProperty("fruit.mango.name"));
        fruit.setPrice(Double.valueOf(environment.getProperty("fruit.mango.id")));
        result.add(fruit);

        return result;
    }

}
