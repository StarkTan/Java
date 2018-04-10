package com.stark.design23.factory;

/**
 * Created by Stark on 2018/1/26.
 * 产品工厂
 */
public class ProductFactory {
    public static Product getProduct(String type) {
        switch (type) {
            case "A":
                return new ProductA();
            case "B":
                return new ProductB();
            default:
                return null;
        }
    }
}
