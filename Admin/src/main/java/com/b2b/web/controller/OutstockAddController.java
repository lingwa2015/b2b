package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.service.*;
import com.b2b.web.util.SessionHelper;
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
@RequestMapping("/outstockAdd")
public class OutstockAddController {

	private static final Logger logger = LoggerFactory.getLogger(OutstockAddController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

	@Autowired
	OutstockService outstockService;

	@Autowired
	OutstockItemService outstockItemService;

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
	LogService logService;

	@Autowired
	ItemTasteService itemTasteService;

	@RequestMapping("outstockAdd.htm")
	public ModelAndView outstockAdd(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("outstock/outstockAdd");
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

	@RequestMapping(value = "/outstockAdd.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
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

			String remark = req.getParameter("remark");

			Supplier supplier = supplierService.findById(Integer.parseInt(supplierId));
			Long outstockId = outstockService.getMaxOutstockId();

			Outstock outstock = new Outstock();
			outstock.setId(outstockId);
			outstock.setSupplierId(supplier.getId());
			outstock.setSupplierName(supplier.getSupplierName());
			outstock.setCityId(pUser.getCityId());
			outstock.setOutstockTime(time);
//			Outstock.setTotalPrice(purchaseItem.getTotalPrice());
			outstock.setPrintFlg(0);
			outstock.setStatus(0);
			Date date = new Date();
			Calendar calendar = Calendar.getInstance();
			date = calendar.getTime();
			outstock.setExecutedTime(date);
			outstock.setRemark(remark);
			outstock.setUserId(pUser.getId());
			outstock.setUserName(pUser.getUserName());

			List<OutstockItem> outstockItemList = new ArrayList<OutstockItem>();
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
				int cnum = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("convertnum" + i), "0"));
				String size = StringUtils.defaultString(
						req.getParameter("size" + i), "");

				String itemRemark = StringUtils.defaultString(req.getParameter("remark" + i), "");
				try {
					OutstockItem pi = new OutstockItem();
					pi.setOutstockId(outstock.getId());
					pi.setItemId(itemId);
					pi.setOutstockNum(num);
					pi.setItemRemark(itemRemark);
					pi.setConvertNum(cnum);
					pi.setSize(size);
					double par = Double.parseDouble(req.getParameter("buyPrice" + i));
					Double a = par*100;
					long price = Math.round(a);
					pi.setCostPrice(price);
					pi.setTotalPrice(price*num);

					outstockItemList.add(pi);
				} catch (Exception e) {
					logger.error(e.getMessage(), e);
				}
			}
			Integer orderTotalNum = 0;
			Long orderTotalFee = 0l;
			Long orderTotalCost = 0l;
			Long notaxInclusiveTotalCost = 0l;
			outstock.setOutstockItemList(outstockItemList);
			for (OutstockItem pi : outstockItemList) {
				if(pi.getOutstockNum()==null||pi.getOutstockNum()==0){
					return "商品 :" + pi.getItemId() + ",数量为0";
				}
				Item item = this.itemService.findById(pi.getItemId());
				if (item == null) {
					return "商品 :" + pi.getItemId() + ",无法找到商品数据";
				}

				PurchaseItem plan = purchaseItemService.findItemsByItemIdAndCity(pi.getItemId(), pUser.getCityId());

				pi.setItemName(item.getItemName());
				pi.setCategoryId(plan.getCategoryId());
				pi.setCategoryName(plan.getCategoryName());
				long costPrice = 0;
				if (pi.getSize().equals("SIZE")) { //普通规格
					costPrice = item.getCostPrice();
				} else if (pi.getSize().equals("SALE_SIZE")) { // 零售
					costPrice = item.getSaleCostPrice();
				} else if (pi.getSize().equals("BUY_SIZE")) { //批发
					costPrice = item.getBuyPrice();
				} else {
					return "商品 :" + item.getItemName() + ",无法找到规格数据";
				}
				String sizeValue = item.calSize(pi.getSize());
				pi.setSize(sizeValue);

				pi.setPrice(costPrice);
				pi.setCostPrice(costPrice);
				pi.setTotalPrice(pi.getOutstockNum() * pi.getCostPrice());
				orderTotalNum += pi.getOutstockNum();
				orderTotalCost += pi.getCostPrice()*pi.getOutstockNum();
			}
			if(orderTotalNum<=0){
				return "订单中商品数量为0";
			}
			outstock.setTotalPrice(orderTotalCost);

			outstockService.createOutstock(outstock);
			this.saveLog(req.getSession(),outstock, "添加采购订单，outstockId:"+outstock.getId(),pUser.getCityId());
			return "添加成功";

		} catch (Throwable e) {
			logger.error("添加订单失败", e);
			return e.getMessage();
		}
	}

	private void saveLog(HttpSession session,Outstock dto,String content,Integer cityId){
		try{
			SysLog sysLog = new SysLog();
			sysLog.setContent(content);
			sysLog.setCreateTime(new Date());
			sysLog.setUserId(SessionHelper.getUserId(session));
			sysLog.setCityId(cityId);
			sysLog.setDataType(LogDataTypeEnum.PURCHASE.getName());
//			sysLog.setDataId(dto.getId());

			String dataContent = new Gson().toJson(dto,
					new TypeToken<Outstock>() {
					}.getType());

			sysLog.setDataContent(dataContent);

			logService.createLog(sysLog);
		}catch(Exception e){
			logger.error("保存日志失败",e);
		}
	}


}
