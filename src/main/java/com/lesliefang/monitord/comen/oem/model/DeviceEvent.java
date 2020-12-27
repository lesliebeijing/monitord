package com.lesliefang.monitord.comen.oem.model;

public class DeviceEvent {

    private int eventType;

    private Integer netBedNum; // 网络床号，可作为设备唯一标识

    private String deviceSn;

    private String patientName; // 病人姓名

    private Object data;

    public DeviceEvent() {

    }

    public DeviceEvent(int eventType, int netBedNum) {
        this(eventType, netBedNum, null);
    }

    public DeviceEvent(int eventType, int netBedNum, Object data) {
        this.eventType = eventType;
        this.data = data;
        this.netBedNum = netBedNum;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public int getNetBedNum() {
        return netBedNum;
    }

    public void setNetBedNum(int netBedNum) {
        this.netBedNum = netBedNum;
    }

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DeviceEvent{" +
                "eventType=" + eventType +
                ", netBedNum=" + netBedNum +
                ", deviceSn='" + deviceSn + '\'' +
                ", patientName='" + patientName + '\'' +
                ", data=" + data +
                '}';
    }
}
