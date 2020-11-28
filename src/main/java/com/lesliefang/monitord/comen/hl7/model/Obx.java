package com.lesliefang.monitord.comen.hl7.model;

/**
 * 观察项
 */
public class Obx {
    /**
     * 唯一标识符
     */
    private String key;

    /**
     * 观察值
     */
    private String value;

    /**
     * 单位
     */
    private String unit;

    /**
     * 参考范围下限
     */
    private Float lowLimit;

    /**
     * 参考范围上限
     */
    private Float highLimit;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Float getLowLimit() {
        return lowLimit;
    }

    public void setLowLimit(Float lowLimit) {
        this.lowLimit = lowLimit;
    }

    public Float getHighLimit() {
        return highLimit;
    }

    public void setHighLimit(Float highLimit) {
        this.highLimit = highLimit;
    }

    @Override
    public String toString() {
        return "Obx{" +
                "key='" + key + '\'' +
                ", value='" + value + '\'' +
                ", unit='" + unit + '\'' +
                ", lowLimit=" + lowLimit +
                ", highLimit=" + highLimit +
                '}';
    }
}
