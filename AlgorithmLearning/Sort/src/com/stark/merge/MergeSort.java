package com.stark.merge;

import com.stark.bubble.BubbleSort;
import com.stark.util.ArrayUtil;

import java.util.Arrays;

/**
 * Created by Stark on 2017/8/30.
 * 归并排序
 */
public class MergeSort {
    //MD我就不使用递归！
    public static void mergeSort_V1(int[] arr) {
        int len = arr.length;
        int[] copy = new int[len];//占用空间
        for (int wid = 1; wid < len; wid = wid * 2) {//每次比较数
            for (int i = 0; i < len; i = i + 2 * wid) {//每次比较的两个数
                //定位要比较的两个数组
                //定位比较的数组是否完善
                int begin_1 = i;
                int end_1 = begin_1 + wid - 1;
                if (end_1 >= len - 1) { //第一个数组到末尾,不需要比较
                    for(int j = begin_1;j<len;j++){
                        copy[j] = arr[j];
                    }
                    continue;
                }
                int begin_2 = end_1 + 1;
                int end_2 = begin_2 + wid - 1;
                if (end_2 >= len - 1) { //第二个数组到达末尾
                    end_2 = len - 1;
                }
                //对两个数组进行排序
                for (int j = begin_1; j <= end_2; j++) {
                    if (begin_1 > end_1) {
                        copy[j] = arr[begin_2];
                        begin_2++;
                        continue;
                    }
                    if (begin_2 > end_2) {
                        copy[j] = arr[begin_1];
                        begin_1++;
                        continue;
                    }
                    int num1 = arr[begin_1];
                    int num2 = arr[begin_2];
                    if (num1 <= num2) {
                        copy[j] = num1;
                        begin_1++;
                    } else {
                        copy[j] = num2;
                        begin_2++;
                    }
                }
            }
            //复制copy到arr，看传递方式选择优化，可以追加空间消耗换取时间
            for(int i =0;i<len;i++){
                arr[i] = copy[i];
            }
        }
    }

    //还是写一个递归吧
    public static void mergeSort_V2(int[] arr){
        int begin = 0;
        int end = arr.length-1;
        int[] copy = new int[arr.length];
        merger(arr,0,end,copy);

    }
    private static void merger(int[] arr,int begin,int end,int[] copy){
        if(begin==end) return;
        //构建递归
        int begin_1 = begin;
        int end_1 = (begin+end)/2;
        int begin_2 =end_1+1;
        int end_2 = end;
        merger(arr,begin_1,end_1,copy);
        merger(arr,begin_2,end_2,copy);
        //构建主逻辑,对两个数据排序
        for (int j = begin; j <= end; j++) {
            if (begin_1 > end_1) {
                copy[j] = arr[begin_2];
                begin_2++;
                continue;
            }
            if (begin_2 > end_2) {
                copy[j] = arr[begin_1];
                begin_1++;
                continue;
            }
            int num1 = arr[begin_1];
            int num2 = arr[begin_2];
            if (num1 <= num2) {
                copy[j] = num1;
                begin_1++;
            } else {
                copy[j] = num2;
                begin_2++;
            }
        }
        //复制到arr中
        for (int j = begin_1; j <= end_2; j++){
            arr[j] = copy[j];
        }
    }
    public static void main(String[] args) {
        int[] array = ArrayUtil.makeArray(1000000);
        int[] testarr = Arrays.copyOf(array, array.length);
        //BubbleSort.bubbleSort_V2(testarr);
        //ArrayUtil.print(array);
        System.out.println();
        mergeSort_V1(array);
        //ArrayUtil.print(array);

        for(int i=0;i<array.length;i++){
            if(array[i]!=testarr[i]) {
                System.out.println(false);
                break;
            }
        }
    }
}
