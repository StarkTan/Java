package com.stark.design23.adapter;

/**
 * Created by Stark on 2018/1/16.
 * Dog类的设配器类  类适配器模式！
 */
public class DogAdapter extends Dog implements Animal {

    public void call() {
        this.dogCall();
    }
}
