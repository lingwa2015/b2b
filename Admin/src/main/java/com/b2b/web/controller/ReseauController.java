package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.Reseau;
import com.b2b.common.domain.SysLog;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.LogService;
import com.b2b.service.ReseauService;
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
import java.util.Date;
import java.util.List;

@RequestMapping("reseau")
@Controller
public class ReseauController {
	private static final Logger logger = LoggerFactory.getLogger(ReseauController.class);
	@Autowired
	private LogService logService;
	
	@Autowired
	private ReseauService reseauService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("add.htm")
	public ModelAndView addhtm(HttpServletRequest request){
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView view = new ModelAndView("/reseau/add");
		List<PersonUser> users = this.userService.findUserkhjlFirstByCityId(user.getCityId());
		view.addObject("users", users);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping(value="add.do",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String doadd(@RequestParam("name")String name,@RequestParam("remark")String remark,@RequestParam("managerId")Integer managerId,HttpServletRequest req){
		try {
			PersonUser personUser = (PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			int id = Integer.valueOf(StringUtils.defaultIfBlank(
					req.getParameter("id"), "0"));
			if(0==id){
				Reseau reseau = new Reseau();
				reseau.setName(name);
				reseau.setRemark(remark);
				reseau.setCityId(personUser.getCityId());
				reseau.setManagerId(managerId);
				this.reseauService.save(reseau);
				this.saveLog(req.getSession(), reseau, "添加网格");
				return "成功";
			}else{
				Reseau reseau = new Reseau();
				reseau.setId(id);
				reseau.setName(name);
				reseau.setRemark(remark);
				reseau.setManagerId(managerId);
				this.reseauService.update(reseau);
				this.saveLog(req.getSession(), reseau, "编辑网格");
				return "成功";
			}
		} catch (Exception e) {
			logger.error("添加网格异常："+e);
			return "添加失败";
		}
	}
	
	@RequestMapping("reseauList.htm")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView view = new ModelAndView("/reseau/list");
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		PageHelper.startPage(currentPage, 50);
		List<Reseau> reseaus = this.reseauService.findAllWithManageNameByCityId(user.getCityId());
		PageInfo<Reseau> info = new PageInfo<Reseau>(reseaus);
		Page<Reseau> page = new Page<Reseau>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		view.addObject("page", page);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping("edit.htm")
	public ModelAndView edithtm(@RequestParam("id")Integer id,HttpServletRequest req){
		ModelAndView view = new ModelAndView("/reseau/add");
		PersonUser user = (PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		Reseau reseau = this.reseauService.findById(id);
		if(null==reseau && reseau.getCityId() != user.getCityId() ){
			return new ModelAndView("notCity");
		}
		List<PersonUser> users = this.userService.findUserkhjlFirstByCityId(user.getCityId());
		view.addObject("users", users);
		view.addObject("reseau", reseau);
		TestController.getMenuPoint(view, req);
		return view;
	}
	
	@RequestMapping(value="delete.do",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String dodelete(@RequestParam("id")Integer id,HttpServletRequest req){
		try {
			PersonUser personUser = (PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			Reseau reseau = this.reseauService.findById(id);
			if(null!=reseau && reseau.getCityId() == personUser.getCityId() ){
				this.reseauService.delete(id);
				this.saveLog(req.getSession(), reseau, "删除网格");
			}else{
				return "你设置的默认城市与实际不符";
			}
			return "200";
		} catch (Exception e) {
			logger.error("删除网格异常："+e);
			return "201";
		}
	}
	
	private void saveLog(HttpSession session,Reseau dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setCityId(dto.getCityId());
	       sysLog.setDataType(LogDataTypeEnum.Reseau.getName());
	       if(dto.getId()!=null){
	       sysLog.setDataId(dto.getId().toString());
	       }

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<Reseau>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
