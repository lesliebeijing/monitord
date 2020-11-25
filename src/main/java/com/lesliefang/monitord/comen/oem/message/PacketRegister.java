package com.lesliefang.monitord.comen.oem.message;

import java.util.HashMap;
import java.util.Map;

public class PacketRegister {
    public static Map<Byte, Class<? extends Packet>> packetMap = new HashMap<>();

    static {
        packetMap.put(PacketType.PT_LOGIN, LoginPacket.class);
        packetMap.put(PacketType.PT_GETSYSTIME, GetSystemTimePacket.class);
        packetMap.put(PacketType.PT_RELEASE_PATIENT, ReleasePatientPacket.class);
        packetMap.put(PacketType.PT_MODULE_INFO, ModuleInfoPacket.class);
        packetMap.put(PacketType.PT_PM_TYPE, PmTypePacket.class);
        packetMap.put(PacketType.PT_PATIENT_INFO, PatientInfoPacket.class);
        packetMap.put(PacketType.PT_ECG1L_DATA, ECG1LPacket.class);
        packetMap.put(PacketType.PT_ECG3L_DATA, ECG3LPacket.class);
        packetMap.put(PacketType.PT_ECG8L_DATA, ECG8LPacket.class);
        packetMap.put(PacketType.PT_PR_DATA, PRPacket.class);
        packetMap.put(PacketType.PT_NIBP_DATA, NIBPPacket.class);
        packetMap.put(PacketType.PT_TEMP_DATA, TempPacket.class);
        packetMap.put(PacketType.PT_RESP_DATA, RespPacket.class);

    }
}
