package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
import com.b2b.enums.ItemSizeEnum;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.enums.OrderStatusEnum;
import com.b2b.page.Page;
import com.b2b.service.*;
import com.b2b.web.util.NumberTool;
import com.b2b.web.util.SessionHelper;
import com.b2b.web.wx.util.Properties;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.*;


@Controller
@RequestMapping("/purchaseList")
public class PurchaseListController {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseListController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

	@Autowired
	PurchaseService purchaseService;

	@Autowired
	PurchaseItemService purchaseItemService;

	@Autowired
	OrderService orderService;

	@Autowired
	SupplierService supplierService;

//	@Autowired
//	UserService userService;

	@Autowired
	CustomerService customerService;

	@Autowired
	ItemService itemService;

	@Autowired
	ItemCategoryService itemCategoryService;

	@Autowired
	BusinessService businessService;

	@Autowired
	LogService logService;

	@Autowired
	AccountLockService accountLockService;

	@Autowired
	OrderBagService orderBagService;

	@Autowired
	StockService stockService;

	@Autowired
	private StandardOrderItemService standardOrderItemService;

	@Autowired
	StandardOrderService standardOrderService;

	@Autowired
	WXAccountService wxAccountService;

	@Autowired
	ShopBlackListService shopBlackListService;

	@Autowired
	UserService userService;

	@Autowired
	PersonRegionService personRegionService;

	@Autowired
	AfterSalesRecordService afterSalesRecordService;

	@Autowired
	WholeTokenService wholeTokenService;

	@Autowired
	Properties properties;

	@Autowired
	ReseauService reseauService;

	@Autowired
	StorageService storageService;

	@Autowired
	ItemTasteService itemTasteService;

	@RequestMapping("purchaseList.htm")
	public ModelAndView getPurchaseListPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("purchase/purchaseList");
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

			String purchaseId = request.getParameter("purchaseId");
			mv.addObject("purchaseId", purchaseId);
			String supplierName = request.getParameter("supplierName");
			mv.addObject("supplierName", supplierName);
			String itemName = request.getParameter("itemName");
			mv.addObject("itemName", itemName);

			PageHelper.startPage(currentPage, Page.DEFAULT_PAGE_SIZE);

			List<Purchase> purchases = this.purchaseService.findPurchasesAndPurchasesItemByCondition(startTime,
					endTime, purchaseId, supplierName, itemName, user.getCityId());

			PageInfo<Purchase> info = new PageInfo<Purchase>(purchases);

			Page<Purchase> page = new Page<Purchase>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());

			mv.addObject("page", page);

