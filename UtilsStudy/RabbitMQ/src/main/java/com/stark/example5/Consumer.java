package com.stark.example5;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

/**
 * Created by Stark on 2017/7/15.
 */
public class Consumer {
    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
        //创建连接和通道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        final Connection connection = factory.newConnection();
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 4; i++) {
            final int cur = i;
            service.submit(new Runnable() {
                Channel channel = connection.createChannel();
                String queryname = channel.queueDeclare().getQueue();

                public void run() {
                    //创建队列消费者
                    QueueingConsumer consumer = new QueueingConsumer(channel);
                    try {
                        switch (cur) {
                            case 0: //获取0开头的主题消息
                                channel.queueBind(queryname, "topic", "1.#");
                            case 1: //获取3结尾的主题消息
                                channel.queueBind(queryname, "topic", "#.3");
                            case 2: //获取2中间的主题消息
                                channel.queueBind(queryname, "topic", "*.2.*");
                            default://获取4中间的主题消息
                                channel.queueBind(queryname, "topic", "*.4.*");
                        }
                        channel.basicConsume(queryname, consumer);
                        while (true) {
                            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                            String message = new String(delivery.getBody());
                            System.out.println("线程 " + cur + " 获取到消息 " + message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        service.shutdown();
    }
}
