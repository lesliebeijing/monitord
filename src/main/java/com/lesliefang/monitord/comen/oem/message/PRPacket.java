package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

/**
 * PR 脉率
 */
public class PRPacket extends Packet {
    private short PR;
    private byte src;//来源(0: SPO2;1:NIBP; 2:自动)

    public PRPacket() {
        super(PacketType.PT_PR_DATA);
    }

    @Override
    public void parseData(ByteBuf data) {
        PR = data.readShortLE();
        src = data.readByte();
    }

    public short getPR() {
        return PR;
    }

    public byte getSrc() {
        return src;
    }
}
