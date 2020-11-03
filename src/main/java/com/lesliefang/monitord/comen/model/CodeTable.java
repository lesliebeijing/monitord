package com.lesliefang.monitord.comen.model;

public enum CodeTable {
    HR("147842", "HR", "bpm", "心率 次/分"),
    ST1("131841", "ST1", "mv", "st1"),
    ST2("131842", "ST2", "mv", "st2"),
    ST3("131901", "ST3", "mv", "st3"),
    STaVF("131904", "STaVF", "mv", "STaVF"),
    STaVL("131903", "STaVL", "mv", "STaVL"),
    STaVR("131902", "STaVR", "mv", "STaVR"),
    STV1("131843", "STV1", "mv", "STV1"),
    STV2("131844", "STV2", "mv", "STV2"),
    STV3("131845", "STV3", "mv", "STV3"),
    STV4("131846", "STV4", "mv", "STV4"),
    STV5("131847", "STV5", "mv", "STV5"),
    STV6("131848", "STV6", "mv", "STV6"),
    RR("151562", "RR", "rpm", "RESP_RATE 呼吸频率 次/分"),
    T1("150344-T1", "T1", "℃", "体温1"),
    T2("150344-T2", "T2", "℃", "体温2"),
    TD("188440", "TD", "℃", "体温差 T1-T2"),
    SPO2("150456", "SPO2", "%", "血氧饱和度"),
    NIBP_SYS("150301", "NIBP_S", "mmHg", "无创血压-收缩压"),
    NIBP_DIA("150302", "NIBP_D", "mmHg", "无创血压-舒张压"),
    NIBP_MEAN("150303", "NIBP_M", "mmHg", "无创血压-平均压  平均动脉压=(收缩压+2×舒张压)/3"),
    PR_NON_INV("149546", "PR", "bpm", "脉搏（Plus Rate） 次/分"),
    PR("149522", "PR", "bpm", "脉搏（Plus Rate） 次/分"),
//    HEIGHT("188740", "HEIGHT", "cm", "身高"),
//    WEIGHT("188736", "WEIGHT", "kg", "体重"),
//    AGE("2010", "AGE", "", "年龄"),
//    PATI_SPACE("2006", "PATISPACE", "", "病人列表"),
//    BLOOD_TYPE("2007", "BLOODTYPE", "", "血型"),
    ;

    CodeTable(String code, String label, String unit, String desc) {
        this.code = code;
        this.label = label;
        this.unit = unit;
        this.desc = desc;
    }

    CodeTable(String code, String label, String unit) {
        this(code, label, unit, "");
    }

    private String code;

    private String label;

    private String unit;

    private String desc;

    public static CodeTable getCodeTable(String code) {
        for (CodeTable value : CodeTable.values()) {
            if (value.code.equals(code)) {
                return value;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
