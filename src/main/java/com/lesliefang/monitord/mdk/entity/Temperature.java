package com.lesliefang.monitord.mdk.entity;

public class Temperature {
    public byte[] head1 = new byte[4];// 	将来扩展
    public short t1; //	体温1值
    public short t1_high;
    public short t1_low;
    public short t1_level;
    public short t2; //	体温2值
    public short t2_high;
    public short t2_low;
    public short t2_level;

    @Override
    public String toString() {
        return "Temperature{" +
                ", t1=" + t1 +
                ", t1_high=" + t1_high +
                ", t1_low=" + t1_low +
                ", t1_level=" + t1_level +
                ", t2=" + t2 +
                ", t2_high=" + t2_high +
                ", t2_low=" + t2_low +
                ", t2_level=" + t2_level +
                '}';
    }
}
