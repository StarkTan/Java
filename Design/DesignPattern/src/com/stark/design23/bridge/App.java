package com.stark.design23.bridge;

/**
 * Created by Stark on 2018/1/17.
 * 测试
 */
public class App {
    public static void main(String[] args) {
        Product productA = new Product(new DataBaseA());
        Product productB = new Product(new DataBaseB());
        productA.update("use a");
        System.out.println(productA.display());
        productB.update("use b");
        System.out.println(productB.display());

    }
}
