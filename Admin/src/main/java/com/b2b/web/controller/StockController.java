package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.ItemCategoryService;
import com.b2b.service.ItemService;
import com.b2b.service.LogService;
import com.b2b.service.StockService;
import com.b2b.web.util.NumberTool;
import com.b2b.web.util.SessionHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/stock")
public class StockController {

	private static final Logger logger = LoggerFactory.getLogger(StockController.class);


	@Autowired
	StockService stockService;
	
	@Autowired
	ItemCategoryService itemCategoryService;

	@Autowired
	LogService logService;
	
	@Autowired
	ItemService itemService;


	@RequestMapping(value = "/stockList.htm")
	@ResponseBody
	public ModelAndView list(Stock stock,HttpServletRequest request) {

		ModelAndView view = new ModelAndView("stock/list");
		TestController.getMenuPoint(view, request);
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		int onecate = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("onecate"), "0"));
		int twocate = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("twocate"), "0"));
		int unsalable = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("unsalable"), "0"));
		view.addObject("onecate", onecate);
		view.addObject("twocate", twocate);
		view.addObject("unsalable", unsalable);
		String isdown = request.getParameter("isdown");
		view.addObject("isdown", isdown);
		String itemName = stock.getItemName();
		boolean alertFlag = false;
		if(stock.getAlertNum()!=null&&stock.getAlertNum()==1){
			alertFlag = true;
		}
		
		PageHelper.startPage(currentPage, 30);
		List<Stock> stocks = this.stockService.findByCondition(itemName,alertFlag,onecate,twocate,isdown,unsalable,user.getCityId());
		PageInfo<Stock> info = new PageInfo<Stock>(stocks);
		Page<Stock> page = new Page<Stock>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		view.addObject("page", page);
		view.addObject("page", page);
		
		Long stockTotalAmount = stockService.findStockTotalMoney(itemName,alertFlag,onecate,twocate,isdown,unsalable,user.getCityId());
		view.addObject("stockTotalAmount", stockTotalAmount);

		view.addObject("itemName", stock.getItemName());
		view.addObject("alertNum", stock.getAlertNum());
		List<ItemCategory> catList = this.itemCategoryService.findAllOneCatsByCityId(user.getCityId());
		view.addObject("catList", catList);
		if(onecate>0){
			List<ItemCategory> leaveCats = this.itemCategoryService.findAllTwoCatsByCityId(user.getCityId());
			view.addObject("leaveCats", leaveCats);
		}
		return view;
	}
	

	@RequestMapping(value = "/stockUpdate.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView update(int id, int currentPage, HttpServletRequest request) {
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		
		Stock dto = stockService.findById(id);
		Item item = this.itemService.findById(dto.getItemId());
		if(null==item || item.getCityId() !=user.getCityId() ){
			return new ModelAndView("notCity");
		}
		ModelAndView view = new ModelAndView("stock/detail");
		view.addObject("currentPage", currentPage);
		view.addObject("dto", dto);
		TestController.getMenuPoint(view, request);
		return view;
	}

	@RequestMapping(value="/stockNumSave.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public ModelAndView save(Stock dto, HttpServletRequest request) {
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));

		try{
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return new ModelAndView("noCity");
			}
			Stock stock = stockService.findById(dto.getId());
			Item item = this.itemService.findById(stock.getItemId());
			if(null==item || item.getCityId() !=user.getCityId() ){
				return new ModelAndView("notCity");
			}
			stock.setNum(dto.getNum());
			stock.setAlertNum(dto.getAlertNum());
			if(!StringUtils.isEmpty(dto.getPosition())){
				stock.setPosition(dto.getPosition());
			}
			stock.setWarningNum(dto.getWarningNum());
			stockService.update(stock);
			this.saveLog(request.getSession(),stock, "更新库存,id:"+stock.getId()+",商品名称："+stock.getItemName(),user.getCityId());
		}catch(Exception e){
            logger.error("更新库存失败",e);
		}

		ModelAndView view = new ModelAndView("redirect:/stock/stockList.htm");
		view.addObject("currentPage", currentPage);
		TestController.getMenuPoint(view, request);
        return view;
	}
	
	
	@RequestMapping(value = "exportExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportExcel(HttpServletRequest request,
			HttpServletResponse response) {
		String FilePutPath = "D:\\商品库存表.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("商品库存表.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			String itemName = request.getParameter("itemName");
			String alert = request.getParameter("alertNum");
			boolean alertFlag = false;
			if(null!=alert&&Integer.parseInt(alert)==1){
				alertFlag = true;
			}
			
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return "未设置默认城市";
			}
			int onecate = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("onecate"), "0"));
			int twocate = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("twocate"), "0"));
			int unsalable = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("unsalable"), "0"));
			String isdowns = request.getParameter("isdown");
			List<HashMap<String, Object>> listitem = stockService.findAllStock(itemName,alertFlag,onecate,twocate,isdowns,unsalable,user.getCityId());
			if (listitem != null && !listitem.isEmpty()) {
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
				Label label = new Label(0, 0, "领蛙商品库存列表", format1);
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
				sheet.addCell(new Label(1, 1, "状态", cellFormat1));
				sheet.addCell(new Label(2, 1, "一级类目", cellFormat1));
				sheet.addCell(new Label(3, 1, "二级类目", cellFormat1));
				
				sheet.addCell(new Label(4, 1, "商品名称", cellFormat1));
				sheet.addCell(new Label(5, 1, "规格", cellFormat1));
				sheet.addCell(new Label(6, 1, "单价", cellFormat1));
				sheet.addCell(new Label(7, 1, "当前库存数量", cellFormat1));
				sheet.addCell(new Label(8, 1, "预警数量", cellFormat1));
				sheet.addCell(new Label(9, 1, "告警数量", cellFormat1));
				sheet.addCell(new Label(10, 1, "零售规格系数", cellFormat1));
				sheet.addCell(new Label(11, 1, "批发成本价", cellFormat1));
				sheet.addCell(new Label(12, 1, "批发规格系数", cellFormat1));
				sheet.addCell(new Label(13, 1, "仓位", cellFormat1));
				sheet.addCell(new Label(14, 1, "最后下单时间", cellFormat1));
				sheet.setColumnView(2, 20);// 根据内容自动设置列宽
				sheet.setColumnView(3, 30);// 根据内容自动设置列宽
				sheet.setColumnView(4, 20);
				sheet.setColumnView(5, 15);
				sheet.setColumnView(6, 15);
				for (int i = 0; i < listitem.size(); i++) {
					sheet.addCell(new Number(0, i + 2, i + 1));
					int isdown = Integer.parseInt(listitem.get(i).get("isdown").toString());
					if(isdown==0){
						sheet.addCell(new Label(1, i + 2, "架上"));
					}else{
						sheet.addCell(new Label(1, i + 2, "架下"));
					}
					sheet.addCell(new Label(2, i + 2, (String) listitem.get(i).get("categoryName")));
					sheet.addCell(new Label(3, i + 2, (String) listitem.get(i).get("cateLevelName")));
					
					sheet.addCell(new Label(4, i + 2, (String) listitem.get(i).get("itemName")));
					sheet.addCell(new Label(5, i + 2, (String) listitem.get(i).get("size")));
					Long price=Long.parseLong(listitem.get(i).get("price").toString());
					Long buyPrice=Long.parseLong(listitem.get(i).get("buyPrice").toString());
					sheet.addCell(new Number(6, i + 2,price==null?0:(new BigDecimal(NumberTool.toYuanNumber(price))).doubleValue()));
					sheet.addCell(new Number(7, i + 2, listitem.get(i).get("num")==null?0:new BigDecimal(listitem.get(i).get("num").toString()).doubleValue()));
					sheet.addCell(new Number(8, i + 2,listitem.get(i).get("warningNum")==null?0:new BigDecimal(listitem.get(i).get("warningNum").toString()).doubleValue()));
					sheet.addCell(new Number(9, i + 2,listitem.get(i).get("alertNum")==null?0:new BigDecimal(listitem.get(i).get("alertNum").toString()).doubleValue()));
					sheet.addCell(new Number(10, i + 2,listitem.get(i).get("saleSizeNum")==null?0:new BigDecimal(listitem.get(i).get("saleSizeNum").toString()).doubleValue()));
					sheet.addCell(new Number(11, i + 2,buyPrice==null?0:(new BigDecimal(NumberTool.toYuanNumber(buyPrice))).doubleValue()));
					sheet.addCell(new Number(12, i + 2,listitem.get(i).get("convertNum")==null?0:new BigDecimal(listitem.get(i).get("convertNum").toString()).doubleValue()));
					sheet.addCell(new Label(13, i + 2, (String) listitem.get(i).get("position")));
					if(null!=listitem.get(i).get("lastTime")){
						sheet.addCell(new Label(14, i + 2, DateUtil.formatDate((Date) listitem.get(i).get("lastTime"), "yyyy-MM-dd")));
					}else{
						sheet.addCell(new Label(14, i + 2, ""));
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

	private void saveLog(HttpSession session,Stock dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setCityId(cityId);
	       sysLog.setDataType(LogDataTypeEnum.STOCK.getName());
	       sysLog.setDataId(dto.getId().toString());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<Stock>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
