package com.stark.design23.abstractfactory;

/**
 * Created by Stark on 2018/1/26.
 * 工厂类接口
 */
public interface ProductFactory {
    default ProductA getProductA() {
        return null;
    }

    default ProductB getProductB() {
        return null;
    }
}
