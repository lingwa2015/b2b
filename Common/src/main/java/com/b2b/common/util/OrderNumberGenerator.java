package com.b2b.common.util;


import java.text.SimpleDateFormat;
import java.util.Date;


import com.b2b.Constant;


/**
 * 订单号生成器
 * 
 * @Author Ming.Zi
 * @Date Feb 9, 2015
 * @Version 1.0
 */
public class OrderNumberGenerator {

	public static void main(String[] args) {
		System.out.println(buildOrderNo(new Date(), 12));
	}

	public static String buildOrderNo(Date createDate, Integer identify) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
		StringBuilder orderNo = new StringBuilder(dateFormat.format(createDate));
		long count = System.currentTimeMillis();
		orderNo.append(identify).append(count);
		return orderNo.toString();
	}
	
	public static String buildDebitNoteNo(Date createDate, Integer identify) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHH");
		StringBuilder debitNoteNo = new StringBuilder(dateFormat.format(createDate));
		long count = System.currentTimeMillis();
		debitNoteNo.append("-"+identify).append("-"+count);
		return debitNoteNo.toString();
	}
	
	public static String buildOrderNo(Date createDate, Integer identify, String source) {
		if (Constant.FROM_APP.equalsIgnoreCase(source)) {
			return "App" + buildOrderNo(createDate, identify);
		}

		return buildOrderNo(createDate, identify);
	}
	
}

