package com.stark.example6;

import com.rabbitmq.client.*;
import com.stark.example2.Consumer;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Stark on 2017/7/16.
 */
public class RPCClient {
    private final static String RPC_Queue_Name = "rpc_queue";

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明队列
        channel.queueDeclare(RPC_Queue_Name, false, false, false, null);

        //为每一个客户端获取一个随机的回调队列
        String replyQueueName = channel.queueDeclare().getQueue();
        //为每一个客户端创建一个消费者（用于监听回调队列，获取结果）
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //消费者与队列关联
        channel.basicConsume(replyQueueName, true, consumer);

        String response = null;
        String corrId = java.util.UUID.randomUUID().toString();

        //设置replyTo和correlationId属性值
        AMQP.BasicProperties props = new AMQP.BasicProperties.Builder().correlationId(corrId).replyTo(replyQueueName).build();

        //发送消息到rpc_queue队列
        channel.basicPublish("", RPC_Queue_Name, props, "8".getBytes());

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            if (delivery.getProperties().getCorrelationId().equals(corrId)) {
                response = new String(delivery.getBody(),"UTF-8");
                break;
            }
        }
        System.out.println( "fib(8) is " + response);
    }
}
