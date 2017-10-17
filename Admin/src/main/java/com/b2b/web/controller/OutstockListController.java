package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
import com.b2b.enums.ItemSizeEnum;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.*;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/outstockList")
public class OutstockListController {

	private static final Logger logger = LoggerFactory.getLogger(OutstockListController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

	@Autowired
	OutstockService outstockService;

	@Autowired
	OutstockItemService outstockItemService;

	@Autowired
	SupplierService supplierService;

	@Autowired
	ItemService itemService;

	@Autowired
	ItemCategoryService itemCategoryService;

	@Autowired
	LogService logService;

	@Autowired
	AccountLockService accountLockService;

	@Autowired
	StockService stockService;

	@Autowired
	StandardOrderService standardOrderService;

	@Autowired
	UserService userService;

	@Autowired
	PersonRegionService personRegionService;

	@Autowired
	StorageService storageService;

	@Autowired
	ItemTasteService itemTasteService;

	@RequestMapping("outstockList.htm")
	public ModelAndView getOutstockListPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("outstock/OutstockList");
		try {
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return new ModelAndView("noCity");
			}
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));

			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");

			Date startTime = null;
			Date endTime = null;

			if (StringUtils.isNotBlank(startTimeStr)) {
				startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
				mv.addObject("startTime", startTimeStr);
			}

			if (StringUtils.isNotBlank(endTimeStr)) {
				endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YMD);
				mv.addObject("endTime", endTimeStr);
			}

			String supplierName = request.getParameter("supplierName");
			mv.addObject("supplierName", supplierName);
			String itemName = request.getParameter("itemName");
			mv.addObject("itemName", itemName);
			String param = request.getParameter("param");
			mv.addObject("param", param);

			PageHelper.startPage(currentPage, Page.DEFAULT_PAGE_SIZE);
