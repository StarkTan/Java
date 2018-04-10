package com.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by Stark on 2018/1/8.
 * 实现InvocationHandler 目标类方法的执行就是有method.invoke()完成的
 * 在 invoke方法中加入切面逻辑
 */
public class ServiceInvocationHandler implements InvocationHandler {
    private Object target;

    ServiceInvocationHandler() {
        super();
    }

    ServiceInvocationHandler(Object target) {
        super();
        this.target = target;
    }


    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //程序执行前逻辑
        System.out.println("before------------");
        //程序执行
        Object result = method.invoke(target, args);
        //程序执行后逻辑
        System.out.println("after-------------");
        return result;
    }
}
