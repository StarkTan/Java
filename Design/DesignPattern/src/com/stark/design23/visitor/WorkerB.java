package com.stark.design23.visitor;

/**
 * Created by Stark on 2018/3/28.
 * 工人 B
 */
public class WorkerB implements Worker {

    public void runB() {
        System.out.println("B run");
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.run(this);
    }
}
