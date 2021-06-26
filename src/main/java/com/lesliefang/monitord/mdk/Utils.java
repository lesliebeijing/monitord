package com.lesliefang.monitord.mdk;

public class Utils {
    public static String myArrayToString(byte[] data) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < data.length; i++) {
            sb.append((data[i] & 0xff) + ",");
        }
        sb.append("]");
        return sb.toString();
    }
}
