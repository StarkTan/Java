package com.stark.nio.base;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by Stark on 2018/1/2.
 * scatter/gather 用于描述从Channel中读取或者写入到CHannel中的操作
 */
public class Scatter_Gather {
    public static void main(String[] args) throws IOException {
        String fileName = "test.txt";
        try (FileOutputStream outputStream = new FileOutputStream(new File(fileName))) {
            FileChannel channel = outputStream.getChannel();
            ByteBuffer head = ByteBuffer.allocate(4);
            ByteBuffer body = ByteBuffer.allocate(12);
            head.put("head".getBytes());
            body.put("this is body".getBytes());
            head.flip();
            body.flip();
            ByteBuffer[] message = {head, body};
            channel.write(message);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileInputStream inputStream = new FileInputStream(new File(fileName))) {
            FileChannel channel = inputStream.getChannel();
            ByteBuffer head = ByteBuffer.allocate(4);
            ByteBuffer body = ByteBuffer.allocate(12);
            ByteBuffer[] message = {head, body};
            //向buffer中写入数据，上一个写完才会写下一个
            channel.read(message);
            System.out.println(new String(head.array()));
            System.out.println(new String(body.array()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
