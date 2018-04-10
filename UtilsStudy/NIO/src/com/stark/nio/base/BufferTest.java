package com.stark.nio.base;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Created by Stark on 2017/12/26.
 * 测试Buffer的读写
 */
public class BufferTest {
    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(12); //初始化buffer，使用charbuffer为例
        print("write", buffer); //初始时是写模式，数据从pos写入
        String str = "testBuffer";
        buffer.put(str.getBytes()); //使用10个字节填充buffer
        print("write", buffer);
        buffer.flip();//切换成读模式
        print("read", buffer);
        buffer.get(new byte[3]);//读取三个字节
        print("read", buffer);
        buffer.compact();//切换成写模式，将没有被读取的数据放到起始位置
        print("write", buffer);
        buffer.flip();//切换成读模式
        print("read", buffer);
        buffer.clear();//切换成写模式，直接从最开始写入
        print("write", buffer);
    }

    private static void print(String str, Buffer buffer) {
        String sb = str + " , pos: " + buffer.position() +
                " , lim: " + buffer.limit() +
                " , cap: " + buffer.capacity();
        System.out.println(sb);
    }
}
