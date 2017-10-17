package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
import com.b2b.page.Page;
import com.b2b.service.ReseauService;
import com.b2b.service.ShopItemService;
import com.b2b.service.ShopOrderService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RequestMapping("actual")
@Controller
public class ActualController {
	
	private static final Logger logger = LoggerFactory.getLogger(ActualController.class);

	@Autowired
	private ShopOrderService shopOrderService;
	
	@Autowired
	private ShopItemService shopItemService;
	
	@Autowired
	ReseauService reseauService;
	
	@RequestMapping("actualReportList.htm")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView view = new ModelAndView("actualReport/list");
		try {
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return new ModelAndView("noCity");
			}
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));
			String name = request.getParameter("name");
			view.addObject("name", name);
			String shopname = request.getParameter("shopname");
			view.addObject("shopname", shopname);
			String itemName = request.getParameter("itemName");
			view.addObject("itemName", itemName);
			String param = request.getParameter("param");
			String menuHeight = request.getParameter("menuHeight");
			String menuName = request.getParameter("menuName");
			view.addObject("menuHeight", menuHeight);
			view.addObject("menuName", menuName);

			if(StringUtils.isEmpty(param)){
				param = "0";
			}
			view.addObject("param", param);
			if(StringUtils.isEmpty(name)&&StringUtils.isEmpty(shopname)&&StringUtils.isEmpty(itemName)&&param.equals("0")){
				Date date = new Date();
				String string1 = DateUtil.formatDate(date, "yyyy-MM-dd 23:59:59");
				Date enddate = DateUtil.parseDateStr(string1, "yyyy-MM-dd HH:mm:ss");
				String string2 = DateUtil.formatDate(date, "yyyy-MM-dd 00:00:00");
				Date startdate = DateUtil.parseDateStr(string2, "yyyy-MM-dd HH:mm:ss");
		    	ShopOrder order = this.shopOrderService.findConsumeMoneyByCityIdAndDate(personUser.getCityId(),startdate,enddate);
				view.addObject("data", order);
			}
			PageHelper.startPage(currentPage, 50);
			List<ShopOrder> lists = this.shopOrderService.findOrderAndItem(name,shopname,param,personUser.getCityId(),itemName);
			PageInfo<ShopOrder> info = new PageInfo<ShopOrder>(lists);
			Page<ShopOrder> page = new Page<ShopOrder>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			view.addObject("page", page);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			Page<ShopOrder> page = new Page<ShopOrder>(1, 1, 50, new ArrayList<ShopOrder>());
			view.addObject("page", page);
		}
		TestController.getMenuPoint(view, request);
		return view;
		
	}
	
	@RequestMapping("actualShopReportList.htm")
	public ModelAndView Shoplist(HttpServletRequest request){
		ModelAndView view = new ModelAndView("actualReport/shoplist");
		try {
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return new ModelAndView("noCity");
			}
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));
			String param = request.getParameter("param");
			if(StringUtils.isEmpty(param)){
				param="0";
			}
			view.addObject("param", param);
			int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
			view.addObject("reseauId", reseauId);
			String username = request.getParameter("userName");
			view.addObject("userName", username);
			PageHelper.startPage(currentPage, 200);
			List<ActualShopReport> lists = this.shopOrderService.findactualShopReportList(param,username,reseauId,personUser.getCityId());
			PageInfo<ActualShopReport> info = new PageInfo<ActualShopReport>(lists);
			Page<ActualShopReport> page = new Page<ActualShopReport>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			view.addObject("page", page);
			List<Reseau> list = this.reseauService.findAllByCityId(personUser.getCityId());
			view.addObject("reseaus", list);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			Page<ActualShopReport> page = new Page<ActualShopReport>(1, 1, 200, new ArrayList<ActualShopReport>());
			view.addObject("page", page);
		}
		TestController.getMenuPoint(view, request);
		return view;
		
	}
	
	
	@RequestMapping("actualShopItemList.htm")
	public ModelAndView ShopItemlist(HttpServletRequest request, @RequestParam(value="reseauId",defaultValue="-1",required=false)Integer reseauId){
		ModelAndView view = new ModelAndView("actualReport/shopItemlist");
		try {
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return new ModelAndView("noCity");
			}
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));
			String userName = request.getParameter("userName");
			view.addObject("userName", userName);
			String itemName = request.getParameter("itemName");
			view.addObject("itemName", itemName);
			//新品
			String isnew = request.getParameter("isnew");
			view.addObject("isnew", isnew);
			//网格初期追加
			if(reseauId==-1){
				reseauId=null;
			}
			view.addObject("reseauId", reseauId);
			//网格list内容
			List<Reseau> list = this.reseauService.findAllByCityId(personUser.getCityId());
			view.addObject("reseaus", list);

			//排序
			String param = request.getParameter("param");
			if(StringUtils.isEmpty(param)){
				param = "0";
			}
			view.addObject("param", param);
			PageHelper.startPage(currentPage, 100);
			List<ShopItem> lists = this.shopItemService.findactualShopItemList(userName,itemName,personUser.getCityId(), isnew, reseauId, param);
			PageInfo<ShopItem> info = new PageInfo<ShopItem>(lists);
			Page<ShopItem> page = new Page<ShopItem>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			view.addObject("page", page);
			ShopItem shopItem = this.shopItemService.findTolal(userName,itemName,personUser.getCityId(), isnew, reseauId);
			view.addObject("total", shopItem);
			int consumeTotal = this.shopItemService.findactualShopItemConsumeTotal(userName,itemName,personUser.getCityId(), isnew, reseauId);
			view.addObject("consumeTotal", consumeTotal);


		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			Page<ActualShopReport> page = new Page<ActualShopReport>(1, 1, 100, new ArrayList<ActualShopReport>());
			view.addObject("page", page);
		}
		TestController.getMenuPoint(view, request);
		return view;
		
	}
	
		
	@RequestMapping(value = "/actualShopExportExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportExcel(HttpServletRequest request,
		HttpServletResponse response) {
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "你没有设置默认城市";
		}
		String FilePutPath = "D:\\单店实时.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("单店实时.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			
			String starttimeStr = request.getParameter("starttime");
			String param = request.getParameter("param");
			int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
			String username = request.getParameter("userName");
			List<ActualShopReport> reports = this.shopOrderService.findactualShopReportList(param,username,reseauId,personUser.getCityId());
			
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
				Label label = new Label(0, 0, "单店实时", format1);
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
				sheet.addCell(new Label(3, 1, "最近采购", cellFormat1));
				sheet.addCell(new Label(4, 1, "消费金额", cellFormat1));
				sheet.addCell(new Label(5, 1, "消费笔数", cellFormat1));
				sheet.addCell(new Label(6, 1, "在售金额", cellFormat1));
				sheet.addCell(new Label(7, 1, "在售品类", cellFormat1));
				sheet.addCell(new Label(8, 1, "滞销品", cellFormat1));
				sheet.addCell(new Label(9, 1, "可售天数", cellFormat1));
				sheet.addCell(new Label(10, 1, "最近采购日期", cellFormat1));
				sheet.addCell(new Label(11, 1, "最近盘点日期", cellFormat1));
				sheet.addCell(new Label(12, 1, "id", cellFormat1));
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
					Long sourcingFee=Long.parseLong(reports.get(i).getSourcingFee().toString());
					sheet.addCell(new Number(3, i + 2, sourcingFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(sourcingFee))).doubleValue()));
					Long consumeFee=Long.parseLong(reports.get(i).getConsumeFee().toString());
					sheet.addCell(new Number(4, i + 2,consumeFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(consumeFee))).doubleValue()));
					Integer consumeNum = reports.get(i).getConsumeNum();
					if(consumeNum!=null){
						sheet.addCell(new Number(5, i + 2, consumeNum ));
					}else{
						sheet.addCell(new Label(5, i + 2, ""));
					}
					Long onsaleFee=Long.parseLong(reports.get(i).getOnsaleFee().toString());
					sheet.addCell(new Number(6, i + 2,onsaleFee==null?0:(new BigDecimal(NumberTool.toYuanNumber(onsaleFee))).doubleValue()));
					
					Integer kinds = reports.get(i).getKinds();
					if(kinds!=null){
						sheet.addCell(new Number(7, i + 2, kinds ));
					}else{
						sheet.addCell(new Label(7, i + 2, ""));
					}
					
					Integer unsalable = reports.get(i).getUnsalable();
					if(unsalable!=null){
						sheet.addCell(new Number(8, i + 2, unsalable ));
					}else{
						sheet.addCell(new Label(8, i + 2, ""));
					}
					
					BigDecimal day = reports.get(i).getDay();
					if(null!=day){
						sheet.addCell(new Number(9, i + 2, Double.parseDouble(day.toString()) ));
					}else{
						sheet.addCell(new Label(9, i + 2, ""));
					}
					sheet.addCell(new Label(10, i + 2, reports.get(i).getLastTime()));
					sheet.addCell(new Label(11, i + 2, reports.get(i).getCheckTime()));
					sheet.addCell(new Number(12, i + 2, reports.get(i).getShopId()));
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

	@RequestMapping(value = "exportShopItemExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportShopItemExcel(HttpServletRequest request,
								 HttpServletResponse response) {
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "你没有设置默认城市";
		}
		String FilePutPath = "D:\\在售商品.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
					+ new String((java.net.URLEncoder.encode("在售商品.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			String userNameStr = request.getParameter("userName");
			String itemNameStr = request.getParameter("itemName");
			String isnewStr = request.getParameter("isnew");
			String paramStr = request.getParameter("param");
			if(StringUtils.isEmpty(paramStr)){
				paramStr = "0";
			}
			int reseauId = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("reseauId"), "0"));
			List<ShopItem> lists = this.shopItemService.findactualShopItemList(userNameStr,itemNameStr, personUser.getCityId(), isnewStr, reseauId, paramStr);
			if (lists != null && !lists.isEmpty()) {
				sheet.mergeCells(0, 0, 9, 0);// 合并标题单元格
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, "在售商品", format1);
				sheet.addCell(label);
				ShopItem shopItem = this.shopItemService.findTolal(userNameStr,itemNameStr, personUser.getCityId(),
						isnewStr, reseauId);
				int consumeTotal = this.shopItemService.findactualShopItemConsumeTotal(userNameStr,itemNameStr,personUser.getCityId(), isnewStr, reseauId);
				Long sumSalePrice = Long.parseLong(shopItem.getSalePrice().toString());
				String cell2 = "在售金额：" + (new BigDecimal(NumberTool.toYuanNumber(sumSalePrice))).doubleValue();
				cell2 = cell2 + "  在售数量：" + shopItem.getNum();
				cell2 = cell2 + "  在售品类：" + shopItem.getFlag();
				cell2 = cell2 + "  在售店铺数：" + shopItem.getShopId();
				cell2 = cell2 + "  周消费：" + consumeTotal;
				font1 = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD);
				format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.LEFT);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				Label label2 = new Label(0, 1, cell2, format1);
				sheet.addCell(label2);

				font1 = new WritableFont(WritableFont.TIMES, 11, WritableFont.BOLD);
				WritableCellFormat cellFormat1 = new WritableCellFormat(font1);
				// 设置背景颜色;
				cellFormat1.setBackground(Colour.GRAY_50);
				// 设置文字居中对齐方式;
				cellFormat1.setAlignment(jxl.format.Alignment.CENTRE);
				// 设置垂直居中;
				cellFormat1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				sheet.addCell(new Label(0, 2, "编号", cellFormat1));
				sheet.addCell(new Label(1, 2, "区域", cellFormat1));
				sheet.addCell(new Label(2, 2, "客户", cellFormat1));
				sheet.addCell(new Label(3, 2, "商品名称", cellFormat1));
				sheet.addCell(new Label(4, 2, "规格", cellFormat1));
				sheet.addCell(new Label(5, 2, "采购价", cellFormat1));
				sheet.addCell(new Label(6, 2, "售价", cellFormat1));
				sheet.addCell(new Label(7, 2, "数量", cellFormat1));
				sheet.addCell(new Label(8, 2, "周消费", cellFormat1));
				sheet.addCell(new Label(9, 2, "上架时间", cellFormat1));

				for (int i = 0; i < lists.size(); i++) {
					sheet.addCell(new Number(0, i + 3, i + 1));
					sheet.addCell(new Label(1, i + 3, lists.get(i).getRegion()));
					sheet.addCell(new Label(2, i + 3, lists.get(i).getUserName()));
					sheet.addCell(new Label(3, i + 3, lists.get(i).getName()));
					sheet.addCell(new Label(4, i + 3, lists.get(i).getSize()));
//

					Long sourcingPrice=Long.parseLong(lists.get(i).getSourcingPrice().toString());
					sheet.addCell(new Number(5, i + 3,sourcingPrice==null?0:(new BigDecimal(NumberTool.toYuanNumber(sourcingPrice))).doubleValue()));
					Long salePrice = Long.parseLong(lists.get(i).getSalePrice().toString());
					sheet.addCell(new Number(6, i + 3,salePrice==null?0:(new BigDecimal(NumberTool.toYuanNumber(salePrice))).doubleValue()));
					Integer num = lists.get(i).getNum();
					if(num!=null){
						sheet.addCell(new Number(7, i + 3, Integer.parseInt(num.toString()) ));
					}else{
						sheet.addCell(new Label(7, i + 3, ""));
					}
					Integer consumeNum = lists.get(i).getConsumeNum();
					if(consumeNum!=null){
						sheet.addCell(new Number(8, i + 3, Integer.parseInt(consumeNum.toString()) ));
					}else{
						sheet.addCell(new Label(8, i + 3, ""));
					}
					sheet.addCell(new Label(9, i + 3, DateUtil.formatDate(lists.get(i).getOnshelfTime(), "yyyy-MM-dd") ));
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
