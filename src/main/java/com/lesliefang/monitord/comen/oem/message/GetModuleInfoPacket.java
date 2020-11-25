package com.lesliefang.monitord.comen.oem.message;

/**
 * 获取模块信息包
 */
public class GetModuleInfoPacket extends Packet {
    public GetModuleInfoPacket() {
        super(PacketType.PT_GET_MODULE_INFO);
    }
}
