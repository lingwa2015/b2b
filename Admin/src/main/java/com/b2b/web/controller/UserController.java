package com.b2b.web.controller;

import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.domain.Token;
import com.b2b.common.util.EncryptHelper;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.page.Page;
import com.b2b.service.*;
import com.b2b.web.util.FileUtil;
import com.b2b.web.util.MD5Util;
import com.b2b.web.util.SessionHelper;
import com.b2b.web.wx.util.*;
import com.b2b.web.wx.util.pay.OrderUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController {
	private static String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URL&response_type=code&scope=snsapi_userinfo"
			+ "&state=STATE#wechat_redirect";
	@Value("${item_image_path}")
	private String imagePath;
	
	@Autowired
	UserService userService;

	@Autowired
	LogService logService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	PersonRegionService personRegionService;
	
	@Autowired
	ReseauService reseauService;
	
	@Autowired
	TokenService tokenService;
	
	@Autowired
	private Properties properties;
	
	@Autowired
	ShopUserService shopUserService;
	
	@Autowired
	CityService cityService;
	
	@Autowired
	UserCityService userCityService;

	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/userList.htm", produces="text/html;charset=UTF-8")
	@ResponseBody
	public ModelAndView listView(HttpServletRequest request,Model model,
			String currentPage,
			String userName,
			String mobilePhone) {
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		//int _currentPage = Integer.valueOf(StringUtils.defaultIfBlank(currentPage, "1"));
		int _currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
				request.getParameter("currentPage"), "1"));

		//查询条件
		PersonUser personUser =new PersonUser();

		//组装查询条件
		personUser.setUserName(userName);
		personUser.setMobilePhone(mobilePhone);
		personUser.setStatus(Constant.VALID_STATUS);
		personUser.setDingCityId(user.getCityId());
		//
