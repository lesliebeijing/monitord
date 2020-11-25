package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 系统时间包
 */
public class SystemTimePacket extends Packet {
    private short year;
    private byte month;
    private byte day;
    private byte hour;
    private byte minute;
    private byte second;
    private byte reserved; // 预留

    public SystemTimePacket(short year, byte month, byte day,
                            byte hour, byte minute, byte second) {
        super(PacketType.PT_SYSTIME);
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    @Override
    protected ByteBuf buildData() {
        ByteBuf buf = Unpooled.buffer();
        buf.writeShortLE(year);
        buf.writeByte(month);
        buf.writeByte(day);
        buf.writeByte(hour);
        buf.writeByte(minute);
        buf.writeByte(second);
        buf.writeByte(reserved);
        return buf;
    }
}
