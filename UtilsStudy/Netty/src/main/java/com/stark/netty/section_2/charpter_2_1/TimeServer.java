package com.stark.netty.section_2.charpter_2_1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Stark on 2018/1/13.
 * 同步阻塞式IO创建TimeServer
 */
public class TimeServer {

    public static void main(String[] args) {
        try (ServerSocket server = new ServerSocket(8080)) {
            System.out.println("Time Server Started!");
            Socket socket;
            TimeServerHandlerExcutePool pool = new TimeServerHandlerExcutePool(10, 1000);
            while (true) {
                socket = server.accept();//阻塞监听
                //使用线程池优化
                pool.execute(new TimeServerHandler(socket));
                //new Thread(new TimeServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
