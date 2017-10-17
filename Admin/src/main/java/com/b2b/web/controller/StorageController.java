package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.util.DateUtil;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.*;
import com.b2b.web.util.SessionHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@RequestMapping("/storage")
public class StorageController {

	private static final Logger logger = LoggerFactory.getLogger(StorageController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

	@Autowired
	SupplierService supplierService;

	@Autowired
	StorageService storageService;

	@Autowired
	ItemCategoryService itemCategoryService;

	@Autowired
	ItemService itemService;

	@Autowired
	LogService logService;

	@RequestMapping("storageList.htm")
	public ModelAndView getStorageListPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("storage/list");
		try {
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return new ModelAndView("noCity");
			}
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));

			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");
			
			String numbers = request.getParameter("numbers");
			String supplierName = request.getParameter("supplierName");
			mv.addObject("numbers", numbers);
			mv.addObject("supplierName", supplierName);
			String itemName = request.getParameter("itemName");
			mv.addObject("itemName", itemName);
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

			Storage storage = new Storage();


			Page<Pair<Storage, List<StorageItem>>> storagePage = storageService
					.findStorage(storage, startTime, endTime,numbers,supplierName,currentPage,
							Page.DEFAULT_PAGE_SIZE,user.getCityId(),itemName);


			mv.addObject("page", storagePage);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<Pair<Storage, List<StorageItem>>> page = new Page<Pair<Storage, List<StorageItem>>>(1, 1, Page.DEFAULT_PAGE_SIZE,new ArrayList<Pair<Storage, List<StorageItem>>>());
			mv.addObject("page", page);
		}
		TestController.getMenuPoint(mv, request);
		return mv;
	}

	@RequestMapping(value = "addStorage.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addSave(HttpServletRequest request) {
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			Storage storage = new Storage();
			//float totalFeeAll=Float.parseFloat(request.getParameter("totalFeeAll"));
			storage.setCityId(personUser.getCityId());
			int rowCount = Integer.parseInt(request.getParameter("rowCount"));
			List<StorageItem> skuList = new ArrayList<StorageItem>();
			String number = request.getParameter("numbers");
			storage.setNumber(number);
			long totalFeeAll =0;

			for (int i = 0; i < rowCount; i++) {
				String itemIds = request.getParameter("skuId" + i);
				if (StringUtils.isEmpty(itemIds)) {
					continue;
				}
				int itemId = Integer.parseInt(itemIds);
				Item item = itemService.findById(itemId);

				String size = request
						.getParameter("size" + i);

				//long buyPrice =(item.getBuyPrice()==null)?0:item.getBuyPrice();

				long buyPrice = item.calPrice(size);

				int buyNum = Integer.parseInt(request
						.getParameter("buyNum" + i));
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

			String executedTimeStr = request.getParameter("executedTime");
			if (StringUtils.isEmpty(executedTimeStr)) {
				storage.setExecutedTime(new Date());
			} else {
				storage.setExecutedTime(DateUtil.parseDateStr(executedTimeStr, DATE_FORMAT_YMD));
			}
			String supplierId = request.getParameter("supplierId");
			if (StringUtils.isNotBlank(supplierId)) {
				storage.setSupplierId(Integer.parseInt(supplierId));
			}
			storage.setTotalFee(totalFeeAll);
			storage.setStorageItemList(skuList);
			storageService.create(storage);

			this.saveLog(request.getSession(),storage, "新增入库单，id:"+storage.getId());
			return "添加成功";
		} catch (Exception e) {
			logger.error("添加入库单失败", e);
			return e.getMessage();
		}
	}

	@RequestMapping(value = "storageAdd.htm", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request) throws Exception {
		try {

			ModelAndView mv = new ModelAndView("storage/add");
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return new ModelAndView("noCity");
			}
			mv.addObject("executedTime", new Date());
			this.fillCommonSupplier(mv,user.getCityId());
			this.fillCommonData(mv, user.getCityId());
			TestController.getMenuPoint(mv, request);
			return mv;
		} catch (Exception e) {
			logger.error("show storage detail.", e);
			throw e;
		}
	}

	private void fillCommonSupplier(ModelAndView view,Integer cityId) {
		List<Supplier> supplierList = null;
		supplierList = supplierService.findByCityId(cityId);
		view.addObject("supplierList", supplierList);
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
		
		List<Item> itemList = itemService.findAllWithStockAndBarcodeByCityId(cityId);
		view.addObject("itemList", itemList);
		Item item1 = null;
		if(CollectionUtils.isNotEmpty(itemList)){
			for(Item item : itemList){
				if(item.getCategoryId().intValue()==cateId1){
					item1 = item;
					break;
				}
			}
		}

		view.addObject("item1", item1);

	}

//	@RequestMapping(value = "storageDetail.htm", method = RequestMethod.GET)
//	public ModelAndView detail(HttpServletRequest request) throws Exception {
//		try {
//
//			int id = Integer.valueOf(StringUtils.defaultIfBlank(
//					request.getParameter("id"), "0"));
//			if (id == 0) {
//				return new ModelAndView("errorPage");
//			}
//			Pair<Storage, List<StorageItem>> pair = storageService
//					.findById(id);
//
//			return new ModelAndView("storage/detail", "pair", pair);
//		} catch (Exception e) {
//			logger.error("show storage detail.", e);
//			throw e;
//		}
//	}

	@RequestMapping(value = "deleteStorage.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delete(HttpServletRequest request) throws Exception {
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			int id = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("id"), "0"));
			if (id == 0) {
				return "删除失败";
			}

			Pair<Storage, List<StorageItem>> pair = storageService.findById(id);
			
			if(pair!=null){
				Storage dto = pair.getKey();
				if(!dto.getCityId().equals(personUser.getCityId())){
					return "你设置的默认城市与操作城市不符";
				}
				storageService.delete(id);

				this.saveLog(request.getSession(),dto, "删除入库单，id:"+id);
			}
		} catch (Exception e) {
			logger.error("删除入库单失败.", e);
			return e.getMessage();
		}

		return "删除成功";
	}

	@RequestMapping(value = "updateTotalFee.do",  produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String updateTotalFee(HttpServletRequest request) throws Exception {
        try {
        	storageService.updateTotalFee();
        } catch (Exception e) {
            logger.error("更新入库单费用失败.", e);
            return e.getMessage();
        }

        return "更新成功";
    }

	private void saveLog(HttpSession session,Storage dto,String content){
		try{
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
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
	
	@RequestMapping(value = "/print.do", method = RequestMethod.POST)
	public ModelAndView printOrders(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("storage/print");

		String orderNos = request.getParameter("storageId");
		if (StringUtils.isBlank(orderNos)) {
			throw new NullPointerException("入库单参数异常");
		}
		String[] orders = orderNos.split(",");
		//key: userId 按用户合并订单数据
		List<Pair<Storage, List<StorageItem>>> list = new ArrayList<Pair<Storage, List<StorageItem>>>();
		for (String orderNo : orders) {
			Pair<Storage,List<StorageItem>> pair = this.storageService.findById(Integer.valueOf(orderNo));
			if (pair == null || pair.getLeft() == null) {
				logger.error("订单数据异常, orderNo:" + orderNo);
				continue;
			}
			list.add(pair);
		}

		mv.addObject("list", list);


		return mv;
	}
}
