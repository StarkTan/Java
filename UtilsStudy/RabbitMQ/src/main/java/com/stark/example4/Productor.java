package com.stark.example4;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Stark on 2017/7/15.
 * 消息生产者，声明直接交换机，在传递信息的时候绑定routkey
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
        //声明一个直接交换机
        channel.exchangeDeclare("direct", BuiltinExchangeType.DIRECT);
        System.out.println("发送信息！");
        String message = "WorkQueue Message number " ;
        //第二个参数是routingkey
        channel.basicPublish("direct", "0", true, null, (message+"one").getBytes());
        channel.basicPublish("direct", "1", true, null, (message+"two").getBytes());
        channel.basicPublish("direct", "2", true, null,(message+"three").getBytes());
        channel.close();
        connection.close();
    }
}
