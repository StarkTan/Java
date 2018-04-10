package com.stark;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Created by Stark on 2017/10/30.
 * 本机直接溢出
 * -XX:MaxDirectMemorySize=10M //不指定和堆的最大值一样
 */
public class DirectMemoryOOM {

    private static final int _1MB  = 1024*1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true){
            unsafe.allocateMemory(_1MB); //直接请求内存 而不是通过计算抛出异常
        }
    }

}
