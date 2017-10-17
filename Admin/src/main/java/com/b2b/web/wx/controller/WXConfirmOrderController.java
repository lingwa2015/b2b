package com.b2b.web.wx.controller;

import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.b2b.common.domain.StandardOrder;
import com.b2b.common.domain.WXUser;
import com.b2b.common.domain.WXUserAddress;
import com.b2b.common.domain.WXUserInvoice;
import com.b2b.service.StandardOrderService;
import com.b2b.service.WXUserAddressService;
import com.b2b.service.WXUserInvoiceService;
import com.b2b.service.WXUserService;
import com.b2b.web.wx.util.AdvancedUtil;
import com.b2b.web.wx.util.CommonUtil;
import com.b2b.web.wx.util.Properties;
import com.b2b.web.wx.util.Token;
import com.b2b.web.wx.util.WXSessionHelper;
import com.b2b.web.wx.util.WeiXinOauth2Token;
import com.b2b.web.wx.util.pay.OrderUtil;

@Controller
@RequestMapping("/weixin")
public class WXConfirmOrderController {
	private static final Logger logger = LoggerFactory.getLogger(WXConfirmOrderController.class);
	private static String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URL&response_type=code&scope=snsapi_base&state=STATE#wechat_redirect";
	@Autowired
	private WXUserAddressService wxuserAddressService;

	@Autowired
	private WXUserInvoiceService wxuserInvoiceService;
	
	@Autowired
	private WXUserService wXUserService;
	
	@Autowired
	StandardOrderService standardOrderService;
	
	@Autowired
	private Properties properties;
	
	@RequestMapping(value = "/wxconfirmOrderList.htm")
	@ResponseBody
	public ModelAndView confirmOrderList(HttpServletRequest request) {
		String standId = request.getParameter("standId");
		String num = request.getParameter("num");
		String offerPrice=request.getParameter("offerPrice");
		String appid = properties.weixinAppid;
		String redirect_uri = "http://lingwa.com.cn/weixin/wxreconfirmOrderList.htm";
		//String redirect_uri = "http://192.168.10.110:8080/weixin/wxreconfirmOrderList.htm";
		String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
		String state = standId+","+num+","+offerPrice;
		String requestUrl = url.replace("APPID", appid).replace("REDIRECT_URL", urlEncode).replace("STATE", state);
		return new ModelAndView("redirect:"+requestUrl+"");          
	}
	
	@RequestMapping(value = "/wxreconfirmOrderList.htm")
	@ResponseBody
	public ModelAndView reConfirmOrderList(HttpServletRequest request,RedirectAttributes attr) {
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String openId ="";
		String token = "";
		if (!"authdeny".equals(code)) {
			WeiXinOauth2Token weiXinOauth2Token = AdvancedUtil
					.getOauth2AccessToken(properties.weixinAppid,
							properties.weixinAppsecret, code);
			openId = weiXinOauth2Token.getOpenId();
			token = weiXinOauth2Token.getAccessToken();
		}
		WXUser wxUser = this.wXUserService.findByOpenId(openId);
		if(null==wxUser){
			wxUser = new WXUser();
			wxUser.setOpenid(openId);
			this.wXUserService.create(wxUser);
			WXSessionHelper.setWxUser(request.getSession(), wxUser);
		}else{
			WXSessionHelper.setWxUser(request.getSession(), wxUser);
		}
		attr.addAttribute("wxuserId", wxUser.getId());
		attr.addAttribute("state", state);
		attr.addAttribute("token", token);
		return new ModelAndView("redirect:/weixin/wxconfirm.htm"); 
	}
	
	@RequestMapping(value = "/wxconfirm.htm")
	@ResponseBody
	public ModelAndView confirm(HttpServletRequest request,@RequestParam("wxuserId")Integer wxuserId,@RequestParam("state")String state,@RequestParam("token")String token){
		WXUser user = WXSessionHelper.getWxUser(request.getSession());
		if(null!=user && user.getId().equals(wxuserId)){
			ModelAndView view = new ModelAndView("wx/pay/confirm");
			String[] split = state.split(",");
			String standId = split[0];
			String num = split[1];
			String offerPrice = split[2];
			List<WXUserAddress> list=wxuserAddressService.selectByExample(wxuserId);
			WXUserAddress wxuserAddress=new WXUserAddress();
			if(list.size()>0){
				wxuserAddress=list.get(0);
			}
			view.addObject("wxuserAddress", wxuserAddress);
			
			WXUserInvoice wxuserInvoice= wxuserInvoiceService.findByWXUserId(wxuserId);
			if(null!=wxuserInvoice){
				view.addObject("invoice", wxuserInvoice.getCompanyName());
				view.addObject("invoiceid", wxuserInvoice.getId());
			}else{
				view.addObject("invoice", "");
				view.addObject("invoiceid", "");
			}
			StandardOrder standardOrder = this.standardOrderService.findByStandId(standId);
			view.addObject("standardOrder", standardOrder);
			view.addObject("standId", standId);
			view.addObject("num", num);
			view.addObject("offerPrice", offerPrice);
			view.addObject("offerPrice", offerPrice);
			String timeStamp = OrderUtil.GetTimestamp();
			Random random = new Random();
			String randomStr = DigestUtils.md5Hex(String.valueOf(random.nextInt(10000))).toUpperCase();
			view.addObject("timeStamp", timeStamp);
			view.addObject("randomStr", randomStr);
			view.addObject("token", token);
			view.addObject("appid", properties.weixinAppid);
			if(StringUtils.isNotEmpty(num) && StringUtils.isNotEmpty(offerPrice)){
				view.addObject("fee", Long.parseLong(num)*Long.parseLong(offerPrice));
			}
			return view;
		}else{
			return new ModelAndView("redirect:/wxuserStandardOrder/wxWeekStandardOrderList.htm"); 
		}
	}
}
