package com.lesliefang.monitord.comen.oem.message;

/**
 * 获取系统时间包
 */
public class GetSystemTimePacket extends Packet {
    public GetSystemTimePacket() {
        super(PacketType.PT_GETSYSTIME);
    }
}
