package com.stark.reflect.six;

import java.lang.reflect.Field;

/**
 * Created by HP on 2017/4/1.
 * 通过反射操作对象的值
 */
public class TestSix
{
    private String property1="p1";
    public String property2="p2";

    public static void main(String[] args) throws Exception
    {
        Class<?> clazz = Class.forName("com.stark.reflect.six.TestSix");
        Object six = clazz.newInstance();
        Field field2 = clazz.getField("property2");
        Field field1 = clazz.getDeclaredField("property1");
        System.out.println(field2.get(six));
        field2.set(six,"field2");
        System.out.println(field2.get(six));

        System.out.println(field1.get(six));
        //field1.setAccessible(true); 不需要修改标签？
        field1.set(six,"field1");
        System.out.println(field1.get(six));
    }
}
