package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.SysLog;
import com.b2b.common.domain.SysShopLog;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.LogService;
import com.b2b.service.SysShopLogService;
import com.b2b.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/log")
public class LogController {

	 private static final Logger logger = LoggerFactory.getLogger(LogController.class);
	 private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

	@Autowired
	LogService logService;

	@Autowired
	UserService userService;
	
	@Autowired
	SysShopLogService sysShopLogService;

	@RequestMapping("logList.htm")
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("log/list");
		try {
				int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
                    request.getParameter("currentPage"), "1"));
				PersonUser user=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
				if(null == user.getCityId()){
					return new ModelAndView("noCity");
				}
			    String startTimeStr = request.getParameter("startTime");
	            String endTimeStr = request.getParameter("endTime");


	            Date startTime = null;
	            Date endTime = null;

	            String phone = request.getParameter("phone");
	            String content = request.getParameter("content");

	            if (StringUtils.isNotBlank(startTimeStr)) {
	                startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
	                mv.addObject("startTime", startTimeStr);
	            }

	            if (StringUtils.isNotBlank(endTimeStr)) {
	                endTime = DateUtil.parseDateStr(endTimeStr+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
	                mv.addObject("endTime", endTimeStr);
	            }


	            mv.addObject("phone", phone);
	            mv.addObject("content", content);


	            PageHelper.startPage(currentPage, 40);
	            List<SysLog> lists = this.logService.findList(startTime, endTime,phone,content,user.getCityId());
	            PageInfo<SysLog> info = new PageInfo<SysLog>(lists);
			    Page<SysLog> page = new Page<SysLog>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			    
	            mv.addObject("page", page);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<SysLog> page = new Page<SysLog>(1, 1, Page.DEFAULT_PAGE_SIZE, new ArrayList<SysLog>());
			mv.addObject("page", page);
		}
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping("shopLogList.htm")
	public ModelAndView shopLogList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("log/shop_list");
		try {
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
			        request.getParameter("currentPage"), "1"));
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return new ModelAndView("noCity");
			}
			    String startTimeStr = request.getParameter("startTime");
			    String endTimeStr = request.getParameter("endTime");


			    Date startTime = null;
			    Date endTime = null;

			    String content = request.getParameter("content");
			    String company = request.getParameter("company");
			    
			    if (StringUtils.isNotBlank(startTimeStr)) {
			        startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
			        mv.addObject("startTime", startTimeStr);
			    }

			    if (StringUtils.isNotBlank(endTimeStr)) {
			        endTime = DateUtil.parseDateStr(endTimeStr+" 23:59:59", "yyyy-MM-dd HH:mm:ss");
			        mv.addObject("endTime", endTimeStr);
			    }
			    mv.addObject("content", content);
			    mv.addObject("company", company);
			    PageHelper.startPage(currentPage, 40);
			    List<SysShopLog> list = this.sysShopLogService.findList(content,company,startTime,endTime,personUser.getCityId());
			    PageInfo<SysShopLog> info = new PageInfo<SysShopLog>(list);
			    Page<SysShopLog> page = new Page<SysShopLog>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			    mv.addObject("page", page);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			Page<SysShopLog> page = new Page<SysShopLog>(1, 1, 40, new ArrayList<SysShopLog>());
			mv.addObject("page", page);
		}
		TestController.getMenuPoint(mv, request);
		return mv;
	}

}
