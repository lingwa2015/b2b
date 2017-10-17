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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;


@Controller
@RequestMapping("/stockCheck")
public class StockCheckController {

	private static final Logger logger = LoggerFactory.getLogger(StockCheckController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";

	@Autowired
	StockCheckService stockCheckService;

	@Autowired
	ItemCategoryService itemCategoryService;
	
	@Autowired
	StockService stockService;

	@Autowired
	ItemService itemService;

	@Autowired
	LogService logService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	AccountLockService accountLockService;
	
	@Autowired
	private StockCheckTempService stockCheckTempService;

	@RequestMapping("stockCheckList.htm")
	public ModelAndView getStockCheckListPage(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("stockCheck/list");
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return new ModelAndView("noCity");
			}
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));

			String startTimeStr = request.getParameter("startTime");
			String endTimeStr = request.getParameter("endTime");
			String userName = request.getParameter("userName");
			String type = request.getParameter("type");
			mv.addObject("userName", userName);
			mv.addObject("type", type);
			Date startTime = null;
			Date endTime = null;
			String itemName = request.getParameter("itemName");
			mv.addObject("itemName", itemName);

			if (StringUtils.isNotBlank(startTimeStr)) {
				startTime = DateUtil.parseDateStr(startTimeStr, DATE_FORMAT_YMD);
				mv.addObject("startTime", startTimeStr);
			}

			if (StringUtils.isNotBlank(endTimeStr)) {
				endTime = DateUtil.parseDateStr(endTimeStr, DATE_FORMAT_YMD);
				mv.addObject("endTime", endTimeStr);
			}


			Page<Pair<StockCheck, List<StockCheckItem>>> stockCheckPage = stockCheckService
					.findStockCheck(userName,type, startTime, endTime, currentPage,
							Page.DEFAULT_PAGE_SIZE,personUser.getCityId(),itemName);
			Long money = stockCheckService.findTotalByCondition(userName,type, startTime, endTime,personUser.getCityId(),itemName);
			mv.addObject("money", money);
			mv.addObject("page", stockCheckPage);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<Pair<StockCheck, List<StockCheckItem>>> page = new Page<Pair<StockCheck, List<StockCheckItem>>>(1, 1, Page.DEFAULT_PAGE_SIZE,new ArrayList<Pair<StockCheck, List<StockCheckItem>>>());
			mv.addObject("page", page);
		}
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping(value = "/stockAllList.htm")
	@ResponseBody
	public ModelAndView allList(Stock stock,HttpServletRequest request) {
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		int pageSize = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("pageSize"), "1000"));
		ModelAndView view = new ModelAndView("stockCheck/all");
		int onecate = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("onecate"), "0"));
		int twocate = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("twocate"), "0"));
		String isdown = request.getParameter("isdown");
		view.addObject("onecate", onecate);
		view.addObject("twocate", twocate);
		view.addObject("isdown", isdown);
		List<ItemCategory> catList = this.itemCategoryService.findAllOneCatsByCityId(personUser.getCityId());
		view.addObject("catList", catList);
		if(onecate>0){
			List<ItemCategory> leaveCats = this.itemCategoryService.findAllTwoCatsByCityId(personUser.getCityId());
			view.addObject("leaveCats", leaveCats);
		}
		Page<HashMap<String,Object>> page = null;

		try{
			page = stockService.findPageWithModifyNum(stock, currentPage, pageSize,personUser.getCityId(), onecate, twocate, isdown);
		}catch(Exception e){
			logger.error("查询商品库存出错",e);
		}


		view.addObject("page", page);
		view.addObject("itemName", stock.getItemName());
		TestController.getMenuPoint(view, request);
		return view;
	}

	@RequestMapping(value = "addStockCheck.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String addSave(HttpServletRequest request) {
		try {

			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			StockCheck stockCheck = new StockCheck();
			String executedTimeStr = request.getParameter("executedTime");
			if (StringUtils.isEmpty(executedTimeStr)) {
				stockCheck.setExecutedTime(new Date());
			} else {
				stockCheck.setExecutedTime(DateUtil.parseDateStr(executedTimeStr, DATE_FORMAT_YMD));
				AccountLock accountLock=new AccountLock(); 
				Calendar calendar = Calendar.getInstance();
					calendar.setTime(stockCheck.getExecutedTime());
					int year = calendar.get(Calendar.YEAR);
					int month = calendar.get(Calendar.MONTH)+1;
		            accountLock.setYears(year+"");
		            accountLock.setMonths(month+"");
		            accountLock.setCityId(personUser.getCityId());
		            int lock=accountLockService.findLockByCityId(accountLock);
					if(lock==1)
					{
						return "执行时间不能设置到已锁帐月份!";
					}
				
				
			}
			stockCheck.setCityId(personUser.getCityId());
			int rowCount = Integer.parseInt(request.getParameter("rowCount"));
			List<StockCheckItem> skuList = new ArrayList<StockCheckItem>();
			Long totalFee = 0l;
			for (int i = 0; i < rowCount; i++) {
				String itemIds = request.getParameter("skuId" + i);
				if (StringUtils.isEmpty(itemIds)) {
					continue;
				}
				int itemId = Integer.parseInt(itemIds);
				StockCheckItem sku = new StockCheckItem();
				// sku_id
				sku.setItemId(Integer.valueOf(itemId));

				// sku_name
				sku.setItemName(request.getParameter("skuName"+i));
				String oldNum = request.getParameter("stockNum"+i);
				sku.setOldNum(Integer.parseInt(oldNum));
				sku.setNum(Integer.parseInt(request
						.getParameter("num" + i))-Integer.parseInt(oldNum));
				Item item = this.itemService.findById(itemId);
				sku.setPrice(item.getCostPrice());
				sku.setFee(item.getCostPrice()*sku.getNum());
				totalFee +=sku.getFee();
				skuList.add(sku);
				//logger.error(i+":"+sku.getItemId());
			}
			String userName = request.getParameter("userName");
			String customerId = request.getParameter("customerId");
			if(!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(customerId)){
				stockCheck.setUserId(Integer.parseInt(customerId));
			}

			stockCheck.setLastModUser(personUser.getId());
			String Stype = request.getParameter("type");
			stockCheck.setType(Integer.parseInt(Stype));
			String remark = request.getParameter("remark");
			stockCheck.setRemark(remark);
			stockCheck.setItemList(skuList);
			stockCheck.setTotalFee(totalFee);
			stockCheckService.create(stockCheck);

			this.saveLog(request.getSession(),stockCheck, "添加盘库单");

			return "添加成功";
		} catch (Exception e) {
			logger.error("添加盘库单失败", e);
			return e.getMessage();
		}
	}
	
	@RequestMapping(value = "allStockSave.do", method = RequestMethod.POST)
	@ResponseBody
	public String allStockSave(HttpServletRequest request) {
		try {

			StockCheck stockCheck = new StockCheck();
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			stockCheck.setCityId(personUser.getCityId());
			int rowCount = Integer.parseInt(request.getParameter("rowCount"));
			List<StockCheckItem> skuList = new ArrayList<StockCheckItem>();
			ArrayList<Integer> ids = new ArrayList<Integer>();
			Long totalFee = 0l;
			for (int i = 0; i < rowCount; i++) {
				String itemIds = request.getParameter("itemId" + i);
				if (StringUtils.isEmpty(itemIds)) {
					continue;
				}
				int itemId = Integer.parseInt(itemIds);
				ids.add(itemId);
				StockCheckItem sku = new StockCheckItem();
				// sku_id
				sku.setItemId(Integer.valueOf(itemId));

				// sku_name
				sku.setItemName(request.getParameter("itemName"+i));
				int oldNum = Integer.parseInt(request.getParameter("oldNum" + i));
				sku.setOldNum(oldNum);
				sku.setNum(Integer.parseInt(request.getParameter("modifyNum" + i))-oldNum);
				Item item = this.itemService.findById(itemId);
				sku.setPrice(item.getCostPrice());
				sku.setFee(item.getCostPrice()*sku.getNum());
				totalFee +=sku.getFee();
				skuList.add(sku);

				//logger.error(i+":"+sku.getItemId());
			}

			stockCheck.setLastModUser(personUser.getId());
			stockCheck.setType(1);
			String executedTimeStr = request.getParameter("executedTime");
			if (StringUtils.isEmpty(executedTimeStr)) {
				stockCheck.setExecutedTime(new Date());
			} else {
				stockCheck.setExecutedTime(DateUtil.parseDateStr(executedTimeStr, DATE_FORMAT_YMD));
				AccountLock accountLock=new AccountLock(); 
				Calendar calendar = Calendar.getInstance();
					calendar.setTime(stockCheck.getExecutedTime());
					int year = calendar.get(Calendar.YEAR);
					int month = calendar.get(Calendar.MONTH)+1;
		            accountLock.setYears(year+"");
		            accountLock.setMonths(month+"");
		            accountLock.setCityId(personUser.getCityId());
		            int lock=accountLockService.findLockByCityId(accountLock);
					if(lock==1)
					{
						return "执行时间不能设置到已锁帐月份!";
					}
			}
			//String remark = request.getParameter("remark");
			//stockCheck.setRemark(remark);
			stockCheck.setItemList(skuList);
			stockCheck.setTotalFee(totalFee);
			stockCheckService.create(stockCheck);
			this.saveLog(request.getSession(),stockCheck, "添加盘库单");
			stockCheckTempService.deleteAll(ids.toArray());
			logger.info("删除临时盘库表");
			return "200";
		} catch (Exception e) {
			logger.error("添加盘库单失败", e);
			return "201";
		}
	}

	@RequestMapping(value = "stockCheckAdd.htm", method = RequestMethod.GET)
	public ModelAndView add(HttpServletRequest request) throws Exception {
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return new ModelAndView("noCity");
			}
			//List<Item> itemList = itemService.findAll();
			List<Item> itemList = itemService.findAllWithStockByCityId(personUser.getCityId());

			ModelAndView mv = new ModelAndView("stockCheck/add");
			mv.addObject("itemList", itemList);
			mv.addObject("executedTime", new Date());

			this.fillCommonData(mv, personUser.getCityId());
			TestController.getMenuPoint(mv, request);
			return mv;
		} catch (Exception e) {
			logger.error("show stockCheck detail.", e);
			throw e;
		}
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

	}

