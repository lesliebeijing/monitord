package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

/**
 * TEMP报警上下限包
 */
public class TEMPLimitPacket extends Packet {
    private boolean isAlarmOn;
    private byte alarmLevel;
    private short T1High;//T1高限
    private short T1Low;//T1低限
    private short T2High;//T2高限
    private short T2Low;//T2低限

    public TEMPLimitPacket() {
        super(PacketType.PT_TEMP_LIMIT_DATA);
    }

    @Override
    public void parseData(ByteBuf data) {
        isAlarmOn = data.readByte() == 1;
        alarmLevel = data.readByte();
        T1High = data.readShortLE();
        T1Low = data.readShortLE();
        T2High = data.readShortLE();
        T2Low = data.readShortLE();
    }

    public boolean isAlarmOn() {
        return isAlarmOn;
    }

    public byte getAlarmLevel() {
        return alarmLevel;
    }

    public short getT1High() {
        return T1High;
    }

    public short getT1Low() {
        return T1Low;
    }

    public short getT2High() {
        return T2High;
    }

    public short getT2Low() {
        return T2Low;
    }

    @Override
    public String toString() {
        return "TEMPLimitPacket{" +
                "isAlarmOn=" + isAlarmOn +
                ", alarmLevel=" + alarmLevel +
                ", T1High=" + T1High +
                ", T1Low=" + T1Low +
                ", T2High=" + T2High +
                ", T2Low=" + T2Low +
                ", type=" + type +
                ", bedNum=" + bedNum +
                '}';
    }
}
