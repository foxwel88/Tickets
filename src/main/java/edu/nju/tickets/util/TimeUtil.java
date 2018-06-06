package edu.nju.tickets.util;

import java.text.ParseException;
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

    private static SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public static String getTimeString() {
        return simpleDateFormat.format(new Date());
    }

    public static Date calcCheckDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 14);
        return calendar.getTime();
    }

    public static boolean calcCancelDate(Date date) {
        Date nowDate = new Date();

        Date date1 = new Date(date.getTime());
        date1.setTime(date1.getTime() + 15*60*1000);
        System.out.println(date1);
        if (date1.after(nowDate)) {
            return true;
        }
        return false;
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

    public static Date parseString(String showTime) {
        try {
            return simpleDateFormat3.parse(showTime);
        } catch (ParseException e) {
            return new Date();
        }
    }
}
