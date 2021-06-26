package com.lesliefang.monitord.mdk.entity;

public class NIBP {
    public byte[] head1 = new byte[4];  // 将来扩展
    public byte[] head2 = new byte[12]; // 将来扩展
    public short sys; // 高压值
    public short sys_high;
    public short sys_low;
    public short sys_level;
    public short mea; // 平均压值
    public short mea_high;
    public short mea_low;
    public short mea_level;
    public short dia; // 低压值
    public short dia_high;
    public short dia_low;
    public short dia_level;

    @Override
    public String toString() {
        return "NIBP{" +
                ", sys=" + sys +
                ", sys_high=" + sys_high +
                ", sys_low=" + sys_low +
                ", sys_level=" + sys_level +
                ", mea=" + mea +
                ", mea_high=" + mea_high +
                ", mea_low=" + mea_low +
                ", mea_level=" + mea_level +
                ", dia=" + dia +
                ", dia_high=" + dia_high +
                ", dia_low=" + dia_low +
                ", dia_level=" + dia_level +
                '}';
    }
}
