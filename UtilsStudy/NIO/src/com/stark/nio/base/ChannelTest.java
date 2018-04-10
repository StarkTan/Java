package com.stark.nio.base;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;

/**
 * Created by Stark on 2017/12/26.
 * 使用NIO读写一个文件
 */
public class ChannelTest {
    public static void main(String[] args) throws IOException {
        writeFile("test.txt");
        readFile("test.txt");
        //testServerSocket();
    }

    private static void readFile(String name) {
        File file = new File(name);
        if (!file.exists()) {
            System.out.println("没有此文件: " + name);
        }
        try (FileInputStream inputStream = new FileInputStream(file)) {
            FileChannel channel = inputStream.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(11);
            while (channel.read(buffer) != -1) {
                buffer.flip();
                byte[] bytes = new byte[buffer.limit()];
                buffer.get(bytes);
                buffer.clear();
                System.out.print(new String(bytes, "utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeFile(String name) {
        try (FileOutputStream outputStream = new FileOutputStream(new File(name))) {
            FileChannel channel = outputStream.getChannel();
            ByteBuffer buf;
            for (int i = 0; i < 100; i++) {
                String str = "test num : " + i + "\r\n";
                buf = Charset.forName("utf-8").encode(str);
                channel.write(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void testServerSocket() throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9090));
        SocketChannel socketChannel = serverSocketChannel.accept();
        ByteBuffer buf = ByteBuffer.allocate(1024);
        int read = socketChannel.read(buf);
        System.out.println(read);
        buf.flip();
        byte[] bytes = new byte[buf.limit()];
        buf.get(bytes);
        buf.clear();
        System.out.println(new String(bytes, "utf-8"));
        socketChannel.close();
    }
}
