package com.lesliefang.monitord.mdk.entity;

import com.lesliefang.monitord.mdk.Utils;

public class ECG {
    public byte ecglead1off; // 1-有导联 0-无导联
    public byte ecglead2off;
    public byte leadmode; // 默认0
    public byte respleadoff; //默认0
    /**
     * 心电通道1参数
     * D6D5D4: 对应导联波形;  1-1导，2-2导，3-3导，4-AVR，5-AVL，6-AVF， 7--V
     * D1,D0 对应增益 00-X0.25, 01-X0.5,02-X1,03--X2
     */
    public short ecgdef1;
    public short ecgdef2;

    public short hr; // 心率值
    public short hr_high; // 心率上限
    public short hr_low; //	心率下限
    public short hr_level; // 心率报警级别
    public short rr;  // 呼吸率值，同上
    public short rr_high;
    public short rr_low;
    public short rr_level;
    public short st1;  // st1值
    public short st1_high;
    public short st1_low;
    public short st1_level;
    public short st2; //	st2值
    public short st2_high;
    public short st2_low;
    public short st2_level;
    public short pvc; //	pvc值
    public short pvc_high;
    public short pvc_low;
    public short pvc_level;

    public byte[] wavedata = new byte[256 * 2 + 128];    //	2通道心电波形，1通道呼吸波形。
    public byte[] ecg3data = new byte[256];
    public byte demoflag;
    public byte ext;

    @Override
    public String toString() {
        return "ECG{" +
                "ecglead1off=" + ecglead1off +
                ", ecglead2off=" + ecglead2off +
                ", leadmode=" + leadmode +
                ", respleadoff=" + respleadoff +
                ", ecgdef1=" + ecgdef1 +
                ", ecgdef2=" + ecgdef2 +
                ", hr=" + hr +
                ", hr_high=" + hr_high +
                ", hr_low=" + hr_low +
                ", hr_level=" + hr_level +
                ", rr=" + rr +
                ", rr_high=" + rr_high +
                ", rr_low=" + rr_low +
                ", rr_level=" + rr_level +
                ", st1=" + st1 +
                ", st1_high=" + st1_high +
                ", st1_low=" + st1_low +
                ", st1_level=" + st1_level +
                ", st2=" + st2 +
                ", st2_high=" + st2_high +
                ", st2_low=" + st2_low +
                ", st2_level=" + st2_level +
                ", pvc=" + pvc +
                ", pvc_high=" + pvc_high +
                ", pvc_low=" + pvc_low +
                ", pvc_level=" + pvc_level +
                ", wavedata=" + Utils.myArrayToString(wavedata) +
                ", demoflag=" + demoflag +
                ", ext=" + ext +
                '}';
    }
}
