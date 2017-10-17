package com.b2b.web.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.Constant;
import com.b2b.common.domain.BeBlackWhiteListCategory;
import com.b2b.common.domain.BeBlackWhiteListItem;
import com.b2b.common.domain.BeBlackWhiteListVariety;
import com.b2b.common.domain.BeCustomerBlackWhiteList;
import com.b2b.common.domain.BlackWhiteListCategory;
import com.b2b.common.domain.BlackWhiteListItem;
import com.b2b.common.domain.BlackWhiteListVariety;
import com.b2b.common.domain.CustomerBlackWhiteList;
import com.b2b.common.domain.Item;
import com.b2b.common.domain.ItemCategory;
import com.b2b.common.domain.ItemVariety;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.SysLog;
import com.b2b.common.domain.CustomerUser;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.service.BeBlackWhiteListCategoryService;
import com.b2b.service.BeBlackWhiteListItemService;
import com.b2b.service.BeBlackWhiteListVarietyService;
import com.b2b.service.BeCustomerBlackWhiteListService;
import com.b2b.service.CustomerService;
import com.b2b.service.ItemCategoryService;
import com.b2b.service.ItemService;
import com.b2b.service.ItemVarietyService;
import com.b2b.service.LogService;
import com.b2b.service.UserService;
import com.b2b.web.util.SessionHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RequestMapping("beBlackWhiteList")
@Controller
public class BeCustomerBlackWhiteListController {
	private static final Logger logger = LoggerFactory.getLogger(BeCustomerBlackWhiteListController.class);
	
	@Autowired
	private BeCustomerBlackWhiteListService beCustomerBlackWhiteListService;
	
	@Autowired
	private BeBlackWhiteListCategoryService beBlackWhiteListCategoryService;
	
	@Autowired
	private BeBlackWhiteListItemService beBlackWhiteListItemService;
	
	@Autowired
	private BeBlackWhiteListVarietyService beBlackWhiteListVarietyService;
	
	@Autowired
	private ItemCategoryService itemCategoryService;
	
	@Autowired
	private ItemVarietyService itemVarietyService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	LogService logService;
	
	@RequestMapping(value = "/add.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView add() {
		ModelAndView mv = new ModelAndView("beBlackWhite/add");
		List<ItemVariety> itemVarietyList = this.itemVarietyService.findAll();
		mv.addObject("itemVarietyList", itemVarietyList);
		List<Item> itemList = itemService.findAll();
		mv.addObject("itemList", itemList);
		this.fillCommonData(mv, 0);
		this.fillLevelCommonData(mv, 0);
		return mv;
	}
	
	@RequestMapping(value = "/update.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView updateShow(@RequestParam("id")Integer id) {
		ModelAndView mv = new ModelAndView("beBlackWhite/edit");
		List<ItemVariety> itemVarietyList = this.itemVarietyService.findAll();
		mv.addObject("itemVarietyList", itemVarietyList);
		List<Item> itemList = itemService.findAll();
		mv.addObject("itemList", itemList);
		CustomerUser customer = customerService.findById(id);
		List<HashMap<String,Object>> blackCate = this.beBlackWhiteListCategoryService.findAll(id,1);
		List<HashMap<String,Object>> whiteCate = this.beBlackWhiteListCategoryService.findAll(id,2);
		List<HashMap<String,Object>> whiteItem = this.beBlackWhiteListItemService.findAll(id,2);
		List<HashMap<String, Object>> blackVariety = this.beBlackWhiteListVarietyService.findAll(id,1);
		List<HashMap<String, Object>> whiteVariety = this.beBlackWhiteListVarietyService.findAll(id,2);
		mv.addObject("customer", customer);
		mv.addObject("blackCate", blackCate);
		mv.addObject("blackCateSize", blackCate.size());
		mv.addObject("whiteCate", whiteCate);
		mv.addObject("whiteCateSize", whiteCate.size());
		mv.addObject("whiteItem", whiteItem);
		mv.addObject("whiteItemSize", whiteItem.size());
		mv.addObject("blackVariety", blackVariety);
		mv.addObject("blackVarietySize", blackVariety.size());
		mv.addObject("whiteVariety", whiteVariety);
		mv.addObject("whiteVarietySize", whiteVariety.size());
		this.fillCommonData(mv, 0);
		this.fillLevelCommonData(mv, 0);
		return mv;
	}
	
