package com.b2b.web.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.Constant;
import com.b2b.common.domain.AccountLock;
import com.b2b.common.domain.CustomerOrder;
import com.b2b.common.domain.CustomerOrderItem;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.DebitNoteDemo;
import com.b2b.common.domain.Item;
import com.b2b.common.domain.ItemCategory;
import com.b2b.common.domain.Order;
import com.b2b.common.domain.OrderItem;
import com.b2b.common.domain.SnackPackageType;
import com.b2b.common.domain.StandardOrder;
import com.b2b.common.domain.StandardOrderItem;
import com.b2b.common.domain.SysLog;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.util.DateUtil;
import com.b2b.common.util.OrderNumberGenerator;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.enums.OrderStatusEnum;
import com.b2b.job.AutoStandradOrder;
import com.b2b.page.Page;
import com.b2b.service.CustomerOrderService;
import com.b2b.service.ItemCategoryService;
import com.b2b.service.ItemService;
import com.b2b.service.LogService;
import com.b2b.service.SnackPackageTypeService;
import com.b2b.service.StandardOrderService;
import com.b2b.service.UserService;
import com.b2b.web.util.GsonUtil;
import com.b2b.web.util.JsonResult;
import com.b2b.web.util.NumberTool;
import com.b2b.web.util.SessionHelper;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
@Controller
@RequestMapping("/snackPackageOrder")
public class SnackPackageOrderController {
	private static final Logger logger = LoggerFactory.getLogger(SnackPackageOrderController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	
	@Autowired
	UserService userService;
	
	@Autowired
	ItemService itemService;

	@Autowired
	ItemCategoryService itemCategoryService;
	
	@Autowired
	LogService logService;
	
	@Autowired
	AutoStandradOrder autoStandradOrder;
	
	@Autowired
	StandardOrderService standardOrderService;
	
	@Autowired
	SnackPackageTypeService snackPackageTypeService;
	
	@RequestMapping("snackPackageOrderlist.htm")
	public ModelAndView getOrderListPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("snackPackage/list");
		try {

			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));

			int queryUser = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("queryUser"), "0"));

			mv.addObject("queryUser", queryUser);

			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");
			String snackpackageType = request.getParameter("snackpackageType");

			Date startTime = null;
			Date endTime = null;
			int snackpackageTypeInt=0;

			if (StringUtils.isNotBlank(startTimeStr)) {
				startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
				mv.addObject("startTime", startTimeStr);
			}

			if (StringUtils.isNotBlank(endTimeStr)) {
				endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YMD);
				mv.addObject("endTime", endTimeStr);
			}

			StandardOrder order = new StandardOrder();
			String standardorderId = request.getParameter("standardorderId");
			if(StringUtils.isNotEmpty(standardorderId)){
				order.setStandardorderId(Integer.parseInt(standardorderId));
				mv.addObject("standardorderId", standardorderId);
			}else{
				order.setStandardorderId(0);
				mv.addObject("standardorderId", 0);
			}
			
			if (StringUtils.isNotEmpty(snackpackageType)) {
				snackpackageTypeInt=Integer.parseInt(snackpackageType);
				order.setSnackpackageType(snackpackageTypeInt);
				mv.addObject("snackpackageType", snackpackageType);
			}

