package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.Reseau;
import com.b2b.common.domain.ShopMonthReport;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.ReseauService;
import com.b2b.service.ShopMonthReportService;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("shopMonthReport")
@Controller
public class ShopMonthReportController {
	private static final Logger logger = LoggerFactory
			.getLogger(ShopMonthReportController.class);
	@Autowired
	private ShopMonthReportService shopMonthReportService;
	
	@Autowired
	private ReseauService reseauService;
	
	@RequestMapping("shopMonthReportList.htm")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("shopMonthReport/list");
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		String date = request.getParameter("queryDate");
		Date startTime = null;
		Date endTime = null;
		if(StringUtils.isNotEmpty(date) && !date.equals("0")){
			Date date2 = DateUtil.parseDateStr(date, "yyyy-MM");
			startTime = DateUtil.getFirstDayOfMonth(date2);
			endTime = DateUtil.getLastDayOfMonth(date2);
		}
		String username = request.getParameter("userName");
		String param = request.getParameter("param");
		if(StringUtils.isEmpty(param)){
			param = "0";
		}
		int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
		mv.addObject("reseauId", reseauId);
		String region = request.getParameter("region");
		mv.addObject("querydate", date);
		mv.addObject("username", username);
		mv.addObject("param", param);
		mv.addObject("region", region);
		PageHelper.startPage(currentPage, 50);
		List<ShopMonthReport> reports = this.shopMonthReportService.findByCondition(startTime,endTime,username,param,region,reseauId,personUser.getCityId());
		PageInfo<ShopMonthReport> info = new PageInfo<ShopMonthReport>(reports);
		Page<ShopMonthReport> page = new Page<ShopMonthReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		List<Reseau> list = this.reseauService.findAllByCityId(personUser.getCityId());
		mv.addObject("reseaus", list);
		mv.addObject("page", page);
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping("netMonthReportList.htm")
	public ModelAndView netlist(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("shopMonthReport/netlist");
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		String querydate = request.getParameter("queryDate");
		mv.addObject("querydate", querydate);
		int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
		mv.addObject("reseauId", reseauId);
		PageHelper.startPage(currentPage, 50);
		List<ShopMonthReport> reports = this.shopMonthReportService.findNetMonthReport(reseauId,querydate,personUser.getCityId());
		PageInfo<ShopMonthReport> info = new PageInfo<ShopMonthReport>(reports);
		Page<ShopMonthReport> page = new Page<ShopMonthReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		mv.addObject("page", page);
		List<Reseau> list = this.reseauService.findAllByCityId(personUser.getCityId());
		mv.addObject("reseaus", list);
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping("allMonthReportList.htm")
	public ModelAndView monthReportlist(HttpServletRequest request){
		ModelAndView view = new ModelAndView("shopMonthReport/allList");
		try {
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return new ModelAndView("noCity");
			}
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
			        request.getParameter("currentPage"), "1"));
			PageHelper.startPage(currentPage, 50);
			List<ShopMonthReport> reports = this.shopMonthReportService.findAllMonthReport(personUser.getCityId());
			PageInfo<ShopMonthReport> info = new PageInfo<ShopMonthReport>(reports);
			Page<ShopMonthReport> page = new Page<ShopMonthReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			view.addObject("page", page);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			Page<ShopMonthReport> page = new Page<ShopMonthReport>(1, 1, 50, new ArrayList<ShopMonthReport>());
			view.addObject("page", page);
		}
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping(value = "exportExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportExcel(HttpServletRequest request,
			HttpServletResponse response) {
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "你没有设置默认城市";
		}
		String FilePutPath = "D:\\单店月报.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("单店月报.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			String date = request.getParameter("queryDate");
			String username = request.getParameter("userName");
			String param = request.getParameter("param");
			if(StringUtils.isEmpty(param)){
				param = "0";
			}
			Date startTime = null;
			Date endTime = null;
			if(StringUtils.isNotEmpty(date) && !date.equals("0")){
				Date date2 = DateUtil.parseDateStr(date, "yyyy-MM");
				startTime = DateUtil.getFirstDayOfMonth(date2);
				endTime = DateUtil.getLastDayOfMonth(date2);
			}
			int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
			List<ShopMonthReport> shopDailyReports = this.shopMonthReportService.findByCondition(startTime,endTime,username,param,null,reseauId,personUser.getCityId());
			if (shopDailyReports != null && !shopDailyReports.isEmpty()) {
				sheet.mergeCells(0, 0, 25, 0);// 合并标题单元格
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, "单店月报", format1);
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
				//sheet.addCell(new Label(4, 1, "折扣", cellFormat1));
				sheet.addCell(new Label(4, 1, "消费额", cellFormat1));
				sheet.addCell(new Label(5, 1, "同比", cellFormat1));
				
				sheet.addCell(new Label(6, 1, "日均", cellFormat1));
				sheet.addCell(new Label(7, 1, "消费笔数", cellFormat1));
				sheet.addCell(new Label(8, 1, "笔均", cellFormat1));
				sheet.addCell(new Label(9, 1, "消费人数", cellFormat1));
				sheet.addCell(new Label(10, 1, "人均", cellFormat1));
				sheet.addCell(new Label(11, 1, "复购率", cellFormat1));
				sheet.addCell(new Label(12, 1, "月初库存", cellFormat1));
				sheet.addCell(new Label(13, 1, "月底库存", cellFormat1));
				sheet.addCell(new Label(14, 1, "周转率", cellFormat1));
				sheet.addCell(new Label(15, 1, "利润", cellFormat1));
				sheet.addCell(new Label(16, 1, "利润率", cellFormat1));
				
				sheet.addCell(new Label(17, 1, "采购额", cellFormat1));
				sheet.addCell(new Label(18, 1, "订单数", cellFormat1));
				sheet.addCell(new Label(19, 1, "退货单数", cellFormat1));
				sheet.addCell(new Label(20, 1, "消耗额", cellFormat1));
				sheet.addCell(new Label(21, 1, "损耗额", cellFormat1));
				sheet.addCell(new Label(22, 1, "补贴额", cellFormat1));
				sheet.addCell(new Label(23, 1, "损耗率", cellFormat1));
				sheet.addCell(new Label(24, 1, "id", cellFormat1));
				sheet.addCell(new Label(25, 1, "客户须知", cellFormat1));
