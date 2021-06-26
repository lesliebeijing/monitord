package com.lesliefang.monitord.mdk;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteOrder;

public class MdkMonitorServer {
    private Logger logger = LoggerFactory.getLogger(MdkMonitorServer.class);

    private NioEventLoopGroup boss = new NioEventLoopGroup();
    private NioEventLoopGroup worker = new NioEventLoopGroup();

    private int bedNum;

    public MdkMonitorServer(int bedNum) {
        this.bedNum = bedNum;
    }

    public void run() {
        /*
         * 服务端端口：50000 + 床号，  比如13号床，则端口为50013
         */
        int port;
        if (bedNum <= 8) {
            port = 5000 + bedNum;
        } else {
            port = 50000 + bedNum;
        }

        ServerBootstrap serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(boss, worker)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_KEEPALIVE, true)
                .childHandler(new ChannelInitializer<NioSocketChannel>() {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new IdleStateHandler(30, 0, 0));
                        ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(ByteOrder.LITTLE_ENDIAN, 5000, 0, 2, 0, 0, true));
                        ch.pipeline().addLast(new MessageHandler());
                    }
                });
        try {
            ChannelFuture future = serverBootstrap.bind(port).sync();

            logger.debug("mdk monitor server start at " + port);

            future.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    boss.shutdownGracefully();
                    worker.shutdownGracefully();

                    logger.debug("mdk monitor server shutdown");
                }
            });
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new MdkMonitorServer(5).run();
    }
}

