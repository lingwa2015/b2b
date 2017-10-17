package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.ItemCategory;
import com.b2b.common.domain.ItemMonthReport;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.ItemCategoryService;
import com.b2b.service.ItemMonthReportService;
import com.b2b.service.LogService;
import com.b2b.service.ReseauService;
import com.b2b.web.util.NumberTool;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jxl.Workbook;
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


@Controller
@RequestMapping("/itemMonthReport")
public class ItemMonthReportController {
	private static final Logger logger = LoggerFactory.getLogger(ItemMonthReportController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	@Autowired
	private ItemMonthReportService itemMonthReportService;

	@Autowired
	ItemCategoryService itemCategoryService;

	@Autowired
	private ReseauService reseauService;
	
	@Autowired
	LogService logService;
	
	@RequestMapping(value = "/itemMonthReports.htm")
	@ResponseBody
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("itemReport/itemMonthReports");
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		String date = request.getParameter("queryDate");
		Date dateTime = null;
		if(StringUtils.isNotEmpty(date) && !date.equals("0")){
			Date date2 = DateUtil.parseDateStr(date, "yyyy-MM");
			dateTime = DateUtil.getFirstDayOfMonth(date2);
		}
		String itemName = request.getParameter("itemName");
		mv.addObject("itemName", itemName);
		String param = request.getParameter("param");
		if(StringUtils.isEmpty(param)){
			param = "0";
		}
		mv.addObject("param", param);
		String isnew = request.getParameter("isnew");
		mv.addObject("isnew", isnew);
		String isrecommend = request.getParameter("isrecommend");
		mv.addObject("isrecommend", isrecommend);
		mv.addObject("querydate", date);

		int onecate = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("onecate"), "0"));
		int twocate = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("twocate"), "0"));
		mv.addObject("onecate", onecate);
		mv.addObject("twocate", twocate);
		List<ItemCategory> catList = this.itemCategoryService.findAllOneCatsByCityId(personUser.getCityId());
		mv.addObject("catList", catList);
		if(onecate>0){
			List<ItemCategory> leaveCats = this.itemCategoryService.findCatByParentIdAndCityId(onecate, personUser.getCityId());
			mv.addObject("leaveCats", leaveCats);
		}

		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));

		PageHelper.startPage(currentPage, 50);
		List<ItemMonthReport> reports = this.itemMonthReportService.findItemDailyReports(dateTime,
				itemName, param, isnew, isrecommend, onecate, twocate, personUser.getCityId());
		PageInfo<ItemMonthReport> info = new PageInfo<ItemMonthReport>(reports);
		Page<ItemMonthReport> page = new Page<ItemMonthReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());

		mv.addObject("page", page);
		
//		test(dateTime);

		TestController.getMenuPoint(mv, request);
		return mv;
	}

	@RequestMapping(value = "exportExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportExcel(HttpServletRequest request,
							  HttpServletResponse response) {
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "你没有设置默认城市";
		}
		String FilePutPath = "D:\\商品月报.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
					+ new String((java.net.URLEncoder.encode("商品月报.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			String starttimeStr = request.getParameter("starttime");
			String endtimeStr = request.getParameter("endtime");
			String date = request.getParameter("queryDate");
			Date dateTime = null;
			if(StringUtils.isNotEmpty(date) && !date.equals("0")){
				Date date2 = DateUtil.parseDateStr(date, "yyyy-MM");
				dateTime = DateUtil.getFirstDayOfMonth(date2);
			}
			String param = request.getParameter("param");
			if(StringUtils.isEmpty(param)){
				param = "0";
			}
			String itemName = request.getParameter("itemName");
			String isnew = request.getParameter("isnew");
			String isrecommend = request.getParameter("isrecommend");
			int onecate = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("onecate"), "0"));
			int twocate = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("twocate"), "0"));
			List<ItemMonthReport> reports = this.itemMonthReportService.findItemDailyReports(dateTime,
					itemName, param, isnew, isrecommend, onecate, twocate, personUser.getCityId());
			if (reports != null && !reports.isEmpty()) {
				sheet.mergeCells(0, 0, 22, 0);// 合并标题单元格
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, "商品月报", format1);
				sheet.addCell(label);

				font1 = new WritableFont(WritableFont.TIMES, 11,
						WritableFont.BOLD);
				WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
				// 设置背景颜色;
				cellFormat1.setBackground(jxl.format.Colour.GRAY_50);
				// 设置文字居中对齐方式;
				cellFormat1.setAlignment(jxl.format.Alignment.CENTRE);
				// 设置垂直居中;
				cellFormat1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				sheet.addCell(new Label(0 , 1, "ID", cellFormat1));
				sheet.addCell(new Label(1 , 1, "日期", cellFormat1));
				sheet.addCell(new Label(2 , 1, "城市", cellFormat1));
				sheet.addCell(new Label(3 , 1, "一级类目", cellFormat1));
				sheet.addCell(new Label(4 , 1, "二级类目", cellFormat1));
				sheet.addCell(new Label(5 , 1, "品牌", cellFormat1));
				sheet.addCell(new Label(6 , 1, "商品名称", cellFormat1));
				sheet.addCell(new Label(7 , 1, "规格", cellFormat1));
				sheet.addCell(new Label(8 , 1, "单价", cellFormat1));
				sheet.addCell(new Label(9 , 1, "数量", cellFormat1));
				sheet.addCell(new Label(10 , 1, "消费数量", cellFormat1));
				sheet.addCell(new Label(11 , 1, "在售店铺数", cellFormat1));
				sheet.addCell(new Label(12 , 1, "消费金额", cellFormat1));
				sheet.addCell(new Label(13 , 1, "订单数量", cellFormat1));
				sheet.addCell(new Label(14 , 1, "订单店铺数", cellFormat1));
				sheet.addCell(new Label(15 , 1, "订单金额", cellFormat1));
				sheet.addCell(new Label(16 , 1, "退货店铺数", cellFormat1));
				sheet.addCell(new Label(17 , 1, "退货数量", cellFormat1));
				sheet.addCell(new Label(18 , 1, "利润率", cellFormat1));
				sheet.addCell(new Label(19 , 1, "周转率", cellFormat1));
