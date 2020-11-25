package com.lesliefang.monitord.comen.oem.message;

/**
 * 获取病人信息包
 */
public class GetPatientInfoPacket extends Packet {
    public GetPatientInfoPacket() {
        super(PacketType.PT_GET_PATIENT_INFO);
    }
}
