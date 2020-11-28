package com.lesliefang.monitord.comen.oem.message;

public interface PacketType {
    byte PT_PM_TYPE = 1; // 监护仪类型包
    byte PT_GET_MODULE_INFO = 2; // 获取模块信息包
    byte PT_MODULE_INFO = 3; // 模块信息包(注意可以双向)
    byte PT_GET_PATIENT_INFO = 4; //	获取病人信息包
    byte PT_PATIENT_INFO = 5; //	病人信息包
    byte PT_RECIEVE_PATIENT = 6; // 接收病人包
    byte PT_RELEASE_PATIENT = 7; // 解除病人包
    byte PT_HEARTBEAT = 8; // 心跳包
    byte PT_LOGIN = 9; // 上电注册包
    byte PT_LOGIN_ACK = 10; // 上电注册应答包
    byte PT_LOGOUT = 11; // 注销包
    byte PT_INIT_FINISH = 12; // 注册完成包
    byte PT_ECG1L_DATA = 13; // 1个导联数据的ECG数据包(波形只有I/II/V中的其中1道)
    byte PT_ECG3L_DATA = 14; // 3个导联数据的ECG数据包(波形I+II+V三道)
    byte PT_ECG8L_DATA = 15; // 8导联数据的ECG数据包(波形I+II+V1~6V)
    byte PT_CO_DATA = 16; //	CO数据包
    byte PT_IOC_DATA = 17; // IOC数据包
    byte PT_FETUS_DATA = 18; // FETUS胎监数据包
    byte PT_PR_DATA = 19; //	PR数据包
    byte PT_ICG_DATA = 20; // ICG数据包
    byte PT_AG_DATA = 21; //	AG数据包
    byte PT_IBP_DATA = 22; // IBP 数据包
    byte PT_NIBP_DATA = 23; // NIBP 数据包
    byte PT_TEMP_DATA = 24; // TEMP 数据包
    byte PT_RESP_DATA = 25; // RESP 数据包
    byte PT_SPO2_NELL_DATA = 26; // SPO2_NELL数据包
    byte PT_SPO2_MAS_DATA = 27; // SPO2_MAS数据包
    byte PT_SPO2_DIG_DATA = 28; // SPO2_DIG数据包
    byte PT_CO2_DATA = 29; // CO2数据包
    byte PT_NET_CONTROL = 30; //	网络控制数据包
    byte PT_NET_CONTROL_ACK = 31; //	网络控制应答包
    byte PT_NET_ALM_INTELLI = 32; //	智能报警包
    byte PT_START_NIBP = 33; // NIBP 测量开始包
    byte PT_STOP_NIBP = 34; // NIBP 测量中止包
    byte PT_HR_LIMIT_DATA = 35; // 心率报警上下限包
    byte PT_PVC_LIMIT_DATA = 36; // PVC报警上下限包
    byte PT_ST_LIMIT_DATA = 37; // ST的报警上下限包
    byte PT_CO_LIMIT_DATA = 38; // C.O.报警上下限包
    byte PT_IOC_LIMIT_DATA = 39; // IOC报警上下限包
    byte PT_FETUS_LIMIT_DATA = 40; // FETUS胎监上下限包
    byte PT_PR_LIMIT_DATA = 41; // PR报警上下限包
    byte PT_ICG_LIMIT_DATA = 42; // ICG报警上下限包
    byte PT_AG_LIMIT_DATA = 43; // AG报警上下限包
    byte PT_IBP_LIMIT_DATA = 44; // IBP报警上下限包
    byte PT_NIBP_LIMIT_DATA = 45; //	NIBP报警上下限包
    byte PT_TEMP_LIMIT_DATA = 46; //	TEMP报警上下限包
    byte PT_RESP_LIMIT_DATA = 47; //	RESP报警上下限包
    byte PT_SPO2_LIMIT_DATA = 48; // SPO2报警上下限包
    byte PT_CO2_LIMIT_DATA = 49; // CO2报警上下限包
    byte PT_GETSYSTIME = 50; // 获取系统时间
    byte PT_SYSTIME = 51; //	系统时间
    byte PT_MAC_ADDR = 52; // 设备的Mac地址
    byte PT_DEVICE_SN = 53; // 设备序列号
    byte PT_HEARTBEAT_INTERVAL = 54; // 设置心跳包发送时间间隔
    byte PT_DATA_CTRL_ACK = 55; // 互控数据应答包

    byte PT_REMOTE_ECG1L_WAVE = 80; // 1个导联数据的心电数据包(固定II导)
    byte PT_REMOTE_ECG3L_WAVE = 81; // 3个导联数据的心电数据包
    byte PT_REMOTE_ECG8L_WAVE = 82; // 8个导联数据的心电数据包
    byte PT_REMOTE_SPO2_DATA = 83; // 遥测脉率包
    byte PT_REMOTE_STATE_DATA = 84; // 遥测状态数据包


    byte CUSTOM_DISCONNECTED = 100; // 自定义：监护仪掉线
}
