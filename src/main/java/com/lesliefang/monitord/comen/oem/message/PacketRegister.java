package com.lesliefang.monitord.comen.oem.message;

import java.util.HashMap;
import java.util.Map;

public class PacketRegister {
    public static Map<Byte, Class<? extends Packet>> packetMap = new HashMap<>();

    static {
        packetMap.put(PacketType.PT_LOGIN, LoginPacket.class);
        packetMap.put(PacketType.PT_LOGOUT, LogoutPacket.class);
        packetMap.put(PacketType.PT_INIT_FINISH, InitFinishPacket.class);
        packetMap.put(PacketType.PT_GETSYSTIME, GetSystemTimePacket.class);
        packetMap.put(PacketType.PT_RELEASE_PATIENT, ReleasePatientPacket.class);
        packetMap.put(PacketType.PT_MODULE_INFO, ModuleInfoPacket.class);
        packetMap.put(PacketType.PT_PM_TYPE, PmTypePacket.class);
        packetMap.put(PacketType.PT_PATIENT_INFO, PatientInfoPacket.class);
        packetMap.put(PacketType.PT_HEARTBEAT, HeartBeatPacket.class);
        packetMap.put(PacketType.PT_ECG1L_DATA, ECG1LPacket.class);
        packetMap.put(PacketType.PT_ECG3L_DATA, ECG3LPacket.class);
        packetMap.put(PacketType.PT_ECG8L_DATA, ECG8LPacket.class);
        packetMap.put(PacketType.PT_PR_DATA, PRPacket.class);
        packetMap.put(PacketType.PT_NIBP_DATA, NIBPPacket.class);
        packetMap.put(PacketType.PT_TEMP_DATA, TEMPPacket.class);
        packetMap.put(PacketType.PT_RESP_DATA, RESPPacket.class);
        packetMap.put(PacketType.PT_SPO2_NELL_DATA, SPO2NellPacket.class);
        packetMap.put(PacketType.PT_SPO2_MAS_DATA, SPO2MasPacket.class);
        packetMap.put(PacketType.PT_SPO2_DIG_DATA, SPO2DigPacket.class);
        packetMap.put(PacketType.PT_DATA_CTRL_ACK, CtrlAckPacket.class);
        packetMap.put(PacketType.PT_MAC_ADDR, MacAddressPacket.class);
        packetMap.put(PacketType.PT_DEVICE_SN, DeviceSnPacket.class);
        packetMap.put(PacketType.PT_NET_CONTROL, NetControlPacket.class);
        packetMap.put(PacketType.PT_NET_CONTROL_ACK, NetControlAckPacket.class);
        packetMap.put(PacketType.PT_HR_LIMIT_DATA, HRLimitPacket.class);
        packetMap.put(PacketType.PT_PVC_LIMIT_DATA, PVCLimitPacket.class);
        packetMap.put(PacketType.PT_ST_LIMIT_DATA, STLimitPacket.class);
        packetMap.put(PacketType.PT_PR_LIMIT_DATA, PRLimitPacket.class);
        packetMap.put(PacketType.PT_NIBP_LIMIT_DATA, NIBPLimitPacket.class);
        packetMap.put(PacketType.PT_RESP_LIMIT_DATA, RESPLimitPacket.class);
        packetMap.put(PacketType.PT_TEMP_LIMIT_DATA, TEMPLimitPacket.class);
        packetMap.put(PacketType.PT_SPO2_LIMIT_DATA, SPO2LimitPacket.class);

    }
}
