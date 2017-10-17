package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.FreeShopDailyReportService;
import com.b2b.service.FreeShopMonthReportService;
import com.b2b.service.ReseauService;
import com.b2b.web.util.NumberTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.*;
import jxl.write.Number;
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
import java.util.Date;
import java.util.List;

@RequestMapping("free")
@Controller
public class FreeShopMonthReportController {
	private static final Logger logger = LoggerFactory
			.getLogger(FreeShopMonthReportController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	@Autowired
	private FreeShopMonthReportService freeShopMonthReportService;
	
	@Autowired
	private FreeShopDailyReportService freeShopDailyReportService;
	
	@Autowired
	ReseauService reseauService;
	
	@RequestMapping("allMonthReportlist.htm")
	public ModelAndView monthReportList(HttpServletRequest request){
		ModelAndView view = new ModelAndView("free/allMonthReportList");
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		PageHelper.startPage(currentPage, 50);
		List<FreeMonthReport> reports = this.freeShopMonthReportService.findAllMonthReport(personUser.getCityId());
		PageInfo<FreeMonthReport> info = new PageInfo<FreeMonthReport>(reports);
		Page<FreeMonthReport> page = new Page<FreeMonthReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		view.addObject("page", page);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	
	@RequestMapping("shopMonthReportlist.htm")
	public ModelAndView shopmonthReportList(HttpServletRequest request){
		ModelAndView view = new ModelAndView("free/shopMonthReportList");
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		String starttimeStr = request.getParameter("querydate");
		String param = request.getParameter("param");
		if(StringUtils.isEmpty(param)){
			param = "0";
		}
		view.addObject("param", param);
		String flag = request.getParameter("flag");
		if(StringUtils.isEmpty(flag)){
			flag = "0";
		}
		view.addObject("flag", flag);
		String region = request.getParameter("region");
		view.addObject("region", region);
		Date starttime = null;
		if (StringUtils.isNotBlank(starttimeStr)) {
			starttime = DateUtil.parseDateStr(starttimeStr, "yyyy-MM");
			view.addObject("querydate", starttimeStr);
		}
		int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
		view.addObject("reseauId", reseauId);
		String username = request.getParameter("userName");
		view.addObject("userName", username);
		PageHelper.startPage(currentPage, 50);
		List<FreeShopMonthReport> reports = this.freeShopMonthReportService.findAll(username,starttime,param,flag,region,reseauId,personUser.getCityId());
		PageInfo<FreeShopMonthReport> info = new PageInfo<FreeShopMonthReport>(reports);
		Page<FreeShopMonthReport> page = new Page<FreeShopMonthReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		List<Reseau> list = this.reseauService.findAllByCityId(personUser.getCityId());
		view.addObject("reseaus", list);
		view.addObject("page", page);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping("netMonthReportlist.htm")
	public ModelAndView netmonthReportList(HttpServletRequest request){
		ModelAndView view = new ModelAndView("free/netMonthReportList");
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		String querydate = request.getParameter("queryDate");
		view.addObject("querydate", querydate);
		int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
		view.addObject("reseauId", reseauId);
		PageHelper.startPage(currentPage, 50);
		List<FreeShopMonthReport> reports = this.freeShopMonthReportService.findNetShopmonthReport(querydate,reseauId,personUser.getCityId());
		PageInfo<FreeShopMonthReport> info = new PageInfo<FreeShopMonthReport>(reports);
		Page<FreeShopMonthReport> page = new Page<FreeShopMonthReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		List<Reseau> list = this.reseauService.findAllByCityId(personUser.getCityId());
		view.addObject("reseaus", list);
		view.addObject("page", page);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping(value = "month/exportExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String monthexportExcel(HttpServletRequest request,
			HttpServletResponse response) {
		String FilePutPath = "D:\\福利店单店月报.xls";
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "你没有设置默认城市";
		}
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("福利店单店月报.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			
			String starttimeStr = request.getParameter("starttime");
			String param = request.getParameter("param");
			if(StringUtils.isEmpty(param)){
				param = "0";
			}
			String flag = request.getParameter("flag");
			if(StringUtils.isEmpty(flag)){
				flag = "0";
			}
			int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
			Date starttime = null;
			if (StringUtils.isNotBlank(starttimeStr)) {
				starttime = DateUtil.parseDateStr(starttimeStr, "yyyy-MM");
			}
			String username = request.getParameter("userName");
			List<FreeShopMonthReport> reports = this.freeShopMonthReportService.findAll(username,starttime,param,flag,null,reseauId,personUser.getCityId());
			
			if (reports != null && !reports.isEmpty()) {
				sheet.mergeCells(0, 0, 14, 0);// 合并标题单元格
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, "福利店单店月报", format1);
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
				sheet.addCell(new Label(0, 1, "编号", cellFormat1));
				sheet.addCell(new Label(1, 1, "网格", cellFormat1));
				sheet.addCell(new Label(2, 1, "客户", cellFormat1));
				sheet.addCell(new Label(3, 1, "日期", cellFormat1));
				sheet.addCell(new Label(4, 1, "订单金额", cellFormat1));
				sheet.addCell(new Label(5, 1, "订单数量", cellFormat1));
				sheet.addCell(new Label(6, 1, "退货金额", cellFormat1));
				sheet.addCell(new Label(7, 1, "退货单数量", cellFormat1));
				sheet.addCell(new Label(8, 1, "采购总额", cellFormat1));
				sheet.addCell(new Label(9, 1, "同比", cellFormat1));
				sheet.addCell(new Label(10, 1, "日均", cellFormat1));
				sheet.addCell(new Label(11, 1, "利润", cellFormat1));
				sheet.addCell(new Label(12, 1, "利润率", cellFormat1));
				sheet.addCell(new Label(13, 1, "id", cellFormat1));
				sheet.addCell(new Label(14, 1, "客户性质", cellFormat1));
//				sheet.setColumnView(1, 25);// 根据内容自动设置列宽
//				sheet.setColumnView(2, 10);
//				sheet.setColumnView(3, 15);
//				sheet.setColumnView(4, 15);
//				sheet.setColumnView(5, 15);
//				sheet.setColumnView(6, 25);
				for (int i = 0; i < reports.size(); i++) {
					sheet.addCell(new Number(0, i + 2, i + 1));
					sheet.addCell(new Label(1, i + 2, reports.get(i).getRegion()));
					sheet.addCell(new Label(2, i + 2, reports.get(i).getUserName()));
					sheet.addCell(new Label(3, i + 2, DateUtil.formatDate(reports.get(i).getSumDate(), "yyyy-MM")));
					Long orderFee=Long.parseLong(reports.get(i).getOrderFee().toString());
					sheet.addCell(new Number(4, i + 2,orderFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(orderFee))).doubleValue()));
					Integer orderNum = reports.get(i).getOrderNum();
					if(orderNum!=null){
						sheet.addCell(new Number(5, i + 2, orderNum ));
					}else{
						sheet.addCell(new Label(5, i + 2, ""));
					}
					Long refundFee=Long.parseLong(reports.get(i).getRefundFee().toString());
					sheet.addCell(new Number(6, i + 2,refundFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(refundFee))).doubleValue()));
					
					Integer refundNum = reports.get(i).getRefundNum();
					if(orderNum!=null){
						sheet.addCell(new Number(7, i + 2, refundNum ));
					}else{
						sheet.addCell(new Label(7, i + 2, ""));
					}
					Long sourcingFee=Long.parseLong(reports.get(i).getSoucingFee().toString());
					sheet.addCell(new Number(8, i + 2,sourcingFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(sourcingFee))).doubleValue()));
					BigDecimal beforeSourcingFee = reports.get(i).getBeforeSoucingFee();
					if(null!=beforeSourcingFee){
						sheet.addCell(new Number(9, i + 2, Double.parseDouble(beforeSourcingFee.toString()) ));
					}else{
						sheet.addCell(new Label(9, i + 2, ""));
					}
					
