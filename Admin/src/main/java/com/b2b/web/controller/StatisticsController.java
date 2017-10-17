package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.*;
import com.b2b.web.util.NumberTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.*;
import jxl.write.Number;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping({ "/statistics" })
public class StatisticsController {
	private static final Logger logger = LoggerFactory
			.getLogger(StatisticsController.class);
	
	@Autowired
	SupplierService supplierService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	StorageService storageService;

	@Autowired
	CustomerService customerService;
	
	@Autowired
	ItemCategoryService itemCategoryService;

	@RequestMapping({"statisticslist.htm" })
	public ModelAndView getStatisticsPage(HttpServletRequest request,
			HttpServletResponse response) {

		int currentPage = Integer.valueOf(
				(String) StringUtils.defaultIfBlank(
						request.getParameter("currentPage"), "1")).intValue();
		int pageSize = Integer.valueOf(
				(String) StringUtils.defaultIfBlank(
						request.getParameter("pageSize"), "15")).intValue();

		ModelAndView mv = new ModelAndView("statistics/list");
		TestController.getMenuPoint(mv, request);
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		List<ItemCategory> all = this.itemCategoryService.findAllOneCatsByCityId(user.getCityId());
		mv.addObject("cats", all);
		try {
			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");
			Date startTime = null;
			Date endTime = null;
			if ((!(StringUtils.isNotBlank(startTimeStr)))
					&& (!(StringUtils.isNotBlank(endTimeStr)))) {
				mv.addObject("page", null);
				return mv;
			}

			if (StringUtils.isNotBlank(startTimeStr)) {
				startTime = DateUtil.parseDateStr(startTimeStr, "yyyy-MM-dd");
				mv.addObject("startTime", startTimeStr);
			}

			if (StringUtils.isNotBlank(endTimeStr)) {
				endTime = DateUtil.parseDateStr(endTimeStr, "yyyy-MM-dd");
				mv.addObject("endTime", endTimeStr);
			}
			String item_name = request.getParameter("item_name");
			if (StringUtils.isNotEmpty(item_name)) {
				mv.addObject("item_name", item_name);
			}

			int userId = 0;

			String userName = request.getParameter("userName");
			if(StringUtils.isNotEmpty(userName)){
				CustomerUser personUser = customerService.findByUserName(userName);
				if(personUser!=null){
					userId= personUser.getId();
				}
				mv.addObject("userName", userName);
			}
			
			String sortColumn = request.getParameter("sortColumn");
			if(StringUtils.isBlank(sortColumn) || sortColumn.equals("${sortColumn}")){
				sortColumn="";
			}
 			mv.addObject("sortColumn", sortColumn);
			String catid = request.getParameter("catid");
			mv.addObject("catid", catid);
			String iswxvip = request.getParameter("type");
			Integer type = null;
			if(!StringUtils.isEmpty(iswxvip)){
				type =Integer.parseInt(iswxvip);
			}
			mv.addObject("type", type);
			PageHelper.startPage(currentPage, 50);
			List<OrderItem> orderItems = this.orderService
					.statisticsOrderItem(startTime, endTime, item_name,userId,catid,
							type,sortColumn,user.getCityId());
			PageInfo<OrderItem> info = new PageInfo<OrderItem>(orderItems);
			Page<OrderItem> page = new Page<OrderItem>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			mv.addObject("page", page);
			OrderItem total = this.orderService.statisticsOrderItemTotal(startTime,
					endTime,item_name,userId,catid,type,user.getCityId());

			mv.addObject("total", total);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<OrderItem> page = new Page<OrderItem>(1, 1L, 15, new ArrayList<OrderItem>());
			mv.addObject("page", page);
		}
		return mv;
	}
	
