package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

import java.util.Arrays;

/**
 * RESP
 */
public class RESPPacket extends Packet {
    private boolean isLeadOff;
    private short RR;//呼吸率值(每分钟次数)
    private byte[] waveData = new byte[125];//呼吸波形数据(采样率为125,周期1秒)

    public RESPPacket() {
        super(PacketType.PT_RESP_DATA);
    }

    @Override
    public void parseData(ByteBuf data) {
        isLeadOff = data.readByte() == 1;
        RR = data.readShortLE();
        data.readBytes(waveData);
    }

    public boolean isLeadOff() {
        return isLeadOff;
    }

    public short getRR() {
        return RR;
    }

    public byte[] getWaveData() {
        return waveData;
    }

    @Override
    public String toString() {
        return "RESPPacket{" +
                "type=" + type +
                ", bedNum=" + bedNum +
                ", isLeadOff=" + isLeadOff +
                ", RR=" + RR +
                ", waveData=" + Arrays.toString(waveData) +
                '}';
    }
}
