package com.stark.netty.section_6.charpter_6_1;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;

/**
 * Created by Stark on 2018/3/7.
 * 测试序列化时间
 * <p>
 * 这个测试很无赖啊。没有体现在使用的功能上！
 */
public class TestUserInfo {
    public static void main(String[] args) throws IOException {
        timeTest();
        performTest();
    }

    private static void performTest() throws IOException {
        UserInfo info = new UserInfo();
        info.buildUserID(100).buildUserName("Stark Tan");
        int loop = 1000000;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < loop; i++) {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(info);
            os.flush();
            os.close();
            byte[] b = bos.toByteArray();
            bos.close();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("The jdk serializable cost time is : " + (endTime - startTime) + "ms");
        System.out.println("-------------------------------------");
        startTime = System.currentTimeMillis();
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        for (int i = 0; i < loop; i++) {
            info.codeC(buffer);
        }
        endTime = System.currentTimeMillis();
        System.out.println("The byte serializable cost time is : " + (endTime - startTime) + "ms");

    }

    private static void timeTest() throws IOException {
        UserInfo info = new UserInfo();
        info.buildUserID(100).buildUserName("Stark Tan");
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream os = new ObjectOutputStream(bos);
        os.writeObject(info);
        os.flush();
        os.close();
        byte[] b = bos.toByteArray();
        System.out.println("The jdk serializable length is : " + b.length);
        bos.close();
        System.out.println("---------------------------------------");
        System.out.println("The byte array serializable length is : " + info.codeC().length);
    }
}
