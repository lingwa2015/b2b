package com.b2b.web.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.Constant;
import com.b2b.common.domain.CityRegion;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.Privilege;
import com.b2b.common.domain.SysLog;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.CityRegionService;
import com.b2b.service.LogService;
import com.b2b.web.util.SessionHelper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RequestMapping("cityRegion")
@Controller
public class CityRegionController {
	private static final Logger logger = LoggerFactory.getLogger(CityRegionController.class);
	
	@Autowired
	private CityRegionService cityRegionService;
	
	@Autowired
	private LogService logService;
		
	@RequestMapping("cityRegionList.htm")
	public ModelAndView list(HttpServletRequest request){
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		ModelAndView view = new ModelAndView("/region/list");
		PageHelper.startPage(currentPage, 15);
		List<CityRegion> cityRegions= this.cityRegionService.findByCityId(personUser.getCityId());
		PageInfo<CityRegion> info = new PageInfo<CityRegion>(cityRegions);
		Page<CityRegion> page = new Page<CityRegion>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		view.addObject("page", page);
		return view;
	}
	
	@RequestMapping("add.htm")
	public ModelAndView add(){
		ModelAndView view = new ModelAndView("/region/add");
		return view;
	}
	
	@RequestMapping(value="add.do",method = RequestMethod.POST)
	public String doadd(HttpServletRequest req,CityRegion cityRegion){
		try {
			PersonUser personUser=(PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你没有设置默认城市";
			}
			cityRegion.setCityId(personUser.getCityId());	
			cityRegion.setStatus(1);
			this.cityRegionService.insert(cityRegion);
			this.saveLog(req.getSession(), cityRegion, "添加区域",personUser.getCityId());
			return "redirect:/cityRegion/cityRegionList.htm";
			
		} catch (Exception e) {
			logger.error("添加区域异常："+e);
			return "redirect:/cityRegion/add.htm";
		}
	}
	
	private void saveLog(HttpSession session,CityRegion dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setCityId(cityId);
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType("Region");
	       if(dto.getId()!=null){
	       sysLog.setDataId(dto.getId().toString());
	       }

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<CityRegion>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
