package com.stark.reflect.four;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * Created by HP on 2017/3/31
 * 通过反射获取.某个类的全部参数
 */
public class TestFour
{
    public static void main(String[] args) throws Exception
    {
        //1.
        Class<?> class1 = Class.forName("com.stark.reflect.four.User");
        User user = (User) class1.newInstance();
        user.setName("StarkTan");
        user.setAge(29);
        user.setMale(true);
        System.out.println(user);

        //2
        Constructor<?>[] constructors = class1.getConstructors();

        for(Constructor<?> constructor : constructors)
        {
            Class<?>[] clazzs = constructor.getParameterTypes(); //getParameterAnnotations() 获取注解
            for(Class<?> clazz : clazzs)
            {
                System.out.print(clazz.getName()+"");
            }
            System.out.println("");
        }
        user = (User) constructors[0].newInstance("Stark",true);
        System.out.println(user);
        user = (User) constructors[1].newInstance("Stark");
        System.out.println(user);

        Constructor<?> declaredConstructor = class1.getDeclaredConstructor(String.class, Integer.class, boolean.class);
        declaredConstructor.setAccessible(true); //调用非public函数或者方法需要允许进入
        user = (User) declaredConstructor.newInstance("Stark",23,true);
        System.out.println(user);
    }
}
