package com.example.webproject.core.Utils;

import com.example.webproject.WebProjectApplication;
import org.springframework.boot.SpringApplication;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTime {
    /***
     * 获取当前时间
     * ***/
    public String getTime(){
        Date date = new Date();
        // this object contains the current date value
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(date));
        return formatter.format(date);
    }

//    public static void main(String[] args) {
//         GetTime getTime = new GetTime();
//         getTime.getTime();
//
//    }
}
