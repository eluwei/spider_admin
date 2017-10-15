package com.junfly.water.utils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by bj on 2016/11/22.
 */
public class DateUtil {
    /**
     * 时间格式 yyyy-MM-dd HH:mm:ss
     */
    public static final String DATE_FORMAT_ONE = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_FORMAT_TWO = "yyyyMMddHHmmss";

    /**
     * 比较时间大小
     *
     * @param dateOne
     * @param dateTwo
     * @return true dateOne大于dateTwo  false dateOne小于等于dateTwo
     */
    public static boolean compareTime(Date dateOne, Date dateTwo) {
        if (dateOne.getTime() - dateTwo.getTime() <= 0) {
            return false;
        }
        return true;
    }

    /**
     * 字符串转Timestamp 字符串格式必须为 "2011-05-09 11:49:45"
     *
     * @param str
     * @return
     */
    public static Timestamp strToTimestamp(String str) {
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        try {
            ts = Timestamp.valueOf(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ts;
    }

    /**
     * 返回 yyyyMMddHHmmss格式字符串
     * @return
     */
    public static String getDateTime(){
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        return format.format(new Date());
    }


    /**
     * 日期转化成字符串
     * @param time
     * @param dateFormat
     * @return
     */
    public static String dateToString(Date time, String dateFormat) {
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(time);
    }

    /**
     * 字符串转化成时间
     * @param timeStr
     * @param dateFormat
     * @return
     */
    public static Date stringToDate(String timeStr, String dateFormat) {
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat(dateFormat);
        Date date = null;
        try {
            date = formatter.parse(timeStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 时间转换
     *
     * @param myDate
     * @return
     */
    public static String timeValueOf(Date myDate) {
        SimpleDateFormat myformat = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        String datetime = myformat.format(new Date());
        String timeval = "";
        try {
            Date date = myformat.parse(datetime);
            long time = (date.getTime() - myDate.getTime()) / (24 * 60 * 60 * 1000);
            if (time == 0) {
                timeval = "今天" + "\n" + format.format(myDate);
            } else if (time == 1) {
                timeval = "昨天" + "\n" + format.format(myDate);
            } else if (time == 2) {
                timeval = "前天" + "\n" + format.format(myDate);
            } else {
                timeval = sdf.format(myDate) + "\n" + format.format(myDate);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return timeval;
    }

    public static String dateToStr(Date date)
    {
        return dateToStr(date, null);
    }

    public static String dateToStr(Date date, String aMask)
    {
        String ret = null;
        String mask = aMask;
        if (mask == null || "".equals(mask))
            mask = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(mask);
        ret = sdf.format(date);
        return ret;
    }


    public static Date strToDate(String date)
    {
        return strToDate(date, null);
    }

    public static Date strToDate(String date, String aMask)
    {
        Date ret = null;
        String mask = aMask;
        if (mask == null || "".equals(mask))
            mask = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(mask);
        try
        {
            ret = sdf.parse(date);
        }
        catch (ParseException e)
        {
            e.printStackTrace();
        }
        return ret;
    }

    public static String lpadnum(int num, int length)
    {
        int len = String.valueOf(num).length();
        StringBuffer str = new StringBuffer("");
        if (len < length)
        {
            for (int i = 0; i < length - len; i++)
                str.append("0");

            return str.append(num).toString();
        } else
        {
            return String.valueOf(num);
        }
    }

    public static Calendar getToday()
    {
        return new GregorianCalendar();
    }

    public static String getYear()
    {
        Calendar calendar = getToday();
        return String.valueOf(calendar.get(1));
    }

    public static String getMonth()
    {
        Calendar calendar = getToday();
        int month = calendar.get(2) + 1;
        return lpadnum(month, 2);
    }

    public static String getDay()
    {
        Calendar calendar = getToday();
        return lpadnum(calendar.get(5), 2);
    }

    public static String getHour()
    {
        Calendar calendar = getToday();
        return lpadnum(calendar.get(11), 2);
    }

    public static String getMinute()
    {
        Calendar calendar = getToday();
        return lpadnum(calendar.get(12), 2);
    }

    public static String getSecond()
    {
        Calendar calendar = getToday();
        return lpadnum(calendar.get(13), 2);
    }

    public static String getDate()
    {
        return (new StringBuilder(String.valueOf(getYear()))).append("-").append(getMonth()).append("-").append(getDay()).toString();
    }

    public static String getDate(String aMask)
    {
        String ret = null;
        String mask = aMask;
        if (mask == null || "".equals(mask))
            mask = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(mask);
        ret = sdf.format(Calendar.getInstance().getTime());
        return ret;
    }

    public static String getDateWithTime()
    {
        return (new StringBuilder(String.valueOf(getDate()))).append(" ").append(getHour()).append(":").append(getMinute()).append(":").append(getSecond()).toString();
    }

    /**
     * 添加时间计算
     * @param date
     * @param hour
     * @return
     */
    public static String addMinute(String date,int minute){
        Calendar ca = Calendar.getInstance();
        ca.setTime(DateUtil.strToDate(date, "yyyy-MM-dd HH:mm:ss"));
        ca.add(Calendar.MINUTE,minute);
        return dateToStr(ca.getTime(),"yyyy-MM-dd HH:mm:ss");
    }
}
