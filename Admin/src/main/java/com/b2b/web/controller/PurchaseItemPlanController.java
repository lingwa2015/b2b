package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
import com.b2b.common.util.PurchaseNumberGenerator;
import com.b2b.page.Page;
import com.b2b.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/purchaseItemPlan")
public class PurchaseItemPlanController {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseItemPlanController.class);

	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

	@Autowired
	PurchaseItemService purchaseItemService;

	@Autowired
	PurchaseService purchaseService;

	@Autowired
	ItemCategoryService itemCategoryService;

	@Autowired
	SupplierService supplierService;

	@Autowired
	ItemTasteService itemTasteService;
	
	@Autowired
	private StockCheckTempService stockCheckTempService;

	@RequestMapping("purchaseItemList.htm")
	public ModelAndView getStockCheckListPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("purchase/purchasePlan");
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));

		//架上条件
		String isDown = request.getParameter("isDown");
		if(StringUtils.isEmpty(isDown)){
			isDown = "";
		}
		mv.addObject("isDown", isDown);
		String grade = request.getParameter("grade");
		if(StringUtils.isEmpty(grade)){
			grade = "";
		}
		mv.addObject("grade", grade);

		//商品类目
		int onecate = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("onecate"), "0"));
		if(onecate>0){
			List<ItemCategory> leaveCats = this.itemCategoryService.findAllTwoCatsByCityId(personUser.getCityId());
			mv.addObject("leaveCats", leaveCats);
		}
		mv.addObject("onecate", onecate);

		//商品查询
		String itemName = request.getParameter("itemName");
		mv.addObject("itemName", itemName);

		//采购日期
		String timeStr = request.getParameter("time");
		Integer newUserNum = Integer.parseInt(StringUtils.defaultIfBlank(request.getParameter("time"), "0"));
		mv.addObject("newUserNum", newUserNum);
		Date time = null;
		if (StringUtils.isNotBlank(timeStr)) {
			time = DateUtil.parseDateStr(timeStr, DATE_FORMAT_YMD);
			mv.addObject("time", timeStr);
		}

		PageHelper.startPage(currentPage, 50);
		List<PurchaseItem> plans = purchaseItemService.findItemPlan(isDown, onecate, itemName, personUser.getCityId(), grade, newUserNum);
		//添加口味备注
		for (int i = 0; i < plans.size(); i++) {
			if (!StringUtils.isNotBlank(plans.get(i).getItemRemark())) {
				List<ItemTaste> tastes = itemTasteService.findByItemId(plans.get(i).getItemId());
				for (ItemTaste taste : tastes) {
					String remark = taste.getTasteName() == null ? "" : taste.getTasteName() + "()；";
					plans.get(i).setItemRemark(plans.get(i).getItemRemark() == null ? "" + remark
							: plans.get(i).getItemRemark() + remark);
				}
			}

//			Integer useNum = this.purchaseItemService.findUseNumByItemId(plans.get(i).getItemId());
//			plans.get(i).setUseNum(useNum);
		}

		List<ItemCategory> catList = this.itemCategoryService.findAllOneCatsByCityId(personUser.getCityId());
		mv.addObject("catList", catList);

		List<Supplier> suppliers = supplierService.findByCityId(personUser.getCityId());
		mv.addObject("suppliers", suppliers);

		PageInfo<PurchaseItem> info = new PageInfo<PurchaseItem>(plans);
		Page<PurchaseItem> page = new Page<PurchaseItem>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());


		mv.addObject("page", page);
		TestController.getMenuPoint(mv, request);
		return mv;
	}

	@RequestMapping("add.do")
	@ResponseBody
	public String addPlan(@RequestParam("itemId")String itemId, @RequestParam("itemName")String itemName,
						  @RequestParam("newConvertNum")String newConvertNum, @RequestParam("categoryName")String categoryName,
						  @RequestParam("categoryId")String categoryId, @RequestParam("size")String size,
						  @RequestParam("costPrice")String costPrice, @RequestParam("stockNum")String stockNum,
						  @RequestParam("useNum")String useNum, @RequestParam("itemWeight")String itemWeight,
						  @RequestParam("itemRemark")String itemRemark, @RequestParam("shelfLife")String shelfLife,
						  @RequestParam("supplierId")String supplierId, @RequestParam("supplierName")String supplierName,
						  @RequestParam("purchaseNum")String purchaseNum, @RequestParam("barcode")String barcode,
						  HttpServletRequest request){
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "201";
			}

			Integer id = Integer.valueOf(itemId);

			Integer newPurchaseNum = Integer.valueOf(StringUtils.defaultIfBlank(purchaseNum, "0"));
			if (!newPurchaseNum.toString().equals("-1")) {
				Integer supplier = Integer.valueOf(supplierId);
				PurchaseItem purchaseItem = this.purchaseItemService.findbyItemId(id);
				String purchaseId = this.purchaseItemService.findbySupplierId(supplier);

				if (purchaseItem == null) {
					PurchaseItem purchase = new PurchaseItem();
					if (!StringUtils.isNotBlank(purchaseId)) {
						purchaseId = PurchaseNumberGenerator.getPurchaseId(this.purchaseItemService.findMaxPurchaseId());
					}
					purchase.setPurchaseId(purchaseId);
					purchase.setItemId(id);
					purchase.setItemName(itemName);
					purchase.setBarcode(barcode);
					purchase.setConvertNum(Integer.valueOf(StringUtils.defaultIfBlank(newConvertNum, "0")));
					purchase.setCategoryId(Integer.valueOf(StringUtils.defaultIfBlank(categoryId, "0")));
					purchase.setCategoryName(categoryName);
					purchase.setSize(size);
					purchase.setCostPrice(Long.valueOf(StringUtils.defaultIfBlank(costPrice, "0")));
					purchase.setStockNum(Integer.valueOf(StringUtils.defaultIfBlank(stockNum, "0")));
					purchase.setTotalPrice(newPurchaseNum * purchase.getCostPrice());
					purchase.setPurchasedNum(0);
					purchase.setTotalPrice(newPurchaseNum * purchase.getCostPrice());
//					purchase.setUseNum(Integer.valueOf(useNum)+newPurchaseNum);
					if (!itemWeight.isEmpty()) {
						purchase.setItemWeight(new BigDecimal(itemWeight));
					}
					purchase.setItemRemark(itemRemark);
					if (!shelfLife.isEmpty()) {
						purchase.setShelfLife(Integer.valueOf(shelfLife));
					}
					purchase.setSupplierId(Integer.valueOf(supplierId));
					purchase.setSupplierName(supplierName);
					purchase.setPurchaseNum(newPurchaseNum);
					purchase.setStatus(4);
					purchase.setCityId(personUser.getCityId());
					this.purchaseItemService.insert(purchase);
				} else {
					if (!StringUtils.isNotBlank(purchaseId)) {
						purchaseId = PurchaseNumberGenerator.getPurchaseId(this.purchaseItemService.findMaxPurchaseId());
					}
					purchaseItem.setPurchaseId(purchaseId);
					purchaseItem.setItemRemark(itemRemark);
					purchaseItem.setSupplierId(Integer.valueOf(supplierId));
					purchaseItem.setSupplierName(supplierName);
					purchaseItem.setPurchaseNum(newPurchaseNum);
					purchaseItem.setTotalPrice(newPurchaseNum * purchaseItem.getCostPrice());
					this.purchaseItemService.update(purchaseItem);
				}
			} else {
				this.purchaseItemService.deleteByItemId(id);
			}


			return "200";
		} catch (Exception e) {
			logger.error("采购商品失败", e);
		}
		return "201";
	}

	@RequestMapping("insertPurchase.do")
	@ResponseBody
	public String insertPurchase(@RequestParam("time")String timeStr, HttpServletRequest request){
		try {

			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "201";
			}
			Date time = null;
			if (StringUtils.isNotBlank(timeStr)) {
				time = DateUtil.parseDateStr(timeStr, DATE_FORMAT_YMD);
			}

			List<PurchaseItem> purchaseItems = this.purchaseItemService.findPlans(personUser.getCityId());

			if (purchaseItems.size() == 0) {
				return "202";
			}

			for (PurchaseItem purchaseItem : purchaseItems) {
				Purchase purchase = new Purchase();
				purchase.setId(purchaseItem.getPurchaseId());
				purchase.setSupplierId(purchaseItem.getSupplierId());
				purchase.setSupplierName(purchaseItem.getSupplierName());
				purchase.setCityId(personUser.getCityId());
				purchase.setPurchasedTime(time);
				purchase.setTotalPrice(purchaseItem.getTotalPrice());
				purchase.setPrintFlag(0);
				purchase.setStatus(0);
				Date date = new Date();
				Calendar calendar = Calendar.getInstance();
				date = calendar.getTime();
				purchase.setCreatedTime(date);
				purchase.setExecutedTime(date);

				this.purchaseService.insert(purchase);
			}

			List<PurchaseItem> purchaseItemPlans = this.purchaseItemService.findItemPlans(personUser.getCityId());
			for (PurchaseItem purchaseItemplan : purchaseItemPlans) {
				purchaseItemplan.setStatus(0);
//				purchaseItemplan.setUseNum(purchaseItemplan.getPurchaseNum());
				this.purchaseItemService.update(purchaseItemplan);
			}
			return "200";
		} catch (Exception e) {
			logger.error("采购商品失败", e);
		}
		return "201";
	}


}
