package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.StockMoneyMonitor;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.StockMoneyMonitorService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping("monitor")
public class StockMoneyMonitorController {
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd hh:mm:ss";
	@Autowired
	private StockMoneyMonitorService stockMoneyMonitorService;
	
	@RequestMapping("mointorList.htm")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("stock/monitorList");
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		int pageSize = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("pageSize"), Page.DEFAULT_PAGE_SIZE_STR));
		String startTimeStr = request.getParameter("startTime");
		String endTimeStr = request.getParameter("endTime");
		Date startTime = null;
		Date endTime = null;

		if (StringUtils.isNotBlank(startTimeStr)) {
			String startTimeStr1 = startTimeStr + " 00:00:00";
			startTime = DateUtil.parseDateStr(startTimeStr1, DATE_FORMAT_YMD);
			mv.addObject("startTime", startTimeStr);
		}

		if (StringUtils.isNotBlank(endTimeStr)) {
			String endTimeStr1 = endTimeStr + " 23:59:59";
			endTime = DateUtil.parseDateStr(endTimeStr1, DATE_FORMAT_YMD);
			mv.addObject("endTime", endTimeStr);
		}
		Page<StockMoneyMonitor> page = this.stockMoneyMonitorService.pageList(currentPage,pageSize,startTime,endTime,user.getCityId());
		mv.addObject("page", page);
		TestController.getMenuPoint(mv, request);
		return mv;
	}
}
