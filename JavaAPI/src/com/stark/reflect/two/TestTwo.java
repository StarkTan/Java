package com.stark.reflect.two;

/**
 * Created by HP on 2017/3/31.
 * 使用反射实例化对象
 */
public class TestTwo
{
    public static void main(String[] args) throws ClassNotFoundException
    {
        Class<?> class1 = Class.forName("com.stark.reflect.two.TestTwo");
        Class<?> class2 = new TestTwo().getClass();
        Class<?> class3 = TestTwo.class;
        System.out.println("类名称   " + class1.getName());
        System.out.println("类名称   " + class2.getName());
        System.out.println("类名称   " + class3.getName());
    }
}
