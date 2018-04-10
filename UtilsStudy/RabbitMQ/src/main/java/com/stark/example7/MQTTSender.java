package com.stark.example7;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * Created by Stark on 2017/11/3.
 */
public class MQTTSender {
    private static void testSendMqtt() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setHost("127.0.0.1");
        factory.setPort(5672);
        Connection conn = null;
        Channel channel = null;
        try {
            conn = factory.newConnection();
            channel = conn.createChannel();

            byte[] messageBodyBytes = "{'text':'Hello, world!中文'}".getBytes();
            channel.basicPublish("amq.topic", "mqtt.test.aaa", null, messageBodyBytes);
        } finally {
            if (channel != null) {
                channel.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public static void main(String[] args) throws IOException, TimeoutException {
        MQTTSender.testSendMqtt();
    }

}