//			PersonUser user=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);

			Page<Pair<StandardOrder, List<StandardOrderItem>>> orderPage = standardOrderService.findStandardOrder(order, startTime, endTime, currentPage,
							Page.DEFAULT_PAGE_SIZE);
			Map<String, String> orderMap = Maps.newHashMap();
			mv.addObject("orderMap", orderMap);

			mv.addObject("page", orderPage);
			
			List<SnackPackageType> snackList=snackPackageTypeService.selectAll();
			mv.addObject("snackList", snackList);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<Pair<CustomerOrder, List<CustomerOrderItem>>> page = new Page<Pair<CustomerOrder, List<CustomerOrderItem>>>(1, 1, Page.DEFAULT_PAGE_SIZE,new ArrayList<Pair<CustomerOrder, List<CustomerOrderItem>>>());
			mv.addObject("page", page);
		}
		return mv;
	}
	
	
	@RequestMapping("addSnackPackageOrder.htm")
	public ModelAndView addOrderPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("snackPackage/add");
		//生成标准包
		String standardorderId = request.getParameter("standardorderId");
		String executedTimeStr = request.getParameter("executedTime");
		if(StringUtils.isNotEmpty(standardorderId)){
			mv.addObject("standardorderId", standardorderId);
		}
		if(StringUtils.isNotEmpty(executedTimeStr)){
			Date executedTime = DateUtil.parseDateStr(executedTimeStr, DATE_FORMAT_YMD);
			mv.addObject("executedTime", executedTime);
		}
		this.fillCommonData(mv, 0);
		return mv;
	}
	
	private void fillCommonData(ModelAndView view,int businessId){
		List<ItemCategory> catList =null;
		if(businessId==0)
		catList= itemCategoryService.findAll();
		else {
			catList= itemCategoryService.findByBusinessId(businessId);
		}
		view.addObject("catList", catList);

		int cateId1=0;
		if(CollectionUtils.isNotEmpty(catList)){
			cateId1  = catList.get(0).getId();
		}

		view.addObject("cateId1", cateId1);

		List<Item> itemList = itemService.findAllWithStock();
		view.addObject("itemList", itemList);
		
		List<SnackPackageType> snackList=snackPackageTypeService.selectAll();
		view.addObject("snackList", snackList);
	}
	
	/**
	 * @return
	 */
	@RequestMapping(value = "/AutoSnackOrder.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String generateAutoSnackOrder(HttpServletRequest request,@RequestParam String standardorderId,
			@RequestParam String budget) {
		if (logger.isDebugEnabled()) {
			logger.debug("入参信息" + ", standardorderId:" + standardorderId
					+ ", budget:" + budget);
		}
		String result="";
		JsonResult results = new JsonResult();
		try {
			//生成标准包
			StandardOrder order = new StandardOrder();
			if(StringUtils.isNotEmpty(standardorderId)){
				if(StringUtils.isNotEmpty(budget)){
					Long budgetFee=Long.parseLong(budget)*100;
					StandardOrder snackPackageOrder=autoStandradOrder.getSnackPackageOrderbyHand(Integer.parseInt(standardorderId),budgetFee);
					if(snackPackageOrder!=null){
						Long price=snackPackageOrder.getTotalFee();
						Long costPrice=snackPackageOrder.getTotalCost();
						results.setTotalFee(price);
						results.setTotalCost(costPrice);
						BigDecimal profit=new BigDecimal(0);
						double priced=(double)price;
						double costPriced=(double)costPrice;
						double profitPrice=((priced-costPriced)/priced)*100;
						profit=new BigDecimal(profitPrice);
						results.setProfitRate(profit.setScale(2, BigDecimal.ROUND_HALF_UP));
						results.setMsg("totalPrice:" + new BigDecimal(price).movePointLeft(2));
						List<Item> items = Lists.newArrayList();
						List<StandardOrderItem> itemList = snackPackageOrder.getStandardOrderList();
						for (StandardOrderItem standardOrderItem : itemList) {
							Item item=new Item();
							item.setCategoryId(standardOrderItem.getCategoryId());
							item.setId(standardOrderItem.getItemId());
							item.setSize(standardOrderItem.getItemSizeType());
							item.setPurchaseNum(standardOrderItem.getNum());
							item.setSalePrice(standardOrderItem.getItemPrice());
							item.setStock(0);
							item.setActualUsedStock(standardOrderItem.getStockNum());
							item.setTotalPurchasePrice(standardOrderItem.getFee());
							items.add(item);
						}
						results.setDataList(items);
					    results.setStatus("success");
					}
				}
			}
		} catch (Exception e) {
			logger.error("自动生成零食包", e);
		}
        return GsonUtil.jsonResult2String(results);
	}
	
	public String AddorUpdateStandardOrder(HttpServletRequest req,String value)
	{
		try {
			StandardOrder order = new StandardOrder();
			String executedTimeStr = req.getParameter("executedTime");
			String snackpackageType = req.getParameter("snackpackageType");
			String budget = req.getParameter("budget");
			String offerPrice = req.getParameter("offerPrice");
			String weight = req.getParameter("weight");
			String remark = req.getParameter("remark");
			if (StringUtils.isEmpty(executedTimeStr)) {
				order.setExecutedTime(new Date());
			} else {
				order.setExecutedTime(DateUtil.parseDateStr(executedTimeStr, DATE_FORMAT_YMD));
			}
			if (StringUtils.isNotEmpty(snackpackageType)) {
				order.setSnackpackageType(Integer.parseInt(snackpackageType));
			}
			if (StringUtils.isNotEmpty(remark)) {
				order.setRemark(remark);
			}
			if (StringUtils.isNotEmpty(budget)) {
				order.setBudget(Integer.parseInt(budget)*100L);//商品总金额
			}
			if (StringUtils.isNotEmpty(offerPrice)) {
				order.setOfferPrice((long)(Double.parseDouble(offerPrice)*100));//销售价
			}
			if (StringUtils.isNotEmpty(weight)) {
				order.setWeight(new BigDecimal(weight));//重量
			}
			Date exce=order.getExecutedTime();
			order.setWeek(DateUtil.getWeekInt(exce));
			List<StandardOrderItem> orderItemList = new ArrayList<StandardOrderItem>();
			int rowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("rowCount"), "0"));
			for (int i = 0; i < rowCount; i++) {
				int itemId = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("itemId" + i), "0"));
				if (itemId == 0) {
					continue;
				}
				int num = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("purchaseNum" + i), "0"));
				String specType = StringUtils.defaultString(req.getParameter("specification" + i), "");
				try {
					StandardOrderItem oi = new StandardOrderItem();
					oi.setItemId(itemId);
					oi.setNum(num);
					oi.setItemSizeType(specType);
					orderItemList.add(oi);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
			Integer orderTotalNum = 0;
			Long orderTotalFee = 0l;
			Long orderTotalCost = 0l;
			long notaxInclusivecostPrice = 0l; // 成本价
			order.setStandardOrderList(orderItemList);
			for (StandardOrderItem oi : orderItemList) {
				if(oi.getNum()==null||oi.getNum()==0){
					return "商品 :" + oi.getItemName() + ",数量为0";
				}
				Item item = this.itemService.findById(oi.getItemId());
				if (item == null) {
					return "商品 :" + oi.getItemName() + ",无法找到商品数据";
				}
				String itemSizeType = oi.getItemSizeType();	// 规格
				long price = 0l; //单价
				long costPrice = 0l; // 成本价
				String itemSize = null;
				if (itemSizeType.equals("SIZE")) { //普通规格
					itemSize = item.getSize();
					price = item.getPrice();
					costPrice = item.getCostPrice();
					notaxInclusivecostPrice = item.getNotaxInclusiveCostPrice();
				} else if (itemSizeType.equals("SALE_SIZE")) { // 零售
					itemSize = item.getSaleSize();
					price = item.getSalePrice();
					costPrice = item.getSaleCostPrice();
					notaxInclusivecostPrice = item.getNotaxInclusiveSaleCostPrice();
				} else if (itemSizeType.equals("BUY_SIZE")) { //批发
					itemSize = item.getBuySize();
					price = item.getPurchasePrice();
					costPrice = item.getBuyPrice();
					notaxInclusivecostPrice = item.getNotaxInclusiveBuyPrice();
				} else {
					return "商品 :" + oi.getItemName() + ",无法找到规格数据";
				}
				oi.setFee(price * oi.getNum());
				oi.setItemName(item.getItemName());
				oi.setItemPrice(price);
				oi.setItemCostPrice(costPrice);
				oi.setNotaxinclusivecostprice(notaxInclusivecostPrice);
				oi.setItemSize(itemSize);
				oi.setStockNum(item.getSaleSizeNum()*oi.getNum());
				orderTotalNum += oi.getNum();
				orderTotalFee += oi.getFee();
				orderTotalCost += costPrice*oi.getNum();
			}
			if(orderTotalNum<=0){
				return "订单中商品数量为0";
			}
			order.setTotalFee(orderTotalFee);
			order.setTotalNum(orderTotalNum);
			order.setTotalCost(orderTotalCost);
			if(value.equals("添加")){
				order.setStandardStatus(Constant.DELETE_STATUS);
				order.setStatus(Constant.VALID_STATUS);
				order.setSnackpackageStatus(Constant.DELETE_STATUS);
				order.setCreatedTime(new Date());
				order.setCreatedUserid(SessionHelper.getUserId(req.getSession()));
				order.setUpdatedTime(new Date());
				order.setUpdatedUserid(order.getCreatedUserid());
				String standardorderId = req.getParameter("standardorderId");
				if(StringUtils.isNotEmpty(standardorderId)){
					order.setParentid(Integer.parseInt(standardorderId));
				}
				standardOrderService.createStandardOrder(order);
				StandardOrder order2 = new StandardOrder();
				if(StringUtils.isNotEmpty(standardorderId)){
					order2.setStandardorderId(Integer.parseInt(standardorderId));
				}
				order2.setSnackpackageStatus(1);
				order2.setUpdatedTime(new Date());
				order2.setUpdatedUserid(SessionHelper.getUserId(req.getSession()));
				standardOrderService.updateStandardOrder(order2);
				
			}else{
				String standardorderId = req.getParameter("standardorderId");
				if(StringUtils.isNotEmpty(standardorderId)){
					order.setStandardorderId(Integer.parseInt(standardorderId));
				}
				order.setUpdatedTime(new Date());
				order.setUpdatedUserid(SessionHelper.getUserId(req.getSession()));
				standardOrderService.updateOrderAndItems(order);
			}
			this.saveLog(req.getSession(),order, value+"零食包，orderNo:"+order.getStandardorderId());
			return value+"成功";

		} catch (Throwable e) {
			logger.error(value+"零食包失败", e);
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "/addOrder.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest req) {
		return AddorUpdateStandardOrder(req,"添加");
	}
	
	@RequestMapping("showOrder.htm")
	public ModelAndView editOrderShow(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("snackPackage/edit");

		try {
			String standardorderId = request.getParameter("standardorderId");
			if(StringUtils.isNotEmpty(standardorderId)){
			//Pair<Order, List<OrderItem>> pair = orderService.findByOrderNo(orderNo);
			//查询结果多加一个isDown字段，用于判断订单列表中下架的商品
			Pair<StandardOrder, List<HashMap<String, Object>>> pair = standardOrderService.findInfoByOrderNo(Integer.parseInt(standardorderId));
			Date executedTime=pair.getLeft().getExecutedTime();
			if(DateUtil.diff(executedTime,new Date(),Calendar.SECOND)>0){
				mv.addObject("isUpdateOfferPrice", 1);
			}else{
				mv.addObject("isUpdateOfferPrice", 0);
			}
			mv.addObject("pair", pair);
			mv.addObject("itemSize",CollectionUtils.size(pair.getRight()));
			this.fillCommonData(mv, 0);
			}
		} catch (Throwable e) {
			logger.error("编辑预订单显示", e);
		}

		return mv;
	}
	
	@RequestMapping(value="editOrder.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String editOrderSave( HttpServletRequest req) {
		return this.AddorUpdateStandardOrder(req, "保存");
	}
	
	@RequestMapping(value = "autoSnackPackageOrder.do", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String run(HttpServletRequest request) throws Exception {
		//ModelAndView view = new ModelAndView("redirect:/snackPackage/snackPackageOrderlist.htm");
		String result="";
		try {
			//生成标准包
			String standardorderId = request.getParameter("standardorderId");
			StandardOrder order = new StandardOrder();
			if(StringUtils.isNotEmpty(standardorderId)){
				Long budgetFee=20000L;
				result =autoStandradOrder.getSnackPackageOrder(Integer.parseInt(standardorderId),budgetFee);
				order.setStandardorderId(Integer.parseInt(standardorderId));
				order.setSnackpackageStatus(1);
				order.setUpdatedTime(new Date());
				order.setUpdatedUserid(SessionHelper.getUserId(request.getSession()));
				standardOrderService.updateStandardOrder(order);
			}
		} catch (Exception e) {
			logger.error("自动生成零食包", e);
			//return e.getMessage();
		}
		//return result;
		return "success";
	}
	
	@RequestMapping(value = "/printOrders.do", method = RequestMethod.POST)
	public ModelAndView printOrders(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("snackPackage/printOrders");
		String orderNo = request.getParameter("orderNo");
		StandardOrder order = this.standardOrderService.findByStandId(orderNo);
		List<StandardOrderItem> item = this.standardOrderService.findOrderItemById(Integer.parseInt(orderNo));
		mv.addObject("itemlist", item);
		mv.addObject("name", order.getTypeValue());
		return mv;
	}
	//
	@RequestMapping("standardOrderlist.htm")
	public ModelAndView getListPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("snackPackage/packageList");
		try {

			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));

			int queryUser = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("queryUser"), "0"));

			mv.addObject("queryUser", queryUser);

			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");

			Date startTime = null;
			Date endTime = null;
			int snackpackageTypeInt=0;

			if (StringUtils.isNotBlank(startTimeStr)) {
				startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
				mv.addObject("startTime", startTimeStr);
			}

			if (StringUtils.isNotBlank(endTimeStr)) {
				endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YMD);
				mv.addObject("endTime", endTimeStr);
			}


			StandardOrder order = new StandardOrder();
			String standardorderId = request.getParameter("standardorderId");
			if(StringUtils.isNotEmpty(standardorderId)){
				order.setStandardorderId(Integer.parseInt(standardorderId));
				mv.addObject("standardorderId", standardorderId);
			}else{
				order.setStandardorderId(0);
				mv.addObject("standardorderId", 0);
			}
			
			String snackpackageType = request.getParameter("snackpackageType");
			if (StringUtils.isNotBlank(snackpackageType)) {
				snackpackageTypeInt=Integer.parseInt(snackpackageType);
				order.setSnackpackageType(snackpackageTypeInt);
				mv.addObject("snackpackageType", snackpackageType);
			}
			//PersonUser user=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);

			Page<Pair<StandardOrder, List<StandardOrderItem>>> orderPage = standardOrderService
					.findStandardOrder(order, startTime, endTime, currentPage,
							Page.DEFAULT_PAGE_SIZE);
			Map<String, String> orderMap = Maps.newHashMap();
			mv.addObject("orderMap", orderMap);

			mv.addObject("page", orderPage);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<Pair<StandardOrder, List<StandardOrderItem>>> page = new Page<Pair<StandardOrder, List<StandardOrderItem>>>(1, 1, Page.DEFAULT_PAGE_SIZE,new ArrayList<Pair<StandardOrder, List<StandardOrderItem>>>());
			mv.addObject("page", page);
		}
		return mv;
	}
	
	@RequestMapping("addstandardOrder.htm")
	public ModelAndView addOrderPage(HttpServletRequest request,
			String returnedPurchase) {
		ModelAndView mv = new ModelAndView("snackPackage/packageAdd");
		mv.addObject("executedTime", new Date());
		this.fillCommonData(mv, 0);
		return mv;
	}
	
	@RequestMapping("show.htm")
	public ModelAndView editShow(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("snackPackage/packageEdit");
		try {
			String standardorderId = request.getParameter("standardorderId");
			if(StringUtils.isNotEmpty(standardorderId)){
			//Pair<Order, List<OrderItem>> pair = orderService.findByOrderNo(orderNo);
			//查询结果多加一个isDown字段，用于判断订单列表中下架的商品
			Pair<StandardOrder, List<HashMap<String, Object>>> pair = standardOrderService.findInfoByOrderNo(Integer.parseInt(standardorderId));
			mv.addObject("pair", pair);
			mv.addObject("itemSize",CollectionUtils.size(pair.getRight()));
			String snackpackageType = request.getParameter("snackpackageType");
			if (StringUtils.isNotBlank(snackpackageType)) {
				mv.addObject("snackpackageType", snackpackageType);
			}
			this.fillCommonData(mv, 0);
			}
		} catch (Throwable e) {
			logger.error("编辑标准套餐订单显示", e);
		}
		return mv;
	}
	
	@RequestMapping(value = "/addBag.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addCombo(HttpServletRequest req) {
		return AddorUpdateStandardComboOrder(req,"添加");
	}
	
	@RequestMapping(value="editBag.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String editCombo( HttpServletRequest req) {
		return this.AddorUpdateStandardComboOrder(req, "保存");
	}
	
	public String AddorUpdateStandardComboOrder(HttpServletRequest req,String value)
	{	
		StandardOrder order = new StandardOrder();
		try {
			
			order.setStandardStatus(Constant.DELETE_STATUS);
			order.setStatus(Constant.VALID_STATUS);
			String executedTimeStr = req.getParameter("executedTime");
			if (StringUtils.isEmpty(executedTimeStr)) {
				order.setExecutedTime(new Date());
			} else {
				order.setExecutedTime(DateUtil.parseDateStr(executedTimeStr, DATE_FORMAT_YMD));
			}
			Date exce=order.getExecutedTime();
			order.setWeek(DateUtil.getWeekInt(exce));
            Calendar calendar = Calendar.getInstance();
			calendar.setTime(exce);
			List<StandardOrderItem> orderItemList = new ArrayList<StandardOrderItem>();
			int rowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("rowCount"), "0"));
			for (int i = 0; i < rowCount; i++) {
				int itemId = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("itemId" + i), "0"));
				if (itemId == 0) {
					continue;
				}
				int num = 1;
				String specType = StringUtils.defaultString(req.getParameter("specification" + i), "");
				try {
					StandardOrderItem oi = new StandardOrderItem();
					oi.setItemId(itemId);
					oi.setNum(num);
					oi.setItemSizeType(specType);
					orderItemList.add(oi);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
			Integer orderTotalNum = 0;
			Long orderTotalFee = 0l;
			Long orderTotalCost = 0l;
			order.setStandardOrderList(orderItemList);
			for (StandardOrderItem oi : orderItemList) {
				if(oi.getNum()==null||oi.getNum()==0){
					return "商品 :" + oi.getItemName() + ",数量为0";
				}
				Item item = this.itemService.findById(oi.getItemId());
				if (item == null) {
					return "商品 :" + oi.getItemName() + ",无法找到商品数据";
				}
				String itemSizeType = oi.getItemSizeType();	// 规格
				long price = 0l; //单价
				long costPrice = 0l; // 成本价
				long notaxInclusivecostPrice = 0l; // 成本价
				String itemSize = null;
				if (itemSizeType.equals("SIZE")) { //普通规格
					itemSize = item.getSize();
					price = item.getPrice();
					costPrice = item.getCostPrice();
					notaxInclusivecostPrice = item.getNotaxInclusiveCostPrice();
				} else if (itemSizeType.equals("SALE_SIZE")) { // 零售
					itemSize = item.getSaleSize();
					price = item.getSalePrice();
					costPrice = item.getSaleCostPrice();
					notaxInclusivecostPrice = item.getNotaxInclusiveSaleCostPrice();
				} else if (itemSizeType.equals("BUY_SIZE")) { //批发
					itemSize = item.getBuySize();
					price = item.getPurchasePrice();
					costPrice = item.getBuyPrice();
					notaxInclusivecostPrice = item.getNotaxInclusiveBuyPrice();
				} else {
					return "商品 :" + oi.getItemName() + ",无法找到规格数据";
				}
				oi.setFee(price * oi.getNum());
				oi.setItemName(item.getItemName());
				oi.setItemPrice(price);
				oi.setItemCostPrice(costPrice);
				oi.setNotaxinclusivecostprice(notaxInclusivecostPrice);
				oi.setItemSize(itemSize);
				orderTotalNum += oi.getNum();
				orderTotalFee += oi.getFee();
				orderTotalCost += costPrice*oi.getNum();
			}
			if(orderTotalNum<=0){
				return "订单中商品数量为0";
			}
			order.setTotalFee(orderTotalFee);
			if(value.equals("添加")){
				order.setCreatedTime(new Date());
				order.setCreatedUserid(SessionHelper.getUserId(req.getSession()));
				order.setUpdatedTime(new Date());
				order.setUpdatedUserid(order.getCreatedUserid());
				order.setSnackpackageStatus(Constant.DELETE_STATUS);
				order.setSnackpackageType(2);
				standardOrderService.createStandardOrder(order);
			}else{
				order.setUpdatedTime(new Date());
				order.setUpdatedUserid(SessionHelper.getUserId(req.getSession()));
				String standardorderId = req.getParameter("standardorderId");
				if(StringUtils.isNotEmpty(standardorderId)){
					order.setStandardorderId(Integer.parseInt(standardorderId));
				}
				standardOrderService.updateOrderAndItems(order);
			}
			this.saveLog(req.getSession(),order, value+"订单，orderNo:"+order.getStandardorderId());
			return value+"成功";

		} catch (Throwable e) {
			logger.error(value+"订单失败", e);
			return e.getMessage();
		}
	}
	
	private void saveLog(HttpSession session,StandardOrder dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.StandardOrder.getName());
	       sysLog.setDataId(dto.getStandardorderId()+"");

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<StandardOrder>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}

}
