package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
import com.b2b.common.util.OrderNumberGenerator;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.enums.OrderStatusEnum;
import com.b2b.page.Page;
import com.b2b.service.*;
import com.b2b.web.util.GsonUtil;
import com.b2b.web.util.JsonResult;
import com.b2b.web.util.NumberTool;
import com.b2b.web.util.SessionHelper;
import com.b2b.web.wx.util.CommonUtil;
import com.b2b.web.wx.util.NotifyUtil;
import com.b2b.web.wx.util.Properties;
import com.b2b.web.wx.util.Token;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.*;
import jxl.write.Number;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;


@Controller
@RequestMapping("/order")
public class OrderController {

	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

	@Autowired
	OrderService orderService;

	@Autowired
	CustomerService customerService;

	@Autowired
	ItemService itemService;

	@Autowired
	ItemCategoryService itemCategoryService;

	@Autowired
	BusinessService businessService;

	@Autowired
	LogService logService;

	@Autowired
	AccountLockService accountLockService;
	
	@Autowired
	OrderBagService orderBagService;

	@Autowired
	StockService stockService;

	@Autowired
	StandardOrderService standardOrderService;

	@Autowired
	WXAccountService wxAccountService;
	
	@Autowired
	ShopBlackListService shopBlackListService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PersonRegionService personRegionService;
	
	@Autowired
	AfterSalesRecordService afterSalesRecordService;
	
	@Autowired
	WholeTokenService wholeTokenService;
	
	@Autowired
	Properties properties;
	
	@Autowired
	ReseauService reseauService;

