package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.Order;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.OrderService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping(" gift")
public class GiftController {
	@Autowired
	OrderService orderService;
	
    @RequestMapping("giftList.htm")
	public ModelAndView giftlist(HttpServletRequest request){
    	ModelAndView mv = new ModelAndView("order/giftlist");
    	try {
    		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
    		if(null == personUser.getCityId()){
    			return new ModelAndView("noCity");
    		}
    		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));

			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");
			Date startTime = null;
			Date endTime = null;
			if (StringUtils.isNotBlank(startTimeStr)) {
				startTime = DateUtil.parseDateStr(startTimeStr, "yyyy-MM-dd");
				mv.addObject("startTime", startTimeStr);
			}

			if (StringUtils.isNotBlank(endTimeStr)) {
				endTime = DateUtil.parseDateStr(endTimeStr, "yyyy-MM-dd");
				mv.addObject("endTime", endTimeStr);
			}
			String orderNum = request.getParameter("orderNum");
			String userName = request.getParameter("userName");
			mv.addObject("orderNum", orderNum);
			mv.addObject("userName", userName);
			PageHelper.startPage(currentPage, 50);
			List<Order> orders =this.orderService.findGiftOrdersByCondition(orderNum,userName,startTime,endTime,personUser.getCityId());
			PageInfo<Order> info = new PageInfo<Order>(orders);
			Page<Order> page = new Page<Order>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			mv.addObject("page", page);
			Order total = this.orderService.findTotalGiftOrdersByCondition(orderNum,userName,startTime,endTime,personUser.getCityId());
			mv.addObject("total", total);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		TestController.getMenuPoint(mv, request);
    	return mv;
	}
}
