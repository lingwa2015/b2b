package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.page.Page;
import com.b2b.service.*;
import com.b2b.web.util.LWResult;
import com.b2b.web.util.SessionHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("afterSalesRecord")
@Controller
public class AfterSalesRecordController {
	private static final Logger logger = LoggerFactory
			.getLogger(AfterSalesRecordController.class);
	@Autowired
	AfterSalesRecordService afterSalesRecordService;
	
	@Autowired
	CustomerService customerService; 
	
	@Autowired
	UserService userService;
	
	@Autowired
	ReseauService reseauService;
	
	@Autowired
	LogService logService;
	
	@Autowired
	CityRegionService cityRegionService;
	
	@RequestMapping("add.htm")
	public ModelAndView add(HttpServletRequest request){
		ModelAndView view = new ModelAndView("afterSalesRecord/add");
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		List<PersonUser> fzusers = this.userService.findUserkfFirstByCityId(user.getCityId());
		view.addObject("fzusers", fzusers);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping(value="add.do",method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String save(HttpServletRequest request,AfterSalesRecord record){
		try {
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			String id = request.getParameter("customerId");
			if(StringUtils.isEmpty(id)){
				return "请选择客户";
			}
			if(!StringUtils.isEmpty(id)){
				record.setUserId(Integer.valueOf(id));
			}
			if(user.getOpenid()==null){
				return "未绑定openid";
			}
//			CustomerUser customerUser = this.customerService.findById(Integer.valueOf(id));
//			if(null!=customerUser.getRegion()){
//				record.setRegion(customerUser.getRegion());
//			}
			record.setCityId(user.getCityId());
			record.setRecordMan(user.getOpenid());
			record.setStatus(1);
			record.setCreatedTime(new Date());
			this.afterSalesRecordService.insert(record);
			this.saveLog(request.getSession(), record, "新建售后记录", user.getCityId());
			return "成功";
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return "失败";
	}
	
	@RequestMapping("edit.htm")
	public ModelAndView edit(HttpServletRequest request,@RequestParam("id")Integer id){
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView view = new ModelAndView("afterSalesRecord/edit");
		List<PersonUser> fzusers = this.userService.findUserkfFirstByCityId(user.getCityId());
		AfterSalesRecord record = this.afterSalesRecordService.findById(id);
		view.addObject("record", record);
		view.addObject("fzusers", fzusers);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping(value="edit.do",method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String doedit(HttpServletRequest request,AfterSalesRecord record){
		try {
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			AfterSalesRecord salesRecord = this.afterSalesRecordService.findById(record.getId());
			if(null == salesRecord || !salesRecord.getCityId().equals(user.getCityId())){
				return "你设置的默认城市与实际不符";
			}
			String id = request.getParameter("customerId");
			if(!StringUtils.isEmpty(id)){
				record.setUserId(Integer.valueOf(id));
			}
//			CustomerUser customerUser = this.customerService.findById(Integer.valueOf(id));
//			if(null!=customerUser.getRegion()){
//				record.setRegion(customerUser.getRegion());
//			}
			this.afterSalesRecordService.update(record);
			this.saveLog(request.getSession(), record, "更新售后记录", user.getCityId());
			return "成功";
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return "失败";
	}
	
	@RequestMapping("afterSalesRecordList.htm")
	public ModelAndView list(HttpServletRequest request,@RequestParam(value="reseauId",defaultValue="-1",required=false)Integer reseauId){
		ModelAndView view = new ModelAndView("afterSalesRecord/list");
		TestController.getMenuPoint(view, request);
		try {
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return new ModelAndView("noCity");
			}
			String userName = request.getParameter("userName");
			String recordMan = request.getParameter("recordMan");
			String recordType = request.getParameter("recordType");
			String doState = request.getParameter("doState");
			String region = request.getParameter("regionId");
			String fuzeMan = request.getParameter("fuzeMan");
			view.addObject("userName", userName);
			view.addObject("recordMan", recordMan);
			view.addObject("recordType", recordType);
			view.addObject("doState", doState);
			view.addObject("fuzeMan", fuzeMan);
			if(reseauId==-1){
				reseauId=null;
			}
			Integer regionId = null;
			if(!StringUtils.isEmpty(region)){
				regionId = Integer.valueOf(region);
			}
			view.addObject("regionId", regionId);
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
			PageHelper.startPage(currentPage, 50);
			List<AfterSalesRecord> records = this.afterSalesRecordService.findByCondition(userName,recordMan,fuzeMan,recordType,doState,regionId,reseauId,user.getCityId());
			PageInfo<AfterSalesRecord> info = new PageInfo<AfterSalesRecord>(records);
			Page<AfterSalesRecord> page = new Page<AfterSalesRecord>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			List<Reseau> lists = this.reseauService.findAllByCityId(user.getCityId());
			List<CityRegion> regions = this.cityRegionService.findByCityId(user.getCityId());
			view.addObject("regions", regions);
			view.addObject("reseaus", lists);
			view.addObject("reseauId", reseauId);
			view.addObject("page", page);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			Page<AfterSalesRecord> page = new Page<AfterSalesRecord>(1, 1, 50, new ArrayList<AfterSalesRecord>());
			view.addObject("page", page);
		}
		return view;
	}
	
	@RequestMapping("delete.do")
	@ResponseBody
	public String delete(HttpServletRequest request,@RequestParam("id")Integer id){
		try {
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return "202";
			}
			AfterSalesRecord salesRecord = this.afterSalesRecordService.findById(id);
			if(null == salesRecord || !salesRecord.getCityId().equals(user.getCityId())){
				return "203";
			}
			this.afterSalesRecordService.delete(id);
			this.saveLog(request.getSession(), salesRecord, "删除售后记录", user.getCityId());
			return "200";
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return "201";
	}
	
	@RequestMapping("updateState.do")
	@ResponseBody
	public LWResult updateState(@RequestParam("id")Integer id){
		try {
			AfterSalesRecord salesRecord = this.afterSalesRecordService.findById(id);
			if(1==salesRecord.getDoState()){
				salesRecord.setDoState(2);
				this.afterSalesRecordService.update(salesRecord);
				return LWResult.build(200,"处理中");
			}else if (2==salesRecord.getDoState()) {
				salesRecord.setDoState(3);
				this.afterSalesRecordService.update(salesRecord);
				return LWResult.build(200,"已处理");
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return LWResult.build(201, "异常");
		}
		return LWResult.build(202, "无更新");
	}
	
	private void saveLog(HttpSession session,AfterSalesRecord dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setCityId(cityId);
	       sysLog.setDataType("AfterSales");
	       sysLog.setDataId(dto.getId().toString());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<AfterSalesRecord>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
	
}
