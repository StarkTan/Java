package com.proxy.jdk;

/**
 * Created by Stark on 2018/1/8.
 * 该类是被代理类的接口类，JDK的代理要求被代理类基于同一的接口
 */
public interface Service {
    //add 方法
    void add();

    //update 方法
    void update();
}
