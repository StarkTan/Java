package com.stark.netty.section_2.charpter_2_4;

/**
 * Created by Stark on 2018/3/2.
 * AIO 时间服务器客户端
 */
public class TimeClient {
    public static void main(String[] args) {
        AsyncTimeClientHandler asyncTimeClientHandler = new AsyncTimeClientHandler(8080);
        new Thread(asyncTimeClientHandler).start();
    }
}
