package com.stark.design23.mediator;

/**
 * Created by Stark on 2018/1/21.
 * 同事抽象类
 */
public abstract class Colleague {
    private int number;
    private Mediator mediator;

    public Colleague(int data) {
        number = data;
    }

    public int getNumber() {
        return number;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public abstract int getData();
}
