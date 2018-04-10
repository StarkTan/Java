package com.stark.bubble;

import com.stark.util.ArrayUtil;

import java.util.*;

/**
 * Created by Stark on 2017/8/13.
 * 冒泡排序
 */
public class BubbleSort {

    /**
     * 它重复地走访过要排序的数列，一次比较两个元素，如果它们的顺序错误就把它们交换过来
     */
    public static void bubbleSort_V1(int[] array) {
        int len = array.length;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len - 1 - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

    /* 设置一标志性变量pos,用于记录每趟排序中最后一次进行交换的位置。
     * 由于pos位置之后的记录均已交换到位,故在进行下一趟排序时只要扫描到pos位置即可。*/
    public static void bubbleSort_V2(int[] array) {
        int len = array.length;
        int end = len - 1;
        while (end > 0) {
            int pos = 0; //初始化位置
            for (int j = 0; j < end; j++) {
                if (array[j] > array[j + 1]) {
                    pos = j;
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
            end = pos;
        }
    }

    /* 考虑利用在每趟排序中进行正向和反向两遍冒泡的方法一次可以得到两个最终值*/
    public static void bubbleSort_V3(int[] array) {
        int len = array.length;
        int low = 0;
        int high = len - 1;

        while (low < high) {
            for (int i = low; i < high; i++) {
                if (array[i] > array[i + 1]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
            high--;
            for (int i = high; i > low; i--) {
                if (array[i] < array[i - 1]) {
                    int temp = array[i];
                    array[i] = array[i - 1];
                    array[i - 1] = temp;
                }
            }
            low++;
        }
    }

    public static void main(String[] args) {
        int[] array1 = ArrayUtil.makeArray(100000);
        int[] array2 = Arrays.copyOf(array1, array1.length);
        int[] array3 = Arrays.copyOf(array1, array1.length);
        long l1 = System.currentTimeMillis();
        bubbleSort_V1(array1);
        long l2 = System.currentTimeMillis();
        System.out.println("耗时：" + (l2 - l1));
        bubbleSort_V2(array2);
        long l3 = System.currentTimeMillis();
        System.out.println("耗时：" + (l3 - l2));
        bubbleSort_V3(array3);
        long l4 = System.currentTimeMillis();
        System.out.println("耗时：" + (l4 - l3));
    }
}
