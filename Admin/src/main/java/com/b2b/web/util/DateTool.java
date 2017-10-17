package com.b2b.web.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.b2b.common.util.DateUtil;

public class DateTool {
	public static String dateFormat(Date d,String dateParrten){
		if(d == null) return "";
		SimpleDateFormat sdf = new SimpleDateFormat(dateParrten);
		return sdf.format(d);
	}
	
	public static boolean checkDate(Date d){
		Calendar cal = Calendar.getInstance();
		cal.set(cal.HOUR_OF_DAY, 23);
		cal.set(cal.MINUTE, 59);
		cal.set(cal.SECOND, 59);
		return cal.getTime().before(d); 
	}
	
	public static String dateFormat(Date d){
		return dateFormat(d,"yyyy-MM-dd");
	}
	
	public static List<Date> processDateSection(String dateStr){
		List<Date> result = new ArrayList<Date>();
		
		Date dateTime = DateUtil.parseDateStr(dateStr, "yyyy-MM-dd");
		Calendar startCal = Calendar.getInstance();
		Calendar endCal = Calendar.getInstance();
		startCal.setTime(dateTime);
		endCal.setTime(dateTime);
		startCal.set(Calendar.HOUR_OF_DAY, 0);
		startCal.set(Calendar.SECOND, 0);
		startCal.set(Calendar.MINUTE, 0);
		
		endCal.set(Calendar.HOUR_OF_DAY, 23);
		endCal.set(Calendar.SECOND, 59);
		endCal.set(Calendar.MINUTE, 59);
		
		result.add(startCal.getTime());
		result.add(endCal.getTime());
		
		return result;
	}
}
