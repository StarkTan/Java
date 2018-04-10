package com.stark.design23.facade;

/**
 * Created by Stark on 2018/1/16.
 * 客户端 A
 */
public class ClientA {
    public int handle() {
        /*MinusStrategy minus = new MinusStrategy();
        Plus plus = new Plus();
        return plus.plus(minus.minus(plus.plus(1, 2), 3), 4);*/
        Facade facade = new Facade();
        return facade.handle(1, 4);
    }
}
