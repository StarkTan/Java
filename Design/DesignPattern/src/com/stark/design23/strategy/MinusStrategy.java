package com.stark.design23.strategy;

/**
 * Created by Stark on 2018/3/27.
 * 实现减法策略
 */
public class MinusStrategy implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 - num2;
    }
}
