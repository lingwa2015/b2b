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
import com.b2b.common.domain.CustomerWise;
import com.b2b.common.domain.ItemVariety;
import com.b2b.common.domain.SysLog;
import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.WeightCoefficient;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.CustomerWiseService;
import com.b2b.service.LogService;
import com.b2b.web.util.NumberTool;
import com.b2b.web.util.SessionHelper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RequestMapping("customerWise")
@Controller
public class CustomerWiseController {
	private static final Logger logger = LoggerFactory.getLogger(CustomerWiseController.class);
	
	@Autowired
	private CustomerWiseService customerWiseService;
	
	@Autowired
	LogService logService;
	
	@RequestMapping(value = "/customerWiseList.htm")
	@ResponseBody
	public ModelAndView list(HttpServletRequest request) {
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("currentPage"), "1"));
		int pageSize = Integer.valueOf(StringUtils.defaultIfBlank(request.getParameter("pageSize"), Page.DEFAULT_PAGE_SIZE_STR));
		Page<CustomerWise> page = customerWiseService.findPage(currentPage,pageSize);
		return new ModelAndView("customerWise/list", "page", page);
	}
	
	@RequestMapping(value = "/customerWiseAdd.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView add() {
		CustomerWise dto = new CustomerWise();
		return new ModelAndView("customerWise/detail", "dto", dto);
	}
	
	@RequestMapping(value = "/customerWiseUpdate.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView update(@RequestParam("id")Integer id) {
		CustomerWise dto = this.customerWiseService.findById(id);
		return new ModelAndView("customerWise/detail", "dto", dto);
	}
	
	@RequestMapping(value="save.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String save(CustomerWise dto,HttpServletRequest request){
		String result = "操作成功";

		try{
			
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			String start = request.getParameter("startPrice");
			String end = request.getParameter("endPrice");
			String budGet = request.getParameter("budGet");
			if(null!=start){
				dto.setStartprice((int)Double.parseDouble(start)*100);
			}
			if(null!=end){
				dto.setEndprice((int)Double.parseDouble(end)*100);
			}
			if (null!=budGet) {
				long budget = NumberTool.str2Double2Fen(budGet.toString());
				dto.setBudget(budget);
			}
			if(dto.getWiseId()!=null&&dto.getWiseId()>0){
				dto.setUpdatedTime(new Date());
				dto.setUpdatedUserid(personUser.getId());
				this.customerWiseService.update(dto);
				this.saveLog(request.getSession(),dto, "修改客户智选属性，ID："+dto.getWiseId());
			}else{
				dto.setCreatedTime(new Date());
				dto.setCreatedUserid(personUser.getId());
				dto.setUpdatedTime(dto.getCreatedTime());
				dto.setUpdatedUserid(personUser.getId());
				this.customerWiseService.create(dto);
				this.saveLog(request.getSession(),dto, "添加客户智选属性，ID："+dto.getWiseId());
			}
		}catch(Exception e){
            logger.error("保存客户智选属性失败",e);
            result = "操作失败，原因："+e.getMessage();
		}

		return result;
	}
	
	@RequestMapping(value="delete.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delete(int id,HttpServletRequest request) {
		String result = "操作成功";
		try{
			CustomerWise dto = customerWiseService.findById(id);
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(dto!=null){
				CustomerWise customerWise = new CustomerWise();
				customerWise.setWiseId(id);
				customerWise.setStatus(Constant.DELETE_STATUS);
				customerWise.setUpdatedTime(new Date());
				customerWise.setUpdatedUserid(personUser.getId());
				customerWiseService.delete(customerWise);
				this.saveLog(request.getSession(),dto, "删除品种，ID："+dto.getWiseId());
			}
		}catch(Exception e){
            logger.error("删除品种失败",e);
            result = "操作失败，原因："+e.getMessage();
		}

		return result;
	}
	
	private void saveLog(HttpSession session,CustomerWise dto,String content){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.ITEM_CATEGORY.getName());
	       sysLog.setDataId(dto.getWiseId().toString());

	       String dataContent = new Gson().toJson(dto,
					new TypeToken<CustomerWise>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
