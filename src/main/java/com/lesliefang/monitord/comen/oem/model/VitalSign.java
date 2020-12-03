package com.lesliefang.monitord.comen.oem.model;


/**
 * 生命体征
 */
public class VitalSign {
    private int HR;
    private String ecg1LeadName;
    private boolean isEcg1LeadOff;
    private int ecg1PVC;
    private byte[] ecg1WaveData;

    private int PR;
    private int PRSource;

    private int RR;
    private boolean isRRLeadOff;
    private byte[] respWaveData;

    private int SPO2;
    private boolean isSPO2LeadOff;
    private byte[] spo2WaveData;

    private int T1;
    private int T2;
    private boolean isTEMPLeadOff;

    private String NIBP_TIME; // 血压测量时间
    private int NIBP_SYS;
    private int NIBP_DIA;
    private int NIBP_MEAN;
    private boolean isNIBPFinish; // 是否测量结束

    public int getHR() {
        return HR;
    }

    public void setHR(int HR) {
        this.HR = HR;
    }

    public String getEcg1LeadName() {
        return ecg1LeadName;
    }

    public void setEcg1LeadName(String ecg1LeadName) {
        this.ecg1LeadName = ecg1LeadName;
    }

    public boolean isEcg1LeadOff() {
        return isEcg1LeadOff;
    }

    public void setEcg1LeadOff(boolean ecg1LeadOff) {
        isEcg1LeadOff = ecg1LeadOff;
    }

    public int getEcg1PVC() {
        return ecg1PVC;
    }

    public void setEcg1PVC(int ecg1PVC) {
        this.ecg1PVC = ecg1PVC;
    }

    public byte[] getEcg1WaveData() {
        return ecg1WaveData;
    }

    public void setEcg1WaveData(byte[] ecg1WaveData) {
        this.ecg1WaveData = ecg1WaveData;
    }

    public int getPR() {
        return PR;
    }

    public void setPR(int PR) {
        this.PR = PR;
    }

    public int getPRSource() {
        return PRSource;
    }

    public void setPRSource(int PRSource) {
        this.PRSource = PRSource;
    }

    public int getRR() {
        return RR;
    }

    public void setRR(int RR) {
        this.RR = RR;
    }

    public boolean isRRLeadOff() {
        return isRRLeadOff;
    }

    public void setRRLeadOff(boolean RRLeadOff) {
        isRRLeadOff = RRLeadOff;
    }

    public byte[] getRespWaveData() {
        return respWaveData;
    }

    public void setRespWaveData(byte[] respWaveData) {
        this.respWaveData = respWaveData;
    }

    public int getSPO2() {
        return SPO2;
    }

    public void setSPO2(int SPO2) {
        this.SPO2 = SPO2;
    }

    public boolean isSPO2LeadOff() {
        return isSPO2LeadOff;
    }

    public void setSPO2LeadOff(boolean SPO2LeadOff) {
        isSPO2LeadOff = SPO2LeadOff;
    }

    public byte[] getSpo2WaveData() {
        return spo2WaveData;
    }

    public void setSpo2WaveData(byte[] spo2WaveData) {
        this.spo2WaveData = spo2WaveData;
    }

    public int getT1() {
        return T1;
    }

    public void setT1(int t1) {
        T1 = t1;
    }

    public int getT2() {
        return T2;
    }

    public void setT2(int t2) {
        T2 = t2;
    }

    public boolean isTEMPLeadOff() {
        return isTEMPLeadOff;
    }

    public void setTEMPLeadOff(boolean TEMPLeadOff) {
        isTEMPLeadOff = TEMPLeadOff;
    }

    public String getNIBP_TIME() {
        return NIBP_TIME;
    }

    public void setNIBP_TIME(String NIBP_TIME) {
        this.NIBP_TIME = NIBP_TIME;
    }

    public int getNIBP_SYS() {
        return NIBP_SYS;
    }

    public void setNIBP_SYS(int NIBP_SYS) {
        this.NIBP_SYS = NIBP_SYS;
    }

    public int getNIBP_DIA() {
        return NIBP_DIA;
    }

    public void setNIBP_DIA(int NIBP_DIA) {
        this.NIBP_DIA = NIBP_DIA;
    }

    public int getNIBP_MEAN() {
        return NIBP_MEAN;
    }

    public void setNIBP_MEAN(int NIBP_MEAN) {
        this.NIBP_MEAN = NIBP_MEAN;
    }

    public boolean isNIBPFinish() {
        return isNIBPFinish;
    }

    public void setNIBPFinish(boolean NIBPFinish) {
        isNIBPFinish = NIBPFinish;
    }
}
