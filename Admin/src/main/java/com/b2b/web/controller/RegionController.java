package com.b2b.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.b2b.common.domain.Region;
import com.b2b.service.RegionService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("region")
@Controller
public class RegionController {
	private static final Logger logger = LoggerFactory.getLogger(RegionController.class);
	@Autowired
	private RegionService regionService;
	
	@RequestMapping("query.do")
	@ResponseBody
	public List<Region> findAll(){
		return this.regionService.findAll();
	}
}
