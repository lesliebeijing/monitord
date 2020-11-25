package com.lesliefang.monitord.websocket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebSocketServer {
    private final static Logger logger = LoggerFactory.getLogger(WebSocketServer.class);
    private final int port = 5520;

    public static ChannelGroup channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    private final EventLoopGroup bossGroup = new NioEventLoopGroup();
    private final EventLoopGroup workerGroup = new NioEventLoopGroup();

    public void run() {
        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new HttpServerCodec());
                        ch.pipeline().addLast(new HttpObjectAggregator(65536));
                        ch.pipeline().addLast(new WebSocketServerProtocolHandler("/websocket"));
                        ch.pipeline().addLast(new WebSocketHandler());
                    }
                });
        try {
            ChannelFuture bindFuture = bootstrap.bind(port).sync();
            logger.info("websocket server is started on port {}", port);

            bindFuture.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                    logger.info("websocket server shutdownGracefully");
                }
            });
        } catch (Exception e) {
            logger.error("websocket server starts failed");
        }
    }

    public void stop() {
        logger.info("websocket server stopped");
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    class WebSocketHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {

        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            channelGroup.add(ctx.channel());
        }

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {

        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            channelGroup.remove(ctx.channel());
        }
    }

    public static void main(String[] args) {
        new WebSocketServer().run();
    }
}