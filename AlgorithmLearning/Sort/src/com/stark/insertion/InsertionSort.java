package com.stark.insertion;

import com.stark.util.ArrayUtil;

import java.util.Arrays;

/**
 * Created by Stark on 2017/8/23.
 * 插入排序
 */
public class InsertionSort {
    public static void insertionSort_V1(int[] array) {
        int len = array.length;
        for (int i = 1; i < len; i++) {
            int j = i - 1;
            int key = array[i];
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j--;
            }
            array[j + 1] = key;
        }
    }

    //使用二分查找法加速（失败）
    public static void insertionSort_V2(int[] array) {
        int len = array.length;
        for (int i = 1; i < len; i++) {
            int key = array[i];
            int left = 0;
            int right = i - 1;
            while (right >= left) {
                int mid = (right + left) / 2;
                if (array[mid] > key) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            for (int j = i - 1; j >= left; j--) {
                array[j + 1] = array[j];
            }
            array[left] = key;
        }
    }


    public static void main(String[] args) {
//        int[] array = ArrayUtil.makeArray(10);
//        ArrayUtil.print(array);
//        System.out.println();
//        //insertionSort_V1(array);
//        insertionSort_V2(array);
//        ArrayUtil.print(array);
        int[] array1 = ArrayUtil.makeArray(100000);
        int[] array2 = Arrays.copyOf(array1, array1.length);
        long l1 = System.currentTimeMillis();
        insertionSort_V1(array1);
        long l2 = System.currentTimeMillis();
        System.out.println("耗时：" + (l2 - l1));
        insertionSort_V2(array2);
        long l3 = System.currentTimeMillis(); //由于一样要循环移动，耗时更长
        System.out.println("耗时：" + (l3 - l2));
    }
}
