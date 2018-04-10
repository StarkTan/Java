package com.reflect;

import java.util.Date;

/**
 * Created by Stark on 2018/1/8.
 * 接口
 */
@Anno(name = "Interface")
public interface IBase<T> {
    default void callI(Date date) {
        System.out.println("I am Interface! " + date);
    }
}
