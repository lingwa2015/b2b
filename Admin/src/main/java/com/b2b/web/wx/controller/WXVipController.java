package com.b2b.web.wx.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.NotifyState;
import com.b2b.common.domain.Order;
import com.b2b.common.domain.StandardOrder;
import com.b2b.common.domain.SysLog;
import com.b2b.common.domain.WXAccount;
import com.b2b.common.domain.WXRechargeRecord;
import com.b2b.common.domain.WXUser;
import com.b2b.common.domain.WXUserAddress;
import com.b2b.common.domain.WXUserInvoice;
import com.b2b.common.domain.WXUserOrder;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.service.CustomerService;
import com.b2b.service.LogService;
import com.b2b.service.NotifyStateService;
import com.b2b.service.OrderService;
import com.b2b.service.WXAccountService;
import com.b2b.service.WXRechargeRecordService;
import com.b2b.service.WXUserService;
import com.b2b.web.util.LWResult;
import com.b2b.web.util.NumberTool;
import com.b2b.web.util.SessionHelper;
import com.b2b.web.wx.util.AdvancedUtil;
import com.b2b.web.wx.util.CommonUtil;
import com.b2b.web.wx.util.NotifyUtil;
import com.b2b.web.wx.util.Properties;
import com.b2b.web.wx.util.WXSessionHelper;
import com.b2b.web.wx.util.WeiXinOauth2Token;
import com.b2b.web.wx.util.pay.DOM4JParser;
import com.b2b.web.wx.util.pay.OrderUtil;
import com.b2b.web.wx.util.pay.WXPayUtil;
import com.b2b.web.wx.util.pay.WXPrepay;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/weixin")
public class WXVipController {
	private static final Logger LOGGER = LoggerFactory.getLogger(WXVipController.class);
	private static String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URL&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	private static final ObjectMapper MAPPER = new ObjectMapper();
	@Autowired
	private Properties properties;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private WXAccountService wxAccountService;
	
	@Autowired
	private WXUserService wxUserService;
	
	@Autowired
	private WXRechargeRecordService wxRechargeRecordService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private WXPayUtil wxPayUtil;

	@Autowired
	private NotifyStateService notifyStateService;
	
	@Autowired
	LogService logService;
	
	@RequestMapping("wxaccount.htm")
	public ModelAndView list(HttpServletRequest request){
		WXUser wxUser = WXSessionHelper.getWxUser(request.getSession());
		//WXUser wxUser = new WXUser();
		if(null!=wxUser){
			ModelAndView view = new ModelAndView("wx/vip/index");
			if(null!=wxUser.getCustomerUserId()){
				CustomerUser user = this.customerService.findByIdAndState(wxUser.getCustomerUserId());
				WXAccount account = this.wxAccountService.findVipBycid(wxUser.getCustomerUserId());
				view.addObject("company", user.getCompanyName());
				view.addObject("account", account.getMoney());
				view.addObject("status", 1);
			}else{
				view.addObject("status", 2);
			}
			return view;
		}else{
			String appid = properties.weixinAppid;
			String redirect_uri = "http://lingwa.com.cn/weixin/wxreaccount.htm";
			//String redirect_uri = "http://192.168.10.110:8080/weixin/wxreaccount.htm";
			String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
			String requestUrl = url.replace("APPID", appid).replace("REDIRECT_URL", urlEncode).replace("STATE", "1");
			return new ModelAndView("redirect:"+requestUrl+"");          
		}
	}
	
