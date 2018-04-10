package com.stark.netty.section_2.charpter_2_4;

/**
 * Created by Stark on 2018/3/2.
 * AIO时间服务器服务端
 */
public class TimeServer {
    public static void main(String[] args) {
        int port = 8080;
        AsyncTimeServerHandler timeServer = new AsyncTimeServerHandler(port);
        new Thread(timeServer).start();
    }
}