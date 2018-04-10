package com.stark.netty.section_2.charpter_2_1;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Stark on 2018/1/13.
 * 使用线程池实现伪异步IO
 */
public class TimeServerHandlerExcutePool {
    private ExecutorService pool;

    public TimeServerHandlerExcutePool(int maxPoolSize, int queueSize) {
        pool = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(), maxPoolSize, 120L, TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(queueSize));
    }

    public void execute(Runnable task) {
        pool.execute(task);
    }
}
