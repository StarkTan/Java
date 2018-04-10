package com.stark.design23.mediator;

/**
 * Created by Stark on 2018/1/21.
 * 中介实现类
 */
public class MediatorImp implements Mediator {
    private Colleague A, B, C;

    public MediatorImp(Colleague A, Colleague B, Colleague C) {
        this.A = A;
        this.B = B;
        this.C = C;
    }

    @Override
    public int getBC() {
        return B.getNumber() + C.getNumber();
    }

    @Override
    public int getCA() {
        return A.getNumber() + C.getNumber();
    }

    @Override
    public int getAB() {
        return B.getNumber() + A.getNumber();
    }
}
