package com.stark.design23.singleton;

/**
 * Created by Stark on 2018/1/21.
 * 单体类
 */
public final class Singleton {
    private String name;

    private Singleton() {
        this.name = "singleton";
    }

    public synchronized void updateName(String name) {

    }

    public synchronized String getName() {
        return name;
    }

    //初始化时创建（饥饿模式）
//    private static Singleton singleton = new Singleton();
//
//    private static Singleton getInstance() {
//        return singleton;
//    }
    //运行时创建（懒汉模式）
    private volatile static Singleton singleton = null;

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                    //可能错误，线程被只能重排在构造完成前就赋值了，导致后面线程报错
                }
            }
        }
        return singleton;
    }
}
