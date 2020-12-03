package com.lesliefang.monitord.comen.oem.model;

public class DeviceContext {
    /**
     * 设备序列号
     */
    private String deviceSn;

    /**
     * 网络床号
     */
    private Integer netBedNum;

    /**
     * 是否已经接收病人，只有接收病人以后监护仪才会发送病人信息和监护数据
     * 监护仪和中央站都可以控制接收病人和解除病人
     */
    private boolean hasReceivePatient;

    /**
     * 病人床号
     */
    private String bedNum;

    // 病人姓名
    private String patientName;

    /**
     * HR 心率报警设置
     */
    private boolean isHRAlarmOn = true; // HR报警是否开启
    private int HRAlarmLevel = -1; // HR报警级别
    private int HRHighLimit = -1; // HR报警上限
    private int HRLowLimit = -1; // HR报警下限

    /**
     * PR 脉搏报警设置
     */
    private boolean isPRAlarmOn = true;
    private int PRAlarmLevel = -1;
    private int PRHighLimit = -1;
    private int PRLowLimit = -1;

    /**
     * NIBP 血压报警设置
     */
    private boolean isNIBPAlarmOn = true;
    private int NIBPAlarmLevel = -1;
    private int NIBPSysHighLimit = -1;
    private int NIBPSysLowLimit = -1;
    private int NIBPDiaHighLimit = -1;
    private int NIBPDiaLowLimit = -1;
    private int NIBPMeanHighLimit = -1;
    private int NIBPMeanLowLimit = -1;

    /**
     * TEMP 体温报警设置
     */
    private boolean isTEMPAlarmOn = true;
    private int TEMPAlarmLevel = -1;
    private int T1HighLimit = -1;
    private int T1LowLimit = -1;
    private int T2HighLimit = -1;
    private int T2LowLimit = -1;

    /**
     * RESP 呼吸报警设置
     */
    private boolean isRESPAlarmOn = true;
    private int RESPAlarmLevel = -1;
    private int RESPHighLimit = -1;
    private int RESPLowLimit = -1;

    /**
     * SPO2 呼吸报警设置
     */
    private boolean isSPO2AlarmOn = true;
    private int SPO2AlarmLevel = -1;
    private int SPO2HighLimit = -1;
    private int SPO2LowLimit = -1;

    public String getDeviceSn() {
        return deviceSn;
    }

    public void setDeviceSn(String deviceSn) {
        this.deviceSn = deviceSn;
    }

    public Integer getNetBedNum() {
        return netBedNum;
    }

    public void setNetBedNum(Integer netBedNum) {
        this.netBedNum = netBedNum;
    }

    public String getBedNum() {
        return bedNum;
    }

