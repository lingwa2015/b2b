package com.b2b.web.app.controller;

import com.b2b.common.domain.Advisory;
import com.b2b.common.domain.SysLog;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.AppAdvisoryService;
import com.b2b.service.LogService;
import com.b2b.web.controller.TestController;
import com.b2b.web.util.SessionHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
@RequestMapping("appAdvisory")
public class AppAdvisoryController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AppAdvisoryService appAdvisoryService;
	
	@Autowired
	private LogService logService;
	
	@RequestMapping(value="appAdd.do",method = RequestMethod.POST)
	public String addAdvisory(HttpServletRequest request,@RequestParam("name")String name,@RequestParam("mobilePhone")String mobilePhone,@RequestParam("companyName")String companyName){
		try {
			Advisory advisory = new Advisory();
			advisory.setCompanyName(companyName);
			advisory.setMobilePhone(mobilePhone);
			advisory.setName(name);
			advisory.setCreated(new Date());
			this.appAdvisoryService.insertSelective(advisory);
		
			
			
			this.saveLog(request.getSession(), advisory, "添加意向客户信息，id：");
			return "redirect:/advisory/appSuccess.htm";
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return "redirect:/advisory/appNew.htm";
		}
	}
	
	@RequestMapping(value="wxSpread.do",method = RequestMethod.POST)
	public String addSpread(HttpServletRequest request,@RequestParam("name")String name,@RequestParam("mobilePhone")String mobilePhone,@RequestParam("companyName")String companyName){
		try {
			Advisory advisory = new Advisory();
			advisory.setCompanyName("*"+companyName);
			advisory.setMobilePhone(mobilePhone);
			advisory.setName(name);
			advisory.setCreated(new Date());
			this.appAdvisoryService.insertSelective(advisory);
			this.saveLog(request.getSession(), advisory, "添加意向客户信息，id：");
			return "redirect:/advisory/wxSuccess.htm";
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return "redirect:/advisory/wxSpread.htm";
		}
	}
	
	@RequestMapping(value="list.htm")
	public ModelAndView advisoryList(HttpServletRequest request){
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		int pageSize = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("pageSize"), Page.DEFAULT_PAGE_SIZE_STR));

		Page<Advisory> page = null;
		
		try {
			page = this.appAdvisoryService.findPage(currentPage,pageSize);
		} catch (Exception e) {
			logger.error("查询咨询信息出错",e);
		}
		ModelAndView view = new ModelAndView("advisory/list");
		view.addObject("page", page);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	private void saveLog(HttpSession session,Advisory dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.STOCK_CHECK.getName());
	       if(dto.getId()!=null){
	       sysLog.setDataId(dto.getId().toString());
	       }

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<Advisory>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
