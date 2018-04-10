package com.stark.design23.abstractfactory;

/**
 * Created by Stark on 2018/1/26.
 * B产品工厂类的实现
 */
public class FactoryB implements ProductFactory {
    @Override
    public ProductB getProductB() {
        return () -> System.out.println("I am pruduct B");
    }
}