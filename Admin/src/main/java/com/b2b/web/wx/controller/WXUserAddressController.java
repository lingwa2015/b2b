package com.b2b.web.wx.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.common.domain.Item;
import com.b2b.common.domain.SnackPackageType;
import com.b2b.common.domain.SysLog;
import com.b2b.common.domain.WXUserAddress;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.LogService;
import com.b2b.service.WXUserAddressService;
import com.b2b.service.WXUserInvoiceService;
import com.b2b.web.util.SessionHelper;
import com.b2b.web.wx.util.WXSessionHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/wxuserAddress")
public class WXUserAddressController {
	private static final Logger logger = LoggerFactory.getLogger(WXUserAddressController.class);
	@Autowired
	private WXUserAddressService wxuserAddressService;
	
//	@Autowired
//	private WXUserInvoiceService wxuserInvoiceService;
//	wxuserInvoiceService.create(1, "测试公司1"); 测试发票
	
	@Autowired
	LogService logService;
	
	/**
	 * 查询微信用户相关地址信息
	 * */
	@RequestMapping(value = "/wxAddressList.htm")
	@ResponseBody
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("wx/address/list");
		String standId = request.getParameter("standId");
		String num = request.getParameter("num");
		String offerPrice=request.getParameter("offerPrice");
		int wxuserId=WXSessionHelper.getWxUserId(request.getSession())==null?1:WXSessionHelper.getWxUserId(request.getSession());
		List<WXUserAddress> wxuserAddressList = wxuserAddressService.findByWXUserId(wxuserId);
		if(StringUtils.isNotEmpty(standId)){
			view.addObject("standId", standId);
		}else{
			view.addObject("standId", 0);
		}
		view.addObject("num", num);
		view.addObject("offerPrice", offerPrice);
		if(wxuserAddressList.size()>0){
			view.addObject("list", wxuserAddressList);
		}else{
			view.addObject("list",  new ArrayList<WXUserAddress>());
		}
		return view;
	}
	
	
	/**
	 * 微信客户编辑单条地址记录
	 * */
	@RequestMapping(value = "/wxShowAddressList.htm")
	@ResponseBody
	public ModelAndView show(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("wx/address/edit");
		String standId = request.getParameter("standId");
		String num = request.getParameter("num");
		String offerPrice=request.getParameter("offerPrice");
		String id = request.getParameter("id");
		WXUserAddress wxuserAddress=new WXUserAddress();
		if(StringUtils.isNotEmpty(id)){
			wxuserAddress = wxuserAddressService.findById(Integer.parseInt(id));
		}else{
			wxuserAddress.setProvinceId(33);
			wxuserAddress.setCityId(0);
			wxuserAddress.setTownId(0);
		}
		if(StringUtils.isNotEmpty(standId)){
			view.addObject("standId", standId);
		}else{
			view.addObject("standId", 0);
		}
		view.addObject("num", num);
		view.addObject("offerPrice", offerPrice);
		view.addObject("dto", wxuserAddress);
		return view;
	}
	
