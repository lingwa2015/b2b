package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.Equipment;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.SysLog;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.EquipmentService;
import com.b2b.service.LogService;
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
import java.util.HashMap;
import java.util.List;

@RequestMapping("equipment")
@Controller
public class EquipmentController {
	private static final Logger logger = LoggerFactory.getLogger(EquipmentController.class);
	@Autowired
	LogService logService;
	
	@Autowired
	EquipmentService equipmentService;
	
	@RequestMapping(value = "/add.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("equipment/add");
		Equipment dto = new Equipment();
		mv.addObject("dto", dto);
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping(value = "/update.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView update(int id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("equipment/add");
		HashMap<String, Object> dto = equipmentService.findWithParentName(id);
		mv.addObject("dto", dto);
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping(value = "/typeAdd.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView typeAdd(int id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("equipment/typeAdd");
		Equipment dto = equipmentService.findById(id);
		mv.addObject("dto", dto);
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping(value="save.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String save(Equipment dto,HttpServletRequest request) {
		String result = "操作成功";
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "你没有设置默认城市";
		}
		try{
			if(dto.getId()!=null&&dto.getId()>0){
				Equipment equipment = this.equipmentService.findById(dto.getId());
				if(null==equipment || !equipment.getCityId().equals(personUser.getCityId())){
					return "你设置的默认城市与操作城市不符";
				}
				equipmentService.update(dto);
				this.saveLog(request.getSession(),dto, "修改设备名称："+dto.getName(),personUser.getCityId());
			}else{
				dto.setCreatedTime(new Date());
				dto.setStatus(1);
				dto.setCityId(personUser.getCityId());
				equipmentService.create(dto);
				this.saveLog(request.getSession(),dto, "添加设备名称："+dto.getName(),personUser.getCityId());
			}
		}catch(Exception e){
            logger.error("保存设备失败",e);
            result = "操作失败，原因："+e.getMessage();
		}
		return result;
	}
	
	@RequestMapping(value="delete.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delete(@RequestParam("id")Integer id,HttpServletRequest request) {
		String result = "操作成功";
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "你没有设置默认城市";
		}
		try{
			Equipment equipment = this.equipmentService.findById(id);
			if(null==equipment || !equipment.getCityId().equals(personUser.getCityId())){
				return "你设置的默认城市与操作城市不符";
			}
			if(equipment.getParentId()==null){
				equipmentService.deleteOneWithTwoTypeById(id);
				this.saveLog(request.getSession(),equipment, "删除设备"+equipment.getName(),personUser.getCityId());
			}else{
				equipmentService.deteleById(id);
				this.saveLog(request.getSession(),equipment, "删除设备"+equipment.getName(),personUser.getCityId());
			}
		}catch(Exception e){
            logger.error("删除设备失败",e);
            result = "操作失败，原因："+e.getMessage();
		}
		return result;
	}
	
	@RequestMapping(value="savexing.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String savexing(Equipment dto,HttpServletRequest request) {
		String result = "操作成功";
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "你没有设置默认城市";
		}
		try{
				dto.setCityId(personUser.getCityId());
				dto.setCreatedTime(new Date());
				dto.setStatus(1);
				equipmentService.create(dto);
				this.saveLog(request.getSession(),dto, "添加型号名称："+dto.getName(),personUser.getCityId());
		}catch(Exception e){
            logger.error("保存型号失败",e);
            result = "操作失败，原因："+e.getMessage();
		}
		return result;
	}
	
	
	@RequestMapping(value = "/equipmentList.htm")
	@ResponseBody
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("equipment/list");
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		PageHelper.startPage(currentPage, 15);
		List<HashMap<String, Object>> lists = this.equipmentService.findAllByCityId(personUser.getCityId());
		PageInfo<HashMap<String, Object>> info = new PageInfo<HashMap<String, Object>>(lists);
		Page<HashMap<String, Object>> page = new Page<HashMap<String, Object>>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		mv.addObject("page", page);
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping(value = "/twoType.json")
	@ResponseBody
	public List<Equipment> leavecat(@RequestParam("oneTypeId")Integer oneTypeId){
		List<Equipment> leaveCats = this.equipmentService.findByParentId(oneTypeId);
		return leaveCats;
	}
	
	private void saveLog(HttpSession session,Equipment dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setCityId(cityId);
	       sysLog.setDataType(LogDataTypeEnum.Equipment.getName());
	       sysLog.setDataId(dto.getId().toString());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<Equipment>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
