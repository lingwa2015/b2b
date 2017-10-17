package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.NewCustomerActivity;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.Reseau;
import com.b2b.common.domain.SysLog;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.LogService;
import com.b2b.service.NewCustomerActivityService;
import com.b2b.service.ReseauService;
import com.b2b.web.util.SessionHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@RequestMapping("newCustomerActivity")
@Controller
public class NewCustomerActivityController {
	private static final Logger logger = LoggerFactory.getLogger(NewCustomerActivityController.class);
	
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	
	@Autowired
	NewCustomerActivityService newCustomerActivityService;
	
	@Autowired
	LogService logService;
	
	@Autowired
	ReseauService reseauService;
	
	@RequestMapping("add.htm")
	public ModelAndView add(HttpServletRequest request){
		ModelAndView view = new ModelAndView("activity/add");
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping(value="save.do",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest request,NewCustomerActivity dto){
		try {
			PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			String startTimeStr = request.getParameter("startTimestr");
			String endTimeStr = request.getParameter("endTimestr");
			if (StringUtils.isNotBlank(startTimeStr) && StringUtils.isNotBlank(endTimeStr)) {
				Date startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
				Date endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YMD);
				dto.setStartTime(startTime);
				dto.setEndTime(endTime);
			}
			
		    if(dto.getEndTime().before(dto.getStartTime())){
		    	return "201";
		    }
		    List<NewCustomerActivity> one = this.newCustomerActivityService.findByDateAndCityId(dto.getStartTime(), pUser.getCityId());
		    List<NewCustomerActivity> two = this.newCustomerActivityService.findByDateAndCityId(dto.getEndTime(),pUser.getCityId());
		    List<NewCustomerActivity> three = this.newCustomerActivityService.findByStartAndEndTimeAndCityId(dto.getStartTime(),dto.getEndTime(),pUser.getCityId());
		    if(!one.isEmpty() || !two.isEmpty() || !three.isEmpty()){
				return "201";
			}
			dto.setStatus(1);
			dto.setCreatedTime(new Date());
			dto.setCityId(pUser.getCityId());
			this.newCustomerActivityService.save(dto);
			this.saveLog(request.getSession(), dto, "新建新客户活动", pUser.getCityId());
			return "200";
		} catch (Exception e) {
			logger.error("新建新客户活动失败"+e.getMessage());
			return "新建新客户活动失败"+e.getMessage();
		}
	}
	
	@RequestMapping("newCustomerActivityList.htm")
    public ModelAndView newCustomerActivityList(HttpServletRequest request){
		PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == pUser.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView view = new ModelAndView("activity/list");
    	List<NewCustomerActivity> lists = this.newCustomerActivityService.findByCondition(pUser.getCityId());
    	view.addObject("lists", lists);
		TestController.getMenuPoint(view, request);
    	return view;
    }
	
	@RequestMapping("delete.do")
	@ResponseBody
    public String delete(HttpServletRequest request,@RequestParam("id")Integer id){
		try {
			PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			NewCustomerActivity dto = new NewCustomerActivity();
			dto.setId(id);
			dto.setStatus(0);
			this.newCustomerActivityService.delete(dto);
			this.saveLog(request.getSession(), dto, "删除新客户活动", pUser.getCityId());
			return "200";
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return "201";
		}
		
    }
	
	@RequestMapping("preferentialList.htm")
	public ModelAndView list(HttpServletRequest request,@RequestParam(value="reseauId",defaultValue="-1",required=false)Integer reseauId){
		PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == pUser.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView view = new ModelAndView("activity/preferential");
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		String startTimeStr = request.getParameter("startTime");
		String endTimeStr = request.getParameter("endTime");

		Date startTime = null;
		Date endTime = null;

		if (StringUtils.isNotBlank(startTimeStr)) {
			startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
			view.addObject("startTime", startTimeStr);
		}

		if (StringUtils.isNotBlank(endTimeStr)) {
			endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YMD);
			view.addObject("endTime", endTimeStr);
		}
		
		String userName = request.getParameter("userName");
		view.addObject("userName", userName);
		
		if(reseauId==-1){
			reseauId=null;
		}
		view.addObject("reseauId", reseauId);
		PageHelper.startPage(currentPage, Page.DEFAULT_PAGE_SIZE);
		List<NewCustomerActivity> lists = this.newCustomerActivityService.findByConditionAndCityId(startTime,endTime,userName,reseauId,pUser.getCityId());
		
		PageInfo<NewCustomerActivity> info = new PageInfo<NewCustomerActivity>(lists);
		
		Page<NewCustomerActivity> page = new Page<NewCustomerActivity>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		Long fee1 = this.newCustomerActivityService.findTotalFeeByConditionAndCityId(startTime,endTime,userName,reseauId,pUser.getCityId());
		List<Reseau> reseaus = this.reseauService.findAllByCityId(pUser.getCityId());
		view.addObject("page", page);
		view.addObject("fee1", fee1);
		view.addObject("reseaus", reseaus);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	private void saveLog(HttpSession session,NewCustomerActivity dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setCityId(cityId);
	       sysLog.setDataType("新客户活动");
	       sysLog.setDataId(dto.getId().toString());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<NewCustomerActivity>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
