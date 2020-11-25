package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;

/**
 * 模块信息包
 */
public class ModuleInfoPacket extends Packet {
    /**
     * 用于模块标识模块是否在线,每1位对应一个模块(1为可用，0为不可用)，
     * 中央机可以根据对应的模块是否可用来决定是给该模块留显示空间。
     * 中央机也可发送这个包来要求仪器发送指定的模块数据，以便节省数据流量。
     */
    private long modeMap;
    /**
     * 是否是演示模式
     */
    private boolean isDemo;

    public ModuleInfoPacket() {
        super(PacketType.PT_MODULE_INFO);
    }

    @Override
    public void parseData(ByteBuf data) {
        modeMap = data.readUnsignedIntLE();
        isDemo = data.readUnsignedIntLE() == 1;
    }

    public long getModeMap() {
        return modeMap;
    }

    public boolean isDemo() {
        return isDemo;
    }

    @Override
    public String toString() {
        return "ModuleInfoPacket{" +
                "type=" + type +
                ", bedNum=" + bedNum +
                ", modeMap=" + modeMap +
                ", isDemo=" + isDemo +
                '}';
    }
}
