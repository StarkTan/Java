package com.stark.heap;

import com.stark.util.ArrayUtil;

/**
 * Created by Stark on 2017/8/31.
 */
public class HeapSort {

    public static void heapSort(int[] arr) {
        int len = arr.length;
        int temp;
        //构建堆
        for (int i = len / 2 - 1; i >= 0; i--) {
            keepBigHeap(arr, i, len);
        }
        //排序
        for (int i = len - 1; i >= 0; i--) {
            temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            keepBigHeap(arr, 0, i);
        }


    }

    //维护大顶堆性质
    private static void keepBigHeap(int[] arr, int node, int len) {
        int l = node * 2 + 1;
        int r = node * 2 + 2;
        int largest = node;
        int temp;
        if (len > l && arr[l] > arr[largest]) {
            largest = l;
        }
        if (len > r && arr[r] > arr[largest]) {
            largest = r;
        }
        if (largest != node) {//发生交换
            temp = arr[largest];
            arr[largest] = arr[node];
            arr[node] = temp;
            keepBigHeap(arr, largest, len);
        }
    }

    public static void main(String[] args) {
        int[] array = ArrayUtil.makeArray(10);
        ArrayUtil.print(array);
        System.out.println();
        //insertionSort_V1(array);
        heapSort(array);
        ArrayUtil.print(array);
    }
}