	@RequestMapping({"storageSumList.htm" })
	public ModelAndView getStorageSumPage(HttpServletRequest request,
			HttpServletResponse response) {
		int currentPage = Integer.valueOf(
				(String) StringUtils.defaultIfBlank(
						request.getParameter("currentPage"), "1")).intValue();
		int pageSize = Integer.valueOf(
				(String) StringUtils.defaultIfBlank(
						request.getParameter("pageSize"), "15")).intValue();
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		ModelAndView mv = new ModelAndView("statistics/storageList");
		TestController.getMenuPoint(mv, request);
		this.fillCommonSupplier(mv,user.getCityId());
		List<ItemCategory> all = this.itemCategoryService.findAllOneCatsByCityId(user.getCityId());
		mv.addObject("cats", all);
		try {
			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");
			Date startTime = null;
			Date endTime = null;
			if ((!(StringUtils.isNotBlank(startTimeStr)))
					&& (!(StringUtils.isNotBlank(endTimeStr)))) {
				mv.addObject("page", null);
				return mv;
			}

			if (StringUtils.isNotBlank(startTimeStr)) {
				startTime = DateUtil.parseDateStr(startTimeStr, "yyyy-MM-dd");
				mv.addObject("startTime", startTimeStr);
			}

			if (StringUtils.isNotBlank(endTimeStr)) {
				endTime = DateUtil.parseDateStr(endTimeStr, "yyyy-MM-dd");
				mv.addObject("endTime", endTimeStr);
			}
			String item_name = request.getParameter("item_name");
			if (StringUtils.isNotEmpty(item_name)) {
				mv.addObject("item_name", item_name);
			}
			String supplier_id = request.getParameter("supplier_id");
			if (StringUtils.isNotEmpty(supplier_id)) {
				mv.addObject("supplierid", supplier_id);
			}
			
			String catid = request.getParameter("catid");
			mv.addObject("catid", catid);
			Page<StorageItem> storageItemPage = this.storageService
					.storageItemList(startTime, endTime, item_name,supplier_id,catid,
							currentPage, pageSize,user.getCityId());
			if ((storageItemPage == null)
					|| (CollectionUtils.isEmpty(storageItemPage.getResult()))) {
				mv.addObject("page", null);
				return mv;
			}
			mv.addObject("page", storageItemPage);
			StorageItem total = this.storageService.storageItemTotal(startTime, endTime, item_name,supplier_id,catid,user.getCityId());
			mv.addObject("total", total);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<StorageItem> page = new Page<StorageItem>(1, 1L, 15, new ArrayList<StorageItem>());
			mv.addObject("page", page);
		}
		return mv;
	}
	
	private void fillCommonSupplier(ModelAndView view,Integer cityId) {
		List<Supplier> supplierList = null;
		supplierList = supplierService.findByCityId(cityId);
		view.addObject("supplierList", supplierList);
	}
	
	/**
	 * 导出核算信息到Excel
	 */
	@RequestMapping(value = "exportExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportExcel(HttpServletRequest request,
			HttpServletResponse response) {
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return "你还未设置默认城市，联系管理员设置";
		}
		String startTimeStr = request.getParameter("startTime");
		String endTimeStr = request.getParameter("endTime");
		Date startTime = null;
		Date endTime = null;
		if (StringUtils.isNotBlank(startTimeStr)) {
			startTime = DateUtil.parseDateStr(startTimeStr, "yyyy-MM-dd");
		}

		if (StringUtils.isNotBlank(endTimeStr)) {
			endTime = DateUtil.parseDateStr(endTimeStr, "yyyy-MM-dd");
		}
		String item_name = request.getParameter("item_name");
		String userName = request.getParameter("userName");
		int userId = 0;

//		String mobile = request.getParameter("mobile");
//		if (StringUtils.isNotEmpty(mobile)) {
//			CustomerUser personUser = customerService.findByPhone(mobile);
//			if(personUser!=null){
//				userId= personUser.getId();
//			}
//		}
		
		if(StringUtils.isNotEmpty(userName)){
			CustomerUser personUser = customerService.findByUserName(userName);
			if(personUser!=null){
				userId= personUser.getId();
			}
		}
		String catid = request.getParameter("catid");
		String sortColumn = request.getParameter("sortColumn");

