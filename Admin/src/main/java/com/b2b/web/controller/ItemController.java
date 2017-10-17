package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.*;
import com.b2b.web.util.FileUtil;
import com.b2b.web.util.NumberTool;
import com.b2b.web.util.SessionHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jxl.Workbook;
import jxl.format.Colour;
import jxl.write.*;
import jxl.write.Number;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/item")
public class ItemController {

	private static final Logger logger = LoggerFactory
			.getLogger(ItemController.class);

	@Value("${item_image_path}")
	private String imagePath;
	
	@Autowired
	ItemService itemService;

	@Autowired
	ItemCategoryService itemCategoryService;
	
	@Autowired
	private ItemVarietyService itemVarietyService;
	
	@Autowired
	StockService stockService;
	
	@Autowired
	private ItemTasteService itemTasteService;

	@Autowired
	LogService logService;
	
	@Autowired
	SupplierService supplierService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ShopBlackListService shopBlackListService;

	@Autowired
	ItemPriceHistoryService itemPriceHistoryService;

	//int down = 0;
	//String sortItem=null;

	@RequestMapping(value = "fix.htm", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String fixItemStockBug(HttpServletRequest request) {
		List<Item> itemList = itemService.findAll();

		for (Item item : itemList) {
			Stock stock = this.stockService.findByItem(item.getId());
			if (stock == null) {
				stock = new Stock();

				stock.setItemName(item.getItemName());
				stock.setItemId(item.getId());
				stock.setNum(0);
				this.stockService.create(stock);
			}
		}

		return "ok";
	}

	@RequestMapping(value = "/itemList.htm",produces = "text/html;charset=UTF-8")
	@ResponseBody
	public ModelAndView list(Item item, HttpServletRequest request) {
		ModelAndView view = new ModelAndView("item/list");
		int down = 0;
		int isdelete = 1;
		int currentPage = 1;
		int pageSize = 15;
		String isdown = null;
		String status = null;
		String sortItem = null;
		String catId = null;
		String itemName = null;
		String grade = null;
		String isnew = null;
		String isrecommend = null;
		Integer frees = null;
		Integer categoryId = null;
		Integer categorylevelId = null;
		HttpSession session = request.getSession();
		if (session.getAttribute("isItemSelect") == null) {
			currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));
			pageSize = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("pageSize"), Page.DEFAULT_PAGE_SIZE_STR));
			isdown = request.getParameter("isdown");
			status = request.getParameter("status");
			sortItem = request.getParameter("itemSort");
			catId = request.getParameter("categoryId");
			itemName = request.getParameter("itemName");
			grade = request.getParameter("grade");
			isnew = request.getParameter("isnew");
			isrecommend = request.getParameter("isrecommend");
			sortItem = sortItem == null ? "item_name" : sortItem;
			categorylevelId = item.getCategorylevelId();
			if(null!=item.getFreeSpecialSupply()){
				frees = item.getFreeSpecialSupply();
				view.addObject("free", frees);
			}
			session.setAttribute("itemfree", frees);
			if(null!=item.getCategoryId()){
				categoryId = item.getCategoryId();
			}
			session.setAttribute("ItemcategoryId", categoryId);
			session.setAttribute("ItemcategorylevelId", categorylevelId);
			putSelectContent(request);
		} else {
			currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					session.getValue("itemcurrentPage").toString(), "1"));
			pageSize = Integer.valueOf(StringUtils.defaultIfBlank(
					session.getValue("itempageSize").toString(), Page.DEFAULT_PAGE_SIZE_STR));
			isdown = session.getValue("itemisdown") == null ? null : session.getValue("itemisdown").toString();
			status = session.getValue("itemstatus") == null ? null : session.getValue("itemstatus").toString();
			sortItem = session.getValue("itemsortItem") == null ? null : session.getValue("itemsortItem").toString();
			catId = session.getValue("itemcatId") == null ? null : session.getValue("itemcatId").toString();
			itemName = session.getValue("itemitemName") == null ? null : session.getValue("itemitemName").toString();
			grade = session.getValue("itemgrade") == null ? null : session.getValue("itemgrade").toString();
			isnew = session.getValue("itemisnew") == null ? null : session.getValue("itemisnew").toString();
			isrecommend = session.getValue("itemisrecommend") == null ? null : session.getValue("itemisrecommend").toString();
			frees = session.getValue("itemfree") == null ? null : Integer.valueOf(session.getValue("itemfree").toString());
			categorylevelId = session.getValue("ItemcategorylevelId") == null ? null : Integer.valueOf(session.getValue("ItemcategorylevelId").toString());
			if (frees != null) {
				view.addObject("free", frees);
			}
			categoryId = session.getValue("ItemcategoryId") == null ? null : Integer.valueOf(session.getValue("ItemcategoryId").toString());
			sortItem = sortItem == null ? "item_name" : sortItem;
			session.removeAttribute("isItemSelect");
		}
		Page<Item> page = null;
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		int businessId = personUser.getBusinessId();
		try {
			if(itemName!=null){
				item.setItemName(itemName);
			}
			if(status == null){
				if (isdown == null) {
					page = itemService.findPage(item, currentPage, pageSize,
							businessId,sortItem,grade,isnew,isrecommend,personUser.getCityId());
				} else {
					down = Integer.parseInt(isdown);
					page = itemService.findPageByIsDown(item, currentPage,
							pageSize, businessId, down,sortItem,grade,isnew,isrecommend,personUser.getCityId());
				}
			}else{
				isdelete = Integer.parseInt(status);
				if(isdelete==0){
					page = itemService.findPageByStatus(item, currentPage, pageSize,
							businessId,sortItem,grade,isnew,isrecommend,personUser.getCityId());
				}else{
					if (isdown == null) {
						page = itemService.findPage(item, currentPage, pageSize,
								businessId,sortItem,grade,isnew,isrecommend,personUser.getCityId());
					} else {
						down = Integer.parseInt(isdown);
						page = itemService.findPageByIsDown(item, currentPage,
								pageSize, businessId, down,sortItem,grade,isnew,isrecommend,personUser.getCityId());
					}
				}
				
			}
		} catch (Exception e) {
			logger.error("查询商品出错", e);
		}
		isdown=isdown==null?"0":isdown;
		view.addObject("page", page);
		view.addObject("itemSort", sortItem);
		view.addObject("isdown", down);
		view.addObject("isdelete", isdelete);
		view.addObject("currentPage", currentPage);
		view.addObject("grade", grade);
		view.addObject("isnew", isnew);
		view.addObject("isrecommend", isrecommend);
		if (catId==null || catId == "")
		{
			view.addObject("catId", 0);
		}else{
			view.addObject("catId", catId);
		}

		view.addObject("itemName", itemName);
