package com.example.webproject.core.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTime {
    /***
     * 获取当前时间
     * ***/
    public Date getTime(){
        Date date = new Date();
        // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(date));
        return date;
    }

//    public static void main(String[] args) {
//         GetTime getTime = new GetTime();
//         getTime.getTime();
//
//    }
}
