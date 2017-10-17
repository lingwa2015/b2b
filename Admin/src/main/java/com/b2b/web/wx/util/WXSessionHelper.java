package com.b2b.web.wx.util;

import javax.servlet.http.HttpSession;

import com.b2b.Constant;
import com.b2b.common.domain.ShopAliUser;
import com.b2b.common.domain.ShopUser;
import com.b2b.common.domain.WXUser;

public class WXSessionHelper {

	public static WXUser getWxUser(HttpSession session) {
		return (WXUser) session.getAttribute(Constant.USER_WX_KEY);
	}

	public static Integer getWxUserId(HttpSession session){
		Integer id = null;
		WXUser wxUser = WXSessionHelper.getWxUser(session);
		if(wxUser!=null){
			id = wxUser.getId();
		}
		return id;
	}

	public static void setWxUser(HttpSession session,WXUser wxUser) {
		session.setAttribute(Constant.USER_WX_KEY, wxUser);
	}
	
	
	
	public static ShopUser getShopUser(HttpSession session){
		return (ShopUser) session.getAttribute(Constant.USER_SHOP_KEY);
	}
	
	
	public static void setShopUser(HttpSession session,ShopUser shopUser) {
		session.setAttribute(Constant.USER_SHOP_KEY, shopUser);
	}
	
	public static Integer getShopUserId(HttpSession session){
		Integer id = null;
		ShopUser user = WXSessionHelper.getShopUser(session);
		if(user!=null){
			id = user.getId();
		}
		return id;
	}
	
	public static ShopAliUser getShopAliUser(HttpSession session){
		return (ShopAliUser) session.getAttribute(Constant.USER_SHOP_ALI_KEY);
	}
	
	public static void setShopAliUser(HttpSession session,ShopAliUser shopAliUser) {
		session.setAttribute(Constant.USER_SHOP_ALI_KEY, shopAliUser);
	}
	
	public static Integer getShopAliUserId(HttpSession session){
		Integer id = null;
		ShopAliUser user = WXSessionHelper.getShopAliUser(session);
		if(user!=null){
			id = user.getId();
		}
		return id;
	}
}
