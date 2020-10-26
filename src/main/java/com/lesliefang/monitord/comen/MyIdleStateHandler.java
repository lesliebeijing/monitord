package com.lesliefang.monitord.comen;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.handler.timeout.IdleStateHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyIdleStateHandler extends IdleStateHandler {
    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    public MyIdleStateHandler(int readerIdleTimeSeconds) {
        super(readerIdleTimeSeconds, 0, 0);
    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent e = ((IdleStateEvent) evt);
            if (e.state() == IdleState.READER_IDLE) {
                logger.info("read idle {} close channel", ctx.channel().remoteAddress());
                ctx.close();
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }
}
