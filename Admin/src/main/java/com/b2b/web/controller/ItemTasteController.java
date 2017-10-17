package com.b2b.web.controller;

import java.util.Date;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.b2b.common.domain.ItemTaste;
import com.b2b.common.domain.SysLog;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.service.LogService;
import com.b2b.web.util.SessionHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RequestMapping("itemTaste")
@Controller
public class ItemTasteController {
	private static final Logger logger = LoggerFactory.getLogger(ItemTasteController.class);
	
	@Autowired
	LogService logService;
	
	
	
	private void saveLog(HttpSession session,ItemTaste dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.ItemTaste.getName());
	       sysLog.setDataId(dto.getId().toString());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<ItemTaste>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
