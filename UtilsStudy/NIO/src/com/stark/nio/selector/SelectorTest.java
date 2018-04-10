package com.stark.nio.selector;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by Stark on 2018/1/2.
 * selector 测试练习
 */
public class SelectorTest {

    public static void main(String[] args) throws IOException {
        new Thread(() -> {
            try {
                //开启一个Selector
                Selector sel_ser = Selector.open();
                //开启服务器server
                ServerSocketChannel server = ServerSocketChannel.open();
                //设置Channel为非阻塞状态
                server.configureBlocking(false);
                server.bind(new InetSocketAddress(9091));
                server.register(sel_ser, SelectionKey.OP_ACCEPT);
                while (true) {
                    //监听满足的channel
                    sel_ser.select();
                    Iterator<SelectionKey> iterator = sel_ser.selectedKeys().iterator();
                    while (iterator.hasNext()) {
                        SelectionKey key = iterator.next();
                        if (key.isAcceptable()) {
                            ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                            SocketChannel socket = channel.accept();
                            socket.configureBlocking(false);
                            socket.register(sel_ser, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
                            iterator.remove();//移除，不然会引起异常
                        }
                        if (key.isWritable()) {
                            SocketChannel socket = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(1000);
                            buffer.put(("message come from server " + new Date() + "\n").getBytes());
                            buffer.flip();
                            socket.write(buffer);
                        }
                        if (key.isReadable()) {
                            SocketChannel socket = (SocketChannel) key.channel();
                            ByteBuffer buffer = ByteBuffer.allocate(100);
                            socket.read(buffer);
                            buffer.flip();
                            byte[] bytes = new byte[buffer.remaining()];
                            buffer.get(bytes);
                            System.out.println(new String(bytes).trim());
                        }
                    }
                    Thread.sleep(2000);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        try {
            //创建Selector 注册在两个SocketChannel上
            Selector cli_ser = Selector.open();
            SocketChannel socket_1 = SocketChannel.open(new InetSocketAddress(9091));
            SocketChannel socket_2 = SocketChannel.open(new InetSocketAddress(9091));
            socket_1.configureBlocking(false);
            socket_2.configureBlocking(false);
            socket_1.register(cli_ser, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
            socket_2.register(cli_ser, SelectionKey.OP_WRITE | SelectionKey.OP_READ);
            //监听Select
            while (true) {
                cli_ser.select();
                Iterator<SelectionKey> iterator = cli_ser.selectedKeys().iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    if (key.isWritable()) {
                        SocketChannel socket = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(100);
                        buffer.put(("message come from client " + new Date() + "\n").getBytes());
                        buffer.flip();
                        socket.write(buffer);
                    }
                    if (key.isReadable()) {
                        SocketChannel socket = (SocketChannel) key.channel();
                        ByteBuffer buffer = ByteBuffer.allocate(1000);
                        socket.read(buffer);
                        buffer.flip();
                        byte[] bytes = new byte[buffer.remaining()];
                        buffer.get(bytes);
                        System.out.println(new String(bytes).trim());
                    }
                }
                Thread.sleep(2000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

}
