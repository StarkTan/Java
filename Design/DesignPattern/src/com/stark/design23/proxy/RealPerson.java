package com.stark.design23.proxy;

/**
 * Created by Stark on 2018/1/26.
 * 实现行动接口的人
 */
public class RealPerson implements Behavior {

    @Override
    public void cry() {
        System.out.println("555555");
    }

    @Override
    public void laugh() {
        System.out.println("hahahahahahahahaha");
    }
}
