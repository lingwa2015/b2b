package com.b2b.web.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.common.domain.WXRechargeRecord;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.WXRechargeRecordService;

@RequestMapping("record")
@Controller
public class WXReChargeRecordController {
	private static final Logger logger = LoggerFactory.getLogger(WXReChargeRecordController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	
	@Autowired
	private WXRechargeRecordService wxRechargeRecordService;
	
	@RequestMapping(value="/recordlist.htm", produces="text/html;charset=UTF-8")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("record/list");
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		String startTimeStr = request.getParameter("startTime");
		String endTimeStr = request.getParameter("endTime");


		Date startTime = null;
		Date endTime = null;

		if (StringUtils.isNotBlank(startTimeStr)) {
			startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
			mv.addObject("startTime", startTimeStr);
		}

		if (StringUtils.isNotBlank(endTimeStr)) {
			endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YMD);
			mv.addObject("endTime", endTimeStr);
		}
		String userName = request.getParameter("userName");
		if(StringUtils.isNotEmpty(userName)){
			mv.addObject("userName", userName);
		}
		Page<WXRechargeRecord> page = this.wxRechargeRecordService.findPageByCondition(currentPage,Page.DEFAULT_PAGE_SIZE,startTime,endTime,userName);
		mv.addObject("page", page);
		return mv;
	}
}
