package com.stark.design23.visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Stark on 2018/3/28.
 * 测试
 */
public class App {
    public static void main(String[] args) {
        Visitor visitor = new Visitor();

        List<Worker> workers = new ArrayList<>();
        workers.add(new WorkerA());
        workers.add(new WorkerA());
        workers.add(new WorkerB());

        Iterator<Worker> iterator = workers.iterator();
        while (iterator.hasNext()) {
            Worker worker = iterator.next();
            worker.accept(visitor);
        }
    }
}
