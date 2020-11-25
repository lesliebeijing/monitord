package com.lesliefang.monitord.comen.oem.message;

/**
 * RESP报警上下限包
 */
public class RESPLimitPacket extends HighLowLimitPacket {
    public RESPLimitPacket() {
        super(PacketType.PT_RESP_LIMIT_DATA);
    }
}
