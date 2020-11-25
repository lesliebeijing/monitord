package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

/**
 * 3个导联数据的ECG数据包(I、II、V)
 */
public class ECG3LPacket extends Packet {
    private boolean isLeadOff; //导联是否脱落(0正常，1脱落)
    private short HR; //心率值(每分钟次数)
    private short PVC; //pvc 值每分钟室早的个数(0到10)
    private short ARRType; //心律失常类型
    private short STI; //STI
    private short STII; //STII
    private short STIII; //STIII
    private short STAVR; //STAVR
    private short STAVL; //STAVL
    private short STAVF; //STAVL
    private short STV;
    private byte[] waveIData = new byte[250]; //波形I数据 INT8U无符号
    private byte[] waveIIData = new byte[250]; //波形II数据 INT8U无符号
    private byte[] waveVData = new byte[250]; //波形V1数据 INT8U无符号

    public ECG3LPacket() {
        super(PacketType.PT_ECG3L_DATA);
    }

    @Override
    public void parseData(ByteBuf data) {
        isLeadOff = data.readByte() == 1;
        HR = data.readShortLE();
        PVC = data.readShortLE();
        ARRType = data.readUnsignedByte();
        STI = data.readShortLE();
        STII = data.readShortLE();
        STIII = data.readShortLE();
        STAVR = data.readShortLE();
        STAVL = data.readShortLE();
        STAVF = data.readShortLE();
        STV = data.readShortLE();
        data.readBytes(waveIData);
        data.readBytes(waveIIData);
        data.readBytes(waveVData);
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

    public short getSTI() {
        return STI;
    }

    public short getSTII() {
        return STII;
    }

    public short getSTIII() {
        return STIII;
    }

    public short getSTAVR() {
        return STAVR;
    }

    public short getSTAVL() {
        return STAVL;
    }

    public short getSTAVF() {
        return STAVF;
    }

    public short getSTV() {
        return STV;
    }

    public byte[] getWaveIData() {
        return waveIData;
    }

    public byte[] getWaveIIData() {
        return waveIIData;
    }

    public byte[] getWaveVData() {
        return waveVData;
    }
}
