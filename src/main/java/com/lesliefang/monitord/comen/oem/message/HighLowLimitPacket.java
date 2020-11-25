package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

public class HighLowLimitPacket extends Packet {
    protected boolean isAlarmOn;//报警开关(0:关, 1:开)
    protected byte alarmLevel;//报警级别
    protected short high;//高限
    protected short low;//低限

    public HighLowLimitPacket(byte type) {
        super(type);
    }

    @Override
    public void parseData(ByteBuf data) {
        isAlarmOn = data.readByte() == 1;
        alarmLevel = data.readByte();
        high = data.readShortLE();
        low = data.readShortLE();
    }

    public boolean isAlarmOn() {
        return isAlarmOn;
    }

    public byte getAlarmLevel() {
        return alarmLevel;
    }

    public short getHigh() {
        return high;
    }

    public short getLow() {
        return low;
    }

    @Override
    public String toString() {
        return "HighLowLimitPacket{" +
                "type=" + type +
                ", bedNum=" + bedNum +
                ", isAlarmOn=" + isAlarmOn +
                ", alarmLevel=" + alarmLevel +
                ", high=" + high +
                ", low=" + low +
                '}';
    }
}
