package com.stark.classloader;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;

/**
 * Created by Stark on 2018/4/11.
 * 学习ClassLoader 加载外部数据 在D盘创建了一个class文件
 */
public class APP {
    public static void main(String[] args) throws ClassNotFoundException, MalformedURLException, IllegalAccessException, InstantiationException, InvocationTargetException, InterruptedException {
        new Server("D:/Test").run();
    }
}