	@RequestMapping(value = "/list.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView list(HttpServletRequest req,@RequestParam("id")Integer id){
		ModelAndView mv = new ModelAndView("beBlackWhite/list");
		try{
			CustomerUser customer = customerService.findById(id);
			List<HashMap<String,Object>> blackCate = this.beBlackWhiteListCategoryService.findAll(id,1);
			List<HashMap<String,Object>> whiteCate = this.beBlackWhiteListCategoryService.findAll(id,2);
			List<HashMap<String, Object>> blackVariety = this.beBlackWhiteListVarietyService.findAll(id,1);
			List<HashMap<String, Object>> whiteVariety = this.beBlackWhiteListVarietyService.findAll(id,2);
			List<HashMap<String,Object>> whiteItem = this.beBlackWhiteListItemService.findAll(id,2);
			mv.addObject("customer", customer);
			mv.addObject("blackCate", blackCate);
			mv.addObject("whiteCate", whiteCate);
			mv.addObject("whiteItem", whiteItem);
			mv.addObject("blackVariety", blackVariety);
			mv.addObject("whiteVariety", whiteVariety);
			}catch(Exception e){
				logger.error("添加失败", e);
			}
		return mv;
	}
	
	
	@RequestMapping(value="save.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String save(HttpServletRequest req){
		try{
			String id = req.getParameter("customerId");
			if (StringUtils.isEmpty(id)) {
				return "用户ID不能为空!";
			}
			int customerId = Integer.parseInt(id);
			PersonUser user=(PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			
			List<BeBlackWhiteListVariety> list1 = new ArrayList<BeBlackWhiteListVariety>();
			int blackItemVarietyRowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("blackItemVarietyRowCount"), "0"));
			for (int i = 0; i < blackItemVarietyRowCount; i++) {
				int blackItemVarietyId = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("blackItemVariety" + i), "0"));
				if (blackItemVarietyId == 0) {
					continue;
				}
				BeBlackWhiteListVariety blackWhiteListVariety = new BeBlackWhiteListVariety();
				blackWhiteListVariety.setVarietyId(blackItemVarietyId);
				list1.add(blackWhiteListVariety);
			}
			
			/*List<BeBlackWhiteListItem> list2 = new ArrayList<BeBlackWhiteListItem>();
			int blackItemRowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("blackItemRowCount"), "0"));
			for (int i = 0; i < blackItemRowCount; i++) {
				int blackItemId = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("blackItemId" + i), "0"));
				if (blackItemId == 0) {
					continue;
				}
				BlackWhiteListItem blackWhiteListItem = new BlackWhiteListItem();
				blackWhiteListItem.setItemId(blackItemId);
				list2.add(blackWhiteListItem);
			}*/
			
			List<BeBlackWhiteListVariety> list3 = new ArrayList<BeBlackWhiteListVariety>();
			int whiteItemVarietyRowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("whiteItemVarietyRowCount"), "0"));
			for (int i = 0; i < whiteItemVarietyRowCount; i++) {
				int whiteItemVariety = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("whiteItemVariety" + i), "0"));
				if (whiteItemVariety == 0) {
					continue;
				}
				BeBlackWhiteListVariety blackWhiteListVariety = new BeBlackWhiteListVariety();
				blackWhiteListVariety.setVarietyId(whiteItemVariety);
				list3.add(blackWhiteListVariety);
			}
			
			List<BeBlackWhiteListItem> list4 = new ArrayList<BeBlackWhiteListItem>();
			int whiteItemRowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("whiteItemRowCount"), "0"));
			for (int i = 0; i < whiteItemRowCount; i++) {
				int whiteItemId = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("whiteItemId" + i), "0"));
				if (whiteItemId == 0) {
					continue;
				}
				BeBlackWhiteListItem blackWhiteListItem = new BeBlackWhiteListItem();
				blackWhiteListItem.setItemId(whiteItemId);
				list4.add(blackWhiteListItem);
			}
			
			List<BeBlackWhiteListCategory> list5 = new ArrayList<BeBlackWhiteListCategory>();
			int blackCateRowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("blackCateRowCount"), "0"));
			for (int i = 0; i < blackCateRowCount; i++) {
				int blackCateId = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("blackCateId" + i), "0"));
				if (blackCateId == 0) {
					continue;
				}
				BeBlackWhiteListCategory blackWhiteListCategory = new BeBlackWhiteListCategory();
				blackWhiteListCategory.setCategoryId(blackCateId);
				list5.add(blackWhiteListCategory);
			}
			
			List<BeBlackWhiteListCategory> list6 = new ArrayList<BeBlackWhiteListCategory>();
			int whiteCateRowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("whiteCateRowCount"), "0"));
			for (int i = 0; i < whiteCateRowCount; i++) {
				int whiteCateId = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("whiteCateId" + i), "0"));
				if (whiteCateId == 0) {
					continue;
				}
				BeBlackWhiteListCategory blackWhiteListCategory = new BeBlackWhiteListCategory();
				blackWhiteListCategory.setCategoryId(whiteCateId);
				list6.add(blackWhiteListCategory);
			}
			this.beCustomerBlackWhiteListService.saveAll(user,customerId,list1,list3,list4,list5,list6);
			return "添加成功";
		}catch(Exception e){
			logger.error("添加失败", e);
			return e.getMessage();
		}

	}
	
	@RequestMapping(value="update.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String updateDo(HttpServletRequest req){
		try{
			String id = req.getParameter("customerId");
			if (StringUtils.isEmpty(id)) {
				return "用户ID不能为空!";
			}
			int customerId = Integer.parseInt(id);
			PersonUser user=(PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			
			List<BeBlackWhiteListVariety> list1 = new ArrayList<BeBlackWhiteListVariety>();
			int blackItemVarietyRowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("blackItemVarietyRowCount"), "0"));
			for (int i = 0; i < blackItemVarietyRowCount; i++) {
				int blackItemVarietyId = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("blackItemVariety" + i), "0"));
				if (blackItemVarietyId == 0) {
					continue;
				}
				BeBlackWhiteListVariety blackWhiteListVariety = new BeBlackWhiteListVariety();
				blackWhiteListVariety.setVarietyId(blackItemVarietyId);
				list1.add(blackWhiteListVariety);
			}
			
			/*List<BeBlackWhiteListItem> list2 = new ArrayList<BeBlackWhiteListItem>();
			int blackItemRowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("blackItemRowCount"), "0"));
			for (int i = 0; i < blackItemRowCount; i++) {
				int blackItemId = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("blackItemId" + i), "0"));
				if (blackItemId == 0) {
					continue;
				}
				BlackWhiteListItem blackWhiteListItem = new BlackWhiteListItem();
				blackWhiteListItem.setItemId(blackItemId);
				list2.add(blackWhiteListItem);
			}*/
			
			List<BeBlackWhiteListVariety> list3 = new ArrayList<BeBlackWhiteListVariety>();
			int whiteItemVarietyRowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("whiteItemVarietyRowCount"), "0"));
			for (int i = 0; i < whiteItemVarietyRowCount; i++) {
				int whiteItemVariety = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("whiteItemVariety" + i), "0"));
				if (whiteItemVariety == 0) {
					continue;
				}
				BeBlackWhiteListVariety blackWhiteListVariety = new BeBlackWhiteListVariety();
				blackWhiteListVariety.setVarietyId(whiteItemVariety);
				list3.add(blackWhiteListVariety);
			}
			
			List<BeBlackWhiteListItem> list4 = new ArrayList<BeBlackWhiteListItem>();
			int whiteItemRowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("whiteItemRowCount"), "0"));
			for (int i = 0; i < whiteItemRowCount; i++) {
				int whiteItemId = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("whiteItemId" + i), "0"));
				if (whiteItemId == 0) {
					continue;
				}
				BeBlackWhiteListItem blackWhiteListItem = new BeBlackWhiteListItem();
				blackWhiteListItem.setItemId(whiteItemId);
				list4.add(blackWhiteListItem);
			}
			
			List<BeBlackWhiteListCategory> list5 = new ArrayList<BeBlackWhiteListCategory>();
			int blackCateRowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("blackCateRowCount"), "0"));
			for (int i = 0; i < blackCateRowCount; i++) {
				int blackCateId = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("blackCateId" + i), "0"));
				if (blackCateId == 0) {
					continue;
				}
				BeBlackWhiteListCategory blackWhiteListCategory = new BeBlackWhiteListCategory();
				blackWhiteListCategory.setCategoryId(blackCateId);
				list5.add(blackWhiteListCategory);
			}
			
			List<BeBlackWhiteListCategory> list6 = new ArrayList<BeBlackWhiteListCategory>();
			int whiteCateRowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("whiteCateRowCount"), "0"));
			for (int i = 0; i < whiteCateRowCount; i++) {
				int whiteCateId = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("whiteCateId" + i), "0"));
				if (whiteCateId == 0) {
					continue;
				}
				BeBlackWhiteListCategory blackWhiteListCategory = new BeBlackWhiteListCategory();
				blackWhiteListCategory.setCategoryId(whiteCateId);
				list6.add(blackWhiteListCategory);
			}
			this.beCustomerBlackWhiteListService.updateAll(user,customerId,list1,list3,list4,list5,list6);
			return "添加成功";
		}catch(Exception e){
			logger.error("添加失败", e);
			return e.getMessage();
		}

	}
	
	private void fillCommonData(ModelAndView view,int businessId){
		List<ItemCategory> catList =null;
		if(businessId==0)
		catList= itemCategoryService.findAll();
		else {
			catList= itemCategoryService.findByBusinessId(businessId);
		}
		view.addObject("catList", catList);

		int cateId1=0;
		if(CollectionUtils.isNotEmpty(catList)){
			cateId1  = catList.get(0).getId();
		}

		view.addObject("cateId1", cateId1);

	}

	private void fillLevelCommonData(ModelAndView view, int businessId) {
		List<ItemCategory> catLevelList = null;
		catLevelList = itemCategoryService.findByParentId(businessId);
		view.addObject("catLevelList", catLevelList);
	}
	
	private void saveLog(HttpSession session,BeCustomerBlackWhiteList dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.BeCustomeBlackwhite.getName());
	       sysLog.setDataId(dto.getBeBlackwhiteId().toString());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<BeCustomerBlackWhiteList>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
