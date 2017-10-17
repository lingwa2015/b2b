package com.b2b.web.controller;

import com.b2b.common.domain.Privilege;
import com.b2b.common.domain.SysLog;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.LogService;
import com.b2b.service.PrivilegeService;
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
import java.util.Date;

@RequestMapping("privilege")
@Controller
public class PrivilegeController {
	private static final Logger logger = LoggerFactory.getLogger(PrivilegeController.class);
	
	@Autowired
	private PrivilegeService privilegeService;
	
	@Autowired
	private LogService logService;
	
	@RequestMapping("add.htm")
	public ModelAndView add(HttpServletRequest req){
		ModelAndView view = new ModelAndView("/privilege/add");
		TestController.getMenuPoint(view, req);
		return view;
	}
	
	@RequestMapping(value="add.do",method = RequestMethod.POST)
	public String doadd(@RequestParam("right")String rightName,@RequestParam("description")String description,HttpServletRequest req){
		try {
			int id = Integer.valueOf(StringUtils.defaultIfBlank(
					req.getParameter("id"), "0"));
			if(0==id){
				Privilege right = new Privilege();
				right.setName(rightName);
				right.setDescription(description);
				right.setStatus(1);
				this.privilegeService.save(right);
				this.saveLog(req.getSession(), right, "添加权限");
				return "redirect:/privilege/privilegeList.htm";
			}else{
				Privilege right = new Privilege();
				right.setId(id);
				right.setName(rightName);
				right.setDescription(description);
				right.setStatus(1);
				this.privilegeService.saveEdit(right);
				this.saveLog(req.getSession(), right, "编辑权限");
				return "redirect:/privilege/privilegeList.htm";
			}
		} catch (Exception e) {
			logger.error("添加权限异常："+e);
			return "redirect:/privilege/add.htm";
		}
	}
	
	@RequestMapping("privilegeList.htm")
	public ModelAndView list(HttpServletRequest request){
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		int pageSize = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("pageSize"), Page.DEFAULT_PAGE_SIZE_STR));
		Page<Privilege> page = null;
		ModelAndView view = new ModelAndView("/privilege/list");
		page = this.privilegeService.findpage(currentPage,pageSize);
		view.addObject("page", page);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping("edit.htm")
	public ModelAndView edit(@RequestParam("id")int id, HttpServletRequest request){
		ModelAndView view = new ModelAndView("/privilege/add");
		Privilege privilege = this.privilegeService.findById(id);
		view.addObject("privilege", privilege);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping("delete.do")
	@ResponseBody
	public String delete(@RequestParam("id")int id){
		try {
			Privilege right = new Privilege();
			right.setId(id);
			right.setStatus(0);
			this.privilegeService.delete(right);
			return "200";
		} catch (Exception e) {
			logger.error("删除权限失败"+e);
			return "201";
		}
	}
	
	
	private void saveLog(HttpSession session,Privilege dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.Privilege.getName());
	       if(dto.getId()!=null){
	       sysLog.setDataId(dto.getId().toString());
	       }

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<Privilege>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
