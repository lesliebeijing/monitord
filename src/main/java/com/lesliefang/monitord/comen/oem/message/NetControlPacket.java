package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

import java.util.Arrays;

/**
 * 网络控制包
 */
public class NetControlPacket extends Packet {
    /**
     * 网络控制包子类型：
     * 1: 设置波形速度
     * 2：更改心电导联名称
     * 3: 设置心电滤波方式和增益
     * 4：NIBP测量模式
     * 5：床旁机状态位
     * 6：报警暂停时长
     */
    private short subType;
    private byte[] data = new byte[2];

    public NetControlPacket() {
        super(PacketType.PT_NET_CONTROL);
    }

    @Override
    public void parseData(ByteBuf data) {
        subType = data.readShortLE();
        data.readBytes(this.data);
    }

    public short getSubType() {
        return subType;
    }

    public byte[] getData() {
        return data;
    }

    @Override
    public String toString() {
        return "NetControlPacket{" +
                "type=" + type +
                ", bedNum=" + bedNum +
                ", subType=" + subType +
                ", data=" + Arrays.toString(data) +
                '}';
    }
}
