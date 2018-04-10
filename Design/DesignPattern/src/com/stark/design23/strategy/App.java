package com.stark.design23.strategy;

/**
 * Created by Stark on 2018/3/27.\
 * 测试
 */
public class App {
    public static void main(String[] args) {
        MathUtil mathUtil = new MathUtil(new PlusStrategy());
        System.out.println("10 + 5 = " + mathUtil.doOperation(10, 5));

        mathUtil = new MathUtil(new MinusStrategy());
        System.out.println("10 - 5 = " + mathUtil.doOperation(10, 5));
    }
}
