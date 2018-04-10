package com.stark.getresource;

import java.net.URL;

/**
 * Created by Stark on 2018/4/10.
 * 使用 class 和classLoader 访问资源
 * 打包到jar文件中的资源不能访问他的路径了，只能使用Class类的getResourceAsStream()方法来获取内容
 */
public class APP {

    public static void main(String[] args) {
        useClass();
        useClassLoader();
        otherPath();
    }

    //使用class 来访问资源
    //path 以'/' 开头则是从根路径下获取，否则从此类所在包获取
    private static void useClass() {
        Class<APP> appClass = APP.class;
        //在同一个包内
        URL resource = appClass.getResource("test1.txt");
        System.out.println(resource);
        //在根路径下
        resource = appClass.getResource("/test2.txt");
        System.out.println(resource);
        //在上一级包中
        resource = appClass.getResource("../test3.txt");
        System.out.println(resource);
        //在其他包里
        resource = appClass.getResource("../logger/test4.txt");
        System.out.println(resource);
    }

    //使用classLoader 来访问资源
    //path 以'/' 开头则是从根路径下获取，否则从此类所在包获取
    private static void useClassLoader() {
//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        //这样加载更安全
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        //在同一个包内 需要包名+文件名
        URL resource = classLoader.getResource("com/stark/getresource/test1.txt");
        System.out.println(resource);
        //在根路径下 直接使用文件名
        resource = classLoader.getResource("test2.txt");
        System.out.println(resource);
        //在上一级包中
        resource = classLoader.getResource("com/stark/test3.txt");
        System.out.println(resource);
        //在其他包里
        resource = classLoader.getResource("com/stark/logger/test4.txt");
        System.out.println(resource);

        //使用ClassLoader.getSystemResource

        //在同一个包内 需要包名+文件名
        resource = ClassLoader.getSystemResource("com/stark/getresource/test1.txt");
        System.out.println(resource);
        //在根路径下 直接使用文件名
        resource = ClassLoader.getSystemResource("test2.txt");
        System.out.println(resource);
        //在上一级包中
        resource = ClassLoader.getSystemResource("com/stark/test3.txt");
        System.out.println(resource);
        //在其他包里
        resource = ClassLoader.getSystemResource("com/stark/logger/test4.txt");
        System.out.println(resource);
    }

    private static void otherPath(){
        //获取项目路径
        System.out.println(APP.class.getResource("").getPath());

        //获取根路径
        System.out.println(APP.class.getResource("/").getPath());

        //获取当前工程路径
        System.out.println(System.getProperty("user.dir"));
        //获取所有的类路径 包括jar包的路径
        System.out.println(System.getProperty("java.class.path"));
    }
}
