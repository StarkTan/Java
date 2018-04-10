package com.stark.design23.decorator;

/**
 * Created by Stark on 2018/3/27.
 * 只实现接口功能的类
 */
public class PhoneImp implements Phone {
    @Override
    public void call(String phoneNumber) {
        System.out.println("call the " + phoneNumber);
    }
}
