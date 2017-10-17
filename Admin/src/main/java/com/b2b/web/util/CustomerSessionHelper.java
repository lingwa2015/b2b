package com.b2b.web.util;

import javax.servlet.http.HttpSession;

import com.b2b.Constant;
import com.b2b.common.domain.CustomerUser;

public class CustomerSessionHelper {

	public static CustomerUser getUser(HttpSession session) {
		return (CustomerUser) session.getAttribute(Constant.USER_KEY);
	}

	public static Integer getUserId(HttpSession session){
		Integer id = null;
		CustomerUser personUser = CustomerSessionHelper.getUser(session);
		if(personUser!=null){
			id = personUser.getId();
		}
		return id;
	}

	public static void setUser(HttpSession session,CustomerUser personUser) {
		session.setAttribute(Constant.USER_KEY, personUser);
	}

	public static CustomerUser getAppUser(HttpSession session) {
		return (CustomerUser) session.getAttribute(Constant.USER_APP_KEY);
	}

	public static Integer getAppUserId(HttpSession session){
		Integer id = null;
		CustomerUser personUser = CustomerSessionHelper.getUser(session);
		if(personUser!=null){
			id = personUser.getId();
		}
		return id;
	}

	public static void setAppUser(HttpSession session,CustomerUser personUser) {
		session.setAttribute(Constant.USER_APP_KEY, personUser);
	}
}
