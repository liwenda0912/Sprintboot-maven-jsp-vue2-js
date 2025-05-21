package com.example.webproject.core.common;

public class globalDate {
    private static String globalValue = null;

    public static String getGlobalValue() {
        return globalValue;
    }

    public static void setGlobalValue(String globalValue) {
        globalDate.globalValue = globalValue;
    }
}
