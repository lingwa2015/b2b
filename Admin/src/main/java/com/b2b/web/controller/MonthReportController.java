package com.b2b.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.common.domain.DailyReport;
import com.b2b.common.domain.MonthReport;
import com.b2b.page.Page;
import com.b2b.service.MonthReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RequestMapping("monthreport")
@Controller
public class MonthReportController {
	private static final Logger logger = LoggerFactory
			.getLogger(MonthReportController.class);
	@Autowired
	MonthReportService monthReportService;
	
	@RequestMapping("monthReportList.htm")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView view = new ModelAndView("monthreport/list");
		try {
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
			        request.getParameter("currentPage"), "1"));
			PageHelper.startPage(currentPage, 50);
			List<MonthReport> reports = this.monthReportService.findList();
			PageInfo<MonthReport> info = new PageInfo<MonthReport>(reports);
			Page<MonthReport> page = new Page<MonthReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			view.addObject("page", page);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			Page<MonthReport> page = new Page<MonthReport>(1, 1, 50, new ArrayList<MonthReport>());
			view.addObject("page", page);
		}
		return view;
	}
}
