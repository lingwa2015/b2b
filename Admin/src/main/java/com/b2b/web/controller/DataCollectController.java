package com.b2b.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.b2b.common.domain.DataCollect;
import com.b2b.common.domain.ShopUser;
import com.b2b.service.DataCollectService;
import com.b2b.web.wx.util.WXSessionHelper;

@RequestMapping("dataCollect")
@Controller
public class DataCollectController {
	@Autowired
	DataCollectService dataCollectService;
	
	
	@RequestMapping(value="shop_save.do",method=RequestMethod.POST)
	@ResponseBody
	public String save(HttpServletRequest request,DataCollect dataCollect){
		try {
			dataCollect.setCreatedTime(new Date());
			this.dataCollectService.save(dataCollect);
			return "200";
		} catch (Exception e) {
			return "201";
		}
	}
}
