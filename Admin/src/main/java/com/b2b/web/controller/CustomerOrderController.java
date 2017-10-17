package com.b2b.web.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.Constant;
import com.b2b.common.domain.AccountLock;
import com.b2b.common.domain.CustomerOrder;
import com.b2b.common.domain.CustomerOrderItem;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.CustomerWise;
import com.b2b.common.domain.DebitNoteDemo;
import com.b2b.common.domain.Item;
import com.b2b.common.domain.ItemCategory;
import com.b2b.common.domain.Order;
import com.b2b.common.domain.OrderItem;
import com.b2b.common.domain.StandardOrder;
import com.b2b.common.domain.SysLog;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.util.DateUtil;
import com.b2b.common.util.OrderNumberGenerator;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.enums.OrderStatusEnum;
import com.b2b.job.CreateCustomerOrder;
import com.b2b.page.Page;
import com.b2b.service.CustomerOrderService;
import com.b2b.service.CustomerService;
import com.b2b.service.ItemCategoryService;
import com.b2b.service.ItemService;
import com.b2b.service.LogService;
import com.b2b.service.StandardOrderService;
import com.b2b.service.UserService;
import com.b2b.web.util.NumberTool;
import com.b2b.web.util.SessionHelper;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/customerOrder")
public class CustomerOrderController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerOrderController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	
	@Autowired
	CustomerOrderService customerOrderService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ItemService itemService;

	@Autowired
	ItemCategoryService itemCategoryService;
	
	@Autowired
	LogService logService;
	
	@Autowired
	StandardOrderService standardOrderService;
	
	@Autowired
	CreateCustomerOrder createCustomerOrder;
	
	@RequestMapping("customerOrderlist.htm")
	public ModelAndView getOrderListPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("customerOrder/list");
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

			if (StringUtils.isNotBlank(startTimeStr)) {
				startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
				mv.addObject("startTime", startTimeStr);
			}

			if (StringUtils.isNotBlank(endTimeStr)) {
				endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YMD);
				mv.addObject("endTime", endTimeStr);
			}

			CustomerOrder order = new CustomerOrder();

			String orderNum = request.getParameter("orderNum");
			String userName = request.getParameter("userName");

			if(StringUtils.isNotEmpty(orderNum)){
				order.setCustomerorderId(Integer.parseInt(orderNum));
				mv.addObject("orderNum", orderNum);
			}

            if(StringUtils.isNotEmpty(userName)){
				CustomerUser personUser = customerService.findByUserName(userName);
				if(personUser==null){
					order.setCustomerId(-1);
				}else{
					order.setCustomerId(personUser.getId());
				}
				mv.addObject("userName", userName);
			}
			
			if(queryUser>0){
				order.setCustomerId(queryUser);
			}

