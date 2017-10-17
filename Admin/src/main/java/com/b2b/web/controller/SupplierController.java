package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.Supplier;
import com.b2b.common.domain.SysLog;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.*;
import com.b2b.web.util.NumberTool;
import com.b2b.web.util.SessionHelper;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/supplier")
public class SupplierController {
	private static final Logger logger = LoggerFactory.getLogger(SupplierController.class);

	@Autowired
	SupplierService supplierService;

	@Autowired
	LogService logService;
	
    @Autowired
    ItemService itemService;
    
    @Autowired
    ItemCategoryService itemCategoryService;
    
    @Autowired
    ItemSupplierService itemSupplierService;

	@RequestMapping(value = "/supplierList.htm")
	public ModelAndView showDebitNoteList(HttpServletRequest request) {
		String supplierName = request.getParameter("supplierName");
		String mobilePhone = request.getParameter("mobilePhone");
		Page<Supplier> page =null;
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		try {
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
			if(StringUtils.isBlank(supplierName)){
				supplierName=null;
			}
			Supplier sup=new Supplier();
			sup.setSupplierName(supplierName);
			sup.setMobilePhone(mobilePhone);
			sup.setCityId(user.getCityId());
			page = supplierService.findPage(sup, currentPage ,Page.DEFAULT_PAGE_SIZE);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		ModelAndView mv = new ModelAndView("supplier/supplierList");
		mv.addObject("supplierName", supplierName);
		mv.addObject("mobilePhone", mobilePhone);
		mv.addObject("page", page);
		TestController.getMenuPoint(mv, request);
		return mv;
	}

	@RequestMapping("/supplierAdd.htm")
	public ModelAndView addSupplier(Supplier sup,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("supplier/supplierAdd");
		TestController.getMenuPoint(mv, request);
		return mv;
	}

	@RequestMapping(value = "/supplierUpdate.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView update(int id, int currentPage,
			HttpServletRequest request) {
		Supplier dto = supplierService.findById(id);
		ModelAndView view = new ModelAndView("supplier/supplierAdd");
		view.addObject("currentPage", currentPage);
		view.addObject("dto", dto);
		TestController.getMenuPoint(view, request);
		return view;
	}

	@RequestMapping(value = "/supplierSave.do", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addOrUpdate(Supplier supplier , HttpServletRequest request) {
		Integer user=SessionHelper.getUserId(request.getSession());
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "未设置默认城市";
		}
		supplier.setCreateUserid(user);
		if(supplier.getId()!=null && supplier.getId()>0){
			//更新供应商
			Supplier sup = this.supplierService.findById(supplier.getId());
			if(sup.getCityId() != personUser.getCityId()){
				return "你设置的城市与默认不符";
			}
			supplierService.update(supplier);
			this.saveLog(request.getSession(),supplier, "更新供应商");
		}
		else
		{
			supplier.setCityId(personUser.getCityId());
			supplierService.create(supplier);
			this.saveLog(request.getSession(),supplier, "添加供应商");
		}
		String result = "保存成功";
		return result;
	}

	@RequestMapping(value="supplierDelete.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delete(int id,HttpServletRequest request) {

		String result = "删除成功";

		try{
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "未设置默认城市";
			}
			Supplier supplier = supplierService.findById(id);
			if(supplier!=null && supplier.getCityId() == personUser.getCityId()){
				supplierService.delete(id);
				this.saveLog(request.getSession(),supplier, "删除供应商，用户名:"+supplier.getSupplierName());
			}
		}catch(Exception e){
           logger.error("删除失败",e);
           result = "删除失败,原因："+e.getMessage();
		}

		return result;
	}
	
//	@RequestMapping(value = "/additem.htm", method = RequestMethod.GET)
//	@ResponseBody
//	public ModelAndView additem(int id, 
//			HttpServletRequest request) {
//		ModelAndView mv = new ModelAndView("supplier/itemAdd");
//		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
//		if(null == personUser.getCityId()){
//			return new ModelAndView("noCity");
//		}
//		Supplier supplier = this.supplierService.findById(id);
//		if(personUser.getCityId() != supplier.getCityId()){
//			return new ModelAndView("notCity");
//		}
//		List<Item> itemList = itemService.findAllWithStockByCityId(personUser.getCityId());
//		mv.addObject("itemList", itemList);
//		this.fillCommonData(mv, personUser.getCityId());
//		List<HashMap<String,Object>> items = this.supplierService.findBySupplierId(id);
//		mv.addObject("items", items);
//		mv.addObject("size", items.size());
//		
//		mv.addObject("supplier", supplier);
//		return mv;
//	}
//	
//	@RequestMapping(value = "/additem.do",produces="text/html;charset=UTF-8")
//	@ResponseBody
//	public String additemdo(HttpServletRequest req) {
//		String result = "保存成功";
//		try {
//			String id = req.getParameter("suppilerId");
//			if (StringUtils.isEmpty(id)) {
//				return "用户ID不能为空!";
//			}
//			Integer suppilerId = Integer.parseInt(id);
//			int blackItemRowCount = Integer.parseInt(StringUtils.defaultString(
//					req.getParameter("blackItemRowCount"), "0"));
//			List<ItemSupplier> itemSupplier = new ArrayList<ItemSupplier>();
//			for (int i = 0; i < blackItemRowCount; i++) {
//				int blackItemId = Integer.parseInt(StringUtils.defaultString(
//						req.getParameter("blackItemId" + i), "0"));
//				if (blackItemId == 0) {
//					continue;
//				}
//				ItemSupplier supplier = new ItemSupplier();
//				supplier.setSuppilerId(suppilerId);
//				supplier.setItemId(blackItemId);
//				itemSupplier.add(supplier);
//			}
//			Integer itemid = this.itemSupplierService.save(itemSupplier,suppilerId);
//			if(itemid!=null){
//				Item item = this.itemService.findById(itemid);
//				return "保存失败,"+item.getItemName()+"重复";
//			}
//		} catch (Exception e) {
//			logger.error("保存失败",e);
//			result = "保存失败，原因："+e.getMessage();
//		}
//		return result;
//	}
//	
//	
//	
//	private void fillCommonData(ModelAndView view,int cityId){
//		List<ItemCategory> catList =null;
//		catList= itemCategoryService.findAllOneCatsByCityId(cityId);
//		view.addObject("catList", catList);
//		int cateId1=0;
//		if(CollectionUtils.isNotEmpty(catList)){
//			cateId1  = catList.get(0).getId();
//		}
//		view.addObject("cateId1", cateId1);
//
//	}
	
