package com.lesliefang.monitord.comen.model;

public class DeviceEvent {

    private int eventCode;

    /**
     * 设备唯一标识，可用网络床号作为唯一标识
     */
    private String sn;

    private DeviceData deviceData;

    public DeviceEvent() {

    }

    public DeviceEvent(String sn) {
        this.sn = sn;
    }

    public DeviceEvent(String sn, int eventCode) {
        this.sn = sn;
        this.eventCode = eventCode;
    }

    public int getEventCode() {
        return eventCode;
    }

    public void setEventCode(int eventCode) {
        this.eventCode = eventCode;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public DeviceData getDeviceData() {
        return deviceData;
    }

    public void setDeviceData(DeviceData deviceData) {
        this.deviceData = deviceData;
    }

    @Override
    public String toString() {
        return "DeviceEvent{" +
                "eventCode=" + eventCode +
                ", sn='" + sn + '\'' +
                ", deviceData=" + deviceData +
                '}';
    }
}