					Long avgDayFee=Long.parseLong(reports.get(i).getAvgDayFee().toString());
					sheet.addCell(new Number(10, i + 2,avgDayFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(avgDayFee))).doubleValue()));
					Long profit=Long.parseLong(reports.get(i).getProfit().toString());
					sheet.addCell(new Number(11, i + 2,profit==null?0:(new BigDecimal(NumberTool.toYuanNumber(profit))).doubleValue()));
					BigDecimal profitRate = reports.get(i).getProfitRate();
					if(null!=profitRate){
						sheet.addCell(new Number(12, i + 2, Double.parseDouble(profitRate.toString()) ));
					}else{
						sheet.addCell(new Label(12, i + 2, ""));
					}
					sheet.addCell(new Number(13, i + 2, reports.get(i).getShopId() ));
					sheet.addCell(new Label(14, i + 2,reports.get(i).getIsnew() == 1 ? "新客户" : "老客户"));
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
	
	@RequestMapping(value = "monthnet/exportExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String monthnetexportExcel(HttpServletRequest request,
			HttpServletResponse response) {
		String FilePutPath = "D:\\福利店网格月报.xls";
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "你没有设置默认城市";
		}
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("福利店网格月报.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			
			String querydate = request.getParameter("queryDate");
			int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
			List<FreeShopMonthReport> reports = this.freeShopMonthReportService.findNetShopmonthReport(querydate,reseauId,personUser.getCityId());
			if (reports != null && !reports.isEmpty()) {
				sheet.mergeCells(0, 0, 17, 0);// 合并标题单元格
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, "福利店网格月报", format1);
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
				sheet.addCell(new Label(0, 1, "编号", cellFormat1));
				sheet.addCell(new Label(1, 1, "网格", cellFormat1));
				sheet.addCell(new Label(2, 1, "开通店铺数", cellFormat1));
				sheet.addCell(new Label(3, 1, "下单店铺数", cellFormat1));
				
				sheet.addCell(new Label(4, 1, "日期", cellFormat1));
				sheet.addCell(new Label(5, 1, "订单金额", cellFormat1));
				sheet.addCell(new Label(6, 1, "订单数量", cellFormat1));
				sheet.addCell(new Label(7, 1, "退货金额", cellFormat1));
				sheet.addCell(new Label(8, 1, "退货单数量", cellFormat1));
				sheet.addCell(new Label(9, 1, "采购总额", cellFormat1));
				sheet.addCell(new Label(10, 1, "店均采购额", cellFormat1));
				sheet.addCell(new Label(11, 1, "店日均采购额", cellFormat1));
				sheet.addCell(new Label(12, 1, "利润", cellFormat1));
				sheet.addCell(new Label(13, 1, "利润率", cellFormat1));
				sheet.addCell(new Label(14, 1, "新客户数", cellFormat1));
				sheet.addCell(new Label(15, 1, "老客户数", cellFormat1));
				sheet.addCell(new Label(16, 1, "新客户采购额", cellFormat1));
				sheet.addCell(new Label(17, 1, "老客户采购额", cellFormat1));
//				sheet.addCell(new Label(18, 1, "id", cellFormat1));
//				sheet.setColumnView(1, 25);// 根据内容自动设置列宽
//				sheet.setColumnView(2, 10);
//				sheet.setColumnView(3, 15);
//				sheet.setColumnView(4, 15);
//				sheet.setColumnView(5, 15);
//				sheet.setColumnView(6, 25);
				for (int i = 0; i < reports.size(); i++) {
					sheet.addCell(new Number(0, i + 2, i + 1));
					sheet.addCell(new Label(1, i + 2, reports.get(i).getRegion()));
					Integer openshopnum = reports.get(i).getOpenshopnum();
					if(openshopnum!=null){
						sheet.addCell(new Number(2, i + 2, openshopnum ));
					}else{
						sheet.addCell(new Label(2, i + 2, ""));
					}
					Integer ordershopnum = reports.get(i).getOrdershopnum();
					if(ordershopnum!=null){
						sheet.addCell(new Number(3, i + 2, ordershopnum ));
					}else{
						sheet.addCell(new Label(3, i + 2, ""));
					}
					
					sheet.addCell(new Label(4, i + 2, DateUtil.formatDate(reports.get(i).getSumDate(), "yyyy-MM")));
					Long orderFee=Long.parseLong(reports.get(i).getOrderFee().toString());
					sheet.addCell(new Number(5, i + 2,orderFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(orderFee))).doubleValue()));
					Integer orderNum = reports.get(i).getOrderNum();
					if(orderNum!=null){
						sheet.addCell(new Number(6, i + 2, orderNum ));
					}else{
						sheet.addCell(new Label(6, i + 2, ""));
					}
					Long refundFee=Long.parseLong(reports.get(i).getRefundFee().toString());
					sheet.addCell(new Number(7, i + 2,refundFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(refundFee))).doubleValue()));
					
					Integer refundNum = reports.get(i).getRefundNum();
					if(orderNum!=null){
						sheet.addCell(new Number(8, i + 2, refundNum ));
					}else{
						sheet.addCell(new Label(8, i + 2, ""));
					}
					Long sourcingFee=Long.parseLong(reports.get(i).getSoucingFee().toString());
					sheet.addCell(new Number(9, i + 2,sourcingFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(sourcingFee))).doubleValue()));
					Long avgShopDayFee=Long.parseLong(reports.get(i).getAvgShopDayFee().toString());
					sheet.addCell(new Number(10, i + 2,avgShopDayFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(avgShopDayFee))).doubleValue()));
					
					Long avgDayFee=Long.parseLong(reports.get(i).getAvgDayFee().toString());
					sheet.addCell(new Number(11, i + 2,avgDayFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(avgDayFee))).doubleValue()));
					Long profit=Long.parseLong(reports.get(i).getProfit().toString());
					sheet.addCell(new Number(12, i + 2,profit==null?0:(new BigDecimal(NumberTool.toYuanNumber(profit))).doubleValue()));
					BigDecimal profitRate = reports.get(i).getProfitRate();
					if(null!=profitRate){
						sheet.addCell(new Number(13, i + 2, Double.parseDouble(profitRate.toString()) ));
					}else{
						sheet.addCell(new Label(13, i + 2, ""));
					}
					Integer newordershopnum = reports.get(i).getNewordershopnum();
					if(ordershopnum!=null){
						sheet.addCell(new Number(14, i + 2, newordershopnum ));
					}else{
						sheet.addCell(new Label(14, i + 2, ""));
					}

					Integer oldordershopnum = reports.get(i).getOldordershopnum();
					if(ordershopnum!=null){
						sheet.addCell(new Number(15, i + 2, oldordershopnum ));
					}else{
						sheet.addCell(new Label(15, i + 2, ""));
					}

					Long newsourcingFee=Long.parseLong(reports.get(i).getNewsoucingFee().toString());
					sheet.addCell(new Number(16, i + 2,sourcingFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(newsourcingFee))).doubleValue()));
					Long oldsourcingFee=Long.parseLong(reports.get(i).getOldsoucingFee().toString());
					sheet.addCell(new Number(17, i + 2,sourcingFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(oldsourcingFee))).doubleValue()));

