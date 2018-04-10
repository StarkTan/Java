package com.stark.netty.section_12.charpter.client;

import com.stark.netty.section_12.charpter.Header;
import com.stark.netty.section_12.charpter.NettyMessage;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Stark on 2018/3/20.
 */
public class ClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        Header header = new Header();
        //header.setAttachment(attchment);
        header.setCrcCode((byte) 1);
        header.setLength(2);
        header.setSessionID(1231241L);
        header.setType((byte) 4);
        NettyMessage message = new NettyMessage();
        message.setBody("this is message body");
        message.setHeader(header);
        ctx.writeAndFlush(header);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf byteBuf = (ByteBuf) msg;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        System.out.println("client receive msg : " + new String(bytes));
        ctx.close();
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        cause.printStackTrace();
        ctx.close();
    }
}
