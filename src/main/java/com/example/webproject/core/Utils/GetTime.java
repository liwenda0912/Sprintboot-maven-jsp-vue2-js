package com.example.webproject.core.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class GetTime {
//    public static void main(String[] args) {
//        GetTime getTime = new GetTime();
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Long data = getSimpleDateFormat(getTime());
//        System.out.print(getFormattedDate(data));;
//   }

    /***
     * 获取当前时间
     * ***/
    public static String getTime() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(date);
    }

    // 时间格式变为时间戳
    public static Long getSimpleDateFormat(String date_) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            Date date = sdf.parse(date_);
            return date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     *
     *
     * @return 时间格式
     */
    // 时间戳转变为时间格式
    public static String getFormattedDate(Long s) {
        Date date = new Date(s);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }

    /**
     * 获取00：00：00的时间戳
     *
     * @return 时间戳
     */
    public static Long getStartTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTimeInMillis()/1000;
    }

    /**
     * 获取当天23：59：59的时间戳
     *
     * @return 时间戳
     */
    public static Long getEndTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTimeInMillis()/1000;
    }
}
