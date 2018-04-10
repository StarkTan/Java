package com.stark.logger;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Stark on 2017/10/18.
 */
public class Test1 {
    public void test() {
        Logger logger = LoggerFactory.getLogger();
        logger.log(Level.WARNING,"test1 warning!");
        logger.info("test1 info!");
    }
}
