package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

/**
 * TEMP 体温
 */
public class TEMPPacket extends Packet {
    private boolean isLeadOff; //导联是否脱落(0正常，1脱落)
    private short T1; ////温度1(扩大10倍℃)
    private short T2; //温度2(扩大10倍℃)

    public TEMPPacket() {
        super(PacketType.PT_TEMP_DATA);
    }

    @Override
    public void parseData(ByteBuf data) {
        isLeadOff = data.readByte() == 1;
        T1 = data.readShortLE();
        T2 = data.readShortLE();
    }

    public boolean isLeadOff() {
        return isLeadOff;
    }

    public short getT1() {
        return T1;
    }

    public short getT2() {
        return T2;
    }

    @Override
    public String toString() {
        return "TEMPPacket{" +
                "type=" + type +
                ", bedNum=" + bedNum +
                ", isLeadOff=" + isLeadOff +
                ", T1=" + T1 +
                ", T2=" + T2 +
                '}';
    }
}
