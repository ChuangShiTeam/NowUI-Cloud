package com.nowui.cloud.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

/**
 * @author ZhongYongQiang
 */
public class DateUtil {

	public static String getDateTimeString(Date dateTime) {
		if (dateTime == null) {
			return null;
		}

		SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return dateTimeFormat.format(dateTime);
	}
	
	public static Date parseDate(String dateTime) {
        if (Util.isNullOrEmpty(dateTime)) {
            return null;
        }

        SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateTimeFormat.parse(dateTime);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

	public static String getTodayStartDateTimeString() {
		return DateFormatUtils.format(new Date(), "yyyy-MM-dd 00:00:00");
	}
	
	/**
	 * 获取当日00:00:00 时间点
	 * 
	 * @return Date 当日00:00:00时间点
	 */
	public static Date getTodayStartDateTime() {
    	Calendar calendar1 = Calendar.getInstance();
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        return calendar1.getTime();
	}

	/**
	 * 获取当日23:59:59 时间点
	 * 
	 * @return Date 当日23:59:59时间点
	 */
	public static Date getTodayEndDateTime() {
    	Calendar calendar1 = Calendar.getInstance();
        calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH), 23, 59, 59);
        return calendar1.getTime();
	}
	
	/**
	 * 获取距今两个月前的时间点
	 * 
	 * @return Date 距今两个月前的时间点
	 */
	public static Date getTwoMonthAgoDateTime() {
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.MONTH, curr.get(Calendar.MONTH) - 2);
		return curr.getTime();
	}
	
	public static void main(String args[]) {
//	    Calendar calendar1 = Calendar.getInstance();
//        calendar1.add(Calendar.MINUTE, -10);
//        System.out.println(DateUtil.getDateTimeString(calendar1.getTime()));
		
//		Calendar date = Calendar.getInstance();
//        Date d = new Date();
//        date.setTime(d);
//        int td = date.get(Calendar.DATE);
//        date.set(Calendar.DATE, td+1);      
//        System.out.println(date.getTime());
		
		Calendar curr = Calendar.getInstance();
		curr.set(Calendar.MONTH, curr.get(Calendar.MONTH) + 2);
		Date date = curr.getTime();
		System.out.println(getDateTimeString(date));
	}

}
