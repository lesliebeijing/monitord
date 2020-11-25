package com.lesliefang.monitord.comen.oem;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.ByteOrder;

public class CmsServer {
    private static final Logger logger = LoggerFactory.getLogger(CmsServer.class);
    private static final int PORT = 5001;
    private static final int READ_TIMEOUT = 30;

    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .childOption(ChannelOption.TCP_NODELAY, true)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new IdleStateHandler(READ_TIMEOUT, 0, 0));
                            ch.pipeline().addLast(new LengthFieldBasedFrameDecoder(ByteOrder.LITTLE_ENDIAN, 5000, 4, 2, -4, 0, true));
                            ch.pipeline().addLast(new MessageCodec());
                            ch.pipeline().addLast(new CmsServerHandler());
                        }
                    });
            ChannelFuture f = b.bind(PORT).sync();

            logger.info("server start listening at {}", PORT);

            f.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();

                    logger.info("server shutdown");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new CmsServer().run();
    }
}
