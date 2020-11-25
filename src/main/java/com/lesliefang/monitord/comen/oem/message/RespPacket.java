package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

/**
 * RESP
 */
public class RespPacket extends Packet {
    private boolean isLeadOff;
    private short RR;//呼吸率值(每分钟次数)
    private byte[] waveData = new byte[125];//呼吸波形数据(采样率为125,周期1秒)

    public RespPacket() {
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
}
