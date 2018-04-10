package com.stark.example7;

import org.fusesource.hawtbuf.Buffer;
import org.fusesource.hawtbuf.UTF8Buffer;
import org.fusesource.mqtt.client.*;

import java.net.URISyntaxException;

/**
 * Created by Stark on 2017/11/3.
 */
public class MqttTest {
    public static void main(String[] args) throws URISyntaxException {
        MQTT mqtt = new MQTT();
        mqtt.setHost("tcp://localhost:1883");
        final CallbackConnection connection = mqtt.callbackConnection();
        connection.listener(new Listener() {

            public void onDisconnected() {
                System.out.println("listen disConnect");
            }

            public void onConnected() {
                System.out.println("listent connect");
            }

            public void onPublish(UTF8Buffer topic, Buffer payload, Runnable ack) {
                System.out.println("listen publish");
                System.out.println(UTF8Buffer.decode(topic));
                System.out.println(UTF8Buffer.decode(Buffer.utf8(payload)));
                ack.run();
            }

            public void onFailure(Throwable value) {
                System.out.println("listen fail" + value.toString());
            }
        });
        connection.connect(new Callback<Void>() {
            public void onFailure(Throwable value) {
                System.out.println("connect fail");
            }

            // Once we connect..
            public void onSuccess(Void v) {
                // Subscribe to a topic
                Topic[] topics = {new Topic("mqtt/test/#", QoS.AT_LEAST_ONCE)};
                connection.subscribe(topics, new Callback<byte[]>() {
                    public void onSuccess(byte[] qoses) {
                        System.out.println("subscribe success");
                        System.out.println(new String(qoses).toString());
                    }

                    public void onFailure(Throwable value) {
                        System.out.println("subscribe fail");
                    }
                });
            }
        });
        try {
            Thread.sleep(100000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // To disconnect..
        connection.disconnect(new Callback<Void>() {
            public void onSuccess(Void v) {
                System.out.println("disconnect success");
            }

            public void onFailure(Throwable value) {
                System.out.println("fail");
            }
        });

    }
}
