package com.b2b.web.app.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.Constant;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.util.EncryptHelper;
import com.b2b.service.CustomerService;
import com.b2b.service.UserService;
import com.b2b.web.util.CustomerSessionHelper;
import com.b2b.web.util.SessionHelper;

@Controller
@RequestMapping("/appLogin")
public class AppLoginController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	CustomerService customerService;

	@RequestMapping(value = "appLogin.htm")
	public ModelAndView login() {
		return new ModelAndView("app/login/login");

	}

	@RequestMapping(value = "appLogout.htm")
	public ModelAndView logout(HttpServletRequest request, HttpSession session) {
		session.setAttribute(Constant.USER_KEY, null);
		//return new ModelAndView("redirect:/appItem/appGoods.htm");
		return new ModelAndView("redirect:index.htm");
	}

	@RequestMapping(value = "/appLogin.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	public ModelAndView login(@RequestParam("account") String userName,
			@RequestParam("password") String password, ModelMap model,
			HttpServletRequest request, HttpSession session) {

		CustomerUser personUser = customerService.findByLogin(userName, password);

		if(personUser == null){
			personUser = customerService.findByPhone(userName);
			if(personUser!=null){
				if(EncryptHelper.md5(password).equals(personUser.getPassWord())){

				}else{
					personUser = null;
				}
			}
		}
		if (personUser == null) {
			return new ModelAndView("redirect:/appLogin/appLogin.htm");
		} else {
			session.setAttribute(Constant.USER_APP_KEY, personUser);

			CustomerSessionHelper.setAppUser(session,personUser);

			/*
			if (session.getAttribute(Constant.LOGIN_APP_REDIRECT_URL) != null) {
				String tmpUrl = session
						.getAttribute(Constant.LOGIN_APP_REDIRECT_URL) + "";
				session.setAttribute(Constant.LOGIN_APP_REDIRECT_URL, null);
				return new ModelAndView("redirect:"+tmpUrl);
			} else {
				return new ModelAndView("redirect:/appItem/appItemList.htm");
			}
			*/

			logger.error("ceshi 跳转.....................");
			//默认跳到订单页
			return new ModelAndView("redirect:/appOrder/appPriceList.htm");
		}
	}

	@RequestMapping(value = "/appLogout.htm", method = RequestMethod.GET)
	public ModelAndView appLogout(HttpServletRequest request, HttpSession session) {
		session.setAttribute(Constant.USER_APP_KEY, null);
		//return new ModelAndView("redirect:/appItem/appGoods.htm");
		return new ModelAndView("redirect:/index.htm");
	}

	@RequestMapping("/welcome.htm")
	public ModelAndView welcome(HttpServletRequest request) {
		return new ModelAndView("welcome");
	}

}
