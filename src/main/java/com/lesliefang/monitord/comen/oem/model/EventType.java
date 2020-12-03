package com.lesliefang.monitord.comen.oem.model;

public interface EventType {
    /**
     * 设备基本信息  DeviceContext
     * 上位机首次连上和监护仪信息发生改变后发送
     */
    int EVENT_DEVICE_CONTEXT = 1;
    /**
     * 监护数据  VitalSign
     * 实时发送
     */
    int EVENT_MONITOR_DATA = 2;
    /**
     * 设备下线
     * 设备离线时发送
     */
    int EVENT_DISCONNECTED = 3;
}
