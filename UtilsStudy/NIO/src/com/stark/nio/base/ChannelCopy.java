package com.stark.nio.base;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by Stark on 2018/1/2.
 * 测试channel之间的数据传输
 */
public class ChannelCopy {

    public static void main(String[] args) throws InterruptedException {
        /*测试数据冲socket中写入到文本中
        new Thread(ChannelCopy::copyToFile).start();
        Thread.sleep(2000);
        new Thread(() -> {
            try (Socket socket = new Socket("127.0.0.1", 9090);
                 OutputStream outputStream = socket.getOutputStream();) {
                outputStream.write("test copy socket data to file".getBytes());
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();*/
        new Thread(ChannelCopy::copyToSocket).start();
        Thread.sleep(2000);
        new Thread(() -> {
            try (Socket socket = new Socket("127.0.0.1", 9090);
                 InputStream inputStream = socket.getInputStream()) {
                byte[] bytes = new byte[100];
                inputStream.read(bytes);
                System.out.println(new String(bytes));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    //获取一个socket连接
    private static SocketChannel getSocket() throws IOException {
        ServerSocketChannel channel = ServerSocketChannel.open();
        channel.bind(new InetSocketAddress(9090));
        return channel.accept();
    }

    //从socket中获取数据写入到文本中
    private static void copyToFile() {
        String fileName = "test.txt";
        try (FileOutputStream outputStream = new FileOutputStream(new File(fileName))) {
            SocketChannel socket = getSocket();
            FileChannel channel = outputStream.getChannel();
            channel.transferFrom(socket, 0, 100);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //将数据从文本中写入到socket中
    private static void copyToSocket() {
        String fileName = "test.txt";
        try (FileInputStream outputStream = new FileInputStream(new File(fileName))) {
            SocketChannel socket = getSocket();
            FileChannel channel = outputStream.getChannel();
            channel.transferTo(0, 100, socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
