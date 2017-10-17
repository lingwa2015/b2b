package com.b2b.web.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.Constant;
import com.b2b.common.domain.City;
import com.b2b.common.domain.Privilege;
import com.b2b.common.domain.SysLog;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.UserCity;
import com.b2b.common.util.EncryptHelper;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.service.CityService;
import com.b2b.service.LogService;
import com.b2b.service.UserCityService;
import com.b2b.service.UserService;
import com.b2b.web.util.SessionHelper;
import com.b2b.web.util.VerifyCodeUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
public class LoginController {
    // \b 是单词边界(连着的两个(字母字符 与 非字母字符) 之间的逻辑上的间隔),
    // 字符串在编译时会被转码一次,所以是 "\\b"
    // \B 是单词内部逻辑间隔(连着的两个字母字符之间的逻辑上的间隔)
    static String phoneReg = "\\b(ip(hone|od)|android|opera m(ob|in)i"
            +"|windows (phone|ce)|blackberry"
            +"|s(ymbian|eries60|amsung)|p(laybook|alm|rofile/midp"
            +"|laystation portable)|nokia|fennec|htc[-_]"
            +"|mobile|up.browser|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
    static String tableReg = "\\b(ipad|tablet|(Nexus 7)|up.browser"
            +"|[1-4][0-9]{2}x[1-4][0-9]{2})\\b";
    //移动设备正则匹配：手机端、平板
    static Pattern phonePat = Pattern.compile(phoneReg, Pattern.CASE_INSENSITIVE);
    static Pattern tablePat = Pattern.compile(tableReg, Pattern.CASE_INSENSITIVE);

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	UserService userService;
	
	@Autowired
	UserCityService userCityService;
	
	@Autowired
	CityService cityService;

	@Autowired
	LogService logService;

    /**
     * 检测是否是移动设备访问
     *
     * @param userAgent 浏览器标识
     * @return true:移动设备接入，false:pc端接入
     */
    public static boolean check(String userAgent){
        if(null == userAgent){
            userAgent = "";
        }
        // 匹配
        Matcher matcherPhone = phonePat.matcher(userAgent);
        Matcher matcherTable = tablePat.matcher(userAgent);
        if(matcherPhone.find() || matcherTable.find()){
            return true;
        } else {
            return false;
        }
    }


	@RequestMapping(value = "index.htm")
	public String index(HttpServletRequest request) {
		try{


			//String message="test";

			//Client zx=new Client("ZXHD-CRM-0100-SLMMZC", "97662511");

			//result = zx.sendSMSEX("ZXHD-CRM-0100-SLMMZC", "97662511", "15665195375", message,
			//	"", "1", "", "1", "", "4");


			}
			catch(Exception e){
				e.printStackTrace();
			}



//			String phone=	"15665195375";
//			String content="您的订单已提交，我们的送货小哥正在火速为您配货";
//			Client zx=new Client("ZXHD-CRM-0100-SLMMZC", "97662511");
//			zx.register("ZXHD-CRM-0100-SLMMZC", "97662511","杭州冷恋科技有限公司","冷恋","常州科教城信息产业园","","","","","","");
//		  //发送普通的单发，或群发短信
//			String result=	zx.sendSMS( "ZXHD-CRM-0100-SLMMZC", "97662511", phone , content,"1124", "1", null, "0", "", "0");


		if (isFromPC(request)) {
    		return "redirect:/static/pc_index.htm";
    	} else {
    		return "redirect:/static/index.htm";
    	}
	//	return "login";
	}


	@RequestMapping(value = "adminLogin.htm")
	public String adminLogin(HttpServletRequest request) {
		try{
			}
			catch(Exception e){
				e.printStackTrace();
			}
		return "login";
	}
	
	@RequestMapping("verifyCode.htm")
	public void service(HttpServletRequest request, HttpServletResponse response) { 
        response.setHeader("Pragma", "No-cache"); 
        response.setHeader("Cache-Control", "no-cache"); 
        response.setDateHeader("Expires", 0); 
        response.setContentType("image/jpeg"); 
           
        //生成随机字串 
        String verifyCode = VerifyCodeUtils.generateVerifyCode(4); 
        //存入会话session 
        HttpSession session = request.getSession(true); 
        //删除以前的
        session.removeAttribute("verCode");
        session.setAttribute("verCode", verifyCode.toLowerCase()); 
        //生成图片 
        int w = 100, h = 30; 
        try {
			VerifyCodeUtils.outputImage(w, h, response.getOutputStream(), verifyCode);
		} catch (IOException e) {
			e.printStackTrace();
		} 
   
    } 

