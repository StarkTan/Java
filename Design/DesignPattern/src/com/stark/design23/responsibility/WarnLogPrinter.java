package com.stark.design23.responsibility;

/**
 * Created by Stark on 2018/1/17.
 * 警告输出
 */
public class WarnLogPrinter extends AbstractLogPrinter {
    public WarnLogPrinter() {
        this.level = AbstractLogPrinter.WARN;
    }

    public void log(String msg) {
        System.out.println("info: " + msg);
    }
}
