package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.StockCheckTemp;
import com.b2b.common.domain.SysLog;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.service.LogService;
import com.b2b.service.StockCheckTempService;
import com.b2b.web.util.SessionHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import java.util.List;

@Controller
@RequestMapping("stockCheckTemp")
public class StockCheckTempController {
	private static final Logger logger = LoggerFactory.getLogger(StockCheckTempController.class);
	private static final String DATE_FORMAT_YMD = "yyyy-MM-dd";
	
	@Autowired
	private StockCheckTempService stockCheckTempService;
	
	@Autowired
	LogService logService;
	/**
	 * 全库存盘点，添加到临时盘库列表
	 * @param session
	 * @param itemId
	 * @param itemName
	 * @param newnum
	 * @param oldCount
	 * @return
	 */
	@RequestMapping(value = "/add.do",method = RequestMethod.POST)
	@ResponseBody
	public String addToTempStockCheck(HttpSession session,@RequestParam("itemId")Integer itemId,@RequestParam("itemName")String itemName,@RequestParam("newnum")Integer newnum,@RequestParam("oldCount")Integer oldCount){
		StockCheckTemp existStock = this.stockCheckTempService.selectByPrimaryKey(itemId);
		PersonUser personUser = (PersonUser) session.getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return "你还未设置默认城市，联系管理员设置";
		}
		StockCheckTemp stockCheckTemp = new StockCheckTemp();
		stockCheckTemp.setCityId(personUser.getCityId());
		stockCheckTemp.setItemId(itemId);
		stockCheckTemp.setOldNum(oldCount);
		stockCheckTemp.setModifyNum(newnum);
		stockCheckTemp.setUserId(personUser.getId());
		stockCheckTemp.setUserName(personUser.getUserName());
		stockCheckTemp.setCreated(new Date());
		if(null!=existStock){
			try {
				if (newnum.equals(-1)){
					this.stockCheckTempService.deleteByPrimaryKey(itemId);
				} else {
					this.stockCheckTempService.updateByPrimaryKeySelective(stockCheckTemp);
				}
				return "200";
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return "201";
			}
		}else if (newnum != -1){
			stockCheckTemp.setItemName(itemName);
			try {
				this.stockCheckTempService.insert(stockCheckTemp);
				return "200";
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
				return "201";
			}
		} else {
			return "200";
		}
	}
	
	
	
	@RequestMapping(value="allTemp.htm")
	public ModelAndView getList(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("stockTemp/temp");
		PersonUser personUser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		List<StockCheckTemp> stockCheckTemps = this.stockCheckTempService.findAllByCityId(personUser.getCityId());
		TestController.getMenuPoint(mv, request);
		return mv.addObject("stockCheckTemps", stockCheckTemps);
	}
	
	private void saveLog(HttpSession session,StockCheckTemp dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.STOCK_CHECK.getName());
	       if(dto.getItemId()!=null){
	       sysLog.setDataId(dto.getItemId().toString());
	       }

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<StockCheckTemp>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
