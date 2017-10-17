package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.*;
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
import java.util.Date;
import java.util.List;

@RequestMapping("shopDiscountSetting")
@Controller
public class ShopDiscountSettingController {
	private static final Logger logger = LoggerFactory.getLogger(ShopDiscountSettingController.class);
	
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	
	@Autowired
	private ShopDiscountSettingService shopDiscountSettingService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ShopDiscountCustomerService shopDiscountCustomerService;
	
	@Autowired
	private ReseauService reseauService;
	
	@RequestMapping("add.htm")
	public ModelAndView addOrderPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shopDiscount/add");
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping(value="save.do",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest request,ShopDiscountSetting dto){
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
				dto.setStartTime(startTime);
				dto.setEndTime(endTime);
			}
			
		    if(dto.getEndTime().before(dto.getStartTime())){
		    	return "201";
		    }
		   
		    dto.setShopType(0);
			dto.setStatus(1);
			dto.setCreatedTime(new Date());
			dto.setCreatedUserid(pUser.getId());
			dto.setCityId(pUser.getCityId());
			this.shopDiscountSettingService.save(dto);
			this.saveLog(request.getSession(), dto, "新建店铺折扣活动", pUser.getCityId());
			return "200";
		} catch (Exception e) {
			logger.error("新建店铺折扣活动失败"+e.getMessage());
			return "新建店铺折扣活动失败"+e.getMessage();
		}
	}
	
	@RequestMapping("discountSettingList.htm")
	public ModelAndView list(HttpServletRequest request){
		PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == pUser.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView view = new ModelAndView("shopDiscount/list");
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		PageHelper.startPage(currentPage, Page.DEFAULT_PAGE_SIZE);
		List<ShopDiscountSetting> lists = this.shopDiscountSettingService.findAllByCityId(pUser.getCityId());
		PageInfo<ShopDiscountSetting> info = new PageInfo<ShopDiscountSetting>(lists);
		
		Page<ShopDiscountSetting> page = new Page<ShopDiscountSetting>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		view.addObject("page", page);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping("stop.do")
	@ResponseBody
    public String stop(HttpServletRequest request,@RequestParam("id")Integer id){
		try {
			PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			ShopDiscountSetting setting = new ShopDiscountSetting();
			setting.setId(id);
			setting.setStatus(0);
			this.shopDiscountSettingService.changeStatus(setting);
			this.saveLog(request.getSession(), setting, "紧急停止商品促销活动", pUser.getCityId());
			return "200";
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return "201";
		}
		
    }
	
	@RequestMapping("addCustomer.htm")
    public ModelAndView addcustomer(HttpServletRequest request,@RequestParam("id")Integer id){
		PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == pUser.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView view = new ModelAndView("shopDiscount/customer");
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		String userName = request.getParameter("userName");
		String selectev = request.getParameter("selectev");
		view.addObject("selectev", selectev);
		PageHelper.startPage(currentPage, Page.DEFAULT_PAGE_SIZE);
    	List<CustomerUser> lists =this.customerService.findShopByNameCityIdAndShopDiscountId(userName,pUser.getCityId(),id, selectev);
        PageInfo<CustomerUser> info = new PageInfo<CustomerUser>(lists);
		Page<CustomerUser> page = new Page<CustomerUser>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
    	view.addObject("page", page);
    	view.addObject("shopDiscountId", id);
		TestController.getMenuPoint(view, request);
    	return view;
    }
	
	@RequestMapping("addcustomer.do")
	@ResponseBody
    public String doaddcustomer(HttpServletRequest request,@RequestParam("jsondata")String jsondata,@RequestParam("shopDiscountId")Integer shopDiscountId){
		try {
			PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			Gson gson = new Gson(); 
			List<ShopDiscountCustomer> datas = gson.fromJson(jsondata,  new TypeToken<List<ShopDiscountCustomer>>() {}.getType());
			this.shopDiscountSettingService.changeTypeAndSaveShopDiscountCustomer(shopDiscountId,datas);
			ShopDiscountSetting setting = new ShopDiscountSetting();
			setting.setId(shopDiscountId);
			setting.setShopType(2);
			this.saveLog(request.getSession(), setting, "部分店铺", pUser.getCityId());
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
			List<ShopDiscountCustomer> datas = gson.fromJson(jsondata,  new TypeToken<List<ShopDiscountCustomer>>() {}.getType());
			this.shopDiscountCustomerService.delete(datas);
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
			ShopDiscountSetting setting = new ShopDiscountSetting();
			setting.setId(id);
			setting.setShopType(1);
			this.shopDiscountSettingService.changeStatus(setting);
			this.saveLog(request.getSession(), setting, "全部发放", pUser.getCityId());
			return "200";
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return "201";
		}
		
    }
	
	@RequestMapping("preferentialDetailList.htm")
	public ModelAndView preferentialDetail(HttpServletRequest request,@RequestParam(value="reseauId",defaultValue="-1",required=false)Integer reseauId){
		PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == pUser.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView view = new ModelAndView("shopDiscount/preferentialList");
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		String userName = request.getParameter("userName");
		view.addObject("userName", userName);
		String startTimeStr = request.getParameter("startTime");
		String endTimeStr = request.getParameter("endTime");

		Date startTime = null;
		Date endTime = null;

		if (StringUtils.isNotBlank(startTimeStr)) {
			startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
			view.addObject("startTime", startTimeStr);
		}

		if (StringUtils.isNotBlank(endTimeStr)) {
			String str = endTimeStr+" 23:59:59";
			endTime = DateUtil.parseDateStr(str,"yyyy-MM-dd HH:mm:ss");
			view.addObject("endTime", endTimeStr);
		}
		if(reseauId==-1){
			reseauId=null;
		}
		view.addObject("reseauId", reseauId);
		PageHelper.startPage(currentPage, 50);
		List<ShopDiscountSetting> lists = this.shopDiscountSettingService.findPreferentialDetailsByConditions(startTime,endTime,userName,reseauId,pUser.getCityId());
		PageInfo<ShopDiscountSetting> info = new PageInfo<ShopDiscountSetting>(lists);
		
		Page<ShopDiscountSetting> page = new Page<ShopDiscountSetting>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		view.addObject("page", page);
		Long money = this.shopDiscountSettingService.findCountByConditions(startTime,endTime,userName,reseauId,pUser.getCityId());
		List<Reseau> reseaus = this.reseauService.findAllByCityId(pUser.getCityId());
		view.addObject("reseaus", reseaus);
		view.addObject("money", money);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	
	private void saveLog(HttpSession session,ShopDiscountSetting dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setCityId(cityId);
	       sysLog.setDataType("店铺折扣活动");
	       sysLog.setDataId(dto.getId().toString());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<ShopDiscountSetting>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
	
}