	@RequestMapping(value = "/login.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String login(@RequestParam("userName") String userName,
			@RequestParam("password") String password,@RequestParam("verityCode") String verityCode, ModelMap model,
			HttpServletRequest request, HttpSession session) {

		try{
			String verCode = session.getAttribute("verCode").toString();
			String upperCase = StringUtils.lowerCase(verityCode);
			if(!verCode.equals(upperCase)){
				return "verifyerror";
			}
		    logger.info("userName:" + userName );
			PersonUser personUser = userService.findByLogin(userName, password);
			
			if (personUser == null ||personUser.getCityId() == null) {
				logger.warn("userName:" + userName);
				return "failure";
			} else {
				List<Privilege> privileges = this.userService.findPrivileges(personUser.getId());
				List<City> citys = this.userCityService.findCityByUserId(personUser.getId());
				City city = this.cityService.findById(personUser.getCityId());
				personUser.setPrivileges(privileges);
				personUser.setCitys(citys);
				personUser.setCityName(city.getCityName());
				session.setAttribute(Constant.USER_KEY, personUser);

				SessionHelper.setUser(session,personUser);

				this.saveLog(session,personUser, "用户登录，用户："+personUser.getUserName(),personUser.getCityId());

				if (session.getAttribute(Constant.LOGIN_REDIRECT_URL) != null) {
					String tmpUrl = session
							.getAttribute(Constant.LOGIN_REDIRECT_URL) + "";
					session.setAttribute(Constant.LOGIN_REDIRECT_URL, null);
					return tmpUrl;
				} else {
					return "welcome.htm";
				}
			}
		}catch(Exception e){
            logger.error("登录出错",e);
            return "登录失败";
		}
	}

	@RequestMapping(value = "/appLogin.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String applogin(@RequestParam("account") String userName,
			@RequestParam("password") String password, ModelMap model,
			HttpServletRequest request, HttpSession session) {

		if (logger.isDebugEnabled()) {
			logger.debug("userName:" + userName + ", password:" + password);
		}


		PersonUser personUser = userService.findByLogin(userName, password);

		if(personUser == null){
			personUser = userService.findByPhone(userName);
			if(personUser!=null){
				if(EncryptHelper.md5(password).equals(personUser.getPassWord())){

				}else{
					personUser = null;
				}
			}
		}
		if (personUser == null) {
			return "failure";
		} else {
			session.setAttribute(Constant.USER_APP_KEY, personUser);


			/*if (session.getAttribute(Constant.LOGIN_APP_REDIRECT_URL) != null) {
				String tmpUrl = session
						.getAttribute(Constant.LOGIN_APP_REDIRECT_URL) + "";
				session.setAttribute(Constant.LOGIN_APP_REDIRECT_URL, null);
				return tmpUrl;
			} else {
		    	// 判断是移动还是pc,如果是移动，则。。。；如果是pc

			}*/
			/*
			if (isFromPC(request)) {
	    		return "/static/pc_index.htm";
	    	} else {
	    		return "/static/index.htm";
	    	}
	    	*/

			return "/appOrder/appPriceList.htm";


		}
	}

    private boolean isFromPC(HttpServletRequest request) {
    	boolean result = false;

    	String userAgentInfo = request.getHeader("User-Agent");
    	String info = userAgentInfo.toUpperCase();

    	logger.warn("userAgentInfo:" + info);

//    	if (info.indexOf("WINDOWS") >= 0 || info.indexOf("MAC OS") >=0) {
//    		result = true;
//    	}
        result=!check(info);
    	return result;
    }

	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	public ModelAndView logout(HttpServletRequest request, HttpSession session) {
		try{
			PersonUser dto = SessionHelper.getUser(session);
			this.saveLog(session,dto, "用户注销，用户："+dto.getUserName(),dto.getCityId());
			session.setAttribute(Constant.USER_KEY, null);

		}catch(Exception e){
			logger.error("注销失败",e);
		}
		return new ModelAndView("redirect:/adminLogin.htm");
	}

	@RequestMapping("/welcome.htm")
	public ModelAndView welcome(HttpServletRequest request) {
		return new ModelAndView("welcome");
	}

	private void saveLog(HttpSession session,PersonUser dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setCityId(cityId);
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.USER_LOGIN.getName());
	       sysLog.setDataId(dto.getId().toString());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<PersonUser>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
