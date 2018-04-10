package com.stark.design23.mediator;

/**
 * Created by Stark on 2018/1/21.
 * 测试
 */
public class App {
    public static void main(String[] args) {
        Colleague a = new Colleague(1) {
            @Override
            public int getData() {
                return getMediator().getBC();
            }
        };
        Colleague b = new Colleague(2) {
            @Override
            public int getData() {
                return getMediator().getCA();
            }
        };
        Colleague c = new Colleague(3) {
            @Override
            public int getData() {
                return getMediator().getAB();
            }
        };
        Mediator mediator = new MediatorImp(a, b, c);
        a.setMediator(mediator);
        b.setMediator(mediator);
        c.setMediator(mediator);

        System.out.println(a.getData());
        System.out.println(b.getData());
        System.out.println(c.getData());

    }
}