	@RequestMapping("wxreaccount.htm")
	public ModelAndView relist(HttpServletRequest request){
		ModelAndView view = new ModelAndView("wx/vip/index");
		WXUser wxUser = WXSessionHelper.getWxUser(request.getSession());
		if(null==wxUser){
			String code = request.getParameter("code");
			String openId ="";
			if (!"authdeny".equals(code)) {
				WeiXinOauth2Token weiXinOauth2Token = AdvancedUtil
						.getOauth2AccessToken(properties.weixinAppid,
								properties.weixinAppsecret, code);
				openId = weiXinOauth2Token.getOpenId();
			}
			wxUser = this.wxUserService.findByOpenId(openId);
			if(null==wxUser){
				wxUser = new WXUser();
				wxUser.setOpenid(openId);
				this.wxUserService.create(wxUser);
				WXSessionHelper.setWxUser(request.getSession(), wxUser);
			}else{
				WXSessionHelper.setWxUser(request.getSession(), wxUser);
			}
		}
		if(null!=wxUser.getCustomerUserId()){
			CustomerUser user = this.customerService.findById(wxUser.getCustomerUserId());
			WXAccount account = this.wxAccountService.findVipBycid(wxUser.getCustomerUserId());
			view.addObject("company", user.getCompanyName());
			view.addObject("account", account.getMoney());
			view.addObject("status", 1);
		}else{
			view.addObject("status", 2);
		}	
		return view;
	}
	
	@RequestMapping("wxagreement.htm")
	public ModelAndView agreement(HttpServletRequest request){
		ModelAndView view = new ModelAndView("wx/vip/agreement");
		return view;
	}
	
	@RequestMapping("wxagree.htm")
	public ModelAndView agreeMent(HttpServletRequest request){
		ModelAndView view = new ModelAndView("wx/vip/agree");
		return view;
	}
	
	@RequestMapping("wxadd.htm")
	public ModelAndView add(HttpServletRequest request){
		ModelAndView view = new ModelAndView("wx/vip/add");
		return view;
	}
	
	@RequestMapping("wxadd.do")
	@ResponseBody
	public ModelAndView save(HttpServletRequest request){
		String companyName = request.getParameter("companyName");
		String name = request.getParameter("name");
		String position = request.getParameter("position");
		String phone = request.getParameter("phone");
		WXUser wxUser = WXSessionHelper.getWxUser(request.getSession());
		if(null!=wxUser){
				CustomerUser user = new CustomerUser();
				user.setUserName(companyName+"(vip)");
				user.setCompanyName(companyName);
				user.setLikeman(name);
				user.setIswxvip(2);
				user.setPosition(position);
				user.setCreatedTime(new Date());
				user.setBusinessId(0);
				user.setIsadmin(0);
				user.setDiscount(new BigDecimal(1));
				user.setMobilePhone(phone);
				user.setManagershopid(0);
				user.setPassWord("");
				user.setAddress("");
				this.customerService.insertVIP(user,wxUser.getId());
				WXUser wxuser = this.wxUserService.findById(wxUser.getId());
				WXSessionHelper.setWxUser(request.getSession(), wxuser);
				ModelAndView view = new ModelAndView("wx/vip/recharge");
				view.addObject("id", user.getId());
				saveLog(request.getSession(), user, wxUser.getId()+"开通会员");
				return view;
		}else{
			String appid = properties.weixinAppid;
			String redirect_uri = "http://lingwa.com.cn/weixin/wxreaccount.htm";
			//String redirect_uri = "http://192.168.10.110:8080/weixin/wxreaccount.htm";
			String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
			String state =companyName+","+name+","+position+","+phone;
			String requestUrl = url.replace("APPID", appid).replace("REDIRECT_URL", urlEncode).replace("STATE", state);
			return new ModelAndView("redirect:"+requestUrl+"");          
		}
	}
	
