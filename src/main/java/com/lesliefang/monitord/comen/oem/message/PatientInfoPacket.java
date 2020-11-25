package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;
import io.netty.util.CharsetUtil;

/**
 * 病人信息包
 */
public class PatientInfoPacket extends Packet {
    private String familyName; // 姓
    private String givenName;  // 名
    private String medicalRecord; // 病历号
    private short age; // 年龄
    private short height; // 身高(cm)
    private int weight; // 体重(扩大10倍，kg)
    private short sex; // 病人性别(0:男; 1:女)
    private short bloodType; // 病人血型类型(0:不详; 1:A; 2:B; 3:AB; 4:O)
    private short patientType; // 病人类型(0:成人; 1:小孩; 2:新生儿)
    private String birthday; // 出生日期(YYYYMMDD，ASCII码”20120512”)
    private String admiDate; // 入院日期(YYYYMMDD，ASCII码”20120512”)
    private String doctor; // 医生
    private String order;
    private String comment; // 病人注解
    private short gravd; //孕次
    private short embryo; //胎数
    private short para; //胎数
    private int gesta; //孕龄(周)
    private String department; //科室
    private String room; //病房(数字或字母的ASCII码)
    private String bed; //病床(数字或字母的ASCII码)
    private String telephone; //电话(数字的ASCII码)
    private String postCode; //邮政编码(数字的ASCII码)
    private String email; //EMAIL(数字或字母的ASCII码)
    private String address; //住址

    public PatientInfoPacket() {
        super(PacketType.PT_PATIENT_INFO);
    }

    @Override
    public void parseData(ByteBuf data) {
        familyName = data.readCharSequence(24, CharsetUtil.UTF_8).toString();
        givenName = data.readCharSequence(24, CharsetUtil.UTF_8).toString();
        medicalRecord = data.readCharSequence(24, CharsetUtil.UTF_8).toString();
        age = data.readUnsignedByte();
        height = data.readUnsignedByte();
        weight = data.readUnsignedShortLE();
        sex = data.readUnsignedByte();
        bloodType = data.readUnsignedByte();
        patientType = data.readUnsignedByte();
        birthday = data.readCharSequence(8, CharsetUtil.UTF_8).toString();
        admiDate = data.readCharSequence(8, CharsetUtil.UTF_8).toString();
        doctor = data.readCharSequence(24, CharsetUtil.UTF_8).toString();
        order = data.readCharSequence(250, CharsetUtil.UTF_8).toString();
        comment = data.readCharSequence(250, CharsetUtil.UTF_8).toString();
        gravd = data.readUnsignedByte();
        embryo = data.readUnsignedByte();
        para = data.readUnsignedByte();
        gesta = data.readUnsignedShortLE();
        department = data.readCharSequence(24, CharsetUtil.UTF_8).toString();
        room = data.readCharSequence(6, CharsetUtil.UTF_8).toString();
        bed = data.readCharSequence(10, CharsetUtil.UTF_8).toString();
        telephone = data.readCharSequence(24, CharsetUtil.UTF_8).toString();
        postCode = data.readCharSequence(8, CharsetUtil.UTF_8).toString();
        email = data.readCharSequence(48, CharsetUtil.UTF_8).toString();
        address = data.readCharSequence(200, CharsetUtil.UTF_8).toString();
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public short getAge() {
        return age;
    }

    public short getHeight() {
        return height;
    }

    public int getWeight() {
        return weight;
    }

    public short getSex() {
        return sex;
    }

    public short getBloodType() {
        return bloodType;
    }

    public short getPatientType() {
        return patientType;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getAdmiDate() {
        return admiDate;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getOrder() {
        return order;
    }

    public String getComment() {
        return comment;
    }

    public short getGravd() {
        return gravd;
    }

    public short getEmbryo() {
        return embryo;
    }

    public short getPara() {
        return para;
    }

    public int getGesta() {
        return gesta;
    }

    public String getDepartment() {
        return department;
    }

    public String getRoom() {
        return room;
    }

    public String getBed() {
        return bed;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getPostCode() {
        return postCode;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    @Override
    public String toString() {
        return "PatientInfoPacket{" +
                "type=" + type +
                ", bedNum=" + bedNum +
                ", familyName='" + familyName + '\'' +
                ", givenName='" + givenName + '\'' +
                ", medicalRecord='" + medicalRecord + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                ", sex=" + sex +
                ", bloodType=" + bloodType +
                ", patientType=" + patientType +
                ", room='" + room + '\'' +
                ", bed='" + bed + '\'' +
                '}';
    }
}
