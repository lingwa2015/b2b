package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.*;
import com.b2b.web.util.NumberTool;
import com.b2b.web.util.SessionHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
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

@RequestMapping("redPacket")
@Controller
public class RedPacketController {
	private static final Logger logger = LoggerFactory.getLogger(RedPacketController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private RedPacketService redPacketService;
	
	@Autowired
	private RedPacketCustomerService redPacketCustomerService;
	
	@Autowired
	private RedReceiveService redReceiveService;
	
	@Autowired
	private ShopOrderService shopOrderService;
	
	@Autowired
	private LogService logService;
	
	@RequestMapping("add.htm")
	public ModelAndView add(HttpServletRequest request){
		ModelAndView view = new ModelAndView("redPacket/add");
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping(value="save.do",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest request,RedPacket red){
		try {
			PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			String startTimeStr = request.getParameter("startTimestr");
			String endTimeStr = request.getParameter("endTimestr");
			if (StringUtils.isNotBlank(startTimeStr) && StringUtils.isNotBlank(endTimeStr)) {
				Date startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
				Date endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YMD);
				red.setStartTime(startTime);
				red.setEndTime(endTime);
			}
			red.setRedStatus(1);
			red.setCreatedTime(new Date());
			red.setCreatedUserid(pUser.getId());
			String daybudget = request.getParameter("daybudget");
			red.setRedBudget(NumberTool.str2Double2Fen(request.getParameter("daybudget")));
			if(null == red.getSkip()){
				red.setSkip(false);
			}
            red.setCityId(pUser.getCityId());
			red.setType(0);
			ArrayList<RedPacketType> list = new ArrayList<RedPacketType>();
			int rowCount = Integer.parseInt(StringUtils.defaultString(
					request.getParameter("rowCount"), "0"));
			for (int i = 0; i < rowCount; i++) {
				String redmoney = request.getParameter("redmoney" + i); 
				String rednum = request.getParameter("rednum" + i);
				if(StringUtils.isEmpty(redmoney) || StringUtils.isEmpty(rednum) ){
					continue;
				}
				RedPacketType type = new RedPacketType();
				type.setFee(NumberTool.str2Double2Fen(redmoney));
				type.setNum(Integer.parseInt(rednum));
				type.setType(1);
				list.add(type);
			}
			int rowCount1 = Integer.parseInt(StringUtils.defaultString(
					request.getParameter("rowCount1"), "0"));
			for (int i = 0; i < rowCount1; i++) {
				String reddown = request.getParameter("reddown" + i); 
				String redup = request.getParameter("redup" + i);
				String redpen = request.getParameter("redpen" + i);
				if(StringUtils.isEmpty(reddown) || StringUtils.isEmpty(redup) || StringUtils.isEmpty(redpen) ){
					continue;
				}
				RedPacketType type = new RedPacketType();
				type.setDownFee(NumberTool.str2Double2Fen(reddown));
				type.setUpFee(NumberTool.str2Double2Fen(redup));
				type.setNum(Integer.parseInt(redpen));
				type.setType(2);
				list.add(type);
			}
			this.redPacketService.save(red,list);
			this.saveLog(request.getSession(), red, "创建红包或送", pUser.getCityId());
			return "200";
		} catch (Exception e) {
			logger.error("添加红包失败"+e.getMessage());
			return "添加红包失败"+e.getMessage();
		}
	}
	
	@RequestMapping("redList.htm")
	public ModelAndView list(HttpServletRequest request){
		PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == pUser.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView view = new ModelAndView("redPacket/list");
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		PageHelper.startPage(currentPage, Page.DEFAULT_PAGE_SIZE);
		List<RedPacket> lists = this.redPacketService.findAllByCityId(pUser.getCityId());
		PageInfo<RedPacket> info = new PageInfo<RedPacket>(lists);
		
		Page<RedPacket> page = new Page<RedPacket>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		Long fee1 = this.redReceiveService.findTotalFeeByCityId(pUser.getCityId());
		Long fee2 = this.shopOrderService.findTotalUseRedFeeByCityId(pUser.getCityId());
		view.addObject("page", page);
		view.addObject("fee1", fee1);
		view.addObject("fee2", fee2);
		view.addObject("fee3", fee1-fee2);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping("addcustomer.htm")
    public ModelAndView addcustomer(HttpServletRequest request,@RequestParam("id")Integer id){
		PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == pUser.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView view = new ModelAndView("redPacket/addcustomer");
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		String userName = request.getParameter("userName");
		String selectev = request.getParameter("selectev");
		view.addObject("selectev", selectev);
		PageHelper.startPage(currentPage, Page.DEFAULT_PAGE_SIZE);
    	List<CustomerUser> lists =this.customerService.findShopByNameCityId(userName,pUser.getCityId(),id, selectev);
        PageInfo<CustomerUser> info = new PageInfo<CustomerUser>(lists);
		
		Page<CustomerUser> page = new Page<CustomerUser>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());

    	view.addObject("page", page);
    	view.addObject("redId", id);
		TestController.getMenuPoint(view, request);
    	return view;
    }
	
	@RequestMapping("addcustomer.do")
	@ResponseBody
    public String doaddcustomer(HttpServletRequest request,@RequestParam("jsondata")String jsondata,@RequestParam("redId")Integer redId){
		try {
			PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			Gson gson = new Gson(); 
			List<RedPacketCustomer> datas = gson.fromJson(jsondata,  new TypeToken<List<RedPacketCustomer>>() {}.getType());
			this.redPacketService.changeTypeAndSaveRedPacketCustomer(redId,datas);
			RedPacket packet = new RedPacket();
			packet.setId(redId);
			packet.setType(2);
			this.saveLog(request.getSession(), packet, "部分发放", pUser.getCityId());
			return "200";
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return "201";
		}
		
    }
	@RequestMapping("delcustomer.do")
	@ResponseBody
    public String dodelcustomer(HttpServletRequest request,@RequestParam("jsondata")String jsondata){
		try {
			PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			Gson gson = new Gson(); 
			List<RedPacketCustomer> datas = gson.fromJson(jsondata,  new TypeToken<List<RedPacketCustomer>>() {}.getType());
			this.redPacketCustomerService.delete(datas);
			return "200";
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return "201";
		}
		
    }
	
	@RequestMapping("change.do")
	@ResponseBody
    public String change(HttpServletRequest request,@RequestParam("id")Integer id){
		try {
			PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			RedPacket packet = new RedPacket();
			packet.setId(id);
			packet.setType(1);
			this.redPacketService.changeType(packet);
			this.saveLog(request.getSession(), packet, "全部发放", pUser.getCityId());
			return "200";
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return "201";
		}
		
    }
	
	@RequestMapping("stop.do")
	@ResponseBody
    public String stop(HttpServletRequest request,@RequestParam("id")Integer id){
		try {
			PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			RedPacket packet = new RedPacket();
			packet.setId(id);
			packet.setRedStatus(0);
			this.redPacketService.changeType(packet);
			this.saveLog(request.getSession(), packet, "紧急停止红包活动", pUser.getCityId());
			return "200";
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return "201";
		}
		
    }
	
	@RequestMapping("receiveList.htm")
    public ModelAndView receiveList(HttpServletRequest request,@RequestParam("id")Integer id){
		PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == pUser.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView view = new ModelAndView("redPacket/receiveList");
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		PageHelper.startPage(currentPage, 50);
    	List<RedReceive> lists = this.redReceiveService.findByCondition(id,pUser.getCityId());
        PageInfo<RedReceive> info = new PageInfo<RedReceive>(lists);
		Page<RedReceive> page = new Page<RedReceive>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
    	view.addObject("page", page);
    	view.addObject("id", id);
		TestController.getMenuPoint(view, request);
    	return view;
    }
	
	private void saveLog(HttpSession session,RedPacket dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setCityId(cityId);
	       sysLog.setDataType(LogDataTypeEnum.RedPacket.getName());
	       sysLog.setDataId(dto.getId().toString());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<RedPacket>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
