package com.bestsnail.utils;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * 作者：liang on 2016/5/18 15:12
 */
public class DateUtils {
    /**
     * 判断两个date类型的数据相差多少天
     * @param date1
     * @param date2
     * @return
     */
    public static int getBetweenDay(Date date1, Date date2) {
        Calendar d1 = new GregorianCalendar();
        d1.setTime(date1);
        Calendar d2 = new GregorianCalendar();
        d2.setTime(date2);
        int days = d1.get(Calendar.DAY_OF_YEAR) - d2.get(Calendar.DAY_OF_YEAR);
        return days;
    }
}
