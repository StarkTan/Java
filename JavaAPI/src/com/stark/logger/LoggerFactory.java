package com.stark.logger;


import java.io.File;
import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Stark on 2017/10/17.
 */
public class LoggerFactory {
    private static Logger logger = null;

    public static Logger getLogger() {
        if (logger != null) {
            return logger;
        }
        synchronized (LoggerFactory.class) {
            if (logger != null) {
                return logger;
            }
            logger = Logger.getLogger(LoggerFactory.class.getSimpleName());
            Formatter customLogHandler = new CustomLogHandler();
            try {
                FileHandler handlerAll = new FileHandler("D:\\all.log", true);
                handlerAll.setFormatter(customLogHandler);
                handlerAll.setLevel(Level.ALL);
                FileHandler handlerWARNING = new FileHandler("D:\\warn.log", true);
                handlerWARNING.setFormatter(customLogHandler);
                handlerWARNING.setLevel(Level.WARNING);
                logger.addHandler(handlerWARNING);
                logger.addHandler(handlerAll);
                return logger;
            } catch (IOException e) {
                e.printStackTrace();
                logger = null;
                return logger;
            }
        }
    }
}
