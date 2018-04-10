package com.proxy.cgliib;

/**
 * Created by Stark on 2018/1/8.
 * 需要被代理的类，cglib不需要定义目标类的同一接口
 */
public class Service {
    //模拟的add方法
    public void add() {
        System.out.println("add-------");
    }


}
