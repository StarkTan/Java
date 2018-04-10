package com.stark.reflect.five;

import java.lang.reflect.Method;

/**
 * Created by HP on 2017/3/31.
 * 使用反射机制调用某个类的方法
 */
public class TestFive
{
    public static void main(String[] args) throws Exception
    {
        Class<?> clazz = Class.forName("com.stark.reflect.five.TestFive");
        Method[] methods = clazz.getMethods(); //无法获得非public方法 getDeclaredMethod可以获取
        for (Method method : methods)
        {
            System.out.println(method.getName());
        }
        Method method1 = clazz.getMethod("method1");
        Method method2 = clazz.getDeclaredMethod("method2",String.class); //无法获得非public方法
        method1.invoke(clazz.newInstance());
        method2.setAccessible(true);
        System.out.println(method2.invoke(clazz.newInstance(),"TSH"));
    }
    public void method1()
    {
        System.out.println("调用方法一");
    }
    private String method2(String msg)
    {
        return msg.toLowerCase();
    }
}
