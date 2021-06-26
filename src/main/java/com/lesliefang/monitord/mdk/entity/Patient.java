package com.lesliefang.monitord.mdk.entity;

public class Patient {
    public String name;

    public String medNo;

    public String doctor;

    public int age;

    public int height;

    public byte gender; // 0:男 1:女

    public byte bloodType;

    public byte patientType;

    public byte ommit;

    public String comment;

    @Override
    public String toString() {
        return "Patient{" +
                "name='" + name + '\'' +
                ", medNo='" + medNo + '\'' +
                ", doctor='" + doctor + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", gender=" + gender +
                ", bloodType=" + bloodType +
                ", patientType=" + patientType +
                ", ommit=" + ommit +
                ", comment='" + comment + '\'' +
                '}';
    }
}
