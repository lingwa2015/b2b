package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.Privilege;
import com.b2b.common.domain.Role;
import com.b2b.common.domain.SysLog;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.LogService;
import com.b2b.service.PrivilegeService;
import com.b2b.service.RoleService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("role")
@Controller
public class RoleController {
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Autowired
	LogService logService;
	
	@Autowired
	PrivilegeService privilegeService;
	
	@Autowired
	RoleService roleService;
	
	@RequestMapping("add.htm")
	public ModelAndView addHtm(HttpServletRequest req){
		ModelAndView view = new ModelAndView("/role/add");
		List<Privilege> privileges = privilegeService.findAll();
		view.addObject("privileges", privileges);
		view.addObject("size", privileges.size());
		TestController.getMenuPoint(view, req);
		return view;
	}
	
	@RequestMapping(value="add.do",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest req){
		try {
			PersonUser pUser = (PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			int roleId = Integer.parseInt(StringUtils.defaultIfBlank(
					req.getParameter("roleId"), "0"));
			if(roleId==0){
				String name = req.getParameter("name");
				String description = req.getParameter("description");
				Role role = new Role();
				role.setCityId(pUser.getCityId());
				role.setRoleName(name);
				role.setDescription(description);
				role.setStatus(1);
				role.setCreateTime(new Date());
				int num = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("num"), "0"));
				ArrayList<Integer> List = new ArrayList<Integer>();
				for (int i = 1; i <= num; i++) {
					int privilegeId = Integer.parseInt(StringUtils.defaultIfBlank(
							req.getParameter("checkbox" + i), "0"));
					if (privilegeId == 0) {
						continue;
					}
					List.add(privilegeId);
				}
				this.roleService.saveRole(role,List);
				this.saveLog(req.getSession(), role, "新建角色,添加权限为:"+List.toString(),pUser.getCityId());
				return "添加成功";
			}else{
				//编辑
				String name = req.getParameter("name");
				String description = req.getParameter("description");
				Role role = new Role();
				role.setRoleName(name);
				role.setDescription(description);
				role.setStatus(1);
				role.setRoleId(roleId);
				int num = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("num"), "0"));
				ArrayList<Integer> List = new ArrayList<Integer>();
				for (int i = 1; i <= num; i++) {
					int privilegeId = Integer.parseInt(StringUtils.defaultString(
							req.getParameter("checkbox" + i), "0"));
					if (privilegeId == 0) {
						continue;
					}
					List.add(privilegeId);
				}
				this.roleService.updateRole(role,List);
				this.saveLog(req.getSession(), role, "编辑角色,添加权限为:"+List.toString(),pUser.getCityId());
				return "添加成功";
			}
		} catch (NumberFormatException e) {
			logger.error("新建角色失败"+e);
			return "添加失败";
		}
	}
	
	@RequestMapping("roleList.htm")
	public ModelAndView list(HttpServletRequest request){
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView view = new ModelAndView("/role/list");
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		int pageSize = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("pageSize"), Page.DEFAULT_PAGE_SIZE_STR));
		Page<Role> page = null;
		page = this.roleService.findPage(currentPage,pageSize,user.getCityId());
		view.addObject("page", page);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping("edit.htm")
	public ModelAndView edit(HttpServletRequest request,@RequestParam("roleId")int roleId){
		ModelAndView view = new ModelAndView("/role/add");
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		Role role = this.roleService.findById(roleId);
		if(null==role || !role.getCityId().equals(user.getCityId())){
			return new ModelAndView("notCity");
		}
		List<Privilege> privileges = privilegeService.findAll();
		List<Privilege> exist = privilegeService.findByRoleId(roleId);
		view.addObject("role", role);
		view.addObject("privileges", privileges);
		view.addObject("exist", exist);
		view.addObject("size", privileges.size());
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping("delete.do")
	@ResponseBody
	public String delete(HttpServletRequest request,@RequestParam("roleId")int roleId){
		try {
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return "202";
			}
			Role role2 = this.roleService.findById(roleId);
			if(null==role2 || !role2.getCityId().equals(user.getCityId())){
				return "203";
			}
			Role role = new Role();
			role.setRoleId(roleId);
			role.setStatus(0);
			this.roleService.delete(role);
			this.saveLog(request.getSession(), role2, "删除角色", user.getCityId());
			return "200";
		} catch (Exception e) {
			logger.error("删除角色失败"+e);
			return "201";
		}
	}
	
	private void saveLog(HttpSession session,Role dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setCityId(cityId);
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.Role.getName());
	       if(dto.getRoleId()!=null){
	       sysLog.setDataId(dto.getRoleId().toString());
	       }

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<Role>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
	
}
