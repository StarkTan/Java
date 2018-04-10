package com.stark.netty.section_12.charpter.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * Created by Stark on 2018/3/20.
 */
public class CustomerDecoder extends MessageToMessageDecoder<ByteBuf> {

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf byteBuf, List<Object> list) throws Exception {
        final byte[] array;
        final int lenght = byteBuf.readableBytes();
        array = new byte[lenght];
        byteBuf.getBytes(byteBuf.readerIndex(), array, 0, lenght);
        MessagePack msfpack = new MessagePack();
        list.add(msfpack.read(array));
    }
}
