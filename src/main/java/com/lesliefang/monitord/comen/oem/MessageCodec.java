package com.lesliefang.monitord.comen.oem;

import com.lesliefang.monitord.comen.oem.message.Packet;
import com.lesliefang.monitord.comen.oem.message.PacketRegister;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@ChannelHandler.Sharable
public class MessageCodec extends MessageToMessageCodec<ByteBuf, Packet> {
    private static final Logger logger = LoggerFactory.getLogger(MessageCodec.class);

    @Override
    protected void encode(ChannelHandlerContext ctx, Packet msg, List<Object> out) throws Exception {
        out.add(msg.buildPacket());
    }

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
        logger.trace("<<== {}", ByteBufUtil.prettyHexDump(msg));
        msg.readByte(); // 前导符
        msg.readByte(); // 前导符
        byte packetType = msg.readByte(); // 包类型
        Class<? extends Packet> cls = PacketRegister.packetMap.get(packetType);
        if (cls == null) {
            logger.info("未注册这个类型的包 type: {}", packetType);
            return;
        }

        byte bedNum = msg.readByte(); // 床号
        int length = msg.readUnsignedShortLE(); // 包长

        Packet packet = cls.newInstance();
        packet.setType(packetType);
        packet.setBedNum(bedNum);

        if (length > 7) {
            // 长度等于7说明没有数据部分
            byte[] data = new byte[length - 7];
            msg.readBytes(data); // 数据
            // 解析数据
            ByteBuf dataBuf = Unpooled.wrappedBuffer(data);
            packet.parseData(dataBuf);
            dataBuf.release();
        }

        packet.setCheckSum(msg.readByte()); // 校验和
        msg.readByte(); // 尾导符
        msg.readByte(); // 尾导符

        logger.debug("<<=={} {}", ctx.channel().remoteAddress(), packet);

        out.add(packet);
    }
}
