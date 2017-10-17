package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.ShopSales;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.ShopSalesService;
import com.b2b.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("performance")
public class SalesmanPerformanceController {
	
	@Autowired
	ShopSalesService shopSalesService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping("performanceList.htm")
	public ModelAndView list(HttpServletRequest request) throws ParseException {
		ModelAndView mv = new ModelAndView("performance/list");
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		String date = request.getParameter("queryDate");
		Date dateStart = null;
		Date dateEnd = null;
		String dateAdd = date;
		if(StringUtils.isEmpty(date)){
			date = DateUtil.formatDate(new Date(), "yyyy-MM");
			dateAdd = DateUtil.formatDate(new Date(), "yyyy-MM-07");
			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new Date());
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH, 7);
			SimpleDateFormat dateEndStr = new java.text.SimpleDateFormat("yyyy-MM-dd");
			dateStart = dateEndStr.parse(dateEndStr.format(calendar.getTime()));
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(new Date());
			calendar2.add(Calendar.MONTH, 1);
			calendar2.set(Calendar.DAY_OF_MONTH, 7);
			SimpleDateFormat dateStarStr = new java.text.SimpleDateFormat("yyyy-MM-dd");
			dateEnd = dateStarStr.parse(dateStarStr.format(calendar2.getTime()));
		} else {
			dateAdd = dateAdd + "-07";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			Date sourceDate = null;
			try {
				sourceDate = sdf.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			Calendar cal = Calendar.getInstance();
			cal.setTime(sourceDate);

			SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(sourceDate);
			calendar.add(Calendar.MONTH, -1);
			calendar.set(Calendar.DAY_OF_MONTH, 7);
			Calendar calendar2 = Calendar.getInstance();
			calendar2.setTime(sourceDate);
			calendar2.add(Calendar.MONTH, 1);
			calendar2.set(Calendar.DAY_OF_MONTH, 7);
			SimpleDateFormat dateStarStr = new java.text.SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateEndStr = new java.text.SimpleDateFormat("yyyy-MM-dd");
			dateStart = dateEndStr.parse(dateEndStr.format(calendar.getTime()));
			dateEnd = dateStarStr.parse(dateStarStr.format(calendar2.getTime()));

		}
		mv.addObject("queryDate", date);
		int interfaceId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("interfaceId"), "0"));
		mv.addObject("interfaceId", interfaceId);
		PageHelper.startPage(currentPage, 50);
		List<ShopSales> list = this.shopSalesService.findByCondition(dateAdd,interfaceId,personUser.getCityId(), dateStart, dateEnd);
		PageInfo<ShopSales> info = new PageInfo<ShopSales>(list);
		Page<ShopSales> page = new Page<ShopSales>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		mv.addObject("page", page);
		List<PersonUser> user = this.userService.findUsershFirstByCityId(personUser.getCityId());
		mv.addObject("pusers", user);
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	@RequestMapping("details.htm")
	public ModelAndView detailsList(HttpServletRequest request,@RequestParam("sumdate")String sumdate,@RequestParam("interfaceId")Integer interfaceId,@RequestParam("cityId")Integer cityId){
		ModelAndView mv = new ModelAndView("performance/detail");
		TestController.getMenuPoint(mv, request);
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		Calendar calendar2 = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Date sourceDate = null;
		Date dateEnd = null;
		sumdate = sumdate+"-07";
		try {
			sourceDate = sdf.parse(sumdate);
			calendar2.setTime(sourceDate);
			calendar2.add(Calendar.MONTH, 1);
			calendar2.set(Calendar.DAY_OF_MONTH, 7);
			SimpleDateFormat dateStarStr = new java.text.SimpleDateFormat("yyyy-MM-dd");
			SimpleDateFormat dateEndStr = new java.text.SimpleDateFormat("yyyy-MM-dd");
			dateEnd = dateStarStr.parse(dateStarStr.format(calendar2.getTime()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		PageHelper.startPage(currentPage, 50);
		List<ShopSales> list = this.shopSalesService.findDetailByInterfaceIdAndDateAndCityId(sumdate,interfaceId,cityId, dateEnd);
		PageInfo<ShopSales> info = new PageInfo<ShopSales>(list);
		Page<ShopSales> page = new Page<ShopSales>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		mv.addObject("page", page);
		return mv;
	}
}
