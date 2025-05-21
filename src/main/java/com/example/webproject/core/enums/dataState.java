package com.example.webproject.core.enums;

public enum dataState {
        DELETE(0,"删除"),

        NORMAL(1,"正常"),

        SUSPEND(3,"暂停");

        private final Integer code;
        private final String value;

        dataState(Integer code, String value) {
            this.code = code;
            this.value = value;
        }

        public Integer getCode() {
            return code;
        }

        public String getValue() {
            return value;
        }

        public static dataState getState(int value) {
            dataState[] var1 = values();
            int var2 = var1.length;

            for(int var3 = 0; var3 < var2; ++var3) {
                dataState enu = var1[var3];
                if (enu.code == value) {
                    return enu;
                }
            }

            return null;
        }
    }

