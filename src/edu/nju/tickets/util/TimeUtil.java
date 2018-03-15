package edu.nju.tickets.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by foxwel on 2018/3/7.
 */
public class TimeUtil {

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static SimpleDateFormat simpleDateFormat1= new SimpleDateFormat("yyyy-MM-dd");

    private static SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm:ss");

    public static String getTimeString() {
        return simpleDateFormat.format(new Date());
    }

    public static Date calcCheckDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 14);
        return calendar.getTime();
    }

    public static String getDayString(Date date) {
        return simpleDateFormat1.format(date);
    }

    public static String getTimeString(Date date) {
        return simpleDateFormat2.format(date);
    }

    public static String getDateString(Date date) {
        return simpleDateFormat.format(date);
    }
}
