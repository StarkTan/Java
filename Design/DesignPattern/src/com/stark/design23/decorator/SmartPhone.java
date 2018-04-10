package com.stark.design23.decorator;

/**
 * Created by Stark on 2018/3/27.
 * 智能手机，在实现了手机打电话功能时，同时提供上网功能
 */
public class SmartPhone implements Phone {
    private Phone phone;

    public SmartPhone(Phone phone) {
        this.phone = phone;
    }

    @Override
    public void call(String phoneNumber) {
        this.phone.call(phoneNumber);
    }

    public void networking(){
        System.out.println("connect to net");
    }
}
