package com.b2b.web.pc.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.common.domain.Advisory;
import com.b2b.common.domain.Item;
import com.b2b.common.domain.StandardOrder;
import com.b2b.page.Page;
import com.b2b.service.AppAdvisoryService;
import com.b2b.service.ItemService;
import com.b2b.service.StandardOrderService;
import com.b2b.web.util.LWResult;


@RequestMapping("official")
@Controller
public class OfficialController {
	private static final Logger logger = LoggerFactory.getLogger(OfficialController.class);
	
	@Autowired
	StandardOrderService standardOrderService;
	
	@Autowired
	AppAdvisoryService appAdvisoryService;
	
	@Autowired
	ItemService ItemService;
	
	@RequestMapping("/pcIndex.htm")
	public ModelAndView index(HttpServletRequest request) {
            return new ModelAndView("official/pcIndex");
	}
	
	@RequestMapping(value = "/phoneIndex.htm")
	public ModelAndView appIndex() {
		 return new ModelAndView("official/phoneIndex");
	}
	
}