	@RequestMapping("wxreadd.do")
	@ResponseBody
	public ModelAndView resave(HttpServletRequest request){
		WXUser wxUser = WXSessionHelper.getWxUser(request.getSession());
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String[] split = state.split(",");
		String companyName = split[0];
		String name = split[1];
		String position = split[2];
		String phone = split[2];
		String openId ="";
		if(null==wxUser){
			
			if (!"authdeny".equals(code)) {
				WeiXinOauth2Token weiXinOauth2Token = AdvancedUtil
						.getOauth2AccessToken(properties.weixinAppid,
								properties.weixinAppsecret, code);
				openId = weiXinOauth2Token.getOpenId();
			}
			wxUser = this.wxUserService.findByOpenId(openId);
			if(null==wxUser){
				wxUser = new WXUser();
				wxUser.setOpenid(openId);
				this.wxUserService.create(wxUser);
				WXSessionHelper.setWxUser(request.getSession(), wxUser);
			}else{
				WXSessionHelper.setWxUser(request.getSession(), wxUser);
			}
		}
		
		CustomerUser user = new CustomerUser();
		user.setUserName(companyName+"(vip)");
		user.setCompanyName(companyName);
		user.setLikeman(name);
		user.setIswxvip(2);
		user.setPosition(position);
		user.setCreatedTime(new Date());
		user.setBusinessId(0);
		user.setIsadmin(0);
		user.setDiscount(new BigDecimal(1));
		user.setMobilePhone(phone);
		user.setManagershopid(0);
		user.setPassWord("");
		user.setAddress("");
		this.customerService.insertVIP(user,wxUser.getId());
		WXUser wxuser = this.wxUserService.findById(wxUser.getId());
		WXSessionHelper.setWxUser(request.getSession(), wxuser);
		ModelAndView view = new ModelAndView("wx/vip/recharge");
		view.addObject("id", user.getId());
		saveLog(request.getSession(), user, wxUser.getId()+"开通会员");
		return view;
	}
	
	@RequestMapping("wxcharge.htm")
	public ModelAndView charge(HttpServletRequest request){
		WXUser wxUser = WXSessionHelper.getWxUser(request.getSession());
		ModelAndView view = new ModelAndView("wx/vip/recharge");
		if(null!=wxUser){
			if(null!=wxUser.getCustomerUserId()){
				view.addObject("id", wxUser.getCustomerUserId());
			}
		}
		return view;
	}
	
	@RequestMapping("wxtransfer.htm")
	public ModelAndView transfer(HttpServletRequest request){
		ModelAndView view = new ModelAndView("wx/vip/transfer");
		return view;
	}
	
	@RequestMapping("wxinfo.htm")
	public ModelAndView info(HttpServletRequest request){
		ModelAndView view = new ModelAndView("wx/vip/info");
		return view;
	}
	
	@RequestMapping("wxrecord.htm")
	public ModelAndView record(HttpServletRequest request){
		WXUser wxUser = WXSessionHelper.getWxUser(request.getSession());
		//WXUser wxUser = new WXUser();
		if(null!=wxUser){
			ModelAndView view = new ModelAndView("wx/vip/chargeRecord");
			if(null!=wxUser.getCustomerUserId()){
				List<WXRechargeRecord> wxRechargeRecordList = this.wxRechargeRecordService.findAllByCid(wxUser.getCustomerUserId());
				view.addObject("recordList", wxRechargeRecordList);
			}
			return view;
		}else{
			String appid = properties.weixinAppid;
			String redirect_uri = "http://lingwa.com.cn/weixin/wxrerecord.htm";
			//String redirect_uri = "http://192.168.10.110:8080/weixin/wxrerecord.htm";
			String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
			String requestUrl = url.replace("APPID", appid).replace("REDIRECT_URL", urlEncode).replace("STATE", "1");
			return new ModelAndView("redirect:"+requestUrl+"");          
		}
	}
	
