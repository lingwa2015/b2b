package com.b2b.web.wx.controller;

import com.b2b.common.domain.NotifyState;
import com.b2b.common.domain.PayFriends;
import com.b2b.common.domain.WXUser;
import com.b2b.common.domain.WXUserOrder;
import com.b2b.service.NotifyStateService;
import com.b2b.service.PayFriendsService;
import com.b2b.service.WXUserOrderService;
import com.b2b.service.WXUserService;
import com.b2b.web.util.CookieHelper;
import com.b2b.web.util.LWResult;
import com.b2b.web.util.NumberTool;
import com.b2b.web.wx.util.*;
import com.b2b.web.wx.util.pay.DOM4JParser;
import com.b2b.web.wx.util.pay.OrderUtil;
import com.b2b.web.wx.util.pay.WXPayUtil;
import com.b2b.web.wx.util.pay.WXPrepay;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/weixin")
public class WXFundraisingController {
	private static final Logger logger = LoggerFactory.getLogger(WXFundraisingController.class);
	private static String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URL&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect";
	private static final ObjectMapper MAPPER = new ObjectMapper();
	@Autowired
	private WXUserOrderService wxUserOrderService;
	
	@Autowired
	private PayFriendsService payFriendsService;
	
	@Autowired
	private Properties properties;
	
	@Autowired
	private WXPayUtil wxPayUtil;
	
	@Autowired
	private WXUserService wXUserService;
	
	@Autowired
	private NotifyStateService notifyStateService;
	
