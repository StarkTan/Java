package com.stark.design23.visitor;

/**
 * Created by Stark on 2018/3/28.
 * 访问者
 */
public class Visitor {
    //通过重载对让不同工人执行命令
    public void run(WorkerA workerA) {
        workerA.runA();
    }

    public void run(WorkerB workerB) {
        workerB.runB();
    }
}