//		PersonUser curUser=SessionHelper.getInstance().getUser();
		PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		int businessId=curUser.getBusinessId();
		Page<PersonUser> page=userService.findPage(personUser, _currentPage, Page.DEFAULT_PAGE_SIZE,businessId);

		ModelAndView mv=new ModelAndView("user/userList");

		mv.addObject("userName", userName);

		mv.addObject("mobilePhone", mobilePhone);

		mv.addObject("page", page);
		model.addAttribute("businessId", ((PersonUser) request.getSession().getAttribute(Constant.USER_KEY)).getBusinessId());
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping(value = "/userlist.htm", produces="text/html;charset=UTF-8")
	@ResponseBody
	public ModelAndView list(HttpServletRequest request,Model model,
							 String currentPage,
							 String userName,
							 String mobilePhone) {

		ModelAndView mv = new ModelAndView("user/list");
		try {
			PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == user.getCityId()){
				return new ModelAndView("noCity");
			}
			int _currentPage = Integer.valueOf(StringUtils.defaultIfBlank(
					request.getParameter("currentPage"), "1"));
			PageHelper.startPage(_currentPage, 50);
			//查询条件
			PersonUser personUser =new PersonUser();

			//组装查询条件
			personUser.setUserName(userName);
			personUser.setMobilePhone(mobilePhone);
			personUser.setStatus(Constant.VALID_STATUS);
			personUser.setDingCityId(user.getCityId());

			List<PersonUser> users = this.userService.findUserAndRolesNew(user.getCityId(), userName, mobilePhone, Constant.VALID_STATUS);
			PageInfo<PersonUser> info = new PageInfo<PersonUser>(users);
			Page<PersonUser> page=new Page<PersonUser>(info.getPageNum(),info.getTotal(),info.getPageSize(),info.getList());
			mv.addObject("userName", userName);

			mv.addObject("mobilePhone", mobilePhone);

			mv.addObject("page", page);
			model.addAttribute("businessId", ((PersonUser) request.getSession().getAttribute(Constant.USER_KEY)).getBusinessId());
			TestController.getMenuPoint(mv, request);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			Page<PersonUser> page = new Page<PersonUser>(1, 1, 50, new ArrayList<PersonUser>());
			mv.addObject("page", page);
		}
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping("edit.htm")
	public ModelAndView edit(HttpServletRequest request,@RequestParam("userId")int userId){
		ModelAndView view = new ModelAndView("/user/add");
		PersonUser puser = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == puser.getCityId()){
			return new ModelAndView("noCity");
		}
		PersonUser user = this.userService.findById(userId);
		if(user==null || !user.getDingCityId().equals(puser.getCityId())){
			return new ModelAndView("notCity");
		}
		List<Role> roles = this.roleService.findAllByCityId(user.getCityId());
		List<Role> exist = this.roleService.findByUserId(userId);
		view.addObject("user", user);
		view.addObject("roles", roles);
		view.addObject("exist", exist);
		view.addObject("size", roles.size());
		TestController.getMenuPoint(view, request);
		return view;
	}
	
	@RequestMapping(value="addRole.do",method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String addRole(HttpServletRequest req){
		try {
			int userId = Integer.parseInt(StringUtils.defaultIfBlank(
					req.getParameter("userId"), "0"));
			if(userId==0){
				return "请选择用户进行操作!";
			}
			PersonUser personUser = (PersonUser) req.getSession().getAttribute(Constant.USER_KEY);
			if(null == personUser.getCityId()){
				return "你还未设置默认城市，联系管理员设置";
			}
			PersonUser user = this.userService.findById(userId);
			if(user==null || !user.getDingCityId().equals(personUser.getCityId())){
				return "你设置的默认城市与操作城市不符";
			}
			int num = Integer.parseInt(StringUtils.defaultIfBlank(
					req.getParameter("num"), "0"));
			ArrayList<Integer> List = new ArrayList<Integer>();
			for (int i = 1; i <= num; i++) {
				int roleId = Integer.parseInt(StringUtils.defaultIfBlank(
						req.getParameter("checkbox" + i), "0"));
				if (roleId == 0) {
					continue;
				}
				List.add(roleId);
			}
			this.userService.saveRole(userId,List);
			return "添加成功";
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			logger.error("用户关联角色失败"+e);
			return "添加失败"+e;
		}
	}


	@RequestMapping(value = "/userAdd.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView addView(Model model, HttpServletRequest request) {
		ModelAndView mv=new ModelAndView("user/userAdd");
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		//List<Reseau> lists = this.reseauService.findAllByCityId(user.getCityId());
		//List<City> citys = this.cityService.findAllOpenCity();
		//mv.addObject("reseaus", lists);
		//mv.addObject("citys", citys);
		TestController.getMenuPoint(mv, request);
		return mv;

	}


	@RequestMapping(value = "/userUpdate.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView updateView(HttpServletRequest request,int id) {
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		PersonUser personUser=userService.findById(id);

		ModelAndView mv = new ModelAndView("user/userUpdate");
		mv.addObject("user",personUser);
		//List<City> citys = this.cityService.findAllOpenCity();
		//List<UserCity> exist = this.userCityService.findByUserId(id);
		//mv.addObject("citys", citys);
		//mv.addObject("exist", exist);
		TestController.getMenuPoint(mv, request);
		return mv;
	}

	@RequestMapping(value = "/userUpdatePassword.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView updatePasswordView(HttpServletRequest request,int id) {

		PersonUser personUser=userService.findById(id);

		ModelAndView mv = new ModelAndView("user/userUpdatePassword");

		mv.addObject("user",personUser);

		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping(value = "/setPost.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView setPost(HttpServletRequest request,int id) {
		PersonUser user = (PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		if(null == user.getCityId()){
			return new ModelAndView("noCity");
		}
		PersonUser personUser=userService.findById(id);
		List<PersonUser> users = this.userService.findUsershFirstByCityId(user.getCityId());
		ModelAndView mv = new ModelAndView("user/setPost");
		//List<Reseau> lists = this.reseauService.findAllByCityId(user.getCityId());
		//mv.addObject("reseaus", lists);
		mv.addObject("user",personUser);
		mv.addObject("users",users);
		TestController.getMenuPoint(mv, request);
		return mv;
	}
	
	@RequestMapping(value = "/setPost.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String setPost(HttpServletRequest request,PersonUser user) {
		String result = "保存成功";
		try {
			PersonUser personUser=userService.findById(user.getId());
      if(StringUtils.isEmpty(user.getPost())){
			   personUser.setPost(null);
			   if(null == user.getGread()){
				   personUser.setGread(null);
			   }else{
				   personUser.setGread(user.getGread());
			   }
			   if(null == user.getManagerId()){
				   personUser.setManagerId(null);
			   }else{
				   personUser.setManagerId(user.getManagerId());
			   }
      }else{
			   personUser.setPost(user.getPost());
			   if(null == user.getGread()){
				   result = "201";
			   }else{
				   personUser.setGread(user.getGread());
			   }
			   if(null == user.getManagerId()){
				   personUser.setManagerId(null);
			   }else{
				   personUser.setManagerId(user.getManagerId());
			   }
      }
      
      
      //personUser.setReseauId(user.getReseauId());
      userService.update(personUser);
      this.saveLog(request.getSession(),personUser, "设置岗位分级，用户名："+personUser.getUserName(),personUser.getCityId());
		}catch(Exception e){
			logger.error("设置岗位分级失败",e);
			result = "设置岗位分级失败，原因："+e.getMessage();
		}

		return result;

	}


	@RequestMapping(value = "/userSave.do", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
	public String addOrUpdate(PersonUser personUser , HttpServletRequest request,@RequestParam("myfile") MultipartFile myfile) {

		String result = "保存成功";

		try{
			PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			if(null == curUser.getCityId()){
				return "你还未设置默认城市";
			}
			//有主键就更新
			if(personUser.getId()!=null && personUser.getId()>0){

				String oldMobilePhone = request.getParameter("oldMobilePhone");

				PersonUser editUser=userService.findById(personUser.getId());
				//检测手机号是否存在
				if(!personUser.getMobilePhone().equals(oldMobilePhone))
					if(isPhoneExist(personUser))
						return "该手机号已存在";
				
				if (!myfile.isEmpty()) {
					File file = new File(imagePath);
					if (!file.exists()) {
						file.mkdir();
					}
					try {
						String oldName = myfile.getOriginalFilename();
						String name = FileUtil.genUploadFileName(oldName);
						FileUtils.copyInputStreamToFile(myfile.getInputStream(),
								new File(imagePath, name));
						editUser.setWeixinimg("http://assets.lingwa.com.cn/images/item/"+name);
					} catch (IOException e) {
						e.printStackTrace();
						logger.error("上传图片失败", e);
						result = "操作失败";
					}
				}
				editUser.setUserName(personUser.getUserName());
				editUser.setMobilePhone(personUser.getMobilePhone());
				if(StringUtils.isEmpty(personUser.getWeixinnum())){
					editUser.setWeixinnum(null);
				}else{
					editUser.setWeixinnum(personUser.getWeixinnum());
				}
				
				if(StringUtils.isEmpty(personUser.getCompanyMemo())){
					editUser.setCompanyMemo(null);
				}else{
					editUser.setCompanyMemo(personUser.getCompanyMemo());
				}
				editUser.setReseauId(personUser.getReseauId());
				editUser.setUpdatedUserid(curUser.getId());
				editUser.setUpdatedTime(new Date());
//				int num = Integer.parseInt(StringUtils.defaultIfBlank(
//						request.getParameter("num"), "0"));
//				ArrayList<Integer> list = new ArrayList<Integer>();
//				for (int i = 1; i <= num; i++) {
//					int cityId = Integer.parseInt(StringUtils.defaultIfBlank(
//							request.getParameter("checkbox" + i), "0"));
//					if (cityId == 0) {
//						continue;
//					}
//					list.add(cityId);
//				}
				//更新用户
				userService.update(editUser);
				this.saveLog(request.getSession(),personUser, "修改用户，用户名："+personUser.getUserName(),curUser.getCityId());
			}else{
				//检测手机号是否存在
				if(isPhoneExist(personUser))
					return "该手机号已存在";
				if (!myfile.isEmpty()) {
					File file = new File(imagePath);
					if (!file.exists()) {
						file.mkdir();
					}
					try {
						String oldName = myfile.getOriginalFilename();
						String name = FileUtil.genUploadFileName(oldName);
						FileUtils.copyInputStreamToFile(myfile.getInputStream(),
								new File(imagePath, name));
						personUser.setWeixinimg("http://assets.lingwa.com.cn/images/item/"+name);
					} catch (IOException e) {
						e.printStackTrace();
						logger.error("上传图片失败", e);
						result = "操作失败";
					}
				}
				int businessId=((PersonUser) request.getSession().getAttribute(Constant.USER_KEY)).getBusinessId();
				int managerShopId=((PersonUser) request.getSession().getAttribute(Constant.USER_KEY)).getManagershopid();
				if(StringUtils.isEmpty(personUser.getWeixinnum())){
					personUser.setWeixinnum(null);
				}
				if(StringUtils.isEmpty(personUser.getPost())){
					personUser.setPost(null);
				}
				if(StringUtils.isEmpty(personUser.getCompanyMemo())){
					personUser.setCompanyMemo(null);
				}
				personUser.setCityId(curUser.getCityId());
				personUser.setDingCityId(curUser.getCityId());
				personUser.setIsadmin(Constant.Admin_User);
				personUser.setBusinessId(businessId);
				personUser.setManagershopid(0);
				personUser.setCreatedUserid(curUser.getId());
				personUser.setCreatedTime(new Date());
				personUser.setUpdatedUserid(curUser.getId());
				personUser.setUpdatedTime(personUser.getCreatedTime());
//				int num = Integer.parseInt(StringUtils.defaultIfBlank(
//						request.getParameter("num"), "0"));
//				ArrayList<Integer> list = new ArrayList<Integer>();
//				for (int i = 1; i <= num; i++) {
//					int cityId = Integer.parseInt(StringUtils.defaultIfBlank(
//							request.getParameter("checkbox" + i), "0"));
//					if (cityId == 0) {
//						continue;
//					}
//					list.add(cityId);
//				}
				//添加用户
				userService.create(personUser);
				this.saveLog(request.getSession(),personUser, "添加用户，用户名："+personUser.getUserName(),curUser.getCityId());
			}
		}catch(Exception e){
			logger.error("保存失败",e);
			result = "保存失败，原因："+e.getMessage();
		}
		if(result.equals("保存成功")){
			return "redirect:/user/userlist.htm";
		}else{
			return result;
		}

	}

	@RequestMapping(value = "/userPasswordSave.do", method = RequestMethod.POST, produces="text/html;charset=UTF-8")
	@ResponseBody
	public String updatePassword (PersonUser personUser , HttpServletRequest request) {
		String result = "保存成功";

		try{
			PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			PersonUser dto = userService.findById(personUser.getId());
			dto.setPassWord(EncryptHelper.md5(personUser.getPassWord()));
			dto.setUpdatedUserid(curUser.getId());
			dto.setUpdatedTime(new Date());
			userService.update(dto);
			this.saveLog(request.getSession(),dto, "修改密码，用户名："+dto.getUserName(),curUser.getCityId());
		}catch(Exception e){
			logger.error("保存密码失败",e);
			result = "保存失败，原因："+e.getMessage();
		}

		return result;
	}

	@RequestMapping(value="userDelete.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String delete(int id,HttpServletRequest request) {

		String result = "删除成功";

		try{
			PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			PersonUser personUser = userService.findById(id);
			if(personUser!=null){
				personUser.setUpdatedUserid(curUser.getId());
				personUser.setUpdatedTime(new Date());
				userService.delete(personUser);
				this.saveLog(request.getSession(),personUser, "删除用户，用户名:"+personUser.getUserName(),curUser.getCityId());
			}
		}catch(Exception e){
           logger.error("删除失败",e);
           result = "删除失败,原因："+e.getMessage();
		}

		return result;
	}

	@RequestMapping(value="setAdmin.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String setUserAdmin(int id,HttpServletRequest request) {

		String result = "设置成功";
		try{
			PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			PersonUser personUser = userService.findById(id);
			if(personUser!=null){
				personUser.setUpdatedUserid(curUser.getId());
				personUser.setUpdatedTime(new Date());
				userService.setAdmin(personUser);
				this.saveLog(request.getSession(),personUser, "设置管理员，用户名："+personUser.getUserName(),curUser.getCityId());
			}
		}catch(Exception e){
			logger.error("设置失败",e);
			result = "设置失败，原因："+e.getMessage();
		}
		return result;
	}

	@RequestMapping(value="unSetAdmin.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String unSetUserAdmin(int id,HttpServletRequest request) {

		String result = "设置成功";
		try{
			PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
			PersonUser personUser = userService.findById(id);
			if(personUser!=null){
				personUser.setUpdatedUserid(curUser.getId());
				personUser.setUpdatedTime(new Date());
				userService.unSetAdmin(personUser);
				this.saveLog(request.getSession(),personUser, "取消设置管理员，用户名："+personUser.getUserName(),curUser.getCityId());
			}
		}catch(Exception e){
			logger.error("取消设置失败",e);
			result = "取消设置失败，原因："+e.getMessage();
		}
		return result;
	}

	@RequestMapping(value = "/SendSms.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView SendSmsView(HttpServletRequest request) {



		ModelAndView mv = new ModelAndView("user/sendSms");

		float balance=userService.getSmsBalance();
		mv.addObject("balance", balance);
		TestController.getMenuPoint(mv, request);

		return mv;
	}

	@RequestMapping(value="sendSms.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String sendSms(HttpServletRequest request) {
		PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
		String message=request.getParameter("content");
		String result = userService.sendSms(message);

		this.saveLog(request.getSession(),null, "短信群发："+message,curUser.getCityId());

		return result;
	}

	@RequestMapping(value="changeDefaultCity.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
	@ResponseBody
	public String changeDefaultCity(HttpServletRequest request,@RequestParam("userId")Integer userId,@RequestParam("cityId")Integer cityId) {
		try {
			PersonUser personUser = this.userService.findById(userId);
			personUser.setCityId(cityId);
			this.userService.update(personUser);
			List<Privilege> privileges = this.userService.findPrivileges(personUser.getId());
			List<City> citys = this.userCityService.findCityByUserId(personUser.getId());
			City city = this.cityService.findById(personUser.getCityId());
			personUser.setPrivileges(privileges);
			personUser.setCitys(citys);
			personUser.setCityName(city.getCityName());
			SessionHelper.setUser(request.getSession(),personUser);
			this.saveLog(request.getSession(),personUser, "切换城市",personUser.getDingCityId());
			return "200";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "201";
	}


	@RequestMapping(value = "/checkPhoneNum.json")
	public @ResponseBody String checkPhoneNum(@ModelAttribute("user") PersonUser personUser,String mobilePhone, HttpServletRequest request) {

		if(StringUtils.isNotBlank(mobilePhone)){
			personUser.setMobilePhone(mobilePhone);
		}

		String oldMobilePhone = request.getParameter("oldMobilePhone");

		if(mobilePhone.equals(oldMobilePhone)){
			return "success";
		}

		if(!isPhoneExist(personUser)){
			return "success";
		}

		return "error";
	}

	private boolean isPhoneExist(PersonUser personUser){

		List<PersonUser> personUsers=userService.findUsersByCondition(personUser);

		if(CollectionUtils.isNotEmpty(personUsers)){
			return true;
		}

		return false;
	}

	@RequestMapping(value = "/queryUser.json")
	@ResponseBody
	public  Map<String, Object> getUser(HttpServletRequest request) {
		Map<String, Object> resultMap = Maps.newHashMap();
		PersonUser personUser = new PersonUser();
		personUser.setMobilePhone(request.getParameter("mobilePhone"));
		personUser.setCompanyName(request.getParameter("companyName"));
		List<PersonUser> personUsers = userService.findUsersByCondition(personUser);
		if (CollectionUtils.isNotEmpty(personUsers)) {

		     resultMap.put("user", personUsers.get(0));

		}
		return resultMap;
	}

	@RequestMapping(value = "/queryComboUser.json")
	@ResponseBody
	public  List<PersonUser> getUser4ComboQuery(HttpServletRequest request) {

		PersonUser personUser = new PersonUser();
		personUser.setMobilePhone(request.getParameter("mobilePhone"));
		personUser.setUserName(request.getParameter("userName"));
		List<PersonUser> personUsers = userService.findUsersByCondition(personUser);

		return personUsers;
	}
	
	@RequestMapping(value = "/setManager.htm", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView setManager(HttpServletRequest request,@RequestParam("userid")Integer userid) {
		ModelAndView mv = new ModelAndView("user/managecode");
		TestController.getMenuPoint(mv, request);
		Token token = this.tokenService.findByid(-1);
		String timestamp = OrderUtil.GetTimestamp();
		String stringMD5 = MD5Util.getStringMD5(timestamp);
		if(null==token){
			Token token2 = new Token();
			token2.setShopId(-1);
			token2.setTime(new Date());
			token2.setToken(stringMD5);
			this.tokenService.create(token2);
		}else{
			token.setToken(stringMD5);
			token.setTime(new Date());
			this.tokenService.update(token);
		}
		mv.addObject("stringMD5", stringMD5);
		mv.addObject("userid", userid);
		return mv;
	}
	
	//添加管理员二维码
	@RequestMapping(value="/manageQRCode.htm")
	public void manageerweima(HttpServletResponse response,@RequestParam("userid")String userid,@RequestParam("token")String token){
		String content = properties.ONLINE_URL+"user/wxsetManager.do?userid="+userid+"&token="+token;
		 try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content,
					BarcodeFormat.QR_CODE, 500, 500);
			MatrixToImageWriter.writeToStream(bitMatrix, "png",
					response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//管理员添加二维码扫码后
		@RequestMapping(value="/wxsetManager.do")
		public ModelAndView addManagedo(HttpServletRequest request,RedirectAttributes attr,@RequestParam("userid")String userid,@RequestParam("token")String token){
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			String state = userid+","+token;
			if(null!=user){
				attr.addAttribute("state", state);
				return new ModelAndView("redirect:/user/wxaddmanage.htm"); 
			}
			String appid = properties.weixinAppid;
			String redirect_uri = properties.ONLINE_URL+"user/wxaddManageOauth.htm";
			String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
			String requestUrl = url.replace("APPID", appid).replace("REDIRECT_URL", urlEncode).replace("STATE", state);
			return new ModelAndView("redirect:"+requestUrl+"");
		}
	
		@RequestMapping("/wxaddManageOauth.htm")
		public ModelAndView addManageOauth(HttpServletRequest request,RedirectAttributes attr){
			String code = request.getParameter("code");
			String state = request.getParameter("state");
			String openId ="";
			String token = "";
			WXUser info = null;
			if (!"authdeny".equals(code)) {
				WeiXinOauth2Token weiXinOauth2Token = AdvancedUtil
						.getOauth2AccessToken(properties.weixinAppid,
								properties.weixinAppsecret, code);
				openId = weiXinOauth2Token.getOpenId();
				token = weiXinOauth2Token.getAccessToken();
			}
			ShopUser wxUser = this.shopUserService.findByOpenId(openId);
			if(null==wxUser){
				info = AdvancedUtil.getWeixinUserInfo(token, openId);
				wxUser = new ShopUser();
				wxUser.setOpenid(openId);
				wxUser.setCity(info.getCity());
				wxUser.setCountry(info.getCountry());
				wxUser.setHeadImgurl(info.getHeadImgurl());
				wxUser.setNickName(info.getNickName());
				wxUser.setProvince(info.getProvince());
				wxUser.setSex(info.getSex());
				wxUser.setIsadmin(0);
				wxUser.setCreated(new Date());
				this.shopUserService.create(wxUser);
				WXSessionHelper.setShopUser(request.getSession(), wxUser);
			}else{
				WXSessionHelper.setShopUser(request.getSession(), wxUser);
			}
			attr.addAttribute("state", state);
			return new ModelAndView("redirect:/user/wxaddmanage.htm"); 
		}
		
		@RequestMapping(value="wxaddmanage.htm")
		public ModelAndView shop_addmanage(HttpServletRequest request){
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			if(null!=user){
				ModelAndView view = new ModelAndView("user/success");
				TestController.getMenuPoint(view, request);
				String state = request.getParameter("state");
				String[] split = state.split(",");
				String userid = split[0];
				Integer user_id = Integer.parseInt(userid);
				
				String str_token = split[1];
				PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
				if(personUser!=null){
					//超级管理员 提示已经是管理员
					view.addObject("sign", 1);
				}else{
						//不是该店铺管理员，设置为该店铺管理员
						Token token = this.tokenService.findByIdAndTime(-1);
						if(null!=token && str_token.equals(token.getToken())){
							//设置为管理员
							this.userService.updateUserToManager(user,user_id);
							view.addObject("sign", 3);
						}else{
							//已过期
							view.addObject("sign", 2);
						}
					
				}
				return view;
			}else{
				return new ModelAndView("redirect:/user/wxsetManager.do");
			}
		}
		
		@RequestMapping(value="/cancalManager.do")
		@ResponseBody
		public String cancalManagedo(HttpServletRequest request,RedirectAttributes attr,@RequestParam("userid")Integer userid){
			try {
				PersonUser curUser=(PersonUser) request.getSession().getAttribute(Constant.USER_KEY);
				PersonUser user = this.userService.findById(userid);
				user.setOpenid(null);
				this.userService.cancalManager(user);
				this.saveLog(request.getSession(), user, "解绑微信",curUser.getCityId());
				return "200";
			} catch (Exception e) {
				logger.error("解绑微信失败",e);
			}
			return "201";
		}

	private void saveLog(HttpSession session,PersonUser dto,String content,Integer cityId){
		try{
	       SysLog sysLog = new SysLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(SessionHelper.getUserId(session));
	       sysLog.setCityId(cityId);
	       sysLog.setDataType(LogDataTypeEnum.USER.getName());
	       sysLog.setDataId(dto.getId().toString());

	       if(dto!=null){
		       String dataContent = new Gson().toJson(dto,
						new TypeToken<PersonUser>() {
						}.getType());

		       sysLog.setDataContent(dataContent);
	       }

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
}