//	@RequestMapping(value = "stockCheckDetail.htm", method = RequestMethod.GET)
//	public ModelAndView detail(HttpServletRequest request) throws Exception {
//		try {
//
//			int id = Integer.valueOf(StringUtils.defaultIfBlank(
//					request.getParameter("id"), "0"));
//			if (id == 0) {
//				return new ModelAndView("errorPage");
//			}
//			Pair<StockCheck, List<StockCheckItem>> pair = stockCheckService
//					.findById(id);
//
//			return new ModelAndView("stockCheck/detail", "pair", pair);
//		} catch (Exception e) {
//			logger.error("show stockCheck detail.", e);
//			throw e;
//		}
//	}

	@RequestMapping(value = "deleteStockCheck.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
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

			Pair<StockCheck, List<StockCheckItem>> pair = stockCheckService
					.findById(id);
			if(pair!=null){
				StockCheck dto = pair.getKey();
				if(dto.getCityId()!=personUser.getCityId()){
					return "你设置默认城市与实际不符";
				}
				
				AccountLock accountLock=new AccountLock(); 
				Calendar calendar = Calendar.getInstance();
					calendar.setTime(dto.getExecutedTime());
					int year = calendar.get(Calendar.YEAR);
					int month = calendar.get(Calendar.MONTH)+1;
		            accountLock.setYears(year+"");
		            accountLock.setMonths(month+"");
		            accountLock.setCityId(personUser.getCityId());
		            int lock=accountLockService.findLockByCityId(accountLock);
					if(lock==1)
					{
						return "无法删除已锁帐月份盘库单!";
					}
				stockCheckService.delete(id);

				this.saveLog(request.getSession(),dto, "删除盘库单,id:"+dto.getId());
			}

		} catch (Exception e) {
			logger.error("删除盘库单失败.", e);
			return e.getMessage();
		}

		return "删除成功";
	}
	
	@RequestMapping("print.htm")
	public ModelAndView print(HttpServletRequest request,@RequestParam("id")Integer id){
		ModelAndView mv = new ModelAndView("stockCheck/print");
		Pair<StockCheck,List<StockCheckItem>> pair = this.stockCheckService.findById(id);
		mv.addObject("pairs", pair);
		StockCheck stockCheck = pair.getLeft();
		if(stockCheck.getUserId()!=null){
			CustomerUser user = this.customerService.findById(stockCheck.getUserId());
			mv.addObject("customerUser", user);
		}
		TestController.getMenuPoint(mv, request);
		return mv;
	}

	private void saveLog(HttpSession session,StockCheck dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setCityId(dto.getCityId());
	       sysLog.setDataType(LogDataTypeEnum.STOCK_CHECK.getName());
	       if(dto.getId()!=null){
	       sysLog.setDataId(dto.getId().toString());
	       }

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<StockCheck>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
