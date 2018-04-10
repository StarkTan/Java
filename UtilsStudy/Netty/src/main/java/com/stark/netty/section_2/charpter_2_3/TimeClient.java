package com.stark.netty.section_2.charpter_2_3;

import com.stark.netty.section_2.charpter_2_1.TimeServerHandler;

/**
 * Created by Stark on 2018/3/1.
 * NIO时间服务器客户端
 */
public class TimeClient {
    public static void main(String[] args) {
        int port = 8080;
        new Thread(new TimeClientHandler(port)).start();
    }
}
