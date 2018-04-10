package com.stark.netty.section_12.charpter.server;

import com.stark.netty.section_12.charpter.Header;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.msgpack.annotation.Message;

/**
 * Created by Stark on 2018/3/20.
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Header header = (Header) msg;
        System.out.println(header);
        ctx.writeAndFlush(msg);
    }
}
