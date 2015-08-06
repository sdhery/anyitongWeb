package com.framework.common.util;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.lang.time.FastDateFormat;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * ��Ŀ���ƣ�anyitongWeb
 * �����ƣ�
 * ��������
 * �����ˣ�Administrator
 * ����ʱ�䣺2015/7/3 12:36
 * �޸��ˣ�Administrator
 * �޸�ʱ�䣺2015/7/3 12:36
 * �޸ı�ע��
 */
public class DateUtil extends DateUtils {
    public static final String FORMAT_LONGDATETIME = "yyyy-MM-dd HH:mm:ss.SSS";
    public static final String FORMAT_SHORTDATETIME = "yyyy-MM-dd";
    public static final String FORMAT_LONGDATETIME_COMPACT = "yyyyMMddHHmmssSSS";
    public static final String FORMAT_SHORTDATETIME_COMPACT = "yyyyMMdd";

    /**
     * ���ַ������date����
     *
     * @param dateString
     * @param format
     * @return
     */
    public static Date getDateFormat(String dateString, String format) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        try {
            return simpleDateFormat.parse(dateString);
        } catch (ParseException e) {
            return null;
        }
    }

    public static final String convertDateToString(java.util.Date aDate, String pattern) {
        return aDate == null ? null : new SimpleDateFormat(pattern).format(aDate);
    }

    public static java.util.Date getDayEndTime(java.util.Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return getDayEndTime(calendar);
    }

    public static java.util.Date getDayEndTime(String date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(convertStringToDateTime("yyyy-MM-dd", date));
        return getDayEndTime(calendar);
    }

    public static java.util.Date convertStringToDateTime(String datePattern, String strDateTime) throws ParseException {
        return new java.util.Date(new SimpleDateFormat(datePattern).parse(strDateTime).getTime());
    }

    public static java.util.Date getDayEndTime(Calendar calendar) {
        Calendar tmpCalendar = (Calendar) calendar.clone();
        tmpCalendar.set(11, 0);
        tmpCalendar.set(12, 0);
        tmpCalendar.set(13, 0);
        tmpCalendar.set(14, 0);

        tmpCalendar.add(5, 1);
        tmpCalendar.add(14, -1);
        return tmpCalendar.getTime();
    }

    public static java.util.Date getDayStartTime(String date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(convertStringToDateTime("yyyy-MM-dd", date));
        return getDayStartTime(calendar);
    }

    public static java.util.Date getDayStartTime(Calendar calendar) {
        Calendar tmpCalendar = (Calendar) calendar.clone();
        tmpCalendar.set(11, 0);
        tmpCalendar.set(12, 0);
        tmpCalendar.set(13, 0);
        tmpCalendar.set(14, 0);
        return tmpCalendar.getTime();
    }

    /**
     * ��ȡ����ʱ���ڵ�����ʱ��
     * �磺2013-9-13 00::00:00
     * @param day
     * @return
     */
    public static Date getTheDayStart(Date day){
        if(day == null){
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * ��ȡ����ʱ���ڵ�������ʱ��
     * �磺2013-9-13 23::59:59
     * @param day
     * @return
     */
    public static Date getTheDayEnd(Date day){
        if(day == null){
            return null;
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(day);
        calendar.set(Calendar.HOUR, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return new Date(calendar.getTimeInMillis());
    }

    /**
     * //�жϵ�ǰ�����������ڼ�
     * ����ֵΪ 1��2��3��4��5��6��7
     *
     * @param time
     * @return
     */
    public static int getWeekForTime(long time) {
        Date newDate = new Date(time);
        Calendar cal = Calendar.getInstance();
        cal.setTime(newDate);
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 0) week = 7;
        return week;
    }

    /**
     * �жϵ�ǰ�����������ڼ�
     * ����ֵΪ һ�����������ġ��塢������
     *
     * @param longDate
     * @return
     */
    public static String getWeekStrForTime(String longDate) {
        long time = Long.parseLong(longDate);
        int week = getWeekForTime(time);
        String weekValue = "";
        if (week == 1) {
            weekValue = "һ";
        } else if (week == 2) {
            weekValue = "��";
        } else if (week == 3) {
            weekValue = "��";
        } else if (week == 4) {
            weekValue = "��";
        } else if (week == 5) {
            weekValue = "��";
        } else if (week == 6) {
            weekValue = "��";
        } else if (week == 7) {
            weekValue = "��";
        }
        return weekValue;
    }

    /**
     * �õ�����ϵͳʱ��
     */
    public static long getNowTime() {
        return System.currentTimeMillis();
    }

    public static String convertHHmmString(String hhMM){
        return hhMM.substring(0,2)+":"+hhMM.substring(2,4);
    }

    /**
     * ����yyyy-MM-dd HH:mm:ss��ʽ������ʱ���ַ���
     * ���쳣�򷵻ؿ��ַ���
     */
    public static String getLongDate(Timestamp d) {
        String s = "";
        try {
            s = FastDateFormat.getInstance(FORMAT_LONGDATETIME).format(d);
        } catch (Exception e) {
        }
        return s;
    }
}
