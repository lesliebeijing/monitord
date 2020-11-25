package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

/**
 * NIBP
 */
public class NIBPPacket extends Packet {
    private short year;
    private byte month;
    private byte day;
    private byte hour;
    private byte minute;
    private byte second;
    private short sys; //收缩压
    private short mean; //平均压
    private short dia; //舒张压
    private boolean isFinish;//是否测量结束(1:是;0:测量过程值)
    private byte errCode; //测量结果(0:正常;1:袖带脱落;2:漏气)

    public NIBPPacket() {
        super(PacketType.PT_NIBP_DATA);
    }

    @Override
    public void parseData(ByteBuf data) {
        year = data.readShortLE();
        month = data.readByte();
        day = data.readByte();
        hour = data.readByte();
        minute = data.readByte();
        second = data.readByte();
        sys = data.readShortLE();
        mean = data.readShortLE();
        dia = data.readShortLE();
        isFinish = data.readByte() == 1;
        errCode = data.readByte();
    }

    public short getYear() {
        return year;
    }

    public byte getMonth() {
        return month;
    }

    public byte getDay() {
        return day;
    }

    public byte getHour() {
        return hour;
    }

    public byte getMinute() {
        return minute;
    }

    public byte getSecond() {
        return second;
    }

    public short getSys() {
        return sys;
    }

    public short getMean() {
        return mean;
    }

    public short getDia() {
        return dia;
    }

    public boolean isFinish() {
        return isFinish;
    }

    public byte getErrCode() {
        return errCode;
    }
}
