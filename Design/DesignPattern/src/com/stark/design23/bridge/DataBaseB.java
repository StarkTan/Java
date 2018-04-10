package com.stark.design23.bridge;

/**
 * Created by Stark on 2018/1/17.
 * B对接口的实现
 */
public class DataBaseB implements DataBase {
    private static String msg;

    public void write(String msg) {
        DataBaseB.msg = msg;
    }

    public String read() {
        return msg;
    }
}
