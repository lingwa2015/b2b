package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.Machine;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.SysLog;
import com.b2b.common.domain.Transfer;
import com.b2b.common.util.DateUtil;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.LogService;
import com.b2b.service.MachineService;
import com.b2b.service.TransferService;
import com.b2b.web.util.NumberTool;
import com.b2b.web.util.SessionHelper;
import com.b2b.web.wx.util.pay.OrderUtil;
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

@RequestMapping("transfer")
@Controller
public class TransferController {
	private static final Logger logger = LoggerFactory.getLogger(TransferController.class);
	
	@Autowired
	private LogService logService;
	
	@Autowired
	MachineService machineService;
	
	@Autowired
	TransferService transferService;
	
	@RequestMapping(value = "/add.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView add(@RequestParam("id")Integer id, HttpServletRequest request) {
		ModelAndView view = new ModelAndView("transfer/add");
		Machine machine = this.machineService.findWithTypeNameById(id);
		view.addObject("machine", machine);
		view.addObject("type", 1);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping(value = "/transferList.htm")
	@ResponseBody
	public ModelAndView callback(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("transfer/list");
		TestController.getMenuPoint(view, request);
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		String machineId = request.getParameter("machineId");
		view.addObject("machineId", machineId);
		String startTimeStr = request.getParameter("startTime");
		String endTimeStr = request.getParameter("endTime");
		Date startTime = null;
		Date endTime = null;
		if (StringUtils.isNotBlank(startTimeStr)) {
			startTime = DateUtil.parseDateStr(startTimeStr, "yyyy-MM-dd");
			view.addObject("startTime", startTimeStr);
		}
		if (StringUtils.isNotBlank(endTimeStr)) {
			endTime = DateUtil.parseDateStr(endTimeStr, "yyyy-MM-dd");
			view.addObject("endTime", endTimeStr);
		}
		String type = request.getParameter("type");
		view.addObject("type", type);
		PageHelper.startPage(currentPage, 15);
		List<Transfer> lists = this.transferService.findByCondition(machineId,startTime,endTime,type,personUser.getCityId());
		PageInfo<Transfer> info = new PageInfo<Transfer>(lists);
		Page<Transfer> page = new Page<Transfer>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		view.addObject("page", page);
		return view;
	}
	
	@RequestMapping(value = "/callback.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView callback(@RequestParam("id")Integer id, HttpServletRequest request) {
		ModelAndView view = new ModelAndView("transfer/add");
		Machine machine = this.machineService.findWithTypeNameById(id);
		view.addObject("machine", machine);
		view.addObject("type", 2);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping(value="save.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String save(Transfer dto,HttpServletRequest request) {
		String result = "操作成功";
		try{
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你没有设置默认城市";
			}
			String executedTimeStr = request.getParameter("executedDate");
			if (StringUtils.isEmpty(executedTimeStr)) {
				dto.setExecutedTime(new Date());
			} else {
				dto.setExecutedTime(DateUtil.parseDateStr(executedTimeStr, "yyyy-MM-dd"));
			}
			
			if (dto.getActualForegift() != null) {
				long price = NumberTool.str2Double2Fen(dto.getActualForegift()
						.toString());
				dto.setForegift(price);
			}
			
			dto.setCityId(personUser.getCityId());
			dto.setState(1);
			String number = OrderUtil.GetOrderNumber();
			dto.setTransferId(number);
			dto.setCreatedTime(new Date());
			this.transferService.insert(dto);
			saveLog(request.getSession(), dto, "添加流转单,设备编号:"+dto.getMachineId(),personUser.getCityId());
		}catch(Exception e){
            logger.error("保存流转单失败",e);
            result = "操作失败，原因："+e.getMessage();
		}
		return result;
	}
	
	@RequestMapping(value="delete.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delete(HttpServletRequest request) {
		String result = "操作成功";
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "你没有设置默认城市";
		}
		try{
			String id = request.getParameter("id");
			Transfer transfer = this.transferService.findById(id);
			if(null == transfer || !transfer.getCityId().equals(personUser.getCityId())){
				return "你设置的默认城市与操作城市不符";
			}
			this.transferService.delete(id);
			this.saveLog(request.getSession(), transfer, "删除流转单", personUser.getCityId());
		}catch(Exception e){
            logger.error("删除流转单失败",e);
            result = "操作失败，原因："+e.getMessage();
		}
		return result;
	}
	
	@RequestMapping(value = "/print.do", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView print(HttpServletRequest request,@RequestParam("transferId")String transferId) {
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView view = new ModelAndView("transfer/print");
		Transfer transfer = this.transferService.findById(transferId);
		if(null == transfer || !transfer.getCityId().equals(personUser.getCityId())){
			return new ModelAndView("notCity");
		}
		view.addObject("dto", transfer);
		return view;
	}
	
	private void saveLog(HttpSession session,Transfer dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.Transfer.getName());
	       sysLog.setDataId(dto.getTransferId());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<Transfer>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