//				日均消费数量
				sheet.addCell(new Label(20 , 1, "日均消费数量", cellFormat1));
//				入库单数量
				sheet.addCell(new Label(21 , 1, "入库单数量", cellFormat1));
//				入库数量
				sheet.addCell(new Label(22 , 1, "入库数量", cellFormat1));

				for (int i = 0; i < reports.size(); i++) {
					sheet.addCell(new Number(0, i + 2, Integer.parseInt(reports.get(i).getItemId().toString())));
					sheet.addCell(new Label(1, i + 2, DateUtil.formatDate(reports.get(i).getSumDate(), "yyyy-MM")));
					sheet.addCell(new Label(2, i + 2, reports.get(i).getCity()));
					sheet.addCell(new Label(3, i + 2, reports.get(i).getCategoryName()));
					sheet.addCell(new Label(4, i + 2, reports.get(i).getCategorylevelName()));
					sheet.addCell(new Label(5, i + 2, reports.get(i).getBrand()));
					sheet.addCell(new Label(6, i + 2, reports.get(i).getItemName()));
					sheet.addCell(new Label(7, i + 2, reports.get(i).getSize()));
					Long consume = Long.parseLong(reports.get(i).getPrice().toString());
					sheet.addCell(new Number(8, i + 2,consume == null ? 0:(new BigDecimal(NumberTool.toYuanNumber(consume))).doubleValue()));
					sheet.addCell(new Number(9, i + 2, Integer.parseInt(reports.get(i).getOrderItemNum().toString())));
					sheet.addCell(new Number(10, i + 2, Integer.parseInt(reports.get(i).getShopOrderItemNum().toString())));
					sheet.addCell(new Number(11, i + 2, Integer.parseInt(reports.get(i).getShopNum().toString())));
					Long shopOrderItemTotal = Long.parseLong(reports.get(i).getShopOrderItemTotal().toString());
					sheet.addCell(new Number(12, i + 2,shopOrderItemTotal == null ? 0:(new BigDecimal(NumberTool.toYuanNumber(shopOrderItemTotal))).doubleValue()));
					sheet.addCell(new Number(13, i + 2, Integer.parseInt(reports.get(i).getOrderNum().toString())));
					sheet.addCell(new Number(14, i + 2, Integer.parseInt(reports.get(i).getOrderShopNum().toString())));
					Long orderShopTotal = Long.parseLong(reports.get(i).getOrderShopTotal().toString());
					sheet.addCell(new Number(15, i + 2, orderShopTotal == null ? 0:(new BigDecimal(NumberTool.toYuanNumber(orderShopTotal))).doubleValue()));
					sheet.addCell(new Number(16, i + 2, Integer.parseInt(reports.get(i).getOrderRefundShopNum().toString())));
					sheet.addCell(new Number(17, i + 2, Integer.parseInt(reports.get(i).getOrderRefundNum().toString())));
					BigDecimal profitRate = reports.get(i).getProfitRate();
					sheet.addCell(new Number(18, i + 2,profitRate == null ? 0 : profitRate.doubleValue()));
					BigDecimal shelfSalesRate = reports.get(i).getShelfSalesRate();
					sheet.addCell(new Number(19, i + 2, shelfSalesRate== null ? 0 : shelfSalesRate.doubleValue()));
					BigDecimal avgNun = reports.get(i).getAvgNum();
					sheet.addCell(new Number(20, i + 2, avgNun== null ? 0 : avgNun.doubleValue()));
					sheet.addCell(new Number(21, i + 2, Integer.parseInt(reports.get(i).getStorageOrderNum().toString())));
					sheet.addCell(new Number(22, i + 2, Integer.parseInt(reports.get(i).getStorageNum().toString())));
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

	private void test(Date starttime){


		List<ItemMonthReport> reports = this.itemMonthReportService.findAllItem();


		Integer orderItemNum = itemMonthReportService.selectOrderItemNum(1, starttime);
		ItemMonthReport  r1= itemMonthReportService.selectShopOrderInfo(1, starttime);
		Integer i1 = itemMonthReportService.selectShopNum(1, starttime);
		ItemMonthReport  r2 = itemMonthReportService.selectOrderInfo(1, starttime);
		Integer i2 = itemMonthReportService.selectOrderShopNum(1, starttime);
		ItemMonthReport  r3 = itemMonthReportService.selectRefundInfo(1, starttime);
		Integer i3 = itemMonthReportService.selectOrderRefundShopNum(1, starttime);
		Integer i4 = itemMonthReportService.selectAllShopNum(1, starttime);
		Integer i5 = itemMonthReportService.selectStockNum(1, starttime);
	}
}
