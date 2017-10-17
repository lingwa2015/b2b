package com.b2b.web.util;

import javax.servlet.http.HttpSession;

import com.b2b.Constant;
import com.b2b.common.domain.PersonUser;

public class SessionHelper {

	public static PersonUser getUser(HttpSession session) {
		return (PersonUser) session.getAttribute(Constant.USER_KEY);
	}

	public static Integer getUserId(HttpSession session){
		Integer id = null;
		PersonUser personUser = SessionHelper.getUser(session);
		if(personUser!=null){
			id = personUser.getId();
		}
		return id;
	}

	public static void setUser(HttpSession session,PersonUser personUser) {
		session.setAttribute(Constant.USER_KEY, personUser);
	}

	public static PersonUser getAppUser(HttpSession session) {
		return (PersonUser) session.getAttribute(Constant.USER_APP_KEY);
	}

	public static Integer getAppUserId(HttpSession session){
		Integer id = null;
		PersonUser personUser = SessionHelper.getUser(session);
		if(personUser!=null){
			id = personUser.getId();
		}
		return id;
	}

	public static void setAppUser(HttpSession session,PersonUser personUser) {
		session.setAttribute(Constant.USER_APP_KEY, personUser);
	}
}
