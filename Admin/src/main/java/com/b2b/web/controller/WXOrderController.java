package com.b2b.web.controller;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.Order;
import com.b2b.common.domain.OrderBag;
import com.b2b.common.domain.OrderItem;
import com.b2b.common.domain.StandardOrder;
import com.b2b.common.domain.StandardOrderItem;
import com.b2b.common.domain.Stock;
import com.b2b.common.domain.SysLog;
import com.b2b.common.domain.WXUser;
import com.b2b.common.domain.WXUserOrder;
import com.b2b.common.domain.WeightCoefficient;
import com.b2b.common.util.DateUtil;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.enums.OrderStatusEnum;
import com.b2b.page.Page;
import com.b2b.service.AccountLockService;
import com.b2b.service.CustomerService;
import com.b2b.service.LogService;
import com.b2b.service.OrderService;
import com.b2b.service.StandardOrderItemService;
import com.b2b.service.StandardOrderService;
import com.b2b.service.StockService;
import com.b2b.service.WXUserOrderService;
import com.b2b.service.WXUserService;
import com.b2b.web.util.SessionHelper;
import com.b2b.web.wx.util.NotifyUtil;
import com.b2b.web.wx.util.Properties;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RequestMapping("wxOrder")
@Controller
public class WXOrderController {
	private static final Logger logger = LoggerFactory.getLogger(WXOrderController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd hh:mm:ss";
	@Autowired
	private StandardOrderItemService standardOrderItemService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private AccountLockService accountLockService;
	
	@Autowired
	private StockService stockService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private WXUserService wxUserService;
	
	@Autowired
	private WXUserOrderService wxUserOrderService;
	
	@Autowired
	private Properties properties;
	
	@Autowired
	LogService logService;
	
	@Autowired
	StandardOrderService standardOrderService;
	
	@RequestMapping("orderList.htm")
	@ResponseBody
	public ModelAndView findWXorderByParam(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("wxOrder/list");
		try {
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));
			
			
			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");
			String start = startTimeStr+" 00:00:00";
			String end = endTimeStr+ " 23:59:59";
			Date startTime = null;
			Date endTime = null;

			if (StringUtils.isNotBlank(startTimeStr)) {
				startTime = DateUtil.parseDateStr(start, DATE_FORMAT_YMD);
				mv.addObject("startTime", startTimeStr);
			}

			if (StringUtils.isNotBlank(endTimeStr)) {
				endTime = DateUtil.parseDateStr(end, DATE_FORMAT_YMD);
				mv.addObject("endTime", endTimeStr);
			}
			String orderNum = request.getParameter("orderNum");
			String userName = request.getParameter("userName");
			mv.addObject("orderNum", orderNum);
			mv.addObject("userName", userName);
			String param = request.getParameter("param");
			mv.addObject("param", param);
			Page<WXUserOrder> wxUserOrderlist=this.wxUserOrderService.findAllwxOrder(startTime,endTime,currentPage,Page.DEFAULT_PAGE_SIZE,orderNum,userName,param);
			mv.addObject("page",wxUserOrderlist);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			Page<WXUserOrder> page = new Page<WXUserOrder>(1, 1, Page.DEFAULT_PAGE_SIZE,new ArrayList<WXUserOrder>());
			mv.addObject("page", page);
		}
		return mv;
	}
	
	@RequestMapping(value = "delivery.do", method = RequestMethod.POST)
	@ResponseBody
	public HashMap<String,String> delivery(@RequestParam("id")Integer id,@RequestParam("name")String Fastname,@RequestParam("num")String Fastnum,HttpServletRequest req){
		try {
			HashMap<String,String> map = new HashMap<String, String>();
			WXUserOrder wXUserOrder = this.wxUserOrderService.findById(id);
			CustomerUser personUser = customerService.findById(1044);
			Order order = new Order();
			BigDecimal dis=new BigDecimal(1);
			order.setDiscount(dis);
			order.setAddress(wXUserOrder.getWxaddress());
			order.setCreatedTime(new Date());
			order.setOrderStatus(OrderStatusEnum.PAY.getId());
			order.setStatus(Constant.VALID_STATUS);
			order.setUserId(personUser.getId());
			order.setBusinessId(personUser.getBusinessId());
			order.setOrderNo(wXUserOrder.getWxorderNum());
			order.setExecutedTime(new Date());
			AccountLock accountLock=new AccountLock();
			Date exce=order.getExecutedTime();
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(exce);
			int year = calendar.get(Calendar.YEAR);
			int month = calendar.get(Calendar.MONTH)+1;
			accountLock.setYears(year+"");
			accountLock.setMonths(month+"");
//			int lock=accountLockService.findLock(accountLock);
//			if(lock==1)
//			{
//				map.put("data", "执行时间不能设置到已锁帐月份!");
//				return map;
//						
//			}
			List<OrderItem> orderItemList = new ArrayList<OrderItem>();
			List<OrderBag> orderBagList = new ArrayList<OrderBag>();
			List<StandardOrderItem> itemList = this.standardOrderItemService.findByStandOrderId(wXUserOrder.getSnackpackageId());
			StandardOrder stand = standardOrderService.findByStandId(String.valueOf(wXUserOrder.getSnackpackageId()));
			int num = wXUserOrder.getSnackpackageNum();
			
			OrderBag orderBag = new OrderBag();
			orderBag.setBagId(wXUserOrder.getSnackpackageId());
			orderBag.setBagName(stand.getTypeValue());
			orderBag.setNum(num);
			orderBag.setOrderno(order.getOrderNo());
			orderBag.setPrice(stand.getOfferPrice());
			orderBag.setTotalFee(orderBag.getPrice()*orderBag.getNum());
			orderBag.setStatus(1);
			orderBagList.add(orderBag);
			Long orderTotalCost = 0l;
			Long orderTotalFee = 0l;
			Long notaxInclusiveTotalCost = 0l;
			for (StandardOrderItem item : itemList) {
				Stock stock = this.stockService.findByItemIdIngoreIsdown(item.getItemId());
				if(null!=stock&&stock.getNum()>=item.getStockNum()*num){
					OrderItem oi = new OrderItem();
					oi.setOrderNo(order.getOrderNo());
					oi.setItemId(item.getItemId());
					oi.setNum(item.getNum()*num);
					oi.setItemSizeType(item.getItemSizeType());
					oi.setItemName(item.getItemName());
					oi.setItemPrice(item.getItemPrice());
					oi.setItemCostPrice(item.getItemCostPrice());
					oi.setItemSize(item.getItemSize());
					oi.setFee(item.getFee()*num);
					oi.setConsumeStockNum(item.getStockNum()*num);
					orderItemList.add(oi);
					orderTotalFee += item.getFee();
					orderTotalCost += oi.getItemCostPrice()*oi.getNum();
					notaxInclusiveTotalCost += item.getNotaxinclusivecostprice()*oi.getNum();
				}else{
					 map.put("data", "商品id:"+item.getItemId()+"库存不足");
					 return map;
				}
			}
			
			if(wXUserOrder.getSnackpackageFee()>orderTotalFee){
				OrderItem oi = new OrderItem();
				oi.setOrderNo(order.getOrderNo());
				oi.setItemId(1244);
				oi.setNum(num);
				oi.setItemSizeType("SIZE");
				oi.setItemName("包装物流");
				oi.setItemPrice(wXUserOrder.getSnackpackageFee()-orderTotalFee);
				oi.setItemCostPrice(1600l);
				oi.setItemSize("件");
				oi.setFee(oi.getItemPrice()*num);
				oi.setConsumeStockNum(num);
				orderItemList.add(oi);
			}
			order.setTotalFee(wXUserOrder.getSnackpackageTotal());
			order.setTotalNum(wXUserOrder.getSnackpackageNum());
			order.setTotalCost(orderTotalCost);
			order.setNotaxInclusiveTotalCost(notaxInclusiveTotalCost);
			order.setOrderItemList(orderItemList);
			wxUserOrderService.createOrder(order,orderBagList,Fastname,Fastnum,id);
			WXUser wxuser = wxUserService.findById(wXUserOrder.getWxuserId());
			//DateFormat df = new SimpleDateFormat(DATE_FORMAT_YMD);
			//String date = df.format(wXUserOrder.getCreatedTime());
			String fastName = "";
			String fastNO = "";
			if("" != Fastname && null != Fastname && "" != Fastnum &&null!=Fastnum){
				fastName = Fastname;
				fastNO = Fastnum;
			}else{
				fastName = "领蛙物流";
				fastNO = "无";
			}
			String SHinfo = wXUserOrder.getWxname()+" "+wXUserOrder.getWxphone()+" "+wXUserOrder.getWxaddress();
			NotifyUtil.deliveryMessage(properties.DELIVERY_TEMP_ID, wxuser.getOpenid(), wXUserOrder.getWxorderNum(), fastName,fastNO, SHinfo, properties.weixinAppid, properties.weixinAppsecret);
			this.savelog(req.getSession(),order, "微信订单转内部订单，orderNo:"+order.getOrderNo());
			 map.put("data", "200");
			 return map;
		} catch (Exception e) {
			logger.error("添加订单失败", e);
			HashMap<String,String> map = new HashMap<String, String>();
			map.put("data", e.getMessage());
			return map;
		}
	}
	
	@RequestMapping("fast.do")
	@ResponseBody
	public String addFast(@RequestParam("name")String name,@RequestParam("num")String num,@RequestParam("id")Integer id){
		try {
			String fastNO = "";
			if(name!=""&&num!=""){
				fastNO = name+"   "+num;
			}
			this.wxUserOrderService.updateFast(fastNO,id);
			return "200";
		} catch (Exception e) {
			logger.error("添加快递单失败",e);
		}
		return "201";
	}
	
	@RequestMapping("printKD.do")
	@ResponseBody
	public ModelAndView printKD(@RequestParam("id")Integer id){
		ModelAndView mv = new ModelAndView("wxOrder/printOrder");
		WXUserOrder orderInfo = this.wxUserOrderService.findById(id);
		mv.addObject("orderInfo", orderInfo);
		return mv;
	}
	
	
	@RequestMapping("refund.do")
	@ResponseBody
	public String refund(@RequestParam("id")Integer id,HttpServletRequest req){
		try {
			WXUserOrder order = new WXUserOrder();
			order.setId(id);
			order.setPayfeeStatus(2);
			this.wxUserOrderService.changePayfeeStatus(order);
			saveLog(req.getSession(), order , "微信订单退货：id"+id);
			return "200";
		} catch (Exception e) {
			 logger.error("微信订单退货失败",e);
		}
		return "201";
	}
	
	private void saveLog(HttpSession session,WXUserOrder dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.wxOrder.getName());
	       sysLog.setDataId(dto.getId().toString());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<WXUserOrder>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
	
	private void savelog(HttpSession session,Order dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
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
}
