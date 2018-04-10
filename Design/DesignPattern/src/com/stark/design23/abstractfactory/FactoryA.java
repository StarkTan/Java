package com.stark.design23.abstractfactory;

/**
 * Created by Stark on 2018/1/26.
 * A产品类工厂的实现
 */
public class FactoryA implements ProductFactory {
    @Override
    public ProductA getProductA() {
        return () -> System.out.println("I am pruduct A");
    }
}
