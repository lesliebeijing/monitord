package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

/**
 * NIBP报警上下限包
 */
public class NIBPLimitPacket extends Packet {
    private boolean isAlarmOn;//报警开关(0:关, 1:开)
    private byte alarmLevel;
    private short sysHigh;//收缩压报警高限
    private short sysLow;//收缩压报警低限
    private short meanHigh;//平均压报警高限
    private short meanLow;//平均压报警低限
    private short diaHigh;//舒张压报警高限
    private short diaLow;//舒张压报警低限

    public NIBPLimitPacket() {
        super(PacketType.PT_NIBP_LIMIT_DATA);
    }

    @Override
    public void parseData(ByteBuf data) {
        isAlarmOn = data.readByte() == 1;
        alarmLevel = data.readByte();
        sysHigh = data.readShortLE();
        sysLow = data.readShortLE();
        meanHigh = data.readShortLE();
        meanLow = data.readShortLE();
        diaHigh = data.readShortLE();
        diaLow = data.readShortLE();
    }

    public boolean isAlarmOn() {
        return isAlarmOn;
    }

    public byte getAlarmLevel() {
        return alarmLevel;
    }

    public short getSysHigh() {
        return sysHigh;
    }

    public short getSysLow() {
        return sysLow;
    }

    public short getMeanHigh() {
        return meanHigh;
    }

    public short getMeanLow() {
        return meanLow;
    }

    public short getDiaHigh() {
        return diaHigh;
    }

    public short getDiaLow() {
        return diaLow;
    }

    @Override
    public String toString() {
        return "NIBPLimitPacket{" +
                "isAlarmOn=" + isAlarmOn +
                ", alarmLevel=" + alarmLevel +
                ", sysHigh=" + sysHigh +
                ", sysLow=" + sysLow +
                ", meanHigh=" + meanHigh +
                ", meanLow=" + meanLow +
                ", diaHigh=" + diaHigh +
                ", diaLow=" + diaLow +
                ", type=" + type +
                ", bedNum=" + bedNum +
                '}';
    }
}
