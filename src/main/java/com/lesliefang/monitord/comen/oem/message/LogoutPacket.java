package com.lesliefang.monitord.comen.oem.message;

/**
 * 注销包
 */
public class LogoutPacket extends Packet {
    public LogoutPacket() {
        super(PacketType.PT_LOGOUT);
    }
}
