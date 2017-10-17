package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
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
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("itemSalesPromotion")
public class ItemSalesPromotionController {
	private static final Logger logger = LoggerFactory.getLogger(ItemSalesPromotionController.class);
	
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	
	
	@Autowired
	private ItemSalesPromotionService itemSalesPromotionService;
	
	@Autowired
	private LogService logService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ItemSaleCustomerService itemSaleCustomerService;
	
	@Autowired
	ShopItemStockService shopItemStockService;
	
	@Autowired
	ShopItemService shopItemService;
	
	@RequestMapping("add.htm")
	public ModelAndView addOrderPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("activity/itemSaleAdd");
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping(value="save.do",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest request,ItemSalesPromotion dto){
		try {
			PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			String startTimeStr = request.getParameter("startTimestr");
			String endTimeStr = request.getParameter("endTimestr");
			String moneystr = request.getParameter("moneystr");
			if (StringUtils.isNotBlank(startTimeStr) && StringUtils.isNotBlank(endTimeStr)) {
				Date startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
				Date endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YMD);
				dto.setStartTime(startTime);
				dto.setEndTime(endTime);
			}
			
		    if(dto.getEndTime().before(dto.getStartTime())){
		    	return "201";
		    }
		    List<ItemSalesPromotion> one = this.itemSalesPromotionService.findByItemIdAndDateAndCityId(dto.getItemId(),dto.getStartTime(), pUser.getCityId());
		    List<ItemSalesPromotion> two = this.itemSalesPromotionService.findByItemIdAndDateAndCityId(dto.getItemId(),dto.getEndTime(),pUser.getCityId());
		    List<ItemSalesPromotion> three = this.itemSalesPromotionService.findByItemIdAndStartAndEndTimeAndCityId(dto.getItemId(),dto.getStartTime(),dto.getEndTime(),pUser.getCityId());
		    if(!one.isEmpty() || !two.isEmpty() || !three.isEmpty()){
				return "201";
			}
		    if(dto.getType()==1){
		    	if(null!=moneystr){
		    		dto.setMoney(NumberTool.str2Double2Fen(moneystr));
		    		dto.setDiscount(null);
		    	}else{
		    		return "202";
		    	}
		    }else{
		    	if(null!=dto.getDiscount()){
		    		dto.setMoney(null);
		    	}else{
		    		return "202";
		    	}
		    }
		    dto.setShopType(0);
			dto.setStatus(1);
			dto.setCreatedTime(new Date());
			dto.setCreatedUserid(pUser.getId());
			dto.setCityId(pUser.getCityId());
			this.itemSalesPromotionService.save(dto);
			this.saveLog(request.getSession(), dto, "新建商品促销活动", pUser.getCityId());
			return "200";
		} catch (Exception e) {
			logger.error("新建商品促销活动失败"+e.getMessage());
			return "新建商品促销活动失败"+e.getMessage();
		}
	}
	
	@RequestMapping("itemSalesPromotionList.htm")
	public ModelAndView list(HttpServletRequest request){
		PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == pUser.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView view = new ModelAndView("activity/itemSlaeList");
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		String itemname = request.getParameter("itemname");
		view.addObject("itemname", itemname);
		String param = request.getParameter("param");
		view.addObject("param", param);
		
		PageHelper.startPage(currentPage, Page.DEFAULT_PAGE_SIZE);
		List<ItemSalesPromotion> lists = this.itemSalesPromotionService.findAllByConditions(itemname,param,pUser.getCityId());
		PageInfo<ItemSalesPromotion> info = new PageInfo<ItemSalesPromotion>(lists);
		
		Page<ItemSalesPromotion> page = new Page<ItemSalesPromotion>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
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
			ItemSalesPromotion promotion = new ItemSalesPromotion();
			promotion.setId(id);
			promotion.setStatus(0);
			this.itemSalesPromotionService.changeStatus(promotion);
			this.saveLog(request.getSession(), promotion, "紧急停止商品促销活动", pUser.getCityId());
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
		ModelAndView view = new ModelAndView("activity/itemcxcustomer");
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		String userName = request.getParameter("userName");
		String selectev = request.getParameter("selectev");
		view.addObject("selectev", selectev);
		PageHelper.startPage(currentPage, Page.DEFAULT_PAGE_SIZE);
    	List<CustomerUser> lists =this.customerService.findShopByNameCityIdAndItemSaleId(userName,pUser.getCityId(),id, selectev);
        PageInfo<CustomerUser> info = new PageInfo<CustomerUser>(lists);
		
		Page<CustomerUser> page = new Page<CustomerUser>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());

    	view.addObject("page", page);
    	view.addObject("itemsaleId", id);
		TestController.getMenuPoint(view, request);
    	return view;
    }
	
	@RequestMapping("addcustomer.do")
	@ResponseBody
    public String doaddcustomer(HttpServletRequest request,@RequestParam("jsondata")String jsondata,@RequestParam("itemsaleId")Integer itemsaleId){
		try {
			PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			Gson gson = new Gson(); 
			List<ItemSaleCustomer> datas = gson.fromJson(jsondata,  new TypeToken<List<ItemSaleCustomer>>() {}.getType());
			this.itemSalesPromotionService.changeTypeAndSaveItemSaleCustomer(itemsaleId,datas);
			ItemSalesPromotion promotion = new ItemSalesPromotion();
			promotion.setId(itemsaleId);
			promotion.setShopType(2);
			this.saveLog(request.getSession(), promotion, "部分店铺", pUser.getCityId());
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
			List<ItemSaleCustomer> datas = gson.fromJson(jsondata,  new TypeToken<List<ItemSaleCustomer>>() {}.getType());
			this.itemSaleCustomerService.delete(datas);
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
			ItemSalesPromotion promotion = new ItemSalesPromotion();
			promotion.setId(id);
			promotion.setShopType(1);
			this.itemSalesPromotionService.changeStatus(promotion);
			this.saveLog(request.getSession(), promotion, "全部发放", pUser.getCityId());
			return "200";
		} catch (JsonSyntaxException e) {
			e.printStackTrace();
			return "201";
		}
		
    }
	
	@RequestMapping("detailList.htm")
	public ModelAndView detailList(HttpServletRequest request,@RequestParam("id")Integer id){
		PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == pUser.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView view = new ModelAndView("activity/detailList");
		List<ItemSalesPromotion> lists = this.itemSalesPromotionService.findPreferentialByItemSaleId(id);
		ItemSalesPromotion itemsale = this.itemSalesPromotionService.findById(id);
		Integer totalNum = 0;
		if(2==itemsale.getShopType()){
			totalNum = this.shopItemService.findTotalNumByItemIdAndSaleId(itemsale.getItemId(),itemsale.getId());
		}else{
			totalNum = this.shopItemService.findTotalNumByItemId(itemsale.getItemId());
		}
		if(!lists.isEmpty()){
			if(2==itemsale.getShopType()){
				for (ItemSalesPromotion itemSalesPromotion : lists) {
					if(0==DateUtil.diff(new Date(), itemSalesPromotion.getCreatedTime(), Calendar.DAY_OF_MONTH)){
						Date date2 = DateUtil.dateAdd("d", -1, itemSalesPromotion.getCreatedTime());
						Integer num1 = this.shopItemStockService.findByItemIdAndSumdateAndSaleId(itemsale.getItemId(),date2,itemsale.getId());
						int a = (totalNum + num1)/2;
						Integer consumeNum = itemSalesPromotion.getConsumeNum();
						if(a==0){
							itemSalesPromotion.setDiscount(null);
						}else{
							itemSalesPromotion.setDiscount(new BigDecimal((float)consumeNum/a).setScale(2, BigDecimal.ROUND_HALF_UP));
						}
					}else{
						Date date2 = DateUtil.dateAdd("d", -1, itemSalesPromotion.getCreatedTime());
						Integer num1 = this.shopItemStockService.findByItemIdAndSumdateAndSaleId(itemsale.getItemId(),date2,itemsale.getId());
						Integer num2 = this.shopItemStockService.findByItemIdAndSumdateAndSaleId(itemsale.getItemId(),itemSalesPromotion.getCreatedTime(),itemsale.getId());
						int a = (num2 + num1)/2;
						Integer consumeNum = itemSalesPromotion.getConsumeNum();
						if(a==0){
							itemSalesPromotion.setDiscount(null);
						}else{
							itemSalesPromotion.setDiscount(new BigDecimal((float)consumeNum/a).setScale(2, BigDecimal.ROUND_HALF_UP));
						}
					}
				}
			}else{
				for (ItemSalesPromotion itemSalesPromotion : lists) {
					if(0==DateUtil.diff(new Date(), itemSalesPromotion.getCreatedTime(), Calendar.DAY_OF_MONTH)){
						Integer num1 = this.shopItemStockService.findByItemIdAndSumdate(itemsale.getItemId(),itemSalesPromotion.getCreatedTime());
						int a = (totalNum + num1)/2;
						Integer consumeNum = itemSalesPromotion.getConsumeNum();
						if(a==0){
							itemSalesPromotion.setDiscount(null);
						}else{
							itemSalesPromotion.setDiscount(new BigDecimal((float)consumeNum/a).setScale(2, BigDecimal.ROUND_HALF_UP));
						}
					}else{
						Integer num1 = this.shopItemStockService.findByItemIdAndSumdate(itemsale.getItemId(),itemSalesPromotion.getCreatedTime());
						Date date2 = DateUtil.dateAdd("d", 1, itemSalesPromotion.getCreatedTime());
						Integer num2 = this.shopItemStockService.findByItemIdAndSumdate(itemsale.getItemId(),date2);
						int a = (num2 + num1)/2;
						Integer consumeNum = itemSalesPromotion.getConsumeNum();
						if(a==0){
							itemSalesPromotion.setDiscount(null);
						}else{
							itemSalesPromotion.setDiscount(new BigDecimal((float)consumeNum/a).setScale(2, BigDecimal.ROUND_HALF_UP));
						}
					}
					
				}
			}
		}
		ItemSalesPromotion dto = this.itemSalesPromotionService.findTotalByItemSaleId(id);
		view.addObject("lists", lists);
		view.addObject("dto", dto);
		view.addObject("totalNum", totalNum);
		view.addObject("itemName", itemsale.getItemName());
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	
	
	private void saveLog(HttpSession session,ItemSalesPromotion dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setCityId(cityId);
	       sysLog.setDataType("商品促销活动");
	       sysLog.setDataId(dto.getId().toString());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<ItemSalesPromotion>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
	
}
