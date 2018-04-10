package com.stark.netty.section_12.charpter;

import io.netty.buffer.ByteBuf;
import org.msgpack.annotation.Message;

import java.nio.ByteBuffer;

/**
 * Created by Stark on 2018/3/16.
 * Netty 协议消息体
 */
@Message
public final class NettyMessage {
    private Header header;//消息头
    private Object body;//消息体

    public final Header getHeader() {
        return header;
    }

    public final void setHeader(Header header) {
        this.header = header;
    }

    public final Object getBody() {
        return body;
    }

    public final void setBody(Object body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Netty Message [header=" + header + "]";
    }
}
