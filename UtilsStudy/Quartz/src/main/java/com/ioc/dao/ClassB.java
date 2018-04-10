package com.ioc.dao;

import com.ioc.anno.Bean;
import com.ioc.anno.Require;

/**
 * Created by Stark on 2018/1/9.
 * ClassB 需要被IOC创建 名称为class_b
 * 具有一个属性 ClassA 需要注入名称为class_a的实例
 */
@Bean(name = "class_b")
public class ClassB {
    @Require(name = "class_a")
    private ClassA classA;

    public String call() {
        return classA.call();
    }
}
