package com.b2b.web.controller;

import java.util.Date;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.Constant;
import com.b2b.common.domain.ItemVariety;
import com.b2b.common.domain.SysLog;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.WeightCoefficient;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.LogService;
import com.b2b.service.WeightCoefficientService;
import com.b2b.web.util.SessionHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RequestMapping("weightCoefficient")
@Controller
public class WeightCoefficientController {
	private static final Logger logger = LoggerFactory.getLogger(WeightCoefficientController.class);
	
	@Autowired
	private WeightCoefficientService weightCoefficientService;
	
	@Autowired
	LogService logService;
	
	@RequestMapping(value = "/weightCoefficientList.htm")
	@ResponseBody
	public ModelAndView list(HttpServletRequest request) {
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		int pageSize = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("pageSize"), Page.DEFAULT_PAGE_SIZE_STR));
		Page<WeightCoefficient> page = weightCoefficientService.findPage(currentPage,pageSize);
		return new ModelAndView("weightCoefficient/list", "page", page);
	}
	
	@RequestMapping(value = "/weightCoefficientAdd.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView add() {
		ItemVariety dto = new ItemVariety();
		return new ModelAndView("weightCoefficient/detail", "dto", dto);
	}
	
	@RequestMapping(value = "/weightCoefficientUpdate.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView update(@RequestParam("id")Integer id) {
		WeightCoefficient dto = this.weightCoefficientService.findById(id);
		return new ModelAndView("weightCoefficient/detail", "dto", dto);
	}
	
	@RequestMapping(value="save.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String save(WeightCoefficient dto,HttpServletRequest request){
		String result = "操作成功";

		try{
			
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(dto.getWeightId()!=null&&dto.getWeightId()>0){
				dto.setUpdatedTime(new Date());
				dto.setUpdatedUserid(personUser.getId());
				this.weightCoefficientService.update(dto);
				this.saveLog(request.getSession(),dto, "修改权重系数，ID："+dto.getWeightId());
			}else{
				dto.setNewitemWeight(1);
				dto.setProfitWeight(1);
				dto.setItemWeights(1);
				dto.setCreatedTime(new Date());
				dto.setCreatedUserid(personUser.getId());
				dto.setUpdatedTime(dto.getCreatedTime());
				dto.setUpdatedUserid(personUser.getId());
				this.weightCoefficientService.create(dto);
				this.saveLog(request.getSession(),dto, "添加权重系数，ID："+dto.getWeightId());
			}
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
			WeightCoefficient dto = weightCoefficientService.findById(id);
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(dto!=null){
				WeightCoefficient weightCoefficient = new WeightCoefficient();
				weightCoefficient.setWeightId(id);
				weightCoefficient.setStatus(Constant.DELETE_STATUS);
				weightCoefficient.setUpdatedTime(new Date());
				weightCoefficient.setUpdatedUserid(personUser.getId());
				weightCoefficientService.delete(weightCoefficient);
				this.saveLog(request.getSession(),dto, "删除品种，ID："+dto.getWeightId());
			}
		}catch(Exception e){
            logger.error("删除品种失败",e);
            result = "操作失败，原因："+e.getMessage();
		}

		return result;
	}
	
	private void saveLog(HttpSession session,WeightCoefficient dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.WeightCoefficient.getName());
	       sysLog.setDataId(dto.getWeightId().toString());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<WeightCoefficient>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
