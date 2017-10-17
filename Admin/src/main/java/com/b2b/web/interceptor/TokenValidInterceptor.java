package com.b2b.web.interceptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.Constant;
import com.b2b.web.util.TokenHandler;

public class TokenValidInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		 if(!TokenHandler.validToken(request)){
	            try  
	            {  
	                response.setCharacterEncoding("UTF-8");  
	                response.getWriter().print("請不要頻繁提交！");  
	            }  
	            finally  
	            {  
	                response.getWriter().close();  
	            } 
	            return false;
	        }
	     return true;
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
		
	}

}
