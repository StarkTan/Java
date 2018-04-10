package com.stark.design23.observer;

/**
 * Created by Stark on 2018/1/21.
 * 测试
 */
public class App {

    public static void main(String[] args) {
        ObserverImp sub = new ObserverImp();
        Listener lisA = (String data) -> {
            System.out.println("A  :" + data);
        };
        Listener lisB = (String data) -> {
            System.out.println("B  :" + data);
        };
        Listener lisC = (String data) -> {
            System.out.println("C  :" + data);
        };
        sub.addListener(lisA);
        sub.addListener(lisB);
        sub.addListener(lisC);

        sub.update("test1");
        sub.removeListener(lisB);
        sub.update("test2");
    }
}
