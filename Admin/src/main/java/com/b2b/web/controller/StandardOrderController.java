package com.b2b.web.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
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
import com.b2b.common.domain.BlackWhiteListVariety;
import com.b2b.common.domain.CategoryNum;
import com.b2b.common.domain.CustomerOrder;
import com.b2b.common.domain.CustomerOrderItem;
import com.b2b.common.domain.Item;
import com.b2b.common.domain.ItemCategory;
import com.b2b.common.domain.Order;
import com.b2b.common.domain.OrderItem;
import com.b2b.common.domain.StandardOrder;
import com.b2b.common.domain.StandardOrderItem;
import com.b2b.common.domain.SysLog;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.util.DateUtil;
import com.b2b.common.util.OrderNumberGenerator;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.enums.OrderStatusEnum;
import com.b2b.job.AutoStandradOrder;
import com.b2b.job.CreateCustomerOrder;
import com.b2b.job.GenTranSumJob;
import com.b2b.page.Page;
import com.b2b.service.CategoryNumService;
import com.b2b.service.ItemCategoryService;
import com.b2b.service.ItemService;
import com.b2b.service.LogService;
import com.b2b.service.OrderService;
import com.b2b.service.StandardOrderService;
import com.b2b.service.UserService;
import com.b2b.web.util.SessionHelper;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Controller
@RequestMapping("/standardOrder")
public class StandardOrderController {
	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	
	@Autowired
	StandardOrderService standardOrderService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	ItemCategoryService itemCategoryService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	LogService logService;
	
	@Autowired
	AutoStandradOrder autoStandradOrder;
	
	@Autowired
	CreateCustomerOrder createCustomerOrder;
	
	@Autowired
	CategoryNumService categoryNumService;
	
	@RequestMapping("standardOrderList.htm")
	public ModelAndView getOrderListPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("standardOrder/list");
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
		ModelAndView mv = new ModelAndView("standardOrder/add");
		mv.addObject("executedTime", new Date());
		this.fillCommonData(mv, 0);
		return mv;
	}
	
	@RequestMapping("categorynum.htm")
	public ModelAndView addCateNum(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("standardOrder/categorynum");
		List<CategoryNum> categoryNumList=categoryNumService.selectAll();
		mv.addObject("catListResult", categoryNumList);
		mv.addObject("catListSizeResult", categoryNumList.size());
		this.fillCommonData(mv, 0);
		return mv;
	}
	
//	@RequestMapping("categorynumedit.htm")
//	public ModelAndView editCateNum(HttpServletRequest req) {
//		ModelAndView mv = new ModelAndView("standardOrder/categorynumedit");
//		List<CategoryNum> categoryNumList=categoryNumService.selectAll();
//		mv.addObject("catListResult", categoryNumList);
//		mv.addObject("catListSizeResult", categoryNumList.size());
//		this.fillCommonData(mv, 0);
//		return mv;
//	}
	
	@RequestMapping("savecategory.do")
	public ModelAndView saveCateNum(HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("redirect:/standardOrder/categorynum.htm");
		List<CategoryNum> categoryNumList=new ArrayList<CategoryNum>();
		//PersonUser personUser=(PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
		int categoryRowCount = Integer.parseInt(StringUtils.defaultString(
				req.getParameter("rowCount_query"), "0"));
		for (int i = 0; i < categoryRowCount; i++) {
			int categoryId = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("queryCateId" + i), "0"));
			if (categoryId == 0) {
				continue;
			}
			int num = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("queryNum" + i), "0"));
			CategoryNum categoryNum = new CategoryNum();
			categoryNum.setCategoryid(categoryId);
			categoryNum.setNum(num);
			categoryNumList.add(categoryNum);
		}
		categoryNumService.insert(categoryNumList);
