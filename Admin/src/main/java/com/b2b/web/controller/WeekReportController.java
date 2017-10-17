package com.b2b.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.common.domain.ShopWeekReport;
import com.b2b.common.domain.WeekReport;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.WeekReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RequestMapping("weekReport")
@Controller
public class WeekReportController {
	private static final Logger logger = LoggerFactory
			.getLogger(WeekReportController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	
	@Autowired
	WeekReportService weekReportService;
	
	@RequestMapping("weekReportList.htm")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView view = new ModelAndView("weekReport/list");
		try {
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));
			PageHelper.startPage(currentPage, 50);
			List<WeekReport> shopDailyReports= this.weekReportService.findAll();
			PageInfo<WeekReport> info = new PageInfo<WeekReport>(shopDailyReports);
			Page<WeekReport> page = new Page<WeekReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			view.addObject("page", page);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			Page<WeekReport> page = new Page<WeekReport>(1, 1, 50, new ArrayList<WeekReport>());
			view.addObject("page", page);
		}
		return view;
	}
}
