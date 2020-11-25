package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

/**
 * 上电注册包
 */
public class LoginPacket extends Packet {
    private int protocolType; // 协议类型
    private int protocolVersion; // 协议版本号
    private int pmType; // 仪器类型

    public LoginPacket() {
        super(PacketType.PT_LOGIN);
    }

    @Override
    public void parseData(ByteBuf data) {
        protocolType = data.readUnsignedShortLE();
        protocolVersion = data.readUnsignedShortLE();
        pmType = data.readUnsignedShortLE();
    }

    public int getProtocolType() {
        return protocolType;
    }

    public void setProtocolType(int protocolType) {
        this.protocolType = protocolType;
    }

    public int getProtocolVersion() {
        return protocolVersion;
    }

    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    public int getPmType() {
        return pmType;
    }

    public void setPmType(int pmType) {
        this.pmType = pmType;
    }
}
