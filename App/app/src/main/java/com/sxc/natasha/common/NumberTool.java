package com.sxc.natasha.common;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class NumberTool {
	
	public static String toYuanNumber(Long price){
		if(price == null) return "0.0";
        DecimalFormat df = new DecimalFormat("#.00");
		BigDecimal val = new BigDecimal(price);
		val = val.divide(new BigDecimal(100));
		return  df.format(val).toString();
	}
	
	public static Long toFenNumber(Long price){
		return price * 100;
	}
	
	/**
     * str转换为double再转换为分－工具方法
     * 
     * @author zhengshutian
     * 
     */
	public static Long str2Double2Fen(String price){
		BigDecimal val = new BigDecimal(price);
		
		//保留两位小数，向上取四舍五入   20.4587 -->  20.46
		val=val.setScale(2, BigDecimal.ROUND_HALF_UP);
		
		//20.46 -->  2046
		val = val.multiply(new BigDecimal(100));
		
		return val.longValue();
	}
	
	/**
     * str转换为double再转换为分－工具方法
     * 
     * @author zhengshutian
     * 
     */
	public static String date2Str(Date time){
		
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String date="";
		try{
			date	 = df.format(time);
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return date;
	}

}
