package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.Reseau;
import com.b2b.common.domain.ShopDailyReport;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.ReseauService;
import com.b2b.service.ShopDailyReportService;
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

@RequestMapping("shopDailyReport")
@Controller
public class ShopDailyReportController {
	
	private static final Logger logger = LoggerFactory
			.getLogger(ShopDailyReportController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	@Autowired
	ShopDailyReportService shopDailyReportService;
	@Autowired
	ReseauService reseauService;
	
	@RequestMapping(value="shopDailyReportList.htm",produces = "text/html;charset=UTF-8")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView view = new ModelAndView("shopDailyReport/list");
		try {
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return new ModelAndView("noCity");
			}
			String starttimeStr = request.getParameter("starttime");
			String endtimeStr = request.getParameter("endtime");
			String param = request.getParameter("param");
			view.addObject("param", param);
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
			String userName = request.getParameter("userName");
			view.addObject("userName", userName);
			PageHelper.startPage(currentPage, 50);
			List<ShopDailyReport> shopDailyReports= this.shopDailyReportService.findByCondition(userName,starttime,endtime,param,reseauId,personUser.getCityId());
			PageInfo<ShopDailyReport> info = new PageInfo<ShopDailyReport>(shopDailyReports);
			Page<ShopDailyReport> page = new Page<ShopDailyReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			List<Reseau> list = this.reseauService.findAllByCityId(personUser.getCityId());
			view.addObject("reseaus", list);
			view.addObject("page", page);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			Page<ShopDailyReport> page = new Page<ShopDailyReport>(1, 1, 50, new ArrayList<ShopDailyReport>());
			view.addObject("page", page);
		}
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping("netDailyReportList.htm")
	public ModelAndView netlist(HttpServletRequest request){
		ModelAndView view = new ModelAndView("shopDailyReport/netlist");
		try {
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return new ModelAndView("noCity");
			}
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
			List<ShopDailyReport> reports = this.shopDailyReportService.findNetDailyReport(starttime,endtime,reseauId,personUser.getCityId());
			PageInfo<ShopDailyReport> info = new PageInfo<ShopDailyReport>(reports);
			Page<ShopDailyReport> page = new Page<ShopDailyReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			List<Reseau> list = this.reseauService.findAllByCityId(personUser.getCityId());
			view.addObject("reseaus", list);
			view.addObject("page", page);
		} catch (NumberFormatException e) {
			Page<ShopDailyReport> page = new Page<ShopDailyReport>(1, 1, 50, new ArrayList<ShopDailyReport>());
			view.addObject("page", page);
		}
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping("allDailyReportList.htm")
	public ModelAndView alllist(HttpServletRequest request){
		ModelAndView view = new ModelAndView("shopDailyReport/alllist");
		try {
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return new ModelAndView("noCity");
			}
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));
			PageHelper.startPage(currentPage, 50);
			List<ShopDailyReport> reports = this.shopDailyReportService.findAllDailyReport(personUser.getCityId());
			PageInfo<ShopDailyReport> info = new PageInfo<ShopDailyReport>(reports);
			Page<ShopDailyReport> page = new Page<ShopDailyReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			view.addObject("page", page);
		} catch (NumberFormatException e) {
			Page<ShopDailyReport> page = new Page<ShopDailyReport>(1, 1, 50, new ArrayList<ShopDailyReport>());
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
		String FilePutPath = "D:\\单店日报.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("单店日报.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			String starttimeStr = request.getParameter("starttime");
			String endtimeStr = request.getParameter("endtime");
			String userName = request.getParameter("userName");
			String param = request.getParameter("param");
			Date starttime = null;
			Date endtime = null;
			if (StringUtils.isNotBlank(starttimeStr)) {
				starttime = DateUtil.parseDateStr(starttimeStr, DATE_FORMAT_YMD);
			}
			if (StringUtils.isNotBlank(endtimeStr)) {
				endtime = DateUtil.parseDateStr(endtimeStr, DATE_FORMAT_YMD);
			}
			int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
			List<ShopDailyReport> shopDailyReports= this.shopDailyReportService.findByCondition(userName,starttime,endtime,param,reseauId,personUser.getCityId());
			if (shopDailyReports != null && !shopDailyReports.isEmpty()) {
				sheet.mergeCells(0, 0, 10, 0);// 合并标题单元格
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, "单店日报", format1);
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
				sheet.addCell(new Label(1, 1, "区域", cellFormat1));
				sheet.addCell(new Label(2, 1, "客户", cellFormat1));
				sheet.addCell(new Label(3, 1, "日期", cellFormat1));
				sheet.addCell(new Label(4, 1, "折扣", cellFormat1));
				sheet.addCell(new Label(5, 1, "消费额", cellFormat1));
				sheet.addCell(new Label(6, 1, "同比上周", cellFormat1));
				sheet.addCell(new Label(7, 1, "消费笔数", cellFormat1));
				sheet.addCell(new Label(8, 1, "笔均", cellFormat1));
				sheet.addCell(new Label(9, 1, "消费人数", cellFormat1));
				sheet.addCell(new Label(10, 1, "人均", cellFormat1));
				sheet.addCell(new Label(11, 1, "加权消费额", cellFormat1));
				sheet.addCell(new Label(12, 1, "在架库存", cellFormat1));
				sheet.addCell(new Label(13, 1, "在架品类", cellFormat1));
				sheet.addCell(new Label(14, 1, "动销品类", cellFormat1));
				sheet.addCell(new Label(15, 1, "动销率", cellFormat1));
				sheet.addCell(new Label(16, 1, "id", cellFormat1));
//				sheet.setColumnView(1, 25);// 根据内容自动设置列宽
//				sheet.setColumnView(2, 10);
//				sheet.setColumnView(3, 15);
//				sheet.setColumnView(4, 15);
//				sheet.setColumnView(5, 15);
//				sheet.setColumnView(6, 25);
				WritableCellFormat wcf=new WritableCellFormat(NumberFormats.PERCENT_FLOAT);
				for (int i = 0; i < shopDailyReports.size(); i++) {
					sheet.addCell(new Number(0, i + 2, i + 1));
					sheet.addCell(new Label(1, i + 2, shopDailyReports.get(i).getRegion()));
					sheet.addCell(new Label(2, i + 2, shopDailyReports.get(i).getShopName()));
					sheet.addCell(new Label(3, i + 2, DateUtil.formatDate(shopDailyReports.get(i).getSumdate(), "yyyy-MM-dd") ));
					sheet.addCell(new Number(4, i + 2, Double.parseDouble(shopDailyReports.get(i).getDiscount().toString()) ));
					
					Long consume=Long.parseLong(shopDailyReports.get(i).getTotalConsume().toString());
					sheet.addCell(new Number(5, i + 2,consume==null?0:(new BigDecimal(NumberTool.toYuanNumber(consume))).doubleValue()));
					BigDecimal beforeLastWeekExpand = shopDailyReports.get(i).getBeforeLastWeekExpand();
					if(null!=beforeLastWeekExpand){
						double aa = beforeLastWeekExpand.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP).doubleValue();
						sheet.addCell(new Number(6, i + 2, beforeLastWeekExpand.divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP).doubleValue(),wcf ));
					}else{
						sheet.addCell(new Label(6, i + 2, ""));
					}
					Integer pen = shopDailyReports.get(i).getConsumePen();
					if(pen!=null){
						sheet.addCell(new Number(7, i + 2, Integer.parseInt(shopDailyReports.get(i).getConsumePen().toString()) ));
					}else{
						sheet.addCell(new Label(7, i + 2, ""));
					}
					Long avgPenFee = shopDailyReports.get(i).getAvgPenFee();
					sheet.addCell(new Number(8, i + 2,avgPenFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(avgPenFee))).doubleValue()));
					sheet.addCell(new Number(9, i + 2, Integer.parseInt(shopDailyReports.get(i).getConsumePersonNum().toString()) ));
					Long avgexpend=Long.parseLong(shopDailyReports.get(i).getAvgExpend().toString());
					sheet.addCell(new Number(10, i + 2,avgexpend==null?0:(new BigDecimal(NumberTool.toYuanNumber(avgexpend))).doubleValue()));
					Long expend=Long.parseLong(shopDailyReports.get(i).getTotalExpend().toString());
					sheet.addCell(new Number(11, i + 2,expend==null?0:(new BigDecimal(NumberTool.toYuanNumber(expend))).doubleValue()));
					Long onshelfFee = shopDailyReports.get(i).getOnshelfFee();
					sheet.addCell(new Number(12, i + 2,onshelfFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(onshelfFee))).doubleValue()));
					Integer onshelfKind = shopDailyReports.get(i).getOnshelfKind();
					if(onshelfKind!=null){
						sheet.addCell(new Number(13, i + 2, Integer.parseInt(shopDailyReports.get(i).getOnshelfKind().toString()) ));
					}else{
						sheet.addCell(new Label(13, i + 2, ""));
					}
					Integer moveKind = shopDailyReports.get(i).getMoveKind();
					if(moveKind!=null){
						sheet.addCell(new Number(14, i + 2, Integer.parseInt(shopDailyReports.get(i).getMoveKind().toString()) ));
					}else{
						sheet.addCell(new Label(14, i + 2, ""));
					}
					BigDecimal movePercent = shopDailyReports.get(i).getMovePercent();
					if(null!=movePercent){
						sheet.addCell(new Number(15, i + 2, movePercent.divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP).doubleValue(),wcf ));
					}else{
						sheet.addCell(new Label(15, i + 2, ""));
					}
					sheet.addCell(new Number(16, i + 2, shopDailyReports.get(i).getShopId()));
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
	
	
	@RequestMapping(value = "exportNetExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportNetExcel(HttpServletRequest request,
			HttpServletResponse response) {
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "你没有设置默认城市";
		}
		String FilePutPath = "D:\\网格日报.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("网格日报.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

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
			List<ShopDailyReport> shopDailyReports = this.shopDailyReportService.findNetDailyReport(starttime,endtime,reseauId,personUser.getCityId());
			if (shopDailyReports != null && !shopDailyReports.isEmpty()) {
				sheet.mergeCells(0, 0, 10, 0);// 合并标题单元格
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, "网格日报", format1);
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
				sheet.addCell(new Label(5, 1, "消费额", cellFormat1));
				sheet.addCell(new Label(6, 1, "店均消费额", cellFormat1));
				sheet.addCell(new Label(7, 1, "消费笔数", cellFormat1));
				sheet.addCell(new Label(8, 1, "店均消费笔数", cellFormat1));
				sheet.addCell(new Label(9, 1, "笔均", cellFormat1));
				sheet.addCell(new Label(10, 1, "消费人数", cellFormat1));
				sheet.addCell(new Label(11, 1, "人均", cellFormat1));
				sheet.addCell(new Label(12, 1, "加权消费额", cellFormat1));
				sheet.addCell(new Label(13, 1, "在架库存", cellFormat1));
				sheet.addCell(new Label(14, 1, "在架品类", cellFormat1));
				sheet.addCell(new Label(15, 1, "动销品类", cellFormat1));
				sheet.addCell(new Label(16, 1, "动销率", cellFormat1));
