package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.Equipment;
import com.b2b.common.domain.Machine;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.SysLog;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.EquipmentService;
import com.b2b.service.LogService;
import com.b2b.service.MachineService;
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

@RequestMapping("machine")
@Controller
public class MachineController {
	private static final Logger logger = LoggerFactory.getLogger(MachineController.class);
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private EquipmentService equipmentService;
	
	@Autowired
	private MachineService machineService;
	
	@RequestMapping(value = "/add.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("machine/add");
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		List<Equipment> onetype = equipmentService.findOneTypeByCityId(personUser.getCityId());
		List<Equipment> twotype = equipmentService.findTwoTypeByCityId(personUser.getCityId());
		view.addObject("onetype", onetype);
		view.addObject("twotype", twotype);
		view.addObject("flag", 1);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping(value = "/update.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView add(HttpServletRequest request,@RequestParam("id")Integer id) {
		ModelAndView view = new ModelAndView("machine/add");
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		List<Equipment> onetype = equipmentService.findOneTypeByCityId(personUser.getCityId());
		List<Equipment> twotype = equipmentService.findTwoTypeByCityId(personUser.getCityId());
		view.addObject("onetype", onetype);
		view.addObject("twotype", twotype);
		Machine machine = this.machineService.findById(id);
		view.addObject("dto", machine);
		view.addObject("flag", 2);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping(value = "/machineList.htm")
	@ResponseBody
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("machine/list");
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		Integer oneTypeId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("oneTypeId"), "0"));
		Integer twoTypeId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("twoTypeId"), "0"));
		view.addObject("oneTypeId", oneTypeId);
		view.addObject("twoTypeId", twoTypeId);
		String userName = request.getParameter("userName");
		String machineId = request.getParameter("machineId");
		view.addObject("userName", userName);
		view.addObject("machineId", machineId);
		PageHelper.startPage(currentPage, 15);
		List<Machine> lists = this.machineService.findAll(oneTypeId,twoTypeId,userName,machineId,personUser.getCityId());
		PageInfo<Machine> info = new PageInfo<Machine>(lists);
		Page<Machine> page = new Page<Machine>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		List<Equipment> onetype = equipmentService.findOneTypeByCityId(personUser.getCityId());
		view.addObject("onetype", onetype);
		List<Equipment> twotype = equipmentService.findByParentId(oneTypeId);
		view.addObject("twotype", twotype);
		view.addObject("page", page);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping(value="save.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String save(Machine dto,HttpServletRequest request) {
		String result = "操作成功";
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "你没有设置默认城市";
		}
		String flag = request.getParameter("flag");
		try{
			if("1".equals(flag)){
				Machine machine = this.machineService.findByMachineIdAndCityId(dto.getMachineId(),personUser.getCityId());
				if(machine==null){
					dto.setCreatedTime(new Date());
					dto.setStatus(1);
					dto.setCityId(personUser.getCityId());
					machineService.create(dto);
					this.saveLog(request.getSession(),dto, "添加设备信息",personUser.getCityId());
				}else{
					return "该设备编号已存在";
				}
			}else if ("2".equals(flag)) {
				Machine machine = this.machineService.findById(dto.getId());
				if(null==machine || !machine.getCityId().equals(personUser.getCityId())){
					return "你设置的默认城市与操作城市不符";
				}
				machineService.update(dto);
				this.saveLog(request.getSession(),dto, "修改设备信息",personUser.getCityId());
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
			Machine machine = this.machineService.findById(id);
			if(null==machine || !machine.getCityId().equals(personUser.getCityId())){
				return "你设置的默认城市与操作城市不符";
			}
				machineService.deleteMachine(id);
				this.saveLog(request.getSession(),machine, "删除设备"+machine.getMachineId(),personUser.getCityId());
		}catch(Exception e){
            logger.error("删除设备失败",e);
            result = "操作失败，原因："+e.getMessage();
		}
		return result;
	}
	
	
	
	private void saveLog(HttpSession session,Machine dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setCityId(cityId);
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.Machine.getName());
	       sysLog.setDataId(dto.getMachineId());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<Machine>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
