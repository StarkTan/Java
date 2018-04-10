package com.stark.netty.section_6.charpter_6_1;

import java.io.Serializable;
import java.nio.ByteBuffer;

/**
 * Created by Stark on 2018/3/7.
 * POJO 对象类
 */
public class UserInfo implements Serializable {
    /**
     * 默认的序列号
     */
    private static final long serialVersionUID = 1L;

    private String userName;
    private int userID;

    public UserInfo buildUserID(int userID) {
        this.userID = userID;
        return this;
    }

    public UserInfo buildUserName(String userName) {
        this.userName = userName;
        return this;
    }


    public final String getUserName() {
        return userName;
    }

    public final void setUserName(String userName) {
        this.userName = userName;
    }

    public final int getUserID() {
        return userID;
    }

    public final void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * 用于字节长度测试
     *
     * @return
     */
    public byte[] codeC() {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        byte[] value = this.userName.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.userID);
        buffer.flip();
        //value = null;
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;
    }

    /**
     * 简单修改
     * @param buffer
     * @return
     */
    public byte[] codeC(ByteBuffer buffer) {
        buffer.clear();
        byte[] value = this.userName.getBytes();
        buffer.putInt(value.length);
        buffer.put(value);
        buffer.putInt(this.userID);
        buffer.flip();
        //value = null;
        byte[] result = new byte[buffer.remaining()];
        buffer.get(result);
        return result;


    }
}