;
			List<Outstock> outstocks = this.outstockService.findOutstocksAndOutstocksItemByCondition(startTime,
					endTime, supplierName, itemName, param, user.getCityId());

			PageInfo<Outstock> info = new PageInfo<Outstock>(outstocks);

			Page<Outstock> page = new Page<Outstock>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());

			mv.addObject("page", page);

			Integer mun = this.outstockService.findPreparationNum(user.getCityId());
			mv.addObject("mun", mun);

			Long completionMoney = this.outstockService.findTotalPrice(startTime,
					endTime, supplierName, itemName, "1", user.getCityId());
			Long waitMoney = this.outstockService.findTotalPrice(startTime,
					endTime, supplierName, itemName, "0", user.getCityId());

			mv.addObject("completionMoney", completionMoney);
			mv.addObject("waitMoney", waitMoney);

			int num = this.outstockService.findPreparationNum(user.getCityId());
			mv.addObject("num", num);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<Pair<Outstock, List<OutstockItem>>> page = new Page<Pair<Outstock, List<OutstockItem>>>(1, 1,
					Page.DEFAULT_PAGE_SIZE,new ArrayList<Pair<Outstock, List<OutstockItem>>>());
			mv.addObject("page", page);
		}
		TestController.getMenuPoint(mv, request);
		return mv;
	}


	@RequestMapping(value = "exportExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportExcel(HttpServletRequest request,
							  HttpServletResponse response) {
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);

		String FilePutPath = "D:\\领蛙采购订单"  + request.getParameter("id") + ".xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			String outstockId = request.getParameter("id");

			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
					+ new String((java.net.URLEncoder.encode("领蛙采购订单" + outstockId + ".xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			List<Outstock> outstocks = this.outstockService.findOutstocksAndOutstocksItemByCondition(null,
					null, outstockId, null, null, user.getCityId());
			if (outstocks != null && !outstocks.isEmpty()) {
				sheet.mergeCells(0, 0, 1, 0);// 合并标题单元格
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, "供应商：" + outstocks.get(0).getSupplierName(), format1);
				sheet.addCell(label);
				sheet.mergeCells(2, 0, 4, 0);// 合并标题单元格
				label = new Label(2, 0, "含税总金额：" + NumberTool.toYuanNumber(outstocks.get(0).getTotalPrice()) + "元", format1);
				sheet.addCell(label);
				sheet.mergeCells(5, 0, 8, 0);// 合并标题单元格
				label = new Label(5, 0, "日期：" + DateUtil.formatDate(outstocks.get(0).getOutstockTime(), "yyyy-MM-dd"), format1);
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
				sheet.addCell(new Label(1, 1, "商品名称", cellFormat1));
				sheet.addCell(new Label(2, 1, "条形码", cellFormat1));
				sheet.addCell(new Label(3, 1, "商品规格", cellFormat1));
				sheet.addCell(new Label(4, 1, "批发规格系数", cellFormat1));
				sheet.addCell(new Label(5, 1, "批发成本价", cellFormat1));
				sheet.addCell(new Label(6, 1, "进货数量", cellFormat1));
				sheet.addCell(new Label(7, 1, "金额", cellFormat1));
				sheet.addCell(new Label(8, 1, "备注", cellFormat1));

				sheet.setColumnView(1, 40);// 根据内容自动设置列宽
				sheet.setColumnView(2, 15);
				sheet.setColumnView(3, 15);
				sheet.setColumnView(4, 20);
				sheet.setColumnView(5, 15);
				sheet.setColumnView(6, 15);
				sheet.setColumnView(7, 15);
				sheet.setColumnView(8, 40);
				for (int i = 0; i < outstocks.get(0).getOutstockItemList().size(); i++) {
					sheet.addCell(new Label(0, i + 2, i+1 + ""));
					sheet.addCell(new Label(1, i + 2, outstocks.get(0).getOutstockItemList().get(i).getItemName()));
//					sheet.addCell(new Label(2, i + 2, outstocks.get(0).getOutstockItemList().get(i).getBarcode()));
					sheet.addCell(new Label(3, i + 2, outstocks.get(0).getOutstockItemList().get(i).getSize()));
					sheet.addCell(new Label(4, i + 2, outstocks.get(0).getOutstockItemList().get(i).getConvertNum() + ""));
					Long costPrice=Long.parseLong(outstocks.get(0).getOutstockItemList().get(i).getCostPrice().toString());
					sheet.addCell(new Number(5, i + 2,costPrice==null?0:(new BigDecimal(NumberTool.toYuanNumber(costPrice))).doubleValue()));
					sheet.addCell(new Label(6, i + 2, outstocks.get(0).getOutstockItemList().get(i).getOutstockNum() + ""));
					Long totalPrice=Long.parseLong(outstocks.get(0).getOutstockItemList().get(i).getTotalPrice().toString());
					sheet.addCell(new Number(7, i + 2,totalPrice==null?0:(new BigDecimal(NumberTool.toYuanNumber(totalPrice))).doubleValue()));
					sheet.addCell(new Label(8, i + 2, outstocks.get(0).getOutstockItemList().get(i).getItemRemark()));
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


	@RequestMapping(value = "/printOutstock.do", method = RequestMethod.POST)
	public ModelAndView printOutstock(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("outstock/printOutstockItems");

		String ids = request.getParameter("ids");
		if (StringUtils.isBlank(ids)) {
			throw new NullPointerException("订单参数异常");
		}
		String[] outstocks = ids.split(",");

		//key: userId 按用户合并订单数据
		List<Pair<Outstock, List<OutstockItem>>> list = new ArrayList<Pair<Outstock, List<OutstockItem>>>();
		for (String outstockId : outstocks) {
			Pair<Outstock, List<OutstockItem>> pair = outstockService.findById(Long.valueOf(outstockId));
			if (pair == null || pair.getLeft() == null) {
				logger.error("订单数据异常, outstockId:" + outstockId);
				continue;
			}
			list.add(pair);
			pair.getLeft().setPrintFlg(1);
			this.outstockService.updateOutstock(pair.getLeft());
		}

		mv.addObject("list", list);

		return mv;
	}

	@RequestMapping("orderPrint.htm")
	public ModelAndView print(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("order/print");

		String orderNo = request.getParameter("orderNo");

//		Pair<Order, List<OrderItem>> pair = orderService
//				.findByOrderNo(orderNo);
		Pair<Order, List<OrderItem>> pair = null;
		if (pair == null || pair.getLeft() == null) {
			throw new NullPointerException("该订单不存在");
		}


		mv.addObject("order",pair.getLeft());
		mv.addObject("itemList",pair.getRight());
		TestController.getMenuPoint(mv, request);
		return mv;
	}



	@RequestMapping(value = "/cancelOutstock.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String cancelOutstock(HttpServletRequest request) {
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			Long outstockId = Long.valueOf(StringUtils.defaultIfBlank(request.getParameter("id"), "-1"));
			if (outstockId == -1) {
				return "删除失败";
			}

			Outstock outstock = this.outstockService.findOutstockById(outstockId);
			if(!personUser.getCityId().equals(outstock.getCityId())){
				return "你设置的城市与实际不符";
			}
			if (!outstock.getStatus().toString().equals("0")) {
                if (!outstock.getStatus().toString().equals("4")) {
                    return "此订单已有货物送单，不能删除，请确认";
                }
			}
            this.outstockService.updateOutstockStatus(outstockId, 2);
			this.saveLog(request.getSession(), outstock, "删除采购退货单，id:" + outstock.getId(), outstock.getCityId());

		} catch (Throwable e) {
			logger.error("取消订单失败", e);
			return "failure";
		}
		return "success";
	}


	@RequestMapping(value = "/outstockStock.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String outstockStock(HttpServletRequest request) {
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "你还未设置默认城市，联系管理员设置";
		}
		try {
			Long outstockId = Long.valueOf(StringUtils.defaultIfBlank(request.getParameter("id"), "-1"));
			if (outstockId == -1) {
				return "采购出库失败";
			}

			Outstock outstock = this.outstockService.findOutstockById(outstockId);
			if(!personUser.getCityId().equals(outstock.getCityId())){
				return "你设置的城市与实际不符";
			}
			if (!outstock.getStatus().toString().equals("0")) {
					return "此订单已处理过，不能删除，请确认";
			}

			Pair<Outstock, List<OutstockItem>> pair = outstockService.findById(outstockId);

			if(pair!=null){
				Outstock dto = pair.getKey();
				if(!dto.getCityId().equals(personUser.getCityId())){
					return "你设置的默认城市与操作城市不符";
				}
				if (CollectionUtils.isNotEmpty(pair.getRight())) {
					for (OutstockItem outstockItem : pair.getRight()) {
						stockService.updateForReduce(outstockItem.getItemId(), outstockItem.getConvertNum());
					}
				} else {
					return "退货订单信息不存在";
				}

				this.outstockService.updateOutstockStatus(outstockId, 1);

				this.saveLog(request.getSession(),dto, "采购出货已完成，id:"+outstockId, personUser.getCityId());
			}


		} catch (Throwable e) {
			logger.error("编辑订单显示", e);
			return "failure" + e;
		}
		return "success";
	}


	private void saveLog(HttpSession session,Outstock dto,String content,Integer cityId){
		try{
			SysLog sysLog = new SysLog();
			sysLog.setContent(content);
			sysLog.setCreateTime(new Date());
			sysLog.setUserId(SessionHelper.getUserId(session));
			sysLog.setCityId(cityId);
			sysLog.setDataType(LogDataTypeEnum.OUTSTOCK.getName());
			sysLog.setDataId(dto.getId().toString());

			String dataContent = new Gson().toJson(dto,
					new TypeToken<Outstock>() {
					}.getType());

			sysLog.setDataContent(dataContent);

			logService.createLog(sysLog);
		}catch(Exception e){
			logger.error("保存日志失败",e);
		}
	}

	private String addStorage(PersonUser personUser, String number, List<OutstockItem> outstockItemList, HttpServletRequest request, Integer supplierId) {
		try {
			Storage storage = new Storage();
			storage.setCityId(personUser.getCityId());
			List<StorageItem> skuList = new ArrayList<StorageItem>();
			storage.setNumber("From outstock:" + number);
			long totalFeeAll = 0;

			for (OutstockItem outstockItem : outstockItemList) {
				int itemId = outstockItem.getItemId();
				Item item = itemService.findById(itemId);

				String size = ItemSizeEnum.BUY_SIZE.getName();

				long buyPrice = item.calPrice(size);

				int buyNum = outstockItem.getOutstockNum();
				int num = item.calNum(buyNum, size);

//				int num = Integer.parseInt(request
//						.getParameter("num" + i));

				long totalFee = buyNum * buyPrice;
				String sizeValue = item.calSize(size);
				//float totalFee=Float.parseFloat(request.getParameter("totalFee" + i));
				StorageItem sku = new StorageItem();
				// sku_id
				sku.setItemId(Integer.valueOf(itemId));

				// sku_name
				sku.setItemName(item.getItemName());
				sku.setTotalFee(totalFee);
				sku.setNum(num);
				sku.setBuyNum(buyNum);
				sku.setBuyPrice(buyPrice);
				sku.setSize(size);
				sku.setSizeValue(sizeValue);

				skuList.add(sku);

				totalFeeAll = totalFeeAll + totalFee;
				//logger.error(i+":"+sku.getItemId());
			}

			storage.setUserId(personUser.getId());
			storage.setLastModUser(personUser.getId());

			storage.setExecutedTime(new Date());

			if (supplierId != null) {
				storage.setSupplierId(supplierId);
			}
			storage.setTotalFee(totalFeeAll);
			storage.setStorageItemList(skuList);
			storageService.create(storage);

			this.saveLog2(request.getSession(), storage, "新增出库单，id:" + storage.getId());
			return "添加成功";
		} catch (Exception e) {
			logger.error("添加出库单失败", e);
			return e.getMessage();
		}
	}

	private void saveLog2(HttpSession session,Storage dto,String content) {
		try {
			SysLog sysLog = new SysLog();
			sysLog.setContent(content);
			sysLog.setCreateTime(new Date());
			sysLog.setUserId(SessionHelper.getUserId(session));
			sysLog.setCityId(dto.getCityId());
			sysLog.setDataType(LogDataTypeEnum.STORAGE.getName());
			sysLog.setDataId(dto.getId().toString());

			String dataContent = new Gson().toJson(dto,
					new TypeToken<Storage>() {
					}.getType());

			sysLog.setDataContent(dataContent);

			logService.createLog(sysLog);
		} catch (Exception e) {
			logger.error("保存日志失败", e);
		}
	}

}
