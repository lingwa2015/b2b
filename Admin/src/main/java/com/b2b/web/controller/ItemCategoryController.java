package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.ItemCategory;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.SysLog;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.ItemCategoryService;
import com.b2b.service.LogService;
import com.b2b.web.util.SessionHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;

@Controller
@RequestMapping("/itemCategory")
public class ItemCategoryController {

	private static final Logger logger = LoggerFactory.getLogger(ItemCategoryController.class);

	@Autowired
	ItemCategoryService itemCategoryService;

	@Autowired
	LogService logService;

	@RequestMapping(value = "/itemCategoryList.htm")
	@ResponseBody
	public ModelAndView list(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("itemCategory/list");
		ItemCategory itemCategory = new ItemCategory();
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		int pageSize = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("pageSize"), Page.DEFAULT_PAGE_SIZE_STR));
		//Page<ItemCategory> page = itemCategoryService.findPage(itemCategory, currentPage,pageSize,user.getBusinessId());
		Page<HashMap<String, Object>> page = itemCategoryService.findAllPage(itemCategory, currentPage,pageSize,personUser.getBusinessId(),personUser.getCityId());
//		if(page.getResult().size()>0)
		mv.addObject("page", page);
		TestController.getMenuPoint(mv, request);
		return mv;
//		return new ModelAndView();
	}


	@RequestMapping(value = "/itemCategoryAdd.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView add(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("itemCategory/detail");
		ItemCategory dto = new ItemCategory();
		mv.addObject("dto", dto);
		TestController.getMenuPoint(mv, request);
		return mv;
	}

	@RequestMapping(value = "/itemCategoryUpdate.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView update(int id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("itemCategory/detail");
		//ItemCategory dto = itemCategoryService.findById(id);
		HashMap<String, Object> dto = itemCategoryService.findWithTwoCat(id);
		mv.addObject("dto", dto);
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping(value = "/itemTwoCategoryAdd.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView TwoCategoryAdd(int id, HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("itemCategory/twoCat");
		ItemCategory dto = itemCategoryService.findById(id);

		mv.addObject("dto", dto);
		TestController.getMenuPoint(mv, request);
		return mv;
	}

	@RequestMapping(value="save.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String save(ItemCategory dto,HttpServletRequest request) {
		String result = "操作成功";

		try{
			String check = itemCategoryService.check(dto);
			if(StringUtils.isNotBlank(check)){
				return check;
			}
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "用户没有设置默认城市";
			}
			dto.setBusiness_id(personUser.getBusinessId());
			if(dto.getId()!=null&&dto.getId()>0){
				ItemCategory category = this.itemCategoryService.findById(dto.getId());
				if(null==category || !category.getCityId().equals(personUser.getCityId())){
					return "你设置的默认城市与操作城市不符";
				}
				dto.setUpdatedTime(new Date());
				dto.setUpdatedUserid(personUser.getId());
				itemCategoryService.update(dto);
				this.saveLog(request.getSession(),dto, "修改商品类目，名称："+dto.getCategoryName());
			}else{
				dto.setCityId(personUser.getCityId());
				dto.setCreatedTime(new Date());
				dto.setCreatedUserid(personUser.getId());
				dto.setUpdatedTime(dto.getCreatedTime());
				dto.setUpdatedUserid(personUser.getId());
				itemCategoryService.create(dto);
				this.saveLog(request.getSession(),dto, "添加商品类目，名称："+dto.getCategoryName());
			}
		}catch(Exception e){
            logger.error("保存类目失败",e);
            result = "操作失败，原因："+e.getMessage();
		}

		return result;
	}
	
	@RequestMapping(value="addTwoCategory.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addTwoCategory(HttpServletRequest request,@RequestParam("id")Integer id,@RequestParam("categoryName")String categoryName) {
		String result = "操作成功";

		try{
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			ItemCategory itemCategory = new ItemCategory();
			if(null == personUser.getCityId()){
				return "用户没有设置默认城市";
			}else{
				itemCategory.setCityId(personUser.getCityId());
			}
			itemCategory.setParentId(id);
			itemCategory.setBusiness_id(0);
			itemCategory.setCategoryName(categoryName);
			itemCategory.setCreatedTime(new Date());
			itemCategory.setUpdatedTime(itemCategory.getCreatedTime());
			itemCategory.setCreatedUserid(personUser.getId());
			itemCategory.setUpdatedUserid(personUser.getId());
			itemCategory.setStatus(1);
			itemCategoryService.create(itemCategory);
			this.saveLog(request.getSession(),itemCategory, "添加商品二级类目，名称："+itemCategory.getCategoryName());
		}catch(Exception e){
            logger.error("保存类目失败",e);
            result = "操作失败，原因："+e.getMessage();
		}

		return result;
	}

	@RequestMapping(value="delete.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delete(int id,HttpServletRequest request) {
		String result = "操作成功";
		try{
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			ItemCategory dto = itemCategoryService.findById(id);
			if(null == personUser.getCityId()){
				return "用户没有设置默认城市";
			}
			if(dto!=null && personUser.getCityId().equals(dto.getCityId()) ){
				itemCategoryService.delete(id);
				this.saveLog(request.getSession(),dto, "删除商品类目，名称："+dto.getCategoryName());
			}else{
				return "用户默认城市不匹配";
			}
		}catch(Exception e){
            logger.error("删除类目失败",e);
            result = "操作失败，原因："+e.getMessage();
		}

		return result;
	}

	private void saveLog(HttpSession session,ItemCategory dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setCityId(dto.getCityId());
	       sysLog.setDataType(LogDataTypeEnum.ITEM_CATEGORY.getName());
	       sysLog.setDataId(dto.getId().toString());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<ItemCategory>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