//		if (item.getCategoryId() == null) {
//			view.addObject("catId", 0);
//		} else {
//			view.addObject("catId", item.getCategoryId());
//		}
//		view.addObject("itemName", item.getItemName());
		this.fillCommonData(view,personUser.getCityId());
		this.fillLevelCommonData(view,personUser.getCityId());
		if(null!=categoryId){
			item.setCategorylevelId(categorylevelId);
			item.setCategoryId(Integer.valueOf(catId));
			view.addObject("item", item);
			if(categorylevelId != null) {
				List<ItemCategory> leaveCats = this.itemCategoryService.findCatByParentIdAndCityId(item.getCategoryId(), personUser.getCityId());
				view.addObject("leaveCats", leaveCats);
			}
		}
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping(value = "/leavecat.json")
	@ResponseBody
	public List<ItemCategory> leavecat(@RequestParam("catid")Integer catid){
		List<ItemCategory> leaveCats = this.itemCategoryService.findCatByParentId(catid);
		return leaveCats;
	}
	

	@RequestMapping(value = "down.do", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public ModelAndView isDown(HttpServletRequest request) {
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		int id = Integer.parseInt(request.getParameter("id"));
		int down = Integer.parseInt(request.getParameter("isdown"));
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		try {
			if(null == personUser.getCityId()){
				return new ModelAndView("noCity");
			}
			Item item = itemService.findById(id);
			if(item==null && !item.getCityId().equals(personUser.getCityId()) ){
				return new ModelAndView("notCity");
			}
			itemService.updateDown(id, down,personUser);
			if(down==1){
				this.saveLog(request.getSession(),item, "商品下架，名称："+item.getItemName());
			}else{
				this.saveLog(request.getSession(),item, "商品上架，名称："+item.getItemName());
			}
		} catch (Exception e) {
			logger.error("商品上下架错误", e);
		}
		ModelAndView view = new ModelAndView("redirect:/item/itemList.htm");
		view.addObject("currentPage", currentPage);
		if (down == 1) {
			down = 0;
		} else {
			down = 1;
		}
		view.addObject("isdown", down);
		TestController.getMenuPoint(view, request);
		HttpSession session = request.getSession();
		session.setAttribute("isItemSelect", "1");
		return view;
	}

	@RequestMapping(value = "/itemAdd.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView add(HttpServletRequest request) {
		Item dto = new Item();

		ModelAndView view = new ModelAndView("item/detail");
		view.addObject("dto", dto);
		view.addObject("itemTasteSize",0);
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		this.fillCommonData(view, personUser.getCityId());
		this.fillLevelCommonData(view, personUser.getCityId());
		//this.fillVarietyCommonData(view);
		List<Supplier> suppliers = this.supplierService.findByCityId(personUser.getCityId());
		view.addObject("suppliers", suppliers);
		TestController.getMenuPoint(view, request);
		return view;
	}

	@RequestMapping(value = "/itemUpdate.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView update(int id, int currentPage,
			HttpServletRequest request) {
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		Item dto = itemService.findById(id);
		if(dto==null && !dto.getCityId().equals(personUser.getCityId()) ){
			return new ModelAndView("notCity");
		}
		String catId=request.getParameter("catId");
		String itemName=request.getParameter("itemNames");
		ModelAndView view = new ModelAndView("item/detail");
		view.addObject("currentPage", currentPage);
		view.addObject("dto", dto);
		view.addObject("itemNames", itemName);
		if (catId == null) {
			view.addObject("catId", 0);
		} else {
			view.addObject("catId", catId);
		}
		List<ItemTaste> itemTaste = this.itemTasteService.findByItemId(id);
		view.addObject("itemTasteList", itemTaste);
		view.addObject("itemTasteSize", itemTaste.size());
		this.fillCommonData(view, personUser.getCityId());
		this.fillLevelCommonData(view, personUser.getCityId());
		//this.fillVarietyCommonData(view);
		String varietyName = this.itemService.findVarietyName(id);
		view.addObject("varietyName", varietyName);
		List<Supplier> suppliers = this.supplierService.findByCityId(personUser.getCityId());
		List<Supplier> exists = this.supplierService.findByItemId(id);
		view.addObject("suppliers", suppliers);
		view.addObject("existsup", exists);
		TestController.getMenuPoint(view, request);
		HttpSession session = request.getSession();
		session.setAttribute("isItemSelect", "1");
		return view;
	}

	private void fillCommonData(ModelAndView view,Integer cityId) {
		List<ItemCategory> catList = null;
		catList = itemCategoryService.findAllOneCatsByCityId(cityId);
		view.addObject("catList", catList);

	}

	private void fillLevelCommonData(ModelAndView view, Integer cityId) {
		List<ItemCategory> catLevelList = null;
		catLevelList = itemCategoryService.findAllTwoCatsByCityId(cityId);
		view.addObject("catLevelList", catLevelList);

	}
	
//	private void fillVarietyCommonData(ModelAndView view){
//		List<ItemVariety> itemVarietyList = null;
//		itemVarietyList = itemVarietyService.findAll();
//		view.addObject("itemVarietyList", itemVarietyList);
//	}

	@RequestMapping(value = "save.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public ModelAndView save(@RequestParam MultipartFile myfile,@RequestParam MultipartFile myfile1, Item dto,
			HttpServletRequest request) {
		String result = "操作成功";

		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));

		try{
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return new ModelAndView("noCity");
			}
			if (!myfile.isEmpty()) {
				File file = new File(imagePath);
				if (!file.exists()) {
					file.mkdir();
				}
				try {
					String oldName = myfile.getOriginalFilename();
					String name = FileUtil.genUploadFileName(oldName);
					FileUtils.copyInputStreamToFile(myfile.getInputStream(),
							new File(imagePath, name));
					dto.setImgPath("http://assets.lingwa.com.cn/images/item/"+name);
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("上传图片失败", e);
					result = "操作失败";
				}
			}
			if (!myfile1.isEmpty()) {
				File file = new File(imagePath);
				if (!file.exists()) {
					file.mkdir();
				}
				try {
					String oldName = myfile1.getOriginalFilename();
					String name = FileUtil.genUploadFileName(oldName);
					FileUtils.copyInputStreamToFile(myfile1.getInputStream(),
							new File(imagePath, name));
					dto.setBigImgPath("http://assets.lingwa.com.cn/images/item/"+name);
				} catch (IOException e) {
					e.printStackTrace();
					logger.error("上传图片失败", e);
					result = "操作失败";
				}
			}
			
			String name = request.getParameter("itemVarietyname");
			int rowCount = Integer.parseInt(StringUtils.defaultString(
					request.getParameter("rowCount"), "0"));
			List<ItemTaste> itemTaste = new ArrayList<ItemTaste>();
			StringBuffer buffer = new StringBuffer();
			int a = 0;
			for (int i = 0; i < rowCount; i++) {
				ItemTaste itemTaste2 = new ItemTaste();
				String taste = request.getParameter("itemTaste" + i); 
				String barcode = request.getParameter("barcode" + i);
				if(StringUtils.isNotEmpty(taste)){
					itemTaste2.setTasteName(taste);
				}
				if(StringUtils.isNotEmpty(barcode)){
					itemTaste2.setBarcode(barcode);
					if(a==0){
						buffer.append(barcode);
						a=1;
					}else{
						buffer.append(","+barcode);
					}
				}
				if(itemTaste2.getTasteName()!=null || itemTaste2.getBarcode()!=null){
					itemTaste.add(itemTaste2);
				}
			}
			dto.setBarcode(buffer.toString());
			if(StringUtils.isNotEmpty(name)){
				ItemVariety itemVariety = this.itemVarietyService.findByName(name,personUser.getCityId());
				if(null!=itemVariety){
					dto.setItemVariety(itemVariety.getItemvarietyId());
				}else{
					ItemVariety variety = new ItemVariety();
					variety.setItemvarietyName(name);
					variety.setCityId(personUser.getCityId());
					variety.setCreatedTime(new Date());
					variety.setCreatedUserid(personUser.getId());
					variety.setUpdatedTime(variety.getCreatedTime());
					variety.setUpdatedUserid(personUser.getId());
					this.itemVarietyService.create(variety);
					dto.setItemVariety(variety.getItemvarietyId());
				}
			}

			/*
			 * String check = itemService.check(dto);
			 * if(StringUtils.isNotBlank(check)){
			 *
			 * }
			 */


			if (dto.getActualPrice() != null) {
				long price = NumberTool.str2Double2Fen(dto.getActualPrice()
						.toString());
				dto.setPrice(price);
			}

			if (dto.getActualCostPrice() != null) {
				long price = NumberTool.str2Double2Fen(dto.getActualCostPrice()
						.toString());
				dto.setCostPrice(price);
			}

			if (dto.getActualSalePrice() != null) {
				long price = NumberTool.str2Double2Fen(dto.getActualSalePrice()
						.toString());
				dto.setSalePrice(price);
			}

			if (dto.getActualSaleCostPrice() != null) {
				long price = NumberTool.str2Double2Fen(dto.getActualSaleCostPrice()
						.toString());
				dto.setSaleCostPrice(price);
			}


			if (dto.getActualBuyPrice() != null) {
				long price = NumberTool.str2Double2Fen(dto.getActualBuyPrice()
						.toString());
				dto.setBuyPrice(price);
			}

			if (dto.getActualPurchasePrice() != null) {
				long price = NumberTool.str2Double2Fen(dto.getActualPurchasePrice()
						.toString());


				dto.setPurchasePrice(price);
			}
			
			if (dto.getActualnotaxInclusiveBuyPrice() != null) {
				long price = NumberTool.str2Double2Fen(dto.getActualnotaxInclusiveBuyPrice().toString());

				dto.setNotaxInclusiveBuyPrice(price);
			}
			
			if (dto.getActualnotaxInclusiveCostPrice() != null) {
				long price = NumberTool.str2Double2Fen(dto.getActualnotaxInclusiveCostPrice()
						.toString());


				dto.setNotaxInclusiveCostPrice(price);
			}
			
			if (dto.getActualnotaxInclusiveSaleCostPrice() != null) {
				long price = NumberTool.str2Double2Fen(dto.getActualnotaxInclusiveSaleCostPrice()
						.toString());


				dto.setNotaxInclusiveSaleCostPrice(price);
			}
			if (dto.getActualMarketPrice() != null) {
				long price = NumberTool.str2Double2Fen(dto.getActualMarketPrice()
						.toString());
				dto.setMarketPrice(price);
			}
			if (dto.getActualcsPrice() != null) {
				long price = NumberTool.str2Double2Fen(dto.getActualcsPrice()
						.toString());
				dto.setCsPrice(price);
			}
			if (dto.getActualjdPrice() != null) {
				long price = NumberTool.str2Double2Fen(dto.getActualjdPrice()
						.toString());
				dto.setJdPrice(price);
			}
			if (dto.getActualtmPrice() != null) {
				long price = NumberTool.str2Double2Fen(dto.getActualtmPrice()
						.toString());
				dto.setTmPrice(price);
			}
			dto.setCityId(personUser.getCityId());
			//计算价格
