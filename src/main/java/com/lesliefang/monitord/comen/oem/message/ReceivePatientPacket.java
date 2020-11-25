package com.lesliefang.monitord.comen.oem.message;

/**
 * 接收病人
 */
public class ReceivePatientPacket extends Packet {
    public ReceivePatientPacket() {
        super(PacketType.PT_RECIEVE_PATIENT);
    }

    @Override
    public String toString() {
        return "ReceivePatientPacket{" +
                "type=" + type +
                ", bedNum=" + bedNum +
                '}';
    }
}
