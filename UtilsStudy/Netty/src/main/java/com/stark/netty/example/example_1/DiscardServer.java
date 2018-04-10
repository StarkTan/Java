package com.stark.netty.example.example_1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by Stark on 2017/12/11.
 * build discard server and run it
 */
public class DiscardServer {

    private int port;

    public DiscardServer(int port) {
        this.port = port;
    }

    public void run() throws Exception {
        /**
         *NioEventLoopGroup is a multithreaded event loop that handles I/O operation.
         * Netty provides various EventLoopGroup implementations for different kind of transports
         */
        //The first one, often called 'boss', accepts an incoming connection
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //The second one, often called 'worker',
        // handles the traffic of the accepted connection
        // once the boss accepts the connection and registers the accepted connection to the worker
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {

            //ServerBootstrap is a helper class that sets up a server.
            //You can set up the server using a Channel directly.
            //However, please note that this is a tedious process, and you do not need to do that in most cases
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workGroup)
                    // we specify to use the NioServerSocketChannel class
                    // which is used to instantiate a new Channel to accept incoming connections
                    .channel(NioServerSocketChannel.class)
                    //The handler specified here will always be evaluated by a newly accepted Channel.
                    // The ChannelInitializer is a special handler that is purposed to help a user configure a new Channel.
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new DiscardServerHandler());
                        }
                    })
                    // option() is for the NioServerSocketChannel that accepts incoming connections.
                    // childOption() is for the Channels accepted by the parent ServerChannel,
                    // which is NioServerSocketChannel in this case.
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);
            //Bind and start to accept the coming connectionss
            //You can now call the bind() method as many times as you want (with different bind addresses.)
            ChannelFuture future = bootstrap.bind(port).sync();
            //Wait until the server socket is close
            //shutdown your server
            future.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8080;
        }
        new DiscardServer(port).run();
    }
}
