package com.b2b.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.b2b.service.BeBlackWhiteListItemService;

@RequestMapping("beBlackWhiteItem")
@Controller
public class BeBlackWhiteListItemController {
	private static final Logger logger = LoggerFactory.getLogger(BeBlackWhiteListItemController.class);
	
	@Autowired
	private BeBlackWhiteListItemService beBlackWhiteListItemService;
	
	@RequestMapping(value="delete.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delete(int id){
		String result = "操作成功";
		try{
			this.beBlackWhiteListItemService.deleteById(id);
		}catch(Exception e){
            logger.error("删除准黑白单品失败",e);
            result = "操作失败，原因："+e.getMessage();
		}

		return result;
	}
}
