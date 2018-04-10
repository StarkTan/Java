package com.stark.reflect.one;

/**
 * Created by HP on 2017/3/31.
 * 通过反射获取对象的完整包名和类名
 */
public class TestOne
{
    public static void main(String[] args)
    {
        TestOne one = new TestOne();
        String name = one.getClass().getName();
        String simpleName = one.getClass().getSimpleName();
        System.out.println(simpleName);
        System.out.println(name);

        Integer.parseInt("");
    }
}
