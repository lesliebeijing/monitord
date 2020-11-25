package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

/**
 * SPO2_MAS数据包
 */
public class SPO2MasPacket extends Packet {
    private boolean isLeadOff;
    private short SPO2;
    private short pi;//血氧信号质量,按原始模块值传上来，显示需/1000,并保留一位小数
    byte[] waveData = new byte[62];//波形数据(采样率62->80, 周期1秒)

    public SPO2MasPacket() {
        super(PacketType.PT_SPO2_MAS_DATA);
    }

    @Override
    public void parseData(ByteBuf data) {
        isLeadOff = data.readByte() == 1;
        SPO2 = data.readShortLE();
        pi = data.readShortLE();
        data.readBytes(waveData);
    }

    public boolean isLeadOff() {
        return isLeadOff;
    }

    public short getSPO2() {
        return SPO2;
    }

    public short getPi() {
        return pi;
    }

    public byte[] getWaveData() {
        return waveData;
    }
}
