package com.stark;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by Stark on 2017/10/30.
 * 方法区存放的是Class的相关信息
 * java 1.8 已经移除了这两个属性
 * -XX:PermSize=10M  -XX:MaxPermSize=10M
 * 使用GCLib不停地创建类 来达到溢出的效果
 */
public class JavaMethodAreaOOM {

    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invoke(objects, args);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject {

    }

}
