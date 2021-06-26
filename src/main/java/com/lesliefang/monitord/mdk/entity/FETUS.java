package com.lesliefang.monitord.mdk.entity;

import java.util.Arrays;

public class FETUS {
    public byte[] head1 = new byte[4]; // 将来扩展
    public byte fetusmode;    // 分辨率
    public byte fetusvolume; //胎心音量
    public byte marked; // 胎动标记
    public byte ucreset;// 宫缩复位标记
    public short[] fhr = new short[4];// 胎心率值
    public short fhr_high;
    public short fhr_low;
    public short fhr_level;
    public short[] uc = new short[4];//	宫缩压值
    public short uc_high;
    public short uc_low;
    public short uc_level;

    @Override
    public String toString() {
        return "FETUS{" +
                ", fetusmode=" + fetusmode +
                ", fetusvolume=" + fetusvolume +
                ", marked=" + marked +
                ", ucreset=" + ucreset +
                ", fhr=" + Arrays.toString(fhr) +
                ", fhr_high=" + fhr_high +
                ", fhr_low=" + fhr_low +
                ", fhr_level=" + fhr_level +
                ", uc=" + Arrays.toString(uc) +
                ", uc_high=" + uc_high +
                ", uc_low=" + uc_low +
                ", uc_level=" + uc_level +
                '}';
    }
}
