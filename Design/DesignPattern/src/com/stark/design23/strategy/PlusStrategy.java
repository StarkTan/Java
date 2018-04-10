package com.stark.design23.strategy;

/**实现加法策略*/
public class PlusStrategy implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 + num2;
    }
}
