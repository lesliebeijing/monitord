package com.lesliefang.monitord.mdk;

public class MonitorEvent {
    public int bed;
    public int hr;
    public int hr_low;
    public int hr_high;
    public byte[] ecg_wave1;
    public byte[] ecg_wave2;
    public byte[] ecg_wave3;
    public byte[] resp_wave;

    public int rr;
    public int rr_low;
    public int rr_high;

    public int spo2;
    public int spo2_low;
    public int spo2_high;
    public byte[] spo2_wave;

    public int pr;
    public int pr_low;
    public int pr_high;

    public int t1;
    public int t2;

    public int nibp_sys;
    public int nibp_sys_low;
    public int nibp_sys_high;
    public int nibp_dia;
    public int nibp_dia_low;
    public int nibp_dia_high;
    public int nibp_mean;
}
