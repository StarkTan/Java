package com.stark.innerclass;

/**
 * 匿名内部类
 * 即时定义即时使用。省代码啊！
 */
public class AnyInnerTest {
    public static void main(String[] args) {
        //实现两个匿名类，分别实现接口和继承抽象类。
        new Thread(new Runnable() {
            @Override
            public void run() {
                new AbstracAny("any") {
                    @Override
                    void say() {
                        System.out.println("my name : " + name);
                    }
                }.say();
            }
        }).start();
    }
}

abstract class AbstracAny {
    public String name;
    public AbstracAny(String name) {
        this.name = name;
    }
    abstract void say();
}
