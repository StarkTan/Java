package com.reflect;

import java.lang.annotation.*;

/**
 * Created by Stark on 2018/1/8.
 * 类和接口的注解
 */
@Target(value = {ElementType.TYPE, ElementType.METHOD, ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
//@Inherited 被子类继承
public @interface Anno {
    public String name();
}
