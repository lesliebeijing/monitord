package com.lesliefang.monitord.comen.hl7;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Hl7Server {
    private static final Logger logger = LoggerFactory.getLogger(Hl7Server.class);
    private static final int PORT = 5001;
    private static final int READ_TIMEOUT = 30;
    private ByteBuf delimiter = Unpooled.copiedBuffer(new byte[]{0x1C, 0x0D});

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
                            ch.pipeline().addLast(new DelimiterBasedFrameDecoder(50000, delimiter));
                            ch.pipeline().addLast(new ServerMessageHandler());
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
        new Hl7Server().run();
    }
}
