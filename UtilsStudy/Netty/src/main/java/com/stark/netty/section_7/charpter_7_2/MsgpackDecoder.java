package com.stark.netty.section_7.charpter_7_2;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.msgpack.MessagePack;

import java.util.List;

/**
 * Created by Stark on 2018/3/8.
 * messagepack decoder
 */
public class MsgpackDecoder extends MessageToMessageDecoder<ByteBuf> {
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        final byte[] array;
        final int lenght = byteBuf.readableBytes();
        array = new byte[lenght];
        byteBuf.getBytes(byteBuf.readerIndex(), array, 0, lenght);
        MessagePack msfpack = new MessagePack();
        list.add(msfpack.read(array));
    }
}
