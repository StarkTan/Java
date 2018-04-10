package com.ioc.dao;

import com.ioc.anno.Bean;
import com.ioc.anno.Require;

/**
 * Created by Stark on 2018/1/9.
 * ClassA 需要被IOC容器实例化 名称为 class_a
 */
@Bean(name = "class_a")
public class ClassA {

    public String call() {
        return " I am A";
    }
}
