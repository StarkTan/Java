package com.stark.design23.visitor;

/**
 * Created by Stark on 2018/3/28.
 * 工人 A
 */
public class WorkerA implements Worker {

    public void runA() {
        System.out.println("A run");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.run(this);
    }
}
