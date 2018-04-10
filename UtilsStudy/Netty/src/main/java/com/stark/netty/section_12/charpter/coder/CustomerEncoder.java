package com.stark.netty.section_12.charpter.coder;

import com.stark.netty.section_12.charpter.Header;
import com.stark.netty.section_12.charpter.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.msgpack.MessagePack;
import org.msgpack.annotation.Message;

/**
 * Created by Stark on 2018/3/20.
 */
public class CustomerEncoder extends MessageToByteEncoder<Header> {
    @Override
    protected void encode(ChannelHandlerContext ctx, Header msg, ByteBuf out) throws Exception {
        MessagePack msgpack = new MessagePack();
        byte[] raw = msgpack.write(msg);
        out.writeBytes(raw);
    }
}
