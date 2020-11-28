package com.lesliefang.monitord.comen.oem.message;

import com.alibaba.fastjson.annotation.JSONField;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

import java.io.UnsupportedEncodingException;

public class Packet {
    public static final byte PREFIX = (byte) 0xBB; // 前导符
    public static final byte SUFFIX = (byte) 0xEE; // 尾导符

    public Packet() {

    }

    public Packet(byte packetType) {
        type = packetType;
    }

    /**
     * 包类型
     */
    byte type;

    /**
     * 网络床号
     */
    byte bedNum;

    /**
     * 数据块,默认返回NULL，子类可复写
     */
    protected ByteBuf buildData() {
        return null;
    }

    /**
     * 解析返回数据，子类可复写
     *
     * @param data 返回数据块
     */
    public void parseData(ByteBuf data) {

    }

    /**
     * 校验和
     */
    @JSONField(serialize = false)
    byte checkSum;

    public ByteBuf buildPacket() {
        int checkSum = 0;
        ByteBuf buf = Unpooled.buffer();
        buf.writeByte(PREFIX);
        buf.writeByte(PREFIX);
        buf.writeByte(type);
        checkSum += type;
        buf.writeByte(bedNum);
        checkSum += bedNum;

        byte[] data = null;
        ByteBuf dataBuf = buildData();
        if (dataBuf != null) {
            data = new byte[dataBuf.readableBytes()];
            dataBuf.readBytes(data);
            dataBuf.release();
        }
        int len = 1 + 1 + 2 + 1 + 2; // 类型1字节 + 床号1字节 + 包长2字节 + 校验位1字节 + 尾导符2字节
        if (data != null) {
            len += data.length;
        }

        buf.writeShortLE(len); // 写入包长
        checkSum += len & 0xff;
        checkSum += (len >> 8) & 0xff;

        if (data != null) {
            for (int i = 0; i < data.length; i++) {
                checkSum += data[i];
                buf.writeByte(data[i]);
            }
        }

        buf.writeByte(checkSum & 0xff + (checkSum >> 8) & 0xff);

        buf.writeByte(SUFFIX);
        buf.writeByte(SUFFIX);

        return buf;
    }

    protected byte[] handlerStringField(String str, int fieldLength) {
        byte[] dest = new byte[fieldLength];
        if (str != null) {
            try {
                byte[] src = str.getBytes("GBK");
                int len = src.length > fieldLength ? fieldLength : src.length;
                System.arraycopy(src, 0, dest, 0, len);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return dest;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public byte getBedNum() {
        return bedNum;
    }

    public void setBedNum(byte bedNum) {
        this.bedNum = bedNum;
    }

    public byte getCheckSum() {
        return checkSum;
    }

    public void setCheckSum(byte checkSum) {
        this.checkSum = checkSum;
    }
}
