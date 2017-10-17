package com.b2b.web.util;

import java.math.BigDecimal;
import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.b2b.common.domain.OrderItem;

public class NumberTool {

	static Logger logger = LoggerFactory.getLogger(NumberTool.class);

	public static String toYuanNumber(Object value){
		if(value==null)
			return "0.00";
		long count=0;
		boolean flag=false;
		if(value instanceof OrderItem[]){
			OrderItem[] orderItems=(OrderItem[])value;
			for (OrderItem orderItem : orderItems) {
				count+=orderItem.getFee();
			}
			flag=true;
		}else if(value instanceof Collection){
			@SuppressWarnings("unchecked")
			Collection<OrderItem> collection=(Collection<OrderItem>)value;

			for (OrderItem orderItem : collection) {
				count+=orderItem.getFee();
			}
			flag=true;
		}
		if(count==0 && flag)
			count=0;
		else {
			count=count==0?(Long)value:count;
		}
			BigDecimal val = new BigDecimal(count);
			val = val.divide(new BigDecimal(100));
			val=val.setScale(2, BigDecimal.ROUND_HALF_UP);
			return val.toString();
	}
	
	public static String integerToYuanNumber(Integer value){
			if(value==null)
			return "0";
			BigDecimal val = new BigDecimal(value);
			val = val.divide(new BigDecimal(100));
			val=val.setScale(2, BigDecimal.ROUND_HALF_UP);
			return val.toString();
	}


	public static Long toFenNumber(Long price){
		return price * 100;
	}

	public static Long str2Double2Fen(String price){
		BigDecimal val = new BigDecimal(price);

		//保留两位小数，向上取四舍五入   20.4587 -->  20.46
		val=val.setScale(2, BigDecimal.ROUND_HALF_UP);

		//20.46 -->  2046
		val = val.multiply(new BigDecimal(100));

		return val.longValue();
	}
	
	public static Long double2percent(BigDecimal val){
		//保留两位小数，向上取四舍五入   20.4587 -->  20.46
		val=val.setScale(2, BigDecimal.ROUND_HALF_UP);

		//20.46 -->  2046
		val = val.multiply(new BigDecimal(100));

		return val.longValue();
	}
	
}
