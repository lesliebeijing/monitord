package com.lesliefang.monitord.comen.oem.message;

/**
 * NIBP 测量中止包
 */
public class StopNIBPPacket extends Packet {
    public StopNIBPPacket() {
        super(PacketType.PT_STOP_NIBP);
    }
}