//            dto.calCostPrice();
//            dto.calPrice();
//            dto.calSaleCostPrice();
//            dto.calSalePrice();
			ArrayList<Integer> list = new ArrayList<Integer>();
			String[] supplierids = request.getParameterValues("supplier");
			for (String string : supplierids) {
				int supplierId = Integer.parseInt(string);
				list.add(supplierId);
			}
           
			if (dto.getId() != null && dto.getId() > 0) {
				Item old = this.itemService.findById(dto.getId());
				ItemPriceHistory itemPriceHistory = new ItemPriceHistory();
				itemPriceHistory.setItemId(dto.getId());
				itemPriceHistory.setItemName(dto.getItemName());
				boolean createHistory = false;
				if(!old.getBuyPrice().equals(dto.getBuyPrice())) {
					itemPriceHistory.setOldBuyPrice(old.getBuyPrice());
					itemPriceHistory.setBuyPrice(dto.getBuyPrice());
					createHistory = true;
				}
				if(!old.getPrice().equals(dto.getPrice())) {
					itemPriceHistory.setOldPrice(old.getPrice());
					itemPriceHistory.setPrice(dto.getPrice());
					createHistory = true;
				}

				if (createHistory) {
					itemPriceHistoryService.inset(itemPriceHistory);
				}

				dto.setUpdatedTime(new Date());
				dto.setUpdatedUserid(personUser.getId());
				itemService.update(dto,itemTaste,list);


				this.saveLog(request.getSession(),dto, "修改商品，名称："+dto.getItemName());
			} else {
				dto.setCreatedTime(new Date());
				dto.setCreatedUserid(personUser.getId());
				dto.setUpdatedTime(dto.getCreatedTime());
				dto.setUpdatedUserid(personUser.getId());
				dto.setRecommend(0);
				itemService.create(dto,itemTaste,list);
				this.saveLog(request.getSession(),dto, "添加商品，名称："+dto.getItemName());
			}
		}catch(Exception e){
            logger.error("保存商品失败",e);
		}
		String catId=request.getParameter("catId");
		String itemName=request.getParameter("itemNames");
		ModelAndView view = new ModelAndView("redirect:/item/itemList.htm");
		view.addObject("currentPage", currentPage);
		if(!catId.equals("$catId")){
		view.addObject("categoryId", catId);
		}
		if(!itemName.equals("$itemNames")){
		view.addObject("itemName", itemName);
		}
		TestController.getMenuPoint(view, request);
		return view;
	}

	@RequestMapping(value = "delete.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String delete(int id,HttpServletRequest request) {
		String result = "操作成功";
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "用户没有设置默认城市";
			}
			Item dto = itemService.findById(id);
			if(dto==null && !dto.getCityId().equals(personUser.getCityId())){
				return "你设置的默认城市与操作城市不符";
			}
			itemService.delete(id);
			this.saveLog(request.getSession(),dto, "删除商品，名称："+dto.getItemName());
			// stockService.deleteByItemId(id);
		} catch (Exception e) {
			logger.error("删除商品错误", e);
			return "删除失败,原因："+e.getMessage();
		}
		HttpSession session = request.getSession();
		session.setAttribute("isItemSelect", "1");
		return result;
	}
	
	@RequestMapping(value = "recover.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String recover(int id,HttpServletRequest request){
		String result = "操作成功";
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "用户没有设置默认城市";
			}
			Item dto = itemService.findById(id);
			if(dto==null && !dto.getCityId().equals(personUser.getCityId())){
				return "你设置的默认城市与操作城市不符";
			}
			itemService.recover(id,personUser);
			this.saveLog(request.getSession(),dto, "恢复商品，名称："+dto.getItemName());
			// stockService.deleteByItemId(id);
		} catch (Exception e) {
			logger.error("恢复商品错误", e);
			return "恢复失败,原因："+e.getMessage();
		}
		return result;
	}

	@RequestMapping(value = "/itemImage.htm")
	public String showImage(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String imageName = request.getParameter("imageName");
			// String name = genImageName(index);
			if(imageName==""){
				logger.warn("图片名称为空");
				return null;
			}
			String path = imagePath + imageName;
			File file = new File(path);
			if (!file.exists()) {
				logger.warn("没有找到对应的文件,path:" + path);
				return null;
			}
			long size = file.length();

			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(path));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			// 清空response
			response.reset();
			// 设置response的Header
			response.addHeader("Content-Disposition", "attachment;filename="
					+ new String(imageName.getBytes("UTF-8"), "ISO-8859-1"));
			response.addHeader("Content-Length", "" + size);
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");
			toClient.write(buffer);
			toClient.flush();
			toClient.close();

		} catch (IOException ex) {
			ex.printStackTrace();
		}

		return null;
	}
