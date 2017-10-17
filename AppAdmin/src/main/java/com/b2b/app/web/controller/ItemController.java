package com.b2b.app.web.controller;

import java.io.BufferedInputStream;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

import com.b2b.common.domain.Item;
import com.b2b.common.domain.ItemCategory;
import com.b2b.page.Page;
import com.b2b.service.ItemCategoryService;
import com.b2b.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {

	private static final Logger logger = LoggerFactory.getLogger(ItemController.class);
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	ItemCategoryService itemCategoryService;
	
	

	
	
	
	@RequestMapping(value = "/itemList.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView add() {
		
		ModelAndView view = new ModelAndView("itemList");
		view.addObject("test", "testbobo");

		//this.fillCommonData(view);
		
		return view;
	}

	private void fillCommonData(ModelAndView view){
		
		List<ItemCategory> catList = itemCategoryService.findAll();
		view.addObject("catList", catList);
		
	}
	
	
	
	
	
	
	
	
}
