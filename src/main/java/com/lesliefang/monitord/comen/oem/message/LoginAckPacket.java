package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 上电注册应答包
 */
public class LoginAckPacket extends Packet {
    /**
     * 0:注册成功 1:注册失败
     */
    private int result;

    public LoginAckPacket(int result) {
        super(PacketType.PT_LOGIN_ACK);
        this.result = result;
    }

    @Override
    protected ByteBuf buildData() {
        ByteBuf buf = Unpooled.buffer();
        buf.writeShortLE(result);
        return buf;
    }
}
