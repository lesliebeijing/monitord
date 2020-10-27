package com.lesliefang.monitord.comen;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

public class NettyClient {
    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);
    private static final int READ_TIMEOUT = 30;
    static final int RECONNECT_DELAY = 5;
    private Bootstrap b = new Bootstrap();
    private EventLoopGroup group;
    private static NettyClient instance = null;
    private ByteBuf delimiter = Unpooled.copiedBuffer(new byte[]{0x1C, 0x0D});

    private NettyClient() {
        group = new NioEventLoopGroup();
        b.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.TCP_NODELAY, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new IdleStateHandler(READ_TIMEOUT, 0, 0));
                        ch.pipeline().addLast(new DelimiterBasedFrameDecoder(5000, delimiter));
                    }
                });
    }

    public static NettyClient getInstance() {
        if (instance == null) {
            synchronized (NettyClient.class) {
                if (instance == null) {
                    instance = new NettyClient();
                }
            }
        }
        return instance;
    }

    public void connect(String host, int port) {
        logger.info("try to connect {}:{}", host, port);
        ChannelFuture f = b.connect(host, port);
        f.addListener(new ChannelFutureListener() {
            @Override
            public void operationComplete(ChannelFuture future) throws Exception {
                if (future.isSuccess()) {
                    logger.info("connected to {}:{}", host, port);
                } else {
                    logger.info("failed connect to {}:{}, schedule reconnect after {}s", host, port, RECONNECT_DELAY);
                    future.channel().eventLoop().schedule(new Runnable() {
                        @Override
                        public void run() {
                            connect(host, port);
                        }
                    }, RECONNECT_DELAY, TimeUnit.SECONDS);
                }
            }
        });
    }

    public void stop() {
        if (group != null) {
            group.shutdownGracefully();
            group = null;
        }
    }

    public static void main(String[] args) {
        String[] ips = new String[]{
//                "172.17.41.152",
                "192.168.100.41",
        };

        NettyClient spaceComClient = NettyClient.getInstance();

        for (int i = 0; i < ips.length; i++) {
            spaceComClient.connect(ips[i], 4001);
        }

        System.out.println("main thread finish");
    }
}
