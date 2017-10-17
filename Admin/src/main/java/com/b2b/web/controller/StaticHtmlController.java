package com.b2b.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/static")
public class StaticHtmlController {

	private static final Logger logger = LoggerFactory.getLogger(StaticHtmlController.class);

	@RequestMapping("/index.htm")
	public ModelAndView index(HttpServletRequest request) {
            return new ModelAndView("static/index");
	}
	
	@RequestMapping("/pc_index.htm")
	public ModelAndView pcIndex(HttpServletRequest request) {
		return new ModelAndView("static/pc_index");
	}
        
	@RequestMapping("/about.htm")
	public ModelAndView about(HttpServletRequest request) {
		return new ModelAndView("static/about");
	}
	
	@RequestMapping("/test.htm")
	public ModelAndView test(HttpServletRequest request) {
		return new ModelAndView("demo/test");
	}
}
