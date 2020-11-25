package com.lesliefang.monitord.comen.oem.message;

/**
 * PVC报警上下限包
 */
public class PVCLimitPacket extends HighLowLimitPacket {
    public PVCLimitPacket() {
        super(PacketType.PT_PVC_LIMIT_DATA);
    }
}
