package com.b2b.web.wx.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.common.domain.StandardOrder;
import com.b2b.common.domain.StandardOrderItem;
import com.b2b.service.StandardOrderItemService;
import com.b2b.service.StandardOrderService;

@Controller
@RequestMapping("/wxuserStandardOrder")
public class WXStandardOrderController {

	@Autowired
	StandardOrderService standardOrderService;
	
	@Autowired
	StandardOrderItemService standardOrderItemService;
	
	/**
	 * 查询本周套餐
	 * */
	@RequestMapping(value = "/wxWeekStandardOrderList.htm")
	@ResponseBody
	public ModelAndView weekList(HttpServletRequest request) {
		List<StandardOrder> wxuserStandardOrderList = standardOrderService.getCurWeekStandOrder();
		return new ModelAndView("wx/item/list", "list", wxuserStandardOrderList);
	}
	
	/**
	 * 查询包详情
	 * */
	@RequestMapping(value = "/wxStandardOrderDetailsList.htm")
	@ResponseBody
	public ModelAndView detailsList(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("wx/item/details");
		String snackpackageId = request.getParameter("snackpackageId");
//		snackpackageId="19";
		List<StandardOrder> wxuserStandardOrderList=new ArrayList<StandardOrder>();
		List<StandardOrderItem> wxuserStandardOrderItemList=new ArrayList<StandardOrderItem>();
		StandardOrder standardOrder=new StandardOrder();
		if(StringUtils.isNotEmpty(snackpackageId)){
			wxuserStandardOrderList = standardOrderService.getStandOrderById(Integer.parseInt(snackpackageId));
			if(wxuserStandardOrderList.size()>0){
				standardOrder=wxuserStandardOrderList.get(0);
			}
			wxuserStandardOrderItemList = standardOrderItemService.findByStandOrderId(Integer.parseInt(snackpackageId));
		}
		mv.addObject("standardOrder", standardOrder);
		mv.addObject("standardOrderItemList", wxuserStandardOrderItemList);
		return mv;
	}
	
	/**
	 * 年货包
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/wxYearBag.htm")
	@ResponseBody
	public ModelAndView YearBag(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("wx/item/yearBag");
		List<StandardOrder> wxuserStandardOrderList=new ArrayList<StandardOrder>();
		List<StandardOrderItem> wxuserStandardOrderItemList=new ArrayList<StandardOrderItem>();
		StandardOrder standardOrder=new StandardOrder();
			wxuserStandardOrderList = standardOrderService.getStandOrderById(94);
			if(wxuserStandardOrderList.size()>0){
				standardOrder=wxuserStandardOrderList.get(0);
			}
			wxuserStandardOrderItemList = standardOrderItemService.findByStandOrderId(94);
		mv.addObject("standardOrder", standardOrder);
		mv.addObject("standardOrderItemList", wxuserStandardOrderItemList);
		return mv;
	}
}
