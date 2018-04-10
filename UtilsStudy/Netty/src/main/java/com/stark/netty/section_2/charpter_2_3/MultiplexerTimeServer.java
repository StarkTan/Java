package com.stark.netty.section_2.charpter_2_3;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by Stark on 2018/3/1.
 */
public class MultiplexerTimeServer implements Runnable {

    private Selector selector;

    private ServerSocketChannel servChannel;

    private volatile boolean stop;

    /*初始化类*/
    public MultiplexerTimeServer(int port) {
        try {
            selector = Selector.open();//初始化多路复用选择器
            servChannel = ServerSocketChannel.open(); //初始化serverChannel
            servChannel.configureBlocking(false);//设置channel 为非阻塞式的
            servChannel.bind(new InetSocketAddress(port), 1024);//监听端口和设置最大连接数
            //将ServerSocketChannal 注册到Selector上，SelectionKey.OP_ACCEPT操作位
            servChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("The time server is start in port:" + port);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public void stop() {
        this.stop = true;
    }

    @Override
    public void run() {
        while (!stop) {
            try {
                selector.select(1000);//间隔1s被唤醒一次。无参可能造成阻塞
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (Exception e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null) {
                                key.channel().close();
                            }
                        }
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }

        //多路复用器关闭之后，所有注册在上的Channel资源和Pipe资源都会被自用去注册并关闭，所以不需要重复释放资源。
        if(selector!=null){
            try{
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     *
     */
    private void handleInput(SelectionKey key) throws IOException {

        if (key.isValid()) {
            //处理新接入的请求消息
            //建立新的链接，将链接设置为非阻塞状态，注册到selector中，监控read标记位
            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            }
            //获取通道的数据,根据数据做出反应
            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuffer = ByteBuffer.allocate(1024);
                int readBytes = sc.read(readBuffer);
                if (readBytes > 0) {
                    readBuffer.flip();
                    byte[] bytes = new byte[readBuffer.remaining()];
                    readBuffer.get(bytes);
                    String body = new String(bytes, "UTF-8");
                    System.out.println("The time server receive order : " + body);
                    String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                            new Date().toString() : "BAD ORDER";
                    doWrite(sc, currentTime);
                }else if( readBytes<0){
                    //链路已经关闭需要关闭SocketChannel 释放资源
                    key.cancel();
                    sc.close();
                }

            }
        }

    }

    private void doWrite(SocketChannel channel, String response) throws IOException {
        if (response != null && response.trim().length() > 0) {
            byte[] bytes = response.getBytes();
            ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
            writeBuffer.put(bytes);
            writeBuffer.flip();
            channel.write(writeBuffer);
        }
    }
}
