package com.lesliefang.monitord.comen.oem.message;

/**
 * 解除病人
 */
public class ReleasePatientPacket extends Packet {
    public ReleasePatientPacket() {
        super(PacketType.PT_RELEASE_PATIENT);
    }
}
