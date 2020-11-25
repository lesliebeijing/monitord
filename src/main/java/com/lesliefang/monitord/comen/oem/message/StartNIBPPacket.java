package com.lesliefang.monitord.comen.oem.message;

/**
 * NIBP 测量开始包
 */
public class StartNIBPPacket extends Packet {
    public StartNIBPPacket() {
        super(PacketType.PT_START_NIBP);
    }
}
