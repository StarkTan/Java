package com.proxy.jdk;

/**
 * Created by Stark on 2018/1/8.
 * 实现Service接口的A类
 */
public class AServiceImp implements Service {
    public void add() {
        System.out.println("=======>>add from A");
    }
    public void update() {
        System.out.println("=======>>update from A");
    }
}