	@RequestMapping(value = "exportExcel.do", method = RequestMethod.GET)
	@ResponseBody
	public String storageSumExportExcel(HttpServletRequest request,
			HttpServletResponse response) {
		String id = request.getParameter("id");
		Integer supplier_id = Integer.parseInt(id);
		Supplier supplier = this.supplierService.findById(supplier_id);
		String FilePutPath = "D:\\"+supplier.getSupplierName()+"的产品表.xls";
		WritableWorkbook book = null;
		OutputStream os = null;
		try {
			os = response.getOutputStream();
			book = Workbook.createWorkbook(os);
			// 清空response
			response.reset();
			response.setContentType("application/dowload");
			response.setHeader("Content-disposition","attachment;filename=\""
			+ new String((java.net.URLEncoder.encode(supplier.getSupplierName()+"的产品表.xls", "UTF-8")).getBytes("UTF-8"),"GB2312") + "\"");

			// 打开文件
			// book = Workbook.createWorkbook(f);
			// 生成名为"领蛙产品"的工作表，参数0表示这是第一页
			WritableSheet sheet = book.createSheet("sheet1", 0);
			PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "未设置默认城市";
			}
			Supplier sup = this.supplierService.findById(supplier_id);
			if(personUser.getCityId() != sup.getCityId()){
				return "你的设置城市与默认不符";
			}
			List<HashMap<String, Object>> items = this.supplierService.findItemBySupplierId(supplier_id);
			if (items != null && !items.isEmpty()) {
				sheet.mergeCells(0, 0, 10, 0);// 合并标题单元格
				// 指定了字串格式
				WritableFont font1 = new WritableFont(WritableFont.TIMES, 16,
						WritableFont.BOLD);
				WritableCellFormat format1 = new WritableCellFormat(font1);
				// 把水平对齐方式指定为居中
				format1.setAlignment(jxl.format.Alignment.CENTRE);
				// 把垂直对齐方式指定为居中
				format1.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
				// 使用了Label类的构造子，指定了字串被赋予那种格式
				Label label = new Label(0, 0, supplier.getSupplierName()+"的产品表", format1);
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
				sheet.addCell(new Label(0, 1, "序号", cellFormat1));
				sheet.addCell(new Label(1, 1, "类目", cellFormat1));
				sheet.addCell(new Label(2, 1, "产品名称", cellFormat1));
				sheet.addCell(new Label(3, 1, "规格", cellFormat1));
				sheet.addCell(new Label(4, 1, "成本价", cellFormat1));
				sheet.addCell(new Label(5, 1, "批发规格", cellFormat1));
				sheet.addCell(new Label(6, 1, "批发规格系数", cellFormat1));
				sheet.addCell(new Label(7, 1, "批发成本价", cellFormat1));
				sheet.addCell(new Label(8, 1, "当前库存", cellFormat1));
				sheet.addCell(new Label(9, 1, "保质期", cellFormat1));
				sheet.addCell(new Label(10, 1, "已下架", cellFormat1));
				sheet.addCell(new Label(11, 1, "采购箱数", cellFormat1));

				sheet.setColumnView(1, 20);// 根据内容自动设置列宽
				for (int i = 0; i < items.size(); i++) {
					sheet.addCell(new Number(0, i + 2, i + 1));
					sheet.addCell(new Label(1, i + 2, (String) items.get(i).get("category_name")));
					sheet.addCell(new Label(2, i + 2, (String) items.get(i).get("item_name")));
					sheet.addCell(new Label(3, i + 2, (String) items.get(i).get("size")));
					Long cost_price=Long.parseLong(items.get(i).get("cost_price").toString());
					sheet.addCell(new Number(4, i + 2, cost_price==null?0:(new BigDecimal(NumberTool.toYuanNumber(cost_price))).doubleValue()));
					sheet.addCell(new Label(5, i + 2, (String) items.get(i).get("buy_size")));
					Object buy_size = items.get(i).get("convert_num");
					if(null!=buy_size){
						sheet.addCell(new Label(6, i + 2, buy_size.toString()));
					}else{
						sheet.addCell(new Label(6, i + 2, ""));
					}
					Long buy_price=Long.parseLong(items.get(i).get("buy_price").toString());
					sheet.addCell(new Number(7, i + 2, buy_price==null?0:(new BigDecimal(NumberTool.toYuanNumber(buy_price))).doubleValue()));
					Object num = items.get(i).get("num");
					if(null!=num){
						sheet.addCell(new Label(8, i + 2, num.toString()));
					}else{
						sheet.addCell(new Label(8, i + 2, ""));
					}
					sheet.addCell(new Label(9, i + 2, items.get(i).get("shelf_life").toString()));
					if(items.get(i).get("isdown").equals(1)){
						sheet.addCell(new Label(10, i + 2, "是"));
					}else{
						sheet.addCell(new Label(10, i + 2, ""));
					}
					sheet.addCell(new Label(11, i + 2, ""));
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
	

	private void saveLog(HttpSession session,Supplier dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setCityId(dto.getCityId());
	       sysLog.setDataType(LogDataTypeEnum.Supplier.getName());
	       if(dto.getId()!=null){
	    	   sysLog.setDataId(dto.getId().toString());
	       }

	       if(dto!=null){
		       String dataContent = new Gson().toJson(dto,
						new TypeToken<Supplier>() {
						}.getType());

		       sysLog.setDataContent(dataContent);
	       }

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}

	/**
	 * 根据简称返回简称
	 * */
	@RequestMapping(value = "/autoUserNameQuery.do")
	@ResponseBody
	public  List<HashMap<String, Object>> autoQueryUserName(HttpServletRequest request) {
		PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == curUser.getCityId()){
			return new ArrayList<HashMap<String, Object>>();
		};
		return supplierService.findByLikeUserNameAndCityId(request.getParameter("userName"),curUser.getCityId());
	}
}
