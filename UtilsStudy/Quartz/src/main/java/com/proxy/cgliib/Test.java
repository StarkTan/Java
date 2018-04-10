package com.proxy.cgliib;

/**
 * Created by Stark on 2018/1/8.
 * 测试类
 */
public class Test {
    public static void main(String[] args) {
        ServiceProxy proxy = new ServiceProxy();
        Service service = ProxyFactory.getInstance(proxy);
        service.add();
        //新生成的类是Service的子类
        System.out.println(service.getClass().getSimpleName());
        System.out.println(( service.getClass().getSuperclass().getSimpleName()));
    }
}
