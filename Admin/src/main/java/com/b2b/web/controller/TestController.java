package com.b2b.web.controller;

import com.b2b.job.*;
import com.b2b.service.ItemCategoryService;
import com.b2b.service.ItemScoreService;
import com.b2b.service.ItemService;
import com.b2b.service.ShopRefundService;
import com.b2b.web.util.ApiService;
import com.b2b.web.util.MD5Util;
import com.b2b.web.wx.util.CommonUtil;
import com.b2b.web.wx.util.Properties;
import com.b2b.web.wx.util.Sha1;
import com.b2b.web.wx.util.Token;
import com.b2b.web.wx.util.pay.OrderUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.security.DigestException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("weixin")
public class TestController {
	@Autowired
	ExpireAutoRefund expireAutoRefund;

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Autowired
	AutoSalesmanPerformance autoSalesmanPerformance;

	@Autowired
	GenTranSumJob genTranSumJob;

	@Autowired
	ShopRefundService shopRefundService;

	@Autowired
	MonthShopStockMoney monthShopStockMoney;

	@Autowired
	ApiService apiService;

	@Autowired
	ItemCategoryService itemCategoryService;

	@Autowired
	ItemService itemService;
	
	@Autowired
	ItemScoreService itemScoreService;
	
	@Autowired
	CustomerLastOrderTime customerLastOrderTime;
	
	@Autowired
	Properties properties;
	
	@Autowired
	StockMoneyMonitor stockMoneyMonitor;
	
	@RequestMapping("wxtest1.htm")
	public ModelAndView weizhi() throws DigestException{
	   String jsticket = "kgt8ON7yVITDhtdwci0qeSJWVF_R6bW-YDFAB1r_MorzvUelFKGCBMgU82DT_uXHW4Td0p744xYqyFWUQDYNog";
	   String temp = "1234567890";
	   String noncestr = "9876543210";
	   String url = "http://192.168.10.117:8080/weixin/wxtest1.htm";
	   Map<String, Object> map = new HashMap<String, Object>();
	   map.put("jsapi_ticket", jsticket);
	   map.put("noncestr", noncestr);
	   map.put("timestamp", temp);
	   map.put("url", url);
	   String sha1 = Sha1.SHA1(map);
	   
	   ModelAndView view = new ModelAndView("weixinTest/test");
	   view.addObject("sha1", sha1);
	   view.addObject("temp", temp);
	   view.addObject("noncestr", noncestr);
	   view.addObject("appid", "wxe3bec87d823a22fc");
	   return view;
	}
//	
//	@RequestMapping("wxtest2.htm")
//	public void test2(){
//		this.genTranSumJob.workmonthReport1();
//	}
//	
//	@RequestMapping("wxtest1.htm")
//	public void test1(){
//		this.genTranSumJob.workDailyReport1();
//	}
//	
//	@RequestMapping("wxtest3.htm")
//	public void test3(){
//		this.genTranSumJob.workShopDailyReport1();
//	}
//	
//	@RequestMapping("wxtest4.htm")
//	public void test4(){
//		this.genTranSumJob.workShopMonthReport1();
//	}
//	
//	@RequestMapping("wxtest5.htm")
//	public void test5(){
//		this.genTranSumJob.workShopWeekReport1();
//	}
//	
//	@RequestMapping("wxtest6.htm")
//	public void test6(){
//		this.genTranSumJob.workWeekReport1();
//	}
//	
//	@RequestMapping("wxtest7.htm")
//	public void test7(){
//		this.genTranSumJob.workFreeShopMonthReport1();
//	}
//	
//	@RequestMapping("wxtest8.htm")
//	public void test8(){
//		this.genTranSumJob.workFreeMonthReport1();
//	}
//	
//	@RequestMapping("wxtest9.htm")
//	public void test9(){
//		this.customerLastOrderTime.updateLastOrderTime();
//	}
//	
//	
//	@RequestMapping("wxtest10.htm")
//	public void test10(){
//		this.genTranSumJob.workFreeShopWeekReport1();
//	}
//	
//	@RequestMapping("wxtest11.htm")
//	public void test11(){
//		this.genTranSumJob.workFreeWeekReport1();
//	}
//	
//	@RequestMapping("wxtest12.htm")
//	public void test12(){
//		this.genTranSumJob.workFreeDayReport1();
//	}
	
	@RequestMapping("wxtest.htm")
	public void test(){
		this.genTranSumJob.workconsume();
	}
	
	@RequestMapping("wxtest2.htm")
	public void test2(@RequestParam("date")String date){
		this.genTranSumJob.workFreeShopDayReport2(date);
	}
	@RequestMapping("wxtest5.htm")
	public void test5(){
		this.genTranSumJob.workShopMonthReport();
	}
	
	@RequestMapping("/wxtest10.htm")
	public ModelAndView sure(HttpServletRequest request){
		String timeStamp = OrderUtil.GetTimestamp();
		Random random = new Random();
		String nonceStr = DigestUtils.md5Hex(String.valueOf(random.nextInt(10000))).toUpperCase();
		Token token = CommonUtil.getToken(properties.weixinAppid, properties.weixinAppsecret);
		Token ticket = CommonUtil.getJsapiTicket(token.getAccessToken());
		String string1 = "jsapi_ticket="+ticket.getAccessToken()+"&noncestr="+nonceStr+"&timestamp="+timeStamp+"&url="+request.getRequestURL();
		if(request.getQueryString()!=null) //判断请求参数是否为空  
			string1+="?"+request.getQueryString();
		 String sha1 = MD5Util.SHA1(string1);
		 ModelAndView view2 = new ModelAndView("weixinTest/sure");
		 view2.addObject("appId", properties.weixinAppid);
		 view2.addObject("timestamp", timeStamp);
		 view2.addObject("nonceStr", nonceStr);
		 view2.addObject("signature", sha1);
		 return view2;
	}
	
