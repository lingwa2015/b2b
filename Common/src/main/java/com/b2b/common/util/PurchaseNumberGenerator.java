package com.b2b.common.util;


import com.b2b.Constant;
import com.b2b.service.PurchaseItemService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * 订单号生成器
 * 
 * @Author Ming.Zi
 * @Date Feb 9, 2015
 * @Version 1.0
 */
public class PurchaseNumberGenerator {

	private static final String DATE_FORMAT_YMDNOT = "yyyyMMdd";


	public static void main(String[] args) {
//		System.out.println(buildOrderNo(new Date(), 12));
	}

	public static String getPurchaseId (String purchaseId) {
		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		date = calendar.getTime();
		String time = DateUtil.formatDate(date, DATE_FORMAT_YMDNOT);
		if (StringUtils.isNotBlank(purchaseId)) {
			if (purchaseId.length() > 8 && time.equals(purchaseId.substring(0, 8))) {
				Integer count = Integer.valueOf(purchaseId.substring(8, 10)) + 1;
				String countStr = (count + "").length() == 1 ? "0" + count : count + "";
				purchaseId = time + countStr;
			} else {
				purchaseId = time + "01";
			}
		} else {
			purchaseId = time + "01";
		}

		return purchaseId;
	}

}

