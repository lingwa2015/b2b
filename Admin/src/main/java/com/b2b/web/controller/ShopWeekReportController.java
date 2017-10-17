package com.b2b.web.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.common.domain.Reseau;
import com.b2b.common.domain.ShopDailyReport;
import com.b2b.common.domain.ShopMonthReport;
import com.b2b.common.domain.ShopWeekReport;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.ReseauService;
import com.b2b.service.ShopWeekReportService;
import com.b2b.web.util.NumberTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RequestMapping("shopWeekReport")
@Controller
public class ShopWeekReportController {
	private static final Logger logger = LoggerFactory
			.getLogger(ShopWeekReportController.class);
	@Autowired
	ShopWeekReportService shopWeekReportService;
	@Autowired
	ReseauService reseauService;
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	@RequestMapping("shopWeekReportList.htm")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView view = new ModelAndView("shopWeekReport/list");
		try {
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));
			
			String starttimeStr = request.getParameter("starttime");
			String param = request.getParameter("param");
			if(StringUtils.isEmpty(param)){
				param = "0";
			}
			view.addObject("param", param);
			int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
			view.addObject("reseauId", reseauId);
			Date starttime = null;
			if (StringUtils.isNotBlank(starttimeStr)) {
				Date time = DateUtil.parseDateStr(starttimeStr, DATE_FORMAT_YMD);
				int dayForWeek = DateUtil.dayForWeek(time);
				int ducday = dayForWeek -1;
				starttime = DateUtil.dateAdd("d", -ducday, time);
				view.addObject("starttime", starttimeStr);
			}
			String userName = request.getParameter("userName");
			view.addObject("userName", userName);
			PageHelper.startPage(currentPage, 50);
			List<ShopWeekReport> shopDailyReports= this.shopWeekReportService.findByCondition(userName,starttime,param,reseauId);
			PageInfo<ShopWeekReport> info = new PageInfo<ShopWeekReport>(shopDailyReports);
			Page<ShopWeekReport> page = new Page<ShopWeekReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			List<Reseau> list = this.reseauService.findAll();
			view.addObject("reseaus", list);
			view.addObject("page", page);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			Page<ShopWeekReport> page = new Page<ShopWeekReport>(1, 1, 50, new ArrayList<ShopWeekReport>());
			view.addObject("page", page);
		}
		return view;
	}
	
	@RequestMapping(value = "exportExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportExcel(HttpServletRequest request,
			HttpServletResponse response) {
		String FilePutPath = "D:\\单店周报.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("单店周报.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			String starttimeStr = request.getParameter("starttime");
			String userName = request.getParameter("userName");
			String param = request.getParameter("param");
			int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
			Date starttime = null;
			if (StringUtils.isNotBlank(starttimeStr)) {
				Date time = DateUtil.parseDateStr(starttimeStr, DATE_FORMAT_YMD);
				int dayForWeek = DateUtil.dayForWeek(time);
				int ducday = dayForWeek -1;
				starttime = DateUtil.dateAdd("d", -ducday, time);
			}
			List<ShopWeekReport> shopDailyReports= this.shopWeekReportService.findByCondition(userName,starttime,param,reseauId);
			if (shopDailyReports != null && !shopDailyReports.isEmpty()) {
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
				Label label = new Label(0, 0, "单店周报", format1);
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
				sheet.addCell(new Label(7, 1, "消耗额", cellFormat1));
				sheet.addCell(new Label(8, 1, "日均", cellFormat1));
				sheet.addCell(new Label(9, 1, "消费笔数", cellFormat1));
				sheet.addCell(new Label(10, 1, "消费人数", cellFormat1));
				sheet.addCell(new Label(11, 1, "人均", cellFormat1));

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
					sheet.addCell(new Label(3, i + 2, DateUtil.formatDate(shopDailyReports.get(i).getSumdate(), "yyyy-MM-dd")+"~"+DateUtil.formatDate(shopDailyReports.get(i).getEnddate(), "yyyy-MM-dd") ));
					sheet.addCell(new Number(4, i + 2, Double.parseDouble(shopDailyReports.get(i).getDiscount().toString()) ));
					
					Long consume=Long.parseLong(shopDailyReports.get(i).getConsumeFee().toString());
					sheet.addCell(new Number(5, i + 2,consume==null?0:(new BigDecimal(NumberTool.toYuanNumber(consume))).doubleValue()));
					BigDecimal beforeLastWeekExpand = shopDailyReports.get(i).getBeforeConsumeFee();
					if(null!=beforeLastWeekExpand){
						sheet.addCell(new Number(6, i + 2, Double.parseDouble(beforeLastWeekExpand.toString()) ));
					}else{
						sheet.addCell(new Label(6, i + 2, ""));
					}
					Long expend=Long.parseLong(shopDailyReports.get(i).getExpendFee().toString());
					sheet.addCell(new Number(7, i + 2,expend==null?0:(new BigDecimal(NumberTool.toYuanNumber(expend))).doubleValue()));
					
					Long avgFee = shopDailyReports.get(i).getAvgFee();
					if(null!=avgFee){
						sheet.addCell(new Number(8, i + 2,avgFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(avgFee))).doubleValue()));
					}
					
					Integer pen = shopDailyReports.get(i).getConsumePen();
					if(pen!=null){
						sheet.addCell(new Number(9, i + 2, Integer.parseInt(shopDailyReports.get(i).getConsumePen().toString()) ));
					}else{
						sheet.addCell(new Label(9, i + 2, ""));
					}
					sheet.addCell(new Number(10, i + 2, Integer.parseInt(shopDailyReports.get(i).getConsumeNum().toString()) ));
					Long AvgManFee=shopDailyReports.get(i).getAvgManFee();
					sheet.addCell(new Number(11, i + 2,AvgManFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(AvgManFee))).doubleValue()));
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
