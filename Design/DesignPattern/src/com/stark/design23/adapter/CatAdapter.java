package com.stark.design23.adapter;

/**
 * Created by Stark on 2018/1/16.
 * Cat的设配器类  对象适配器模式！
 */
public class CatAdapter implements Animal {
    Cat cat = new Cat();

    public void call() {
        cat.catCall();
    }
}
