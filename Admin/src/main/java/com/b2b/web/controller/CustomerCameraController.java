package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.CustomerCamera;
import com.b2b.common.domain.CustomerUser;
import com.b2b.common.domain.PersonUser;
import com.b2b.page.Page;
import com.b2b.service.CustomerCameraService;
import com.b2b.service.CustomerService;
import com.b2b.web.util.ApiService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

@RequestMapping("customerCamera")
@Controller
public class CustomerCameraController {
	
	@Autowired
	CustomerCameraService customerCameraService;
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ApiService apiService;
	
	@RequestMapping("camera.htm")
	public ModelAndView list(HttpServletRequest request){
		ModelAndView mv = new ModelAndView("camera/list");
		PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == personUser.getCityId()){
			return new ModelAndView("noCity");
		}
		int currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));
		String param = request.getParameter("param");
		mv.addObject("param", param);
		String userName = request.getParameter("userName");
		mv.addObject("userName", userName);
		PageHelper.startPage(currentPage, 50);
		List<CustomerCamera> customers=this.customerCameraService.findByCondition(userName,param,personUser.getCityId());
		PageInfo<CustomerCamera> info = new PageInfo<CustomerCamera>(customers);
		Page<CustomerCamera> page = new Page<CustomerCamera>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
		mv.addObject("page", page);
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping("changeNum.do")
	@ResponseBody
	public String changeNum(HttpServletRequest request,@RequestParam("cid")Integer cid,@RequestParam("num")String num){
		try {
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "202";
			}
			CustomerUser user = this.customerService.findById(cid);
			if(null==user || !user.getCityId().equals(personUser.getCityId())){
				return "203";
			}
			CustomerCamera camera = this.customerCameraService.findByCid(cid);
			if(null!=camera){
				if(StringUtils.isEmpty(num)){
					num = null;
				}
				this.customerCameraService.updateNum(cid,num);
			}else{
				CustomerCamera camera2 = new CustomerCamera();
				camera2.setCid(cid);
				if(StringUtils.isEmpty(num)){
					camera2.setNum(null);
				}else{
					camera2.setNum(num);
				}
				camera2.setCreatedTime(new Date());
				this.customerCameraService.insert(camera2);
			}
			return "200";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "201";
	}
	
	@RequestMapping("changeOpen.do")
	@ResponseBody
	public String changeOpen(HttpServletRequest request,@RequestParam("cid")Integer cid,@RequestParam("openWelcome")Integer openWelcome){
		try {
			PersonUser personUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "202";
			}
			CustomerUser user = this.customerService.findById(cid);
			if(null==user || !user.getCityId().equals(personUser.getCityId())){
				return "203";
			}
			CustomerCamera camera = this.customerCameraService.findByCid(cid);
			if(null!=camera && null!=camera.getNum()){
				this.customerCameraService.updateOpen(cid,openWelcome);
				if(1==openWelcome){
					String url = "http://120.26.56.240:9511/lw_action?did="+camera.getNum()+"&action=1&lparam=1";
					apiService.doGet(url);
				}else{
					String url = "http://120.26.56.240:9511/lw_action?did="+camera.getNum()+"&action=1&lparam=0";
					apiService.doGet(url);
				}
			}
			return "200";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "201";
	}
}
