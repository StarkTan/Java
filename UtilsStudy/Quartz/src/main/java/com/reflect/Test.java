package com.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.Date;

/**
 * Created by Stark on 2018/1/8.
 * 测试
 */
public class Test {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException, InstantiationException {
        Base base = new Base();
        Class<? extends Base> baseclass = base.getClass();
        Class<?> superclass = baseclass.getSuperclass();
        Class<?>[] interfaces = baseclass.getInterfaces();
        Class tclass = (Class) ((ParameterizedType) baseclass.getGenericSuperclass())
                .getActualTypeArguments()[0];
        Class oclass = (Class) ((ParameterizedType) baseclass.getGenericInterfaces()[0])
                .getActualTypeArguments()[0];
        //获取 类名，父类名，接口名，泛型类型，注解
        System.out.println("class name-->" + baseclass.getSimpleName());
        System.out.println("supclass name-->" + superclass.getSimpleName());
        System.out.println("interface name-->" + interfaces[0].getSimpleName());
        System.out.println("supclass t name-->" + tclass.getSimpleName());
        System.out.println("interface t name-->" + oclass.getSimpleName());
        Anno baseanno = baseclass.getAnnotation(Anno.class);
        Anno superclassanno = superclass.getAnnotation(Anno.class);
        Anno interfacesanno = interfaces[0].getAnnotation(Anno.class);
        //都成了代理类 注解类的信息包含在里面
        //System.out.println("Annotation name---->"+baseanno.getClass().getSimpleName());
        //可以获取参数
        System.out.println("class Annotation Value---->" + baseanno.name());
        System.out.println("supclass Annotation Value---->" + superclassanno.name());
        System.out.println("interface Annotation Value---->" + interfacesanno.name());

        //获取方法：自身方法，父类方法，接口方法，注解，参数类型，参数注解
        Method[] methods = baseclass.getDeclaredMethods(); //getMethods（） 会获得所有public方法
        for (Method method : methods) {
            method.setAccessible(true);//这样就可以访问私有方法了
            System.out.println(method.getName());
            //获取方法参数
            Type[] paramTypes = method.getGenericParameterTypes();
            System.out.println(paramTypes[0].getTypeName());
            method.invoke(base, new Date());
        }
        methods = superclass.getDeclaredMethods(); //getMethods（） 会获得所有public方法
        for (Method method : methods) {
            method.setAccessible(true);//这样就可以访问私有方法了
            System.out.println(method.getName());
            method.invoke(base, new Date());
        }
        methods = interfaces[0].getDeclaredMethods(); //getMethods（） 会获得所有public方法
        for (Method method : methods) {
            System.out.println(method.getName());
            method.invoke(base, new Date());
        }
        Method method = baseclass.getDeclaredMethod("callPri", Date.class);
        method.setAccessible(true);
        //获取详细的方法注解
        Annotation[] annos = method.getDeclaredAnnotations();
        System.out.println(((Anno) annos[0]).name());
        //获取方法参数注解
        Anno anno = (Anno) method.getParameterAnnotations()[0][0];
        System.out.println(anno.name());

        //属性：自身属性，父类属性，属性修改，注解
        Field[] fields = baseclass.getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            System.out.println(field.getName());
            System.out.println(field.get(base));
        }
        fields = superclass.getDeclaredFields();
        for(Field field:fields){
            field.setAccessible(true);
            System.out.println(field.getName());
            System.out.println(field.get(base));
        }
        Field field = baseclass.getDeclaredField("namePri");
        field.setAccessible(true);
        Object orig = field.get(base);
        field.set(base,orig+" change");
        System.out.println(field.get(base));

        anno = (Anno) field.getDeclaredAnnotations()[0];
        System.out.println(anno.name());
    }
}
