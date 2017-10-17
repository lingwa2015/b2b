package com.b2b.web.app.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.Constant;
import com.b2b.common.domain.Item;
import com.b2b.common.domain.ItemCategory;
import com.b2b.common.domain.CustomerUser;
import com.b2b.page.Page;
import com.b2b.service.ItemCategoryService;
import com.b2b.service.ItemService;
import com.b2b.web.util.SessionHelper;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

@Controller
@RequestMapping("/appItem")
public class AppItemController {

	private static final Logger logger = LoggerFactory.getLogger(AppItemController.class);
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	ItemCategoryService itemCategoryService;
	
	
	
	@RequestMapping(value = "/appItemList.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView itemList(HttpServletRequest request) {
		
		int businessId=((CustomerUser) request.getSession().getAttribute(Constant.USER_APP_KEY)).getBusinessId();
		List<ItemCategory> categoryList=null;
		List<Item> itemList=null;
//		if(businessId==0){
//			categoryList = itemCategoryService.findAll();
//			itemList = itemService.findAll();
//		}else {
			categoryList=itemCategoryService.findByBusinessId(businessId);
			itemList=itemService.findAll(businessId);
//		}
		
		Map<Integer, List<Item>> cateGoryId2Items = new HashMap<Integer, List<Item>>();
		
		for(Item item : itemList){
			if(cateGoryId2Items.get(item.getCategoryId()) == null){
				cateGoryId2Items.put(item.getCategoryId(), new ArrayList<Item>());
			}
			
			cateGoryId2Items.get(item.getCategoryId()).add(item);
		}
		
		Gson gson = new Gson();
		Type t = new TypeToken<Map<Integer, List<Item>>>() {}.getType();
		
		
		
		ModelAndView view = new ModelAndView("app/item/itemList");
		view.addObject("catList", categoryList);

		view.addObject("itemList", gson.toJson(cateGoryId2Items,t)+";");
		//this.fillCommonData(view);
		return view;
	}

	@RequestMapping(value = "/appGoods.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView goodsList(HttpServletRequest request) {
		List<ItemCategory> categoryList=null;
		List<Item> itemList=null;
		String catId = request.getParameter("catId");
		String itemName = request.getParameter("itemName");
		String categoryName = request.getParameter("categoryName");
		if(StringUtils.isBlank(categoryName)){
			categoryName="全部";
		}
		if(StringUtils.isNotBlank(catId)){
			categoryList=itemCategoryService.findAll(Integer.parseInt(catId));
			itemList=itemService.findCatIdAll(Integer.parseInt(catId),0,itemName);
		}else{
			categoryList=itemCategoryService.findAll();
			itemList=itemService.findCatIdAll(0,0,itemName);
		}
		
//		if(businessId==0){
//			categoryList = itemCategoryService.findAll();
//			itemList = itemService.findAll();
//		}else {
			
			
//		}
                
                List<Pair<ItemCategory,List<Item>>> categoryItems=new ArrayList<Pair<ItemCategory, List<Item>>>();
		if(categoryList!=null && categoryList.size()>0){
		int i=0;
                    for (ItemCategory category : categoryList) {
                        List<Item> itemListTemp= new ArrayList<Item>();
                       for(Item item : itemList){
                           if(category.getId()==item.getCategoryId()){
                        	   i++;
                               itemListTemp.add(item);
                           }
                       }
                       if(i>0){
                    	   categoryItems.add(new ImmutablePair<ItemCategory, List<Item>>(category, itemListTemp));
                       }
                       i=0;
                    }
                }
		
		if(StringUtils.isBlank(itemName)){
			itemName="";
		}else{
			categoryName="搜索";
		}
		ModelAndView view = new ModelAndView("app/item/goods");
		view.addObject("categoryItems", categoryItems);
		view.addObject("size", categoryItems.size());
		view.addObject("catId", catId);
		view.addObject("categoryName", categoryName);
		view.addObject("itemName", itemName);
		view.addObject("catLevelId", "");
		//this.fillCommonData(view);
		this.fillCommonData(view);
		this.fillLevelCommonData(view);
		return view;
	}
	
	
	@RequestMapping(value = "/appLevelGoods.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView goodsLevelList(HttpServletRequest request) {
		List<ItemCategory> categoryList=null;
		List<Item> itemList=null;
		String catLevelId = request.getParameter("catLevelId");
		String catId = request.getParameter("catId");
		if(StringUtils.isNotBlank(catLevelId)){
			categoryList=itemCategoryService.findByLevelCatId(0,Integer.parseInt(catLevelId));
			itemList=itemService.findCatIdAll(0,Integer.parseInt(catLevelId),null);
		}
                List<Pair<ItemCategory,List<Item>>> categoryItems=new ArrayList<Pair<ItemCategory, List<Item>>>();
		if(categoryList!=null && categoryList.size()>0){
                    for (ItemCategory category : categoryList) {
                        List<Item> itemListTemp= new ArrayList<Item>();
                       for(Item item : itemList){
                    	   int catid=category.getId();
                    	   int catLevel=item.getCategorylevelId();
                           if(catid==catLevel){
                               itemListTemp.add(item);
                           }
                       }
                       categoryItems.add(new ImmutablePair<ItemCategory, List<Item>>(category, itemListTemp));
                    }
                }
		ModelAndView view = new ModelAndView("app/item/goods");
		view.addObject("categoryItems", categoryItems);
		view.addObject("catLevelId", catLevelId);
		view.addObject("catId", catId);
		view.addObject("categoryName", "饼干糕点");
		view.addObject("size", categoryItems.size());
		view.addObject("itemName", "");
		//this.fillCommonData(view);
		this.fillCommonData(view);
		this.fillLevelCommonData(view);
		return view;
	}
        

	@RequestMapping("/apptest.htm")
	public ModelAndView welcome(HttpServletRequest request) {
		return new ModelAndView("app/item/testOpener");
	}
	
	
	private void fillCommonData(ModelAndView view) {
		List<ItemCategory> catList = null;
		catList = itemCategoryService.findAll();
		view.addObject("catList", catList);

	}
	
	private void fillLevelCommonData(ModelAndView view) {
		List<ItemCategory> catLevelList = null;
		catLevelList = itemCategoryService.findByParentId(0);
		view.addObject("catLevelList", catLevelList);

	}

}
