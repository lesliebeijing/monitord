package com.lesliefang.monitord.comen.hl7.model;

public enum EventCode {
    CONNECTED(1),
    UPDATED(2),
    DISCONNECTED(3);

    private int code;

    EventCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
