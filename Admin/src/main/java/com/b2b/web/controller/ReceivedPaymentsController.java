package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.DebitNoteDemo;
import com.b2b.common.domain.PersonUser;
import com.b2b.page.Page;
import com.b2b.service.DebitNoteService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RequestMapping("received")
@Controller
public class ReceivedPaymentsController {
	
	@Autowired
	DebitNoteService debitNoteService;
	
	@RequestMapping("receivedPaymentsReport.htm")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView view = new ModelAndView("report/receivedList");
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		try {
			String type = request.getParameter("type");
			view.addObject("type", type);
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
			PageHelper.startPage(currentPage, 30);
			List<DebitNoteDemo> lists = this.debitNoteService.findReceivedPaymentsReport(type,personUser.getCityId());
			PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(lists);
			Page<DebitNoteDemo> page = new Page<DebitNoteDemo>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			view.addObject("page", page);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping("receivedPaymentsReportDetail.htm")
	public ModelAndView detailList(HttpServletRequest request){
		ModelAndView view = new ModelAndView("report/receivedDetailList");
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		try {
			String type = request.getParameter("type");
			view.addObject("type", type);
			String queryDate = request.getParameter("queryDate");
			view.addObject("queryDate", queryDate);
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
			PageHelper.startPage(currentPage, 30);
			List<DebitNoteDemo> lists = this.debitNoteService.findReceivedPaymentsDetailReport(type,queryDate,personUser.getCityId());
			PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(lists);
			Page<DebitNoteDemo> page = new Page<DebitNoteDemo>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			view.addObject("page", page);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		TestController.getMenuPoint(view, request);
		return view;
	}
}
