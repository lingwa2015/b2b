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
import com.b2b.common.dao.ShopBlackListMapper;
import com.b2b.common.domain.BeBlackWhiteListItem;
import com.b2b.common.domain.BeBlackWhiteListVariety;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.Item;
import com.b2b.common.domain.ItemCategory;
import com.b2b.common.domain.ItemVariety;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.ShopBlackList;
import com.b2b.common.domain.SysLog;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.service.CustomerService;
import com.b2b.service.ItemCategoryService;
import com.b2b.service.ItemService;
import com.b2b.service.LogService;
import com.b2b.service.ShopBlackListService;
import com.b2b.web.util.SessionHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RequestMapping("shopBlack")
@Controller
public class ShopBlackListController {
	private static final Logger logger = LoggerFactory.getLogger(ShopBlackListController.class);
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ItemCategoryService itemCategoryService;
	
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private ShopBlackListService shopBlackListService;
	
	@Autowired
	private LogService logService;
	
	@RequestMapping("add.htm")
	public ModelAndView add(HttpServletRequest request,@RequestParam("shop_id")Integer shop_id){
		ModelAndView mv = new ModelAndView("shopBlack/add");
		PersonUser puser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == puser.getCityId()){
			return new ModelAndView("noCity");
		}
		CustomerUser user = this.customerService.findById(shop_id);
		if(null==user || !user.getCityId().equals(puser.getCityId())){
			return new ModelAndView("notCity");
		}
		mv.addObject("user", user);
		List<Item> itemList = itemService.findAllWithStockByCityId(puser.getCityId());
		mv.addObject("itemList", itemList);
		this.fillCommonData(mv, puser.getCityId());
		List<HashMap<String,Object>> blackItems = this.shopBlackListService.findByShopId(shop_id);
		mv.addObject("blackItems", blackItems);
		mv.addObject("size", blackItems.size());
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

	}
	
	@RequestMapping(value="add.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String doadd(HttpServletRequest req,@RequestParam("shop_id")Integer shop_id){
		String result = "保存成功";
		try {
			String id = req.getParameter("shop_id");
			if (StringUtils.isEmpty(id)) {
				return "用户ID不能为空!";
			}
			int blackItemRowCount = Integer.parseInt(StringUtils.defaultString(
					req.getParameter("blackItemRowCount"), "0"));
			List<ShopBlackList> ShopBlackList = new ArrayList<ShopBlackList>();
			for (int i = 0; i < blackItemRowCount; i++) {
				int blackItemId = Integer.parseInt(StringUtils.defaultString(
						req.getParameter("blackItemId" + i), "0"));
				if (blackItemId == 0) {
					continue;
				}
				ShopBlackList list = new ShopBlackList();
				list.setItemId(blackItemId);
				list.setShopId(shop_id);
				list.setState(1);
				list.setType(1);
				ShopBlackList.add(list);
			}
			this.shopBlackListService.save(ShopBlackList,shop_id);
		} catch (NumberFormatException e) {
			logger.error("保存失败",e);
			result = "保存失败，原因："+e.getMessage();
		}
		return result;
	}
	
	private void saveLog(HttpSession session,ShopBlackList dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.CustomeBlackwhite.getName());
	       sysLog.setDataId(dto.getId().toString());

	       if(dto!=null){
		       String dataContent = new Gson().toJson(dto,
						new TypeToken<CustomerUser>() {
						}.getType());

		       sysLog.setDataContent(dataContent);
	       }

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
