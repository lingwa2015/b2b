package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.*;
import com.b2b.web.util.LWResult;
import com.b2b.web.util.SessionHelper;
import com.b2b.web.wx.util.Properties;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/customer")
public class CustomerController {
	@Autowired
	CustomerService customerService;

	@Autowired
	LogService logService;
	
	@Autowired
	CustomerWiseService customerWiseService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ShopBlackListService shopBlackListService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	AfterSalesRecordService afterSalesRecordService;
	
	@Autowired
	ReseauService reseauService;
	
	@Autowired
	CityRegionService cityRegionService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	DeliveryReceitpService deliveryReceitpService;

	@Autowired
	AccountLockService accountLockService;
	
	@Autowired
	ItemCategoryService itemCategoryService;
	
	@Autowired
	Properties properties;

	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

	@RequestMapping(value = "/customerList.htm", produces="text/html;charset=UTF-8")
	@ResponseBody
	public ModelAndView listView(HttpServletRequest request,Model model,
			String currentPage,
			String userName,
			String mobilePhone,String linkName,@RequestParam(value="reseauId",defaultValue="-1",required=false)Integer reseauId) {
		ModelAndView mv=new ModelAndView("customer/userList");
		TestController.getMenuPoint(mv, request);
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		};
		int _currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		int param = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("param"), "3"));
		String region = request.getParameter("regionId");
		String sort = request.getParameter("sort");
		String grade = request.getParameter("grade");
		String flag = request.getParameter("flag");
		String buyWay = request.getParameter("buyWay");
		String payBillWay = request.getParameter("payBillWay");

		String status = request.getParameter("status");
		String activationId = request.getParameter("activationId");



		//查询条件
		CustomerUser personUser =new CustomerUser();
		if(reseauId==-1){
			reseauId=null;
		}
		Integer regionId = null;
		if(!StringUtils.isEmpty(region)){
			regionId = Integer.valueOf(region);
		}
		//组装查询条件
		personUser.setUserName(userName);
		personUser.setMobilePhone(mobilePhone);
		personUser.setLikeman(linkName);
		personUser.setStatus(Constant.VALID_STATUS);
		//
