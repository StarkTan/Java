package com.stark.design23.responsibility;

/**
 * Created by Stark on 2018/1/17.
 * 普通消息输出
 */
public class InfoLogPrinter extends AbstractLogPrinter {
    public InfoLogPrinter() {
        this.level = AbstractLogPrinter.INFO;
    }

    public void log(String msg) {
        System.out.println("info: " + msg);
    }
}
