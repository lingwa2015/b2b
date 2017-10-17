package com.b2b.web.app.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.Constant;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.util.EncryptHelper;
import com.b2b.service.CustomerService;
import com.b2b.service.UserService;

@Controller
@RequestMapping("/appUser")
public class AppUserController {

	@Autowired
	CustomerService customerService;

	Logger logger = LoggerFactory.getLogger(AppUserController.class);

	@RequestMapping(value = "/appUserInfo.htm", method = RequestMethod.GET)
	public ModelAndView appUserInfo(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("app/user/userInfo");
		try {
			CustomerUser personUser = (CustomerUser) req.getSession().getAttribute(Constant.USER_APP_KEY);
			mv.addObject("user", personUser);
		} catch (Exception e) {
			logger.error("", e);
		}
		return mv;
	}

	@RequestMapping(value = "/appPartnerList.htm", method = RequestMethod.GET)
	public ModelAndView appPartmerList(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("app/user/partnerList");
		return mv;
	}
        
	@RequestMapping(value = "/appEditUser.do", method = RequestMethod.POST, produces = "text/json;charset=UTF-8")
	public ModelAndView appEditUser(HttpServletRequest req) {

		try {
			req.setCharacterEncoding("utf-8");
			String userIdStr = req.getParameter("id");
			String userName = req.getParameter("userName");
			String userPhone = req.getParameter("mobilePhone");
			String address = req.getParameter("address");
			String passWord = req.getParameter("passWord");

			CustomerUser editUserDto = new CustomerUser();
			editUserDto.setId(Integer.parseInt(userIdStr));
			editUserDto.setUserName(userName);
			editUserDto.setMobilePhone(userPhone);
			editUserDto.setAddress(address);
			if (StringUtils.isNotBlank(passWord)) {
				editUserDto.setPassWord(EncryptHelper.md5(passWord));
			}
			this.customerService.update(editUserDto);

			req.getSession().setAttribute(Constant.USER_APP_KEY, editUserDto);

		} catch (Exception e) {
			logger.error("", e);
		}
		return new ModelAndView("redirect:/appUser/appUserInfo.htm");
	}
}
