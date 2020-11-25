package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

/**
 * 互控数据应答包
 */
public class CtrlAckPacket extends Packet {
    private byte packetId; //需要应答的那个包类型编号
    private byte result;////结果：0表示设置失败，1表示设置成功
    private int reserved1;
    private int reserved2;

    public CtrlAckPacket() {
        super(PacketType.PT_DATA_CTRL_ACK);
    }

    @Override
    public void parseData(ByteBuf data) {
        packetId = data.readByte();
        result = data.readByte();
    }

    public byte getPacketId() {
        return packetId;
    }

    public byte getResult() {
        return result;
    }

    @Override
    public String toString() {
        return "CtrlAckPacket{" +
                "type=" + type +
                ", bedNum=" + bedNum +
                ", packetId=" + packetId +
                ", result=" + result +
                '}';
    }
}
