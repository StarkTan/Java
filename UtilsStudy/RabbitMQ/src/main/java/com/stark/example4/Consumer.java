package com.stark.example4;

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
                        //第四个线程绑定多个routkey
                        if (cur == 3) {
                            channel.queueBind(queryname, "direct", "0");
                            channel.queueBind(queryname, "direct", "1");
                            channel.queueBind(queryname, "direct", "2");
                        } else {
                            //第三个参数是routingkey
                            channel.queueBind(queryname, "direct", cur + "");
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
