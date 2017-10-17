package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
import com.b2b.common.util.PurchaseNumberGenerator;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.service.*;
import com.b2b.web.util.SessionHelper;
import com.b2b.web.wx.util.Properties;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.collections.CollectionUtils;
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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/purchaseAdd")
public class PurchaseAddController {

	private static final Logger logger = LoggerFactory.getLogger(PurchaseAddController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

	@Autowired
	PurchaseService purchaseService;

	@Autowired
	PurchaseItemService purchaseItemService;

	@Autowired
	OrderService orderService;

	@Autowired
	SupplierService supplierService;
	
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
	ItemTasteService itemTasteService;

	@RequestMapping("purchaseAdd.htm")
	public ModelAndView purchaseAdd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("purchase/purchaseAdd");
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return new ModelAndView("noCity");
			}
			mv.addObject("executedTime", new Date());

			this.fillCommonData(mv, user.getCityId());
		TestController.getMenuPoint(mv, request);
		return mv;
	}

	private void fillCommonData(ModelAndView view,int cityId){
		List<ItemCategory> catList =null;
		catList= itemCategoryService.findAllOneCatsByCityId(cityId);
		view.addObject("catList", catList);

		int cateId1=0;
		if(CollectionUtils.isNotEmpty(catList)){
			cateId1  = catList.get(0).getId();
		}

		view.addObject("cateId1", cateId1);

		List<Item> itemList = itemService.findAllWithStockByCityId(cityId);
//		for (int i = 0; i < itemList.size(); i++) {
//			List<ItemTaste> tastes = itemTasteService.findByItemId(itemList.get(i).getId());
//			for (ItemTaste taste : tastes) {
//				String remark = taste.getTasteName() == null ? "" : taste.getTasteName() + "()；";
//				itemList.get(i).setRemark(itemList.get(i).getRemark() == null ? "" + remark
//						: itemList.get(i).getRemark() + remark);
//			}
			view.addObject("itemList", itemList);
//		}

	}


	@RequestMapping(value = "/purchaseAdd.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String add(HttpServletRequest req) {
		try {
			PersonUser pUser = (PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			if(null == pUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			String supplierId = req.getParameter("supplierId");
			if (StringUtils.isEmpty(supplierId)) {
				return "供应商不能为空!";
			}

			Date time = null;
			String timeStr = req.getParameter("executedTime");
			if (StringUtils.isNotBlank(timeStr)) {
				time = DateUtil.parseDateStr(timeStr, DATE_FORMAT_YMD);
			}

			Supplier supplier = supplierService.findById(Integer.parseInt(supplierId));
			String purchaseId = PurchaseNumberGenerator.getPurchaseId(this.purchaseItemService.findMaxPurchaseId());

			Purchase purchase = new Purchase();
			purchase.setId(purchaseId);
			purchase.setSupplierId(supplier.getId());
			purchase.setSupplierName(supplier.getSupplierName());
			purchase.setCityId(pUser.getCityId());
			purchase.setPurchasedTime(time);
//			purchase.setTotalPrice(purchaseItem.getTotalPrice());
			purchase.setPrintFlag(0);
			purchase.setStatus(0);
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			date = calendar.getTime();
			purchase.setCreatedTime(date);
			purchase.setExecutedTime(date);


			List<PurchaseItem> purchaseItemList = new ArrayList<PurchaseItem>();
			int rowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("rowCount"), "0"));
			//判断便利店用户下单规格
			for (int i = 0; i < rowCount; i++) {
				int itemId = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("itemId" + i), "0"));
				if (itemId == 0) {
					continue;
				}
				int num = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("buyNum" + i), "0"));

				String remark = StringUtils.defaultString(req.getParameter("remark" + i), "");
				try {
					PurchaseItem pi = new PurchaseItem();
					pi.setPurchaseId(purchase.getId());
					pi.setItemId(itemId);
					pi.setPurchaseNum(num);
					pi.setItemRemark(remark);
					pi.setPurchasedNum(0);
					double par = Double.parseDouble(req.getParameter("buyPrice" + i));
					Double a = par*100;
					long price = Math.round(a);
					pi.setCostPrice(price);
					pi.setTotalPrice(price*num);

					purchaseItemList.add(pi);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
			Integer orderTotalNum = 0;
			Long orderTotalFee = 0l;
			Long orderTotalCost = 0l;
			Long notaxInclusiveTotalCost = 0l;
			purchase.setPurchaseItemList(purchaseItemList);
			for (PurchaseItem pi : purchaseItemList) {
				if(pi.getPurchaseNum()==null||pi.getPurchaseNum()==0){
					return "商品 :" + pi.getItemId() + ",数量为0";
				}
				Item item = this.itemService.findById(pi.getItemId());
				if (item == null) {
					return "商品 :" + pi.getItemId() + ",无法找到商品数据";
				}

				PurchaseItem plan = purchaseItemService.findItemsByItemIdAndCity(pi.getItemId(), pUser.getCityId());

				pi.setBarcode(item.getBarcode());
				pi.setItemName(item.getItemName());
				pi.setConvertNum(Integer.valueOf(item.getConvertNum()));
				pi.setCategoryId(plan.getCategoryId());
				pi.setCategoryName(plan.getCategoryName());
				pi.setSize(plan.getSize());
//				pi.setCostPrice(pi);
				pi.setStockNum(plan.getStockNum());
//				pi.setTotalPrice(newPurchaseNum * purchase.getCostPrice());
				pi.setUseNum(plan.getUseNum());
				pi.setItemWeight(plan.getItemWeight());
				pi.setShelfLife(plan.getShelfLife());
				pi.setSupplierId(Integer.valueOf(supplierId));
				pi.setSupplierName(supplier.getSupplierName());
//				pi.setPurchaseNum(newPurchaseNum);
				pi.setStatus(0);
				pi.setCityId(pUser.getCityId());
				orderTotalNum += pi.getPurchaseNum();
				orderTotalCost += pi.getCostPrice()*pi.getPurchaseNum();
			}
			if(orderTotalNum<=0){
				return "订单中商品数量为0";
			}
			purchase.setTotalPrice(orderTotalCost);

			purchaseService.createPurchase(purchase);
			this.saveLog(req.getSession(),purchase, "添加采购订单，orderNo:"+purchase.getId(),pUser.getCityId());
			return "添加成功";

		} catch (Throwable e) {
			logger.error("添加订单失败", e);
			return e.getMessage();
		}
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


}
