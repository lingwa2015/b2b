package com.b2b.web.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.Constant;
import com.b2b.common.domain.Business;
import com.b2b.common.domain.PersonUser;
import com.b2b.page.Page;
import com.b2b.service.BusinessService;
import com.b2b.service.UserService;

@Controller
@RequestMapping("/business")
public class BusinessController {
	
	@Autowired
	BusinessService businessService;
	@Autowired
	UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);
	
	@RequestMapping(value = "/businessList.htm", produces="text/html;charset=UTF-8")
	@ResponseBody
	public ModelAndView listView(HttpServletRequest request,
			String currentPage,
			String businessName,
			String mobilePhone) {
		int _currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		//查询条件
		Business business=new Business();
		//组装查询条件
		business.setBusinessName(businessName);
		business.setMobilePhone(mobilePhone);
		business.setStatus(Constant.VALID_STATUS);
		Page<Business> page=businessService.findPage(business, _currentPage, Page.DEFAULT_PAGE_SIZE);
		
		ModelAndView mv=new ModelAndView("business/businessList");
		mv.addObject("businessName", businessName);
		
		mv.addObject("mobilePhone", mobilePhone);
					
		mv.addObject("page", page);

		return mv;
	}
	

	@RequestMapping(value = "/businessAdd.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView addView(Model model, HttpServletRequest requert) {
		
		ModelAndView mv=new ModelAndView("business/businessAdd");
		
		return mv;

	}
	

	@RequestMapping(value = "/businessUpdate.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView updateView(HttpServletRequest request,int id) {

		Business business=businessService.findById(id);
		
		ModelAndView mv = new ModelAndView("business/businessUpdate");
		
		mv.addObject("business",business);
		
		
		return mv;
	}
	
	@RequestMapping(value = "/createBusinessUser.do",method = RequestMethod.POST, produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addBusinessUser(int id){
		String result = "设置成功";
		if(id>0 && userService.findByPhone(businessService.findById(id).getMobilePhone())==null){
			Business business=businessService.findById(id);
			String userName=business.getBusinessName();
			String mobilePhone=business.getMobilePhone();
			String address=business.getBusinessAddress();
			PersonUser personUser=new PersonUser();
			personUser.setUserName(userName);
			personUser.setIsadmin(Constant.Common_User);
			personUser.setAddress(address);
			personUser.setMobilePhone(mobilePhone);
			personUser.setBusinessId(id);
			personUser.setManagershopid(id);
			//添加用户
			userService.create(personUser);
			return result;
		}
		else if(id>0){
			PersonUser personUser=new PersonUser();
			personUser.setId(userService.findByPhone(businessService.findById(id).getMobilePhone()).getId());
			personUser.setBusinessId(id);
			personUser.setManagershopid(id);
			userService.update(personUser);
			return result;
		}
		return "设置失败";
	}
	
	
	@RequestMapping(value = "/businessSave.do", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addOrUpdate(Business business , HttpServletRequest request) {

		//有主键就更新
		if(business.getId()!=null && business.getId()>0){
			
			String oldMobilePhone = request.getParameter("oldMobilePhone");
			
			//检测手机号是否存在
			if(!business.getMobilePhone().equals(oldMobilePhone))
				if(isPhoneExist(business))
					return "该手机号已存在";

			//更新商家
			businessService.update(business);
	
		}else{
			//检测手机号是否存在
			if(isPhoneExist(business))
				return "该手机号已存在";
			//添加商家
			businessService.create(business);
			
		}
		
		String result = "保存成功";
		return result;
	}

	@RequestMapping(value="businessDelete.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delete(int id) {
		
		String result = "删除成功";
		
		businessService.delete(id);

		return result;
	}
	
	@RequestMapping(value = "/SendSms.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView SendSmsView(HttpServletRequest request) {
		
		ModelAndView mv = new ModelAndView("business/sendSms");
		
		float balance=businessService.getSmsBalance();
		mv.addObject("balance", balance);
		
		return mv;
	}
	
	@RequestMapping(value="sendSms.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String sendSms(HttpServletRequest request) {
		
		String message=request.getParameter("content");
		
		
		String result = businessService.sendSms(message);
		return result;
	}
	
	
	@RequestMapping(value = "/checkPhoneNum.json")
	public @ResponseBody String checkPhoneNum(@ModelAttribute("business") Business business,String mobilePhone, HttpServletRequest request) {
	
		if(StringUtils.isNotBlank(mobilePhone)){
			business.setMobilePhone(mobilePhone);
		}	
		
		String oldMobilePhone = request.getParameter("oldMobilePhone");
		
		if(mobilePhone.equals(oldMobilePhone)){
			return "success";
		}
		
		if(!isPhoneExist(business)){
			return "success";
		}
		
		return "error";
	}
	
	private boolean isPhoneExist(Business business){
		
		List<Business> businesses=businessService.findBusinessesByCondition(business);
		
		if(CollectionUtils.isNotEmpty(businesses)){
			return true;
		}
			
		return false;
	}
}
