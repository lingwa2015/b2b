package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.ItemVariety;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.SysLog;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.ItemService;
import com.b2b.service.ItemVarietyService;
import com.b2b.service.LogService;
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


@Controller
@RequestMapping("itemVariety")
public class ItemVarietyController {
	private static final Logger logger = LoggerFactory.getLogger(ItemVarietyController.class);
	@Autowired
	private ItemVarietyService itemVarietyService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	LogService logService;
	
	@RequestMapping(value = "/itemVarietyList.htm")
	@ResponseBody
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("itemVariety/list");
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		int pageSize = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("pageSize"), Page.DEFAULT_PAGE_SIZE_STR));
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		Page<ItemVariety> page = itemVarietyService.findPage(currentPage,pageSize,user.getCityId());
		TestController.getMenuPoint(mv, request);
		mv.addObject("page", page);
		return mv;
	}
	
	@RequestMapping(value = "/itemVarietyAdd.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("itemVariety/detail");
		ItemVariety dto = new ItemVariety();
		TestController.getMenuPoint(mv, request);
		mv.addObject("dto", dto);
		return mv;
	}
	
	@RequestMapping(value = "/itemVarietyUpdate.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView update(@RequestParam("id")Integer id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("itemVariety/detail");
		ItemVariety dto = this.itemVarietyService.findById(id);
		TestController.getMenuPoint(mv, request);
		mv.addObject("dto", dto);
		return mv;
	}
	
	@RequestMapping(value="save.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String save(ItemVariety dto,HttpServletRequest request){
		String result = "操作成功";

		try{
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			if(dto.getItemvarietyId()!=null&&dto.getItemvarietyId()>0){
				dto.setUpdatedTime(new Date());
				dto.setUpdatedUserid(personUser.getId());
				this.itemVarietyService.update(dto);
				this.saveLog(request.getSession(),dto, "修改品种，名称："+dto.getItemvarietyName(),personUser.getCityId());
			}else{
				dto.setCityId(personUser.getCityId());
				dto.setCreatedTime(new Date());
				dto.setCreatedUserid(personUser.getId());
				dto.setUpdatedTime(dto.getCreatedTime());
				dto.setUpdatedUserid(personUser.getId());
				this.itemVarietyService.create(dto);
				this.saveLog(request.getSession(),dto, "添加品种，名称："+dto.getItemvarietyName(),personUser.getCityId());
			}
		}catch(Exception e){
            logger.error("保存品种失败",e);
            result = "操作失败，原因："+e.getMessage();
		}

		return result;
	}
	
	@RequestMapping(value="delete.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delete(int id,HttpServletRequest request) {
		String result = "操作成功";
		try{
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			ItemVariety dto = itemVarietyService.findById(id);
			if(dto!=null){
				ItemVariety itemVariety = new ItemVariety();
				itemVariety.setItemvarietyId(id);
				itemVariety.setStatus(Constant.DELETE_STATUS);
				itemVariety.setUpdatedTime(new Date());
				itemVariety.setUpdatedUserid(personUser.getId());
				itemVarietyService.delete(itemVariety);
				this.saveLog(request.getSession(),dto, "删除品种，名称："+dto.getItemvarietyName(),personUser.getCityId());
				this.itemService.deleteItemVariety(id,personUser.getId());
			}
		}catch(Exception e){
            logger.error("删除品种失败",e);
            result = "操作失败，原因："+e.getMessage();
		}

		return result;
	}
	
	private void saveLog(HttpSession session,ItemVariety dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setCityId(cityId);
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.ItemVariety.getName());
	       sysLog.setDataId(dto.getItemvarietyId().toString());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<ItemVariety>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