//				sheet.setColumnView(1, 25);// 根据内容自动设置列宽
//				sheet.setColumnView(2, 10);
//				sheet.setColumnView(3, 15);
//				sheet.setColumnView(4, 15);
//				sheet.setColumnView(5, 15);
//				sheet.setColumnView(6, 25);
				WritableCellFormat wcf=new WritableCellFormat(NumberFormats.PERCENT_FLOAT);
				for (int i = 0; i < shopDailyReports.size(); i++) {
					sheet.addCell(new Number(0, i + 2, i + 1));
					sheet.addCell(new Label(1, i + 2, shopDailyReports.get(i).getRegion()));
					
					Integer openShopNum = shopDailyReports.get(i).getOpenShopNum();
					if(openShopNum!=null){
						sheet.addCell(new Number(2, i + 2, Integer.parseInt(openShopNum.toString()) ));
					}else{
						sheet.addCell(new Label(2, i + 2, ""));
					}
					Integer consumeShopNum = shopDailyReports.get(i).getConsumeShopNum();
					if(consumeShopNum!=null){
						sheet.addCell(new Number(3, i + 2, Integer.parseInt(consumeShopNum.toString()) ));
					}else{
						sheet.addCell(new Label(3, i + 2, ""));
					}
					sheet.addCell(new Label(4, i + 2, DateUtil.formatDate(shopDailyReports.get(i).getSumdate(), "yyyy-MM-dd") ));
					
					Long consume=Long.parseLong(shopDailyReports.get(i).getTotalConsume().toString());
					sheet.addCell(new Number(5, i + 2,consume==null?0:(new BigDecimal(NumberTool.toYuanNumber(consume))).doubleValue()));
					Long avgShopFee = Long.parseLong(shopDailyReports.get(i).getAvgShopFee().toString());
					sheet.addCell(new Number(6, i + 2,avgShopFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(avgShopFee))).doubleValue()));
					Integer pen = shopDailyReports.get(i).getConsumePen();
					if(pen!=null){
						sheet.addCell(new Number(7, i + 2, Integer.parseInt(shopDailyReports.get(i).getConsumePen().toString()) ));
					}else{
						sheet.addCell(new Label(7, i + 2, ""));
					}
					Long avgShopPen = shopDailyReports.get(i).getAvgShopPen();
					if(avgShopPen!=null){
						sheet.addCell(new Number(8, i + 2, Integer.parseInt(shopDailyReports.get(i).getAvgShopPen().toString()) ));
					}else{
						sheet.addCell(new Label(8, i + 2, ""));
					}
					Long avgPenFee = shopDailyReports.get(i).getAvgPenFee();
					sheet.addCell(new Number(9, i + 2,avgPenFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(avgPenFee))).doubleValue()));
					sheet.addCell(new Number(10, i + 2, Integer.parseInt(shopDailyReports.get(i).getConsumePersonNum().toString()) ));
					Long avgexpend=Long.parseLong(shopDailyReports.get(i).getAvgExpend().toString());
					sheet.addCell(new Number(11, i + 2,avgexpend==null?0:(new BigDecimal(NumberTool.toYuanNumber(avgexpend))).doubleValue()));
					Long expend=Long.parseLong(shopDailyReports.get(i).getTotalExpend().toString());
					sheet.addCell(new Number(12, i + 2,expend==null?0:(new BigDecimal(NumberTool.toYuanNumber(expend))).doubleValue()));
					Long onshelfFee = shopDailyReports.get(i).getOnshelfFee();
					sheet.addCell(new Number(13, i + 2,onshelfFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(onshelfFee))).doubleValue()));
					Integer onshelfKind = shopDailyReports.get(i).getOnshelfKind();
					if(onshelfKind!=null){
						sheet.addCell(new Number(14, i + 2, Integer.parseInt(shopDailyReports.get(i).getOnshelfKind().toString()) ));
					}else{
						sheet.addCell(new Label(14, i + 2, ""));
					}
					Integer moveKind = shopDailyReports.get(i).getMoveKind();
					if(moveKind!=null){
						sheet.addCell(new Number(15, i + 2, Integer.parseInt(shopDailyReports.get(i).getMoveKind().toString()) ));
					}else{
						sheet.addCell(new Label(15, i + 2, ""));
					}
					BigDecimal movePercent = shopDailyReports.get(i).getMovePercent();
					if(null!=movePercent){
						sheet.addCell(new Number(16, i + 2, movePercent.divide(new BigDecimal(100),4,BigDecimal.ROUND_HALF_UP).doubleValue(),wcf ));
					}else{
						sheet.addCell(new Label(16, i + 2, ""));
					}
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