//			int num = this.orderService.findBeComfirmOrderNumByCityId(user.getCityId());
//			mv.addObject("num", num);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<Pair<Purchase, List<PurchaseItem>>> page = new Page<Pair<Purchase, List<PurchaseItem>>>(1, 1,
					Page.DEFAULT_PAGE_SIZE,new ArrayList<Pair<Purchase, List<PurchaseItem>>>());
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

		String purchaseId = request.getParameter("id");
		Purchase purchase = this.purchaseService.findPurchaseById(purchaseId);
		String FilePutPath = "D:\\领蛙采购订单"  + request.getParameter("id") + purchase.getSupplierName() + ".xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {

			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
					+ new String((java.net.URLEncoder.encode("领蛙采购订单" + purchaseId + purchase.getSupplierName() + ".xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			List<Purchase> purchases = this.purchaseService.findPurchasesAndPurchasesItemByCondition(null,
					null, purchaseId, null, null, user.getCityId());
			if (purchases != null && !purchases.isEmpty()) {
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
				Label label = new Label(0, 0, "供应商：" + purchases.get(0).getSupplierName(), format1);
				sheet.addCell(label);
				sheet.mergeCells(2, 0, 4, 0);// 合并标题单元格
				label = new Label(2, 0, "含税总金额：" + NumberTool.toYuanNumber(purchases.get(0).getTotalPrice()) + "元", format1);
				sheet.addCell(label);
				sheet.mergeCells(5, 0, 8, 0);// 合并标题单元格
				label = new Label(5, 0, "日期：" + DateUtil.formatDate(purchases.get(0).getPurchasedTime(), "yyyy-MM-dd"), format1);
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
				sheet.addCell(new Label(9, 1, "库存量", cellFormat1));
				sheet.addCell(new Label(10, 1, "零售规格", cellFormat1));

				sheet.setColumnView(1, 40);// 根据内容自动设置列宽
				sheet.setColumnView(2, 15);
				sheet.setColumnView(3, 15);
				sheet.setColumnView(4, 20);
				sheet.setColumnView(5, 15);
				sheet.setColumnView(6, 15);
				sheet.setColumnView(7, 15);
				sheet.setColumnView(8, 40);
				sheet.setColumnView(9, 15);
				sheet.setColumnView(10, 15);
				for (int i = 0; i < purchases.get(0).getPurchaseItemList().size(); i++) {
					sheet.addCell(new Label(0, i + 2, i+1 + ""));
					sheet.addCell(new Label(1, i + 2, purchases.get(0).getPurchaseItemList().get(i).getItemName()));
					sheet.addCell(new Label(2, i + 2, purchases.get(0).getPurchaseItemList().get(i).getBarcode()));
					sheet.addCell(new Label(3, i + 2, purchases.get(0).getPurchaseItemList().get(i).getSize()));
					sheet.addCell(new Label(4, i + 2, purchases.get(0).getPurchaseItemList().get(i).getConvertNum() + ""));
					Long costPrice=Long.parseLong(purchases.get(0).getPurchaseItemList().get(i).getCostPrice().toString());
					sheet.addCell(new Number(5, i + 2,costPrice==null?0:(new BigDecimal(NumberTool.toYuanNumber(costPrice))).doubleValue()));
					sheet.addCell(new Label(6, i + 2, purchases.get(0).getPurchaseItemList().get(i).getPurchaseNum() + ""));
					Long totalPrice=Long.parseLong(purchases.get(0).getPurchaseItemList().get(i).getTotalPrice().toString());
					sheet.addCell(new Number(7, i + 2,totalPrice==null?0:(new BigDecimal(NumberTool.toYuanNumber(totalPrice))).doubleValue()));
					sheet.addCell(new Label(8, i + 2, purchases.get(0).getPurchaseItemList().get(i).getItemRemark()));
					sheet.addCell(new Label(9, i + 2,purchases.get(0).getPurchaseItemList().get(i).getStockNum().toString() + ""));
					sheet.addCell(new Label(10, i + 2, purchases.get(0).getPurchaseItemList().get(i).getSaleSize()));
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


	@RequestMapping(value = "/printPurchase.do", method = RequestMethod.POST)
	public ModelAndView printPurchase(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("purchase/printPurchaseItems");

		String purchaseId = request.getParameter("id");
		if (StringUtils.isBlank(purchaseId)) {
			throw new NullPointerException("订单参数异常");
		}
//		String[] orders = orderNos.split(",");
		//key: userId 按用户合并订单数据
		Map<Integer, List<Pair<Purchase, List<PurchaseItem>>>> osMaps = Maps.newHashMap();
//		for (String orderNo : orders) {
		List<Pair<Purchase, List<PurchaseItem>>> osList = Lists.newArrayList();
		Pair<Purchase, List<PurchaseItem>> pair = this.purchaseService.findByPurchaseId(purchaseId);
		if (pair == null || pair.getLeft() == null) {
			logger.error("订单数据异常, purchaseId:" + purchaseId);
		}
		osList.add(pair);
		Integer userId = pair.getLeft().getSupplierId();
		if (osMaps.keySet().contains(userId)) {
			osMaps.get(userId).addAll(osList);
		} else {
			osMaps.put(userId, osList);
		}

		//this.orderService.updateOrderStatus(orderNo,OrderStatusEnum.PRINT);
//		}

		mv.addObject("printDate", new Date());
		mv.addObject("osMaps", osMaps);


		return mv;
	}

	@RequestMapping(value = "/purchaseStockAdd.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String purchaseStockAdd(HttpServletRequest request) {

		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if (null == personUser.getCityId()) {
				return "你还未设置默认城市，联系管理员设置";
			}

			String purchaseId = request.getParameter("purchaseId");
			Purchase purchaseOld = this.purchaseService.findPurchaseById(purchaseId);
			if (null == purchaseOld || !personUser.getCityId().equals(purchaseOld.getCityId())) {
				return "采购订单不存在";
			}

			String[] itemIds = request.getParameter("itemIds").split(",");
			String[] purchasedNums = request.getParameter("purchasedNums").split(",");
			String[] ids = request.getParameter("ids").split(",");
			String idsStr = request.getParameter("ids") + "0";
			boolean end = false;
			for (int i = 0; itemIds.length > i; i++) {
				PurchaseItem oldPurchaseItem = this.purchaseItemService.findbyId(Integer.valueOf(ids[i]));
				if (oldPurchaseItem == null) {
					return "采购订单不存在";
				}
				PurchaseItem purchaseItem = new PurchaseItem();
				purchaseItem.setId(Integer.valueOf(ids[i]));
				Integer oldPurchasedNum = oldPurchaseItem.getPurchasedNum() == null ? 0 : oldPurchaseItem.getPurchasedNum();
				purchaseItem.setPurchasedNum(Integer.valueOf(purchasedNums[i]) + oldPurchasedNum);
				purchaseItem.setUseNum(Integer.valueOf(purchasedNums[i]));
				if (oldPurchaseItem.getPurchaseNum() > purchaseItem.getPurchasedNum()) {
					purchaseItem.setStockFlag(1);
				} else {
					purchaseItem.setStockFlag(2);
					purchaseItem.setStatus(1);
				}

				this.purchaseItemService.update(purchaseItem);
			}
			Integer endCount = this.purchaseItemService.findEndCountByPurchaseId(purchaseId);
			if (endCount == 0) {
				purchaseOld.setStatus(1);
			} else {
				purchaseOld.setStatus(6);
			}

			purchaseService.updateByPrimaryKeySelective(purchaseOld);

			List<PurchaseItem> purchaseItemList = new ArrayList<PurchaseItem>();
			for (String id : ids) {
				List<PurchaseItem> purchaseItem = this.purchaseItemService.findInfoByStock(id);
				if (purchaseItem!=null) {
					purchaseItemList.add(purchaseItem.get(0));
				}
			}
			addStorage(personUser, purchaseId, purchaseItemList, request);
		} catch (Throwable e) {
			logger.error("采购订单入库失败", e);
			return "采购订单入库失败";
		}

		return "200";
	}

	@RequestMapping(value = "/changeprintPurchase.do", method = RequestMethod.POST)
	public String changeprintPurchase(@RequestParam("id")String id) {
		try {
			this.orderService.updateOrderStatus(id,OrderStatusEnum.PRINT);
			this.purchaseService.updatePurchasePrintStatus(id);
			return "200";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "201";
	}

	@RequestMapping("orderPrint.htm")
	public ModelAndView print(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("order/print");

		String orderNo = request.getParameter("orderNo");

		Pair<Order, List<OrderItem>> pair = orderService
				.findByOrderNo(orderNo);
		if (pair == null || pair.getLeft() == null) {
			throw new NullPointerException("该订单不存在");
		}


		mv.addObject("order",pair.getLeft());
		mv.addObject("itemList",pair.getRight());

		TestController.getMenuPoint(mv, request);
		return mv;
	}



	@RequestMapping(value = "/cancelPurchase.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String cancelPurchase(HttpServletRequest request) {
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			String purchaseId = request.getParameter("id");
			//会员充值用户取消订单
			Purchase purchase = this.purchaseService.findPurchaseById(purchaseId);
			if(!personUser.getCityId().equals(purchase.getCityId())){
				return "你设置的城市与实际不符";
			}
			if (!purchase.getStatus().toString().equals("0")) {
                if (!purchase.getStatus().toString().equals("4")) {
                    return "此订单已有货物送单，不能删除，请确认";
                }
			}
            this.purchaseService.updatePurchaseStatus(purchaseId, (Integer) 5);
			this.purchaseItemService.updatePurchaseItemStatus(purchaseId, (Integer) 5);






		} catch (Throwable e) {
			logger.error("取消订单失败", e);
			return "failure";
		}
		return "success";
	}

	@RequestMapping(value = "/purchaseOk.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String purchaseOk(HttpServletRequest request) {
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			String purchaseId = request.getParameter("id");
			//会员充值用户取消订单
			Purchase purchase = this.purchaseService.findPurchaseById(purchaseId);
			if(!personUser.getCityId().equals(purchase.getCityId())){
				return "你设置的城市与实际不符";
			}
			String status = purchase.getStatus().toString();
			if (status.equals("1") && status.equals("3")) {
				return "此订单已到终态，不能完成";
			} else {
				this.purchaseService.updatePurchaseStatus(purchaseId, (Integer) 4);
			}


		} catch (Throwable e) {
			logger.error("取消订单失败", e);
			return "failure";
		}
		return "success";
	}

	@RequestMapping("showPurchase.htm")
	public ModelAndView editOrderShow(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("purchase/purchaseEdit");
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		try {
			String purchaseId = request.getParameter("purchaseId");

			Pair<Purchase, List<HashMap<String, Object>>> pair = purchaseService.findInfoByPurchaseId(purchaseId);

			mv.addObject("pair", pair);

			mv.addObject("itemSize",CollectionUtils.size(pair.getRight()));

			Supplier supplier = supplierService.findById(pair.getKey().getSupplierId());

			mv.addObject("supplier", supplier);

			this.fillCommonData2(mv, user.getCityId());
		} catch (Throwable e) {
			logger.error("编辑订单显示", e);
		}
		return mv;
	}

	@RequestMapping("purchaseStock.htm")
	public ModelAndView purchaseStock(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("purchase/purchaseStock");
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		try {
			String purchaseId = request.getParameter("purchaseId");

			Pair<Purchase, List<HashMap<String, Object>>> pair = purchaseService.findInfoByPurchaseId(purchaseId);

			mv.addObject("pair", pair);

			mv.addObject("itemSize",CollectionUtils.size(pair.getRight()));

		} catch (Throwable e) {
			logger.error("编辑订单显示", e);
		}
		TestController.getMenuPoint(mv, request);
		return mv;
	}


	@RequestMapping(value="editPurchase.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String editOrderSave( HttpServletRequest req) {
		try{
			PersonUser pUser = (PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			String supplierId = req.getParameter("supplierId");
			if (StringUtils.isEmpty(supplierId)) {
				return "用户ID不能为空!";
			}

			Purchase purchase = new Purchase();
			purchase.setSupplierId(Integer.valueOf(supplierId));
			purchase.setSupplierName(req.getParameter("supplierName"));
			String executedTimeStr = req.getParameter("executedTime");
			if (StringUtils.isEmpty(executedTimeStr)) {
				purchase.setExecutedTime(new Date());
			} else {
				purchase.setExecutedTime(DateUtil.parseDateStr(executedTimeStr, DATE_FORMAT_YMD));
			}
			String purchaseId = req.getParameter("purchaseId");

			Purchase purchaseOld = this.purchaseService.findPurchaseById(purchaseId);
			if(null == purchaseOld || !pUser.getCityId().equals(purchaseOld.getCityId())){
				return "你设置的默认城市与实际不符";
			}
			purchase.setId(purchaseId);

			List<PurchaseItem> purchaseItemList = new ArrayList<PurchaseItem>();

			int rowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("rowCount"), "0"));

			//判断便利店用户下单规格
			for (int i = 0; i < rowCount; i++) {
				String specType = StringUtils.defaultString(req.getParameter("specification" + i), "");
				int itemId = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("itemId" + i), "0"));
				if (itemId == 0) {
					continue;
				}
				int num = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("purchaseNum" + i), "0"));
				try {
					PurchaseItem purchaseItem = new PurchaseItem();
					purchaseItem.setPurchaseId(purchaseId);
					purchaseItem.setItemId(itemId);
					purchaseItem.setPurchaseNum(num);
					purchaseItem.setItemRemark(req.getParameter("remark" + i));


					if(StringUtils.isEmpty(specType)){
						logger.warn("itemid :" + itemId + ",purchase id :" + purchaseId + " not found specType");
					}
					purchaseItem.setSize(specType);
					purchaseItemList.add(purchaseItem);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}

			Integer orderTotalNum = 0;
