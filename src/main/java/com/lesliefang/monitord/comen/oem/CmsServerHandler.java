package com.lesliefang.monitord.comen.oem;

import com.lesliefang.monitord.comen.oem.message.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;

public class CmsServerHandler extends SimpleChannelInboundHandler<Packet> {
    private static final Logger logger = LoggerFactory.getLogger(CmsServer.class);

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("channelActive {}", ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        switch (msg.getType()) {
            case PacketType.PT_LOGIN:
                LoginAckPacket loginAckPacket = new LoginAckPacket(0);
                ctx.channel().writeAndFlush(loginAckPacket);
                logger.debug("==>>{} {}", ctx.channel().remoteAddress(), loginAckPacket);
                break;
            case PacketType.PT_GETSYSTIME:
                handlerGetSystemTimePacket(ctx.channel());
                break;
            case PacketType.PT_HEARTBEAT:
                break;
        }
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

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        logger.info("channelInactive {}", ctx.channel().remoteAddress());
        super.channelInactive(ctx);
    }

    private void handlerGetSystemTimePacket(Channel channel) {
        Calendar calendar = Calendar.getInstance();
        channel.writeAndFlush(new SystemTimePacket((short) calendar.get(Calendar.YEAR),
                (byte) calendar.get(Calendar.MONTH), (byte) calendar.get(Calendar.DAY_OF_MONTH),
                (byte) calendar.get(Calendar.HOUR), (byte) calendar.get(Calendar.MINUTE),
                (byte) calendar.get(Calendar.SECOND)));
    }
}
