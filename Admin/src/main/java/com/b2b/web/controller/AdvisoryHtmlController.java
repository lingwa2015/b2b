package com.b2b.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b2b.common.domain.*;
import com.b2b.service.*;
import com.b2b.web.util.LWResult;
import com.b2b.web.util.MD5Util;
import com.b2b.web.wx.util.*;
import com.b2b.web.wx.util.pay.OrderUtil;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Controller
@RequestMapping("/advisory")
public class AdvisoryHtmlController {
	private static String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URL&response_type=code&scope=snsapi_userinfo"
			+ "&state=STATE#wechat_redirect";
	private static String quieturl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URL&response_type=code&scope=snsapi_base"
			+ "&state=STATE#wechat_redirect";
	@Autowired
	private AppAdvisoryService advisoryService;
	@Autowired
	private RecommendCashbackService recommendCashbackService;
	@Autowired
	private Properties properties;
	@Autowired
	ShopUserService shopUserService;
	@Autowired
	AccessTokenService accessTokenService;
	@Autowired
	RecommendRedbackService recommendRedbackService;
	@Autowired
	WholeTokenService wholeTokenService;
	@RequestMapping("/appSuccess.htm")
	public ModelAndView success(HttpServletRequest request) {
		return new ModelAndView("advisory/success");
	}
	
	@RequestMapping("/appNew.htm")
	public ModelAndView add(HttpServletRequest request) {
		return new ModelAndView("advisory/add");
	}
	
	@RequestMapping("/wxSpread.htm")
	public ModelAndView Spread(HttpServletRequest request) {
		return new ModelAndView("spread/add");
	}
	
	@RequestMapping("/wxSuccess.htm")
	public ModelAndView spreadSuccess(HttpServletRequest request) {
		return new ModelAndView("spread/success");
	}
	
	@RequestMapping("/edit.htm")
	public ModelAndView edit(HttpServletRequest request,int id) {
		Advisory advisory = this.advisoryService.findById(id);
		ModelAndView view = new ModelAndView("advisory/edit");
		view.addObject("advisory", advisory);
		return view;
	}
	
