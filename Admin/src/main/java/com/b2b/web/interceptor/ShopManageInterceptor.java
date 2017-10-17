package com.b2b.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.Constant;
import com.b2b.common.domain.ShopUser;
import com.b2b.service.ShopUserService;
import com.b2b.web.wx.util.WXSessionHelper;

public class ShopManageInterceptor implements HandlerInterceptor {
	@Autowired
	private ShopUserService shopUserService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		ShopUser user = (ShopUser) request.getSession().getAttribute(Constant.USER_SHOP_KEY);
		if(null==user){
			response.sendRedirect("/convenient/shop_noPeivilege.htm");
			return false;
		}else{
			return true;
		}
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub

	}

}
