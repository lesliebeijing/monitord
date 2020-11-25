package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

import java.util.Arrays;

/**
 * 1个导联数据的ECG数据包(I或II或III)
 */
public class ECG1LPacket extends Packet {
    private byte leadName; //导联名称(从1开始，依次为I、II、III)
    private boolean isLeadOff; //导联是否脱落(0正常，1脱落)
    private short HR; //心率值(每分钟次数)
    private short PVC; //pvc 值每分钟室早的个数(0到10)
    private short ARRType; //心律失常类型
    private short ST; //对应导联的ST
    private byte[] waveData = new byte[250]; //对应导联的波形数据 INT8U无符号

    public ECG1LPacket() {
        super(PacketType.PT_ECG1L_DATA);
    }

    @Override
    public void parseData(ByteBuf data) {
        leadName = data.readByte();
        isLeadOff = data.readByte() == 1;
        HR = data.readShortLE();
        PVC = data.readShortLE();
        ARRType = data.readUnsignedByte();
        ST = data.readShortLE();
        data.readBytes(waveData);
    }

    public byte getLeadName() {
        return leadName;
    }

    public boolean isLeadOff() {
        return isLeadOff;
    }

    public short getHR() {
        return HR;
    }

    public short getPVC() {
        return PVC;
    }

    public short getARRType() {
        return ARRType;
    }

    public short getST() {
        return ST;
    }

    public byte[] getWaveData() {
        return waveData;
    }

    @Override
    public String toString() {
        return "ECG1LPacket{" +
                "type=" + type +
                ", bedNum=" + bedNum +
                ", leadName=" + leadName +
                ", isLeadOff=" + isLeadOff +
                ", HR=" + HR +
                ", PVC=" + PVC +
                ", ARRType=" + ARRType +
                ", ST=" + ST +
                ", waveData=" + Arrays.toString(waveData) +
                '}';
    }
}
