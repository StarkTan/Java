package com.stark.netty.section_2.charpter_2_1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Stark on 2018/1/13.
 * 同步阻塞式 请求客户端
 */
public class TimeClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("127.0.0.1", 8080);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {
            out.println("QUERY TIME ORDER");
            System.out.println("message send succeed");
            String res = in.readLine();
            System.out.println("Now is : " + res);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