//				sheet.setColumnView(1, 25);// 根据内容自动设置列宽
//				sheet.setColumnView(2, 10);
//				sheet.setColumnView(3, 15);
//				sheet.setColumnView(4, 15);
//				sheet.setColumnView(5, 15);
//				sheet.setColumnView(6, 25);
				for (int i = 0; i < shopDailyReports.size(); i++) {
					sheet.addCell(new Number(0, i + 2, i + 1));
					sheet.addCell(new Label(1, i + 2, shopDailyReports.get(i).getRegion()));
					sheet.addCell(new Label(2, i + 2, shopDailyReports.get(i).getUserName()));
					sheet.addCell(new Label(3, i + 2, DateUtil.formatDate(shopDailyReports.get(i).getSumDate(), "yyyy-MM")));
					
					Long consume=Long.parseLong(shopDailyReports.get(i).getConsumeFee().toString());
					sheet.addCell(new Number(4, i + 2,consume==null?0:(new BigDecimal(NumberTool.toYuanNumber(consume))).doubleValue()));
					BigDecimal beforeLastWeekExpand = shopDailyReports.get(i).getBeforeExpandFee();
					if(null!=beforeLastWeekExpand){
						sheet.addCell(new Number(5, i + 2, Double.parseDouble(beforeLastWeekExpand.toString()) ));
					}else{
						sheet.addCell(new Label(5, i + 2, ""));
					}
				
					
					Long avgFee = shopDailyReports.get(i).getAvgFee();
					if(null!=avgFee){
						sheet.addCell(new Number(6, i + 2,avgFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(avgFee))).doubleValue()));
					}
					
					Integer pen = shopDailyReports.get(i).getConsumePen();
					if(pen!=null){
						sheet.addCell(new Number(7, i + 2, Integer.parseInt(shopDailyReports.get(i).getConsumePen().toString()) ));
					}else{
						sheet.addCell(new Label(7, i + 2, ""));
					}
					Long avgPenFee=shopDailyReports.get(i).getAvgPenFee();
					sheet.addCell(new Number(8, i + 2,avgPenFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(avgPenFee))).doubleValue()));
					sheet.addCell(new Number(9, i + 2, Integer.parseInt(shopDailyReports.get(i).getConsumeNum().toString()) ));
					Long AvgManFee=shopDailyReports.get(i).getAvgManFee();
					sheet.addCell(new Number(10, i + 2,AvgManFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(AvgManFee))).doubleValue()));
					BigDecimal repeatBuyRate = shopDailyReports.get(i).getRepeatBuyRate();
					if(null!=repeatBuyRate){
						sheet.addCell(new Number(11, i + 2, Double.parseDouble(repeatBuyRate.toString()) ));
					}else{
						sheet.addCell(new Label(11, i + 2, ""));
					}
					Long firststock = shopDailyReports.get(i).getMonthFirstStock();
					sheet.addCell(new Number(12, i + 2,firststock==null?0:(new BigDecimal(NumberTool.toYuanNumber(firststock))).doubleValue()));
					Long stock = shopDailyReports.get(i).getStock();
					sheet.addCell(new Number(13, i + 2,stock==null?0:(new BigDecimal(NumberTool.toYuanNumber(stock))).doubleValue()));
					BigDecimal turnoverRate = shopDailyReports.get(i).getTurnoverRate();
					if(null!=turnoverRate){
						sheet.addCell(new Number(14, i + 2, Double.parseDouble(turnoverRate.toString()) ));
					}else{
						sheet.addCell(new Label(14, i + 2, ""));
					}
					
					Long profit = shopDailyReports.get(i).getProfit();
					sheet.addCell(new Number(15, i + 2,profit==null?0:(new BigDecimal(NumberTool.toYuanNumber(profit))).doubleValue()));
					BigDecimal profitRate = shopDailyReports.get(i).getProfitRate();
					sheet.addCell(new Number(16, i + 2,profitRate==null?0:profitRate.doubleValue()));
					
					Long SourcingFee=shopDailyReports.get(i).getSourcingFee();
					sheet.addCell(new Number(17, i + 2,SourcingFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(SourcingFee))).doubleValue()));
					Integer orderNum = shopDailyReports.get(i).getOrderNum();
					sheet.addCell(new Number(18, i + 2,orderNum==null?0:orderNum));
					Integer RefundNum = shopDailyReports.get(i).getRefundNum();
					sheet.addCell(new Number(19, i + 2,RefundNum==null?0:RefundNum));
					Long expend=Long.parseLong(shopDailyReports.get(i).getExpandFee().toString());
					sheet.addCell(new Number(20, i + 2,expend==null?0:(new BigDecimal(NumberTool.toYuanNumber(expend))).doubleValue()));
					Long wastage = shopDailyReports.get(i).getWastage();
					sheet.addCell(new Number(21, i + 2,wastage==null?0:(new BigDecimal(NumberTool.toYuanNumber(wastage))).doubleValue()));
					Long subsidy = shopDailyReports.get(i).getSubsidy();
					sheet.addCell(new Number(22, i + 2,subsidy==null?0:(new BigDecimal(NumberTool.toYuanNumber(subsidy))).doubleValue()));
					Integer lossPercent = shopDailyReports.get(i).getLossPercent();
					sheet.addCell(new Number(23, i + 2,lossPercent==null?0:lossPercent));
					sheet.addCell(new Number(24, i + 2,shopDailyReports.get(i).getUserId()));
					sheet.addCell(new Label(25, i + 2,shopDailyReports.get(i).getIsnew() == 1 ? "新客户" : "老客户"));
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
	
	
	@RequestMapping(value = "exportnetExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportnetExcel(HttpServletRequest request,
			HttpServletResponse response) {
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "你没有设置默认城市";
		}
		String FilePutPath = "D:\\网格月报.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("网格月报.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			String querydate = request.getParameter("queryDate");
			int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
			List<ShopMonthReport> reports = this.shopMonthReportService.findNetMonthReport(reseauId,querydate,personUser.getCityId());
			if (reports != null && !reports.isEmpty()) {
				sheet.mergeCells(0, 0, 31, 0);// 合并标题单元格
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, "网格月报", format1);
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
				sheet.addCell(new Label(3, 1, "消费店铺数", cellFormat1));
				sheet.addCell(new Label(4, 1, "日期", cellFormat1));
				//sheet.addCell(new Label(4, 1, "折扣", cellFormat1));
				sheet.addCell(new Label(5, 1, "消费总额", cellFormat1));
				sheet.addCell(new Label(6, 1, "店均消费额", cellFormat1));
				
				sheet.addCell(new Label(7, 1, "店日均消费额", cellFormat1));
				
				sheet.addCell(new Label(8, 1, "消费笔数", cellFormat1));
				sheet.addCell(new Label(9, 1, "笔均", cellFormat1));
				sheet.addCell(new Label(10, 1, "消费人数", cellFormat1));
				sheet.addCell(new Label(11, 1, "人均", cellFormat1));
				sheet.addCell(new Label(12, 1, "复购率", cellFormat1));
				sheet.addCell(new Label(13, 1, "月初库存", cellFormat1));
				sheet.addCell(new Label(14, 1, "月底库存", cellFormat1));
				sheet.addCell(new Label(15, 1, "周转率", cellFormat1));
				sheet.addCell(new Label(16, 1, "利润", cellFormat1));
				sheet.addCell(new Label(17, 1, "利润率", cellFormat1));
				
				sheet.addCell(new Label(18, 1, "采购额", cellFormat1));
				sheet.addCell(new Label(19, 1, "订单数", cellFormat1));
				sheet.addCell(new Label(20, 1, "退货单数", cellFormat1));
				sheet.addCell(new Label(21, 1, "消耗额", cellFormat1));
				sheet.addCell(new Label(22, 1, "损耗额", cellFormat1));
				sheet.addCell(new Label(23, 1, "补贴额", cellFormat1));
				sheet.addCell(new Label(24, 1, "损耗率", cellFormat1));
				sheet.addCell(new Label(25, 1, "新客户数", cellFormat1));
				sheet.addCell(new Label(26, 1, "老客户数", cellFormat1));
				sheet.addCell(new Label(27, 1, "新客户消耗额", cellFormat1));
				sheet.addCell(new Label(28, 1, "老客户消耗额", cellFormat1));
				sheet.addCell(new Label(29, 1, "新客户消费额", cellFormat1));
				sheet.addCell(new Label(30, 1, "老客户消费额", cellFormat1));
				sheet.addCell(new Label(31, 1, "id", cellFormat1));