	@RequestMapping("wxrerecord.htm")
	public ModelAndView rerecord(HttpServletRequest request){
		ModelAndView view = new ModelAndView("wx/vip/chargeRecord");
		WXUser wxUser = WXSessionHelper.getWxUser(request.getSession());
		if(null==wxUser){
			String code = request.getParameter("code");
			String openId ="";
			if (!"authdeny".equals(code)) {
				WeiXinOauth2Token weiXinOauth2Token = AdvancedUtil
						.getOauth2AccessToken(properties.weixinAppid,
								properties.weixinAppsecret, code);
				openId = weiXinOauth2Token.getOpenId();
			}
			wxUser = this.wxUserService.findByOpenId(openId);
			if(null==wxUser){
				wxUser = new WXUser();
				wxUser.setOpenid(openId);
				this.wxUserService.create(wxUser);
				WXSessionHelper.setWxUser(request.getSession(), wxUser);
			}else{
				WXSessionHelper.setWxUser(request.getSession(), wxUser);
			}
		}
		if(null!=wxUser.getCustomerUserId()){
			List<WXRechargeRecord> wxRechargeRecordList = this.wxRechargeRecordService.findAllByCid(wxUser.getCustomerUserId());
			view.addObject("recordList", wxRechargeRecordList);
		}
		return view;
	}
	
	@RequestMapping("wxorder.htm")
	public ModelAndView orderlist(HttpServletRequest request){
		WXUser wxUser = WXSessionHelper.getWxUser(request.getSession());
		//WXUser wxUser = new WXUser();
		if(null!=wxUser){
			ModelAndView view = new ModelAndView("wx/vip/orderlist");
			if(null!=wxUser.getCustomerUserId()){
				List<Order> orders = this.orderService.findOrderListByUserId(wxUser.getCustomerUserId());
				view.addObject("orders", orders);
			}
			return view;
		}else{
			String appid = properties.weixinAppid;
			String redirect_uri = "http://lingwa.com.cn/weixin/wxreorder.htm";
			//String redirect_uri = "http://192.168.10.110:8080/weixin/wxrerecord.htm";
			String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
			String requestUrl = url.replace("APPID", appid).replace("REDIRECT_URL", urlEncode).replace("STATE", "1");
			return new ModelAndView("redirect:"+requestUrl+"");          
		}
	}
	
	@RequestMapping("wxreorder.htm")
	public ModelAndView reorderlist(HttpServletRequest request){
		ModelAndView view = new ModelAndView("wx/vip/orderlist");
		WXUser wxUser = WXSessionHelper.getWxUser(request.getSession());
		if(null==wxUser){
			String code = request.getParameter("code");
			String openId ="";
			if (!"authdeny".equals(code)) {
				WeiXinOauth2Token weiXinOauth2Token = AdvancedUtil
						.getOauth2AccessToken(properties.weixinAppid,
								properties.weixinAppsecret, code);
				openId = weiXinOauth2Token.getOpenId();
			}
			wxUser = this.wxUserService.findByOpenId(openId);
			if(null==wxUser){
				wxUser = new WXUser();
				wxUser.setOpenid(openId);
				this.wxUserService.create(wxUser);
				WXSessionHelper.setWxUser(request.getSession(), wxUser);
			}else{
				WXSessionHelper.setWxUser(request.getSession(), wxUser);
			}
		}
		if(null!=wxUser.getCustomerUserId()){
			List<Order> orders = this.orderService.findOrderListByUserId(wxUser.getCustomerUserId());
			view.addObject("orders", orders);
		}
		return view;
	}
	
	@RequestMapping("wxorderDetail.htm")
	public ModelAndView detail(HttpServletRequest request){
		ModelAndView view = new ModelAndView("wx/vip/orderdetail");
		String id = request.getParameter("id");
		Order order = this.orderService.findOrderById(id);
		view.addObject("order", order);
		return view;
	}
	
