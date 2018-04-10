package com.stark.example2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmListener;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Stark on 2017/7/13.
 * 消息生产者
 */
public class Producter {
    //队列名称
    private final static String QUEUE_NAME = "Work_Queue";

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
        //声明队列，可以手动在mq中创建
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        channel.confirmSelect();
        //添加消息确认
        channel.addConfirmListener(new ConfirmListener() {
            public void handleAck(long deliveryTag, boolean multiple) throws IOException {
                if (multiple) {
                    System.out.println("handle ack multiple, tag : " + deliveryTag);
                } else {
                    System.out.println("handle ack, tag : " + deliveryTag);
                }
            }
            public void handleNack(long deliveryTag, boolean multiple) throws IOException {
                System.out.println("handle nack, tag : " + deliveryTag);
            }
        });
        //写入10条数据（直接循环写入）
        for (int i = 0; i < 4; i++) {
            System.out.println("发送第" + i + "信息！");
            String message = "WorkQueue Message number " + i + " " + System.currentTimeMillis();
            channel.basicPublish("", QUEUE_NAME, true, null, message.getBytes());
        }
        channel.close();
        connection.close();
    }
}
