package com.b2b.web.wx.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.common.domain.SysLog;
import com.b2b.common.domain.WXUser;
import com.b2b.common.domain.WXUserAddress;
import com.b2b.common.domain.WXUserOrder;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.service.LogService;
import com.b2b.service.WXUserOrderService;
import com.b2b.service.WXUserService;
import com.b2b.web.util.SessionHelper;
import com.b2b.web.wx.util.AdvancedUtil;
import com.b2b.web.wx.util.CommonUtil;
import com.b2b.web.wx.util.Properties;
import com.b2b.web.wx.util.WXSessionHelper;
import com.b2b.web.wx.util.WeiXinOauth2Token;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RequestMapping("wxuserOrder")
@Controller
public class WXUserOrderController {
	private static final Logger logger = LoggerFactory.getLogger(WXUserOrderController.class);
	private static String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URL&response_type=code&scope=snsapi_base&state=1#wechat_redirect";
	@Autowired
	private WXUserService wXUserService;
	
	@Autowired
	private Properties properties;
	
	@Autowired
	private WXUserOrderService wXUserOrderService;
	
	@Autowired
	LogService logService;
	
	@RequestMapping("wxlist.htm")
	public ModelAndView list(HttpServletRequest request){
		WXUser wxUser = WXSessionHelper.getWxUser(request.getSession());
		if(null!=wxUser){
			ModelAndView view = new ModelAndView("wx/order/list");
			 List<WXUserOrder> wxUserOrderList = this.wXUserOrderService.findByWXUserId(wxUser.getId());
			 view.addObject("wxUserOrderList", wxUserOrderList);
				return view;
		}else{
			String appid = properties.weixinAppid;
			String redirect_uri = "http://lingwa.com.cn/wxuserOrder/wxrelist.htm";
			//String redirect_uri = "http://192.168.10.110:8080/wxuserOrder/wxrelist.htm";
			String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
			String requestUrl = url.replace("APPID", appid).replace("REDIRECT_URL", urlEncode).replace("STATE", "1");
			return new ModelAndView("redirect:"+requestUrl+"");          
		}
	}
	
	@RequestMapping("wxrelist.htm")
	public ModelAndView relist(HttpServletRequest request){
		ModelAndView view = new ModelAndView("wx/order/list");
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
			wxUser = this.wXUserService.findByOpenId(openId);
			if(null==wxUser){
				wxUser = new WXUser();
				wxUser.setOpenid(openId);
				this.wXUserService.create(wxUser);
				WXSessionHelper.setWxUser(request.getSession(), wxUser);
			}else{
				WXSessionHelper.setWxUser(request.getSession(), wxUser);
			}
		}
        List<WXUserOrder> wxUserOrderList = this.wXUserOrderService.findByWXUserId(wxUser.getId());
        view.addObject("wxUserOrderList", wxUserOrderList);
		return view;
	}
	
	
	@RequestMapping("wxdetail.htm")
	public ModelAndView detail(HttpServletRequest request){
		ModelAndView view = new ModelAndView("wx/order/detail");
		String id = request.getParameter("id");
		WXUserOrder wxUserOrder = this.wXUserOrderService.findOrderDetailById(id);
		view.addObject("wxUserOrder", wxUserOrder);
		return view;
	}
	
	private void saveLog(HttpSession session,WXUserOrder dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.WXUserOrder.getName());
	       if(dto.getId()!=null){
	       sysLog.setDataId(dto.getId().toString());
	       }

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<WXUserAddress>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
