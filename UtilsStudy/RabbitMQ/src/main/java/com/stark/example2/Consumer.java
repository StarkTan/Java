package com.stark.example2;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeoutException;

/**
 * Created by Stark on 2017/7/12.
 * 创建多个消费者从队列中取出信息
 */
public class Consumer {
    //队列名称
    private final static String QUEUE_NAME = "Work_Queue";

    public void getConsum() throws IOException, TimeoutException, InterruptedException {
        //创建连接和通道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        //声明队列，主要为了防止消息接收者先运行此程序，队列还不存在时创建队列。
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        //创建队列消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);
        //指定消费队列，且改为手动确认
        channel.basicConsume(QUEUE_NAME, false, consumer);
        channel.basicQos(1);//保证一次只分发一个
        while (true)
        {
            //nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println(" 获取到消息 " + message + "开始处理");
            try{
                Thread.sleep(10000);
            }catch (InterruptedException e){}
            finally {
                channel.basicAck(delivery.getEnvelope().getDeliveryTag()
                        , false);
            }
            System.out.println( message + "处理完成");
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException, TimeoutException {
        //创建连接和通道
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        final Connection connection = factory.newConnection();
        ExecutorService service = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 2; i++) {
            final int cur = i;
            service.submit(new Runnable() {
                Channel channel = connection.createChannel();
                public void run() {
                    //创建队列消费者
                    QueueingConsumer consumer = new QueueingConsumer(channel);
                    //指定消费队列
                    try {
                        //指定ack消息确认为失败
                        channel.basicConsume(QUEUE_NAME, false, consumer);
                        while (true) {
                            //nextDelivery是一个阻塞方法（内部实现其实是阻塞队列的take方法）
                            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
                            String message = new String(delivery.getBody());
                            System.out.println("线程 " + cur + " 获取到消息 " + message + "开始处理");
                            Thread.sleep(1000 * (cur + 5));
                            System.out.println("线程 " + cur + " " + message + "处理完成");
                            //确认消息，已经收到
                            channel.basicAck(delivery.getEnvelope().getDeliveryTag()
                                    , false);
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