	@RequestMapping(value="/edit.do",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String doEdit(Advisory advisory){
		try {
			this.advisoryService.update(advisory);
			return "编辑成功";
		} catch (Exception e) {
			e.printStackTrace();
			return "编辑失败";
		}
	}

	@RequestMapping("/wxLink.htm")
	public ModelAndView links(HttpServletRequest request) {
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		if(null!=user){
			return new ModelAndView("redirect:/advisory/wxlink.htm");
		}
		//静默授权
		String appid = properties.weixinAppid;
		String redirect_uri = properties.ONLINE_URL+"advisory/shop_quietLinkOauth.htm";
		String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
		String requestUrl = quieturl.replace("APPID", appid).replace("REDIRECT_URL", urlEncode);
		//显示授权
		return new ModelAndView("redirect:"+requestUrl+"");

	}
	@RequestMapping("/shop_quietLinkOauth.htm")
	public ModelAndView quietManageOauth(HttpServletRequest request,HttpServletResponse response){
		String code = request.getParameter("code");
		String openId ="";
		if (!"authdeny".equals(code)) {
			WeiXinOauth2Token weiXinOauth2Token = AdvancedUtil
					.getOauth2AccessToken(properties.weixinAppid,
							properties.weixinAppsecret, code);
			openId = weiXinOauth2Token.getOpenId();
			String accessToken = weiXinOauth2Token.getAccessToken();

			ShopUser wxUser = this.shopUserService.findByOpenId(openId);
			WXUser info = null;
			if(null==wxUser){
				info = AdvancedUtil.getWeixinUserInfo(accessToken, openId);
				if(null!=info && info.getNickName()!=null && info.getHeadImgurl()!=null){
					wxUser = new ShopUser();
					wxUser.setOpenid(openId);
					wxUser.setCity(info.getCity());
					wxUser.setCountry(info.getCountry());
					wxUser.setHeadImgurl(info.getHeadImgurl());
					wxUser.setNickName(info.getNickName());
					wxUser.setProvince(info.getProvince());
					wxUser.setSex(info.getSex());
					wxUser.setIsadmin(0);
					wxUser.setCreated(new Date());
					this.shopUserService.create(wxUser);
					WXSessionHelper.setShopUser(request.getSession(), wxUser);
					return new ModelAndView("redirect:/advisory/wxlink.htm");
				}
			}else{
				info = AdvancedUtil.getWeixinUserInfo(accessToken, openId);
				if(null!=info && info.getNickName()!=null && info.getHeadImgurl()!=null){
					wxUser.setCity(info.getCity());
					wxUser.setCountry(info.getCountry());
					wxUser.setHeadImgurl(info.getHeadImgurl());
					wxUser.setNickName(info.getNickName());
					wxUser.setProvince(info.getProvince());
					wxUser.setSex(info.getSex());
					this.shopUserService.update(wxUser);
					WXSessionHelper.setShopUser(request.getSession(), wxUser);
					return new ModelAndView("redirect:/advisory/wxlink.htm");
				}
			}
		}
		//显示授权
		String appid = properties.weixinAppid;
		String redirect_uri = properties.ONLINE_URL+"advisory/shop_linkOauth.htm";
		String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
		String requestUrl = url.replace("APPID", appid).replace("REDIRECT_URL", urlEncode);
		return new ModelAndView("redirect:"+requestUrl+"");
	}

	@RequestMapping("/shop_linkOauth.htm")
	public ModelAndView manageOauth(HttpServletRequest request,HttpServletResponse response){
		String code = request.getParameter("code");
		String openId ="";
		String token = "";
		WXUser info = null;
		if (!"authdeny".equals(code)) {
			WeiXinOauth2Token weiXinOauth2Token = AdvancedUtil
					.getOauth2AccessToken(properties.weixinAppid,
							properties.weixinAppsecret, code);
			openId = weiXinOauth2Token.getOpenId();
			token = weiXinOauth2Token.getAccessToken();
			AccessToken token2 = this.accessTokenService.findById(openId);
			if(null==token2){
				token2 = new AccessToken();
				token2.setId(openId);
				token2.setAccessToken(weiXinOauth2Token.getAccessToken());
				token2.setRefreshToken(weiXinOauth2Token.getRefeshToken());
				token2.setUpdatedTime(new Date());
				this.accessTokenService.insert(token2);
			}else{
				token2.setAccessToken(weiXinOauth2Token.getAccessToken());
				token2.setRefreshToken(weiXinOauth2Token.getRefeshToken());
				token2.setUpdatedTime(new Date());
				this.accessTokenService.update(token2);
			}
		}
		ShopUser wxUser = this.shopUserService.findByOpenId(openId);
		if(null==wxUser){
			info = AdvancedUtil.getWeixinUserInfo(token, openId);
			wxUser = new ShopUser();
			wxUser.setOpenid(openId);
			wxUser.setCity(info.getCity());
			wxUser.setCountry(info.getCountry());
			wxUser.setHeadImgurl(info.getHeadImgurl());
			wxUser.setNickName(info.getNickName());
			wxUser.setProvince(info.getProvince());
			wxUser.setSex(info.getSex());
			wxUser.setIsadmin(0);
			wxUser.setCreated(new Date());
			this.shopUserService.create(wxUser);
			WXSessionHelper.setShopUser(request.getSession(), wxUser);
		}else{
			info = AdvancedUtil.getWeixinUserInfo(token, openId);
			wxUser.setCity(info.getCity());
			wxUser.setCountry(info.getCountry());
			wxUser.setHeadImgurl(info.getHeadImgurl());
			wxUser.setNickName(info.getNickName());
			wxUser.setProvince(info.getProvince());
			wxUser.setSex(info.getSex());
			this.shopUserService.update(wxUser);
			WXSessionHelper.setShopUser(request.getSession(), wxUser);
		}
		return new ModelAndView("redirect:/advisory/wxlink.htm");
	}

	@RequestMapping("/wxlink.htm")
	public ModelAndView link(HttpServletRequest request) {
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		if(null!=user){
			return new ModelAndView("advisory/link");
		}else{
			return new ModelAndView("redirect:/advisory/wxLink.htm");
		}
	}

	@RequestMapping(value="/wxLink.do",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String doLink(HttpServletRequest request,RecommendRedback recommendRedback){
		try {
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			if(null!=user){
				recommendRedback.setCreatedTime(new Date());
				recommendRedback.setType(0);
				recommendRedback.setUserId(user.getId());
				recommendRedback.setStatus(1);
				this.recommendRedbackService.insert(recommendRedback);
				return "200";
			}else{
				return "201";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "201";
		}
	}
	
	@RequestMapping("/wxLinkSuccess.htm")
	public ModelAndView wxLinkSuccess(HttpServletRequest request) {
		return new ModelAndView("advisory/linkSuccess");
	}

	@RequestMapping("/wxCommend.htm")
	public ModelAndView commend(HttpServletRequest request) {
		return new ModelAndView("advisory/commend");
	}

	@RequestMapping(value="/wxCommend.do",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String docommend(RecommendCashback recommendCashback){
		try {
			recommendCashback.setCreatedTime(new Date());
			recommendCashback.setType(0);
			this.recommendCashbackService.insert(recommendCashback);
			return "200";
		} catch (Exception e) {
			e.printStackTrace();
			return "201";
		}
	}
	
	@RequestMapping("wxconfig.json")
	@ResponseBody
	public LWResult getWxConfig(HttpServletRequest request,@RequestParam("url")String url){
		try {
			String timeStamp = OrderUtil.GetTimestamp();
			Random random = new Random();
			String nonceStr = DigestUtils.md5Hex(String.valueOf(random.nextInt(10000))).toUpperCase();
			WholeToken wholeToken = this.wholeTokenService.findByIdOneHour(1);
			if(null==wholeToken){
				com.b2b.web.wx.util.Token token = CommonUtil.getToken(properties.weixinAppid, properties.weixinAppsecret);
				WholeToken t = this.wholeTokenService.findById(1);
				if(null==t){
					WholeToken token2 = new WholeToken();
					token2.setId(1);
					token2.setAccessToken(token.getAccessToken());
					token2.setCreatedTime(new Date());
					this.wholeTokenService.insert(token2);
				}else{
					t.setAccessToken(token.getAccessToken());
					t.setCreatedTime(new Date());
					this.wholeTokenService.update(t);
				}
				wholeToken = t;
			}
			com.b2b.web.wx.util.Token ticket = CommonUtil.getJsapiTicket(wholeToken.getAccessToken());
			String string1 = "jsapi_ticket="+ticket.getAccessToken()+"&noncestr="+nonceStr+"&timestamp="+timeStamp+"&url="+url;
//			if(request.getQueryString()!=null) //判断请求参数是否为空
//				string1+="?"+request.getQueryString();
			String sha1 = MD5Util.SHA1(string1);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("appId", properties.weixinAppid);
			map.put("timestamp", timeStamp);
			map.put("nonceStr", nonceStr);
			map.put("signature", sha1);
			return LWResult.ok(map);
		} catch (Exception e) {
			e.printStackTrace();
			return LWResult.build(201, "获取失败");
		}
	}
}
