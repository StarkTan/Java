package com.stark.example1;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Stark on 2017/7/12.
 * 消息生产者
 */
public class Producter {
    //队列名称
    private final static String QUEUE_NAME = "first_try";
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
        //声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        //发送消息
        String message = "RabbitMQ Test Message "+System.currentTimeMillis();
        channel.basicPublish("",QUEUE_NAME,true,null,message.getBytes());
        //关闭连接
        channel.close();
        connection.close();
    }
}
