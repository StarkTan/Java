package com.stark.netty.section_13;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Stark on 2018/3/20.
 * 用于研究Netty 服务器创建流程
 */
public class NettySever {


    private void run(int port) throws Exception {
        //其实这两个线程组可以共用
        EventLoopGroup bossGroup = new NioEventLoopGroup(); //用于处理连接的线程组
        EventLoopGroup workerGroup = new NioEventLoopGroup();//用于处理IO事件的线程组

        try {
            /*
             * ServerBootStrap 是Netty服务启动的辅助类，包含大量的设置参数
             * 使用无参的构造函数，但是使用builder模式进行参数配置
             */
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup) //设置绑定线程池
                    .channel(NioServerSocketChannel.class)//设置服务端Channel类型
                    //链路建立的初始化ChannelPipeline
                    .childHandler(new ChannelInitializer<NioServerSocketChannel>() {
                        @Override
                        protected void initChannel(NioServerSocketChannel ch) throws Exception {
                        }
                    });
            //绑定监听端口同步执行
            ChannelFuture future = bootstrap.bind(port).sync();
            //等待同步完成关闭
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        new NettySever().run(8888);
    }

}