//		PersonUser curUser=SessionHelper.getInstance().getUser();
		Integer isdelete = 1;
		int businessId=user.getBusinessId();
		if(status != null) {
			isdelete = Integer.parseInt(status);
		}
		mv.addObject("isdelete", isdelete);
		//Page<CustomerUser> page=customerService.findPage(personUser, _currentPage, Page.DEFAULT_PAGE_SIZE,businessId,startTime,endTime);
		Page<CustomerUser> page=customerService.findPageWithMoney(personUser, _currentPage, Page.DEFAULT_PAGE_SIZE,businessId,param,regionId,sort,grade,flag,buyWay,payBillWay,reseauId,user.getCityId(),isdelete, activationId);
		List<Reseau> lists = this.reseauService.findAllByCityId(user.getCityId());
		List<CityRegion> regions = this.cityRegionService.findByCityId(user.getCityId());
		mv.addObject("regions", regions);
		mv.addObject("reseaus", lists);
		mv.addObject("reseauId", reseauId);
		mv.addObject("userName", userName);
		mv.addObject("mobilePhone", mobilePhone);
		mv.addObject("linkName", linkName);
		mv.addObject("param", param);
		mv.addObject("regionId", regionId);
		mv.addObject("grade", grade);
		mv.addObject("flag", flag);
		mv.addObject("sort", sort);
		mv.addObject("page", page);
		mv.addObject("buyWay", buyWay);
		mv.addObject("payBillWay", payBillWay);
		mv.addObject("activationId", activationId);
		model.addAttribute("businessId", ((PersonUser) request.getSession().getAttribute(Constant.USER_KEY)).getBusinessId());
		return mv;
	}


	@RequestMapping(value = "/userAdd.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView addView(Model model, HttpServletRequest request) {
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		};
		ModelAndView mv=new ModelAndView("customer/userAdd");
		TestController.getMenuPoint(mv, request);
		List<Reseau> lists = this.reseauService.findAllByCityId(user.getCityId());
		List<CityRegion> region = this.cityRegionService.findByCityId(user.getCityId());
		List<PersonUser> pusers = this.userService.findUsershFirstByCityId(user.getCityId());
		mv.addObject("reseaus", lists);
		mv.addObject("regions", region);
		mv.addObject("pusers", pusers);
		return mv;

	}


	@RequestMapping(value = "/userUpdate.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView updateView(HttpServletRequest request,int id) {
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		};
		CustomerUser personUser=customerService.findById(id);
		if(!user.getCityId().equals(personUser.getCityId())){
			return new ModelAndView("notCity");
		}
		String str = personUser.getCheckStr();
		if(!StringUtils.isEmpty(str)){
			String[] split = str.split(",");
			ArrayList<String> list = new ArrayList<String>();
			for (String spl : split) {
				list.add(spl);
			}
			personUser.setChecks(list);
		}
		ModelAndView mv = new ModelAndView("customer/userUpdate");
		TestController.getMenuPoint(mv, request);

		Date createdtime = personUser.getCreatedTime();
		AccountLock lock = new AccountLock();
		lock.setYears(Integer.valueOf(DateUtil.getYear(createdtime)).toString());
		lock.setMonths(Integer.valueOf(DateUtil.getMonth(createdtime)).toString());
		lock.setCityId(user.getCityId());
		int islock = this.accountLockService.findLockByCityId(lock);
		Date date = new Date();
		int m =(date.getYear()-createdtime.getYear())*12+date.getMonth()-createdtime.getMonth();
		if (islock==1 || m>1){
			mv.addObject("islock", 1);
		}
		mv.addObject("user",personUser);
		List<Reseau> lists = this.reseauService.findAllByCityId(user.getCityId());
		mv.addObject("reseaus", lists);
		List<CityRegion> region = this.cityRegionService.findByCityId(user.getCityId());
		mv.addObject("regions", region);
		List<PersonUser> pusers = this.userService.findUsershFirstByCityId(user.getCityId());
		if(null!=personUser.getInterfacePersonId() && personUser.getInterfacePersonId()>0){
			PersonUser user2 = this.userService.findById(personUser.getInterfacePersonId());
			int a = 0;
			if (user2 != null) {
				for (PersonUser personUser2 : pusers) {
					if(personUser2.getId().equals(user2.getId())){
						a = 1;
					}
				}
			}
			if(a == 0){
				pusers.add(user2);
			}
		}
		mv.addObject("pusers", pusers);
		mv.addObject("type", request.getParameter("type"));

		return mv;
	}


	@RequestMapping(value = "/userSave.do", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addOrUpdate(CustomerUser personUser , HttpServletRequest request,@RequestParam("branch")Integer branch) {

		String result = "保存成功";

		try{
			PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == curUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			//有主键就更新
			if(personUser.getId()!=null && personUser.getId()>0){
				String dateStr = request.getParameter("contract_date");
				Date date = DateUtil.parseDateStr(dateStr, "yyyy-MM-dd");
				personUser.setContractDate(date);
				String submitTimeStr = request.getParameter("submit_time");
				Date date1 = DateUtil.parseDateStr(submitTimeStr, "yyyy-MM-dd");
				personUser.setSubmitTime(date1);


				//检测手机号是否存在
//				if(!personUser.getMobilePhone().equals(oldMobilePhone))
//					if(isPhoneExist(personUser))
//						return "该手机号已存在";
				CustomerUser editUser=customerService.findById(personUser.getId());
				if(branch==1){
					//分店，除了简称可以重复
					if(!editUser.getUserName().equals(personUser.getUserName())){
						if(isUserNameExist(personUser))
							return "该客户简称已存在";
					}
				}else{
					if(!editUser.getUserName().equals(personUser.getUserName())&&!editUser.getCompanyName().equals(personUser.getCompanyName())){
						if(isExist(personUser))
							return "该客户已存在";
					}else if (!editUser.getUserName().equals(personUser.getUserName())) {
						if(isUserNameExist(personUser))
							return "该客户简称已存在";
					}else if (!editUser.getCompanyName().equals(personUser.getCompanyName())) {
						if(isCompanyNameExist(personUser))
							return "该客户全称已存在";
					}
				}

				personUser.setBusinessId(editUser.getBusinessId());
				personUser.setManagershopid(editUser.getManagershopid());
				personUser.setUpdatedUserid(curUser.getId());
				personUser.setUpdatedTime(new Date());
				if(null!=personUser.getInterfacePersonId() && personUser.getInterfacePersonId()!=-1){
					PersonUser puser = this.userService.findById(personUser.getInterfacePersonId());
					if(null!=puser && "销售".equals(puser.getPost())){
						personUser.setRoyaltyCoefficient(new BigDecimal(1));
					}else{
						personUser.setRoyaltyCoefficient(new BigDecimal(0.5));
					}
					
				}else{
					personUser.setRoyaltyCoefficient(new BigDecimal(0.5));
				}
				if(null!=personUser.getIswxvip() && personUser.getIswxvip()==0){
					personUser.setPayBillWay(2);
				}

				if (editUser.getSubmitTime() != null && personUser.getSubmitTime() != null) {
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
					if (df.format(editUser.getSubmitTime()).equals(df.format(personUser.getSubmitTime()))) {
						personUser.setSubmitTime(null);
					}
				} else if (editUser.getSubmitTime() == null && personUser.getSubmitTime() != null) {

				} else {
					personUser.setSubmitTime(null);
				}

				//更新用户
				customerService.update(personUser);
				if(null==date){
					this.customerService.updateContractDate(personUser.getId(),null);
				}
				this.saveLog(request.getSession(),personUser, "修改客户，客户名："+personUser.getUserName(),curUser.getCityId());
			}else{
				//检测手机号是否存在
				if(branch==1){
					//分店，除了简称可以重复
					if(isUserNameExist(personUser))
						return "该客户简称已存在";
				}else{
					if(isCompanyExist(personUser))
						return "该客户已存在";
				}
				int businessId=((PersonUser) request.getSession().getAttribute(Constant.USER_KEY)).getBusinessId();
				personUser.setIsadmin(Constant.Common_User);
				personUser.setBusinessId(businessId);
				personUser.setManagershopid(0);
				personUser.setCreatedUserid(curUser.getId());
				personUser.setCreatedTime(new Date());
				personUser.setUpdatedUserid(curUser.getId());
				personUser.setUpdatedTime(personUser.getCreatedTime());
				personUser.setSubmitTime(personUser.getCreatedTime());
				//personUser.setContractDate(new Date());
				//添加用户
				personUser.setCityId(curUser.getCityId());
				
				if(null!=personUser.getInterfacePersonId() && personUser.getInterfacePersonId()!=-1){
					PersonUser puser = this.userService.findById(personUser.getInterfacePersonId());
					if(null!=puser && "销售".equals(puser.getPost())){
						personUser.setRoyaltyCoefficient(new BigDecimal(1));
					}else{
						personUser.setRoyaltyCoefficient(new BigDecimal(0.5));
					}
					
				}else{
					personUser.setRoyaltyCoefficient(new BigDecimal(0.5));
				}
				if(personUser.getIswxvip()==0){
					personUser.setPayBillWay(2);
				}
				customerService.create(personUser);
				this.saveLog(request.getSession(),personUser, "添加客户，客户名："+personUser.getUserName(),curUser.getCityId());
			}
		}catch(Exception e){
			logger.error("保存失败",e);
			result = "保存失败，原因："+e.getMessage();
		}


		return result;
	}


//	@RequestMapping(value = "/userPasswordSave.do", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
//	@ResponseBody
//	public String updatePassword (PersonUser personUser , HttpServletRequest request) {
//		String result = "保存成功";
//
//		try{
//			PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
//			CustomerUser dto = customerService.findById(personUser.getId());
//			dto.setPassWord(EncryptHelper.md5(personUser.getPassWord()));
//			dto.setUpdatedUserid(curUser.getId());
//			dto.setUpdatedTime(new Date());
//			customerService.update(dto);
//			this.saveLog(request.getSession(),dto, "修改密码，用户名："+dto.getUserName());
//		}catch(Exception e){
//			logger.error("保存密码失败",e);
//			result = "保存失败，原因："+e.getMessage();
//		}
//
//		return result;
//	}

	@RequestMapping(value="userDelete.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delete(int id,HttpServletRequest request) {

		String result = "200";

		try{
			PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == curUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			};
			CustomerUser personUser = customerService.findById(id);
			if(personUser!=null){
				if(!curUser.getCityId().equals(personUser.getCityId())){
					return "你设置的默认城市与实际不符";
				}
				personUser.setUpdatedUserid(curUser.getId());
				personUser.setUpdatedTime(new Date());
				customerService.delete(personUser);
				this.saveLog(request.getSession(),personUser, "删除客户，客户名:"+personUser.getUserName(),curUser.getCityId());
			}
		}catch(Exception e){
           logger.error("删除失败",e);
           result = "删除失败,原因："+e.getMessage();
		}

		return result;
	}


	@RequestMapping(value = "/checkPhoneNum.json")
	public @ResponseBody String checkPhoneNum(@ModelAttribute("user") CustomerUser personUser,String mobilePhone, HttpServletRequest request) {

		if(StringUtils.isNotBlank(mobilePhone)){
			personUser.setMobilePhone(mobilePhone);
		}

		String oldMobilePhone = request.getParameter("oldMobilePhone");

		if(mobilePhone.equals(oldMobilePhone)){
			return "success";
		}

		if(!isPhoneExist(personUser)){
			return "success";
		}

		return "error";
	}

	private boolean isPhoneExist(CustomerUser personUser){

		List<CustomerUser> personUsers=customerService.findUsersByCondition(personUser);

		if(CollectionUtils.isNotEmpty(personUsers)){
			return true;
		}

		return false;
	}
	
	private boolean isCompanyExist(CustomerUser personUser){

		List<CustomerUser> personUsers=customerService.isCompanyExist(personUser);

		if(CollectionUtils.isNotEmpty(personUsers)){
			return true;
		}

		return false;
	}
	
	private boolean isUserNameExist(CustomerUser personUser){

		List<CustomerUser> personUsers=customerService.isUserNameExist(personUser);

		if(CollectionUtils.isNotEmpty(personUsers)){
			return true;
		}

		return false;
	}
	private boolean isCompanyNameExist(CustomerUser personUser) {
		List<CustomerUser> personUsers=customerService.isCompanyNameExist(personUser);

		if(CollectionUtils.isNotEmpty(personUsers)){
			return true;
		}

		return false;
	}
	
	private boolean isExist(CustomerUser personUser){

		List<CustomerUser> personUsers=customerService.isExist(personUser);

		if(CollectionUtils.isNotEmpty(personUsers)){
			return true;
		}

		return false;
	}
	
	//可删除
	@RequestMapping(value = "/queryUser.json")
	@ResponseBody
	public  Map<String, Object> getUser(HttpServletRequest request) {
		Map<String, Object> resultMap = Maps.newHashMap();
		CustomerUser personUser = new CustomerUser();
		personUser.setMobilePhone(request.getParameter("mobilePhone"));
		personUser.setUserName(request.getParameter("userName"));
		List<CustomerUser> personUsers = customerService.findUsersByCondition(personUser);
		if (CollectionUtils.isNotEmpty(personUsers)) {
			CustomerUser customerUser=personUsers.get(0);
			CustomerWise customerWise=new CustomerWise();
			customerWise.setCustomerId(personUser.getId());
			List<CustomerWise> customerWiseList=customerWiseService.selectByExample(customerWise);
			customerWise=customerWiseList.get(0);
			if(customerWise!=null){
				Long budget=customerWise.getBudget();
				if(budget!=null){
					customerUser.setBudget(budget/100);
				}else{
					customerUser.setBudget(0L);
				}
				
			}
		     resultMap.put("user", customerUser);

		}
		return resultMap;
	}
	
	@RequestMapping(value = "/queryById.json")
	@ResponseBody
	public CustomerUser findById(@RequestParam("customerId") Integer customerId){
		return this.customerService.findById(customerId);
	}
	
	@RequestMapping(value = "/queryByid.json")
	@ResponseBody
	public HashMap<String,Object> findByid(@RequestParam("customerId") Integer customerId){
		HashMap<String,Object> map = new HashMap<String,Object>();
		CustomerUser user = this.customerService.findById(customerId);
	    ArrayList<Integer> ids = this.orderService.findLastTwoOrderItemByCid(customerId);
	    ArrayList<Integer> ids2 = this.shopBlackListService.findAllByShopId(customerId);
	    ArrayList<Integer> ids3 = this.itemService.findNewItem();
	    ArrayList<Integer> ids4 = this.itemService.findRecommend();
	    ArrayList<Integer> ids5 = this.itemService.findBang();
	    ArrayList<Integer> ids6 = this.itemService.findFreeSpecialSupply();
	    List<AfterSalesRecord> afterSalesRecords = this.afterSalesRecordService.findByUserIdNotCompelete(customerId);
		map.put("user", user);
		map.put("ids", ids);
		map.put("ids2", ids2);
		map.put("ids3", ids3);
		map.put("ids4", ids4);
		map.put("ids5", ids5);
		map.put("ids6", ids6);
		map.put("records", afterSalesRecords);
		return map;
	}
	
	/**
	 * 根据简称返回全称
	 * */
	@RequestMapping(value = "/autoQuery.do")
	@ResponseBody
	public  List<String> autoQueryUser(HttpServletRequest request) {
//		CustomerUser personUser = new CustomerUser();
//		personUser.setUserName(request.getParameter("companyName"));
		//List<CustomerUser> list = customerService.findUsersByCondition(personUser);
		PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == curUser.getCityId()){
			return new ArrayList<String>();
		};
		return customerService.findByLikeNameAndCityId(request.getParameter("userName"),curUser.getCityId());
	}
	
	/**
	 * 根据简称返回简称
	 * */
	@RequestMapping(value = "/autoUserNameQuery.do")
	@ResponseBody
	public  List<HashMap<String, Object>> autoQueryUserName(HttpServletRequest request) {
		PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == curUser.getCityId()){
			return new ArrayList<HashMap<String, Object>>();
		};
		return customerService.findByLikeUserNameAndCityId(request.getParameter("userName"),curUser.getCityId());
	}
	
	//删掉
	/**
	 * 根据简称返回简称，针对便利店
	 * */
	@RequestMapping(value = "/autoUsernameQuery.do")
	@ResponseBody
	public  List<HashMap<String, Object>> autoQueryUsername(@RequestParam("userName")String userName,@RequestParam("flag")Integer flag) {
		return customerService.findByName(userName,flag);
	}

	@RequestMapping(value = "/info.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView infoView(@RequestParam("shop_id")String shop_id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("customer/info");
		TestController.getMenuPoint(mv, request);
		mv.addObject("shop_id", shop_id);
		return mv;
	}
	
	@RequestMapping(value = "/getinfo.json")
	@ResponseBody
	public  LWResult getinfo(HttpServletRequest request,@RequestParam("userid")Integer userid) {

		try {
			PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			CustomerUser user = customerService.findById(userid);
			this.saveLog(request.getSession(), user, "加载了"+user.getUserName()+"的客户信息",curUser.getCityId());
			return LWResult.ok(user);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return LWResult.build(201, "加载失败");
	}
	
	@RequestMapping(value = "/deliveryList.htm")
	@ResponseBody
	public ModelAndView deliveryList(Model model, HttpServletRequest request) {
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		ModelAndView mv=new ModelAndView("customer/deliveryList");

		String startTimeStr = request.getParameter("createStartTime");
		String endTimeStr = request.getParameter("createEndTime");
		String linkName = request.getParameter("linkName");
		String userName = request.getParameter("userName");
		String status = request.getParameter("status");
		String tagStatus = request.getParameter("tagStatus");
		Date startTime = null;
		Date endTime = null;
		if (StringUtils.isNotBlank(startTimeStr)) {
			startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
			mv.addObject("createStartTime", startTimeStr);
		}
		if (StringUtils.isNotBlank(endTimeStr)) {
			endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YMD);
			mv.addObject("createEndTime", endTimeStr);
		}
		mv.addObject("linkName", linkName);
		mv.addObject("userName", userName);
		mv.addObject("status", status);
		mv.addObject("tagStatus", tagStatus);
		PageHelper.startPage(currentPage, 50);
		List<DeliveryReceitp> lists = this.deliveryReceitpService.findByCondition(user.getCityId(), startTime, endTime, linkName, userName, status, tagStatus);
		PageInfo<DeliveryReceitp> info = new PageInfo<DeliveryReceitp>(lists);
		Page<DeliveryReceitp> page = new Page<DeliveryReceitp>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		mv.addObject("page", page);
		TestController.getMenuPoint(mv, request);
		return mv;

	}
	
	
	@RequestMapping(value = "/deliverydetail.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView deliverydetail(HttpServletRequest request,@RequestParam("id")Integer id) {
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		};
		ModelAndView mv=new ModelAndView("customer/deliverydetail");
		List<CityRegion> region = this.cityRegionService.findByCityId(user.getCityId());
		mv.addObject("regions", region);
		DeliveryReceitp deliveryReceitp = this.deliveryReceitpService.findById(id);
		mv.addObject("dto", deliveryReceitp);
		List<PersonUser> pusers = this.userService.findUsershFirstByCityId(user.getCityId());
		mv.addObject("pusers", pusers);
		TestController.getMenuPoint(mv, request);
		return mv;

	}
	
	@RequestMapping(value = "/savedelivery.do", method = RequestMethod.POST)
	@ResponseBody
	public String savedelivery(HttpServletRequest request,DeliveryReceitp dto) {
		try {
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			};
			dto.setUpdatedTime(new Date());
			this.deliveryReceitpService.update(dto);
			return "200";
		} catch (Exception e) {
			logger.error("保存失败"+e);
			return "保存失败"+e.getMessage();
		}
	}
	
	@RequestMapping(value="deletedelivery.do",method = RequestMethod.POST)
	@ResponseBody
	public String deletedelivery(HttpServletRequest request,@RequestParam("id")Integer id){
		try {
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			};
					this.deliveryReceitpService.delete(id);
					return "200";
		} catch (Exception e) {
			logger.error("交接单保存失败"+e);
		}
		return "201";
	}
	
	@RequestMapping(value = "/jiaojie.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView jiaojie(HttpServletRequest request,@RequestParam("id")Integer id) {
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		};
		ModelAndView mv=new ModelAndView("customer/deliveryjj");
	    DeliveryReceitp bean = this.deliveryReceitpService.findById(id);
	    	CustomerUser cu = new CustomerUser();
	    	cu.setCompanyName(bean.getCompanyName());
	    	cu.setUserName(bean.getUserName());
	    	cu.setRegionId(bean.getRegionId());
	    	cu.setAddress(bean.getAddress());
	    	cu.setIswxvip(bean.getIsvxvip());
	    	cu.setGoodsShelfNum(bean.getGoodsShelfNum());
	    	cu.setLikeman(bean.getLinkman());
	    	cu.setPosition(bean.getPosition());
	    	cu.setMobilePhone(bean.getMobilePhone());
	    	cu.setCompanyPersonnum(bean.getCompanyPersonnum());
	    	cu.setCompanyMemo(bean.getRemark()+"，"+bean.getOthers());
	    	cu.setInterfacePersonId(bean.getInterfaceId());
	    	cu.setCityId(bean.getCityId());
	    	if(null!=bean.getDutyParagraph()){
	    		cu.setDutyParagraph(bean.getDutyParagraph());
	    	}
	    	cu.setShopDiscount(bean.getShopDiscount());
	    	cu.setDiscount(bean.getDiscount());
	    	cu.setPayBillWay(bean.getPayBillWay());
	    	cu.setSpecial(bean.getPayWay());
	    	cu.setDeliveryId(bean.getId());
	    	cu.setSubmitTime(bean.getSubmitTime());
	    	cu.setCheckremark(bean.getCheckremark());
	    	cu.setCheckstatus(bean.getCheckstatus());

	    	//shuihao
	    	mv.addObject("user", cu);
	    	List<Reseau> lists = this.reseauService.findAllByCityId(user.getCityId());
	    	List<CityRegion> region = this.cityRegionService.findByCityId(user.getCityId());
	    	List<PersonUser> pusers = this.userService.findUsershFirstByCityId(user.getCityId());
	    	mv.addObject("reseaus", lists);
	    	mv.addObject("regions", region);
	    	mv.addObject("pusers", pusers);
	    	mv.addObject("deliveryId", id);
		TestController.getMenuPoint(mv, request);
		return mv;

	}
	
	@RequestMapping(value="jiaojie.do",method = RequestMethod.POST ,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String deletedelivery(HttpServletRequest request,@RequestParam("deliveryId")Integer deliveryId,CustomerUser personUser){
		try {
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			};
			String branch = request.getParameter("branch");
			if("1".equals(branch)){
				//分店，除了简称可以重复
				if(isUserNameExist(personUser))
					return "该客户简称已存在";
			}else{
				if(isCompanyExist(personUser))
					return "该客户已存在";
			}

			int businessId=((PersonUser) request.getSession().getAttribute(Constant.USER_KEY)).getBusinessId();
			personUser.setIsadmin(Constant.Common_User);
			personUser.setBusinessId(businessId);
			personUser.setManagershopid(0);
			personUser.setCreatedUserid(user.getId());
			personUser.setCreatedTime(new Date());
			personUser.setUpdatedUserid(user.getId());
			personUser.setUpdatedTime(personUser.getCreatedTime());
			//personUser.setContractDate(new Date());
			//添加用户

			if(null!=personUser.getInterfacePersonId() && personUser.getInterfacePersonId()!=-1){
				PersonUser puser = this.userService.findById(personUser.getInterfacePersonId());
				if(null!=puser && "销售".equals(puser.getPost())){
					personUser.setRoyaltyCoefficient(new BigDecimal(1));
				}else{
					personUser.setRoyaltyCoefficient(new BigDecimal(0.5));
				}

			}else{
				personUser.setRoyaltyCoefficient(new BigDecimal(0.5));
			}
			if(personUser.getIswxvip()==0){
				personUser.setPayBillWay(2);
			}
			DeliveryReceitp deliveryReceitp = this.deliveryReceitpService.findById(deliveryId);
			personUser.setSubmitTime(deliveryReceitp.getSubmitTime());
			customerService.create(personUser);
			DeliveryReceitp receitp = new DeliveryReceitp();
			receitp.setId(deliveryId);
			receitp.setIscommit(3);
			receitp.setUpdatedTime(new Date());
			receitp.setCheckstatus(1);
			receitp.setUnqualifiednum(0);
			receitp.setIsvxvip(deliveryReceitp.getIsvxvip());
			this.deliveryReceitpService.update(receitp);

			this.saveLog(request.getSession(),personUser, "交接客户，客户名："+personUser.getUserName(),user.getCityId());
			return "成功";
		} catch (Exception e) {
			logger.error("交接单保存失败"+e);
		}
		return "201";
	}
	
	@RequestMapping("complete.do")
	@ResponseBody
	public String complete(@RequestParam("id")Integer id){
			try {
				DeliveryReceitp deliveryReceitp = this.deliveryReceitpService.findById(id);
				DeliveryReceitp receitp = new DeliveryReceitp();
				receitp.setId(id);
				receitp.setIscommit(3);
				receitp.setUpdatedTime(new Date());
				receitp.setIsvxvip(deliveryReceitp.getIsvxvip());
				if (deliveryReceitp.getCheckstatus().equals(2)){
                    receitp.setCheckstatus(2);
                } else {
					receitp.setUnqualifiednum(0);
                    receitp.setCheckstatus(1);
                }
				this.deliveryReceitpService.update(receitp);
				return "200";
			} catch (Exception e) {
				logger.error("交接单完成失败"+e);
			}
			return "201";
	}
	
	
	private void saveLog(HttpSession session,CustomerUser dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setCityId(cityId);
	       sysLog.setDataType(LogDataTypeEnum.CUSTOMER.getName());
	       sysLog.setDataId(dto.getId().toString());

	       if(dto!=null){
		       String dataContent = new Gson().toJson(dto,
						new TypeToken<CustomerUser>() {
						}.getType());

		       sysLog.setDataContent(dataContent);
	       }

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}

	@RequestMapping(value="userRecover.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String recover(int id, int status, HttpServletRequest request) {

		String result = status == 1 ? "恢复成功" : "彻底删除成功";

		try{
			PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == curUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			};
			CustomerUser personUser = customerService.findById(id);
			if(personUser!=null){
				if(!curUser.getCityId().equals(personUser.getCityId())){
					return "你设置的默认城市与实际不符";
				}
				personUser.setUpdatedUserid(curUser.getId());
				personUser.setUpdatedTime(new Date());
				if (status == 1) {
					personUser.setStatus(Constant.VALID_STATUS);
					customerService.updateByFindBean(personUser);
					this.saveLog(request.getSession(),personUser, "恢复客户，客户名:"+personUser.getUserName(),curUser.getCityId());
				} else {
					personUser.setStatus(Constant.THOROUGH_STATUS);
					customerService.updateByFindBean(personUser);
					this.saveLog(request.getSession(),personUser, "彻底删除客户，客户名:"+personUser.getUserName(),curUser.getCityId());
				}

			}
		}catch(Exception e){
			if (status == 1) {
				logger.error("恢复失败", e);
				result = "恢复失败,原因：" + e.getMessage();
			} else {
				logger.error("彻底删除客户失败", e);
				result = "彻底删除客户失败,原因：" + e.getMessage();
			}
		}

		return result;
	}
	
	@RequestMapping(value = "/QRCode.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView setManager(HttpServletRequest request,@RequestParam("shopId")Integer shopId) {
		ModelAndView view = new ModelAndView("customer/qrcode");
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		};
		view.addObject("shopId", shopId);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping(value="/getQRCode.htm",produces="text/html;charset=UTF-8")
	@ResponseBody
	public void erweima(HttpServletRequest request,HttpServletResponse response,@RequestParam("shopId")String shopId){
		String content = properties.ONLINE_URL+"convenient/shop_item.htm?shop_id="+shopId;
        try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 500, 500);   
			MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}

	@RequestMapping(value = "/verification.do", method = RequestMethod.POST)
	@ResponseBody
	public String verification(HttpServletRequest request,DeliveryReceitp dto) {
		try {
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			dto.setUpdatedTime(new Date());
			DeliveryReceitp deliveryReceitp = this.deliveryReceitpService.findById(dto.getId());
			if (dto.getUnqualifiednum() != 0) {
				dto.setCheckstatus(2);
			} else {
				dto.setCheckstatus(1);
			}
			dto.setIscommit(3);
			dto.setIsvxvip(deliveryReceitp.getIsvxvip());
			this.deliveryReceitpService.update(dto);
			return "200";
		} catch (Exception e) {
			logger.error("保存失败"+e);
			return "保存失败"+e.getMessage();
		}
	}
	
}
