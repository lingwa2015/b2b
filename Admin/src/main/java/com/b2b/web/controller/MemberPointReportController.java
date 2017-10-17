package com.b2b.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.Constant;
import com.b2b.common.domain.AfterSalesRecord;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.MemberPoint;
import com.b2b.common.domain.MemberPointReport;
import com.b2b.common.domain.Order;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.SysLog;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.CustomerService;
import com.b2b.service.LogService;
import com.b2b.service.MemberPointReportService;
import com.b2b.service.MemberPointService;
import com.b2b.web.util.SessionHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RequestMapping("memberPoint")
@Controller
public class MemberPointReportController {
	private static final Logger logger = LoggerFactory.getLogger(MemberPointReportController.class);
	@Autowired
	CustomerService customerService;
	
	@Autowired
	MemberPointReportService memberPointReportService;
	
	@Autowired
	MemberPointService memberPointService;
	
	@Autowired
	LogService logService;
	
	@RequestMapping("memberPointList.htm")
	public ModelAndView list(@RequestParam("userid")Integer userid,HttpServletRequest request){
		ModelAndView view = new ModelAndView("memberPoint/list");
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		PageHelper.startPage(currentPage, 50);
		List<MemberPointReport> lists= this.memberPointReportService.findByShopId(userid);
		PageInfo<MemberPointReport> info = new PageInfo<MemberPointReport>(lists);
		Page<MemberPointReport> page = new Page<MemberPointReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		MemberPoint memberPoint = this.memberPointService.findByid(userid);
		view.addObject("page", page);
		view.addObject("memberPoint", memberPoint);
		view.addObject("userid", userid);
		return view;
	}
	
	@RequestMapping("add.htm")
	public ModelAndView addhtml(@RequestParam("userid")Integer userid){
		ModelAndView view = new ModelAndView("memberPoint/add");
		CustomerUser user = this.customerService.findById(userid);
		view.addObject("user", user);
		return view;
	}
	
	@RequestMapping("save.do")
	@ResponseBody
	public String save(MemberPointReport point,HttpServletRequest request){
		try {
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你没有设置默认城市";
			}
			CustomerUser customerUser = this.customerService.findById(point.getShopId());
			if(null==customerUser || !customerUser.getCityId().equals(personUser.getCityId())){
				return "你设置的默认城市与操作城市不符";
			}
			point.setCreatedTime(new Date());
			point.setStatus(1);
			if(point.getPoint()==null){
				return "积分不能为空！";
			}
			if(point.getPoint()<0){
				point.setType(3);
			}else{
				point.setType(2);
			}
			this.memberPointReportService.save(point);
			this.saveLog(request.getSession(), point, "后台积分操作",personUser.getCityId());
			return "OK";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
	
	@RequestMapping("delete.do")
	@ResponseBody
	public String delete(@RequestParam("id")Integer id,HttpServletRequest request){
		try {
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你没有设置默认城市";
			}
			MemberPointReport memberPointReport = this.memberPointReportService.findById(id);
			CustomerUser customerUser = this.customerService.findById(memberPointReport.getShopId());
			if(null==customerUser || !customerUser.getCityId().equals(personUser.getCityId())){
				return "你设置的默认城市与操作城市不符";
			}
			this.memberPointReportService.deleteByID(memberPointReport);
			this.saveLog(request.getSession(), memberPointReport, "删除后台积分操作"+id,personUser.getCityId());
			return "OK";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "fail";
	}
	
	private void saveLog(HttpSession session,MemberPointReport dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setCityId(cityId);
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.Point.getName());
	       sysLog.setDataId(dto.getId().toString());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<MemberPointReport>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
