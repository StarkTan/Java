package com.stark;

import com.stark.bubble.BubbleSort;
import com.stark.fast.FastSort;
import com.stark.heap.HeapSort;
import com.stark.insertion.InsertionSort;
import com.stark.merge.MergeSort;
import com.stark.selection.SelectionSort;
import com.stark.shell.ShellSort;
import com.stark.util.ArrayUtil;

import java.util.Arrays;

/**
 * Created by Stark on 2017/8/23.
 * 比较集中排序算法需要的时间
 */
public class SortCompare {
    public static void main(String[] args) {
        int[] array = ArrayUtil.makeArray(100000);
        int[] bubbleArr = Arrays.copyOf(array, array.length);
        int[] selectArr = Arrays.copyOf(array, array.length);
        int[] insertArr = Arrays.copyOf(array, array.length);
        int[] shellArr = Arrays.copyOf(array, array.length);
        int[] mergeArr = Arrays.copyOf(array, array.length);
        int[] fastArr = Arrays.copyOf(array, array.length);
        int[] heapArr = Arrays.copyOf(array, array.length);

        long l1 = System.currentTimeMillis();
        BubbleSort.bubbleSort_V1(bubbleArr);
        long l2 = System.currentTimeMillis();
        System.out.println("冒泡排序耗时：" + (l2 - l1)); //冒泡排序交换太频繁,比较操作过多，时间过长

        SelectionSort.selectionSort(selectArr);
        long l3 = System.currentTimeMillis();
        System.out.println("选择排序耗时：" + (l3 - l2));

        InsertionSort.insertionSort_V1(insertArr);
        long l4 = System.currentTimeMillis();
        System.out.println("插入排序耗时：" + (l4 - l3));

        ShellSort.shellSort_V1(shellArr);
        long l5 = System.currentTimeMillis();
        System.out.println("希尔排序耗时：" + (l5 - l4));

        MergeSort.mergeSort_V2(mergeArr);
        long l6 = System.currentTimeMillis();
        System.out.println("归并排序耗时：" + (l6 - l5));

        FastSort.fastSort_V1(fastArr);
        long l7 = System.currentTimeMillis();
        System.out.println("快速排序耗时：" + (l7 - l6));

        HeapSort.heapSort(heapArr);
        long l8 = System.currentTimeMillis();
        System.out.println("堆排序耗时：" + (l8 - l7));

    }
}
