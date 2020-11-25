package com.lesliefang.monitord.comen.oem.message;

/**
 * 网络控制应答包
 */
public class NetControlAckPacket extends Packet {
    public NetControlAckPacket() {
        super(PacketType.PT_NET_CONTROL_ACK);
    }
}
