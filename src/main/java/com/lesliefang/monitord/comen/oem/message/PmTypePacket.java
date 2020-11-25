package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

/**
 * 监护仪类型包
 */
public class PmTypePacket extends Packet {
    private int pmType; // 监护仪类型(大类型：3:多参)

    public PmTypePacket() {
        super(PacketType.PT_PM_TYPE);
    }

    @Override
    public void parseData(ByteBuf data) {
        pmType = data.readUnsignedShortLE();
    }

    public int getPmType() {
        return pmType;
    }
}