//				sheet.setColumnView(1, 25);// 根据内容自动设置列宽
//				sheet.setColumnView(2, 10);
//				sheet.setColumnView(3, 15);
//				sheet.setColumnView(4, 15);
//				sheet.setColumnView(5, 15);
//				sheet.setColumnView(6, 25);
				for (int i = 0; i < reports.size(); i++) {
					sheet.addCell(new Number(0, i + 2, i + 1));
					sheet.addCell(new Label(1, i + 2, reports.get(i).getRegion()));
					Integer openShopNum = reports.get(i).getOpenShopNum();
					if(openShopNum!=null){
						sheet.addCell(new Number(2, i + 2, Integer.parseInt(reports.get(i).getOpenShopNum().toString()) ));
					}else{
						sheet.addCell(new Label(2, i + 2, ""));
					}
					Integer consumeShopNum = reports.get(i).getConsumeShopNum();
					if(consumeShopNum!=null){
						sheet.addCell(new Number(3, i + 2, Integer.parseInt(reports.get(i).getConsumeShopNum().toString()) ));
					}else{
						sheet.addCell(new Label(3, i + 2, ""));
					}
					sheet.addCell(new Label(4, i + 2, DateUtil.formatDate(reports.get(i).getSumDate(), "yyyy-MM")));
					
					Long consume=reports.get(i).getConsumeFee();
					sheet.addCell(new Number(5, i + 2,consume==null?0:(new BigDecimal(NumberTool.toYuanNumber(consume))).doubleValue()));
					Long avgShopFee=reports.get(i).getAvgShopFee();
					sheet.addCell(new Number(6, i + 2,avgShopFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(avgShopFee))).doubleValue()));
					
				
					
					Long avgFee = reports.get(i).getAvgFee();
					if(null!=avgFee){
						sheet.addCell(new Number(7, i + 2,avgFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(avgFee))).doubleValue()));
					}
					
					
					Integer pen = reports.get(i).getConsumePen();
					if(pen!=null){
						sheet.addCell(new Number(8, i + 2, Integer.parseInt(reports.get(i).getConsumePen().toString()) ));
					}else{
						sheet.addCell(new Label(8, i + 2, ""));
					}
					Long avgPenFee=reports.get(i).getAvgPenFee();
					sheet.addCell(new Number(9, i + 2,avgPenFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(avgPenFee))).doubleValue()));
					sheet.addCell(new Number(10, i + 2, Integer.parseInt(reports.get(i).getConsumeNum().toString()) ));
					Long AvgManFee=reports.get(i).getAvgManFee();
					sheet.addCell(new Number(11, i + 2,AvgManFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(AvgManFee))).doubleValue()));
					BigDecimal repeatBuyRate = reports.get(i).getRepeatBuyRate();
					if(null!=repeatBuyRate){
						sheet.addCell(new Number(12, i + 2, Double.parseDouble(repeatBuyRate.toString()) ));
					}else{
						sheet.addCell(new Label(12, i + 2, ""));
					}
					Long firststock = reports.get(i).getMonthFirstStock();
					sheet.addCell(new Number(13, i + 2,firststock==null?0:(new BigDecimal(NumberTool.toYuanNumber(firststock))).doubleValue()));
					Long stock = reports.get(i).getStock();
					sheet.addCell(new Number(14, i + 2,stock==null?0:(new BigDecimal(NumberTool.toYuanNumber(stock))).doubleValue()));
					BigDecimal turnoverRate = reports.get(i).getTurnoverRate();
					if(null!=turnoverRate){
						sheet.addCell(new Number(15, i + 2, Double.parseDouble(turnoverRate.toString()) ));
					}else{
						sheet.addCell(new Label(15, i + 2, ""));
					}
					
					Long profit = reports.get(i).getProfit();
					sheet.addCell(new Number(16, i + 2,profit==null?0:(new BigDecimal(NumberTool.toYuanNumber(profit))).doubleValue()));
					BigDecimal profitRate = reports.get(i).getProfitRate();
					sheet.addCell(new Number(17, i + 2,profitRate==null?0:profitRate.doubleValue()));
					
					Long SourcingFee=reports.get(i).getSourcingFee();
					sheet.addCell(new Number(18, i + 2,SourcingFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(SourcingFee))).doubleValue()));
					Integer orderNum = reports.get(i).getOrderNum();
					sheet.addCell(new Number(19, i + 2,orderNum==null?0:orderNum));
					Integer RefundNum = reports.get(i).getRefundNum();
					sheet.addCell(new Number(20, i + 2,RefundNum==null?0:RefundNum));
					Long expend=Long.parseLong(reports.get(i).getExpandFee().toString());
					sheet.addCell(new Number(21, i + 2,expend==null?0:(new BigDecimal(NumberTool.toYuanNumber(expend))).doubleValue()));
					Long wastage = reports.get(i).getWastage();
					sheet.addCell(new Number(22, i + 2,wastage==null?0:(new BigDecimal(NumberTool.toYuanNumber(wastage))).doubleValue()));
					Long subsidy = reports.get(i).getSubsidy();
					sheet.addCell(new Number(23, i + 2,subsidy==null?0:(new BigDecimal(NumberTool.toYuanNumber(subsidy))).doubleValue()));
					Integer lossPercent = reports.get(i).getLossPercent();
					sheet.addCell(new Number(24, i + 2,lossPercent==null?0:lossPercent));

					Integer newopenShopNum = reports.get(i).getNewopenShopNum();
					if(newopenShopNum!=null){
						sheet.addCell(new Number(25, i + 2, Integer.parseInt(newopenShopNum.toString()) ));
					}else{
						sheet.addCell(new Label(25, i + 2, ""));
					}

					Integer oldopenShopNum = reports.get(i).getOldopenShopNum();
					if(oldopenShopNum!=null){
						sheet.addCell(new Number(26, i + 2, Integer.parseInt(oldopenShopNum.toString()) ));
					}else{
						sheet.addCell(new Label(26, i + 2, ""));
					}

					Long newexpend=Long.parseLong(reports.get(i).getNewexpandFee().toString());
					sheet.addCell(new Number(27, i + 2,expend==null?0:(new BigDecimal(NumberTool.toYuanNumber(newexpend))).doubleValue()));

					Long oldexpend=Long.parseLong(reports.get(i).getOldexpandFee().toString());
					sheet.addCell(new Number(28, i + 2,expend==null?0:(new BigDecimal(NumberTool.toYuanNumber(oldexpend))).doubleValue()));

					Long newconsume=reports.get(i).getNewconsumeFee();
					sheet.addCell(new Number(29, i + 2,consume==null?0:(new BigDecimal(NumberTool.toYuanNumber(newconsume))).doubleValue()));

					Long oldconsume=reports.get(i).getOldconsumeFee();
					sheet.addCell(new Number(30, i + 2,consume==null?0:(new BigDecimal(NumberTool.toYuanNumber(oldconsume))).doubleValue()));


					sheet.addCell(new Number(31, i + 2,reports.get(i).getUserId()));
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
