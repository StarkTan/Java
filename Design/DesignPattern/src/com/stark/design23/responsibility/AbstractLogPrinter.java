package com.stark.design23.responsibility;

/**
 * Created by Stark on 2018/1/17.
 * 日志输出抽象类
 */
public abstract class AbstractLogPrinter implements LogPrinter {
    protected static int INFO = 1;
    protected static int WARN = 2;
    protected static int ERROR = 3;
    private AbstractLogPrinter nextLogPrinter;
    protected int level;

    public void setNextLogPrinter(AbstractLogPrinter nextLogPrinter) {
        this.nextLogPrinter = nextLogPrinter;
    }

    public void printlog(int level, String msg) {
        if (this.level <= level) {
            log(msg);
        }
        if (nextLogPrinter != null) {
            nextLogPrinter.printlog(level, msg);
        }
    }
}