//	/**
//	 * 商品权重调整
//	 */
//	@RequestMapping(value="itemWeight.do",method=RequestMethod.GET)
//	@ResponseBody
//	public String upOrDownItemWeight(HttpServletRequest request,@RequestParam("id")Integer id,@RequestParam("flag")Integer flag){
//		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
//		if(1==flag){
//			try {
//				this.itemService.upOrDownItemWeight(id,1,personUser.getId());
//				logger.info("商品id："+id+"权重加1");
//				return "200";
//			} catch (Exception e) {
//				logger.error("顶出错啦！商品id："+id+"======>"+e);
//				return "202";
//			}
//		}else{
//			Item item = this.itemService.findById(id);
//			if(item.getItemWeight()>0){
//				try {
//					this.itemService.upOrDownItemWeight(id,-1,personUser.getId());
//					logger.info("商品id："+id+"权重减1");
//					return "201";
//				} catch (Exception e) {
//					logger.error("踩出错啦！商品id："+id+"======>"+e);
//					return "202";
//				}
//			}
//			return "202";
//		}
//	}
	
	
	@RequestMapping(value="recommend.do",method=RequestMethod.GET)
	@ResponseBody
	public String upOrDownItemWeight(HttpServletRequest request,@RequestParam("id")Integer id,@RequestParam("flag")Integer flag){
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(1==flag){
			try {
				this.itemService.upOrDownrecommend(id,1,personUser.getId());
				logger.info("商品id："+id+"加1");
				return "200";
			} catch (Exception e) {
				logger.error("顶出错啦！商品id："+id+"======>"+e);
				return "202";
			}
		}else{
			Item item = this.itemService.findById(id);
			if(item.getRecommend()>0){
				try {
					this.itemService.upOrDownrecommend(id,-1,personUser.getId());
					logger.info("商品id："+id+"减1");
					return "201";
				} catch (Exception e) {
					logger.error("踩出错啦！商品id："+id+"======>"+e);
					return "202";
				}
			}
			return "202";
		}
	}

	/**
	 * 导出商品信息到Excel
	 */
	@RequestMapping(value = "exportExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String exportExcel(HttpServletRequest request,Item item,
			HttpServletResponse response) {
		String FilePutPath = "D:\\产品信息.xls";
		WritableWorkbook book = null;
		SimpleDateFormat formatDate = new SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		OutputStream os = null;
		try {
			int withPIC = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("withPIC"), "-1"));
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode("产品信息.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			String isdown = request.getParameter("isdown");
			isdown=isdown==null?"0":isdown;
			int down =Integer.parseInt(isdown);
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "用户没有设置默认城市";
			}
			String grade = request.getParameter("grade");
			String isnews = request.getParameter("isnew");
			String isrecommend = request.getParameter("isrecommend");
			//List<Item> listitem = itemService.findIsDown(down,sortItem);
			List<HashMap<String, Object>> listitem = itemService.findItemsByIsDownAndSortItem(down,item.getItemName(),item.getCategoryId(),item.getCategorylevelId(),grade,isnews,isrecommend,personUser.getCityId(),item.getFreeSpecialSupply());
			if (listitem != null && !listitem.isEmpty()) {
				if(withPIC==1){
					sheet.mergeCells(0, 0, 36, 0);// 合并标题单元格
				}else{
					sheet.mergeCells(0, 0, 35, 0);// 合并标题单元格
				}
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, "领蛙产品信息表", format1);
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
				sheet.addCell(new Label(1, 1, "类目", cellFormat1));
				sheet.addCell(new Label(2, 1, "二级类目", cellFormat1));
				
				sheet.addCell(new Label(3, 1, "产品名称", cellFormat1));
				sheet.addCell(new Label(4, 1, "品种", cellFormat1));
				sheet.addCell(new Label(5, 1, "口味", cellFormat1));
				sheet.addCell(new Label(6, 1, "品牌", cellFormat1));
				sheet.addCell(new Label(7, 1, "规格", cellFormat1));
				sheet.addCell(new Label(8, 1, "创建时间", cellFormat1));
				sheet.addCell(new Label(9, 1, "单价", cellFormat1));
				sheet.addCell(new Label(10, 1, "成本价", cellFormat1));
				sheet.addCell(new Label(11, 1, "利润率", cellFormat1));
				sheet.addCell(new Label(12, 1, "批发规格", cellFormat1));
				sheet.addCell(new Label(13, 1, "批发规格系数", cellFormat1));
				sheet.addCell(new Label(14, 1, "批发成本价", cellFormat1));
				sheet.addCell(new Label(15, 1, "批发价", cellFormat1));
				sheet.addCell(new Label(16, 1, "零售规格", cellFormat1));
				sheet.addCell(new Label(17, 1, "零售规格系数", cellFormat1));
				sheet.addCell(new Label(18, 1, "零售成本价", cellFormat1));
				sheet.addCell(new Label(19, 1, "零售价", cellFormat1));
				sheet.addCell(new Label(20, 1, "当前库存", cellFormat1));
				sheet.addCell(new Label(21, 1, "评分", cellFormat1));
				sheet.addCell(new Label(22, 1, "产地", cellFormat1));
				sheet.addCell(new Label(23, 1, "保质期", cellFormat1));
				sheet.addCell(new Label(24, 1, "供应商", cellFormat1));
				sheet.addCell(new Label(25, 1, "新品", cellFormat1));
				sheet.addCell(new Label(26, 1, "呱呱推荐", cellFormat1));
				sheet.addCell(new Label(27, 1, "分级", cellFormat1));
				sheet.addCell(new Label(28, 1, "备注", cellFormat1));
				sheet.addCell(new Label(29, 1, "便利店价", cellFormat1));
				sheet.addCell(new Label(30, 1, "超市价", cellFormat1));
				sheet.addCell(new Label(31, 1, "京东价", cellFormat1));
				sheet.addCell(new Label(32, 1, "天猫价", cellFormat1));
				sheet.addCell(new Label(33, 1, "条形码", cellFormat1));
				sheet.addCell(new Label(34, 1, "福利店特供", cellFormat1));
				if(withPIC==1){
					sheet.addCell(new Label(35, 1, "图片", cellFormat1));
				}
				sheet.setColumnView(2, 30);// 根据内容自动设置列宽
				sheet.setColumnView(6, 20);// 根据内容自动设置列宽
				sheet.setColumnView(11, 15);// 根据内容自动设置列宽
				sheet.setColumnView(12, 15);// 根据内容自动设置列宽
				sheet.setColumnView(15, 15);// 根据内容自动设置列宽
				sheet.setColumnView(16, 15);// 根据内容自动设置列宽
				if(withPIC==1){
					sheet.setColumnView(35, 28);// 根据内容自动设置列宽
				}
				File outputFile=null;  
				for (int i = 0; i < listitem.size(); i++) {
					sheet.addCell(new Number(0, i + 2, i + 1));
					sheet.addCell(new Label(1, i + 2, (String) listitem.get(i)
							.get("categoryName")));
					sheet.addCell(new Label(2, i + 2, (String) listitem.get(i)
							.get("twoCateName")));
					
					sheet.addCell(new Label(3, i + 2, (String) listitem.get(i)
							.get("itemName")));
					sheet.addCell(new Label(4, i + 2, (String) listitem.get(i)
							.get("itemvarietyName")));
					int itemId = Integer.parseInt(listitem.get(i).get("id").toString());
//					ArrayList<String> array = new ArrayList<String>();
//					for (ItemTaste taste : itemTaste) {
//						if(itemId==taste.getItemId()){
//							array.add(taste.getTasteName());
//						}
//					}
					List<ItemTaste> teast = this.itemTasteService.findByItemId(itemId);
					StringBuffer buffer = new StringBuffer();
					for (ItemTaste itemTaste2 : teast) {
						buffer.append(itemTaste2.getTasteName()+",");
					}
					sheet.addCell(new Label(5, i + 2, buffer.toString()));
					sheet.addCell(new Label(6, i + 2, (String) listitem.get(i).get("brand")));
					sheet.addCell(new Label(7, i + 2, (String) listitem.get(i).get("size")));
					String createtime = formatDate.format(listitem.get(i)
							.get("createdTime"));
					sheet.addCell(new Label(8, i + 2, createtime));
					Long price=Long.parseLong(listitem.get(i).get("price").toString());
					sheet.addCell(new Number(9, i + 2, price==null?0:(new BigDecimal(NumberTool.toYuanNumber(price))).doubleValue()));
					Long costPrice=Long.parseLong(listitem.get(i).get("costPrice").toString());
					sheet.addCell(new Number(10, i + 2, costPrice==null?0:(new BigDecimal(NumberTool.toYuanNumber(costPrice))).doubleValue()));
					BigDecimal profit=new BigDecimal(listitem.get(i).get("profit").toString());
					sheet.addCell(new Number(11, i + 2,(profit==null?0:profit.doubleValue())));
					sheet.addCell(new Label(12, i + 2,(String) listitem.get(i).get("buySize")));
					Object convertNum = listitem.get(i).get("convertNum");
					if(null!=convertNum){
						sheet.addCell(new Label(13,i+2,convertNum.toString()));
					}else{
						sheet.addCell(new Label(13,i+2,""));
					}
					Long buyPrice=Long.parseLong(listitem.get(i).get("buyPrice").toString());
					sheet.addCell(new Number(14, i + 2,buyPrice==null?0:(new BigDecimal(NumberTool.toYuanNumber(buyPrice))).doubleValue()));
					
					Object purchasePrice = listitem.get(i).get("purchasePrice");
					if(null!=purchasePrice){
						sheet.addCell(new Number(15, i + 2,new BigDecimal(NumberTool.toYuanNumber(Long.parseLong(purchasePrice.toString()))).doubleValue()));
					}else{
						sheet.addCell(new Number(15, i + 2,0));
					}
					sheet.addCell(new Label(16,i+2,(String) listitem.get(i).get("saleSize")));
					Object saleSizeNum = listitem.get(i).get("saleSizeNum");
					if(null!=saleSizeNum){
						sheet.addCell(new Label(17,i+2,saleSizeNum.toString()));
					}else{
						sheet.addCell(new Label(17,i+2,""));
					}
					Object saleCostPrice = listitem.get(i).get("saleCostPrice");
					if(null!=saleCostPrice){
						sheet.addCell(new Number(18, i + 2,new BigDecimal(NumberTool.toYuanNumber(Long.parseLong(saleCostPrice.toString()))).doubleValue()));
					}else{
						sheet.addCell(new Number(18, i + 2,0));
					}
					Object salePrice = listitem.get(i).get("salePrice");
					if(null!=salePrice){
						sheet.addCell(new Number(19, i + 2,new BigDecimal(NumberTool.toYuanNumber(Long.parseLong(salePrice.toString()))).doubleValue()));
					}else{
						sheet.addCell(new Number(19, i + 2,0));
					}
					sheet.addCell(new Number(20, i + 2, listitem.get(i).get("num")==null?0:new BigDecimal(listitem.get(i).get("num").toString()).doubleValue()));
					Object itemWeight=listitem.get(i).get("itemWeight");
					if(null!=itemWeight){
						sheet.addCell(new Label(21, i + 2,itemWeight.toString()));
					}else{
						sheet.addCell(new Label(21, i + 2,"0"));
					}
					sheet.addCell(new Label(22,i+2,(String) listitem.get(i).get("place")));
					Object life = listitem.get(i).get("shelfLife");
					if(null!=life){
						sheet.addCell(new Label(23,i+2,life.toString()));
						
					}else{
						sheet.addCell(new Label(23,i+2,""));
					}
					sheet.addCell(new Label(24,i+2,(String) listitem.get(i).get("supplierName")));
					Object isnew = listitem.get(i).get("isnew");
					if(null!=isnew){
						if(isnew.toString().equals("1")){
							sheet.addCell(new Label(25,i+2,"是"));
						}else{
							sheet.addCell(new Label(25,i+2,"否"));
						}
					}else{
						sheet.addCell(new Label(25,i+2,"否"));
					}
					Object recommend = listitem.get(i).get("recommend");
					if(null!=recommend){
						if(recommend.toString().equals("1")){
							sheet.addCell(new Label(26,i+2,"是"));
						}else{
							sheet.addCell(new Label(26,i+2,"否"));
						}
					}else{
						sheet.addCell(new Label(26,i+2,"否"));
					}
					sheet.addCell(new Label(27,i+2,(String) listitem.get(i).get("grade")));
					sheet.addCell(new Label(28,i+2,(String) listitem.get(i).get("remark")));
					Object marketPrice = listitem.get(i).get("marketPrice");
					if(null!=marketPrice){
						sheet.addCell(new Number(29, i + 2,new BigDecimal(NumberTool.toYuanNumber(Long.parseLong(marketPrice.toString()))).doubleValue()));
					}else{
						sheet.addCell(new Number(29, i + 2,0));
					}
					Object csPrice = listitem.get(i).get("csPrice");
					if(null!=csPrice){
						sheet.addCell(new Number(30, i + 2,new BigDecimal(NumberTool.toYuanNumber(Long.parseLong(csPrice.toString()))).doubleValue()));
					}else{
						sheet.addCell(new Number(30, i + 2,0));
					}
					
					Object jdPrice = listitem.get(i).get("jdPrice");
					if(null!=jdPrice){
						sheet.addCell(new Number(31, i + 2,new BigDecimal(NumberTool.toYuanNumber(Long.parseLong(jdPrice.toString()))).doubleValue()));
					}else{
						sheet.addCell(new Number(31, i + 2,0));
					}
					Object tmPrice = listitem.get(i).get("tmPrice");
					if(null!=tmPrice){
						sheet.addCell(new Number(32, i + 2,new BigDecimal(NumberTool.toYuanNumber(Long.parseLong(tmPrice.toString()))).doubleValue()));
					}else{
						sheet.addCell(new Number(32, i + 2,0));
					}
					sheet.addCell(new Label(33,i+2,(String) listitem.get(i).get("barcode")));
					Object freeSpecialSupply = listitem.get(i).get("freeSpecialSupply");
					if(null!=freeSpecialSupply){
						if(freeSpecialSupply.toString().equals("1")){
							sheet.addCell(new Label(34,i+2,"是"));
						}else{
							sheet.addCell(new Label(34,i+2,"否"));
						}
					}else{
						sheet.addCell(new Label(34,i+2,"否"));
					}
					
					if(withPIC==1){
						String img = listitem.get(i).get("imgPath").toString();
						String[] units = img.split("/");
						String a = units[units.length-1];
						String path = imagePath + a;
						//String path = "D:\\8a3c3685-c17e-4e48-80cb-98e7aa874baf.jpg";
						File imgFile = new File(path);
						if(imgFile.exists()&&imgFile.length()>0){  
			                BufferedImage input=null;  
			                try {  
			                    input = ImageIO.read(imgFile);  
			                } catch (Exception e) {  
			                    e.printStackTrace();  
			                }  
			                if(input!=null){  
			                    String path1=imgFile.getAbsolutePath();  
			                    outputFile = new File(path1.substring(0,path1.lastIndexOf('.')+1)+"png");  
			                    ImageIO.write(input, "PNG", outputFile);  
			                    if(outputFile.exists()&&outputFile.length()>0){  
			                    	sheet.setRowView(i+2, 3300, false);
			                    	WritableImage image=new WritableImage(35.01, i + 2.01, 0.99, 0.99,outputFile);
									sheet.addImage(image);
			                    }  
			                }  
			                  
			            }  
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
	
	@RequestMapping("/all.json")
	@ResponseBody
	public List<HashMap<String, Object>> findItemNames(HttpServletRequest request){
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null==personUser){
			return new ArrayList<HashMap<String, Object>>();
		}
		return this.itemService.findItemNames(request.getParameter("itemName"),personUser.getCityId());
	}
	
	@RequestMapping("/search.json")
	@ResponseBody
	public List<HashMap<String, Object>> getItemNames(HttpServletRequest request,@RequestParam("userId")Integer userId){
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null==personUser){
			return new ArrayList<HashMap<String, Object>>();
		}
		List<HashMap<String, Object>> itemList = this.itemService.findItemNames(request.getParameter("itemName"),personUser.getCityId());
		if(!itemList.isEmpty()){
			ArrayList<Integer> ids = this.orderService.findLastTwoOrderItemByCid(userId);
			ArrayList<Integer> ids2 = this.shopBlackListService.findAllByShopId(userId);
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
		}
		return itemList;
	}
	
	@RequestMapping("/query.json")
	@ResponseBody
	public Map<String, Object> findByItemName(@RequestParam("searchId")Integer searchId){
		Map<String, Object> resultMap = Maps.newHashMap();
		Item item = this.itemService.findById(searchId);
		resultMap.put("item", item);
		return resultMap;
	}

	private void saveLog(HttpSession session,Item dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setCityId(dto.getCityId());
	       sysLog.setDataType(LogDataTypeEnum.ITEM.getName());
	       if(dto.getId()!=null){
	       sysLog.setDataId(dto.getId().toString());
	       }

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<Item>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}

	@RequestMapping(value =  "/itemPriceHistory.htm")
	@ResponseBody
	public ModelAndView itemPriceHistory(HttpServletRequest request) {
		ModelAndView view = new ModelAndView("item/itemPriceHistory");
		try {
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));

		String itemName = request.getParameter("itemName");
		view.addObject("itemName", itemName);
		if (itemName != null) {
			itemName = itemName.replace("*", "");
		}
			PageHelper.startPage(currentPage, 50);

		List<ItemPriceHistory> itemPriceHistories = itemPriceHistoryService.findByItemName(itemName, personUser.getCityId());
		PageInfo<ItemPriceHistory> info = new PageInfo<ItemPriceHistory>(itemPriceHistories);

		Page<ItemPriceHistory> page = new Page<ItemPriceHistory>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());

		view.addObject("page", page);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<ItemPriceHistory> page = new Page<ItemPriceHistory>(1, 1,
					Page.DEFAULT_PAGE_SIZE,new ArrayList<ItemPriceHistory>());
			view.addObject("page", page);
		}
		TestController.getMenuPoint(view, request);
		return view;
	}

	@RequestMapping("changeRemark.do")
	@ResponseBody
	public String changeRemark(@RequestParam("id")Integer id, @RequestParam("remark")String remark, HttpServletRequest request){
		try {
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "201";
			}

			ItemPriceHistory itemPriceHistory = new ItemPriceHistory();
			itemPriceHistory.setId(id);
			itemPriceHistory.setRemake(remark);
			itemPriceHistoryService.updataById(itemPriceHistory);


			return "200";
		} catch (Exception e) {
			logger.error("商品价格变动记录备注修改失败", e);
		}
		return "201";
	}

	public void putSelectContent(HttpServletRequest request) {
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		int pageSize = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("pageSize"), Page.DEFAULT_PAGE_SIZE_STR));
		String isdown = request.getParameter("isdown");
		String status = request.getParameter("status");
		String sortItem = request.getParameter("itemSort");
		String catId = request.getParameter("categoryId");
		String itemName = request.getParameter("itemName");
		String grade = request.getParameter("grade");
		String isnew = request.getParameter("isnew");
		String isrecommend = request.getParameter("isrecommend");
		HttpSession session = request.getSession();
		session.setAttribute("itemcurrentPage", currentPage);
		session.setAttribute("itempageSize", pageSize);
		session.setAttribute("itemisdown", isdown);
		session.setAttribute("itemstatus", status);
		session.setAttribute("itemsortItem", sortItem);
		session.setAttribute("itemcatId", catId);
		session.setAttribute("itemitemName", itemName);
		session.setAttribute("itemgrade", grade);
		session.setAttribute("itemisnew", isnew);
		session.setAttribute("itemisrecommend", isrecommend);
	}

}
