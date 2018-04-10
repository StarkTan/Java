package com.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * Created by Stark on 2018/1/8.
 * 测试类
 */
public class Test {
    public static void main(String[] args) {
        Service serviceA = new AServiceImp();
        ServiceInvocationHandler handeler = new ServiceInvocationHandler(serviceA);
        Service serviceProxy = (Service) Proxy.newProxyInstance(serviceA.getClass().getClassLoader(),
                serviceA.getClass().getInterfaces(), handeler);
        serviceProxy.add();
        serviceProxy.update();
    }
}
