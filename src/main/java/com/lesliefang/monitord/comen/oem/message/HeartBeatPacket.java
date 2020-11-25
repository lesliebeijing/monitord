package com.lesliefang.monitord.comen.oem.message;

/**
 * 心跳包
 */
public class HeartBeatPacket extends Packet {
    public HeartBeatPacket() {
        super(PacketType.PT_HEARTBEAT);
    }

    @Override
    public String toString() {
        return "HeartBeatPacket{" +
                "type=" + type +
                ", bedNum=" + bedNum +
                '}';
    }
}
