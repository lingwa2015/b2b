package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.Reseau;
import com.b2b.common.domain.ShopItemMonthReport;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.LogService;
import com.b2b.service.ReseauService;
import com.b2b.service.ShopItemMonthReportService;
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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/shopItemMonthReport")
public class ShopItemMonthReportController {
	private static final Logger logger = LoggerFactory.getLogger(ShopItemMonthReportController.class);
	@Autowired
	private ShopItemMonthReportService shopItemMonthReportService;

	@Autowired
	private ReseauService reseauService;
	
	@Autowired
	LogService logService;
	
	@RequestMapping(value = "/shopItemMonthReportList.htm")
	@ResponseBody
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("shopItemMonthReport/list");
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		String date = request.getParameter("queryDate");
		Date dateTime = null;
		if(StringUtils.isNotEmpty(date) && !date.equals("0")){
			Date date2 = DateUtil.parseDateStr(date, "yyyy-MM");
			dateTime = DateUtil.getFirstDayOfMonth(date2);
		}
		String itemName = request.getParameter("itemName");

		String username = request.getParameter("userName");
		//新品
		String isnew = request.getParameter("isnew");
		String param = request.getParameter("param");
		if(StringUtils.isEmpty(param)){
			param = "0";
		}
		mv.addObject("param", param);

		//网格
		int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
		mv.addObject("reseauId", reseauId);
		mv.addObject("querydate", date);
		mv.addObject("itemName", itemName);
		mv.addObject("username", username);
		mv.addObject("isnew", isnew);

		PageHelper.startPage(currentPage, 50);
		List<ShopItemMonthReport> reports = this.shopItemMonthReportService.findShopItemMontList(dateTime, itemName, username, reseauId,personUser.getCityId(), isnew, param);
		PageInfo<ShopItemMonthReport> info = new PageInfo<ShopItemMonthReport>(reports);
		Page<ShopItemMonthReport> page = new Page<ShopItemMonthReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		List<Reseau> list = this.reseauService.findAllByCityId(personUser.getCityId());
		mv.addObject("reseaus", list);
		mv.addObject("page", page);
		TestController.getMenuPoint(mv, request);
//		test();
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
		String FilePutPath = "D:\\商品店铺月报.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
					+ new String((java.net.URLEncoder.encode("商品店铺月报.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			String username = request.getParameter("userName");
			String date = request.getParameter("queryDate");
			String param = request.getParameter("param");
			if(StringUtils.isEmpty(param)){
				param = "0";
			}
			Date dateTime = null;
			if(StringUtils.isNotEmpty(date) && !date.equals("0")){
				Date date2 = DateUtil.parseDateStr(date, "yyyy-MM");
				dateTime = DateUtil.getFirstDayOfMonth(date2);
			}
			String itemName = request.getParameter("itemName");
			//新品
			String isnew = request.getParameter("isnew");

			int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
			List<ShopItemMonthReport> shopItemMonthReports = this.shopItemMonthReportService.findShopItemMontList(dateTime, itemName, username, reseauId,personUser.getCityId(), isnew, param);
			if (shopItemMonthReports != null && !shopItemMonthReports.isEmpty()) {
				sheet.mergeCells(0, 0, 12, 0);// 合并标题单元格
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, "商品店铺月报", format1);
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
				sheet.addCell(new Label(0	, 1, "编号",cellFormat1));
				sheet.addCell(new Label(1	, 1, "区域",cellFormat1));
				sheet.addCell(new Label(2	, 1, "客户",cellFormat1));
				sheet.addCell(new Label(3	, 1, "月份",cellFormat1));
				sheet.addCell(new Label(4	, 1, "一级类目",cellFormat1));
				sheet.addCell(new Label(5	, 1, "二级类目",cellFormat1));
				sheet.addCell(new Label(6	, 1, "商品名称",cellFormat1));
				sheet.addCell(new Label(7	, 1, "规格",cellFormat1));
				sheet.addCell(new Label(8	, 1, "单价",cellFormat1));
				sheet.addCell(new Label(9	, 1, "数量",cellFormat1));
				sheet.addCell(new Label(10	, 1, "消费数量",cellFormat1));
				sheet.addCell(new Label(11	, 1, "退货数量",cellFormat1));
				sheet.addCell(new Label(12	, 1, "周转率",cellFormat1));


				for (int i = 0; i < shopItemMonthReports.size(); i++) {
					sheet.addCell(new Number(0, i + 2, i + 1));
					sheet.addCell(new Label(1, i + 2, shopItemMonthReports.get(i).getRegion()));
					sheet.addCell(new Label(2, i + 2, shopItemMonthReports.get(i).getUserName()));
					sheet.addCell(new Label(3, i + 2, DateUtil.formatDate(shopItemMonthReports.get(i).getSumDate(), "yyyy-MM")));
					sheet.addCell(new Label(4, i + 2, shopItemMonthReports.get(i).getCategoryName()));
					sheet.addCell(new Label(5, i + 2, shopItemMonthReports.get(i).getCategorylevelName()));
					sheet.addCell(new Label(6, i + 2, shopItemMonthReports.get(i).getItemName()));
					sheet.addCell(new Label(7, i + 2, shopItemMonthReports.get(i).getSize()));
					Long consume=Long.parseLong(shopItemMonthReports.get(i).getPrice().toString());
					sheet.addCell(new Number(8, i + 2,consume==null?0:(new BigDecimal(NumberTool.toYuanNumber(consume))).doubleValue()));
					sheet.addCell(new Number(9, i + 2, Integer.parseInt(shopItemMonthReports.get(i).getOrderNum().toString())));
					sheet.addCell(new Number(10, i + 2, Integer.parseInt(shopItemMonthReports.get(i).getItemNum().toString())));
					sheet.addCell(new Number(11, i + 2, Integer.parseInt(shopItemMonthReports.get(i).getRefundNum().toString())));
					BigDecimal turnoverRate = shopItemMonthReports.get(i).getTurnoverRate();
					sheet.addCell(new Number(12, i + 2,turnoverRate==null?0:turnoverRate.doubleValue()));
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

	private void test() {
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		Date date = calendar.getTime();
		List<ShopItemMonthReport> s =  shopItemMonthReportService.findShopItemsByDate(date, 1);
		Integer a1 =  shopItemMonthReportService.selectShopOrderItemSaleCount(817, 205, date);
		Integer s2 = shopItemMonthReportService.selectShopOrderItemRefundNum(817, 205, date);
		Integer s3 = shopItemMonthReportService.selectShopOrderItemStockNum(817, 205, date);
	}
}