    public void setBedNum(String bedNum) {
        this.bedNum = bedNum;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public boolean isHRAlarmOn() {
        return isHRAlarmOn;
    }

    public void setHRAlarmOn(boolean HRAlarmOn) {
        isHRAlarmOn = HRAlarmOn;
    }

    public int getHRAlarmLevel() {
        return HRAlarmLevel;
    }

    public void setHRAlarmLevel(int HRAlarmLevel) {
        this.HRAlarmLevel = HRAlarmLevel;
    }

    public int getHRHighLimit() {
        return HRHighLimit;
    }

    public void setHRHighLimit(int HRHighLimit) {
        this.HRHighLimit = HRHighLimit;
    }

    public int getHRLowLimit() {
        return HRLowLimit;
    }

    public void setHRLowLimit(int HRLowLimit) {
        this.HRLowLimit = HRLowLimit;
    }

    public boolean isPRAlarmOn() {
        return isPRAlarmOn;
    }

    public void setPRAlarmOn(boolean PRAlarmOn) {
        isPRAlarmOn = PRAlarmOn;
    }

    public int getPRAlarmLevel() {
        return PRAlarmLevel;
    }

    public void setPRAlarmLevel(int PRAlarmLevel) {
        this.PRAlarmLevel = PRAlarmLevel;
    }

    public int getPRHighLimit() {
        return PRHighLimit;
    }

    public void setPRHighLimit(int PRHighLimit) {
        this.PRHighLimit = PRHighLimit;
    }

    public int getPRLowLimit() {
        return PRLowLimit;
    }

    public void setPRLowLimit(int PRLowLimit) {
        this.PRLowLimit = PRLowLimit;
    }

    public boolean isNIBPAlarmOn() {
        return isNIBPAlarmOn;
    }

    public void setNIBPAlarmOn(boolean NIBPAlarmOn) {
        isNIBPAlarmOn = NIBPAlarmOn;
    }

    public int getNIBPAlarmLevel() {
        return NIBPAlarmLevel;
    }

    public void setNIBPAlarmLevel(int NIBPAlarmLevel) {
        this.NIBPAlarmLevel = NIBPAlarmLevel;
    }

    public int getNIBPSysHighLimit() {
        return NIBPSysHighLimit;
    }

    public void setNIBPSysHighLimit(int NIBPSysHighLimit) {
        this.NIBPSysHighLimit = NIBPSysHighLimit;
    }

    public int getNIBPSysLowLimit() {
        return NIBPSysLowLimit;
    }

    public void setNIBPSysLowLimit(int NIBPSysLowLimit) {
        this.NIBPSysLowLimit = NIBPSysLowLimit;
    }

    public int getNIBPDiaHighLimit() {
        return NIBPDiaHighLimit;
    }

    public void setNIBPDiaHighLimit(int NIBPDiaHighLimit) {
        this.NIBPDiaHighLimit = NIBPDiaHighLimit;
    }

    public int getNIBPDiaLowLimit() {
        return NIBPDiaLowLimit;
    }

    public void setNIBPDiaLowLimit(int NIBPDiaLowLimit) {
        this.NIBPDiaLowLimit = NIBPDiaLowLimit;
    }

    public int getNIBPMeanHighLimit() {
        return NIBPMeanHighLimit;
    }

    public void setNIBPMeanHighLimit(int NIBPMeanHighLimit) {
        this.NIBPMeanHighLimit = NIBPMeanHighLimit;
    }

    public int getNIBPMeanLowLimit() {
        return NIBPMeanLowLimit;
    }

    public void setNIBPMeanLowLimit(int NIBPMeanLowLimit) {
        this.NIBPMeanLowLimit = NIBPMeanLowLimit;
    }

    public boolean isTEMPAlarmOn() {
        return isTEMPAlarmOn;
    }

    public void setTEMPAlarmOn(boolean TEMPAlarmOn) {
        isTEMPAlarmOn = TEMPAlarmOn;
    }

    public int getTEMPAlarmLevel() {
        return TEMPAlarmLevel;
    }

    public void setTEMPAlarmLevel(int TEMPAlarmLevel) {
        this.TEMPAlarmLevel = TEMPAlarmLevel;
    }

    public int getT1HighLimit() {
        return T1HighLimit;
    }

    public void setT1HighLimit(int t1HighLimit) {
        T1HighLimit = t1HighLimit;
    }

    public int getT1LowLimit() {
        return T1LowLimit;
    }

    public void setT1LowLimit(int t1LowLimit) {
        T1LowLimit = t1LowLimit;
    }

    public int getT2HighLimit() {
        return T2HighLimit;
    }

    public void setT2HighLimit(int t2HighLimit) {
        T2HighLimit = t2HighLimit;
    }

    public int getT2LowLimit() {
        return T2LowLimit;
    }

    public void setT2LowLimit(int t2LowLimit) {
        T2LowLimit = t2LowLimit;
    }

    public boolean isRESPAlarmOn() {
        return isRESPAlarmOn;
    }

    public void setRESPAlarmOn(boolean RESPAlarmOn) {
        isRESPAlarmOn = RESPAlarmOn;
    }

    public int getRESPAlarmLevel() {
        return RESPAlarmLevel;
    }

    public void setRESPAlarmLevel(int RESPAlarmLevel) {
        this.RESPAlarmLevel = RESPAlarmLevel;
    }

    public int getRESPHighLimit() {
        return RESPHighLimit;
    }

    public void setRESPHighLimit(int RESPHighLimit) {
        this.RESPHighLimit = RESPHighLimit;
    }

    public int getRESPLowLimit() {
        return RESPLowLimit;
    }

    public void setRESPLowLimit(int RESPLowLimit) {
        this.RESPLowLimit = RESPLowLimit;
    }

    public boolean isSPO2AlarmOn() {
        return isSPO2AlarmOn;
    }

    public void setSPO2AlarmOn(boolean SPO2AlarmOn) {
        isSPO2AlarmOn = SPO2AlarmOn;
    }

    public int getSPO2AlarmLevel() {
        return SPO2AlarmLevel;
    }

    public void setSPO2AlarmLevel(int SPO2AlarmLevel) {
        this.SPO2AlarmLevel = SPO2AlarmLevel;
    }

    public int getSPO2HighLimit() {
        return SPO2HighLimit;
    }

    public void setSPO2HighLimit(int SPO2HighLimit) {
        this.SPO2HighLimit = SPO2HighLimit;
    }

    public int getSPO2LowLimit() {
        return SPO2LowLimit;
    }

    public void setSPO2LowLimit(int SPO2LowLimit) {
        this.SPO2LowLimit = SPO2LowLimit;
    }

    public boolean isHasReceivePatient() {
        return hasReceivePatient;
    }

    public void setHasReceivePatient(boolean hasReceivePatient) {
        this.hasReceivePatient = hasReceivePatient;
    }
}
