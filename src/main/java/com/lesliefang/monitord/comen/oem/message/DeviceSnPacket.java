package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;

/**
 * 设备序列号包
 */
public class DeviceSnPacket extends Packet {
    private String sn;

    public DeviceSnPacket() {
        super(PacketType.PT_DEVICE_SN);
    }

    @Override
    public void parseData(ByteBuf data) {
        sn = data.readCharSequence(24, CharsetUtil.UTF_8).toString().trim();
    }

    public String getSn() {
        return sn;
    }

    @Override
    public String toString() {
        return "DeviceSnPacket{" +
                "type=" + type +
                ", bedNum=" + bedNum +
                ", sn='" + sn + '\'' +
                '}';
    }
}
