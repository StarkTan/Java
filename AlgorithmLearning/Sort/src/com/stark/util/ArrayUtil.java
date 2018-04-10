package com.stark.util;

import com.sun.istack.internal.NotNull;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Stark on 2017/8/13.
 * 数组的工具类，可以打印数组，比较生成随机整数数组
 */
public class ArrayUtil {
    //打印数组
    public static void print(@NotNull int[] array) {
        System.out.print("当前数组：");
        for(int i = 0;i<array.length;i++)
        {
            System.out.print(array[i]+" ");
        }
    }

    /**
     * 生成随机数组，0-10000
     * @param capacity
     * @return
     */
    public static int[] makeArray(int capacity) {
        int[] array = new int[capacity];
        Random random = new Random();
        for(int i = 0;i<capacity;i++)
        {
            array[i] = random.nextInt(10000);
        }
        return array;
    }
}
