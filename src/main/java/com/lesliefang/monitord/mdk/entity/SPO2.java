package com.lesliefang.monitord.mdk.entity;

import com.lesliefang.monitord.mdk.Utils;

public class SPO2 {
    public byte[] head = new byte[4]; // 将来扩展
    public short spo2; // 血氧值
    public short spo2_high;
    public short spo2_low;
    public short spo2_level;
    public short pr;                    //	脉率值
    public short pr_high;
    public short pr_low;
    public short pr_level;
    public byte[] wavedata = new byte[86];    //	波形数据

    @Override
    public String toString() {
        return "SPO2{" +
                ", spo2=" + spo2 +
                ", spo2_high=" + spo2_high +
                ", spo2_low=" + spo2_low +
                ", spo2_level=" + spo2_level +
                ", pr=" + pr +
                ", pr_high=" + pr_high +
                ", pr_low=" + pr_low +
                ", pr_level=" + pr_level +
                ", wavedata=" + Utils.myArrayToString(wavedata) +
                '}';
    }
}

