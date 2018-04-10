package com.stark.example6;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Stark on 2017/7/16.
 * 服务器端
 */
public class RPCServer {
    private final static String RPC_Queue_Name = "rpc_queue";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(RPC_Queue_Name,false,false,false,null);
        channel.basicQos(1);

        QueueingConsumer consumer = new QueueingConsumer(channel);
        channel.basicConsume(RPC_Queue_Name, false, consumer);
        System.out.println(" [x] Awaiting RPC requests");

        while (true) {
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();

            //获取请求中的correlationId属性值，并将其设置到结果消息的correlationId属性中
            BasicProperties props = delivery.getProperties();
            AMQP.BasicProperties replyProps = new AMQP.BasicProperties.Builder().correlationId(props.getCorrelationId()).build();
            //获取回调队列名字
            String callQueueName = props.getReplyTo();

            String message = new String(delivery.getBody(),"UTF-8");

            System.out.println(" [.] fib(" + message + ")");

            //获取结果
            String response = "" + fib(Integer.parseInt(message));
            //先发送回调结果
            channel.basicPublish("", callQueueName, replyProps,response.getBytes());
            //后手动发送消息反馈
            channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
        }
    }

    private static int fib(int i)
    {
        if(i==0) return 0;
        if (i==1) return 1;
        return fib(i-1) +fib(i-2);
    }
}
