package com.lesliefang.monitord.comen.oem.message;

/**
 * 注册完成包
 */
public class InitFinishPacket extends Packet {
    public InitFinishPacket() {
        super(PacketType.PT_INIT_FINISH);
    }
}