//			PersonUser user=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);

			Page<Pair<CustomerOrder, List<CustomerOrderItem>>> orderPage = customerOrderService
					.findOrder(order, startTime, endTime, currentPage,
							Page.DEFAULT_PAGE_SIZE);
			Map<String, String> orderMap = Maps.newHashMap();
			mv.addObject("orderMap", orderMap);

			mv.addObject("page", orderPage);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<Pair<CustomerOrder, List<CustomerOrderItem>>> page = new Page<Pair<CustomerOrder, List<CustomerOrderItem>>>(1, 1, Page.DEFAULT_PAGE_SIZE,new ArrayList<Pair<CustomerOrder, List<CustomerOrderItem>>>());
			mv.addObject("page", page);
		}
		return mv;
	}
	
	
	@RequestMapping("addCustomerOrder.htm")
	public ModelAndView addOrderPage(HttpServletRequest request,
			String returnedPurchase) {
		ModelAndView mv = new ModelAndView("customerOrder/add");
		mv.addObject("executedTime", new Date());
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

	}
	
	public String AddorUpdateStandardOrder(HttpServletRequest req,String value)
	{
		try {
			String buyerId = req.getParameter("buyerId");
			if (StringUtils.isEmpty(buyerId)) {
				return "用户ID不能为空!";
			}
			CustomerUser personUser = customerService.findById(Integer.parseInt(buyerId));
			CustomerOrder order = new CustomerOrder();
			order.setRemark(req.getParameter("memo"));
			String discount = req.getParameter("discount");
			if(StringUtils.isEmpty(discount))
			{
				return "折扣不能不空!";
			}
			BigDecimal dis=new BigDecimal(discount);
			order.setDiscount(dis);
			order.setCustomerAddress(personUser.getAddress());
			order.setCreatedTime(new Date());
			order.setCustomerStatus(Constant.DELETE_STATUS);
			order.setStatus(Constant.VALID_STATUS);
			order.setCustomerId(personUser.getId());
			order.setBusinessId(personUser.getBusinessId());
			String executedTimeStr = req.getParameter("executedTime");
			if (StringUtils.isEmpty(executedTimeStr)) {
				order.setExecutedTime(new Date());
			} else {
				order.setExecutedTime(DateUtil.parseDateStr(executedTimeStr, DATE_FORMAT_YMD));
			}
			Date exce=order.getExecutedTime();
			order.setWeek(DateUtil.getWeekInt(exce));
			List<CustomerOrderItem> orderItemList = new ArrayList<CustomerOrderItem>();
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
					CustomerOrderItem oi = new CustomerOrderItem();
					oi.setCustomerorderId(order.getCustomerorderId());
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
			order.setCustomerOrderList(orderItemList);
			for (CustomerOrderItem oi : orderItemList) {
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
				} else if (itemSizeType.equals("SALE_SIZE")) { // 零售
					itemSize = item.getSaleSize();
					price = item.getSalePrice();
					costPrice = item.getSaleCostPrice();
				} else if (itemSizeType.equals("BUY_SIZE")) { //批发
					itemSize = item.getBuySize();
					price = item.getPurchasePrice();
					costPrice = item.getBuyPrice();
				} else {
					return "商品 :" + oi.getItemName() + ",无法找到规格数据";
				}
				BigDecimal pr=(new BigDecimal(price)).multiply(dis).setScale(2, BigDecimal.ROUND_HALF_UP);
				price=pr.longValue();
				oi.setFee(price * oi.getNum());
				oi.setItemName(item.getItemName());
				oi.setItemPrice(price);
				oi.setItemCostPrice(costPrice);
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
				order.setCreatedTime(new Date());
				order.setCreatedUserid(SessionHelper.getUserId(req.getSession()));
				order.setUpdatedTime(new Date());
				order.setUpdatedUserid(order.getCreatedUserid());
				customerOrderService.createCustomerOrder(order);
			}else{
				order.setUpdatedTime(new Date());
				order.setUpdatedUserid(SessionHelper.getUserId(req.getSession()));
				String customerorderId = req.getParameter("customerorderId");
				if(StringUtils.isNotEmpty(customerorderId)){
					order.setCustomerorderId(Integer.parseInt(customerorderId));
				}
				customerOrderService.updateOrderAndItems(order);
			}
			
			this.saveLog(req.getSession(),order, value+"预订单，orderNo:"+order.getCustomerorderId());
			return value+"成功";

		} catch (Throwable e) {
			logger.error(value+"预订单失败", e);
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "/addOrder.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest req) {
		return AddorUpdateStandardOrder(req,"添加");
	}
	
	@RequestMapping(value = "/createCustOrder.do", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String createCustOrder(HttpServletRequest request) {
		try {
			int standardorder=0;
			CustomerWise customerWise=new CustomerWise();
			//生成客户预订单
			String standardorderId = request.getParameter("standardorderId");
			String executedTimeValue = request.getParameter("executedTimeValue");
			String buyerId = request.getParameter("buyerId");
			if (StringUtils.isEmpty(buyerId)) {
				return "用户ID不能为空!";
			}else{
				customerWise.setCustomerId(Integer.parseInt(buyerId));
			}
			String budget= request.getParameter("budget");
			if (StringUtils.isEmpty(budget)) {
				return "预算金额不能为空!";
			}else{
				Long budgetFee=Long.parseLong(budget)*100;
				customerWise.setBudget(budgetFee);
			}
//			PersonUser personUser = userService.findById(Integer.parseInt(buyerId));
			if(StringUtils.isNotEmpty(executedTimeValue)){
				Date date =DateUtil.parseDateStr(executedTimeValue, DATE_FORMAT_YMD);
				customerWise.setWeeks(DateUtil.getWeekInt(date));
				standardorder=standardOrderService.getStandardOrderByOrder(date,1);//1随机生成
			}
			if(StringUtils.isNotEmpty(standardorderId)){
				standardorder=Integer.parseInt(standardorderId);
			}
			if(standardorder>0){
				createCustomerOrder.createOrderInfoByStandId(standardorder, SessionHelper.getUserId(request.getSession()),customerWise);
			}else{
				return "无执行日期对应的套餐！";
			}
			
		} catch (Throwable e) {
			logger.error("生成预订单失败", e);
			return "failure";
		}
		return "success";
	}
	
	/**
	 * http://127.0.0.1:8080/customerOrder/autoCustomerOrder.do
	 * */
	@RequestMapping(value = "autoCustomerOrder.do", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public ModelAndView run(HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("redirect:/customerOrder/customerOrderlist.htm");
		try {
			createCustomerOrder.createOrderInfoByNextWeek();
		} catch (Exception e) {
			logger.error("自动生成客户预订单", e);
		}
		return view;
	}
	
	@RequestMapping(value = "/cancelOrder.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String cancelOrder(HttpServletRequest request) {
		try {

			String customerorderId = request.getParameter("customerorderId");
			CustomerOrder order = new CustomerOrder();
			if(StringUtils.isNotEmpty(customerorderId)){
				order.setCustomerorderId(Integer.parseInt(customerorderId));
				order.setStatus(Constant.DELETE_STATUS);
				order.setUpdatedTime(new Date());
				order.setUpdatedUserid(SessionHelper.getUserId(request.getSession()));
				this.customerOrderService.updateCustomerOrder(order);
				//this.saveLog(request.getSession(),order, "取消预订单，orderNo:"+order.getCustomerorderId());
			}else{
				return "failure";
			}
		} catch (Throwable e) {
			logger.error("取消预订单失败", e);
			return "failure";
		}
		return "success";
	}
	
	
	@RequestMapping(value = "/getOrder.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String getOrder(HttpServletRequest request) {
		try {

			String customerorderId = request.getParameter("customerorderId");
			String customerId=request.getParameter("customerId");
			CustomerOrder order = new CustomerOrder();
			if(StringUtils.isNotEmpty(customerorderId)){
				int customerIds=0;
				if(StringUtils.isNotEmpty(customerId)){
					customerIds=Integer.parseInt(customerId);
				}
				String orderNo=OrderNumberGenerator.buildOrderNo(new Date(), customerIds);
				this.customerOrderService.createOrder(Integer.parseInt(customerorderId),orderNo);
				order.setCustomerorderId(Integer.parseInt(customerorderId));
				order.setCustomerStatus(Constant.VALID_STATUS);
				order.setUpdatedTime(new Date());
				order.setUpdatedUserid(SessionHelper.getUserId(request.getSession()));
				this.customerOrderService.updateCustomerOrder(order);
				//this.saveLog(request.getSession(),order, "取消预订单，orderNo:"+order.getCustomerorderId());
			}else{
				return "failure";
			}
		} catch (Throwable e) {
			logger.error("生成订单失败", e);
			return e.getMessage();
		}
		return "success";
	}
	
	@RequestMapping("showOrder.htm")
	public ModelAndView editOrderShow(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("customerOrder/edit");

		try {
			String customerorderId = request.getParameter("customerorderId");
			if(StringUtils.isNotEmpty(customerorderId)){
			//Pair<Order, List<OrderItem>> pair = orderService.findByOrderNo(orderNo);
			//查询结果多加一个isDown字段，用于判断订单列表中下架的商品
			Pair<CustomerOrder, List<HashMap<String, Object>>> pair = customerOrderService.findByOrderNo(Integer.parseInt(customerorderId));
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
	

	@RequestMapping(value = "/planList.htm")
	public ModelAndView getPurchasePlan(HttpServletRequest request) {
		List<CustomerOrderItem> listItem =null;
		try {
			listItem = customerOrderService.getPurchasePlan();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		ModelAndView mv = new ModelAndView("customerOrder/planlist");
		mv.addObject("listItem", listItem);
		return mv;
	}
	
	/**
	 * 导出下周采购计划到Excel
	 */
	@RequestMapping(value = "exportPlanExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportExcel(HttpServletRequest request,
			HttpServletResponse response) {
		String FilePutPath = "D:\\下周采购计划.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("下周采购计划.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			List<CustomerOrderItem> listItem = customerOrderService.getPurchasePlan();
			if (listItem != null && !listItem.isEmpty()) {
				sheet.mergeCells(0, 0, 2, 0);// 合并标题单元格
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, "下周产品采购计划", format1);
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

				sheet.addCell(new Label(0, 1, "序号", cellFormat1));
				sheet.addCell(new Label(1, 1, "产品名称", cellFormat1));
				sheet.addCell(new Label(2, 1, "产品数量", cellFormat1));
				sheet.setColumnView(1, 30);// 根据内容自动设置列宽
				for (int i = 0; i < listItem.size(); i++) {
					sheet.addCell(new Number(0, i + 2, i + 1));
					sheet.addCell(new Label(1, i + 2, (String) listItem.get(i).getItemName()));
					sheet.addCell(new Number(2, i + 2, listItem.get(i).getNum()));
				}
			}
			// 写入数据并关闭文件
			System.out.println("11");
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
	
	
	private void saveLog(HttpSession session,CustomerOrder dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.CustomerOrder.getName());
	       sysLog.setDataId(dto.getCustomerId()+"");

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<CustomerOrder>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}

}