	/**
	      * 汉语中数字大写
	      */
	     private static final String[] CN_UPPER_NUMBER = { "零", "壹", "贰", "叁", "肆",
	             "伍", "陆", "柒", "捌", "玖" };
	     /**
	      * 汉语中货币单位大写，这样的设计类似于占位符
	      */
	     private static final String[] CN_UPPER_MONETRAY_UNIT = { "分", "角", "元",
	             "拾", "佰", "仟", "万", "拾", "佰", "仟", "亿", "拾", "佰", "仟", "兆", "拾",
	             "佰", "仟" };
	     /**
	      * 特殊字符：整
	      */
	     private static final String CN_FULL = "整";
	     /**
	      * 特殊字符：负
	      */
	     private static final String CN_NEGATIVE = "负";
	     /**
	      * 金额的精度，默认值为2
	      */
	     private static final int MONEY_PRECISION = 2;
	     /**
	      * 特殊字符：零元整
	      */
	     private static final String CN_ZEOR_FULL = "零元" + CN_FULL;
	
	     /**
	      * 把输入的金额转换为汉语中人民币的大写
	      * 
	      * @param numberOfMoney
	      *            输入的金额
	      * @return 对应的汉语大写
	      */
	     public static String number2CNMontrayUnit(BigDecimal numberOfMoney) {
	         StringBuffer sb = new StringBuffer();
	         // -1, 0, or 1 as the value of this BigDecimal is negative, zero, or
	         // positive.
	         int signum = numberOfMoney.signum();
	         // 零元整的情况
	         if (signum == 0) {
	             return CN_ZEOR_FULL;
	         }
	         //这里会进行金额的四舍五入
	         long number = numberOfMoney.movePointRight(MONEY_PRECISION)
	                 .setScale(0, 4).abs().longValue();
	         // 得到小数点后两位值
	         long scale = number % 100;
	         int numUnit = 0;
	         int numIndex = 0;
	         boolean getZero = false;
	         // 判断最后两位数，一共有四中情况：00 = 0, 01 = 1, 10, 11
	         if (!(scale > 0)) {
	             numIndex = 2;
	             number = number / 100;
	             getZero = true;
	         }
	         if ((scale > 0) && (!(scale % 10 > 0))) {
	             numIndex = 1;
	             number = number / 10;
	             getZero = true;
	         }
	         int zeroSize = 0;
	         while (true) {
	             if (number <= 0) {
	                 break;
	             }
	             // 每次获取到最后一个数
	             numUnit = (int) (number % 10);
	             if (numUnit > 0) {
	                 if ((numIndex == 9) && (zeroSize >= 3)) {
	                     sb.insert(0, CN_UPPER_MONETRAY_UNIT[6]);
	                 }
	                 if ((numIndex == 13) && (zeroSize >= 3)) {
	                     sb.insert(0, CN_UPPER_MONETRAY_UNIT[10]);
	                 }
	                 sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
	                 sb.insert(0, CN_UPPER_NUMBER[numUnit]);
	                 getZero = false;
	                 zeroSize = 0;
	             } else {
	                 ++zeroSize;
	                 if (!(getZero)) {
	                     sb.insert(0, CN_UPPER_NUMBER[numUnit]);
	                 }
	                 if (numIndex == 2) {
	                     if (number > 0) {
	                         sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
	                     }
	                 } else if (((numIndex - 2) % 4 == 0) && (number % 1000 > 0)) {
	                     sb.insert(0, CN_UPPER_MONETRAY_UNIT[numIndex]);
	                 }
	                 getZero = true;
	             }
	             // 让number每次都去掉最后一个数
	             number = number / 10;
	             ++numIndex;
	         }
	         // 如果signum == -1，则说明输入的数字为负数，就在最前面追加特殊字符：负
	         if (signum == -1) {
	             sb.insert(0, CN_NEGATIVE);
	         }
	         // 输入的数字小数点后两位为"00"的情况，则要在最后追加特殊字符：整
	         if (!(scale > 0)) {
	             sb.append(CN_FULL);
	         }
	         return sb.toString();
	     }

	public static ModelAndView getMenuPoint(ModelAndView mv, HttpServletRequest request) {
		String menuHeight = request.getParameter("menuHeight");
		String menuName = request.getParameter("menuName");
		HttpSession session = request.getSession();
		if (menuHeight == null) {
			if (session.getValue("menuName") != null) {
				menuName = session.getValue("menuName").toString();
				menuHeight = session.getValue("menuHeight").toString();
			}
		} else {
			session.setAttribute("menuName", menuName);
			session.setAttribute("menuHeight", menuHeight);
		}
		mv.addObject("menuHeight", menuHeight);
		mv.addObject("menuName", menuName);
		return mv;
	}


	public static void main(String[] args){
		String realUrl = "/bsmall/bs/index.htm";
		String methodName = realUrl.substring(1,realUrl.indexOf("/", 1));
		System.out.println(methodName);
	}
	
}
