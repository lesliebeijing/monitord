package com.lesliefang.monitord.comen.model;

import java.util.HashMap;
import java.util.Map;

public class DeviceData {
    /**
     * 设备号
     */
    private String deviceId;

    /**
     * 病人唯一标识
     */
    private String patientId;

    private String patientName;

    /**
     * 病房号
     */
    private String wardNum;

    /**
     * 床号
     */
    private String bedNum;

    /**
     * 网络床号，可以作为设备的唯一标识用
     */
    private String netBedNum;

    /**
     * 观察日期时间
     */
    private String datetime;

    /**
     * 观察项 Map 集合
     */
    private Map<String, Obx> obxMap = new HashMap<>();

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getWardNum() {
        return wardNum;
    }

    public void setWardNum(String wardNum) {
        this.wardNum = wardNum;
    }

    public String getBedNum() {
        return bedNum;
    }

    public void setBedNum(String bedNum) {
        this.bedNum = bedNum;
    }

    public String getNetBedNum() {
        return netBedNum;
    }

    public void setNetBedNum(String netBedNum) {
        this.netBedNum = netBedNum;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public Map<String, Obx> getObxMap() {
        return obxMap;
    }

    public void setObxMap(Map<String, Obx> obxMap) {
        this.obxMap = obxMap;
    }

    @Override
    public String toString() {
        return "DeviceData{" +
                "deviceId='" + deviceId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", patientName='" + patientName + '\'' +
                ", wardNum='" + wardNum + '\'' +
                ", bedNum='" + bedNum + '\'' +
                ", netBedNum='" + netBedNum + '\'' +
                ", datetime='" + datetime + '\'' +
                ", obxMap=" + obxMap +
                '}';
    }
}
