package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.City;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.SysLog;
import com.b2b.common.domain.UserCity;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.CityService;
import com.b2b.service.LogService;
import com.b2b.service.UserCityService;
import com.b2b.service.UserService;
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

@RequestMapping("GM")
@Controller
public class GmController {
	private static final Logger logger = LoggerFactory.getLogger(GmController.class);
	
	@Autowired
	LogService logService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	UserCityService userCityService;
	
	@RequestMapping("gmlist")
	public ModelAndView userlist(HttpServletRequest request){
		ModelAndView view = new ModelAndView("GM/userlist");
		try {
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));
			PageHelper.startPage(currentPage, 30);
			List<PersonUser> users = this.userService.findAll();
			PageInfo<PersonUser> info = new PageInfo<PersonUser>(users);
			Page<PersonUser> page = new Page<PersonUser>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			view.addObject("page", page);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping("editCity.htm")
	public ModelAndView editCity(@RequestParam("userId")int userId, HttpServletRequest request){
		ModelAndView view = new ModelAndView("/GM/editCity");
		PersonUser user = this.userService.findById(userId);
		view.addObject("user", user);
		List<City> citys = this.cityService.findAllOpenCity();
		List<UserCity> exist = this.userCityService.findByUserId(userId);
		view.addObject("citys", citys);
		view.addObject("exist", exist);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping(value="editCity.do",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String editCitydo(HttpServletRequest req){
		try {
			PersonUser pUser = (PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			int userId = Integer.parseInt(StringUtils.defaultIfBlank(
					req.getParameter("userId"), "0"));
			if(userId==0){
				return "请选择用户进行操作!";
			}
			PersonUser personUser = this.userService.findById(userId);
			int num = Integer.parseInt(StringUtils.defaultIfBlank(
					req.getParameter("num"), "0"));
			int flag = 0;
			ArrayList<UserCity> list = new ArrayList<UserCity>();
			for (int i = 1; i <= num; i++) {
				Integer cityId = Integer.parseInt(StringUtils.defaultIfBlank(
						req.getParameter("checkbox" + i), "0"));
				if (cityId == 0) {
					continue;
				}
				Integer cityId2 = personUser.getCityId();
				if(cityId.equals(cityId2) ){
					flag = 1;
				}
				UserCity userCity = new UserCity();
				userCity.setCityId(cityId);
				userCity.setUserId(userId);
				list.add(userCity);
			}
			this.userCityService.updateCity(personUser,list,flag);
			this.saveLog(req.getSession(), personUser, "设置为GM", pUser.getCityId());
			return "添加成功";
		} catch (NumberFormatException e) {
			logger.error("设置默认城市失败"+e);
			return "添加失败"+e;
		}
	}
	
	@RequestMapping(value="setgm.do",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String setGm(HttpServletRequest request,@RequestParam("userId")int userId){
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			PersonUser user = this.userService.findById(userId);
			user.setIsGm(1);
			this.userService.update(user);
			this.saveLog(request.getSession(), user, "设置为GM", personUser.getCityId());
			return "200";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "201";
	}
	
	@RequestMapping(value="cancelgm.do",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String cancelGm(HttpServletRequest request,@RequestParam("userId")int userId){
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			PersonUser user = this.userService.findById(userId);
			user.setIsGm(0);
			this.userService.update(user);
			this.saveLog(request.getSession(), user, "取消GM", personUser.getCityId());
			return "200";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "201";
	}
	
	private void saveLog(HttpSession session,PersonUser dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setCityId(cityId);
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.USER.getName());
	       sysLog.setDataId(dto.getId().toString());
	       String dataContent = new Gson().toJson(dto,
					new TypeToken<PersonUser>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
