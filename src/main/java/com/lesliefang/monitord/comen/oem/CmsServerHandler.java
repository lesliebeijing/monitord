package com.lesliefang.monitord.comen.oem;

import com.lesliefang.monitord.comen.oem.message.*;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.concurrent.ConcurrentHashMap;

public class CmsServerHandler extends SimpleChannelInboundHandler<Packet> {
    private static final Logger logger = LoggerFactory.getLogger(CmsServer.class);
    private static ConcurrentHashMap<String, Channel> allChannels = new ConcurrentHashMap<>();
    private AttributeKey<String> snKey = AttributeKey.valueOf("sn");

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        logger.info("channelActive {}", ctx.channel().remoteAddress());
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Packet msg) throws Exception {
        switch (msg.getType()) {
            case PacketType.PT_LOGIN:
                ctx.channel().writeAndFlush(new LoginAckPacket(0));
                break;
            case PacketType.PT_GETSYSTIME:
                handleGetSystemTimePacket(ctx.channel());
                break;
            case PacketType.PT_NET_CONTROL:
                ctx.channel().writeAndFlush(new NetControlAckPacket());
                break;
            case PacketType.PT_DEVICE_SN:
                handleDeviceSnPacket(msg, ctx.channel());
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
        String sn = ctx.channel().attr(snKey).get();
        if (sn != null) {
            allChannels.remove(sn);
            ctx.channel().attr(snKey).set(null);
        }
        super.channelInactive(ctx);
    }

    private void handleGetSystemTimePacket(Channel channel) {
        Calendar calendar = Calendar.getInstance();
        channel.writeAndFlush(new SystemTimePacket((short) calendar.get(Calendar.YEAR),
                (byte) calendar.get(Calendar.MONTH), (byte) calendar.get(Calendar.DAY_OF_MONTH),
                (byte) calendar.get(Calendar.HOUR), (byte) calendar.get(Calendar.MINUTE),
                (byte) calendar.get(Calendar.SECOND)));
    }

    private void handleDeviceSnPacket(Packet packet, Channel channel) {
        DeviceSnPacket deviceSnPacket = ((DeviceSnPacket) packet);
        if (channel.attr(snKey) == null) {
            channel.attr(snKey).set(deviceSnPacket.getSn());
        }
        allChannels.put(deviceSnPacket.getSn(), channel);
    }

    /**
     * 转发外部上位机请求到设备
     *
     * @param deviceSn 设备sn
     */
    public static void forwardToDevice(String deviceSn, Packet packet) {
        Channel channel = allChannels.get(deviceSn);
        if (channel == null) {
            logger.error("forwardToDevice->{} no channel", deviceSn);
            return;
        }
        channel.writeAndFlush(packet);
    }
}
