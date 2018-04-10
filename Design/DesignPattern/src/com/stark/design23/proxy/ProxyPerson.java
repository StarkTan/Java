package com.stark.design23.proxy;

/**
 * Created by Stark on 2018/1/26.
 * 代理人
 */
public class ProxyPerson implements Behavior {
    private Behavior persion = new RealPerson();

    @Override
    public void cry() {
        persion.cry();
    }

    @Override
    public void laugh() {
        persion.laugh();
    }
}
