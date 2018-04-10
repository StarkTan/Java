package com.proxy.cgliib;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Stark on 2018/1/8.
 * Service 的代理类，实现MethodInterceptor接口
 * 在intercept中加入切面逻辑
 * 目标执行预计语句是
 */
public class ServiceProxy implements MethodInterceptor {
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        //执行前逻辑
        System.out.println("before--------->");
        //程序执行
        Object result = proxy.invokeSuper(obj, args);
        //执行后逻辑
        System.out.println("after---------->");
        return result;
    }
}
