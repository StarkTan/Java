package com.stark.design23.responsibility;

/**
 * Created by Stark on 2018/1/17.
 * 日志记录器
 */
public class Logger {
    private AbstractLogPrinter logprint = null;

    public Logger() {
        AbstractLogPrinter info = new InfoLogPrinter();
        AbstractLogPrinter warn = new WarnLogPrinter();
        AbstractLogPrinter error = new ErrorLogPrinter();
        info.setNextLogPrinter(warn);
        warn.setNextLogPrinter(error);
        logprint = info;
    }

    public void info(String msg) {
        logprint.printlog(AbstractLogPrinter.INFO, msg);
    }

    public void warn(String msg) {
        logprint.printlog(AbstractLogPrinter.WARN, msg);
    }

    public void error(String msg) {
        logprint.printlog(AbstractLogPrinter.ERROR, msg);
        warn(msg);
    }
}
