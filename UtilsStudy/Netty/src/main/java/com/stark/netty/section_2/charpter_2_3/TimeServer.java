package com.stark.netty.section_2.charpter_2_3;

/**
 * Created by Stark on 2018/3/1.
 * NIO时间服务器 入口
 */
public class TimeServer {

    public static void main(String[] args) {
        int port = 8080;
        MultiplexerTimeServer timeServer = new MultiplexerTimeServer(port);
        new Thread(timeServer,"NIO-MultiplexerTimerServer-001").start();
    }
}
