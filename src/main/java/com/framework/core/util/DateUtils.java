package com.framework.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateUtils {
	public static String formatDate(Date date, String pattern) {
		if (date == null){
			return "";
		}
		if (pattern == null){
			pattern = "yyyy-MM-dd";
		}
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return (sdf.format(date));
	}
	public static String formatDateTime(Date date) {
		return (formatDate(date, "yyyy-MM-dd HH:mm:ss"));
	}

	public static String formatDateTime() {
		return (formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}

	public static String formatDate(Date date) {
		return (formatDate(date, "yyyy-MM-dd"));
	}
	public static String formatDate() {
		return (formatDate(new Date(), "yyyy-MM-dd"));
	}
	
	public static String formatDate2(Date date) {
		return (formatDate(date, "yyyyMMdd"));
	}

	public static String formatTime(Date date) {
		return (formatDate(date, "HH:mm:ss"));
	}

	public static String formatTime() {
		return (formatDate(new Date(), "HH:mm:ss"));
	}
	public static String formatTime2() {
		return (formatDate(new Date(), "HHmmss"));
	}
	
	public static Date getNow(){
		Calendar calendar = Calendar.getInstance();
		return calendar.getTime();
	}
	
    /**
     * Description: 默认String转Date，格式：yyyy-MM-dd HH:mm:ss <br/>
     */
    public static Date stringToDate(String str) {
        return stringToDate(str, "yyyy-MM-dd HH:mm:ss");
    }

    public static Date stringToDate(String str, String p) {
        if(str == null || "".equals(str)){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(p);
        try {
            return sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
	

