package com.lesliefang.monitord.comen.oem.message;

/**
 * 心率报警上下限包
 * 报警级别的定义 高级:1; 中级:2; 低级:3
 */
public class HRLimitPacket extends HighLowLimitPacket {

    public HRLimitPacket() {
        super(PacketType.PT_HR_LIMIT_DATA);
    }
}