//					sheet.addCell(new Number(18, i + 2, reports.get(i).getShopId() ));
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
	
	@RequestMapping(value = "day/exportExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String dayexportExcel(HttpServletRequest request,
			HttpServletResponse response) {
		String FilePutPath = "D:\\福利店网格日报.xls";
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "你没有设置默认城市";
		}
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("福利店网格日报.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			
			String starttimeStr = request.getParameter("starttime");
			String endtimeStr = request.getParameter("endtime");
			Date starttime = null;
			Date endtime = null;
			if (StringUtils.isNotBlank(starttimeStr)) {
				starttime = DateUtil.parseDateStr(starttimeStr, DATE_FORMAT_YMD);
			}
			if (StringUtils.isNotBlank(endtimeStr)) {
				endtime = DateUtil.parseDateStr(endtimeStr, DATE_FORMAT_YMD);
			}
			int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
			List<FreeShopDailyReport> reports = this.freeShopDailyReportService.findNetDailyReport(starttime,endtime,reseauId,personUser.getCityId());
			
			if (reports != null && !reports.isEmpty()) {
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
				Label label = new Label(0, 0, "福利店网格日报", format1);
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
				sheet.addCell(new Label(0, 1, "编号", cellFormat1));
				sheet.addCell(new Label(1, 1, "网格", cellFormat1));
				sheet.addCell(new Label(2, 1, "月份", cellFormat1));
				sheet.addCell(new Label(3, 1, "开通店铺数", cellFormat1));
				sheet.addCell(new Label(4, 1, "下单店铺数", cellFormat1));
				sheet.addCell(new Label(5, 1, "订单金额", cellFormat1));
				sheet.addCell(new Label(6, 1, "订单数量", cellFormat1));
				sheet.addCell(new Label(7, 1, "退货金额", cellFormat1));
				sheet.addCell(new Label(8, 1, "退货单数量", cellFormat1));
				sheet.addCell(new Label(9, 1, "采购总额", cellFormat1));
				sheet.addCell(new Label(10, 1, "店均采购额", cellFormat1));

//				sheet.setColumnView(1, 25);// 根据内容自动设置列宽
//				sheet.setColumnView(2, 10);
//				sheet.setColumnView(3, 15);
//				sheet.setColumnView(4, 15);
//				sheet.setColumnView(5, 15);
//				sheet.setColumnView(6, 25);
				for (int i = 0; i < reports.size(); i++) {
					sheet.addCell(new Number(0, i + 2, i + 1));
					sheet.addCell(new Label(1, i + 2, reports.get(i).getReseauName()));
					Integer openShopNum = reports.get(i).getOpenShopNum();
					sheet.addCell(new Label(2, i + 2, DateUtil.formatDate(reports.get(i).getSumDate(), "yyyy-MM-dd")));
					if(openShopNum!=null){
						sheet.addCell(new Number(3, i + 2, openShopNum ));
					}else{
						sheet.addCell(new Label(3, i + 2, ""));
					}
					
					Integer orderShopNum = reports.get(i).getOrderShopNum();
					if(orderShopNum!=null){
						sheet.addCell(new Number(4, i + 2, orderShopNum ));
					}else{
						sheet.addCell(new Label(4, i + 2, ""));
					}
					Long orderFee=Long.parseLong(reports.get(i).getOrderFee().toString());
					sheet.addCell(new Number(5, i + 2,orderFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(orderFee))).doubleValue()));
					Integer orderNum = reports.get(i).getOrderNum();
					if(orderNum!=null){
						sheet.addCell(new Number(6, i + 2, orderNum ));
					}else{
						sheet.addCell(new Label(6, i + 2, ""));
					}
					Long refundFee=Long.parseLong(reports.get(i).getRefundFee().toString());
					sheet.addCell(new Number(7, i + 2,refundFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(refundFee))).doubleValue()));
					
					Integer refundNum = reports.get(i).getRefundNum();
					if(orderNum!=null){
						sheet.addCell(new Number(8, i + 2, refundNum ));
					}else{
						sheet.addCell(new Label(8, i + 2, ""));
					}
					Long sourcingFee=Long.parseLong(reports.get(i).getSourcingFee().toString());
					sheet.addCell(new Number(9, i + 2,sourcingFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(sourcingFee))).doubleValue()));
					
					Long avgFee = reports.get(i).getAvgFee();
					sheet.addCell(new Number(10, i + 2,avgFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(avgFee))).doubleValue()));
					
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
	
	@RequestMapping("netDayReportlist.htm")
	public ModelAndView dayReportList(HttpServletRequest request){
		ModelAndView view = new ModelAndView("free/netDayReportList");
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		String starttimeStr = request.getParameter("starttime");
		String endtimeStr = request.getParameter("endtime");
		Date starttime = null;
		Date endtime = null;
		if (StringUtils.isNotBlank(starttimeStr)) {
			starttime = DateUtil.parseDateStr(starttimeStr, DATE_FORMAT_YMD);
			view.addObject("starttime", starttimeStr);
		}
		if (StringUtils.isNotBlank(endtimeStr)) {
			endtime = DateUtil.parseDateStr(endtimeStr, DATE_FORMAT_YMD);
			view.addObject("endtime", endtimeStr);
		}
		int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
		view.addObject("reseauId", reseauId);
		PageHelper.startPage(currentPage, 50);
		List<FreeShopDailyReport> reports = this.freeShopDailyReportService.findNetDailyReport(starttime,endtime,reseauId,personUser.getCityId());
		PageInfo<FreeShopDailyReport> info = new PageInfo<FreeShopDailyReport>(reports);
		Page<FreeShopDailyReport> page = new Page<FreeShopDailyReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		view.addObject("page", page);
		List<Reseau> list = this.reseauService.findAllByCityId( personUser.getCityId());
		view.addObject("reseaus", list);
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping("allDayReportlist.htm")
	public ModelAndView allDayReportList(HttpServletRequest request){
		ModelAndView view = new ModelAndView("free/allDayReportList");
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		PageHelper.startPage(currentPage, 50);
		List<FreeShopDailyReport> reports = this.freeShopDailyReportService.findAllDailyReport(personUser.getCityId());
		PageInfo<FreeShopDailyReport> info = new PageInfo<FreeShopDailyReport>(reports);
		Page<FreeShopDailyReport> page = new Page<FreeShopDailyReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		view.addObject("page", page);
		TestController.getMenuPoint(view, request);
		return view;
	}
}