	@RequestMapping("wxedit.htm")
	public ModelAndView edit(HttpServletRequest request){
		WXUser wxUser = WXSessionHelper.getWxUser(request.getSession());
		//WXUser wxUser = new WXUser();
		if(null!=wxUser){
			ModelAndView view = new ModelAndView("wx/vip/edit");
			if(null!=wxUser.getCustomerUserId()){
				CustomerUser user = this.customerService.findById(wxUser.getCustomerUserId());
				view.addObject("user", user);
			}
			return view;
		}else{
			String appid = properties.weixinAppid;
			String redirect_uri = "http://lingwa.com.cn/weixin/wxreedit.htm";
			//String redirect_uri = "http://192.168.10.110:8080/weixin/wxreedit.htm";
			String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
			String requestUrl = url.replace("APPID", appid).replace("REDIRECT_URL", urlEncode).replace("STATE", "1");
			return new ModelAndView("redirect:"+requestUrl+"");          
		}
	}
	
	@RequestMapping("wxreedit.htm")
	public ModelAndView reedit(HttpServletRequest request){
		ModelAndView view = new ModelAndView("wx/vip/edit");
		WXUser wxUser = WXSessionHelper.getWxUser(request.getSession());
		if(null==wxUser){
			String code = request.getParameter("code");
			String openId ="";
			if (!"authdeny".equals(code)) {
				WeiXinOauth2Token weiXinOauth2Token = AdvancedUtil
						.getOauth2AccessToken(properties.weixinAppid,
								properties.weixinAppsecret, code);
				openId = weiXinOauth2Token.getOpenId();
			}
			wxUser = this.wxUserService.findByOpenId(openId);
			if(null==wxUser){
				wxUser = new WXUser();
				wxUser.setOpenid(openId);
				this.wxUserService.create(wxUser);
				WXSessionHelper.setWxUser(request.getSession(), wxUser);
			}else{
				WXSessionHelper.setWxUser(request.getSession(), wxUser);
			}
		}
		if(null!=wxUser.getCustomerUserId()){
			CustomerUser user = this.customerService.findById(wxUser.getCustomerUserId());
			view.addObject("user", user);
		}
		return view;
	}
	
