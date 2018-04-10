package com.stark.design23.responsibility;

/**
 * Created by Stark on 2018/1/17.
 * 错误消息输出
 */
public class ErrorLogPrinter extends AbstractLogPrinter {
    public ErrorLogPrinter() {
        this.level = AbstractLogPrinter.ERROR;
    }

    public void log(String msg) {
        System.out.println("info: " + msg);
    }
}
