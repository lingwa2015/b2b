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
import com.b2b.page.Page;
import com.b2b.service.DailyReportService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RequestMapping("dailyReport")
@Controller
public class DailyReportController {
	private static final Logger logger = LoggerFactory
			.getLogger(DailyReportController.class);
	@Autowired
	DailyReportService dailyReportService;
	
	@RequestMapping("reportList.htm")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView view = new ModelAndView("report/list");
		try {
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
			        request.getParameter("currentPage"), "1"));
			PageHelper.startPage(currentPage, 50);
			List<DailyReport> reports = this.dailyReportService.findList();
			PageInfo<DailyReport> info = new PageInfo<DailyReport>(reports);
			Page<DailyReport> page = new Page<DailyReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			view.addObject("page", page);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			Page<DailyReport> page = new Page<DailyReport>(1, 1, 50, new ArrayList<DailyReport>());
			view.addObject("page", page);
		}
		return view;
	}
}
