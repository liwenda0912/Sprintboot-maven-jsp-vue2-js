package com.example.webproject.core.Utils;

public class roundUpFirstNonZeroDecimal {
    public  int roundUpFirst(float num) {
        // 将数字转换为字符串
        String numStr = String.valueOf(num);
        // 找到小数点的位置
        String[] decimalIndex = numStr.split("\\.");
        if (Integer.parseInt(decimalIndex[1]) == 0){
            return Integer.parseInt(decimalIndex[0]);
        }else {
            return Integer.parseInt(decimalIndex[0])+1;
        }
    }
}
