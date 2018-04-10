package com.stark.reflect.seven;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by HP on 2017/4/1.
 * 通过反射实现动态代理 使底层代码更有效的隐藏
 */
public class TestSeven
{
    public static void main(String[] args) throws Exception {
        MyInvocationHandler demo = new MyInvocationHandler();
        Subject sub = (Subject) demo.bind(new RealSubject());
        Subject sub1 = (Subject) demo.bind(new RealSubject());
        String info = sub.info("Stark", 23);
        System.out.println(info);
    }
}

//定义项目接口
interface Subject
{
    String info(String name, int age);
}
//实现
class RealSubject implements Subject
{
    public String info(String name, int age)
    {
        return "姓名："+name+" 年龄："+age;
    }
}
//实现调用
class MyInvocationHandler implements InvocationHandler
{
    private Object obj;
    public Object bind(Object obj) {
        this.obj = obj;
        return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj.getClass().getInterfaces(), this);
    }
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        Object temp = method.invoke(this.obj, args);
        return temp;
    }
}