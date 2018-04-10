package com.stark.netty.section_12.charpter;

import org.msgpack.annotation.Message;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stark on 2018/3/16.
 * 消息头定义
 */
@Message
public final class Header {
    private int crcCode = 0xabef0101; //0xabef 固定值，表示Netty消息协议，2个字节，主版本号01，次版本号01
    private int length; //消息长度
    private long sessionID; //会话ID
    private byte type; //消息类型
    private byte priority; //消息优先级


    public final int getCrcCode() {
        return crcCode;
    }

    public final void setCrcCode(int crcCode) {
        this.crcCode = crcCode;
    }

    public final int getLength() {
        return length;
    }

    public final void setLength(int length) {
        this.length = length;
    }

    public final long getSessionID() {
        return sessionID;
    }

    public final void setSessionID(long sessionID) {
        this.sessionID = sessionID;
    }

    public final byte getType() {
        return type;
    }

    public final void setType(byte type) {
        this.type = type;
    }


    public final byte getPriority() {
        return priority;
    }

    public final void setPriority(byte priority) {
        this.priority = priority;
    }


    @Override
    public String toString() {
        return "Header [crcCode=" + crcCode + ", length=" + length + ", sessionID=" + sessionID + ",type=" + type + ", priority=" + priority + "]";
    }
}
