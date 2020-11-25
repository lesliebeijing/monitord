package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

/**
 * Mac 地址包
 */
public class MacAddressPacket extends Packet {
    private short mac1;
    private short mac2;
    private short mac3;
    private short mac4;
    private short mac5;
    private short mac6;

    public MacAddressPacket() {
        super(PacketType.PT_MAC_ADDR);
    }

    @Override
    public void parseData(ByteBuf data) {
        mac1 = data.readUnsignedByte();
        mac2 = data.readUnsignedByte();
        mac3 = data.readUnsignedByte();
        mac4 = data.readUnsignedByte();
        mac5 = data.readUnsignedByte();
        mac6 = data.readUnsignedByte();
    }

    @Override
    public String toString() {
        return "MacAddressPacket{" +
                "type=" + type +
                ", bedNum=" + bedNum +
                ", mac1=" + mac1 +
                ", mac2=" + mac2 +
                ", mac3=" + mac3 +
                ", mac4=" + mac4 +
                ", mac5=" + mac5 +
                ", mac6=" + mac6 +
                '}';
    }
}
