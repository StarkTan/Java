package com.stark.selection;

import com.stark.util.ArrayUtil;

/**
 * Created by Stark on 2017/8/23.
 * 选择排序
 */
public class SelectionSort {

    public static void selectionSort(int[] array) {
        int len = array.length;
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j; //记录最小值位置
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
    }

    public static void main(String[] args) {
        int[] arr = ArrayUtil.makeArray(6);
        ArrayUtil.print(arr);
        selectionSort(arr);
        ArrayUtil.print(arr);
    }
}