	@RequestMapping("wxedit.do")
	@ResponseBody
	public String doedit(HttpServletRequest request){
			try {
				String cid = request.getParameter("cid");
				String companyName = request.getParameter("companyName");
				String name = request.getParameter("name");
				String position = request.getParameter("position");
				String phone = request.getParameter("phone");
				if(null!=cid&&""!=cid){
					CustomerUser user = new CustomerUser();
					user.setId(Integer.parseInt(cid));
					user.setCompanyName(companyName);
					user.setUserName(companyName+"(vip)");
					user.setPosition(position);
					user.setLikeman(name);
					user.setMobilePhone(phone);
					user.setUpdatedTime(new Date());
					this.customerService.update(user);
					LOGGER.info("修改会员企业信息成功,id="+user.getId());
					return "200";
				}
			} catch (Exception e) {
				LOGGER.error("修改会员企业信息失败:"+e);
			}
			return "201";
			
	}
	
	
	@RequestMapping("wxpay.do")
	@ResponseBody
	public LWResult czPay(HttpServletRequest request){
		try {
			String id = request.getParameter("id");
			String recharge = request.getParameter("recharge");
			String free = request.getParameter("free");
			int money = 0;
			int freeMoney = 0;
			WXUser wxUser = WXSessionHelper.getWxUser(request.getSession());
			if(null==wxUser.getCustomerUserId()){
				return LWResult.build(201, "创建订单失败");
			}
			if(id.equals(wxUser.getCustomerUserId().toString())){
				String orderNo = "CZ"+OrderUtil.GetOrderNumber();
				if(recharge.equals("2000")){
					money = 200000;
					freeMoney = 10000;
				}else if (recharge.equals("5000")) {
					money = 500000;
					freeMoney = 30000;
				}else if (recharge.equals("10000")) {
					money = 1000000;
					freeMoney = 70000;
				}else if (recharge.equals("20000")) {
					money = 2000000;
					freeMoney = 160000;
				}else{
					return LWResult.build(201, "会员充值失败");
				}
				this.wxRechargeRecordService.save(id,money,freeMoney,orderNo);
				
				String spbill_create_ip = request.getRemoteAddr();
				WXPrepay prePay = new WXPrepay();
				prePay.setAppid(properties.weixinAppid);
				prePay.setBody("会员充值");
				prePay.setPartnerKey(properties.weixinAppsecret);
				prePay.setMch_id(properties.WX_MCH_ID);
				prePay.setNotify_url(properties.WX_PAY_CZ_NOTIFY_URL);
				//时间戳生成的订单号
				prePay.setOut_trade_no(orderNo);
				prePay.setSpbill_create_ip(spbill_create_ip);
				prePay.setTotal_fee(String.valueOf(money));
				prePay.setTrade_type("JSAPI");
				prePay.setPartnerKey(properties.WX_PAY_PARTNERKRY);
				prePay.setOpenid(wxUser.getOpenid());
				// 此处添加获取openid的方法，获取预支付订单需要此参数！！！！！！！！！！！
				// 获取预支付订单号
				String prepayid = wxPayUtil.submitXmlGetPrepayId(prePay);
				LOGGER.info("获取的预支付订单是：" + prepayid);
				if (prepayid != null && prepayid.length() > 10) {
					// 生成微信支付参数，此处拼接为完整的JSON格式，符合支付调起传入格式
					String jsParam = wxPayUtil.createPackageValue(
							prePay.getAppid(), prePay.getPartnerKey(), prepayid);
					System.out.println("jsParam=" + jsParam);
					// 此处可以添加订单的处理逻辑
					// model.addAttribute("jsParam", jsParam);
					LOGGER.info("生成的微信调起JS参数为：" + jsParam);
					HashMap<String, Object> map = new HashMap<String, Object>();
					JsonNode jsonNode;
					jsonNode = MAPPER.readTree(jsParam);
					map.put("appId", jsonNode.path("appId").asText());
					map.put("nonceStr", jsonNode.path("nonceStr").asText());
					map.put("package", jsonNode.path("package").asText());
					map.put("paySign", jsonNode.path("paySign").asText());
					map.put("signType", jsonNode.path("signType").asText());
					map.put("timeStamp", jsonNode.path("timeStamp").asText());
					map.put("prepayid", prepayid);
					return LWResult.ok(map);
				}
			}
		} catch (Exception e) {
			LOGGER.error("会员充值失败:"+e);
		} 
		
		return LWResult.build(201, "会员充值失败");
	}
	
	@RequestMapping("/wxnotify.do")
	public void test(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = DOM4JParser.getInputStream(request);
		String xmlStr = "";
		LOGGER.info("------map:" + map);
		if (map.get("return_code").equals("SUCCESS")) {
			NotifyState state = this.notifyStateService.findById(map.get("out_trade_no").toString());
			if(null!=state && state.getStatus()==1){
				xmlStr = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
			}else{
				this.wxRechargeRecordService.changeStatus(map.get("out_trade_no").toString());
				LOGGER.info("支付成功,订单号:" + map.get("out_trade_no").toString());
				xmlStr = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
			}
		}else{
			xmlStr = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
					+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
		}
		LOGGER.info("xmlstr:" + xmlStr);
		PrintWriter writer = null;
		response.setContentType("text/xml");
		try {
			writer = response.getWriter();
			writer.flush();
			writer.println(xmlStr);// 告知微信已成功处理请求
		} catch (Exception e) {
			LOGGER.error("异步回调失败"+e);
		}finally{
			writer.close();
		}
	}
	
	private void saveLog(HttpSession session,CustomerUser dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.VIPCUSTOMER.getName());
	       sysLog.setDataId(dto.getId().toString());

	       if(dto!=null){
		       String dataContent = new Gson().toJson(dto,
						new TypeToken<CustomerUser>() {
						}.getType());

		       sysLog.setDataContent(dataContent);
	       }

	       logService.createLog(sysLog);
		}catch(Exception e){
			LOGGER.error("保存日志失败",e);
		}
	}
}
