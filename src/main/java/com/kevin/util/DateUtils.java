package com.kevin.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {
    public DateUtils() {
    }

    public static String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    public static String getWeekOfDate(Date dt) {
        String[] weekDays = new String[]{"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(7) - 1;
        if (w < 0) {
            w = 0;
        }

        return weekDays[w];
    }

    public static String daysBetween(String startDate, String endDate) throws ParseException {
        long nd = 86400000L;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date newStartDate = sdf.parse(startDate);
        Date newEndDate = sdf.parse(endDate);
        long diff = newEndDate.getTime() - newStartDate.getTime();
        String day = diff / nd + "";
        return day;
    }
}
