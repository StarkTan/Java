package com.stark.shell;

import com.stark.util.ArrayUtil;

/**
 * Created by Stark on 2017/8/29.
 * 希尔排序
 */
public class ShellSort {
    public static void shellSort_V1(int[] arr){
        int len = arr.length;
        int gap = 1;
        //动态规划间距
        while(gap<len/5){
            gap = gap*5+1; //可以保证每次有层次变化
        }
        for(;gap>0; gap=gap/5){
            for(int i =gap;i<len;i++){
                int temp = arr[i];
                for(int j=i-gap;j>=0&&temp<arr[j];j=j-gap){
                    arr[j+gap] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }
    public static void main(String[] args) {
        int[] array = ArrayUtil.makeArray(10);
        ArrayUtil.print(array);
        System.out.println();
        shellSort_V1(array);
        ArrayUtil.print(array);
    }
}