//			Long orderTotalFee = 0l;
			Long orderTotalCost = 0l;
//			Long notaxInclusiveTotalCost = 0l;
			purchase.setPurchaseItemList(purchaseItemList);
			List<PurchaseItem> updateItem = new ArrayList<PurchaseItem>();

			for (PurchaseItem purchaseItem : purchaseItemList) {
				Item item = this.itemService.findById(purchaseItem.getItemId());

				if(purchaseItem.getPurchaseNum()==null||purchaseItem.getPurchaseNum()==0){
					return "商品id :" + purchaseItem.getItemId() + ",数量为0";
				}
				if (item == null) {
					return "商品id :" + purchaseItem.getItemId() + ",无法找到商品数据";
				}

				PurchaseItem purchaseItemInfo = this.purchaseItemService.findItemsByItemIdAndCity(purchaseItem.getItemId(), pUser.getCityId());
				PurchaseItem purchaseItemOld = this.purchaseItemService.findbyPurchaseIdAndItemid(purchaseItem.getPurchaseId(), purchaseItem.getItemId());
				long price = 0l; //单价
				long costPrice = 0l; // 成本价
				long notaxInclusiveCostPrice = 0l;
				price = item.getPrice();
				costPrice = item.getCostPrice();
				notaxInclusiveCostPrice = item.getNotaxInclusiveCostPrice();
//				purchaseItem.setConvertNum(purchaseItem.getPurchaseNum()*item.getConvertNum());
				purchaseItem.setSupplierId(purchase.getSupplierId());
				purchaseItem.setSupplierName(purchase.getSupplierName());
				purchaseItem.setCityId(purchase.getCityId());
				purchaseItem.setBarcode(item.getBarcode());
				purchaseItem.setItemName(item.getItemName());
				purchaseItem.setConvertNum(purchaseItemInfo.getConvertNum());
				purchaseItem.setCategoryId(item.getCategoryId());
				purchaseItem.setCategoryName(purchaseItemInfo.getCategoryName());
				purchaseItem.setCostPrice(purchaseItemInfo.getCostPrice());
				purchaseItem.setStockNum(purchaseItemInfo.getStockNum());
				purchaseItem.setTotalPrice(purchaseItem.getPurchaseNum() * purchaseItem.getCostPrice());
				purchaseItem.setUseNum(purchaseItemInfo.getUseNum());
				purchaseItem.setItemWeight(purchaseItemInfo.getItemWeight());
				purchaseItem.setShelfLife(purchaseItemInfo.getShelfLife());
				purchaseItem.setSize(purchaseItemInfo.getSize());
				if (purchaseItemOld != null) {
					purchaseItem.setPurchasedNum(purchaseItemOld.getPurchasedNum());
					purchaseItem.setStockFlag(purchaseItemOld.getStockFlag());
					if (purchaseItem.getPurchaseNum() == 0) {
						purchaseItem.setPurchasedNum(0);
					} else if(purchaseItem.getPurchaseNum() >= purchaseItem.getPurchasedNum()) {
						purchaseItem.setStatus(purchaseItemOld.getStatus());
					} else {
						return "进货数量不能小于之前的数量";
					}
					purchaseItem.setStatus(purchaseItemOld.getStatus());
				} else {
					purchaseItem.setPurchasedNum(0);
					purchaseItem.setStatus(0);
				}
//				pi.setPurchaseNum(newPurchaseNum);
				updateItem.add(purchaseItem);
				orderTotalCost += purchaseItemInfo.getCostPrice()*purchaseItem.getPurchaseNum();
				orderTotalNum += purchaseItem.getPurchaseNum();

			}


			if(orderTotalNum<=0){
				return "订单中商品数量为0";
			}

			purchase.setTotalPrice(orderTotalCost);
			PersonUser sbuser=(PersonUser) req.getSession().getAttribute(Constant.USER_KEY);

			purchaseService.updatePurchaseAndItems(purchase, updateItem);

			this.saveLog(req.getSession(),purchase, "修改订单，purchaseId:"+purchase.getId(),pUser.getCityId());
			return "保存成功,"+purchase.getId();
		} catch (Throwable e) {
			logger.error("编辑采购订单失败", e);
			return e.getMessage();
		}

	}

	private void fillCommonData2(ModelAndView view,int cityId){
		List<ItemCategory> catList = itemCategoryService.findAllOneCatsByCityId(cityId);
		view.addObject("catList", catList);

		int cateId1=0;
		if(CollectionUtils.isNotEmpty(catList)){
			cateId1  = catList.get(0).getId();
		}

		view.addObject("cateId1", cateId1);
		List<HashMap<String, Object>> itemList = itemService.findAllWithStockByCatIdAndCityId(null,cityId);
		ArrayList<Integer> ids = this.orderService.findLastTwoOrderItemByCid(cityId);
		ArrayList<Integer> ids2 = this.shopBlackListService.findAllByShopId(cityId);
		ArrayList<Integer> ids3 = this.itemService.findNewItem();
		ArrayList<Integer> ids4 = this.itemService.findRecommend();
		ArrayList<Integer> ids5 = this.itemService.findBang();
		ArrayList<Integer> ids6 = this.itemService.findFreeSpecialSupply();
		for (Integer id : ids2) {
			for (HashMap<String, Object> map : itemList) {
				if(Integer.valueOf(map.get("id").toString()).equals(id)){
					map.put("itemName", "xxxxxxxxxx"+map.get("itemName").toString());
					break;
				}
			}
		}
		for (Integer id : ids5) {
			for (HashMap<String, Object> map : itemList) {
				if(Integer.valueOf(map.get("id").toString()).equals(id)){
					map.put("itemName", "$ "+map.get("itemName").toString());
					break;
				}
			}
		}
		for (Integer id : ids4) {
			for (HashMap<String, Object> map : itemList) {
				if(Integer.valueOf(map.get("id").toString()).equals(id)){
					map.put("itemName", "! "+map.get("itemName").toString());
					break;
				}
			}
		}
		for (Integer id : ids3) {
			for (HashMap<String, Object> map : itemList) {
				if(Integer.valueOf(map.get("id").toString()).equals(id)){
					map.put("itemName", "+ "+map.get("itemName").toString());
					break;
				}
			}
		}
		for (Integer id : ids) {
			for (HashMap<String, Object> map : itemList) {
				if(Integer.valueOf(map.get("id").toString()).equals(id)){
					map.put("itemName", "# "+map.get("itemName").toString());
					break;
				}
			}
		}
		for (Integer id : ids6) {
			for (HashMap<String, Object> map : itemList) {
				if(Integer.valueOf(map.get("id").toString()).equals(id)){
					map.put("itemName", "~ "+map.get("itemName").toString());
					break;
				}
			}
		}

//		for (int i = 0; i < itemList.size(); i++) {
//			List<ItemTaste> tastes = itemTasteService.findByItemId(Integer.valueOf(itemList.get(i).get("id").toString()));
//			for (ItemTaste taste : tastes) {
//				String remark = taste.getTasteName() == null ? "" : taste.getTasteName() + "()；";
//				itemList.get(i).put("remark", (itemList.get(i).get("remark") == null ? "" + remark
//						: itemList.get(i).get("remark") + remark));
//			}
//			view.addObject("itemList", itemList);
//		}

		view.addObject("itemList", itemList);
	}

	private void saveLog(HttpSession session,Purchase dto,String content,Integer cityId){
		try{
			SysLog sysLog = new SysLog();
			sysLog.setContent(content);
			sysLog.setCreateTime(new Date());
			sysLog.setUserId(SessionHelper.getUserId(session));
			sysLog.setCityId(cityId);
			sysLog.setDataType(LogDataTypeEnum.PURCHASE.getName());
			sysLog.setDataId(dto.getId());

			String dataContent = new Gson().toJson(dto,
					new TypeToken<Purchase>() {
					}.getType());

			sysLog.setDataContent(dataContent);

			logService.createLog(sysLog);
		}catch(Exception e){
			logger.error("保存日志失败",e);
		}
	}

	private String addStorage(PersonUser personUser, String number, List<PurchaseItem> purchaseItemList, HttpServletRequest request) {
		try {
			Storage storage = new Storage();
			storage.setCityId(personUser.getCityId());
			List<StorageItem> skuList = new ArrayList<StorageItem>();
			String numbers = request.getParameter("numbers");
			storage.setNumber("From purchase:" + numbers + ":" + number);
			long totalFeeAll = 0;

			for (PurchaseItem purchaseItem : purchaseItemList) {
				int itemId = purchaseItem.getItemId();
				Item item = itemService.findById(itemId);

				String size = ItemSizeEnum.BUY_SIZE.getName();

				long buyPrice = item.calPrice(size);

				int buyNum = purchaseItem.getUseNum();
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

			Integer supplierId = purchaseItemList.get(0).getSupplierId();
			if (supplierId != null) {
				storage.setSupplierId(supplierId);
			}
			storage.setTotalFee(totalFeeAll);
			storage.setStorageItemList(skuList);
			storageService.create(storage);

			this.saveLog2(request.getSession(), storage, "新增入库单，id:" + storage.getId());
			return "添加成功";
		} catch (Exception e) {
			logger.error("添加入库单失败", e);
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
