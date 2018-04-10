package com.stark.logger;

import java.util.logging.Logger;

/**
 * Created by Stark on 2017/10/18.
 *
 */
public class Test2 {
    public void test() {
        Logger logger = LoggerFactory.getLogger();
        logger.warning("test2 warning!");
        logger.info("test2 info!");
    }
}
