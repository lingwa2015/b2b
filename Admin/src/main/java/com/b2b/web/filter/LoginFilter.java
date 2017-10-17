package com.b2b.web.filter;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.b2b.Constant;
import com.b2b.web.app.controller.AppOrderController;

public class LoginFilter implements Filter {

	Logger logger = LoggerFactory.getLogger(LoginFilter.class);

	@Override
	public void destroy() {

	}

    static String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i"
            +"|windows (phone|ce)|blackberry"
            +"|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"
            +"|laystation portable)|nokia|fennec|htc[-_]"
            +"|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
    static String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser"
            +"|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";

    static Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);
    static Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);


	  public static boolean check(String userAgent){
	        if(null == userAgent){
	            userAgent = "";
	        }

	        Matcher matcherPhone = phonePat.matcher(userAgent);
	        Matcher matcherTable = tablePat.matcher(userAgent);
	        if(matcherPhone.find() || matcherTable.find()){
	            return true;
	        } else {
	            return false;
	        }
	   }


	  private boolean isFromPC(HttpServletRequest request) {
	    	boolean result = false;

	    	String userAgentInfo = request.getHeader("User-Agent");
	    	String info = userAgentInfo.toUpperCase();

	    	//logger.warn("userAgentInfo:" + info);

//	    	if (info.indexOf("WINDOWS") >= 0 || info.indexOf("MAC OS") >=0) {
//	    		result = true;
//	    	}
	        result=!check(info);
	    	return result;
	    }

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain arg2) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;

		String realUrl = request.getRequestURI().toString();
		if("/".equals(realUrl) || "/index.htm".equals(realUrl)){

			if(isFromPC((HttpServletRequest)req)){
				((HttpServletRequest)req).getRequestDispatcher("/official/pcIndex.htm").forward(req, resp);
			}else{
				((HttpServletRequest)req).getRequestDispatcher("/official/phoneIndex.htm").forward(req, resp);

			}
			return;
		}

		String methodName = realUrl.substring(realUrl.lastIndexOf("/")+1);
		if(methodName.startsWith("app")){

			if(StringUtils.isNotEmpty(request.getQueryString())){
				realUrl = realUrl + "?" + request.getQueryString();
			}

			//from app
			if(request.getSession().getAttribute(Constant.USER_APP_KEY) == null &&
					! request.getRequestURI().endsWith("appLogin.htm") &&
                                        ! request.getRequestURI().endsWith("appPartnerList.htm") &&
                                        ! request.getRequestURI().endsWith("about.htm") &&
                                        ! request.getRequestURI().contains("appGoods.htm") &&
                                        ! request.getRequestURI().contains("appLevelGoods.htm") &&
                                        ! request.getRequestURI().contains("appItemList.htm") &&
                                        ! request.getRequestURI().contains("appSuccess.htm") &&
                                        ! request.getRequestURI().contains("appNew.htm") &&
                                        ! request.getRequestURI().contains("appAdd.do") &&
                                        ! request.getRequestURI().contains("appLink.htm") &&
					! request.getRequestURI().endsWith("ico") &&
					! request.getRequestURI().endsWith("png") &&
					! request.getRequestURI().endsWith("jpg") &&
					! request.getRequestURI().endsWith("gif") &&
					! request.getRequestURI().endsWith("appLogin.do") &&
					! request.getRequestURI().endsWith("css") &&
					! request.getRequestURI().endsWith("js")&&
					! request.getRequestURI().endsWith("txt")&&
					! request.getRequestURI().endsWith("apk")&&
					! request.getRequestURI().endsWith("app.html")
					){

				request.getSession().setAttribute(Constant.LOGIN_APP_REDIRECT_URL, realUrl);


				((HttpServletResponse)resp).sendRedirect(request.getContextPath() + "/appLogin/appLogin.htm");
			}else{
				arg2.doFilter(req, resp);
			}
		}else if(methodName.startsWith("wx")){
			arg2.doFilter(req, resp);
		}else if(methodName.startsWith("pc")){
			arg2.doFilter(req, resp);
		}else if(methodName.startsWith("phone")){
			arg2.doFilter(req, resp);
		}else if(methodName.startsWith("shop")){
			arg2.doFilter(req, resp);
		}else{	

			//from admin
			if(StringUtils.isNotEmpty(request.getQueryString())){
				realUrl = realUrl + "?" + request.getQueryString();
			}



			if(request.getSession().getAttribute(Constant.USER_KEY) == null &&
					! request.getRequestURI().endsWith("index.htm") &&
					! request.getRequestURI().endsWith("adminLogin.htm") &&
					! request.getRequestURI().contains("itemImage.htm") &&
                    ! request.getRequestURI().endsWith("about.htm") &&
                    ! request.getRequestURI().endsWith("baidu_verify_AH4ZkFjjHk.html") &&
                    ! request.getRequestURI().endsWith("baidu_verify_BpavkHU451.html") &&
					! request.getRequestURI().endsWith("ico") &&
					! request.getRequestURI().endsWith("png") &&
					! request.getRequestURI().endsWith("jpg") &&
					! request.getRequestURI().endsWith("gif") &&

					! request.getRequestURI().endsWith("login.do") &&
					! request.getRequestURI().endsWith("css") &&
					! request.getRequestURI().endsWith("js")&&
					! request.getRequestURI().endsWith("txt")&&
					! request.getRequestURI().endsWith("apk")&&
					! request.getRequestURI().endsWith("app.html") &&
					! request.getRequestURI().endsWith("itemImage.htm") &&
					! request.getRequestURI().endsWith("verifyCode.htm")
					//微信测试用
					&& ! request.getRequestURI().contains("weixinTest")

					) {
				request.getSession().setAttribute(Constant.LOGIN_REDIRECT_URL, realUrl);
				((HttpServletResponse)resp).sendRedirect(request.getContextPath() + "/adminLogin.htm");
				return;
			}else{
				arg2.doFilter(req, resp);
			}
		}


	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

}