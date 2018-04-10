package com.ioc;

import com.ioc.dao.ClassB;

/**
 * Created by Stark on 2018/1/9.
 * 测试，从IOCMap中获取叫 classb_的实例
 */
public class App {
    public static void main(String[] args) {
        IOCMap iocMap = new IOCMap();
        ClassB classB = (ClassB) iocMap.getBean("class_b");
        System.out.println(classB.call());
    }
}