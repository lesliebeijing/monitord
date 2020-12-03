package com.lesliefang.monitord.comen.oem.model;

public class DeviceEvent {

    private int eventType;

    private int netBedNum; // 网络床号，可作为设备唯一标识

    private String deviceSn;

    private Object data;

    public DeviceEvent() {

    }

    public DeviceEvent(int eventType) {
        this(eventType, null);
    }

    public DeviceEvent(int eventType, Object data) {
        this.eventType = eventType;
        this.data = data;
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

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