		String FilePutPath = "D:\\销售统计.xls";
		WritableWorkbook book = null;
		SimpleDateFormat formatDate = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("销售统计.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			List<OrderItem> orderItem = this.orderService.statisticsOrderItem(startTime, endTime, item_name,userId,catid,null,sortColumn,user.getCityId());
			if (orderItem != null && !orderItem.isEmpty()) {
				sheet.mergeCells(0, 0, 11, 0);// 合并标题单元格
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, "产品销售查询统计表", format1);
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
				sheet.addCell(new Label(0, 1, "序号", cellFormat1));
				sheet.addCell(new Label(1, 1, "一级类目", cellFormat1));
				sheet.addCell(new Label(2, 1, "二级类目", cellFormat1));
				sheet.addCell(new Label(3, 1, "商品名称", cellFormat1));
				sheet.addCell(new Label(4, 1, "规格", cellFormat1));
				sheet.addCell(new Label(5, 1, "发货总量", cellFormat1));
				sheet.addCell(new Label(6, 1, "退货总量", cellFormat1));
				sheet.addCell(new Label(7, 1, "退货金额", cellFormat1));
				sheet.addCell(new Label(8, 1, "销售额", cellFormat1));
				sheet.addCell(new Label(9, 1, "成本", cellFormat1));
				sheet.addCell(new Label(10, 1, "利润", cellFormat1));

				sheet.setColumnView(1, 20);// 根据内容自动设置列宽
				for (int i = 0; i < orderItem.size(); i++) {
					sheet.addCell(new Number(0, i + 2, i + 1));
					sheet.addCell(new Label(1, i + 2, orderItem.get(i).getOneCate()));
					sheet.addCell(new Label(2, i + 2, orderItem.get(i).getTwoCate()));
					
					sheet.addCell(new Label(3, i + 2, orderItem.get(i).getItemName()));
					sheet.addCell(new Label(4, i + 2, orderItem.get(i).getItemSize()));
					Integer num=orderItem.get(i).getNum();
					sheet.addCell(new Number(5, i + 2,num==null?0:num ));
					Integer refundNum=orderItem.get(i).getRefundNum();
					sheet.addCell(new Number(6, i + 2,refundNum==null?0:refundNum ));
					Long refundFee=orderItem.get(i).getRefundFee();
					sheet.addCell(new Number(7, i + 2, refundFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(refundFee))).doubleValue()));
					Long fee=orderItem.get(i).getFee();
					sheet.addCell(new Number(8, i + 2, fee==null?0:(new BigDecimal(NumberTool.toYuanNumber(fee))).doubleValue()));
					Long itemCostPrice=orderItem.get(i).getItemCostPrice();
					sheet.addCell(new Number(9, i + 2, itemCostPrice==null?0:(new BigDecimal(NumberTool.toYuanNumber(itemCostPrice))).doubleValue()));
					Long profitFee=orderItem.get(i).getProfitFee();
					sheet.addCell(new Number(10, i + 2, profitFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(profitFee))).doubleValue()));
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
	
	/**
	 * 导出进货信息到Excel
	 */
	@RequestMapping(value = "storageSumExportExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String storageSumExportExcel(HttpServletRequest request,
			HttpServletResponse response) {
		String startTimeStr = request.getParameter("startTime");
		String endTimeStr = request.getParameter("endTime");
		Date startTime = null;
		Date endTime = null;
		if (StringUtils.isNotBlank(startTimeStr)) {
			startTime = DateUtil.parseDateStr(startTimeStr, "yyyy-MM-dd");
		}

		if (StringUtils.isNotBlank(endTimeStr)) {
			endTime = DateUtil.parseDateStr(endTimeStr, "yyyy-MM-dd");
		}
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return "你未设置默认城市";
		}
		String item_name = request.getParameter("item_name");
		String supplier_id = request.getParameter("supplier_id");
		String catid = request.getParameter("catid");
		String FilePutPath = "D:\\入库单查询统计.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("入库单查询统计.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			List<StorageItem> storageItem = this.storageService.storageItem(startTime, endTime, item_name,supplier_id,catid,user.getCityId());
			if (storageItem != null && !storageItem.isEmpty()) {
				sheet.mergeCells(0, 0, 3, 0);// 合并标题单元格
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, "入库单查询统计表", format1);
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
				sheet.addCell(new Label(0, 1, "序号", cellFormat1));
				sheet.addCell(new Label(1, 1, "商品名称", cellFormat1));
				sheet.addCell(new Label(2, 1, "进货数量", cellFormat1));
				sheet.addCell(new Label(3, 1, "进货价格", cellFormat1));

				sheet.setColumnView(1, 20);// 根据内容自动设置列宽
				for (int i = 0; i < storageItem.size(); i++) {
					sheet.addCell(new Number(0, i + 2, i + 1));
					sheet.addCell(new Label(1, i + 2, storageItem.get(i).getItemName()));
					Integer num=storageItem.get(i).getNum();
					sheet.addCell(new Number(2, i + 2,num==null?0:num ));
					Long totalFee=storageItem.get(i).getTotalFee();
					sheet.addCell(new Number(3, i + 2,totalFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(totalFee))).doubleValue()));
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
}