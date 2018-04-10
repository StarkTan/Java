package com.stark.example3;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Stark on 2017/7/13.
 * 消息生产者
 */
public class Productor {
    public static void main(String[] args) throws IOException, TimeoutException {
        //配置rabbitmq服务器地址
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        factory.setPort(5672);
        factory.setUsername("starktan");
        factory.setPassword("starktan");
        factory.setVirtualHost("/");
        //建立连接和通道
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明一个扇形交换机
        channel.exchangeDeclare("fanout", BuiltinExchangeType.FANOUT);
        System.out.println("发送信息！");
        String message = "WorkQueue Message number " + System.currentTimeMillis();
        channel.basicPublish("fanout", "", true, null, message.getBytes());
        channel.close();
        connection.close();
    }
}
