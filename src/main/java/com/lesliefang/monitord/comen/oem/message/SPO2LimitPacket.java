package com.lesliefang.monitord.comen.oem.message;

/**
 * SPO2报警上下限包
 */
public class SPO2LimitPacket extends HighLowLimitPacket {
    public SPO2LimitPacket() {
        super(PacketType.PT_SPO2_LIMIT_DATA);
    }
}
