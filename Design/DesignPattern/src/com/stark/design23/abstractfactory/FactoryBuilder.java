package com.stark.design23.abstractfactory;

/**
 * Created by Stark on 2018/1/26.
 * 工厂类生产者
 */
public class FactoryBuilder {
    public static ProductFactory getFactory(String type) {
        switch (type) {
            case "A":
                return new FactoryA();
            case "B":
                return new FactoryB();
            default:
                return null;
        }
    }
}
