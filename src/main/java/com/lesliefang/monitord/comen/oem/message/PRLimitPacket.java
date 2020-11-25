package com.lesliefang.monitord.comen.oem.message;

/**
 * PR报警上下限包
 */
public class PRLimitPacket extends HighLowLimitPacket {
    public PRLimitPacket() {
        super(PacketType.PT_PR_LIMIT_DATA);
    }
}
