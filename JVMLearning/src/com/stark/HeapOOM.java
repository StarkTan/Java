package com.stark;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stark on 2017/10/2.
 * 设置 vm参数： -Xms20m -Xmx20m -XX: +HeapDumpOnOutofMemoryError
 * Xms 堆的最小值 Xmx堆的最大值 设置避免堆自动扩展
 * -XX: +HeapDumpOnOutofMemoryError 出现内存溢出时Dump出当前的内存堆转储快照方便进行分析
 */
public class HeapOOM {
    static class OOMObject{

    }
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