//	/**
//	 * 微信客户新增单条地址记录
//	 * */
//	@RequestMapping(value = "/wxAddAddressList.htm")
//	@ResponseBody
//	public ModelAndView add(HttpServletRequest request) {
//		return new ModelAndView("wx/address/add");
//	}
	
	/**
	 * 删除地址
	 * */
	@RequestMapping(value = "/wxAddressDelete.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delete(HttpServletRequest request) {
		String id = request.getParameter("id");
		String result = "删除成功";
		try {
			if(StringUtils.isNotEmpty(id)){
				WXUserAddress dto=new WXUserAddress();
				dto.setId(Integer.parseInt(id));
				this.saveLog(request.getSession(),dto, "删除地址"+id);
				wxuserAddressService.delete(Integer.parseInt(id));
			}
		} catch (Exception e) {
			logger.error("删除地址错误", e);
			return "删除地址失败,原因："+e.getMessage();
		}
		return result;
	}
	
	/**
	 * 设置默认值
	 * */
	@RequestMapping(value = "/wxdefualtData.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String defualtData(HttpServletRequest request) {
		String id = request.getParameter("id");
		String result = "设置成功";
		try {
			if(id!=null){
				int wxuserId=WXSessionHelper.getWxUserId(request.getSession())==null?1:WXSessionHelper.getWxUserId(request.getSession());
				if(StringUtils.isNotEmpty(id) && wxuserId!=0){
					wxuserAddressService.updateDefaultStatusById(Integer.parseInt(id), wxuserId);
					WXUserAddress dto=new WXUserAddress();
					dto.setId(Integer.parseInt(id));
					this.saveLog(request.getSession(),dto, "设置地址"+id);
				}
			}
		} catch (Exception e) {
			logger.error("设置地址错误", e);
			return "设置地址失败,原因："+e.getMessage();
		}
		return result;
	}
	
	@RequestMapping(value = "/wxSaveAddress.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String editSaveAddress(HttpServletRequest request) {
		String id = request.getParameter("id");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		String provinceId = request.getParameter("provinceId");
		String cityId =request.getParameter("cityId");
		String townId =request.getParameter("townId");
		String address = request.getParameter("address");
		String addressDetails = request.getParameter("addressDetails");
		String from = "保存";
		try {
			WXUserAddress dto=new WXUserAddress();
			if(StringUtils.isNotEmpty(id)){
				from = "修改";
				wxuserAddressService.updateAddressById(Integer.parseInt(id), Integer.parseInt(provinceId), Integer.parseInt(cityId), Integer.parseInt(townId), address,addressDetails);
				int wxuserId=WXSessionHelper.getWxUserId(request.getSession())==null?1:WXSessionHelper.getWxUserId(request.getSession());
				wxuserAddressService.updateDefaultStatusById(Integer.parseInt(id), wxuserId);
				dto.setId(Integer.parseInt(id));
				this.saveLog(request.getSession(),dto, from+"地址"+id);
			}else{
				int wxuserId=WXSessionHelper.getWxUserId(request.getSession())==null?1:WXSessionHelper.getWxUserId(request.getSession());
				dto.setWxuserId(wxuserId);
				dto.setName(name);
				dto.setPhone(phone);
				dto.setProvinceId(Integer.parseInt(provinceId));
				dto.setCityId(Integer.parseInt(cityId));
				dto.setTownId(Integer.parseInt(townId));
				dto.setAddress(address);
				dto.setAddressDetails(addressDetails);
				wxuserAddressService.create(dto);
				this.saveLog(request.getSession(),dto, from+"地址"+dto.getId());
			}
		} catch (Exception e) {
			logger.error(from+"地址错误", e);
			return from+"地址失败,原因："+e.getMessage();
		}
		String result =from+ "成功";
		return result;
	}
	
//	@RequestMapping(value = "/wxaddSaveAddress.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
//	@ResponseBody
//	public String addSaveAddress(HttpServletRequest request) {
//		String provinceId = request.getParameter("provinceId");
//		String name = request.getParameter("name");
//		String phone = request.getParameter("phone");
//		String cityId =request.getParameter("cityId");
//		String townId =request.getParameter("townId");
//		String address = request.getParameter("address");
//		String addressDetails = request.getParameter("addressDetails");
//		WXUserAddress dto=new WXUserAddress();
//		String result = "保存成功";
//		try {
//			//int wxuserId=WXSessionHelper.getWxUserId(request.getSession());
//			int wxuserId=1;
//			dto.setWxuserId(wxuserId);
//			wxuserAddressService.create(dto);
//			this.saveLog(request.getSession(),dto, "保存地址");
//		} catch (Exception e) {
//			logger.error("保存地址错误", e);
//			return "保存地址失败,原因："+e.getMessage();
//		}
//		return result;
//	}
	
	private void saveLog(HttpSession session,WXUserAddress dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.WXUserAddress.getName());
	       if(dto.getId()!=null){
	       sysLog.setDataId(dto.getId().toString());
	       }

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<WXUserAddress>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
