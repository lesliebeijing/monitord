package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

import java.util.Arrays;

/**
 * SPO2_DIG数据包
 */
public class SPO2DigPacket extends Packet {
    private boolean isLeadOff;
    private short SPO2;
    private byte[] waveData = new byte[60];//波形数据(采样率60->80, 周期1秒)

    public SPO2DigPacket() {
        super(PacketType.PT_SPO2_DIG_DATA);
    }

    @Override
    public void parseData(ByteBuf data) {
        isLeadOff = data.readByte() == 1;
        SPO2 = data.readShortLE();
        data.readBytes(waveData);
    }

    public boolean isLeadOff() {
        return isLeadOff;
    }

    public short getSPO2() {
        return SPO2;
    }

    public byte[] getWaveData() {
        return waveData;
    }

    @Override
    public String toString() {
        return "SPO2DigPacket{" +
                "type=" + type +
                ", bedNum=" + bedNum +
                ", isLeadOff=" + isLeadOff +
                ", SPO2=" + SPO2 +
                ", waveData=" + Arrays.toString(waveData) +
                '}';
    }
}
