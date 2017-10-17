package com.b2b.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.Constant;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.ShopUser;
import com.b2b.common.domain.SysLog;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.service.LogService;
import com.b2b.service.ShopUserService;
import com.b2b.web.util.SessionHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RequestMapping("super")
@Controller
public class SuperAdminController {
	
	private static final Logger logger = LoggerFactory.getLogger(SuperAdminController.class);
	
	@Autowired
	private ShopUserService shopUserService;
	
	@Autowired
	private LogService logService;
	
	@RequestMapping("adminList.htm")
	public ModelAndView list(){
		ModelAndView view = new ModelAndView("super/list");
		List<ShopUser> users = this.shopUserService.findAllSuperAdmin();
		view.addObject("users", users);
		return view;
	}
	
	@RequestMapping("delete.do")
	@ResponseBody
	public String delete(HttpServletRequest request,@RequestParam("id")Integer id){
		try {
			this.shopUserService.changeAdminToComon(id);
			ShopUser user = new ShopUser();
			user.setId(id);
			user.setIsadmin(0);
			this.saveLog(request.getSession(), user, "删除便利店后台超级管理员："+id);
			return "200";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "201";
	}
	
	private void saveLog(HttpSession session,ShopUser dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.Shoper.getName());
	       sysLog.setDataId(dto.getId().toString());

	       if(dto!=null){
		       String dataContent = new Gson().toJson(dto,
						new TypeToken<ShopUser>() {
						}.getType());

		       sysLog.setDataContent(dataContent);
	       }

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
