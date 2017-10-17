package com.b2b.web.controller;

import com.b2b.common.domain.ItemScore;
import com.b2b.page.Page;
import com.b2b.service.ItemScoreService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


@RequestMapping("itemScore")
@Controller
public class ItemScoreController {
	private static final Logger logger = LoggerFactory.getLogger(ItemScoreController.class);
	
	@Autowired
	ItemScoreService itemScoreService;
	
	@RequestMapping("scoreList.htm")
	public ModelAndView list(HttpServletRequest request,@RequestParam("item_id")Integer itemId){
		ModelAndView view = new ModelAndView("item/scoreList");
		try {
			int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));
			PageHelper.startPage(currentPage, 50);
			List<ItemScore> itemScores = this.itemScoreService.findByItemId(itemId);
			PageInfo<ItemScore> info = new PageInfo<ItemScore>(itemScores);
			Page<ItemScore> page = new Page<ItemScore>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			view.addObject("itemId", itemId);
			view.addObject("page", page);
		} catch (NumberFormatException e) {
			logger.error(e.getMessage(), e);
			Page<ItemScore> page = new Page<ItemScore>(1, 1, 50, new ArrayList<ItemScore>());
			view.addObject("page", page);
		}
		TestController.getMenuPoint(view, request);
		return view;
	}
}
