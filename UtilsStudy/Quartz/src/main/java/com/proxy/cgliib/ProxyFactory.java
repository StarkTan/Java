package com.proxy.cgliib;

import net.sf.cglib.proxy.Enhancer;

/**
 * Created by Stark on 2018/1/8.
 * 代理对象的工厂类，在这里完成切面的封装
 */
public class ProxyFactory {
    public static Service getInstance(ServiceProxy proxy) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Service.class);
        enhancer.setCallback(proxy);
        return (Service) enhancer.create();
    }
}
