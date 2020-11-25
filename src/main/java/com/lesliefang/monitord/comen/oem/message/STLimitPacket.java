package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

/**
 * ST报警上下限包(包类型0x25)
 * ST名称:0全部ST同步生效，1开始依次为I、II、III、AVR、AVL、AVF、V1、V2、…V6。
 */
public class STLimitPacket extends HighLowLimitPacket {
    private byte stName;

    public STLimitPacket() {
        super(PacketType.PT_ST_LIMIT_DATA);
    }

    @Override
    public void parseData(ByteBuf data) {
        stName = data.readByte();
        super.parseData(data);
    }

    public byte getStName() {
        return stName;
    }

    @Override
    public String toString() {
        return super.toString() + "STLimitPacket{" +
                "stName=" + stName +
                '}';
    }
}