	@RequestMapping("/wxsaveGroup.do")
	@ResponseBody
	public LWResult pindan(HttpServletRequest request){
		try {
			String id = request.getParameter("id");
			String num = request.getParameter("num");
			String totalfee = request.getParameter("totalfee");
			String wxname = request.getParameter("wxname");
			String wxphone = request.getParameter("wxphone");
			String wxadress = request.getParameter("wxadress");
			String invoiceid = request.getParameter("invoiceid");
			String invoiceName = request.getParameter("invoiceName");
			WXUser wxUser = WXSessionHelper.getWxUser(request.getSession());
			String orderNo = OrderUtil.GetOrderNumber();
			WXUserOrder result = this.wxUserOrderService.save(id,num,totalfee,wxname,wxphone,wxadress,invoiceid,invoiceName,orderNo,wxUser.getId(),1);
			if(result!=null){
				return LWResult.ok(result.getId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return LWResult.build(201, "创建订单失败");
	}
	
	@RequestMapping("/wxgroup.htm")
	public ModelAndView pindan(HttpServletRequest request,RedirectAttributes attr,@RequestParam("id")String id){
		String openid = CookieHelper.getCookieValue(request, "token");
		if(null!=openid){
			WXUser wxUser = this.wXUserService.findByOpenId(openid);
			if(null!=wxUser && wxUser.getHeadImgurl()!=null){
				WXSessionHelper.setWxUser(request.getSession(), wxUser);
				attr.addAttribute("id", id);
				attr.addAttribute("wxUserId", wxUser.getId());
				//attr.addAttribute("sign", "1");
				return new ModelAndView("redirect:/weixin/wxgroupDetail.htm"); 
			}
		}
		String appid = properties.weixinAppid;
		String redirect_uri = "http://lingwa.com.cn/weixin/wxpindanOauth.htm";
		//String redirect_uri = "http://192.168.10.110:8080/weixin/wxpindanOauth.htm";
		String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
		String requestUrl = url.replace("APPID", appid).replace("REDIRECT_URL", urlEncode).replace("STATE", id);
		return new ModelAndView("redirect:"+requestUrl+"");  
		
	}
	
	@RequestMapping("/wxpindanOauth.htm")
	public ModelAndView pindanOauth(HttpServletRequest request,HttpServletResponse response,RedirectAttributes attr){
		String code = request.getParameter("code");
		String id = request.getParameter("state");
		String openId ="";
		String token = "";
		WXUser info = null;
		if (!"authdeny".equals(code)) {
			WeiXinOauth2Token weiXinOauth2Token = AdvancedUtil
					.getOauth2AccessToken(properties.weixinAppid,
							properties.weixinAppsecret, code);
			openId = weiXinOauth2Token.getOpenId();
			token = weiXinOauth2Token.getAccessToken();
			CookieHelper.setCookie(response, "token", openId);
		}
		WXUser wxUser = this.wXUserService.findByOpenId(openId);
		if(null==wxUser){
			info = AdvancedUtil.getWeixinUserInfo(token, openId);
			logger.warn("没有记录，---"+info.getHeadImgurl());
			wxUser = new WXUser();
			wxUser.setOpenid(openId);
			wxUser.setCity(info.getCity());
			wxUser.setCountry(info.getCountry());
			wxUser.setHeadImgurl(info.getHeadImgurl());
			wxUser.setNickName(info.getNickName());
			wxUser.setProvince(info.getProvince());
			wxUser.setSex(info.getSex());
			this.wXUserService.create(wxUser);
			WXSessionHelper.setWxUser(request.getSession(), wxUser);
		}else if (null == wxUser.getHeadImgurl()) {
			info = AdvancedUtil.getWeixinUserInfo(token, openId);
			logger.warn("有记录，没头像---"+info.getHeadImgurl());
			wxUser.setCity(info.getCity());
			wxUser.setCountry(info.getCountry());
			wxUser.setHeadImgurl(info.getHeadImgurl());
			wxUser.setNickName(info.getNickName());
			wxUser.setProvince(info.getProvince());
			wxUser.setSex(info.getSex());
			this.wXUserService.upadte(wxUser);
			WXSessionHelper.setWxUser(request.getSession(), wxUser);
		}else{
			WXSessionHelper.setWxUser(request.getSession(), wxUser);
		}
		attr.addAttribute("id", id);
		attr.addAttribute("wxUserId", wxUser.getId());
		return new ModelAndView("redirect:/weixin/wxgroupDetail.htm"); 
	}
	
	@RequestMapping("/wxgroupDetail.htm")
	public ModelAndView pindanDetail(HttpServletRequest request,RedirectAttributes attr,@RequestParam("id")Integer id,@RequestParam("wxUserId")Integer wxUserId){
//		String sign = request.getParameter("sign");
//		if(StringUtils.isEmpty(sign)){
			WXUser user = WXSessionHelper.getWxUser(request.getSession());
			if(null!=user && user.getId().equals(wxUserId)){
				ModelAndView view = new ModelAndView("wx/ping/group");
				try {
					HashMap<String, Object> map = this.wxUserOrderService.findpingdanInfoById(id);
					logger.warn("1----------------------------》"+user.getId()+"<------------------"+map.get("wxuserId").toString());
					if(Integer.valueOf(map.get("wxuserId").toString()).equals(user.getId())){
						view.addObject("flag", 1);
					}else{
						view.addObject("flag", 0);
					}
					List<PayFriends> payFriends = this.payFriendsService.findByOrderId(id);
					int paymoney = this.payFriendsService.findTotalMoney(id);
					int total = Integer.valueOf(map.get("snackpackage_total").toString());
					float percent=(float)paymoney/total;
					if(percent==0){
						view.addObject("percent", "0%");
					}else if (percent>=1) {
						view.addObject("percent", "100%");
						view.addObject("count", payFriends.size());
						long money = Long.valueOf(paymoney);
						view.addObject("paymoney", money);
					}else{
						NumberFormat nt = NumberFormat.getPercentInstance();
						nt.setMinimumFractionDigits(0);
						String format = nt.format(percent);
						view.addObject("percent", format);
					}
					view.addObject("info", map);
					view.addObject("payFriends", payFriends);
					long plus = (long)(total-paymoney);
					view.addObject("surplus", plus);
					Date date = (Date) map.get("created_time");
					long time1 = date.getTime();
					long time2 = System.currentTimeMillis();
					long millis = (time1-time2+86400000)/1000;
					if(millis>0){
						view.addObject("time", millis);
					}else{
						view.addObject("time", 0);
					}
					return view;
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				view.addObject("info", "");
				view.addObject("payFriends", "");
				view.addObject("percent", "");
				view.addObject("surplus", "");
				view.addObject("time", 0);
				return view;
			}else{
				attr.addAttribute("id", id);
				return new ModelAndView("redirect:/weixin/wxgroup.htm"); 
			}
//		}else{
//			WXUser user = WXSessionHelper.getWxUser(request.getSession());
//			ModelAndView view = new ModelAndView("wx/ping/group");
//			try {
//				HashMap<String, Object> map = this.wxUserOrderService.findpingdanInfoById(id);
//				if(Integer.valueOf(map.get("wxuserId").toString()).equals(user.getId())){
//					view.addObject("flag", 1);
//				}else{
//					view.addObject("flag", 0);
//				}
//				List<PayFriends> payFriends = this.payFriendsService.findByOrderId(id);
//				int paymoney = this.payFriendsService.findTotalMoney(id);
//				int total = Integer.valueOf(map.get("snackpackage_total").toString());
//				float percent=(float)paymoney/total;
//				if(percent==0){
//					view.addObject("percent", "0%");
//				}else if (percent>=1) {
//					view.addObject("percent", "100%");
//					view.addObject("count", payFriends.size());
//					long money = Long.valueOf(paymoney);
//					view.addObject("paymoney", money);
//				}else{
//					NumberFormat nt = NumberFormat.getPercentInstance();
//					nt.setMinimumFractionDigits(1);
//					String format = nt.format(percent);
//					view.addObject("percent", format);
//				}
//				view.addObject("info", map);
//				view.addObject("payFriends", payFriends);
//				long plus = (long)(total-paymoney);
//				view.addObject("surplus", plus);
//				Date date = (Date) map.get("created_time");
//				long time1 = date.getTime();
//				long time2 = System.currentTimeMillis();
//				long millis = (time1-time2+86400000)/1000;
//				if(millis>0){
//					view.addObject("time", millis);
//				}else{
//					view.addObject("time", 0);
//				}
//				return view;
//			} catch (NumberFormatException e) {
//				e.printStackTrace();
//			}
//			view.addObject("info", "");
//			view.addObject("payFriends", "");
//			view.addObject("percent", "");
//			view.addObject("surplus", "");
//			view.addObject("time", 0);
//			return view;
//		}
		
	}
	
//	@RequestMapping("/wxsupport.htm")
//	public ModelAndView pindan(@RequestParam("id")String id){
//		String appid = properties.weixinAppid;
//		String redirect_uri = "http://lingwa.com.cn/weixin/wxpindanOauth.htm";
//		//String redirect_uri = "http://192.168.10.110:8080/weixin/wxpindanOauth.htm";
//		String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
//		String requestUrl = url.replace("APPID", appid).replace("REDIRECT_URL", urlEncode).replace("STATE", id);
//		return new ModelAndView("redirect:"+requestUrl+"");  
//	}
//	
//	@RequestMapping("/wxpindanOauth.htm")
//	public ModelAndView pindanOauth(HttpServletRequest request,RedirectAttributes attr){
//		String code = request.getParameter("code");
//		String id = request.getParameter("state");
//		String openId ="";
//		WXUser info = null;
//		if (!"authdeny".equals(code)) {
//			WeiXinOauth2Token weiXinOauth2Token = AdvancedUtil
//					.getOauth2AccessToken(properties.weixinAppid,
//							properties.weixinAppsecret, code);
//			openId = weiXinOauth2Token.getOpenId();
//			Token token2 = CommonUtil.getToken(properties.weixinAppid, properties.weixinAppsecret);
//			info = AdvancedUtil.getWeixinUserInfo(token2.getAccessToken(), openId);
//		}
//		WXUser wxUser = this.wXUserService.findByOpenId(openId);
//		if(null==wxUser){
//			wxUser = new WXUser();
//			wxUser.setOpenid(openId);
//			wxUser.setCity(info.getCity());
//			wxUser.setCountry(info.getCountry());
//			wxUser.setHeadImgurl(info.getHeadImgurl());
//			wxUser.setNickName(info.getNickName());
//			wxUser.setProvince(info.getProvince());
//			wxUser.setSex(info.getSex());
//			this.wXUserService.create(wxUser);
//			WXSessionHelper.setWxUser(request.getSession(), wxUser);
//		}else if (null == wxUser.getHeadImgurl()) {
//			wxUser.setCity(info.getCity());
//			wxUser.setCountry(info.getCountry());
//			wxUser.setHeadImgurl(info.getHeadImgurl());
//			wxUser.setNickName(info.getNickName());
//			wxUser.setProvince(info.getProvince());
//			wxUser.setSex(info.getSex());
//			this.wXUserService.upadte(wxUser);
//			WXSessionHelper.setWxUser(request.getSession(), wxUser);
//		}else{
//			WXSessionHelper.setWxUser(request.getSession(), wxUser);
//		}
//		attr.addAttribute("id", id);
//		attr.addAttribute("wxUserId", wxUser.getId());
//		return new ModelAndView("redirect:/weixin/wxpingPay.htm"); 
//	}
//	
	@RequestMapping("wxpingPay.htm")
	public ModelAndView pingdanpay(HttpServletRequest request,RedirectAttributes attr,@RequestParam("id")Integer id){
		WXUser user = WXSessionHelper.getWxUser(request.getSession());
		if(null!=user && user.getHeadImgurl()!=null){
			ModelAndView view = new ModelAndView("wx/ping/pay");
			try {
				HashMap<String, Object> map = this.wxUserOrderService.findpingdanInfoById(id);
				int paymoney = this.payFriendsService.findTotalMoney(id);
				int total = Integer.valueOf(map.get("snackpackage_total").toString());
				float percent=(float)paymoney/total;
				if(percent==0){
					view.addObject("percent", "0%");
				}else if (percent>=1) {
					view.addObject("percent", "100%");
				}else{
					NumberFormat nt = NumberFormat.getPercentInstance();
					nt.setMinimumFractionDigits(1);
					String format = nt.format(percent);
					view.addObject("percent", format);
				}
				view.addObject("info", map);
				long plus = (long)(total-paymoney);
				view.addObject("surplus", plus);
				Date date = (Date) map.get("created_time");
				long time1 = date.getTime();
				long time2 = System.currentTimeMillis();
				long millis = (time1-time2+86400000)/1000;
				if(millis>0){
					view.addObject("time", millis);
				}else{
					view.addObject("time", 0);
				}
				return view;
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			view.addObject("info", "");
			view.addObject("percent", "");
			view.addObject("surplus", "");
			view.addObject("time", 0);
			return view;
		}else{
			attr.addAttribute("id", id);
			return new ModelAndView("redirect:/weixin/wxgroup.htm"); 
		}
	}
	
	@RequestMapping("wxpingPay.do")
	@ResponseBody
	public LWResult pingPay(@RequestParam("orderId")Integer orderId,@RequestParam("money")Long money,HttpServletRequest request){
		WXUser wxUser = WXSessionHelper.getWxUser(request.getSession());
		if(null==wxUser){
			return LWResult.build(201, "拼单失败");
		}
		if(null==money || null== orderId){
			return LWResult.build(202, "拼单失败");
		}
		String orderNo = OrderUtil.GetOrderNumber();
		orderNo = "PD"+orderNo;
		PayFriends payFriend = this.payFriendsService.save(money,orderId,orderNo,wxUser);
		if(null!=payFriend){
			String spbill_create_ip = request.getRemoteAddr();
			WXPrepay prePay = new WXPrepay();
			prePay.setAppid(properties.weixinAppid);
			prePay.setBody("零食包");
			prePay.setPartnerKey(properties.weixinAppsecret);
			prePay.setMch_id(properties.WX_MCH_ID);
			prePay.setNotify_url(properties.WX_PAY_PD_NOTIFY_URL);
			//时间戳生成的订单号
			prePay.setOut_trade_no(payFriend.getId());
			prePay.setSpbill_create_ip(spbill_create_ip);
			prePay.setTotal_fee(payFriend.getFee()+"");
			prePay.setTrade_type("JSAPI");
			prePay.setPartnerKey(properties.WX_PAY_PARTNERKRY);
			prePay.setOpenid(wxUser.getOpenid());
			// 此处添加获取openid的方法，获取预支付订单需要此参数！！！！！！！！！！！
			// 获取预支付订单号
			String prepayid = wxPayUtil.submitXmlGetPrepayId(prePay);
			logger.info("获取的预支付订单是：" + prepayid);
			if (prepayid != null && prepayid.length() > 10) {
				// 生成微信支付参数，此处拼接为完整的JSON格式，符合支付调起传入格式
				String jsParam = wxPayUtil.createPackageValue(
						prePay.getAppid(), prePay.getPartnerKey(), prepayid);
				System.out.println("jsParam=" + jsParam);
				// 此处可以添加订单的处理逻辑
				// model.addAttribute("jsParam", jsParam);
				logger.info("生成的微信调起JS参数为：" + jsParam);
				HashMap<String, Object> map = new HashMap<String, Object>();
				JsonNode jsonNode;
				try {
					jsonNode = MAPPER.readTree(jsParam);
					map.put("appId", jsonNode.path("appId").asText());
					map.put("nonceStr", jsonNode.path("nonceStr").asText());
					map.put("package", jsonNode.path("package").asText());
					map.put("paySign", jsonNode.path("paySign").asText());
					map.put("signType", jsonNode.path("signType").asText());
					map.put("timeStamp", jsonNode.path("timeStamp").asText());
					map.put("prepayid", prepayid);
					return LWResult.ok(map);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return LWResult.build(201, "创建订单失败");
	}
	
	@RequestMapping("/pd/wxnotify.do")
	public void test(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = DOM4JParser.getInputStream(request);
		String xmlStr = "";
		logger.info("------map:" + map);
		if (map.get("return_code").equals("SUCCESS")) {
			NotifyState notifyState = this.notifyStateService.findById(map.get("out_trade_no").toString());
			if(null!=notifyState && notifyState.getStatus() == 1){
				xmlStr = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
			}else{
				 HashMap<String,Object> data = this.payFriendsService.changePayStatus(map.get("out_trade_no").toString(),1);
				 Integer state = Integer.valueOf(data.get("state").toString());
				 String url = "http://lingwa.com.cn/weixin/wxgroup.htm?id="+data.get("id").toString();
				 String orderNo = data.get("orderNo").toString();
				 String openid = data.get("openid").toString();
				 String name = data.get("name").toString();
				 String frientName = data.get("frientName").toString();
				 long totalfee = Long.parseLong(data.get("total").toString());//订单总金额
				 long totalpay = Long.parseLong(data.get("pay").toString());//订单筹款总额
				 long pay = Long.parseLong(map.get("total_fee").toString());//本次支付金额
				 String youopenid = map.get("openid").toString();
				 long plus = totalfee-totalpay;
				 String remark = "";
				 if(state==1){
					 remark = "["+frientName+"]"+"为您支付了"+NumberTool.toYuanNumber(pay)+"元，您的筹款订单目前筹到的资金"+NumberTool.toYuanNumber(totalpay)+"元,恭喜您的订单筹款成功！";
				 }else{
					 
					 remark = "["+frientName+"]"+"为您支付了"+NumberTool.toYuanNumber(pay)+"元，您的筹款订单目前筹到的资金"+NumberTool.toYuanNumber(totalpay)+"元，还差"+NumberTool.toYuanNumber(plus)+"元即可完成订单支付";
				 }
				 NotifyUtil.pingdanin(url, "Z9w9z_3Q9uvCu02rEFG6e6jR4-VqduOWevRWmUY2n_o", openid, orderNo, NumberTool.toYuanNumber(totalfee), remark, properties.weixinAppid, properties.weixinAppsecret);
				 String remark2 = "您成功为好友"+name+"筹款了"+NumberTool.toYuanNumber(pay)+"元，TA一定会感激你的！";
				 NotifyUtil.pingdanout(url, "Z9w9z_3Q9uvCu02rEFG6e6jR4-VqduOWevRWmUY2n_o", youopenid, orderNo, NumberTool.toYuanNumber(totalfee), remark2, properties.weixinAppid, properties.weixinAppsecret);
				 logger.info("支付成功,订单号:" + map.get("out_trade_no").toString());
				xmlStr = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
			}
		}else{
			xmlStr = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
					+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
		}
		logger.info("xmlstr:" + xmlStr);
		PrintWriter writer = null;
		response.setContentType("text/xml");
		try {
			writer = response.getWriter();
			writer.flush();
			writer.println(xmlStr);// 告知微信已成功处理请求
		} catch (Exception e) {
			logger.error("异步回调失败"+e);
		}finally{
			writer.close();
		}
	}
	
	
}
