package com.stark.design23.strategy;

/**
 * Created by Stark on 2018/3/27.
 * 运算工具
 */
public class MathUtil {
    private Strategy strategy;

    public MathUtil(Strategy strategy) {
        this.strategy = strategy;
    }

    public int doOperation(int num1, int num2) {
        return strategy.doOperation(num1, num2);
    }
}
