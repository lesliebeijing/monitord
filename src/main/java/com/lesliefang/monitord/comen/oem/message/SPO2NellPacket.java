package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

/**
 * SPO2_NELL 数据包
 */
public class SPO2NellPacket extends Packet {
    private boolean isLeadOff;
    private short SPO2;
    private byte rTime;//取值(以0开始以次表示0s,10s,25s,50s,100s)
    private byte radian;//弧度值(单位10度,取值范围[1-36],用于画圈)
    private byte[] waveData = new byte[76];    //弧度值(单位10度,取值范围[1-36],用于画圈)

    public SPO2NellPacket() {
        super(PacketType.PT_SPO2_NELL_DATA);
    }

    @Override
    public void parseData(ByteBuf data) {
        isLeadOff = data.readByte() == 1;
        SPO2 = data.readShort();
        rTime = data.readByte();
        radian = data.readByte();
        data.readBytes(waveData);
    }

    public boolean isLeadOff() {
        return isLeadOff;
    }

    public short getSPO2() {
        return SPO2;
    }

    public byte getrTime() {
        return rTime;
    }

    public byte getRadian() {
        return radian;
    }

    public byte[] getWaveData() {
        return waveData;
    }
}
