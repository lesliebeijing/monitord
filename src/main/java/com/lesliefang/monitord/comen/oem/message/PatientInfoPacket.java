package com.lesliefang.monitord.comen.oem.message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;

import java.nio.charset.Charset;

/**
 * 病人信息包
 */
public class PatientInfoPacket extends Packet {
    private String familyName; // 姓
    private String givenName;  // 名
    private String medicalRecord; // 病历号
    private byte age; // 年龄
    private short height; // 身高(cm)
    private int weight; // 体重(扩大10倍，kg)
    private byte sex; // 病人性别(0:男; 1:女)
    private byte bloodType; // 病人血型类型(0:不详; 1:A; 2:B; 3:AB; 4:O)
    private byte patientType; // 病人类型(0:成人; 1:小孩; 2:新生儿)
    private String birthday; // 出生日期(YYYYMMDD，ASCII码”20120512”)
    private String admiDate; // 入院日期(YYYYMMDD，ASCII码”20120512”)
    private String doctor; // 医生
    private String order;
    private String comment; // 病人注解
    private byte gravd; //孕次
    private byte embryo; //胎数
    private byte para; //产次
    private short gesta; //孕龄(周)
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
    protected ByteBuf buildData() {
        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes(handlerStringField(familyName, 24));
        buf.writeBytes(handlerStringField(givenName, 24));
        buf.writeBytes(handlerStringField(medicalRecord, 24));
        buf.writeByte(age);
        buf.writeByte(height);
        buf.writeShortLE(weight);
        buf.writeByte(sex);
        buf.writeByte(bloodType);
        buf.writeByte(patientType);
        buf.writeBytes(handlerStringField(birthday, 8));
        buf.writeBytes(handlerStringField(admiDate, 8));
        buf.writeBytes(handlerStringField(doctor, 24));
        buf.writeBytes(handlerStringField(comment, 250));
        buf.writeByte(gravd);
        buf.writeByte(embryo);
        buf.writeByte(para);
        buf.writeShortLE(gesta);
        buf.writeBytes(handlerStringField(department, 24));
        buf.writeBytes(handlerStringField(room, 6));
        buf.writeBytes(handlerStringField(bed, 10));
        buf.writeBytes(handlerStringField(telephone, 24));
        buf.writeBytes(handlerStringField(postCode, 8));
        buf.writeBytes(handlerStringField(email, 48));
        buf.writeBytes(handlerStringField(address, 200));
        return buf;
    }

    @Override
    public void parseData(ByteBuf data) {
        familyName = data.readCharSequence(24, Charset.forName("GBK")).toString();
        givenName = data.readCharSequence(24, Charset.forName("GBK")).toString();
        medicalRecord = data.readCharSequence(24, CharsetUtil.UTF_8).toString();
        age = data.readByte();
        height = data.readUnsignedByte();
        weight = data.readUnsignedShortLE();
        sex = data.readByte();
        bloodType = data.readByte();
        patientType = data.readByte();
        birthday = data.readCharSequence(8, CharsetUtil.UTF_8).toString();
        admiDate = data.readCharSequence(8, CharsetUtil.UTF_8).toString();
        doctor = data.readCharSequence(24, Charset.forName("GBK")).toString();
        order = data.readCharSequence(250, CharsetUtil.UTF_8).toString();
        comment = data.readCharSequence(250, Charset.forName("GBK")).toString();
        gravd = data.readByte();
        embryo = data.readByte();
        para = data.readByte();
        gesta = data.readShortLE();
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

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(String medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) {
        this.age = age;
    }

    public short getHeight() {
        return height;
    }

    public void setHeight(short height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public byte getBloodType() {
        return bloodType;
    }

    public void setBloodType(byte bloodType) {
        this.bloodType = bloodType;
    }

    public byte getPatientType() {
        return patientType;
    }

    public void setPatientType(byte patientType) {
        this.patientType = patientType;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getAdmiDate() {
        return admiDate;
    }

    public void setAdmiDate(String admiDate) {
        this.admiDate = admiDate;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public byte getGravd() {
        return gravd;
    }

    public void setGravd(byte gravd) {
        this.gravd = gravd;
    }

    public byte getEmbryo() {
        return embryo;
    }

    public void setEmbryo(byte embryo) {
        this.embryo = embryo;
    }

    public byte getPara() {
        return para;
    }

    public void setPara(byte para) {
        this.para = para;
    }

    public short getGesta() {
        return gesta;
    }

    public void setGesta(short gesta) {
        this.gesta = gesta;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getBed() {
        return bed;
    }

    public void setBed(String bed) {
        this.bed = bed;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
