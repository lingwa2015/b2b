package com.b2b.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class ErrorHandleController {

	@RequestMapping("errorPage.htm")
	public ModelAndView getErrorPage(Exception ex) {
		ModelAndView mv = new ModelAndView("errorPage");
		mv.addObject("ex", ex);
		return mv;
	}
	
	@RequestMapping(value="error404.htm")
	public ModelAndView show404() {
		ModelAndView mv = new ModelAndView("404page");
		mv.addObject("errorCode", "404");
		return mv;
	}
	
	@RequestMapping(value="noPrivilege.htm")
	public ModelAndView noPrivilege() {
		ModelAndView mv = new ModelAndView("noPrivilege");
		mv.addObject("errorCode", "权限不足！请联系管理员");
		return mv;
	}
}
