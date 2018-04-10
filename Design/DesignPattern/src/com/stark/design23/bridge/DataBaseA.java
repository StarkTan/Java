package com.stark.design23.bridge;

/**
 * Created by Stark on 2018/1/17.
 * A对接口的实现
 */
public class DataBaseA implements DataBase {

    private String msg;

    public void write(String msg) {
        this.msg = msg;
    }

    public String read() {
        return this.msg;
    }
}