//		mv.addObject("catListResult", categoryNumList);
//		mv.addObject("catListSizeResult", categoryNumList.size());
//		this.fillCommonData(mv, 0);
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
	
	@RequestMapping(value = "/addOrder.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest req) {
		return AddorUpdateStandardOrder(req,"添加");
	}
	
	@RequestMapping(value = "/cancelOrder.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String cancelOrder(HttpServletRequest request) {
		try {
			String standardorderId = request.getParameter("standardorderId");
			StandardOrder order = new StandardOrder();
			if(StringUtils.isNotEmpty(standardorderId)){
				order.setStandardorderId(Integer.parseInt(standardorderId));
				order.setStatus(Constant.DELETE_STATUS);
				order.setUpdatedTime(new Date());
				order.setUpdatedUserid(SessionHelper.getUserId(request.getSession()));
				this.standardOrderService.updateStandardOrder(order);
				//saveLog(request.getSession(),order, "取消套餐，standardorderId:"+order.getStandardorderId());
			}else{
				return "failure";
			}
		} catch (Throwable e) {
			logger.error("取消套餐失败", e);
			return "failure";
		}
		return "success";
	}
	
	@RequestMapping("showOrder.htm")
	public ModelAndView editOrderShow(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("standardOrder/edit");
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
	
	@RequestMapping(value="editOrder.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String editOrderSave( HttpServletRequest req) {
		return this.AddorUpdateStandardOrder(req, "保存");
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
	
	@RequestMapping(value = "/createOrder.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String createOrder(HttpServletRequest request) {
		try {
			//生成客户预订单
			String standardorderId = request.getParameter("standardorderId");
			StandardOrder order = new StandardOrder();
			if(StringUtils.isNotEmpty(standardorderId)){
				createCustomerOrder.createOrderInfoByStandId(Integer.parseInt(standardorderId), SessionHelper.getUserId(request.getSession()),null);
				order.setStandardorderId(Integer.parseInt(standardorderId));
				order.setStandardStatus(1);
				order.setUpdatedTime(new Date());
				order.setUpdatedUserid(SessionHelper.getUserId(request.getSession()));
				this.standardOrderService.updateStandardOrder(order);
			}else{
				return "failure";
			}
			
		} catch (Throwable e) {
			logger.error("生成预订单失败", e);
			return "failure";
		}
		return "success";
	}
	
	@RequestMapping(value = "/createCustOrder.do", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String createCustOrder(HttpServletRequest request) {
		try {
			int standardorder=0;
			//生成客户预订单
			String standardorderId = request.getParameter("standardorderId");
			String executedTimeValue = request.getParameter("executedTimeValue");
			if(StringUtils.isNotEmpty(executedTimeValue)){
				Date date =DateUtil.parseDateStr(executedTimeValue, DATE_FORMAT_YMD);
				standardorder=standardOrderService.getStandardOrderByOrder(date,1);//1随机生成
			}
			StandardOrder order = new StandardOrder();
			if(StringUtils.isNotEmpty(standardorderId)){
				standardorder=Integer.parseInt(standardorderId);
			}
			if(standardorder>0){
//				createCustomerOrder.createOrderInfoByStandId(standardorder, SessionHelper.getUserId(request.getSession()));
//				order.setStandardorderId(standardorder);
//				order.setStandardStatus(1);
//				order.setUpdatedTime(new Date());
//				order.setUpdatedUserid(SessionHelper.getUserId(request.getSession()));
//				this.standardOrderService.updateStandardOrder(order);
			}else{
				return "failure";
			}
			
		} catch (Throwable e) {
			logger.error("生成预订单失败", e);
			return "failure";
		}
		return "success";
	}
	
	@RequestMapping(value = "autoStandardOrder.do", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public ModelAndView run(HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("redirect:/standardOrder/standardOrderlist.htm?snackpackageType=1");
		String result="";
		try {
//			int num=10;//默认生成10天的标准套餐
//			int standardOrderCount=standardOrderService.selectStandardOrderCount();
//			if(standardOrderCount>0){
//				num=5;//如果标准订单已经生成过，那么生成5天即可
//			}
//			//获取待选商品信息
//			List<Item> listItem=itemService.selectAutoItem();
//			String result = genTranSumJob.work();
//			if (StringUtils.isNotBlank(result)) {
//				return result;
//			}
			result =autoStandradOrder.getNextWeekOrder();
//			result =autoStandradOrder.getAfterNextWeekOrder();
		} catch (Exception e) {
			logger.error("自动生成标准套餐", e);
			//return e.getMessage();
		}
		//return result;
		return view;
	}
	
	@RequestMapping(value = "autoAfterNextWeekOrder.do", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public ModelAndView runAfter(HttpServletRequest request) throws Exception {
		ModelAndView view = new ModelAndView("redirect:/standardOrder/standardOrderlist.htm?snackpackageType=1");
		String result="";
		try {
			result =autoStandradOrder.getAfterNextWeekOrder();
		} catch (Exception e) {
			logger.error("自动生成标准套餐", e);
		}
		return view;
	}
	
	
	/**
	 * 导出标准套餐计划到Excel
	 */
	@RequestMapping(value = "exportPlanExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportExcel(HttpServletRequest request,
			HttpServletResponse response) {
		String FilePutPath = "D:\\标准套餐.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("标准套餐.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);

			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");

			Date startTime = null;
			Date endTime = null;

			if (StringUtils.isNotBlank(startTimeStr)) {
				startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
			}

			if (StringUtils.isNotBlank(endTimeStr)) {
				endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YMD);
			}
			List<StandardOrderItem> listItem = standardOrderService.selectByTime(startTime,endTime);
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
				Label label = new Label(0, 0, "标准套餐", format1);
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
				sheet.addCell(new Label(2, 1, "套餐中出现的次数", cellFormat1));
				sheet.setColumnView(1, 30);// 根据内容自动设置列宽
				sheet.setColumnView(2, 30);// 根据内容自动设置列宽
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

}
