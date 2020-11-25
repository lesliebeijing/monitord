package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

/**
 * 8个导联数据的ECG数据包(I、II、V1-V6)
 * ST扩大100倍，如125为1.25mv，-125代表-1.25mv。0xFFFF无效。
 * 心律失常类型：
 * 0：无心律失常
 * 1：停搏
 * 2：室颤或室速
 * 3：R on T
 * 4：三个或四个连发室早
 * 5：二连发室早
 * 6：单个室早
 * 7：室早二联律
 * 8：室早三联律
 * 9：室上性心动过速
 * 10：心动过缓
 * 11：起搏器未俘获
 * 12：起搏器未起搏
 * 13：漏搏
 * 14：心率不齐
 * 15：正在学习
 * 16：干扰太大
 * 17：信号太弱
 * 18：心动过速
 */
public class ECG8LPacket extends Packet {
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
    private short STV1;
    private short STV2;
    private short STV3;
    private short STV4;
    private short STV5;
    private short STV6;
    private byte[] waveIData = new byte[250]; //波形I数据 INT8U无符号
    private byte[] waveIIData = new byte[250]; //波形II数据 INT8U无符号
    private byte[] waveV1Data = new byte[250]; //波形V1数据 INT8U无符号
    private byte[] waveV2Data = new byte[250]; //波形V2数据 INT8U无符号
    private byte[] waveV3Data = new byte[250]; //波形V3数据 INT8U无符号
    private byte[] waveV4Data = new byte[250]; //波形V4数据 INT8U无符号
    private byte[] waveV5Data = new byte[250]; //波形V5数据 INT8U无符号
    private byte[] waveV6Data = new byte[250]; //波形V6数据 INT8U无符号

    public ECG8LPacket() {
        super(PacketType.PT_ECG8L_DATA);
    }

    @Override
    public void parseData(ByteBuf data) {
        isLeadOff = data.readUnsignedByte() == 1;
        HR = data.readShortLE();
        PVC = data.readShortLE();
        ARRType = data.readUnsignedByte();
        STI = data.readShortLE();
        STII = data.readShortLE();
        STIII = data.readShortLE();
        STAVR = data.readShortLE();
        STAVL = data.readShortLE();
        STAVF = data.readShortLE();
        STV1 = data.readShortLE();
        STV2 = data.readShortLE();
        STV3 = data.readShortLE();
        STV4 = data.readShortLE();
        STV5 = data.readShortLE();
        STV6 = data.readShortLE();
        data.readBytes(waveIData);
        data.readBytes(waveIIData);
        data.readBytes(waveV1Data);
        data.readBytes(waveV2Data);
        data.readBytes(waveV3Data);
        data.readBytes(waveV4Data);
        data.readBytes(waveV5Data);
        data.readBytes(waveV6Data);
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

    public short getSTV1() {
        return STV1;
    }

    public short getSTV2() {
        return STV2;
    }

    public short getSTV3() {
        return STV3;
    }

    public short getSTV4() {
        return STV4;
    }

    public short getSTV5() {
        return STV5;
    }

    public short getSTV6() {
        return STV6;
    }

    public byte[] getWaveIData() {
        return waveIData;
    }

    public byte[] getWaveIIData() {
        return waveIIData;
    }

    public byte[] getWaveV1Data() {
        return waveV1Data;
    }

    public byte[] getWaveV2Data() {
        return waveV2Data;
    }

    public byte[] getWaveV3Data() {
        return waveV3Data;
    }

    public byte[] getWaveV4Data() {
        return waveV4Data;
    }

    public byte[] getWaveV5Data() {
        return waveV5Data;
    }

    public byte[] getWaveV6Data() {
        return waveV6Data;
    }
}