	@RequestMapping("orderlist.htm")
	public ModelAndView getOrderListPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("order/list");
		try {
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return new ModelAndView("noCity");
			}
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));

			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");

			Date startTime = null;
			Date endTime = null;

			if (StringUtils.isNotBlank(startTimeStr)) {
				startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
				mv.addObject("startTime", startTimeStr);
			}

			if (StringUtils.isNotBlank(endTimeStr)) {
				endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YMD);
				mv.addObject("endTime", endTimeStr);
			}

			String orderNum = request.getParameter("orderNum");
			mv.addObject("orderNum", orderNum);
			String userName = request.getParameter("userName");
			mv.addObject("userName", userName);
			String itemName = request.getParameter("itemName");
			mv.addObject("itemName", itemName);
			int param = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("param"), "0"));
			mv.addObject("param", param);
			
			PageHelper.startPage(currentPage, Page.DEFAULT_PAGE_SIZE);
			
			List<Order> orders = this.orderService.findOrderAndOrderItemByCondition(orderNum,userName,param,startTime,endTime,user.getCityId(),itemName);
			
			PageInfo<Order> info = new PageInfo<Order>(orders);
			
			Page<Order> page = new Page<Order>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());

			mv.addObject("page", page);
			
			int num = this.orderService.findBeComfirmOrderNumByCityId(user.getCityId());
			mv.addObject("num", num);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<Pair<Order, List<OrderItem>>> page = new Page<Pair<Order, List<OrderItem>>>(1, 1, Page.DEFAULT_PAGE_SIZE,new ArrayList<Pair<Order, List<OrderItem>>>());
			mv.addObject("page", page);
		}
		TestController.getMenuPoint(mv, request);
		return mv;
	}

	@RequestMapping("orderPrint.htm")
	public ModelAndView print(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("order/print");

		String orderNo = request.getParameter("orderNo");

		Pair<Order, List<OrderItem>> pair = orderService
				.findByOrderNo(orderNo);
		if (pair == null || pair.getLeft() == null) {
			throw new NullPointerException("该订单不存在");
		}


		mv.addObject("order",pair.getLeft());
		mv.addObject("itemList",pair.getRight());

		TestController.getMenuPoint(mv, request);
		return mv;
	}

	@RequestMapping(value = "/printOrders.do")
	public ModelAndView printOrders(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("order/printOrders");

		String orderNos = request.getParameter("orderNos");
		if (StringUtils.isBlank(orderNos)) {
			throw new NullPointerException("订单参数异常");
		}
		String[] orders = orderNos.split(",");
		Map<Integer, CustomerUser> userMap = Maps.newHashMap();
		Map<Integer, String> businessMap = Maps.newHashMap();
		//key: userId 按用户合并订单数据
		Map<Integer, List<Pair<Order, List<OrderItem>>>> osMaps = Maps.newHashMap();
		for (String orderNo : orders) {
			List<Pair<Order, List<OrderItem>>> osList = Lists.newArrayList();
			Pair<Order, List<OrderItem>> pair = orderService.findByOrderNoSort(orderNo.trim());
			if (pair == null || pair.getLeft() == null) {
				logger.error("订单数据异常, orderNo:" + orderNo);
				continue;
			}
			osList.add(pair);
			Integer userId = pair.getLeft().getUserId();
			if (osMaps.keySet().contains(userId)) {
				osMaps.get(userId).addAll(osList);
			} else {
				osMaps.put(userId, osList);
			}
			//获取用户信息
			CustomerUser user = customerService.findPrintOrdersInfo(userId);
			userMap.put(userId, user);
			//获取商家信息
			if(customerService.findById(userId).getBusinessId()==0){
				businessMap.put(userId,"自营");
			}
			else{
				businessMap.put(userId, businessService.findById(customerService.findById(userId).getBusinessId()).getBusinessName());
			}

			//this.orderService.updateOrderStatus(orderNo,OrderStatusEnum.PRINT);
		}

		mv.addObject("printDate", new Date());
		mv.addObject("osMaps", osMaps);
		mv.addObject("userMap", userMap);
		mv.addObject("businessMap", businessMap);


		return mv;
	}
	
	@RequestMapping(value = "/changeprintOrders.do", method = RequestMethod.POST)
	public String changeprintOrders(@RequestParam("orderNo")String orderNo) {
	     try {
			this.orderService.updateOrderStatus(orderNo,OrderStatusEnum.PRINT);
			return "200";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     return "201";

		
	}

	@RequestMapping(value = "/updateOrder.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String updateOrder(HttpServletRequest request) {
		try {

			String orderNo = request.getParameter("orderNo");
			int status = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("status"), "-1"));

			OrderStatusEnum statusEnum = OrderStatusEnum.parseId(status);
			if(statusEnum!=null){
				orderService.updateOrderStatus(orderNo, statusEnum);
			}else{
				return "failure";
			}
		} catch (Throwable e) {
			logger.error("编辑订单状态", e);
			return "failure";
		}
		return "success";
	}


	@RequestMapping(value = "/cancelOrder.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String cancelOrder(HttpServletRequest request) {
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			String orderNo = request.getParameter("orderNo");
			//会员充值用户取消订单
			Order order = this.orderService.findOrderById(orderNo);
			if(!personUser.getCityId().equals(order.getCityId())){
				return "你设置的城市与实际不符";
			}
			CustomerUser user = this.customerService.findById(order.getUserId());
			order.setStatus(Constant.DELETE_STATUS);
			if(null!=user.getIswxvip()&&1==user.getIswxvip()){
				PersonUser personuser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
				this.orderService.cancelOrderAndAddMoney(order,personuser.getId());
				this.saveLog(request.getSession(),order, "会员充值用户后台取消订单，orderNo:"+order.getOrderNo()+"加回预付款:"+order.getTotalFee(),personUser.getCityId());
			}else if(null!=user.getIswxvip()&&2==user.getIswxvip()){
				return "该用户未激活!";
			}else{
				this.orderService.cancelOrder(order);
				this.saveLog(request.getSession(),order, "取消订单，orderNo:"+order.getOrderNo(),personUser.getCityId());
			}


		} catch (Throwable e) {
			logger.error("取消订单失败", e);
			return "failure";
		}
		return "success";
	}
	
	@RequestMapping(value = "/cancelComfirmOrder.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String cancelComfirmOrder(HttpServletRequest request) {
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			String orderNo = request.getParameter("orderNo");
			Order order = this.orderService.findOrderById(orderNo);
			if(!personUser.getCityId().equals(order.getCityId())){
				return "你设置的城市与实际不符";
			}
			CustomerUser user = this.customerService.findById(order.getUserId());
			order.setStatus(Constant.DELETE_STATUS);
			this.orderService.cancelComfirmOrder(order);
			this.saveLog(request.getSession(),order, "取消订单，orderNo:"+order.getOrderNo(),personUser.getCityId());

		} catch (Throwable e) {
			logger.error("取消订单失败", e);
			return "failure";
		}
		return "success";
	}
	
	@RequestMapping("showOrder.htm")
	public ModelAndView editOrderShow(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("order/edit");
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		try {
			String orderNo = request.getParameter("orderNo");
			//Pair<Order, List<OrderItem>> pair = orderService.findByOrderNo(orderNo);
			//查询结果多加一个isDown字段，用于判断订单列表中下架的商品
			Pair<Order, List<HashMap<String, Object>>> pair = orderService.findInfoByOrderNo(orderNo);
			
			mv.addObject("pair", pair);


			mv.addObject("itemSize",CollectionUtils.size(pair.getRight()));

			this.fillCommonData2(mv, user.getCityId(),pair.getKey().getUserId());
		} catch (Throwable e) {
			logger.error("编辑订单显示", e);
		}
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	//编辑待确认订单页面
	@RequestMapping("showComfirmOrder.htm")
	public ModelAndView editComfirmOrderShow(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("order/editComfirm");
		TestController.getMenuPoint(mv, request);
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		try {
			String orderNo = request.getParameter("orderNo");
			//Pair<Order, List<OrderItem>> pair = orderService.findByOrderNo(orderNo);
			//查询结果多加一个isDown字段，用于判断订单列表中下架的商品
			Pair<Order, List<HashMap<String, Object>>> pair = orderService.findInfoByOrderNo(orderNo);
			mv.addObject("pair", pair);


			mv.addObject("itemSize",CollectionUtils.size(pair.getRight()));

			this.fillCommonData2(mv, user.getCityId(),pair.getKey().getUserId());
		} catch (Throwable e) {
			logger.error("编辑订单显示", e);
		}

		return mv;
	}
	

	@RequestMapping(value="editOrder.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String editOrderSave( HttpServletRequest req) {
		try{
			PersonUser pUser = (PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			String buyerId = req.getParameter("buyerId");
			if (StringUtils.isEmpty(buyerId)) {
				return "用户ID不能为空!";
			}
			String discount = req.getParameter("discount");
			if(StringUtils.isEmpty(discount))
			{
				return "折扣不能不空!";
			}
			String type = req.getParameter("type");
			if (StringUtils.isEmpty(type)) {
				return "请选择是否上架";
			}
			BigDecimal dis=new BigDecimal(discount);
			CustomerUser personUser = customerService.findById(Integer.parseInt(buyerId));
			
			Order order = new Order();
			order.setMemo(req.getParameter("memo"));
			order.setAddress(personUser.getAddress());
			order.setDiscount(dis);
			order.setUserId(personUser.getId());
			order.setBusinessId(personUser.getBusinessId());
			order.setOrderNo(req.getParameter("orderNo"));
			order.setType(Integer.parseInt(type));
			String executedTimeStr = req.getParameter("executedTime");
			if (StringUtils.isEmpty(executedTimeStr)) {
				order.setExecutedTime(new Date());
			} else {
				order.setExecutedTime(DateUtil.parseDateStr(executedTimeStr, DATE_FORMAT_YMD));
			}
			Order order2 = this.orderService.findOrderById(req.getParameter("orderNo"));
			if(null==order2 || !pUser.getCityId().equals(order2.getCityId())){
				return "你设置的默认城市与实际不符";
			}
			AccountLock accountLock=new AccountLock();
			Date exce=order.getExecutedTime();
            Calendar calendar = Calendar.getInstance();
			calendar.setTime(exce);
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH)+1;
            accountLock.setYears(year+"");
            accountLock.setMonths(month+"");
            accountLock.setCityId(pUser.getCityId());
            int lock=accountLockService.findLockByCityId(accountLock);
			if(lock==1)
			{
				return "执行时间不能设置到已锁帐月份!";
			}

			List<OrderItem> orderItemList = new ArrayList<OrderItem>();

			int rowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("rowCount"), "0"));
			
			//判断便利店用户下单规格
			
				for (int i = 0; i < rowCount; i++) {
					String specType = StringUtils.defaultString(req.getParameter("specification" + i), "");
					int itemId = Integer.parseInt(StringUtils.defaultString(
							req.getParameter("itemId" + i), "0"));
					if (itemId == 0) {
						continue;
					}
					int num = Integer.parseInt(StringUtils.defaultString(
							req.getParameter("purchaseNum" + i), "0"));
					try {
						OrderItem oi = new OrderItem();
						oi.setOrderNo(order.getOrderNo());
						oi.setItemId(itemId);
						oi.setNum(num);
						if(personUser.getIswxvip()==1){
							try {
								double par = Double.parseDouble(req.getParameter("price" + i));
								Double a = par*100;
								long price = Math.round(a);
								oi.setFee(price*num);
								oi.setItemPrice(price);
							} catch (Exception e) {
								e.printStackTrace();
								return "请填写正确的价格";
							}
							
						}
						if(StringUtils.isEmpty(specType)){
							logger.warn("itemid :" + itemId + ",order no :" + order.getOrderNo() + " not found specType");
						}
						oi.setItemSizeType(specType);
						orderItemList.add(oi);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			
			
			

			Integer orderTotalNum = 0;
			Long orderTotalFee = 0l;
			Long orderTotalCost = 0l;
			Long notaxInclusiveTotalCost = 0l;
			order.setOrderItemList(orderItemList);

			for (OrderItem oi : orderItemList) {
				Item item = this.itemService.findById(oi.getItemId());
				if(oi.getNum()==null||oi.getNum()==0){
					return "商品id :" + oi.getItemId() + ",数量为0";
				}
				if (item == null) {
					return "商品id :" + oi.getItemId() + ",无法找到商品数据";
				}
				if(personUser.getIswxvip()==3){
					if(null==item.getImgPath()||StringUtils.isEmpty(item.getImgPath())){
						return "商品 :" + oi.getItemName() + ",图片不符合";
					}
					String itemSizeType = oi.getItemSizeType();	// 规格
					long price = 0l; //单价
					long costPrice = 0l; // 成本价
					long notaxInclusiveCostPrice = 0l;
					String itemSize = null;
					if (itemSizeType.equals("SIZE")) { //普通规格
						itemSize = item.getSize();
						price = item.getPrice();
						costPrice = item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
					} else if (itemSizeType.equals("SALE_SIZE")) { // 零售
						itemSize = item.getSize();
						price = item.getPrice();
						costPrice = item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
						oi.setNum(oi.getNum()*item.getSaleSizeNum());
						oi.setItemSizeType("SIZE");
					} else if (itemSizeType.equals("BUY_SIZE")) { //批发
						itemSize = item.getSize();
						price = item.getPrice();
						costPrice = item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
						oi.setNum(oi.getNum()*item.getConvertNum());
						oi.setItemSizeType("SIZE");
					} else {
						return "商品 :" + oi.getItemName() + ",无法找到规格数据";
					}
					//BigDecimal pr=(new BigDecimal(price)).multiply(dis).setScale(2, BigDecimal.ROUND_HALF_UP);
					//price=pr.longValue();
					oi.setFee(price * oi.getNum());
					oi.setItemName(item.getItemName());
					oi.setItemPrice(price);
					oi.setItemCostPrice(costPrice);
					oi.setNotaxInclusiveCostPrice(notaxInclusiveCostPrice);
					oi.setItemSize(itemSize);

					orderTotalNum += oi.getNum();
					orderTotalFee += oi.getFee();
					orderTotalCost += costPrice*oi.getNum();
					notaxInclusiveTotalCost += notaxInclusiveCostPrice*oi.getNum();
				}else if (personUser.getIswxvip()==1) {
					String itemSizeType = oi.getItemSizeType();	// 规格
					long price = 0l; //单价
					long costPrice = 0l; // 成本价
					long notaxInclusiveCostPrice = 0l;
					String itemSize = null;
					if ("SIZE".equals(itemSizeType)) { //普通规格
						itemSize = item.getSize();
						price = (item.getPrice()==null)?0:item.getPrice();
						costPrice = (item.getCostPrice()==null)?0:item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
					} else if ("SALE_SIZE".equals(itemSizeType)) { // 零售
//						itemSize = item.getSaleSize();
//						price = (item.getSalePrice()==null)?0:item.getSalePrice();
//						costPrice = (item.getSaleCostPrice()==null)?0:item.getSaleCostPrice();
//						notaxInclusiveCostPrice = item.getNotaxInclusiveSaleCostPrice();
						itemSize = item.getSize();
						price = item.getPrice();
						costPrice = item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
						oi.setNum(oi.getNum()*item.getSaleSizeNum());
						oi.setItemSizeType("SIZE");
					} else if ("BUY_SIZE".equals(itemSizeType)) { //批发
						itemSize = item.getBuySize();
						price = (item.getPurchasePrice()==null)?0:item.getPurchasePrice();
						costPrice = (item.getBuyPrice()==null)?0:item.getBuyPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveBuyPrice();
					} else {
						return "无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSizeType;
					}
					//BigDecimal pr=(new BigDecimal(price)).multiply(dis).setScale(2, BigDecimal.ROUND_HALF_UP);
					//price=pr.longValue();
					oi.setItemName(item.getItemName());
					oi.setItemCostPrice(costPrice);
					oi.setNotaxInclusiveCostPrice(notaxInclusiveCostPrice);
					oi.setItemSize(itemSize);
					
					orderTotalNum += oi.getNum();
					orderTotalFee += oi.getFee();
					orderTotalCost += costPrice*oi.getNum();
					notaxInclusiveTotalCost += notaxInclusiveCostPrice*oi.getNum();
				}else{
					String itemSizeType = oi.getItemSizeType();	// 规格
					long price = 0l; //单价
					long costPrice = 0l; // 成本价
					long notaxInclusiveCostPrice = 0l;
					String itemSize = null;
					if ("SIZE".equals(itemSizeType)) { //普通规格
						itemSize = item.getSize();
						price = (item.getPrice()==null)?0:item.getPrice();
						costPrice = (item.getCostPrice()==null)?0:item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
					} else if ("SALE_SIZE".equals(itemSizeType)) { // 零售
//						itemSize = item.getSaleSize();
//						price = (item.getSalePrice()==null)?0:item.getSalePrice();
//						costPrice = (item.getSaleCostPrice()==null)?0:item.getSaleCostPrice();
//						notaxInclusiveCostPrice = item.getNotaxInclusiveSaleCostPrice();
						itemSize = item.getSize();
						price = item.getPrice();
						costPrice = item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
						oi.setNum(oi.getNum()*item.getSaleSizeNum());
						oi.setItemSizeType("SIZE");
					} else if ("BUY_SIZE".equals(itemSizeType)) { //批发
						itemSize = item.getBuySize();
						price = (item.getPurchasePrice()==null)?0:item.getPurchasePrice();
						costPrice = (item.getBuyPrice()==null)?0:item.getBuyPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveBuyPrice();
					} else {
						return "无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSizeType;
					}
					//BigDecimal pr=(new BigDecimal(price)).multiply(dis).setScale(2, BigDecimal.ROUND_HALF_UP);
					//price=pr.longValue();
					oi.setFee(price * oi.getNum());
					oi.setItemName(item.getItemName());
					oi.setItemPrice(price);
					oi.setItemCostPrice(costPrice);
					oi.setNotaxInclusiveCostPrice(notaxInclusiveCostPrice);
					oi.setItemSize(itemSize);
					
					orderTotalNum += oi.getNum();
					orderTotalFee += oi.getFee();
					orderTotalCost += costPrice*oi.getNum();
					notaxInclusiveTotalCost += notaxInclusiveCostPrice*oi.getNum();
				}
			}

			if(orderTotalNum<=0){
				return "订单中商品数量为0";
			}
			int sign = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("sign"), "0"));
			order.setSign(sign);
				order.setComfirm(2);
			//BigDecimal pr=(new BigDecimal(orderTotalFee)).multiply(dis).setScale(2, BigDecimal.ROUND_HALF_UP);
			//orderTotalFee = pr.longValue();
			order.setTotalFee(orderTotalFee);
			order.setTotalNum(orderTotalNum);
			order.setTotalCost(orderTotalCost);
			order.setNotaxInclusiveTotalCost(notaxInclusiveTotalCost);
			PersonUser sbuser=(PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			order.setZhidan(sbuser.getUserName());
			if(null!=personUser.getIswxvip()&&1==personUser.getIswxvip()){
				PersonUser personuser=(PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
				orderService.updateOrderAndItemsAndMoney(order,personuser.getId());
				this.saveLog(req.getSession(),order, "微信会员后台修改订单，orderNo:"+order.getOrderNo(),pUser.getCityId());
			}else if(null!=personUser.getIswxvip()&&2==personUser.getIswxvip()){
				return "该用户未激活,请联系充值后下单!";
			}else{
				orderService.updateOrderAndItems(order);
				this.saveLog(req.getSession(),order, "修改订单，orderNo:"+order.getOrderNo(),pUser.getCityId());
			}
			return "保存成功,"+order.getOrderNo();
		} catch (Throwable e) {
			logger.error("编辑订单失败", e);
			return e.getMessage();
		}

	}
	

	public Map<Integer, Integer> getChangedOrderItemList(List<OrderItem> oriOrderItemList,
			List<OrderItem> newOrderItemList) {
		Map<Integer, Integer> changedItems = new HashMap<Integer, Integer>();

		// 合并订单中相同名称的商品数量
		oriOrderItemList = combineOrderItemList(oriOrderItemList);
		newOrderItemList = combineOrderItemList(newOrderItemList);

		if (CollectionUtils.isNotEmpty(oriOrderItemList) && CollectionUtils.isNotEmpty(newOrderItemList)) {
			// 先找出：1. 老订单与新订单都包括的商品的变化量  2.老订单中有而新订单中没有的商品的变化量
			for (OrderItem oldOi : oriOrderItemList) {
				OrderItem newOi = getOrderItemFromListById(newOrderItemList, oldOi.getItemId());
				int oldItemNum = oldOi.getNum() == null ? 0 : oldOi.getNum().intValue();
				if (newOi == null) {
					changedItems.put(oldOi.getItemId(), oldItemNum);
				} else {
					int newItemNum = newOi.getNum() == null ? 0 : newOi.getNum().intValue();
					changedItems.put(oldOi.getItemId(), oldItemNum - newItemNum);
				}
			}
			// 再找出：1.新订单中有而老订单中没有的商品的变化量
			for (OrderItem newOi : newOrderItemList) {
				if (getOrderItemFromListById(oriOrderItemList, newOi.getItemId()) == null) {
					int newItemNum = newOi.getNum() == null ? 0 : newOi.getNum().intValue();
					changedItems.put(newOi.getItemId(), newItemNum * -1);
				}
			}
		} else if (CollectionUtils.isEmpty(oriOrderItemList) && CollectionUtils.isNotEmpty(newOrderItemList)) {
			for (OrderItem newOi : newOrderItemList) {
				int newItemNum = newOi.getNum() == null ? 0 : newOi.getNum().intValue();
				changedItems.put(newOi.getItemId(), newItemNum * -1);
			}
		}  else if (CollectionUtils.isNotEmpty(oriOrderItemList) && CollectionUtils.isEmpty(newOrderItemList)) {
			for (OrderItem oldOi : oriOrderItemList) {
				int oldItemNum = oldOi.getNum() == null ? 0 : oldOi.getNum().intValue();
				changedItems.put(oldOi.getItemId(), oldItemNum);
			}
		}

		return changedItems;
	}

	private List<OrderItem> combineOrderItemList(List<OrderItem> orderItemList) {
		if (CollectionUtils.isEmpty(orderItemList)) {
			return orderItemList;
		} else {
			List<OrderItem> orderItemListCombined = new ArrayList<OrderItem>();
			for (OrderItem oi : orderItemList) {
				OrderItem cOi = getOrderItemFromListById(orderItemListCombined, oi.getItemId());
				if ( cOi== null) {
					orderItemListCombined.add(oi);
				} else {
					cOi.setNum(cOi.getNum() + oi.getNum());
					logger.debug("订单中有相同的商品！");
				}
			}

			return orderItemListCombined;
		}
	}

	private OrderItem getOrderItemFromListById(List<OrderItem> orderItemList, Integer itemId) {
		if (orderItemList == null) {
			return null;
		} else {
			OrderItem result = null;
			for (OrderItem oi : orderItemList) {
				if (itemId.intValue() == oi.getItemId().intValue()) {
					result = oi;
					break;
				}
			}

			return result;
		}
	}

	@RequestMapping("addOrderAuto.htm")
	public ModelAndView addOrderAutoPage(HttpServletRequest request,
			String returnedPurchase) {
		ModelAndView mv = new ModelAndView("order/addAuto");


		mv.addObject("executedTime", new Date());

		this.fillCommonData(mv, 0);
		TestController.getMenuPoint(mv, request);

		return mv;
	}

	@RequestMapping("addOrder.htm")
	public ModelAndView addOrderPage(HttpServletRequest request,
			String returnedPurchase) {
		ModelAndView mv = new ModelAndView("order/add");

		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		mv.addObject("executedTime", new Date());

		this.fillCommonData(mv, personUser.getCityId());

		TestController.getMenuPoint(mv, request);
		return mv;
	}
	

	private void fillCommonData(ModelAndView view,int cityId){
		List<ItemCategory> catList =null;
		catList= itemCategoryService.findAllOneCatsByCityId(cityId);
		view.addObject("catList", catList);

		int cateId1=0;
		if(CollectionUtils.isNotEmpty(catList)){
			cateId1  = catList.get(0).getId();
		}

		view.addObject("cateId1", cateId1);

		List<Item> itemList = itemService.findAllWithStockByCityId(cityId);
		view.addObject("itemList", itemList);

	}
	
	private void fillCommonData2(ModelAndView view,int cityId,int cid){
		List<ItemCategory> catList =null;
		catList= itemCategoryService.findAllOneCatsByCityId(cityId);
		view.addObject("catList", catList);

		int cateId1=0;
		if(CollectionUtils.isNotEmpty(catList)){
			cateId1  = catList.get(0).getId();
		}

		view.addObject("cateId1", cateId1);
		List<HashMap<String, Object>> itemList = itemService.findAllWithStockByCatIdAndCityId(null,cityId);
		ArrayList<Integer> ids = this.orderService.findLastTwoOrderItemByCid(cid);
		ArrayList<Integer> ids2 = this.shopBlackListService.findAllByShopId(cid);
		ArrayList<Integer> ids3 = this.itemService.findNewItem();
	    ArrayList<Integer> ids4 = this.itemService.findRecommend();
	    ArrayList<Integer> ids5 = this.itemService.findBang();
	    ArrayList<Integer> ids6 = this.itemService.findFreeSpecialSupply();
		for (Integer id : ids2) {
			for (HashMap<String, Object> map : itemList) {
				if(Integer.valueOf(map.get("id").toString()).equals(id)){
					map.put("itemName", "xxxxxxxxxx"+map.get("itemName").toString());
					break;
				}
			}
		}
		for (Integer id : ids5) {
			for (HashMap<String, Object> map : itemList) {
				if(Integer.valueOf(map.get("id").toString()).equals(id)){
					map.put("itemName", "$ "+map.get("itemName").toString());
					break;
				}
			}
		}
		for (Integer id : ids4) {
			for (HashMap<String, Object> map : itemList) {
				if(Integer.valueOf(map.get("id").toString()).equals(id)){
					map.put("itemName", "! "+map.get("itemName").toString());
					break;
				}
			}
		}
		for (Integer id : ids3) {
			for (HashMap<String, Object> map : itemList) {
				if(Integer.valueOf(map.get("id").toString()).equals(id)){
					map.put("itemName", "+ "+map.get("itemName").toString());
					break;
				}
			}
		}
		for (Integer id : ids) {
			for (HashMap<String, Object> map : itemList) {
				if(Integer.valueOf(map.get("id").toString()).equals(id)){
					map.put("itemName", "# "+map.get("itemName").toString());
					break;
				}
			}
		}
		for (Integer id : ids6) {
			for (HashMap<String, Object> map : itemList) {
				if(Integer.valueOf(map.get("id").toString()).equals(id)){
					map.put("itemName", "~ "+map.get("itemName").toString());
					break;
				}
			}
		}
		view.addObject("itemList", itemList);
		List<AfterSalesRecord> afterSalesRecords = this.afterSalesRecordService.findByUserIdNotCompelete(cid);
		view.addObject("records", afterSalesRecords);
	}

	@RequestMapping(value = "/addOrder.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest req) {
		try {
			PersonUser pUser = (PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			String buyerId = req.getParameter("buyerId");
			if (StringUtils.isEmpty(buyerId)) {
				return "用户ID不能为空!";
			}
			CustomerUser personUser = customerService.findById(Integer.parseInt(buyerId));
			Order order = new Order();
			order.setCityId(pUser.getCityId());
			order.setMemo(req.getParameter("memo"));
			String discount = req.getParameter("discount");
			if(StringUtils.isEmpty(discount))
			{
				return "折扣不能不空!";
			}
			String type = req.getParameter("type");
			if (StringUtils.isEmpty(type)) {
				return "请选择是否上架";
			}
			BigDecimal dis=new BigDecimal(discount);
			order.setDiscount(dis);
			order.setAddress(personUser.getAddress());
			order.setCreatedTime(new Date());
			order.setOrderStatus(OrderStatusEnum.PAY.getId());
			order.setStatus(Constant.VALID_STATUS);
			order.setUserId(personUser.getId());
			order.setBusinessId(personUser.getBusinessId());
			order.setType(Integer.parseInt(type));
			order.setOrderNo(OrderNumberGenerator.buildOrderNo(order.getCreatedTime(), order.getUserId()));
			int sign = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("sign"), "0"));
			order.setSign(sign);
//			String fromCopy = req.getParameter("fromCopy");
//			if (fromCopy != null) {
//				order.setComfirm(0);
//			} else {
				order.setComfirm(2);
//			}
			String executedTimeStr = req.getParameter("executedTime");
			if (StringUtils.isEmpty(executedTimeStr)) {
				order.setExecutedTime(new Date());
			} else {
				order.setExecutedTime(DateUtil.parseDateStr(executedTimeStr, DATE_FORMAT_YMD));
			}
			AccountLock accountLock=new AccountLock();
			Date exce=order.getExecutedTime();
            Calendar calendar = Calendar.getInstance();
			calendar.setTime(exce);
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH)+1;
            accountLock.setYears(year+"");
            accountLock.setMonths(month+"");
            accountLock.setCityId(pUser.getCityId());
            int lock=accountLockService.findLockByCityId(accountLock);
			if(lock==1)
			{
				return "执行时间不能设置到已锁帐月份!";
			}
			List<OrderItem> orderItemList = new ArrayList<OrderItem>();
			int rowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("rowCount"), "0"));
			//判断便利店用户下单规格
				for (int i = 0; i < rowCount; i++) {
					String specType = StringUtils.defaultString(req.getParameter("specification" + i), "");
					int itemId = Integer.parseInt(StringUtils.defaultString(
							req.getParameter("itemId" + i), "0"));
					if (itemId == 0) {
						continue;
					}
					int num = Integer.parseInt(StringUtils.defaultString(
							req.getParameter("purchaseNum" + i), "0"));
					try {
						OrderItem oi = new OrderItem();
						oi.setOrderNo(order.getOrderNo());
						oi.setItemId(itemId);
						oi.setNum(num);
						if(personUser.getIswxvip()==1){
							try {
								double par = Double.parseDouble(req.getParameter("price" + i));
								Double a = par*100;
								long price = Math.round(a);
								oi.setFee(price*num);
								oi.setItemPrice(price);
							} catch (Exception e) {
								e.printStackTrace();
								return "请填写正确的价格";
							}
							
						}
						oi.setItemSizeType(specType);
						orderItemList.add(oi);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			Integer orderTotalNum = 0;
			Long orderTotalFee = 0l;
			Long orderTotalCost = 0l;
			Long notaxInclusiveTotalCost = 0l;
			order.setOrderItemList(orderItemList);
			for (OrderItem oi : orderItemList) {
				if(oi.getNum()==null||oi.getNum()==0){
					return "商品 :" + oi.getItemId() + ",数量为0";
				}
				Item item = this.itemService.findById(oi.getItemId());
				if (item == null) {
					return "商品 :" + oi.getItemId() + ",无法找到商品数据";
				}
				if(personUser.getIswxvip()==3){
					if(null==item.getImgPath()||StringUtils.isEmpty(item.getImgPath())){
						return "商品 :" + oi.getItemId() + ",图片不符合";
					}
					
					String itemSizeType = oi.getItemSizeType();	// 规格
					long price = 0l; //单价
					long costPrice = 0l; // 成本价
					long notaxInclusiveCostPrice = 0l; // 成本价
					String itemSize = null;
					if (itemSizeType.equals("SIZE")) { //普通规格
						itemSize = item.getSize();
						price = item.getPrice();
						costPrice = item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
					} else if (itemSizeType.equals("SALE_SIZE")) { // 零售
						itemSize = item.getSize();
						price = item.getPrice();
						costPrice = item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
						oi.setNum(oi.getNum()*item.getSaleSizeNum());
						oi.setItemSizeType("SIZE");
					} else if (itemSizeType.equals("BUY_SIZE")) { //批发
						itemSize = item.getSize();
						price = item.getPrice();
						costPrice = item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
						oi.setNum(oi.getNum()*item.getConvertNum());
						oi.setItemSizeType("SIZE");
					} else {
						return "商品 :" + oi.getItemName() + ",无法找到规格数据";
					}
					//BigDecimal pr=(new BigDecimal(price)).multiply(dis).setScale(2, BigDecimal.ROUND_HALF_UP);
					//price=pr.longValue();
					oi.setFee(price * oi.getNum());
					oi.setItemName(item.getItemName());
					oi.setItemPrice(price);
					oi.setItemCostPrice(costPrice);
					oi.setNotaxInclusiveCostPrice(notaxInclusiveCostPrice);
					oi.setItemSize(itemSize);
					orderTotalNum += oi.getNum();
					orderTotalFee += oi.getFee();
					orderTotalCost += costPrice*oi.getNum();
					notaxInclusiveTotalCost += notaxInclusiveCostPrice*oi.getNum();
				}else if (personUser.getIswxvip()==1) {
					String itemSizeType = oi.getItemSizeType();	// 规格
					long price = 0l; //单价
					long costPrice = 0l; // 成本价
					long notaxInclusiveCostPrice = 0l;
					String itemSize = null;
					if ("SIZE".equals(itemSizeType)) { //普通规格
						itemSize = item.getSize();
						price = (item.getPrice()==null)?0:item.getPrice();
						costPrice = (item.getCostPrice()==null)?0:item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
					} else if ("SALE_SIZE".equals(itemSizeType)) { // 零售
//						itemSize = item.getSaleSize();
//						price = (item.getSalePrice()==null)?0:item.getSalePrice();
//						costPrice = (item.getSaleCostPrice()==null)?0:item.getSaleCostPrice();
//						notaxInclusiveCostPrice = item.getNotaxInclusiveSaleCostPrice();
						itemSize = item.getSize();
						price = item.getPrice();
						costPrice = item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
						oi.setNum(oi.getNum()*item.getSaleSizeNum());
						oi.setItemSizeType("SIZE");
					} else if ("BUY_SIZE".equals(itemSizeType)) { //批发
						itemSize = item.getBuySize();
						price = (item.getPurchasePrice()==null)?0:item.getPurchasePrice();
						costPrice = (item.getBuyPrice()==null)?0:item.getBuyPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveBuyPrice();
					} else {
						return "无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSizeType;
					}
					//BigDecimal pr=(new BigDecimal(price)).multiply(dis).setScale(2, BigDecimal.ROUND_HALF_UP);
					//price=pr.longValue();
					oi.setItemName(item.getItemName());
					oi.setItemCostPrice(costPrice);
					oi.setNotaxInclusiveCostPrice(notaxInclusiveCostPrice);
					oi.setItemSize(itemSize);
					
					orderTotalNum += oi.getNum();
					orderTotalFee += oi.getFee();
					orderTotalCost += costPrice*oi.getNum();
					notaxInclusiveTotalCost += notaxInclusiveCostPrice*oi.getNum();
				}else{
					String itemSizeType = oi.getItemSizeType();	// 规格
					long price = 0l; //单价
					long costPrice = 0l; // 成本价
					long notaxInclusiveCostPrice = 0l; // 成本价
					String itemSize = null;
					if (itemSizeType.equals("SIZE")) { //普通规格
						itemSize = item.getSize();
						price = item.getPrice();
						costPrice = item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
					} else if (itemSizeType.equals("SALE_SIZE")) { // 零售
//						itemSize = item.getSaleSize();
//						price = item.getSalePrice();
//						costPrice = item.getSaleCostPrice();
//						notaxInclusiveCostPrice = item.getNotaxInclusiveSaleCostPrice();
						itemSize = item.getSize();
						price = item.getPrice();
						costPrice = item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
						oi.setNum(oi.getNum()*item.getSaleSizeNum());
						oi.setItemSizeType("SIZE");
					} else if (itemSizeType.equals("BUY_SIZE")) { //批发
						itemSize = item.getBuySize();
						price = item.getPurchasePrice();
						costPrice = item.getBuyPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveBuyPrice();
					} else {
						return "商品 :" + oi.getItemName() + ",无法找到规格数据";
					}
					//BigDecimal pr=(new BigDecimal(price)).multiply(dis).setScale(2, BigDecimal.ROUND_HALF_UP);
					//price=pr.longValue();
					oi.setFee(price * oi.getNum());
					oi.setItemName(item.getItemName());
					oi.setItemPrice(price);
					oi.setItemCostPrice(costPrice);
					oi.setNotaxInclusiveCostPrice(notaxInclusiveCostPrice);
					oi.setItemSize(itemSize);
					orderTotalNum += oi.getNum();
					orderTotalFee += oi.getFee();
					orderTotalCost += costPrice*oi.getNum();
					notaxInclusiveTotalCost += notaxInclusiveCostPrice*oi.getNum();
				}
			}
			if(orderTotalNum<=0){
				return "订单中商品数量为0";
			}
			//BigDecimal pr=(new BigDecimal(orderTotalFee)).multiply(dis).setScale(2, BigDecimal.ROUND_HALF_UP);
			//orderTotalFee = pr.longValue();
			order.setTotalFee(orderTotalFee);
			order.setTotalNum(orderTotalNum);
			order.setTotalCost(orderTotalCost);
			order.setNotaxInclusiveTotalCost(notaxInclusiveTotalCost);
			PersonUser sbuser=(PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			order.setZhidan(sbuser.getUserName());
			if(null!=personUser.getIswxvip()&&1==personUser.getIswxvip()){
				PersonUser user=(PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
				WXAccount wxAccount = this.wxAccountService.findVipBycid(order.getUserId());
				//if(null!=wxAccount && wxAccount.getMoney() > 0 ){
					orderService.createOrderAndDeductMoney(order,user.getId());
					this.saveLog(req.getSession(),order, "微信会员添加订单并扣款，orderNo:"+order.getOrderNo()+"money:"+order.getTotalFee(),pUser.getCityId());
				//}else{
				//	return "余额不足!";
				//}
			}else if(null!=personUser.getIswxvip()&&2==personUser.getIswxvip()){
				return "该用户未激活,请联系充值后下单!";
			}else{
//				if (fromCopy != null) {
//					orderService.copyOrder(order);
//				} else {
					orderService.createOrder(order);
//				}
				this.saveLog(req.getSession(),order, "添加订单，orderNo:"+order.getOrderNo(),pUser.getCityId());
			}
			return "添加成功";

		} catch (Throwable e) {
			logger.error("添加订单失败", e);
			return e.getMessage();
		}
	}
	
	@RequestMapping(value="/comfirm.do",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String comfirm(HttpServletRequest req,@RequestParam("orderNo")String orderNo){
		try {
			PersonUser sbuser=(PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			if(null == sbuser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			Order order = this.orderService.findOrderById(orderNo);
			if(!sbuser.getCityId().equals(order.getCityId())){
				return "你设置的城市与实际不符";
			}
			this.orderService.comfirmOrderAndReduceStock(orderNo,null,sbuser.getUserName());
			WholeToken wholeToken = this.wholeTokenService.findByIdOneHour(1);
			if(order.getSourcingId()!=null){
				if(null!=wholeToken){
					NotifyUtil.sourcingPass(properties.PASS_TEMP_ID, order.getSourcingId(), orderNo,NumberTool.toYuanNumber(order.getTotalFee()),DateUtil.formatDate(order.getCreatedTime(),"yyyy-MM-dd HH:mm:ss") , DateUtil.formatDate(order.getExecutedTime(), "yyyy-MM-dd"), wholeToken.getAccessToken());
				}else{
					Token token = CommonUtil.getToken(properties.weixinAppid, properties.weixinAppsecret);
					WholeToken t = this.wholeTokenService.findById(1);
					if(null==t){
						WholeToken token2 = new WholeToken();
						token2.setId(1);
						token2.setAccessToken(token.getAccessToken());
						token2.setCreatedTime(new Date());
						this.wholeTokenService.insert(token2);
					}else{
						t.setAccessToken(token.getAccessToken());
						t.setCreatedTime(new Date());
						this.wholeTokenService.update(t);
					}
					NotifyUtil.sourcingPass(properties.PASS_TEMP_ID, order.getSourcingId(), orderNo, NumberTool.toYuanNumber(order.getTotalFee()),DateUtil.formatDate(order.getCreatedTime(),"yyyy-MM-dd HH:mm:ss") , DateUtil.formatDate(order.getExecutedTime(), "yyyy-MM-dd"), token.getAccessToken());
				}
			}
			return "success";
		} catch (Throwable e) {
			logger.error("添加订单失败", e);
			return e.getMessage();
		}
	}
	
	
	//待确认订单编辑
	@RequestMapping(value="editComfirmOrder.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String editComfirmOrder( HttpServletRequest req) {
		try{
			PersonUser pUser = (PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			String buyerId = req.getParameter("buyerId");
			if (StringUtils.isEmpty(buyerId)) {
				return "用户ID不能为空!";
			}
			String discount = req.getParameter("discount");
			if(StringUtils.isEmpty(discount))
			{
				return "折扣不能不空!";
			}
			String type = req.getParameter("type");
			if(StringUtils.isEmpty(type))
			{
				return "请选择是否上架!";
			}
			BigDecimal dis=new BigDecimal(discount);
			CustomerUser personUser = customerService.findById(Integer.parseInt(buyerId));

			Order order = new Order();
			order.setMemo(req.getParameter("memo"));
			order.setAddress(personUser.getAddress());
			order.setDiscount(dis);
			order.setUserId(personUser.getId());
			order.setBusinessId(personUser.getBusinessId());
			order.setOrderNo(req.getParameter("orderNo"));
			order.setType(Integer.parseInt(type));
			String executedTimeStr = req.getParameter("executedTime");
			if (StringUtils.isEmpty(executedTimeStr)) {
				order.setExecutedTime(new Date());
			} else {
				order.setExecutedTime(DateUtil.parseDateStr(executedTimeStr, DATE_FORMAT_YMD));
			}
			AccountLock accountLock=new AccountLock();
			Date exce=order.getExecutedTime();
            Calendar calendar = Calendar.getInstance();
			calendar.setTime(exce);
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH)+1;
            accountLock.setYears(year+"");
            accountLock.setMonths(month+"");
            accountLock.setCityId(pUser.getCityId());
            int lock=accountLockService.findLockByCityId(accountLock);
			if(lock==1)
			{
				return "执行时间不能设置到已锁帐月份!";
			}

			List<OrderItem> orderItemList = new ArrayList<OrderItem>();

			int rowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("rowCount"), "0"));
			
			//判断便利店用户下单规格
			
				for (int i = 0; i < rowCount; i++) {
					String specType = StringUtils.defaultString(req.getParameter("specification" + i), "");
					int itemId = Integer.parseInt(StringUtils.defaultString(
							req.getParameter("itemId" + i), "0"));
					if (itemId == 0) {
						continue;
					}
					int num = Integer.parseInt(StringUtils.defaultString(
							req.getParameter("purchaseNum" + i), "0"));
					try {
						OrderItem oi = new OrderItem();
						oi.setOrderNo(order.getOrderNo());
						oi.setItemId(itemId);
						oi.setNum(num);
						if(StringUtils.isEmpty(specType)){
							logger.warn("itemid :" + itemId + ",order no :" + order.getOrderNo() + " not found specType");
						}
						oi.setItemSizeType(specType);
						orderItemList.add(oi);
					} catch (Exception e) {
						logger.error(e.getMessage(), e);
					}
				}
			
			
			

			Integer orderTotalNum = 0;
			Long orderTotalFee = 0l;
			Long orderTotalCost = 0l;
			Long notaxInclusiveTotalCost = 0l;
			order.setOrderItemList(orderItemList);

			for (OrderItem oi : orderItemList) {
				if(oi.getNum()==null||oi.getNum()==0){
					return "商品id :" + oi.getItemId() + ",数量为0";
				}
				Item item = this.itemService.findById(oi.getItemId());
				if (item == null) {
					return "商品id :" + oi.getItemId() + ",无法找到商品数据";
				}
				if(personUser.getIswxvip()==3){
					if(null==item.getImgPath()||StringUtils.isEmpty(item.getImgPath())){
						return "商品 :" + oi.getItemName() + ",图片不符合";
					}
					String itemSizeType = oi.getItemSizeType();	// 规格
					long price = 0l; //单价
					long costPrice = 0l; // 成本价
					long notaxInclusiveCostPrice = 0l;
					String itemSize = null;
					if (itemSizeType.equals("SIZE")) { //普通规格
						itemSize = item.getSize();
						price = item.getPrice();
						costPrice = item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
					} else if (itemSizeType.equals("SALE_SIZE")) { // 零售
						itemSize = item.getSize();
						price = item.getPrice();
						costPrice = item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
						oi.setNum(oi.getNum()*item.getSaleSizeNum());
						oi.setItemSizeType("SIZE");
					} else if (itemSizeType.equals("BUY_SIZE")) { //批发
						itemSize = item.getSize();
						price = item.getPrice();
						costPrice = item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
						oi.setNum(oi.getNum()*item.getConvertNum());
						oi.setItemSizeType("SIZE");
					} else {
						return "商品 :" + oi.getItemName() + ",无法找到规格数据";
					}
					//BigDecimal pr=(new BigDecimal(price)).multiply(dis).setScale(2, BigDecimal.ROUND_HALF_UP);
					//price=pr.longValue();
					oi.setFee(price * oi.getNum());
					oi.setItemName(item.getItemName());
					oi.setItemPrice(price);
					oi.setItemCostPrice(costPrice);
					oi.setNotaxInclusiveCostPrice(notaxInclusiveCostPrice);
					oi.setItemSize(itemSize);

					orderTotalNum += oi.getNum();
					orderTotalFee += oi.getFee();
					orderTotalCost += costPrice*oi.getNum();
					notaxInclusiveTotalCost += notaxInclusiveCostPrice*oi.getNum();
				}else{
					String itemSizeType = oi.getItemSizeType();	// 规格
					long price = 0l; //单价
					long costPrice = 0l; // 成本价
					long notaxInclusiveCostPrice = 0l;
					String itemSize = null;
					if ("SIZE".equals(itemSizeType)) { //普通规格
						itemSize = item.getSize();
						price = (item.getPrice()==null)?0:item.getPrice();
						costPrice = (item.getCostPrice()==null)?0:item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
					} else if ("SALE_SIZE".equals(itemSizeType)) { // 零售
						itemSize = item.getSize();
						price = item.getPrice();
						costPrice = item.getCostPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
						oi.setNum(oi.getNum()*item.getSaleSizeNum());
						oi.setItemSizeType("SIZE");
					} else if ("BUY_SIZE".equals(itemSizeType)) { //批发
						itemSize = item.getBuySize();
						price = (item.getPurchasePrice()==null)?0:item.getPurchasePrice();
						costPrice = (item.getBuyPrice()==null)?0:item.getBuyPrice();
						notaxInclusiveCostPrice = item.getNotaxInclusiveBuyPrice();
					} else {
						return "无效的规格信息,itemId:"+item.getId()+",商品:"+item.getItemName()+",规格："+itemSizeType;
					}
					//BigDecimal pr=(new BigDecimal(price)).multiply(dis).setScale(2, BigDecimal.ROUND_HALF_UP);
					//price=pr.longValue();
					oi.setFee(price * oi.getNum());
					oi.setItemName(item.getItemName());
					oi.setItemPrice(price);
					oi.setItemCostPrice(costPrice);
					oi.setNotaxInclusiveCostPrice(notaxInclusiveCostPrice);
					oi.setItemSize(itemSize);
					
					orderTotalNum += oi.getNum();
					orderTotalFee += oi.getFee();
					orderTotalCost += costPrice*oi.getNum();
					notaxInclusiveTotalCost += notaxInclusiveCostPrice*oi.getNum();
				}
			}

			if(orderTotalNum<=0){
				return "订单中商品数量为0";
			}
			int sign = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("sign"), "0"));
			order.setSign(sign);
			order.setComfirm(0);
			//BigDecimal pr=(new BigDecimal(orderTotalFee)).multiply(dis).setScale(2, BigDecimal.ROUND_HALF_UP);
			//orderTotalFee = pr.longValue();
			order.setTotalFee(orderTotalFee);
			order.setTotalNum(orderTotalNum);
			order.setTotalCost(orderTotalCost);
			order.setNotaxInclusiveTotalCost(notaxInclusiveTotalCost);
		    orderService.updateOrderAndItemsNoReduceStock(order);
			this.saveLog(req.getSession(),order, "修改待确认订单，orderNo:"+order.getOrderNo(),pUser.getCityId());
			return "保存成功,"+order.getOrderNo();
		} catch (Throwable e) {
			logger.error("编辑订单失败", e);
			return e.getMessage();
		}

	}
	
	private void saveLog(HttpSession session,Order dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setCityId(cityId);
	       sysLog.setDataType(LogDataTypeEnum.ORDER.getName());
	       sysLog.setDataId(dto.getOrderNo());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<Order>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}

	/**
	 * sample URL: http://localhost:8080/order/generateOrderAutomatic.json?totalFee=100.00&profitRate=12.21&skuNum=24@3,25@4,26@5
	 *
	 * @return
	 */
	@RequestMapping(value = "/generateOrderAutomatic.json", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public String generateOrderAutomatic(@RequestParam String totalFee,
			@RequestParam String profitRate, @RequestParam String skuNum) {

		if (logger.isDebugEnabled()) {
			logger.debug("入参信息, totalFee:" + ", profitRate:" + profitRate
					+ ", skuNum:" + skuNum);
		}
		JsonResult results = new JsonResult();
		//统一入参，元转成分
		BigDecimal totalFeeBigDecimal = new BigDecimal(totalFee);
		Long totalFeeLong = totalFeeBigDecimal.movePointRight(2).longValue();

		try {
			//key: categoryId, value: skuNum
			Map<String, String> skuMap = Maps.newHashMap();
			if (skuNum.contains(",")) {
				for(String skuInfo : skuNum.split(",")) {
					String[] cateAndNum = skuInfo.split("@");
					skuMap.put(cateAndNum[0], cateAndNum[1]);
				}
			} else {
				String[] cateAndNum = skuNum.split("@");
				skuMap.put(cateAndNum[0], cateAndNum[1]);
			}
			List<Item> itemList = itemService.findAllWithStock();

			//1.将所有取到的商品进行利润计算，筛选出超过设置阀值的商品
			List<Item> validProfitRateItems = Lists.newArrayList();
			for (Item it : itemList) {
				//库存检查
				if (it == null || (it != null && (it.getStock() <= 0 || it.getSalePrice() == null || it.getSaleCostPrice() == null)))
					continue;
				//logger.warn("it:" + it);
				Long minusResult = it.getSalePrice() - it.getSaleCostPrice();
				BigDecimal bdSalePrice = new BigDecimal(it.getSalePrice());
				BigDecimal bdProfit = new BigDecimal(minusResult);
				BigDecimal bdProfitRate = bdProfit.divide(bdSalePrice, 4, BigDecimal.ROUND_HALF_UP).movePointRight(2);

				if (bdProfitRate.compareTo(new BigDecimal(profitRate)) >= 0) {
					validProfitRateItems.add(it);
				}
			}
			if (CollectionUtils.isEmpty(validProfitRateItems)) {
				results.setMsg("按照利润比，找不到有效的商品");
				return GsonUtil.jsonResult2String(results);
			}
			//乱序
			Collections.shuffle(validProfitRateItems);

			//2.根据类目筛选所有能选择的商品(满足条件:1.在入参的skuMap的keySet里面; 2.总数不超过skuMap对应的catId的value)
			Map<String, List<Item>> validItemsMap = Maps.newHashMap();

			for(Item item : validProfitRateItems) {
				String catId = String.valueOf(item.getCategoryId());
				if (!skuMap.keySet().contains(catId)
						|| (CollectionUtils.isNotEmpty(validItemsMap.get(catId))
						&& validItemsMap.get(catId).size() >= Integer.valueOf(skuMap.get(catId)))) {
					continue;
				}
				List<Item> tempItemList = Lists.newArrayList();
				tempItemList.add(item);
				if (CollectionUtils.isNotEmpty(validItemsMap.get(catId))) {
					tempItemList.addAll(validItemsMap.get(catId));
				}
				validItemsMap.put(catId, tempItemList);
			}
			if (MapUtils.isEmpty(validItemsMap)) {
				results.setMsg("根据类目ID, 找不到有效的商品");
				return GsonUtil.jsonResult2String(results);
			}
			if (logger.isDebugEnabled()) {
				logger.debug("经过利润比和价格筛选，有效的结果集为：" + validItemsMap);
			}
			//3.根据sku数量进行首轮(每个商品选1个)求和
			//4.检查第三步的金额总和
			//4.1 如果正好达标，返回结果
			//4.2  如果已经超过设定的订单总额，依次递减检查
			//4.3 如果金额不够设定的订单总额，递归增加
			List<Item> items = Lists.newArrayList();
			Long totalSalePrice = 0L;

			boolean continueFlag = true;
			for (String catIdString : validItemsMap.keySet()) {
				List<Item> validItems = validItemsMap.get(catIdString);
				Map<Integer, Integer> counterMap = Maps.newHashMap();
				for (Item item : validItems) {
					if (MapUtils.isNotEmpty(counterMap) && (counterMap.keySet().contains(item.getId()) && counterMap.get(item.getId()) > 0)) {
						if (counterMap.get(item.getId()) >= item.getStock()) {
							continue;
						}
						counterMap.put(item.getId(), counterMap.get(item.getId()) + 1);
					} else {
						counterMap.put(item.getId(), 1);
					}

					item.setPurchaseNum(counterMap.get(item.getId()));
					//int remainedSkuNum = Integer.parseInt(skuMap.get(item.getCategoryId())) - item.getPurchaseNum();

					totalSalePrice += item.getSalePrice() * item.getPurchaseNum();
					item.setTotalPurchasePrice(item.getSalePrice() * item.getPurchaseNum());
					item.setActualUsedStock(item.getPurchaseNum() * item.getSaleSizeNum());
					if (totalSalePrice >= totalFeeLong) {
						continueFlag = false;
						break;
					}
					items.add(item);
				}
				if (!continueFlag)
					break;
			}
			//循环一次所有的有效商品，总金额还未达到设定的阀值，采用补充sku数量的方式补全
			if (continueFlag) {
				int breakSwitch = 0;
				do {
					for (Item item : items) {
						if (item.getPurchaseNum() >= item.getStock()) {
							breakSwitch++;
							continue;
						}
						totalSalePrice += item.getSalePrice();
						if (totalSalePrice >= totalFeeLong) {
							totalSalePrice -= item.getSalePrice();
							continueFlag = false;
							break;
						}
						item.setPurchaseNum(item.getPurchaseNum() + 1);
						item.setTotalPurchasePrice(item.getSalePrice() * item.getPurchaseNum());
						item.setActualUsedStock(item.getPurchaseNum() * item.getSaleSizeNum());
						breakSwitch++;
					}
				} while (continueFlag || breakSwitch >= 500);
			}

			results.setTotalFee(totalSalePrice);
			results.setMsg("totalPrice:" + new BigDecimal(totalSalePrice).movePointLeft(2));
			results.setDataList(items);
		    results.setStatus("success");
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

        return GsonUtil.jsonResult2String(results);
	}
	
	
	
	@RequestMapping(value = "exportExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportExcel(HttpServletRequest request,
			HttpServletResponse response) {
		String FilePutPath = "D:\\订单清单.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");
			String userName = request.getParameter("userName");
			String orderNum = request.getParameter("orderNum");
			Integer custmerUserID=null;
			String orderNumStr=null;

			Date startTime = null;
			Date endTime = null;

			if (StringUtils.isNotBlank(startTimeStr)) {
				startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
			}

			if (StringUtils.isNotBlank(endTimeStr)) {
				endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YMD);
			}
			
			if (StringUtils.isNotBlank(orderNum)) {
				orderNumStr = orderNum;
			}
			
			if(StringUtils.isNotEmpty(userName)){
				CustomerUser personUser = customerService.findByUserName(userName);
				if(personUser!=null){
					custmerUserID=personUser.getId();
				}
			}
			
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("订单清单.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			List<HashMap<String, Object>> listOrder = this.orderService.findOrderInfoByDate(startTime,endTime,orderNumStr,custmerUserID);
			if (listOrder != null && !listOrder.isEmpty()) {
				sheet.mergeCells(0, 0, 4, 0);// 合并标题单元格
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, "领蛙订单清单", format1);
				sheet.addCell(label);

				font1 = new WritableFont(WritableFont.TIMES, 11,
						WritableFont.BOLD);
				WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
				// 设置背景颜色;
				cellFormat1.setBackground(Colour.GRAY_50);
				// 设置文字居中对齐方式;
				cellFormat1.setAlignment(jxl.format.Alignment.CENTRE);
				// 设置垂直居中;
				cellFormat1
						.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				sheet.addCell(new Label(0, 1, "订单号", cellFormat1));
				sheet.addCell(new Label(1, 1, "公司名称", cellFormat1));
				sheet.addCell(new Label(2, 1, "价格", cellFormat1));
				sheet.addCell(new Label(3, 1, "执行时间", cellFormat1));

				sheet.setColumnView(0, 35);// 根据内容自动设置列宽
				sheet.setColumnView(1, 40);// 根据内容自动设置列宽
				sheet.setColumnView(2, 15);
				sheet.setColumnView(3, 30);
				for (int i = 0; i < listOrder.size(); i++) {
					sheet.addCell(new Label(0, i + 2, (String) listOrder.get(i).get("orderNo")));
					sheet.addCell(new Label(1, i + 2, (String) listOrder.get(i).get("companyName")));
					Long price=Long.parseLong(listOrder.get(i).get("totalFee").toString());
					sheet.addCell(new Number(2, i + 2,price==null?0:(new BigDecimal(NumberTool.toYuanNumber(price))).doubleValue()));
					sheet.addCell(new Label(3, i + 2, listOrder.get(i).get("executedTime").toString()));
					sheet.addCell(new Label(4, i + 2, listOrder.get(i).get("memo").toString()));
				}
			}
			// 写入数据并关闭文件
			book.write();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (book != null) {
					book.close();
				}
				os.close();
			} catch (Exception e) {
				logger.error("jftj/genexcel WriteException", e);
			}
		}
		return FilePutPath;
	} 
	
	@RequestMapping("showOrder2.json")
	@ResponseBody
	public HashMap<String, Object> editOrderShow2(HttpServletRequest request) {
		HashMap<String, Object> mv = new HashMap<String, Object>();
		try {
			String orderNo = request.getParameter("orderNo");
			//Pair<Order, List<OrderItem>> pair = orderService.findByOrderNo(orderNo);
			//查询结果多加一个isDown字段，用于判断订单列表中下架的商品
			Pair<Order, List<HashMap<String, Object>>> pair = orderService.findInfoByOrderNo(orderNo);
			mv.put("pair", pair);


			mv.put("itemSize",CollectionUtils.size(pair.getRight()));

			List<ItemCategory> catList =null;
			if(pair.getKey().getBusinessId()==0)
			catList= itemCategoryService.findAll();
			else {
				catList= itemCategoryService.findByBusinessId(pair.getKey().getBusinessId());
			}
			mv.put("catList", catList);

			int cateId1=0;
			if(CollectionUtils.isNotEmpty(catList)){
				cateId1  = catList.get(0).getId();
			}

			mv.put("cateId1", cateId1);

			List<Item> itemList = itemService.findAllWithStock();
			mv.put("itemList", itemList);
			return mv;
		} catch (Throwable e) {
			logger.error("编辑订单显示", e);
		}

		return null;
	}
	
	@RequestMapping("gongdan.htm")
	public ModelAndView shabi(HttpServletRequest request,@RequestParam(value="reseauId",defaultValue="-1",required=false)Integer reseauId){
		ModelAndView mv = new ModelAndView("order/worksheet");
		TestController.getMenuPoint(mv, request);
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		PersonUser pUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == pUser.getCityId()){
			return new ModelAndView("noCity");
		}
		String startTimeStr = request.getParameter("startTime");
		String endTimeStr = request.getParameter("endTime");

		Date date = new Date();
		Date startTime = null;
		Date endTime = null;
		if(reseauId==-1){
			reseauId=null;
		}
		String param = request.getParameter("param");
		mv.addObject("param", param);
		if(StringUtils.isEmpty(param)||param.equals("0")){
			endTime = date;
		}

		if (StringUtils.isNotBlank(startTimeStr)) {
			startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
			mv.addObject("startTime", startTimeStr);
		}

		if (StringUtils.isNotBlank(endTimeStr)) {
			endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YMD);
			mv.addObject("endTime", endTimeStr);
		}
		String orderNum = request.getParameter("orderNum");
		String userName = request.getParameter("userName");
		mv.addObject("orderNum", orderNum);
		mv.addObject("userName", userName);
		String region = request.getParameter("region");
		mv.addObject("region", region);
		String zhidan = request.getParameter("zhidan");
		mv.addObject("zhidan", zhidan);
		String fenjian = request.getParameter("fenjian");
		mv.addObject("fenjian", fenjian);
		String peisong = request.getParameter("peisong");
		mv.addObject("peisong", peisong);
		String type = request.getParameter("type");
		mv.addObject("type", type);
		PageHelper.startPage(currentPage, 50);
		List<Order> orders = this.orderService.findgongdan(startTime,endTime,orderNum,userName,region,zhidan,fenjian,peisong,reseauId,pUser.getCityId(),type);
		PageInfo<Order> info = new PageInfo<Order>(orders);
		Page<Order> page = new Page<Order>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		List<PersonUser> kfusers = this.userService.findUserkfFirstByCityId(pUser.getCityId());
		List<PersonUser> cgusers = this.userService.findUsercgFirstByCityId(pUser.getCityId());
		List<PersonUser> psusers = this.userService.findUserpsFirstByCityId(pUser.getCityId());
		Order count= this.orderService.findgongdanCount(startTime,endTime,orderNum,userName,region,zhidan,fenjian,peisong,reseauId,pUser.getCityId(),type);
		List<Reseau> lists = this.reseauService.findAllByCityId(pUser.getCityId());
		mv.addObject("reseaus", lists);
		mv.addObject("reseauId", reseauId);
		mv.addObject("page", page);
		mv.addObject("kfusers", kfusers);
		mv.addObject("cgusers", cgusers);
		mv.addObject("psusers", psusers);
		mv.addObject("totalcount", count);
		return mv;
	}
	
	@RequestMapping("change.do")
	@ResponseBody
	public String zhidan(@RequestParam("orderNo")String orderNo,@RequestParam("name")String name,@RequestParam("flag")Integer flag){
		try {
			Order order = this.orderService.findOrderById(orderNo);
			if(flag==1){
				order.setZhidan(name);
			}else if (flag==2) {
				order.setFenjian(name);
			}else if (flag==3) {
				order.setPeisong(name);
			}
			this.orderService.updateOrder(order);
			return "200";
		} catch (Exception e) {
			logger.error("变更配送制单分拣人员错误", e);
		}
		return "201";
	}
	
	@RequestMapping(value = "export.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportModelExcel(HttpServletRequest request,
			HttpServletResponse response) {
		String FilePutPath = "D:\\订单.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			String orderNum = request.getParameter("orderNum");

			
			
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("订单.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			Pair<Order,List<OrderItem>> pair = this.orderService.findByOrderNo(orderNum);
			
			if (pair != null && pair.getLeft() != null && !pair.getRight().isEmpty()) {
				Order order = pair.getLeft();
				CustomerUser user = customerService.findPrintOrdersInfo(order.getUserId());
				List<OrderItem> items = pair.getRight();
				sheet.mergeCells(0, 0, 7, 0);// 合并标题单元格
				sheet.setRowView(0, 800, false);
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				WritableCellFormat format2 = new WritableCellFormat();
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				format2.setWrap(true);
				Label label = new Label(0, 0, "领蛙自助便利店配送单", format1);
				sheet.addCell(label);

				font1 = new WritableFont(WritableFont.TIMES, 11,
						WritableFont.BOLD);
				WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
				// 设置背景颜色;
				cellFormat1.setBackground(Colour.GRAY_50);
				// 设置文字居中对齐方式;
				cellFormat1.setAlignment(jxl.format.Alignment.CENTRE);
				// 设置垂直居中;
				cellFormat1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				sheet.mergeCells(0, 1, 3, 1);
				sheet.mergeCells(4, 1, 7, 1);
				sheet.addCell(new Label(0, 1, "出库单号："+order.getOrderNo()));
				sheet.addCell(new Label(4, 1, "客户店名:"+order.getUserName()+"，要求到货日期："+DateUtil.formatDate(order.getExecutedTime(), "yyyy-MM-dd"),format2));
				sheet.mergeCells(0, 2, 7, 2);
				sheet.addCell(new Label(0, 2, "送货地址："+order.getAddress()+"（收货人："+user.getLikeman()+user.getMobilePhone()+")"));
				
				sheet.addCell(new Label(0, 3, "序号", cellFormat1));
				sheet.addCell(new Label(1, 3, "编码", cellFormat1));
				sheet.addCell(new Label(2, 3, "储位", cellFormat1));
				sheet.addCell(new Label(3, 3, "品名", cellFormat1));
				sheet.addCell(new Label(4, 3, "订货数量", cellFormat1));
				sheet.addCell(new Label(5, 3, "规格", cellFormat1));
				sheet.addCell(new Label(6, 3, "单价", cellFormat1));
				sheet.addCell(new Label(7, 3, "金额", cellFormat1));

				sheet.setColumnView(0, 5);// 根据内容自动设置列宽
				sheet.setColumnView(1, 5);// 根据内容自动设置列宽
				sheet.setColumnView(2, 10);
				sheet.setColumnView(3, 30);
				sheet.setColumnView(4, 12);// 根据内容自动设置列宽
				sheet.setColumnView(5, 14);// 根据内容自动设置列宽
				sheet.setColumnView(6, 12);
				sheet.setColumnView(7, 12);
				for (int i = 0; i < items.size(); i++) {
					sheet.addCell(new Number(0, i + 4,i + 1));
					sheet.addCell(new Label(1, i + 4,items.get(i).getItemId().toString()));
					sheet.addCell(new Label(2, i + 4,items.get(i).getPosition()));
					sheet.addCell(new Label(3, i + 4,items.get(i).getItemName()));
					sheet.addCell(new Number(4, i + 4,items.get(i).getNum()));
					sheet.addCell(new Label(5, i + 4,items.get(i).getItemSize()));
					sheet.addCell(new Number(6, i + 4,new BigDecimal(NumberTool.toYuanNumber(items.get(i).getItemPrice())).doubleValue()));
					sheet.addCell(new Number(7, i + 4,new BigDecimal(NumberTool.toYuanNumber(items.get(i).getFee())).doubleValue()));
				}
				int a = items.size()+4;
				sheet.mergeCells(0, a, 3, a);
				sheet.addCell(new Label(1, a,"合计"));
				sheet.addCell(new Number(4, a,order.getTotalNum()));
				sheet.addCell(new Number(7, a,new BigDecimal(NumberTool.toYuanNumber(order.getTotalFee())).doubleValue()));
				sheet.mergeCells(0, a+1, 3, a+1);
				sheet.mergeCells(4, a+1, 7, a+1);
				sheet.addCell(new Label(0, a+1,"备注："+order.getMemo()));
				sheet.addCell(new Label(4, a+1,"客服电话：15345818517"));
				sheet.mergeCells(0, a+2, 7, a+2);
				sheet.addCell(new Label(0, a+2,"备注： 签收货品前请清点品名、数量，并检查外包装是否完好，一旦签字，视为正常收货。"));
				sheet.mergeCells(0, a+3, 7, a+3);
				sheet.addCell(new Label(0, a+3,"如有物流问题可致电 021-39570059,13764952414"));
				sheet.mergeCells(0, a+4, 3, a+4);
				sheet.mergeCells(4, a+4, 7, a+4);
				sheet.setRowView(a+4, 800, false);
				sheet.addCell(new Label(0, a+4,"实收数量："));
				sheet.addCell(new Label(4, a+4,"客户负责人签名："));
			}
			// 写入数据并关闭文件
			book.write();
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (book != null) {
					book.close();
				}
				os.close();
			} catch (Exception e) {
				logger.error("jftj/genexcel WriteException", e);
			}
		}
		return FilePutPath;
	}

	@RequestMapping("orderCopy.htm")
	public ModelAndView copyOrderShow(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("order/orderCopy");
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		try {
			String orderNo = request.getParameter("orderNo");
			//Pair<Order, List<OrderItem>> pair = orderService.findByOrderNo(orderNo);
			//查询结果多加一个isDown字段，用于判断订单列表中下架的商品
			Pair<Order, List<HashMap<String, Object>>> pair = orderService.findInfoByOrderNo(orderNo);

			mv.addObject("pair", pair);
			mv.addObject("executedTime", new Date());


			mv.addObject("itemSize",CollectionUtils.size(pair.getRight()));

			this.fillCommonData2(mv, user.getCityId(),pair.getKey().getUserId());
		} catch (Throwable e) {
			logger.error("编辑订单显示", e);
		}
		TestController.getMenuPoint(mv, request);
		return mv;
	}
}
