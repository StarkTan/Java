package com.stark.reflect.three;

import java.io.Serializable;

/**
 * Created by HP on 2017/3/31.
 */
public class TestThree implements Serializable
{
    public static void main(String[] args) throws ClassNotFoundException
    {
        Class<?> class1 = Class.forName("com.stark.reflect.three.TestThree");
        Class<?> superclass = class1.getSuperclass();
        System.out.println("继承的父类： "+ superclass.getName() );

        Class<?>[] interfaces = class1.getInterfaces();
        for(Class<?> inter : interfaces)
        {
            System.out.println("实现的接口： "+ inter.getName() );
        }
    }
}
