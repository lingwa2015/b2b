package com.b2b.web.shop.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.b2b.Constant;
import com.b2b.common.domain.*;
import com.b2b.common.domain.Token;
import com.b2b.common.util.ClientCustomSSL;
import com.b2b.common.util.DateUtil;
import com.b2b.common.util.OrderNumberGenerator;
import com.b2b.common.util.RequestHandler;
import com.b2b.enums.ItemSizeEnum;
import com.b2b.enums.LogDataTypeEnum;
import com.b2b.enums.OrderStatusEnum;
import com.b2b.service.*;
import com.b2b.web.util.ApiService;
import com.b2b.web.util.LWResult;
import com.b2b.web.util.MD5Util;
import com.b2b.web.util.SessionHelper;
import com.b2b.web.wx.util.*;
import com.b2b.web.wx.util.Properties;
import com.b2b.web.wx.util.pay.DOM4JParser;
import com.b2b.web.wx.util.pay.OrderUtil;
import com.b2b.web.wx.util.pay.WXPayUtil;
import com.b2b.web.wx.util.pay.WXPrepay;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import net.sf.json.JSONObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.time.DateUtils;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.*;



@RequestMapping("convenient")
@Controller
public class ConvenientShopController {
	private static final Logger logger = LoggerFactory.getLogger(ConvenientShopController.class);
	private static String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URL&response_type=code&scope=snsapi_userinfo"
			+ "&state=STATE#wechat_redirect";
	private static String quieturl = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=REDIRECT_URL&response_type=code&scope=snsapi_base"
			+ "&state=STATE#wechat_redirect";
	private static String aliurl = "https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=APPID&scope=auth_userinfo&redirect_uri=ENCODED_URL&state=STATE";
	private static final ObjectMapper MAPPER = new ObjectMapper();
	@Autowired
	private Properties properties;
	
	@Autowired
	private ShopUserService shopUserService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private RefundService refundService;
	
	@Autowired
	private ShopItemService shopItemService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ShopOrderService shopOrderService;
	
	@Autowired
	private TranConsumeService tranConsumeService;
	
	@Autowired
	private TranSumService tranSumService;
	
	@Autowired
	UserCityService userCityService;
	
	@Autowired
	private WXPayUtil wxPayUtil;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private SeniorSetService seniorSetService;
	
	@Autowired
	private NotifyStateService notifyStateService;
	
	@Autowired
	AccessTokenService accessTokenService;
	
	@Autowired
	ConsumeRankService consumeRankService;
	
	@Autowired
	SysShopLogService logService;
	
	@Autowired
	ShopAdminService shopAdminService;
	
	@Autowired
	ItemService itemService;
	
	@Autowired
	ItemCategoryService itemCategoryService;
	
	@Autowired
	PersonRegionService personRegionService;
	
	@Autowired
	UserService userService;
	
	@Autowired
	AfterSalesRecordService afterSalesRecordService;
	
	@Autowired
	ShopOrderItemService shopOrderItemService;
	
	@Autowired
	ShopItemStockService shopItemStockService;
	
	@Autowired
	CustomerCameraService customerCameraService;
	
	@Autowired
	ApiService apiService;
	
	@Autowired
	BeRefundItemService beRefundItemService;
	
	@Autowired
	ShopDailyReportService shopDailyReportService;
	
    @Autowired
    FreeDayReportService freeDayReportService;
    
    @Autowired
    ShopMonthReportService shopMonthReportService;
    
    @Autowired
    FreeShopMonthReportService freeShopMonthReportService;
    
    @Autowired
	ReseauService reseauService;
    
    @Autowired
    DebitNoteService debitNoteService;
    
    @Autowired
    MemberPointService memberPointService;
    
    @Autowired
    MemberPointReportService memberPointReportService;
    
    @Autowired
    FreeShopDailyReportService freeShopDailyReportService;
    
    @Autowired
    ShopSalesService shopSalesService;
    
    @Autowired
    CityService cityService;
    
    @Autowired
    DeliveryReceitpService deliveryReceitpService;
    
    @Autowired
    CityRegionService cityRegionService;
    
    @Autowired
    DataCollectService dataCollectService;
    
    @Autowired
    RedReceiveService redReceiveService;
    
    @Autowired
    RedPacketService redPacketService;
    
    @Autowired
    RedPacketTypeService redPacketTypeService;
    
    @Autowired
    RedShopOrderService redShopOrderService;
    
    @Autowired
	private ShopAliUserService shopAliUserService;
    
    @Autowired
    RedAccountService redAccountService;
    
    @Autowired
    RedPacketCustomerService redPacketCustomerService;

    @Autowired
    ShopLayerService shopLayerService;

    @Autowired
	WholeTokenService wholeTokenService;

	@Autowired
	AccountLockService accountLockService;
    
    @Autowired
    NewCustomerActivityService newCustomerActivityService;
    
    @Autowired
    ItemSalesPromotionService itemSalesPromotionService;

    @Autowired
    ShopDiscountSettingService shopDiscountSettingService;

    @Autowired
    ShopUserSubsidyService shopUserSubsidyService;

	@Autowired
	PurchaseService purchaseService;

	@Autowired
	PurchaseItemService purchaseItemService;

	@Autowired
	StorageService storageService;


	@RequestMapping(value="shop_noPeivilege.htm")
	public ModelAndView noPeivilege(){
		return new ModelAndView("shop/manage/noPrivilege");
	}
	
	//更改授权开始。。。
	//后台管理主页
	@RequestMapping(value="shop_manage.htm")
	public ModelAndView manage(HttpServletRequest request){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		if(null!=user){
			return new ModelAndView("redirect:/convenient/shop_manageIndex.htm"); 
		}
		//静默授权
		String appid = properties.weixinAppid;
		String redirect_uri = properties.ONLINE_URL+"convenient/shop_quietManageOauth.htm";
		String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
		String requestUrl = quieturl.replace("APPID", appid).replace("REDIRECT_URL", urlEncode);
		//显示授权
//		String appid = properties.weixinAppid;
//		String redirect_uri = properties.ONLINE_URL+"convenient/shop_manageOauth.htm";
//		String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
//		String requestUrl = url.replace("APPID", appid).replace("REDIRECT_URL", urlEncode);
		return new ModelAndView("redirect:"+requestUrl+""); 
	}
	
	@RequestMapping("/shop_quietManageOauth.htm")
	public ModelAndView quietManageOauth(HttpServletRequest request,HttpServletResponse response){
		String code = request.getParameter("code");
		String openId ="";
		if (!"authdeny".equals(code)) {
			WeiXinOauth2Token weiXinOauth2Token = AdvancedUtil
					.getOauth2AccessToken(properties.weixinAppid,
							properties.weixinAppsecret, code);
			openId = weiXinOauth2Token.getOpenId();
			String accessToken = weiXinOauth2Token.getAccessToken();
			
			ShopUser wxUser = this.shopUserService.findByOpenId(openId);
			WXUser info = null;
			if(null==wxUser){
				info = AdvancedUtil.getWeixinUserInfo(accessToken, openId);
				if(null!=info && info.getNickName()!=null && info.getHeadImgurl()!=null){
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
					return new ModelAndView("redirect:/convenient/shop_manageIndex.htm"); 
				}
			}else{
				info = AdvancedUtil.getWeixinUserInfo(accessToken, openId);
				if(null!=info && info.getNickName()!=null && info.getHeadImgurl()!=null){
					wxUser.setCity(info.getCity());
					wxUser.setCountry(info.getCountry());
					wxUser.setHeadImgurl(info.getHeadImgurl());
					wxUser.setNickName(info.getNickName());
					wxUser.setProvince(info.getProvince());
					wxUser.setSex(info.getSex());
					this.shopUserService.update(wxUser);
					WXSessionHelper.setShopUser(request.getSession(), wxUser);
					return new ModelAndView("redirect:/convenient/shop_manageIndex.htm"); 
				}
			}
		}
		//显示授权
		String appid = properties.weixinAppid;
		String redirect_uri = properties.ONLINE_URL+"convenient/shop_manageOauth.htm";
		String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
		String requestUrl = url.replace("APPID", appid).replace("REDIRECT_URL", urlEncode);
		return new ModelAndView("redirect:"+requestUrl+""); 
	}
	
	@RequestMapping("/shop_manageOauth.htm")
	public ModelAndView manageOauth(HttpServletRequest request,HttpServletResponse response){
		String code = request.getParameter("code");
		String openId ="";
		String token = "";
		WXUser info = null;
		if (!"authdeny".equals(code)) {
			WeiXinOauth2Token weiXinOauth2Token = AdvancedUtil
					.getOauth2AccessToken(properties.weixinAppid,
							properties.weixinAppsecret, code);
			openId = weiXinOauth2Token.getOpenId();
			token = weiXinOauth2Token.getAccessToken();
			AccessToken token2 = this.accessTokenService.findById(openId);
			if(null==token2){
				token2 = new AccessToken();
				token2.setId(openId);
				token2.setAccessToken(weiXinOauth2Token.getAccessToken());
				token2.setRefreshToken(weiXinOauth2Token.getRefeshToken());
				token2.setUpdatedTime(new Date());
				this.accessTokenService.insert(token2);
			}else{
				token2.setAccessToken(weiXinOauth2Token.getAccessToken());
				token2.setRefreshToken(weiXinOauth2Token.getRefeshToken());
				token2.setUpdatedTime(new Date());
				this.accessTokenService.update(token2);
			}
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
			info = AdvancedUtil.getWeixinUserInfo(token, openId);
			wxUser.setCity(info.getCity());
			wxUser.setCountry(info.getCountry());
			wxUser.setHeadImgurl(info.getHeadImgurl());
			wxUser.setNickName(info.getNickName());
			wxUser.setProvince(info.getProvince());
			wxUser.setSex(info.getSex());
			this.shopUserService.update(wxUser);
			WXSessionHelper.setShopUser(request.getSession(), wxUser);
		}
		return new ModelAndView("redirect:/convenient/shop_manageIndex.htm"); 
	}
	
	public boolean isadmin(Integer id){
		boolean flag = false;
		try {
			List<Role> roles = this.userService.findRolesById(id);
			if(!roles.isEmpty()){
				for (Role role : roles) {
					if(role.getRoleName().equals("管理员")||role.getRoleName().equals("超级管理员")){
						flag = true;
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	@RequestMapping(value="shop_manageIndex.htm")
	public ModelAndView manageIndex(HttpServletRequest request,RedirectAttributes attr){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/manage/shoplist");
		if(null!=user){
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			if(null!=puser){
				//超级管理员,返回店铺列表
//				PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
//				if(null!=personUser && null!=personUser.getPost() && personUser.getPost().equals("客户经理") && null!=personUser.getReseauId()){
//					view.addObject("reseauId",personUser.getReseauId());
//				}
//				Integer cityId = null;
//				if(null!=personUser){
//					cityId = personUser.getCityId();
//				}
//				if(!StringUtils.isEmpty(name) && null !=cityId){
//					List<HashMap<String, Object>> shops = this.customerService.findAllShopByCondition(name,cityId);
//					view.addObject("shops", shops);
//				}
//			    if(null!=name && ""!=name){
//			    	 view.addObject("shopName", name);
//			    }
//			    return view;
				if(isadmin(puser.getId())){
			    	ModelAndView view1 = new ModelAndView("shop/manage/admin");
			    	return view1;
			    }
				
			    if(null!=puser.getGread() && puser.getGread().equals(3) && null!=puser.getPost() && puser.getPost().equals("销售")){
			    	return new ModelAndView("redirect:/convenient/manage/shop_jlsaleman.htm");
			    }
			    if(null!=puser.getGread() && puser.getGread().equals(3) && null!=puser.getPost() && puser.getPost().equals("客户经理")){
			    	return new ModelAndView("redirect:/convenient/manage/shop_jlclientmanager.htm");
			    }
			    
			    if(null!=puser.getGread() && puser.getGread().equals(2) && null!=puser.getPost() && puser.getPost().equals("销售")){
			    	return new ModelAndView("redirect:/convenient/manage/shop_jlsaleman.htm");
			    }
			    if(null!=puser.getGread() && puser.getGread().equals(4) && null!=puser.getPost() && puser.getPost().equals("销售")){
			    	return new ModelAndView("redirect:/convenient/manage/shop_jlsaleman.htm");
			    }
			    
			    if(null!=puser.getGread() && puser.getGread().equals(2) && null!=puser.getPost() && puser.getPost().equals("客户经理") || 4==puser.getGread() && "客户经理".equals(puser.getPost())){
			    	return new ModelAndView("redirect:/convenient/manage/shop_jlclientmanager.htm");
			    }
			    
                if(null!=puser.getGread() && puser.getGread().equals(1) && null!=puser.getPost() && puser.getPost().equals("客户经理") && null!= puser.getReseauId()){
                	ModelAndView view1 = new ModelAndView("shop/manage/yg_clientmanager");
//                	ShopOrder order = this.shopOrderService.findTodayAllConsumeMoneyByReseauId(puser.getReseauId());
//                	view1.addObject("actual", order);
                	view1.addObject("reseauId", puser.getReseauId());
                	view1.addObject("userName", puser.getUserName());
			    	return view1;
			    }
                
			    
                if(null!=puser.getGread() && puser.getGread().equals(1) && null!=puser.getPost() && puser.getPost().equals("销售")){
                	attr.addAttribute("saleId", puser.getId());
                	return new ModelAndView("redirect:/convenient/manage/shop_ygsaleman.htm");
			    }
                if( "客服".equals(puser.getPost()) || "配送".equals(puser.getPost()) || "客户经理".equals(puser.getPost())){
                	//302
                	return new ModelAndView("redirect:/convenient/manage/shop_lists.htm"); 
			    }
				if( "仓管".equals(puser.getPost())){
					return new ModelAndView("redirect:/convenient/manage/shop_select.htm");
				}
                return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
			}else{
				List<ShopAdmin> lists = this.shopAdminService.findByAdminId(user.getId());
				if(!lists.isEmpty()){
					if(lists.size()==1){
						ModelAndView mv = new ModelAndView("shop/manage/index");
						//管理员
						CustomerUser customerUser = this.customerService.findById(lists.get(0).getShopId());
						//最近采购
						Long module1 = this.orderService.findShopOrderByCompanyId(lists.get(0).getShopId());
						//今日消费
						HashMap<String, Object> module2 = this.shopOrderService.findTodayConsumeMoney(lists.get(0).getShopId());
						Long module2_money = Long.valueOf(module2.get("totalFee").toString());
						//在售商品
						HashMap<String, Object> module3 = this.shopItemService.findTolalMoneyAndKind(lists.get(0).getShopId());
						Long module3_money = Long.valueOf(module3.get("money").toString());
						//本月采购
						//Long module4 = this.orderService.findCurrentMonthSourcingMoney(user.getCustomerUserId());
						Long module4 = this.tranSumService.findCurrentMonthSourcingMoney(lists.get(0).getShopId());
						//本月消费
						//Long module5 = this.shopOrderService.findCurrentMonthConsumeMoney(user.getCustomerUserId());
						Long module5 = this.tranConsumeService.findCurrentMonthConsumeMoney(lists.get(0).getShopId());
						Date time = this.tranConsumeService.findDate();
						Long module6 = 0l;
						Long red_price = this.tranConsumeService.findCurrentMonthRedPriceByShopId(lists.get(0).getShopId());
						Long module4_ = 0l;
						Long module5_ = 0l;
						Long red_price_ = 0l;
						if(null!=module4){
							module4_ = module4;
						}
						if(null!=module5){
							module5_ = module5;
						}
						if(null!=red_price){
							red_price_ = red_price;
						}
						if(2==customerUser.getPayBillWay()){
								module6 = module4_-module5_-red_price_;
						}else{
							String month = DateUtil.formatDate(new Date(), "yyyy-MM");
							ShopOrder data2 = this.shopOrderService.findMonthRecordByShopIdAndDate(lists.get(0).getShopId(),month);
							if(null!=data2){
								module6 = data2.getLoss();
							}
						}
						
						SeniorSet seniorSet = this.seniorSetService.findById(lists.get(0).getShopId());
						Long money = this.tranConsumeService.findLossMoney(lists.get(0).getShopId());
						if(null==money){
							money = 0l;
						}
						if(null!=seniorSet && seniorSet.getMoney()!=null && seniorSet.getMoney()<=money){
							mv.addObject("red", 1);
						}else if (null!=seniorSet&&null!=seniorSet.getDiscount() && null !=seniorSet.getStart()) {
							int c = seniorSet.getStart();
							int d = 6;
							Date date2 = new Date();
							int hours = date2.getHours();
							if(c<=hours && hours<24 || 0<=hours && hours<6){
								mv.addObject("red", 2);
							}
						}
						mv.addObject("shopName", customerUser.getUserName());
						mv.addObject("iswxvip", customerUser.getIswxvip());
						mv.addObject("payway", customerUser.getPayBillWay());
						mv.addObject("shopId", lists.get(0).getShopId());
						mv.addObject("module1", module1);
						mv.addObject("module2_money", module2_money);
						mv.addObject("module2_num", module2.get("totalNum"));
						mv.addObject("module3_money", module3_money);
						mv.addObject("module3_kinds", module3.get("kinds"));
						mv.addObject("module4", module4);
						mv.addObject("module5", module5);
						mv.addObject("module6", module6);
						mv.addObject("module5_red", red_price);
						mv.addObject("time", time);
						return mv;
					}else{
						return new ModelAndView("redirect:/convenient/manage/shop_lists.htm"); 
					}
				}
			}
			//啥都不是
			return new ModelAndView("shop/manage/advice");
			
		}else{
			return new ModelAndView("redirect:/convenient/shop_manage.htm"); 
		}
			
		
	}
	
	//更改授权结束。。。
	
	
	//后台管理主页
	@RequestMapping(value="/manage/shop_index.htm")
	public ModelAndView shopManage(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		if(null!=user){
			ModelAndView view = new ModelAndView("shop/manage/index");
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			if(null!=puser){
				//超级管理员
				CustomerUser customerUser = this.customerService.findById(shopId);
				//最近采购
				Long module1 = this.orderService.findShopOrderByCompanyId(shopId);
				//今日消费
				HashMap<String, Object> module2 = this.shopOrderService.findTodayConsumeMoney(shopId);
				Long module2_money = Long.valueOf(module2.get("totalFee").toString());
				//在售商品
				HashMap<String, Object> module3 = this.shopItemService.findTolalMoneyAndKind(shopId);
				Long module3_money = Long.valueOf(module3.get("money").toString());
				//本月采购
				Long module4 = this.tranSumService.findCurrentMonthSourcingMoney(shopId);
				//本月消费
				Long module5 = this.tranConsumeService.findCurrentMonthConsumeMoney(shopId);
				Date time = this.tranConsumeService.findDate();
				Long module6 = 0l;
				Long red_price = this.tranConsumeService.findCurrentMonthRedPriceByShopId(shopId);
				Long module4_ = 0l;
				Long module5_ = 0l;
				Long red_price_ = 0l;
				if(null!=module4){
					module4_ = module4;
				}
				if(null!=module5){
					module5_ = module5;
				}
				if(null!=red_price){
					red_price_ = red_price;
				}
				if(2==customerUser.getPayBillWay()){
						module6 = module4_-module5_-red_price_;
				}else{
					String month = DateUtil.formatDate(new Date(), "yyyy-MM");
					ShopOrder data2 = this.shopOrderService.findMonthRecordByShopIdAndDate(shopId,month);
					if(null!=data2){
						module6 = data2.getLoss();
					}
				}
				SeniorSet seniorSet = this.seniorSetService.findById(shopId);
				Long money = this.tranConsumeService.findLossMoney(shopId);
				if(null==money){
					money = 0l;
				}
				if(null!=seniorSet && seniorSet.getMoney()!=null && seniorSet.getMoney()<=money){
					view.addObject("red", 1);
				}else if (null!=seniorSet&&null!=seniorSet.getDiscount() && null !=seniorSet.getStart()) {
					int c = seniorSet.getStart();
					int d = 6;
					Date date2 = new Date();
					int hours = date2.getHours();
					if(c<=hours && hours<24 || 0<=hours && hours<6){
						view.addObject("red", 2);
					}
				}
				view.addObject("shopName", customerUser.getUserName());
				view.addObject("iswxvip", customerUser.getIswxvip());
				view.addObject("payway", customerUser.getPayBillWay());
				view.addObject("shopId", shopId);
				view.addObject("module1", module1);
				view.addObject("module2_money", module2_money);
				view.addObject("module2_num", module2.get("totalNum"));
				view.addObject("module3_money", module3_money);
				view.addObject("module3_kinds", module3.get("kinds"));
				view.addObject("module4", module4);
				view.addObject("module5", module5);
				view.addObject("module5_red", red_price);
				view.addObject("module6", module6);
				view.addObject("time", time);
				return view;
			}else{
				ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
				if(null!=admin){
					//管理员
					CustomerUser customerUser = this.customerService.findById(shopId);
					//最近采购
					Long module1 = this.orderService.findShopOrderByCompanyId(shopId);
					//今日消费
					HashMap<String, Object> module2 = this.shopOrderService.findTodayConsumeMoney(shopId);
					Long module2_money = Long.valueOf(module2.get("totalFee").toString());
					//在售商品
					HashMap<String, Object> module3 = this.shopItemService.findTolalMoneyAndKind(shopId);
					Long module3_money = Long.valueOf(module3.get("money").toString());
					//本月采购
					//Long module4 = this.orderService.findCurrentMonthSourcingMoney(user.getCustomerUserId());
					Long module4 = this.tranSumService.findCurrentMonthSourcingMoney(shopId);
					//本月消费
					//Long module5 = this.shopOrderService.findCurrentMonthConsumeMoney(user.getCustomerUserId());
					Long module5 = this.tranConsumeService.findCurrentMonthConsumeMoney(shopId);
					Date time = this.tranConsumeService.findDate();
					Long module6 = 0l;
					Long red_price = this.tranConsumeService.findCurrentMonthRedPriceByShopId(shopId);
					Long module4_ = 0l;
					Long module5_ = 0l;
					Long red_price_ = 0l;
					if(null!=module4){
						module4_ = module4;
					}
					if(null!=module5){
						module5_ = module5;
					}
					if(null!=red_price){
						red_price_ = red_price;
					}
					if(2==customerUser.getPayBillWay()){
							module6 = module4_-module5_-red_price_;
					}else{
						String month = DateUtil.formatDate(new Date(), "yyyy-MM");
						ShopOrder data2 = this.shopOrderService.findMonthRecordByShopIdAndDate(shopId,month);
						if(null!=data2){
							module6 = data2.getLoss();
						}
					}
					SeniorSet seniorSet = this.seniorSetService.findById(shopId);
					Long money = this.tranConsumeService.findLossMoney(shopId);
					if(null==money){
						money = 0l;
					}
					if(null!=seniorSet && seniorSet.getMoney()!=null && seniorSet.getMoney()<=money){
						view.addObject("red", 1);
					}else if (null!=seniorSet&&null!=seniorSet.getDiscount() && null !=seniorSet.getStart()) {
						int c = seniorSet.getStart();
						int d = 6;
						Date date2 = new Date();
						int hours = date2.getHours();
						if(c<=hours && hours<24 || 0<=hours && hours<6){
							view.addObject("red", 2);
						}
					}else if (null!=seniorSet&&null!=seniorSet.getFreeFee() && null !=seniorSet.getStart()) {
						int c = seniorSet.getStart();
						int d = 6;
						Date date2 = new Date();
						int hours = date2.getHours();
						if(c<=hours && hours<24 || 0<=hours && hours<6){
							view.addObject("red", 3);
						}
					}
					view.addObject("shopName", customerUser.getUserName());
					view.addObject("iswxvip", customerUser.getIswxvip());
					view.addObject("payway", customerUser.getPayBillWay());
					view.addObject("shopId", shopId);
					view.addObject("module1", module1);
					view.addObject("module2_money", module2_money);
					view.addObject("module2_num", module2.get("totalNum"));
					view.addObject("module3_money", module3_money);
					view.addObject("module3_kinds", module3.get("kinds"));
					view.addObject("module4", module4);
					view.addObject("module5", module5);
					view.addObject("module5_red", red_price);
					view.addObject("module6", module6);
					view.addObject("time", time);
					return view;
				}
			}
		}	
		return new ModelAndView("shop/manage/advice");
	}
	
	//添加店铺
//	@RequestMapping(value="manage/shop_add.htm")
//	public ModelAndView addshop(){
//		return new ModelAndView("shop/manage/add");
//	}
	
//	@RequestMapping(value="manage/shop_add.do")
//	@ResponseBody
//	public String doaddshop(HttpServletRequest request,CustomerUser shop){
//		try {
//			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
//			if (user.getIsadmin().equals(2)) {
//				shop.setDiscount(new BigDecimal(1));
//				shop.setIswxvip(3);
//				shop.setStatus(1);
//				shop.setCreatedUserid(user.getId());
//				shop.setCreatedTime(new Date());
//				shop.setIsadmin(0);
//				shop.setManagershopid(0);
//				shop.setBusinessId(0);
//				this.customerService.create(shop);
//				this.saveLog(request.getSession(), shop, "添加便利店用户："+shop.getCompanyName(),shop.getId(),user.getNickName());
//				return "200";
//			}
//		} catch (Exception e) {
//			logger.error(e.getMessage());
//		}
//		return "201";
//	}
	
	//后台采购菜单
	@RequestMapping(value="shop_sourcingIndex.htm")
	public ModelAndView sourcingIndex(@RequestParam("shop_id")Integer shopId){
		ModelAndView view = new ModelAndView("shop/sourcing/index");
		CustomerUser customerUser = this.customerService.findById(shopId);
		view.addObject("shopId", shopId);
		view.addObject("cityId", customerUser.getCityId());
		return view;
	}
	
	//采购订单  需要拦截器
	@RequestMapping(value="shop_sourcingOrderList.htm")
	public ModelAndView sourcingOrderList(@RequestParam("shop_id")Integer shopId){
		ModelAndView view = new ModelAndView("shop/sourcing/list");
		List<Order> orders = this.orderService.findShopOrderListByCompanyId(shopId);
		view.addObject("orderlist", orders);
		return view;
	}
	
	//采购订单详情
	@RequestMapping("shop_sourcingOrderDetail.htm")
	public ModelAndView sourcingOrderDetail(HttpServletRequest request,@RequestParam("id")String id){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/sourcing/detail");
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		Order order = this.orderService.findOrderById(id);
		SeniorSet seniorSet = this.seniorSetService.findById(order.getUserId());
		if(null!=seniorSet && null!=seniorSet.getIslayer() && 1==seniorSet.getIslayer()){
			view.setViewName("shop/sourcing/layerdetail");
			List<ShopLayer> shopLayers = this.shopLayerService.findByShopIdAndStatus(order.getUserId());
			view.addObject("shopLayers", shopLayers);
		}
		if(null!=puser){
			//超级管理员
			List<HashMap<String, Object>> list = this.orderService.findItemInfoByOrderNo(id);
			CustomerUser customerUser = this.customerService.findById(order.getUserId());
			
			view.addObject("itemlist", list);
			view.addObject("order", order);
			view.addObject("user", customerUser);
			
			return view;
		}else{
			//管理员
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), order.getUserId());
			if(null!=admin){
				List<HashMap<String, Object>> list = this.orderService.findItemInfoByOrderNo(id);
				CustomerUser customerUser = this.customerService.findById(order.getUserId());
				view.addObject("itemlist", list);
				view.addObject("order", order);
				view.addObject("user", customerUser);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
		
	}
	
	/**
	 * 采购订单完成无需上架，需要拦截器
	 */
	@RequestMapping(value="shop_complete.do",method=RequestMethod.POST)
	@ResponseBody
	public String oncomplete(HttpServletRequest request,@RequestParam("id")String id){
		try {
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			Order order = this.orderService.findOrderById(id);
			CustomerUser cuser = this.customerService.findById(order.getUserId());
			Integer cityId = null;
			if(null !=cuser){
				cityId = cuser.getCityId();
			}
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			if(null!=puser){
				//超级管理员
				if(order.getComfirm()==2 ||order.getComfirm()==1){
					order.setComfirm(3);
					order.setFlag(0);
					order.setDeliverDate(new Date());
					PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
					if(null!=personUser){
						order.setPeisong(personUser.getUserName());
					}
					this.orderService.updateOrderAndCustomer(order);
					this.saveLog(request.getSession(), order, "更改便利店采购订单状态不上架："+order.getOrderNo(),order.getUserId(),user.getNickName(),cityId);
					return "200";
				}
				return "202";
			}else{
				ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), order.getUserId());
				if(null!=admin){
					if(order.getComfirm()==2 ||order.getComfirm()==1){
						order.setComfirm(3);
						order.setFlag(0);
						order.setDeliverDate(new Date());
						this.orderService.updateOrderAndCustomer(order);
						this.saveLog(request.getSession(), order, "更改便利店采购订单状态不上架："+order.getOrderNo(),order.getUserId(),user.getNickName(),cityId);
						return "200";
					}
					return "202";
				}
			}
		} catch (Exception e) {
			logger.error("采购订单完成操作异常"+e.getMessage());
		}
		return "201";
	}
	
	//上架
	@RequestMapping("shop_batchOnShelf.do")
	@ResponseBody
	public String batchOnShelf(HttpServletRequest request,@RequestParam("id")String id){
		try {
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			Order order = this.orderService.findOrderById(id);
			CustomerUser cuser = this.customerService.findById(order.getUserId());
			Integer cityId = null;
			if(null !=cuser){
				cityId = cuser.getCityId();
			}
			String data = request.getParameter("data");
			Map map = null;
			if(!StringUtils.isEmpty(data)){
				JSONObject jsonObject = JSONObject.fromObject(data);
				map = (Map) jsonObject;
			}
			
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			if(null!=puser){
				//超级管理员
				Date executedTime = order.getExecutedTime();
				String exe = DateUtil.formatDate(executedTime, "yyyy-MM");
				Date date = new Date();
				String now = DateUtil.formatDate(date, "yyyy-MM");
				if(!exe.equals(now)){
					return "204";
				}
				if (executedTime.after(date)) {
					order.setExecutedTime(date);
				}
				if(order.getComfirm()==2){
					List<HashMap<String, Object>> list = this.orderService.findItemInfoByOrderNo(id);
					CustomerUser customerUser = this.customerService.findById(order.getUserId());
					BigDecimal shopDiscount = customerUser.getShopDiscount();
					Double discount = Double.valueOf(shopDiscount.toString());
					for (HashMap<String, Object> item : list) {
						//查店铺中该商品是否存在
						ShopItem shopitem = this.shopItemService.findItemByItemIdAndShopId(Integer.valueOf(item.get("item_id").toString()),order.getUserId());
						if(null==shopitem){
							//商品库里不存在，新增
							ShopItem shopItem2 = new ShopItem();
							shopItem2.setImgPath(item.get("img_path").toString());
							shopItem2.setItemId(Integer.valueOf(item.get("item_id").toString()));
							shopItem2.setIsdown(1);
							shopItem2.setMarketPrice(Long.valueOf(item.get("market_price").toString()));
							shopItem2.setName(item.get("item_name").toString());
							shopItem2.setNum(Integer.valueOf(item.get("num").toString()));
							//二期修改
							Long item_price = Long.valueOf(item.get("item_price").toString());
							if(discount==1){
								
							}else{
								BigDecimal val = new BigDecimal(item_price*discount);
								val = val.divide(new BigDecimal(100));
								val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
								val = val.multiply(new BigDecimal(100));
								item_price = val.longValue();
							}
							shopItem2.setSalePrice(item_price);
							shopItem2.setShopId(order.getUserId());
							shopItem2.setSize(item.get("item_size").toString());
							shopItem2.setSourcingPrice(Long.valueOf(item.get("item_price").toString()));
							shopItem2.setStatus(1);
							shopItem2.setUpdatedTime(new Date());
							shopItem2.setOnshelfTime(new Date());
							if(map!=null){
								Integer layerId = Integer.valueOf(map.get(item.get("item_id").toString()).toString());
								shopItem2.setLayerId(layerId);								
							}
							this.shopItemService.create(shopItem2);
							this.saveLog(request.getSession(), shopItem2, "*"+order.getOrderNo()+"上架，创建商品："+shopItem2.getName()+"数量："+shopItem2.getNum(), order.getUserId(), user.getNickName(),cityId);
						}else{
							//存在，增加数量，isdown状态为上架状态
							if(shopitem.getIsdown()==0){
								if(shopitem.getSourcingPrice()!=Long.valueOf(item.get("item_price").toString())){
									Long item_price = Long.valueOf(item.get("item_price").toString());
									if(discount==1){
										
									}else{
										BigDecimal val = new BigDecimal(item_price*discount);
										val = val.divide(new BigDecimal(100));
										val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
										val = val.multiply(new BigDecimal(100));
										item_price = val.longValue();
									}
									shopitem.setSalePrice(item_price);
								}
								shopitem.setIsdown(1);
								shopitem.setNum(Integer.valueOf(item.get("num").toString()));
								shopitem.setName(item.get("item_name").toString());
								shopitem.setMarketPrice(Long.valueOf(item.get("market_price").toString()));
								shopitem.setSourcingPrice(Long.valueOf(item.get("item_price").toString()));
								shopitem.setImgPath(item.get("img_path").toString());
								shopitem.setSize(item.get("item_size").toString());
								shopitem.setUpdatedTime(new Date());
								shopitem.setOnshelfTime(new Date());
								if(map!=null){
									Integer layerId = Integer.valueOf(map.get(item.get("item_id").toString()).toString());
									shopitem.setLayerId(layerId);								
								}
							}else{
								if(shopitem.getSourcingPrice()!=Long.valueOf(item.get("item_price").toString())){
									Long item_price = Long.valueOf(item.get("item_price").toString());
									if(discount==1){
										
									}else{
										BigDecimal val = new BigDecimal(item_price*discount);
										val = val.divide(new BigDecimal(100));
										val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
										val = val.multiply(new BigDecimal(100));
										item_price = val.longValue();
									}
									shopitem.setSalePrice(item_price);
								}
								shopitem.setIsdown(1);
								shopitem.setNum(Integer.valueOf(item.get("num").toString())+shopitem.getNum());
								shopitem.setName(item.get("item_name").toString());
								shopitem.setMarketPrice(Long.valueOf(item.get("market_price").toString()));
								shopitem.setSourcingPrice(Long.valueOf(item.get("item_price").toString()));
								shopitem.setImgPath(item.get("img_path").toString());
								shopitem.setSize(item.get("item_size").toString());
								shopitem.setOnshelfTime(new Date());
								if(map!=null){
									Integer layerId = Integer.valueOf(map.get(item.get("item_id").toString()).toString());
									shopitem.setLayerId(layerId);								
								}
							}
							this.shopItemService.update(shopitem);
							this.saveLog(request.getSession(), shopitem, "*"+order.getOrderNo()+"上架，增加商品："+shopitem.getName()+"数量："+item.get("num"), order.getUserId(), user.getNickName(),cityId);
						}
					}
					order.setComfirm(3);
					order.setFlag(1);
					order.setDeliverDate(new Date());
					PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
					if(null!=personUser){
						order.setPeisong(personUser.getUserName());
					}
					this.orderService.updateOrderAndCustomer(order);
					this.saveLog(request.getSession(), order, "更改便利店采购订单状态上架："+order.getOrderNo(),order.getUserId(),user.getNickName(),cityId);
					return "200";
				}
				return "202";
			}else{
				//普通管理员
				ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), order.getUserId());
				if(null!=admin){
					Date executedTime = order.getExecutedTime();
					String exe = DateUtil.formatDate(executedTime, "yyyy-MM");
					Date date = new Date();
					String now = DateUtil.formatDate(date, "yyyy-MM");
					if(!exe.equals(now)){
						return "204";
					}
					if (executedTime.after(date)) {
						order.setExecutedTime(date);
					}
					if(order.getComfirm()==2){
						logger.info("上架步骤1");
						List<HashMap<String, Object>> list = this.orderService.findItemInfoByOrderNo(id);
						CustomerUser customerUser = this.customerService.findById(order.getUserId());
						BigDecimal shopDiscount = customerUser.getShopDiscount();
						Double discount = Double.valueOf(shopDiscount.toString());
						logger.info("上架步骤2");
						for (HashMap<String, Object> item : list) {
							//查店铺中该商品是否存在
							logger.info("上架步骤3"+item);
							ShopItem shopitem = this.shopItemService.findItemByItemIdAndShopId(Integer.valueOf(item.get("item_id").toString()),order.getUserId());
							logger.info("上架步骤4"+shopitem);
							if(null==shopitem){
								//商品库里不存在，新增
								ShopItem shopItem2 = new ShopItem();
								shopItem2.setImgPath(item.get("img_path").toString());
								shopItem2.setItemId(Integer.valueOf(item.get("item_id").toString()));
								shopItem2.setIsdown(1);
								shopItem2.setMarketPrice(Long.valueOf(item.get("market_price").toString()));
								shopItem2.setName(item.get("item_name").toString());
								shopItem2.setNum(Integer.valueOf(item.get("num").toString()));
								//二期修改
								Long item_price = Long.valueOf(item.get("item_price").toString());
								if(discount==1){
									
								}else{
									BigDecimal val = new BigDecimal(item_price*discount);
									val = val.divide(new BigDecimal(100));
									val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
									val = val.multiply(new BigDecimal(100));
									item_price = val.longValue();
								}
								shopItem2.setSalePrice(item_price);
								shopItem2.setShopId(order.getUserId());
								shopItem2.setSize(item.get("item_size").toString());
								shopItem2.setSourcingPrice(Long.valueOf(item.get("item_price").toString()));
								shopItem2.setStatus(1);
								shopItem2.setUpdatedTime(new Date());
								shopItem2.setOnshelfTime(new Date());
								if(map!=null){
									Integer layerId = Integer.valueOf(map.get(item.get("item_id").toString()).toString());
									shopItem2.setLayerId(layerId);								
								}
								this.shopItemService.create(shopItem2);
								this.saveLog(request.getSession(), shopItem2, "*"+order.getOrderNo()+"上架，创建商品："+shopItem2.getName()+"数量："+shopItem2.getNum(), order.getUserId(), user.getNickName(),cityId);
							}else{
								//存在，增加数量，isdown状态为上架状态
								if(shopitem.getIsdown()==0){
									if(shopitem.getSourcingPrice()!=Long.valueOf(item.get("item_price").toString())){
										Long item_price = Long.valueOf(item.get("item_price").toString());
										if(discount==1){
											
										}else{
											BigDecimal val = new BigDecimal(item_price*discount);
											val = val.divide(new BigDecimal(100));
											val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
											val = val.multiply(new BigDecimal(100));
											item_price = val.longValue();
										}
										shopitem.setSalePrice(item_price);
									}
									shopitem.setIsdown(1);
									shopitem.setNum(Integer.valueOf(item.get("num").toString()));
									shopitem.setName(item.get("item_name").toString());
									shopitem.setMarketPrice(Long.valueOf(item.get("market_price").toString()));
									shopitem.setSourcingPrice(Long.valueOf(item.get("item_price").toString()));
									shopitem.setImgPath(item.get("img_path").toString());
									shopitem.setSize(item.get("item_size").toString());
									shopitem.setUpdatedTime(new Date());
									shopitem.setOnshelfTime(new Date());
									if(map!=null){
										Integer layerId = Integer.valueOf(map.get(item.get("item_id").toString()).toString());
										shopitem.setLayerId(layerId);								
									}
								}else{
									if(shopitem.getSourcingPrice()!=Long.valueOf(item.get("item_price").toString())){
										Long item_price = Long.valueOf(item.get("item_price").toString());
										if(discount==1){
											
										}else{
											BigDecimal val = new BigDecimal(item_price*discount);
											val = val.divide(new BigDecimal(100));
											val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
											val = val.multiply(new BigDecimal(100));
											item_price = val.longValue();
										}
										shopitem.setSalePrice(item_price);
									}
									shopitem.setIsdown(1);
									shopitem.setNum(Integer.valueOf(item.get("num").toString())+shopitem.getNum());
									shopitem.setName(item.get("item_name").toString());
									shopitem.setMarketPrice(Long.valueOf(item.get("market_price").toString()));
									shopitem.setSourcingPrice(Long.valueOf(item.get("item_price").toString()));
									shopitem.setImgPath(item.get("img_path").toString());
									shopitem.setSize(item.get("item_size").toString());
									shopitem.setOnshelfTime(new Date());
									if(map!=null){
										Integer layerId = Integer.valueOf(map.get(item.get("item_id").toString()).toString());
										shopitem.setLayerId(layerId);								
									}
								}
								this.shopItemService.update(shopitem);
								this.saveLog(request.getSession(), shopitem, "*"+order.getOrderNo()+"上架，增加商品："+shopitem.getName()+"数量："+item.get("num"), order.getUserId(), user.getNickName(),cityId);
							}
						}
						order.setComfirm(3);
						order.setFlag(1);
						order.setDeliverDate(new Date());
						this.orderService.updateOrderAndCustomer(order);
						this.saveLog(request.getSession(), order, "更改便利店采购订单状态上架："+order.getOrderNo(),order.getUserId(),user.getNickName(),cityId);
						return "200";
					}
					return "202";
				}
			}
			
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return "203";
	}
	
	//退货单
	@RequestMapping(value="shop_refund.htm")
	public ModelAndView refund(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/sourcing/refund");
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){
			//超级管理员
			List<Refund> refunds = this.refundService.findRefundByShopId(shopId);
			view.addObject("shopId", shopId);
			view.addObject("refunds", refunds);
			return view;
		}else{
			//管理员
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
			if(null!=admin){
				List<Refund> refunds = this.refundService.findRefundByShopId(shopId);
				view.addObject("shopId", shopId);
				view.addObject("refunds", refunds);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
	}
	
	//退货单详情 
	@RequestMapping(value="shop_refundDetail.htm")
	public ModelAndView refundDetail(HttpServletRequest request,@RequestParam("id")Integer id){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/sourcing/refundDetail");
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){
			//超级管理员
			Refund refund = this.refundService.findRefundById(id);
			List<HashMap<String, Object>> list = this.refundService.findItemInfoByOrderNo(id);
			view.addObject("itemlist", list);
			view.addObject("refund", refund);
			return view;
		}else{
			//管理员
			Refund refund = this.refundService.findRefundById(id);
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), refund.getUserId());
			if(null!=admin){
				List<HashMap<String, Object>> list = this.refundService.findItemInfoByOrderNo(id);
				view.addObject("itemlist", list);
				view.addObject("refund", refund);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
	}
	
	//在售商品 需要拦截器
	@RequestMapping(value="shop_saleItem.htm")
	public ModelAndView saleItem(HttpServletRequest request,@RequestParam("shop_id")Integer shopId,@RequestParam(defaultValue="1",value="isdown")Integer isdown){
		ModelAndView view = new ModelAndView();
		String itemName = request.getParameter("item_name");
		if(null!=itemName){
			view.addObject("itemName", itemName);
		}
		view.addObject("shopId", shopId);
		if(isdown.equals(1)){
			SeniorSet seniorSet = this.seniorSetService.findById(shopId);
			if (seniorSet != null && null!=seniorSet.getIslayer() && seniorSet.getIslayer()==1){
				//开启分层
                view.setViewName("shop/manage/layerItem");
                List<ShopLayer> shopLayers = this.shopLayerService.findByShopIdAndStatus(shopId);
                List<ShopItem> itemlist = this.shopItemService.findItemByShopIdAndIsdownAndLayerId(shopId,1,shopLayers.get(0).getId());
                view.addObject("itemlist", itemlist);
                view.addObject("shopLayers", shopLayers);
                view.addObject("layerId", shopLayers.get(0).getId());
                return view;

            }else{
				view.setViewName("shop/manage/onSaleItem");
				ShopUser user = WXSessionHelper.getShopUser(request.getSession());
				Calendar calendar = Calendar.getInstance();
				calendar.add(Calendar.DATE, -1);
				Date sumdate = calendar.getTime();
				view.addObject("sumdate", sumdate);
				PersonUser puser = this.userService.findByOpenId(user.getOpenid());
				if(null!=puser){
					//超级管理员
					List<ShopItem> itemlist = this.shopItemService.findItemsByShopIdAndIsdown(shopId,isdown,itemName);
					CustomerUser day = this.customerService.findCheckedDay(shopId);
					view.addObject("itemlist", itemlist);
					view.addObject("day", day);
					return view;
				}else{
					//普通管理员
					ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
					if(null!=admin){
						//
						List<ShopItem> itemlist = this.shopItemService.findItemsByShopIdAndIsdown(shopId,isdown,itemName);
						CustomerUser day = this.customerService.findCheckedDay(shopId);
						view.addObject("itemlist", itemlist);
						view.addObject("day", day);
						return view;
					}

				}
			}
		}else{
			view.setViewName("shop/manage/unSaleItem");
			SeniorSet seniorSet = this.seniorSetService.findById(shopId);
			if (seniorSet != null && null!=seniorSet.getIslayer() && seniorSet.getIslayer()==1){
				view.addObject("flag", 1);
			}
			return view;
		}
		//不是管理员
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
	}

	@RequestMapping(value="shop_layerItem.json")
	@ResponseBody
	public LWResult layeritem(HttpServletRequest request,@RequestParam("shop_id")Integer shopId,@RequestParam("layerId")Integer layerId){
		List<ShopItem> itemlist = this.shopItemService.findItemByShopIdAndIsdownAndLayerId(shopId,1,layerId);
		return LWResult.ok(itemlist);
	}
	
	@RequestMapping(value="shop_saleItem.json")
	@ResponseBody
	public LWResult onlineitem(HttpServletRequest request,@RequestParam("shop_id")Integer shopId,@RequestParam(defaultValue="1",value="isdown")Integer isdown,@RequestParam(defaultValue="1",value="page")Integer page,@RequestParam(defaultValue="50",value="rows")Integer rows){
		String itemName = request.getParameter("item_name");
		PageHelper.startPage(page, rows);
		List<ShopItem> itemlist = this.shopItemService.findItemsByShopIdAndIsdown(shopId,isdown,itemName);
		PageInfo<ShopItem> info = new PageInfo<ShopItem>(itemlist);
		return LWResult.ok(info);
	}
	
	/**
	 * 店铺商品下架，需要拦截器
	 */
	@RequestMapping(value="shop_shelf.do",method=RequestMethod.POST)
	@ResponseBody
	public String onShelf(HttpServletRequest request,@RequestParam("item_id")Integer itemId,@RequestParam("isdown")Integer isdown){
		try {
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			ShopItem item = this.shopItemService.findItemById(itemId);
			CustomerUser cuser = this.customerService.findById(item.getShopId());
			Integer cityId = null;
			if(null !=cuser){
				cityId = cuser.getCityId();
			}
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			if(null!=puser){
				//超级管理员
				item.setIsdown(isdown);
				item.setUpdatedTime(new Date());
				this.shopItemService.update(item);
				CustomerUser customerUser = new CustomerUser();
				customerUser.setId(item.getShopId());
				customerUser.setCheckTime(new Date());
				this.customerService.update(customerUser);
				if(isdown.equals(0)){
				BeRefundItem refundItem = new BeRefundItem();
				refundItem.setCreatedDate(new Date());
				refundItem.setImgPath(item.getImgPath());
				refundItem.setItemId(item.getItemId());
				refundItem.setItemName(item.getName());
				refundItem.setPrice(item.getSourcingPrice());
				refundItem.setShopId(item.getShopId());
				refundItem.setSize(item.getSize());
				this.beRefundItemService.insert(refundItem);
				}
				this.saveLog(request.getSession(), item, "便利店单品下架："+item.getId(),item.getShopId(),user.getNickName(),cityId);
				return "200";
			}else{
				ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), item.getShopId());
				if(null!=admin){
					item.setIsdown(isdown);
					item.setUpdatedTime(new Date());
					this.shopItemService.update(item);
					CustomerUser customerUser = new CustomerUser();
					customerUser.setId(item.getShopId());
					customerUser.setCheckTime(new Date());
					this.customerService.update(customerUser);
					if(isdown.equals(0)){
					BeRefundItem refundItem = new BeRefundItem();
					refundItem.setCreatedDate(new Date());
					refundItem.setImgPath(item.getImgPath());
					refundItem.setItemId(item.getItemId());
					refundItem.setItemName(item.getName());
					refundItem.setPrice(item.getSourcingPrice());
					refundItem.setShopId(item.getShopId());
					refundItem.setSize(item.getSize());
					this.beRefundItemService.insert(refundItem);
					}
					this.saveLog(request.getSession(), item, "便利店单品下架："+item.getId(),item.getShopId(),user.getNickName(),cityId);
					return "200";
				}
			}
		} catch (Exception e) {
			logger.error("单品下架操作异常"+e.getMessage());
		}
		return "202";
	}

	//单品上架
	@RequestMapping(value="shop_upShelf.do",method=RequestMethod.POST)
	@ResponseBody
	public LWResult upShelf(HttpServletRequest request,@RequestParam("item_id")Integer itemId){
		try {
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			ShopItem item = this.shopItemService.findItemById(itemId);
			CustomerUser cuser = this.customerService.findById(item.getShopId());
			Integer cityId = null;
			if(null !=cuser){
				cityId = cuser.getCityId();
			}
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			if(null!=puser){
				//超级管理员
				SeniorSet seniorSet = this.seniorSetService.findById(item.getShopId());
				String msg = null;
				if(null!=seniorSet && null!=seniorSet.getIslayer() && 1==seniorSet.getIslayer()){
					ShopLayer layer = this.shopLayerService.findById(item.getLayerId());
					if(null==layer){
						List<ShopLayer> layers = this.shopLayerService.findByShopId(item.getShopId());
						if(!layers.isEmpty()){
							item.setLayerId(layers.get(0).getId());
							msg = layers.get(0).getName();
						}
					}else{
						msg = layer.getName();
					}
				}
				
				item.setIsdown(1);
				item.setUpdatedTime(new Date());
				this.shopItemService.update(item);
				CustomerUser customerUser = new CustomerUser();
				customerUser.setId(item.getShopId());
				customerUser.setCheckTime(new Date());
				this.customerService.update(customerUser);

				this.saveLog(request.getSession(), item, "便利店单品上架："+item.getId(),item.getShopId(),user.getNickName(),cityId);
				return LWResult.ok(msg);
			}else{
				ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), item.getShopId());
				if(null!=admin){
					SeniorSet seniorSet = this.seniorSetService.findById(item.getShopId());
					String msg = null;
					if(null!=seniorSet && null!=seniorSet.getIslayer() && 1==seniorSet.getIslayer()){
						ShopLayer layer = this.shopLayerService.findById(item.getLayerId());
						if(null==layer){
							List<ShopLayer> layers = this.shopLayerService.findByShopId(item.getShopId());
							if(!layers.isEmpty()){
								item.setLayerId(layers.get(0).getId());
								msg = layers.get(0).getName();
							}
						}else{
							msg = layer.getName();
						}
					}
					item.setIsdown(1);
					item.setUpdatedTime(new Date());
					this.shopItemService.update(item);
					CustomerUser customerUser = new CustomerUser();
					customerUser.setId(item.getShopId());
					customerUser.setCheckTime(new Date());
					this.customerService.update(customerUser);

					this.saveLog(request.getSession(), item, "便利店单品上架："+item.getId(),item.getShopId(),user.getNickName(),cityId);
					return LWResult.ok(msg);
				}
			}
			return LWResult.build(201,"不是管理");
		} catch (Exception e) {
			logger.error("单品上架操作异常"+e.getMessage());
		}
		return LWResult.build(202,"上架失败");
	}
	/**
	 * 店铺管理上下架商品编辑价格，需要拦截器
	 * @param request
	 * @param itemId
	 * @param isdown
	 * @param price
	 * @param num
	 * @return
	 */
	@RequestMapping(value="shop_itemUpdate.do",method=RequestMethod.POST)
	@ResponseBody
	public LWResult itemUpdate(HttpServletRequest request,@RequestParam("item_id")Integer itemId,@RequestParam(defaultValue="2",value="isdown")Integer isdown,@RequestParam("price")Long price,@RequestParam("num")Integer num){
		try {
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			ShopItem item = this.shopItemService.findItemById(itemId);
			CustomerUser cuser = this.customerService.findById(item.getShopId());
			Integer cityId = null;
			if(null !=cuser){
				cityId = cuser.getCityId();
			}
			Integer oldnum = item.getNum();
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			if(null!=puser){
				//超级管理员
				String msg = null;
				if(isdown.equals(0)){
					item.setIsdown(0);
				}else if (isdown.equals(1)) {
					item.setIsdown(1);
					SeniorSet seniorSet = this.seniorSetService.findById(item.getShopId());
					if(null!=seniorSet && null!=seniorSet.getIslayer() && 1==seniorSet.getIslayer()){
						ShopLayer layer = this.shopLayerService.findById(item.getLayerId());
						if(null==layer){
							List<ShopLayer> layers = this.shopLayerService.findByShopId(item.getShopId());
							if(!layers.isEmpty()){
								item.setLayerId(layers.get(0).getId());
								msg = layers.get(0).getName();
							}
						}else{
							msg = layer.getName();
						}
					}
				}

				item.setNum(num);
				item.setSalePrice(price);
				item.setUpdatedTime(new Date());
				this.shopItemService.update(item);
				if(!num.equals(oldnum) || isdown.equals(0)){
					CustomerUser customerUser = new CustomerUser();
					customerUser.setId(item.getShopId());
					customerUser.setCheckTime(new Date());
					this.customerService.update(customerUser);
				}
				if(isdown.equals(0)){
					BeRefundItem refundItem = new BeRefundItem();
					refundItem.setCreatedDate(new Date());
					refundItem.setImgPath(item.getImgPath());
					refundItem.setItemId(item.getItemId());
					refundItem.setItemName(item.getName());
					refundItem.setPrice(item.getSourcingPrice());
					refundItem.setShopId(item.getShopId());
					refundItem.setSize(item.getSize());
					this.beRefundItemService.insert(refundItem);
				}
				this.saveLog(request.getSession(), item, "便利店上下架并修改价格："+item.getId(),item.getShopId(),user.getNickName(),cityId);
				return LWResult.ok(msg);
			}else{
				ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), item.getShopId());
				if(null!=admin){
					String msg = null;
					if(isdown.equals(0)){
						item.setIsdown(0);
					}else if (isdown.equals(1)) {
						item.setIsdown(1);
						SeniorSet seniorSet = this.seniorSetService.findById(item.getShopId());
						if(null!=seniorSet && null!=seniorSet.getIslayer() && 1==seniorSet.getIslayer()){
							ShopLayer layer = this.shopLayerService.findById(item.getLayerId());
							if(null==layer){
								List<ShopLayer> layers = this.shopLayerService.findByShopId(item.getShopId());
								if(!layers.isEmpty()){
									item.setLayerId(layers.get(0).getId());
									msg = layers.get(0).getName();
								}
							}else{
								msg = layer.getName();
							}
						}
					}
					item.setNum(num);
					item.setSalePrice(price);
					item.setUpdatedTime(new Date());
					this.shopItemService.update(item);
					if(!num.equals(oldnum) || isdown.equals(0)){
						CustomerUser customerUser = new CustomerUser();
						customerUser.setId(item.getShopId());
						customerUser.setCheckTime(new Date());
						this.customerService.update(customerUser);
					}
					if(isdown.equals(0)){
						BeRefundItem refundItem = new BeRefundItem();
						refundItem.setCreatedDate(new Date());
						refundItem.setImgPath(item.getImgPath());
						refundItem.setItemId(item.getItemId());
						refundItem.setItemName(item.getName());
						refundItem.setPrice(item.getSourcingPrice());
						refundItem.setShopId(item.getShopId());
						refundItem.setSize(item.getSize());
						this.beRefundItemService.insert(refundItem);
					}
					this.saveLog(request.getSession(), item, "便利店上下架并修改价格："+item.getId(),item.getShopId(),user.getNickName(),cityId);
					return LWResult.ok(msg);
				}
			}
			return LWResult.build(201,"无权限");
		} catch (Exception e) {
			logger.error("更新店铺商品操作异常"+e.getMessage());
		}
		return LWResult.build(202,"失败");
	}

    //修改层数
    @RequestMapping(value="/shop_setLayer.do",method=RequestMethod.POST)
    @ResponseBody
    public String setLayer(HttpServletRequest request,@RequestParam("layerId")Integer layerId,@RequestParam("shopId")Integer shopId,@RequestParam("itemId")Integer itemId){
        try {
            ShopUser user = WXSessionHelper.getShopUser(request.getSession());
            PersonUser puser = this.userService.findByOpenId(user.getOpenid());
            ShopItem item = this.shopItemService.findItemById(itemId);
            CustomerUser cuser = this.customerService.findById(item.getShopId());
            Integer cityId = null;
            if(null !=cuser){
                cityId = cuser.getCityId();
            }
            if(null!=puser){
                //超级管理员
                item.setLayerId(layerId);
                item.setUpdatedTime(new Date());
                this.shopItemService.update(item);
                this.saveLog(request.getSession(), item, "修改层数："+item.getId(),item.getShopId(),user.getNickName(),cityId);
                return "200";
            }else{
                //管理员
                ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
                if(null!=admin){
                    item.setLayerId(layerId);
                    item.setUpdatedTime(new Date());
                    this.shopItemService.update(item);
                    this.saveLog(request.getSession(), item, "修改层数："+item.getId(),item.getShopId(),user.getNickName(),cityId);
                    return "200";
                }
            }
        } catch (Exception e) {
            logger.error("更新店铺基础信息异常"+e.getMessage());
        }
        return "201";
    }

    //新增或编辑层数
    @RequestMapping(value="/shop_saveLayer.do",method=RequestMethod.POST)
    @ResponseBody
    public LWResult saveLayer(HttpServletRequest request,ShopLayer layer){
        try {
            ShopUser user = WXSessionHelper.getShopUser(request.getSession());
            PersonUser puser = this.userService.findByOpenId(user.getOpenid());
            CustomerUser cuser = this.customerService.findById(layer.getShopId());
            Integer cityId = null;
            if(null !=cuser){
                cityId = cuser.getCityId();
            }
            if(null!=puser){
                //超级管理员
                if(null!=layer.getId()){
                    this.shopLayerService.update(layer);
                    return LWResult.ok();
                }else{
                    layer.setCreatedTime(new Date());
                    layer.setStatus(1);
                    this.shopLayerService.insert(layer);
                    return LWResult.ok(layer);
                }
            }else{
                //管理员
                ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), layer.getShopId());
                if(null!=admin){
                    if(null!=layer.getId()){
                        this.shopLayerService.update(layer);
                        return LWResult.ok();
                    }else{
                        layer.setCreatedTime(new Date());
                        layer.setStatus(1);
                        this.shopLayerService.insert(layer);
                        return LWResult.ok(layer);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("更新店铺基础信息异常"+e.getMessage());
        }
        return LWResult.build(201,"失败");
    }

	//删除层数
	@RequestMapping(value="/shop_deleteLayer.do",method=RequestMethod.POST)
	@ResponseBody
	public String deleteLayer(HttpServletRequest request,@RequestParam("layerId")Integer layerId,@RequestParam("shopId")Integer shopId){
		try {
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			CustomerUser cuser = this.customerService.findById(shopId);
			Integer cityId = null;
			if(null !=cuser){
				cityId = cuser.getCityId();
			}
			if(null!=puser){
				//超级管理员
				this.shopLayerService.delete(layerId);
				return "200";
			}else{
				//管理员
				ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
				if(null!=admin){
					this.shopLayerService.delete(layerId);
					return "200";
				}
			}
		} catch (Exception e) {
			logger.error("删除层数失败"+e.getMessage());
		}
		return "201";
	}
	
	//消费记录 待修改
	@RequestMapping(value="shop_consumeList.htm")
	public ModelAndView consumeList(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/consume/list");
		view.addObject("shopId", shopId);
		String name = request.getParameter("name");
			view.addObject("name", name);
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){
			return view;
		}else{
			//管理员
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
			if(null!=admin){
				return view;
			}
		}
		//啥都不是
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
	}
	
	@RequestMapping(value="shop_consumeTest.json")
	@ResponseBody
	public LWResult consumetest(HttpServletRequest request,@RequestParam("shop_id")Integer shopId,@RequestParam(defaultValue="1",value="page")Integer page,@RequestParam(defaultValue="50",value="rows")Integer rows){
		String name = request.getParameter("name");
		PageHelper.startPage(page, rows);
		List<ShopOrder> lists = this.shopOrderService.findOrderAndItemByShopId(shopId,name);
		PageInfo<ShopOrder> info = new PageInfo<ShopOrder>(lists);
		return LWResult.ok(info);
	}
	
	//店铺管理 首页
	@RequestMapping(value="/home/shop_index.htm")
	public ModelAndView index(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		ModelAndView view = new ModelAndView("shop/home/index");
		if(null!=puser){
			view.addObject("flag", 1);
			if(isadmin(puser.getId()) || "客户经理".equals(puser.getPost())){
				view.addObject("link", 1);
			}
		}
		Integer type = this.customerService.findById(shopId).getIswxvip();
		view.addObject("shopId", shopId);
		view.addObject("type", type);
		return view;
	}

	@RequestMapping(value="/home/shop_link.htm")
	public ModelAndView link(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		ModelAndView view = new ModelAndView("shop/home/link");
		if(null!=puser){
			if(isadmin(puser.getId()) || "客户经理".equals(puser.getPost())){
				CustomerUser userInfo=customerService.findById(shopId);
				view.addObject("user", userInfo);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
		
	}
	
	//店铺详情
	@RequestMapping(value="/home/shop_details.htm")
	public ModelAndView details(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
//		PersonUser puser = this.userService.findByOpenId(user.getOpenid());

		CustomerUser userInfo=customerService.findById(shopId);
		if (userInfo !=null) {
			if (userInfo.getInterfacePersonId() != null && userInfo.getInterfacePersonId()>0) {
				PersonUser personUser = this.userService.findById(userInfo.getInterfacePersonId());
				if (personUser != null) {
					userInfo.setInterfacePerson(StringUtils.defaultIfBlank(personUser.getUserName(), ""));
				}
			}

			if (userInfo.getRegionId() != null) {
				CityRegion region = this.cityRegionService.findByRegionId(userInfo.getRegionId());
				if (region != null) {
					userInfo.setRegion(StringUtils.defaultIfBlank(region.getName(), ""));
				}

			}

			if (userInfo.getReseauId() != null) {
				Reseau reseau = this.reseauService.findById(userInfo.getReseauId());
				if (reseau != null) {
					userInfo.setReseauName(StringUtils.defaultIfBlank(reseau.getName(), ""));
				}
			}

			if (userInfo.getCheckStr() != null) {
				String checks = "";
				for (String check : userInfo.getCheckStr().split(",")) {
					if (check.equals("1")) {
						checks = checks + "微信,";
					}
					if (check.equals("2")) {
						checks = checks + "支付宝,";
					}
				}
				userInfo.setCheckStr(checks.substring(0, checks.length() - 1));
			}

		}

		Date createdtime = userInfo.getCreatedTime();
		AccountLock lock = new AccountLock();
		lock.setYears(Integer.valueOf(DateUtil.getYear(createdtime)).toString());
		lock.setMonths(Integer.valueOf(DateUtil.getMonth(createdtime)).toString());
		lock.setCityId(userInfo.getCityId());
		int islock = this.accountLockService.findLockByCityId(lock);
		Date date = new Date();
		int m =(date.getYear()-createdtime.getYear())*12+date.getMonth()-createdtime.getMonth();

		ModelAndView view = new ModelAndView("shop/home/details");
		if (islock==1 || m>1){
			view.addObject("islock", 1);
		}
		view.addObject("user", userInfo);
		return view;
	}
	
	//店铺基础信息
	@RequestMapping(value="/home/shop_common.htm")
	public ModelAndView common(@RequestParam("shop_id")Integer shopId){
		ModelAndView view = new ModelAndView("shop/home/common");
		CustomerUser user = this.customerService.findById(shopId);
		view.addObject("user", user);
		return view;
	}
	
	//店铺基础信息修改
	@RequestMapping(value="/home/shop_common.do",method=RequestMethod.POST)
	@ResponseBody
	public String docommon(HttpServletRequest request,CustomerUser shop){
		try {
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			CustomerUser customerUser = this.customerService.findById(shop.getId());
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			if(null!=puser){
				//超级管理员,
				this.customerService.update(shop);
				this.saveLog(request.getSession(), shop, "便利店用户修改信息："+shop.getId()+shop.getCompanyName(),shop.getId(),user.getNickName(),customerUser.getCityId());
				return "200";
			}else{
				//管理员
				ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shop.getId());
				if(null!=admin){
					this.customerService.update(shop);
					this.saveLog(request.getSession(), shop, "便利店用户修改信息："+shop.getId()+shop.getCompanyName(),shop.getId(),user.getNickName(),customerUser.getCityId());
					return "200";
				}
			}
		} catch (Exception e) {
			logger.error("更新店铺基础信息异常"+e.getMessage());
		}	
		return "201";
	}
	
	//店铺高级信息
	@RequestMapping(value="/home/shop_senior.htm")
	public ModelAndView gaoji(@RequestParam("shop_id")Integer shopId){
		ModelAndView view = new ModelAndView("shop/home/senior");
		SeniorSet seniorSet = this.seniorSetService.findById(shopId);
		TranConsume Tran = this.tranConsumeService.findMoreLossMoney(shopId);
		view.addObject("seniorSet", seniorSet);
		view.addObject("Tran", Tran);
		view.addObject("shopId", shopId);
		return view;
	}
	
	//店铺高级信息修改
	@RequestMapping(value="/home/shop_senior.do",method=RequestMethod.POST)
	@ResponseBody
	public String dosenior(HttpServletRequest request,SeniorSet set){
		try {
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			CustomerUser customerUser = this.customerService.findById(set.getShopId());
			Integer cityId = null;
			if(null !=customerUser){
				cityId = customerUser.getCityId();
			}
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			if(null!=puser){
				//超级管理员,
				SeniorSet seniorSet = this.seniorSetService.findById(set.getShopId());
				if(null!=seniorSet){
					this.seniorSetService.updateUnselective(set);
					this.saveLog(request.getSession(), set, "更新便利店高级设置："+set.getShopId(),set.getShopId(),user.getNickName(),cityId);
				}else{
					this.seniorSetService.create(set);
					this.saveLog(request.getSession(), set, "创建便利店高级设置："+set.getShopId(),set.getShopId(),user.getNickName(),cityId);
				}
				return "200";
			}else{
				//管理员
				ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), set.getShopId());
				if(null!=admin){
					SeniorSet seniorSet = this.seniorSetService.findById(set.getShopId());
					if(null!=seniorSet){
						this.seniorSetService.updateUnselective(set);
						this.saveLog(request.getSession(), set, "更新便利店高级设置："+set.getShopId(),set.getShopId(),user.getNickName(),cityId);
					}else{
						this.seniorSetService.create(set);
						this.saveLog(request.getSession(), set, "创建便利店高级设置："+set.getShopId(),set.getShopId(),user.getNickName(),cityId);
					}
				}
				return "200";
			}
		} catch (Exception e) {
			logger.error("更新店铺基础信息异常"+e.getMessage());
		}	
		return "201";
	}
	
	//补贴二维码
		@RequestMapping(value="/shop_subsidy.htm",produces="text/html;charset=UTF-8")
		@ResponseBody
		public void subsidy(HttpServletRequest request,HttpServletResponse response,@RequestParam("shop_id")String shopId){
			String content = properties.ONLINE_URL+"convenient/shop_addUser.htm?shop_id="+shopId;
	        try {
				BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 500, 500);
				MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@RequestMapping(value="/shop_addUser.htm")
		public ModelAndView shop_addUser(HttpServletRequest request,RedirectAttributes attr,@RequestParam("shop_id")String shopId){
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			ModelAndView view = new ModelAndView("shop/home/addUser");
			if(null!=user){
				view.addObject("shopId", shopId);
				view.addObject("userId", user.getId());
				return view;
			}else{
				attr.addAttribute("redirecturl", "/convenient/shop_addUser.htm?shop_id="+shopId);
				return new ModelAndView("redirect:/convenient/shop_authIndex.htm");
			}
		}

		@RequestMapping(value="/shop_addUser.do",method=RequestMethod.POST)
		@ResponseBody
		public String shop_addUserdo(ShopUserSubsidy user){
			try {
				ShopUser shopUser = this.shopUserService.findById(user.getUserId());
				user.setCreatedDate(new Date());
				user.setNickName(shopUser.getNickName());
				ShopUserSubsidy existuser = this.shopUserSubsidyService.findByUserIdAndShopId(user.getUserId(),user.getShopId());
				if(null==existuser){
					this.shopUserSubsidyService.save(user);
					return "200";
				}else{
					return "202";
				}
			} catch (Exception e) {
				logger.error("添加补贴员工失败"+e.getMessage());
				return "201";
			}
		}

		@RequestMapping(value="/shop_addUserSuccess.htm")
		public ModelAndView shop_addUserSuccess(){
			return new ModelAndView("shop/home/add_success");
		}

		//员工列表
		@RequestMapping(value="/shop_userList.htm")
		public ModelAndView shop_userList(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			ModelAndView view = new ModelAndView("shop/home/userList");
			view.addObject("shopId", shopId);
			if(null!=puser){
				SeniorSet seniorSet = this.seniorSetService.findById(shopId);
				if(null!=seniorSet.getFreeFee() && null!=seniorSet.getDayOrMonth()){
					if(seniorSet.getDayOrMonth()==1){
						List<ShopUserSubsidy> list = this.shopUserSubsidyService.findListByShopIdAndType(shopId,1,seniorSet.getFreeFee());
						view.addObject("lists", list);
					}else if (seniorSet.getDayOrMonth()==2) {
						List<ShopUserSubsidy> list = this.shopUserSubsidyService.findListByShopIdAndType(shopId,2,seniorSet.getFreeFee());
						view.addObject("lists", list);
					}
				}else{
					List<ShopUserSubsidy> list = this.shopUserSubsidyService.findListByShopId(shopId);
					view.addObject("lists", list);
				}
				return view;
			}else{
				ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
				if(null!=admin){
					SeniorSet seniorSet = this.seniorSetService.findById(shopId);
					if(null!=seniorSet.getFreeFee() && null!=seniorSet.getDayOrMonth()){
						if(seniorSet.getDayOrMonth()==1){
							List<ShopUserSubsidy> list = this.shopUserSubsidyService.findListByShopIdAndType(shopId,1,seniorSet.getFreeFee());
							view.addObject("lists", list);
						}else if (seniorSet.getDayOrMonth()==2) {
							List<ShopUserSubsidy> list = this.shopUserSubsidyService.findListByShopIdAndType(shopId,2,seniorSet.getFreeFee());
							view.addObject("lists", list);
						}
					}else{
						List<ShopUserSubsidy> list = this.shopUserSubsidyService.findListByShopId(shopId);
						view.addObject("lists", list);
					}
					return view;
				}
			}
			return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
		}

		@RequestMapping(value="/shop_delUser.do",method=RequestMethod.POST)
		@ResponseBody
		public String shop_delUserdo(HttpServletRequest request,@RequestParam("id")Integer id,@RequestParam("shop_id")Integer shopId){
			try {
				ShopUser user = WXSessionHelper.getShopUser(request.getSession());
				PersonUser puser = this.userService.findByOpenId(user.getOpenid());
				if(null!=puser){
					this.shopUserSubsidyService.deleteById(id);
					return "200";
				}else{
					ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
					if(null!=admin){
						this.shopUserSubsidyService.deleteById(id);
						return "200";
					}
				}
				return "202";
			} catch (Exception e) {
				logger.error("删除补贴员工失败"+e.getMessage());
			}
			return "201";
		}

		/**
		 * 通用授权工具,redirecturl毁掉地址参数
		 * @param request
		 * @return
		 */
		@RequestMapping(value="shop_authIndex.htm")
		public ModelAndView shop_authindex(HttpServletRequest request){
			//静默授权
			String state = request.getParameter("redirecturl");
			String appid = properties.weixinAppid;
			String redirect_uri = properties.ONLINE_URL+"convenient/shop_authIndexQuiet.htm";
			String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
			String requestUrl = quieturl.replace("APPID", appid).replace("REDIRECT_URL", urlEncode).replace("STATE", state);
			return new ModelAndView("redirect:"+requestUrl+"");
		}

		@RequestMapping("/shop_authIndexQuiet.htm")
		public ModelAndView shop_authIndexQuiet(HttpServletRequest request,HttpServletResponse response){
			String code = request.getParameter("code");
			String url = request.getParameter("state");
			String openId ="";
			if (!"authdeny".equals(code)) {
				WeiXinOauth2Token weiXinOauth2Token = AdvancedUtil
						.getOauth2AccessToken(properties.weixinAppid,
								properties.weixinAppsecret, code);
				openId = weiXinOauth2Token.getOpenId();
				String accessToken = weiXinOauth2Token.getAccessToken();

				ShopUser wxUser = this.shopUserService.findByOpenId(openId);
				WXUser info = null;
				if(null==wxUser){
					info = AdvancedUtil.getWeixinUserInfo(accessToken, openId);
					if(null!=info && info.getNickName()!=null && info.getHeadImgurl()!=null){
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
						return new ModelAndView("redirect:"+url);
					}
				}else{
					info = AdvancedUtil.getWeixinUserInfo(accessToken, openId);
					if(null!=info && info.getNickName()!=null && info.getHeadImgurl()!=null){
						wxUser.setCity(info.getCity());
						wxUser.setCountry(info.getCountry());
						wxUser.setHeadImgurl(info.getHeadImgurl());
						wxUser.setNickName(info.getNickName());
						wxUser.setProvince(info.getProvince());
						wxUser.setSex(info.getSex());
						this.shopUserService.update(wxUser);
						WXSessionHelper.setShopUser(request.getSession(), wxUser);
						return new ModelAndView("redirect:"+url);
					}
				}
			}
			//显示授权
			String appid = properties.weixinAppid;
			String redirect_uri = properties.ONLINE_URL+"convenient/shop_OauthXian.htm";
			String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
			String requestUrl = url.replace("APPID", appid).replace("REDIRECT_URL", urlEncode).replace("STATE", url);
			return new ModelAndView("redirect:"+requestUrl+"");
		}

		@RequestMapping("/shop_OauthXian.htm")
		public ModelAndView shop_OauthXian(HttpServletRequest request,HttpServletResponse response){
			String code = request.getParameter("code");
			String url = request.getParameter("state");
			String openId ="";
			String token = "";
			WXUser info = null;
			if (!"authdeny".equals(code)) {
				WeiXinOauth2Token weiXinOauth2Token = AdvancedUtil
						.getOauth2AccessToken(properties.weixinAppid,
								properties.weixinAppsecret, code);
				openId = weiXinOauth2Token.getOpenId();
				token = weiXinOauth2Token.getAccessToken();
				AccessToken token2 = this.accessTokenService.findById(openId);
				if(null==token2){
					token2 = new AccessToken();
					token2.setId(openId);
					token2.setAccessToken(weiXinOauth2Token.getAccessToken());
					token2.setRefreshToken(weiXinOauth2Token.getRefeshToken());
					token2.setUpdatedTime(new Date());
					this.accessTokenService.insert(token2);
				}else{
					token2.setAccessToken(weiXinOauth2Token.getAccessToken());
					token2.setRefreshToken(weiXinOauth2Token.getRefeshToken());
					token2.setUpdatedTime(new Date());
					this.accessTokenService.update(token2);
				}
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
				info = AdvancedUtil.getWeixinUserInfo(token, openId);
				wxUser.setCity(info.getCity());
				wxUser.setCountry(info.getCountry());
				wxUser.setHeadImgurl(info.getHeadImgurl());
				wxUser.setNickName(info.getNickName());
				wxUser.setProvince(info.getProvince());
				wxUser.setSex(info.getSex());
				this.shopUserService.update(wxUser);
				WXSessionHelper.setShopUser(request.getSession(), wxUser);
			}
			return new ModelAndView("redirect:"+url);
		}



	//排行榜
	@RequestMapping(value="/home/shop_rank.htm")
	public ModelAndView managerRank(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		ModelAndView view = new ModelAndView("shop/home/rank");
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DATE, -1);
		Date sumdate = calendar.getTime();
		view.addObject("sumdate", sumdate);
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){
			//超级管理员
			List<ConsumeRank> lists = this.consumeRankService.findByShopId(shopId);
			Long money = this.consumeRankService.findTotalByShopId(shopId);
			CustomerUser customerUser = this.customerService.findById(shopId);
			view.addObject("lists", lists);
			view.addObject("money", money);
			view.addObject("discount", customerUser.getShopDiscount());
			return view;
		}else{
			//普通管理员
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
			if(null!=admin){
				//
				List<ConsumeRank> lists = this.consumeRankService.findByShopId(shopId);
				Long money = this.consumeRankService.findTotalByShopId(shopId);
				CustomerUser customerUser = this.customerService.findById(shopId);
				view.addObject("lists", lists);
				view.addObject("money", money);
				view.addObject("discount", customerUser.getShopDiscount());
				return view;
			}
			
		}
		
		//不是管理员
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
		
		
	}
	
	//店铺管理员  
	@RequestMapping(value="/home/shop_manager.htm")
	public ModelAndView managershow(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/home/manager");
		List<ShopUser> managers = this.shopUserService.findManagerByShopid(shopId);
		view.addObject("managers", managers);
		view.addObject("userId", user.getId());
		view.addObject("shopId", shopId);
		return view;
	}
	
	@RequestMapping(value="/home/shop_manager.do")
	@ResponseBody
	public String managerdo(HttpServletRequest request,@RequestParam("id")Integer id,@RequestParam("shop_id")Integer shopId){
		try {
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			if(null!=puser){
				//超级管理员,
				this.shopAdminService.delete(id,shopId);
				return "200";
			}else{
				//管理员
				ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
				if(null!=admin){
					this.shopAdminService.delete(id,shopId);
					return "200";
				}else{
					return "201";
				}
			}
		} catch (Exception e) {
			logger.error("删除店铺管理员异常"+e.getMessage());
		}	
		return "202";
	}
	
	//月账单
	@RequestMapping(value="/home/shop_monthRecord.htm")
	public ModelAndView monthrecord(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/home/monthRecord");
		view.addObject("shop_id", shopId);
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		CustomerUser customerUser = this.customerService.findById(shopId);
		view.addObject("payway", customerUser.getPayBillWay());
		if(null!=puser){
			//超级管理员
			List<ShopOrder> lists = this.shopOrderService.findMonthRecordByShopId(shopId);
			view.addObject("monthrecords", lists);
			return view;
		}else{
			//管理员
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
			if(null!=admin){
				List<ShopOrder> lists = this.shopOrderService.findMonthRecordByShopId(shopId);
				view.addObject("monthrecords", lists);
				return view;
			}
		}
		//啥都不是
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
	}
	
	@RequestMapping(value="/home/shop_monthRecordDetail.htm")
	public ModelAndView monthrecordDetail(HttpServletRequest request,@RequestParam("shop_id")Integer shopId,@RequestParam("month")String month){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/home/monthRecordDetail");
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){
			//超级管理员
			ShopOrder data = this.shopOrderService.findMonthRecordByShopIdAndDate(shopId,month);
			CustomerUser customerUser = this.customerService.findById(shopId);
			view.addObject("discount", customerUser.getShopDiscount());
			view.addObject("record", data);
			view.addObject("month", month);
			view.addObject("shopId", shopId);
			return view;
		}else{
			//管理员
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
			if(null!=admin){
				ShopOrder data = this.shopOrderService.findMonthRecordByShopIdAndDate(shopId,month);
				CustomerUser customerUser = this.customerService.findById(shopId);
				view.addObject("discount", customerUser.getShopDiscount());
				view.addObject("record", data);
				view.addObject("month", month);
				view.addObject("shopId", shopId);
				return view;
			}
		}
		//啥都不是
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
	}
	
	@RequestMapping(value="/home/shop_QRCode.htm")
	public ModelAndView QRCode(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/home/shopcode");
		CustomerUser customerUser = this.customerService.findById(shopId);
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){
			//超级管理员
			List<ItemCategory> list = this.itemCategoryService.findAllEXOtherByCityId(customerUser.getCityId());
			view.addObject("shopId", shopId);
			view.addObject("cats", list);
			return view;
		}else{
			//管理员
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
			if(null!=admin){
				List<ItemCategory> list = this.itemCategoryService.findAllEXOtherByCityId(customerUser.getCityId());
				view.addObject("shopId", shopId);
				view.addObject("cats", list);
				return view;
			}
		}
		//啥都不是
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
	}
	
	//店铺二维码
	@RequestMapping(value="/shop_QRCode.htm",produces="text/html;charset=UTF-8")
	@ResponseBody
	public void erweima(HttpServletRequest request,HttpServletResponse response,@RequestParam("shop_id")String shopId){
		String cid = request.getParameter("cid");
		String content = "";
		if(null!=cid){
			content = properties.ONLINE_URL+"convenient/shop_item.htm?shop_id="+shopId+"&cid="+cid;
		}else{
			content = properties.ONLINE_URL+"convenient/shop_item.htm?shop_id="+shopId;
		}
        try {
			BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, 500, 500);   
			MatrixToImageWriter.writeToStream(bitMatrix, "png", response.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	//售后记录
	@RequestMapping(value="/shop_record.htm")
	public ModelAndView record(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/home/recordlist");
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){
			//超级管理员
			List<AfterSalesRecord> list = this.afterSalesRecordService.findByUserId(shopId);
			view.addObject("salesRecord", list);
			view.addObject("shopId", shopId);
			return view;
		}else{
			//管理员
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
			if(null!=admin){
				List<AfterSalesRecord> list = this.afterSalesRecordService.findByUserIdAndFlag(shopId,0);
				view.addObject("salesRecord", list);
				view.addObject("shopId", shopId);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
	}
	
	
	@RequestMapping(value="shop_deleteRecord.do")
	@ResponseBody
	public String deleteRecord(HttpServletRequest request,@RequestParam("id")Integer id,@RequestParam("shop_id")Integer shopId){
		try {
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			if(null!=puser){
				//超级管理员,
				this.afterSalesRecordService.delete(id);
				return "200";
			}else{
				//管理员
				ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
				if(null!=admin){
					this.afterSalesRecordService.delete(id);
					return "200";
				}else{
					return "201";
				}
			}
		} catch (Exception e) {
			logger.error("删除店铺管理员异常"+e.getMessage());
		}	
		return "202";
	}	
	
	//添加售后记录
	@RequestMapping(value="/shop_addrecord.htm")
	public ModelAndView addrecord(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/home/addrecord");
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){
			//超级管理员
			view.addObject("shopId", shopId);
			return view;
		}else{
			//管理员
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
			if(null!=admin){
				view.addObject("shopId", shopId);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
	}
	
	@RequestMapping(value="shop_addRecord.do")
	@ResponseBody
	public String adddoRecord(HttpServletRequest request,@RequestParam("shop_id")Integer shopId,@RequestParam("content")String content){
		try {
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			CustomerUser customerUser = this.customerService.findById(shopId);
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			if(null!=puser){
				//超级管理员,
				AfterSalesRecord record = new AfterSalesRecord();
				record.setContent(content);
				record.setCreatedTime(new Date());
				record.setDoState(1);
				record.setRecordType(3);
				record.setStatus(1);
				record.setUserId(shopId);
				record.setUserName(customerUser.getUserName());
				//record.setRegion(customerUser.getRegion());
				record.setRecordMan(user.getOpenid());
				record.setFlag(0);
				record.setCityId(customerUser.getCityId());
				this.afterSalesRecordService.insert(record);
				return "200";
			}else{
				//管理员
				ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
				if(null!=admin){
					AfterSalesRecord record = new AfterSalesRecord();
					record.setContent(content);
					record.setCreatedTime(new Date());
					record.setDoState(1);
					record.setRecordType(2);
					record.setStatus(1);
					record.setUserId(shopId);
					record.setUserName(customerUser.getUserName());
					//record.setRegion(customerUser.getRegion());
					record.setRecordMan(user.getOpenid());
					record.setFlag(0);
					record.setCityId(customerUser.getCityId());
					this.afterSalesRecordService.insert(record);
					return "200";
				}else{
					return "201";
				}
			}
		} catch (Exception e) {
			logger.error("删除店铺管理员异常"+e.getMessage());
		}	
		return "202";
	}	
	
	//扫码添加管理员
	@RequestMapping(value="/home/shop_addManage.htm")
	public ModelAndView manageQRCode(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/home/managecode");
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){
			//超级管理员
			Token token = this.tokenService.findByid(shopId);
			String timestamp = OrderUtil.GetTimestamp();
			String stringMD5 = MD5Util.getStringMD5(timestamp);
			if(null==token){
				Token token2 = new Token();
				token2.setShopId(shopId);
				token2.setTime(new Date());
				token2.setToken(stringMD5);
				this.tokenService.create(token2);
			}else{
				token.setToken(stringMD5);
				token.setTime(new Date());
				this.tokenService.update(token);
			}
			view.addObject("stringMD5", stringMD5);
			view.addObject("shopId", shopId);
			return view;
		}else{
			//管理员
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
			if(null!=admin){
				Token token = this.tokenService.findByid(shopId);
				String timestamp = OrderUtil.GetTimestamp();
				String stringMD5 = MD5Util.getStringMD5(timestamp);
				if(null==token){
					Token token2 = new Token();
					token2.setShopId(shopId);
					token2.setTime(new Date());
					token2.setToken(stringMD5);
					this.tokenService.create(token2);
				}else{
					token.setToken(stringMD5);
					token.setTime(new Date());
					this.tokenService.update(token);
				}
				view.addObject("stringMD5", stringMD5);
				view.addObject("shopId", shopId);
				return view;
			}
		}
		//啥都不是
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
	}
	
	//添加管理员二维码
	@RequestMapping(value="/shop_manageQRCode.htm")
	public void manageerweima(HttpServletResponse response,@RequestParam("shop_id")String shopId,@RequestParam("token")String token){
		String content = properties.ONLINE_URL+"convenient/shop_addManage.do?shop_id="+shopId+"&token="+token;
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
	@RequestMapping(value="/shop_addManage.do")
	public ModelAndView addManagedo(HttpServletRequest request,RedirectAttributes attr,@RequestParam("shop_id")String shopId,@RequestParam("token")String token){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		String state = shopId+","+token;
		if(null!=user){
			attr.addAttribute("state", state);
			return new ModelAndView("redirect:/convenient/shop_addmanage.htm"); 
		}
		String appid = properties.weixinAppid;
		String redirect_uri = properties.ONLINE_URL+"convenient/shop_addManageOauth.htm";
		String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
		String requestUrl = url.replace("APPID", appid).replace("REDIRECT_URL", urlEncode).replace("STATE", state);
		return new ModelAndView("redirect:"+requestUrl+"");
	}
	
	@RequestMapping("/shop_addManageOauth.htm")
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
		return new ModelAndView("redirect:/convenient/shop_addmanage.htm"); 
	}
	
	@RequestMapping(value="shop_addmanage.htm")
	public ModelAndView shop_addmanage(HttpServletRequest request){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		if(null!=user){
			ModelAndView view = new ModelAndView("shop/home/success");
			String state = request.getParameter("state");
			String[] split = state.split(",");
			String shopId = split[0];
			Integer shop_id = Integer.parseInt(shopId);
			CustomerUser customerUser = this.customerService.findById(shop_id);
			view.addObject("shop", customerUser);
			String str_token = split[1];
			
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			if(null!=puser){
				//超级管理员 提示已经是管理员
				view.addObject("sign", 1);
				view.addObject("shopId", shop_id);
			}else{
				ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(),shop_id);
				if(null==admin){
					//不是该店铺管理员，设置为该店铺管理员
					Token token = this.tokenService.findByIdAndTime(shop_id);
					if(null!=token && str_token.equals(token.getToken())){
						//设置为管理员
						ShopAdmin shopAdmin = new ShopAdmin();
						shopAdmin.setAdminId(user.getId());
						shopAdmin.setShopId(shop_id);
						shopAdmin.setCreatedTime(new Date());
						this.shopAdminService.save(shopAdmin);
						view.addObject("shopId", shop_id);
						view.addObject("sign", 3);
					}else{
						//已过期
						view.addObject("sign", 2);
					}
				}else{
					//提示是管理员
					view.addObject("sign", 1);
					view.addObject("shopId", shop_id);
				}
			}
			return view;
		}else{
			return new ModelAndView("redirect:/convenient/shop_addManage.do");
		}
	}
	
	
	//前台购买页面授权更改开始。。。
	
	//前台购买页面
	@RequestMapping(value="shop_item.htm",produces="text/html;charset=UTF-8")
	public ModelAndView item(HttpServletRequest request,RedirectAttributes attr,@RequestParam(value="cid",required=false)String cId,@RequestParam("shop_id")String shopid){
		String Agent = request.getHeader("User-Agent").toLowerCase();
		CustomerUser customerUser = this.customerService.findById(Integer.parseInt(shopid));
		List<String> asList = null ;
		if(null!=customerUser && !StringUtils.isEmpty(customerUser.getCheckStr())){
			asList = Arrays.asList(customerUser.getCheckStr().split(","));
		}
		if (Agent.indexOf("micromessenger") > 0 && asList.contains("1")) {// 是微信浏览器
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			String cid = "-1";
			if(null!=cId){
				cid = cId.toString();
			}
			if(null!=user){
				attr.addAttribute("shop_id", shopid);
				attr.addAttribute("cid", cid);
				return new ModelAndView("redirect:/convenient/shop_itemlist.htm"); 
			}
			
			//静默授权
			String state = shopid +","+cid;
			String appid = properties.weixinAppid;
			String redirect_uri = properties.ONLINE_URL+"convenient/shop_indexQuietOauth.htm";
			String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
			String requestUrl = quieturl.replace("APPID", appid).replace("REDIRECT_URL", urlEncode).replace("STATE", state);
			return new ModelAndView("redirect:"+requestUrl+""); 
	    }else if (Agent.indexOf("alipay") > 0 && asList.contains("2")) { //支付宝钱包
	    	ShopAliUser user = WXSessionHelper.getShopAliUser(request.getSession());
	    	String cid = "-1";
	    	if(null!=cId){
				cid=cId.toString();
			}
	    	if(null!=user){
	    		attr.addAttribute("shop_id", shopid);
	    		attr.addAttribute("cid", cid);
	    		return new ModelAndView("redirect:/convenient/ali/shop_itemlist.htm"); 
	    	}
	    	String state = shopid +","+cid;
	    	String appid = properties.ALI_APPID;
	    	String redirect_uri = properties.ONLINE_URL+"convenient/ali/shop_indexOauth.htm";
	    	String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
	    	String requestUrl = aliurl.replace("APPID", appid).replace("ENCODED_URL", urlEncode).replace("STATE", state);
	    	return new ModelAndView("redirect:"+requestUrl+"");  
		}else{
			ModelAndView view = new ModelAndView("shop/index/error");
			if(asList.contains("1") && asList.contains("2")){
				view.addObject("sign", "支付宝，微信");
			}else if (asList.contains("1")) {
				view.addObject("sign", "微信");
			}else if (asList.contains("2")){
				view.addObject("sign", "支付宝");
			}
			return view;
	    }
	}
	
	@RequestMapping(value="shop_items.htm",produces="text/html;charset=UTF-8")
	public ModelAndView items(HttpServletRequest request,RedirectAttributes attr){
		String Agent = request.getHeader("User-Agent").toLowerCase();
		if (Agent.indexOf("micromessenger") > 0) {// 是微信浏览器
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			String shopid="-1";
			String cid = "-1";
			if(null!=user){
				attr.addAttribute("shop_id", shopid);
				attr.addAttribute("cid", cid);
				return new ModelAndView("redirect:/convenient/shop_itemlist.htm"); 
			}
			
			//静默授权
			String state = shopid +","+cid;
			String appid = properties.weixinAppid;
			String redirect_uri = properties.ONLINE_URL+"convenient/shop_indexQuietOauth.htm";
			String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
			String requestUrl = quieturl.replace("APPID", appid).replace("REDIRECT_URL", urlEncode).replace("STATE", state);
			
			return new ModelAndView("redirect:"+requestUrl+"");
		}else if (Agent.indexOf("alipay") > 0) {
			ShopAliUser user = WXSessionHelper.getShopAliUser(request.getSession());
			//ShopAliUser user = this.shopAliUserService.findById(2);
			String shopid="-1";
			String cid = "-1";
			if(null!=user){
				attr.addAttribute("shop_id", shopid);
				attr.addAttribute("cid", cid);
				return new ModelAndView("redirect:/convenient/ali/shop_itemlist.htm"); 
			}
			String state = shopid +","+cid;
	    	String appid = properties.ALI_APPID;
	    	String redirect_uri = properties.ONLINE_URL+"convenient/ali/shop_indexOauth.htm";
	    	String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
	    	String requestUrl = aliurl.replace("APPID", appid).replace("ENCODED_URL", urlEncode).replace("STATE", state);
	    	return new ModelAndView("redirect:"+requestUrl+""); 
		}else{
			ModelAndView view1 = new ModelAndView("shop/index/error");
			
			view1.addObject("sign", "支付宝，微信");
			
			return view1;
	    }
	}
	
	@RequestMapping(value="/shop_indexQuietOauth.htm",produces="text/html;charset=UTF-8")
	public ModelAndView quietindexOauth(HttpServletRequest request,HttpServletResponse response,RedirectAttributes attr){				
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String[] split = state.split(",");
		String shopid = split[0];
		String cid = split[1];
		String openId ="";
		if (!"authdeny".equals(code)) {
			WeiXinOauth2Token weiXinOauth2Token = AdvancedUtil
					.getOauth2AccessToken(properties.weixinAppid,
							properties.weixinAppsecret, code);
			openId = weiXinOauth2Token.getOpenId();
			String accessToken = weiXinOauth2Token.getAccessToken();
			ShopUser wxUser = this.shopUserService.findByOpenId(openId);
			WXUser info = null;
			if(null==wxUser){
				info = AdvancedUtil.getWeixinUserInfo(accessToken, openId);
				if(null!=info && info.getNickName()!=null && info.getHeadImgurl()!=null){
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
					attr.addAttribute("shop_id", shopid);
					attr.addAttribute("cid", cid);
					return new ModelAndView("redirect:/convenient/shop_itemlist.htm"); 
				}
			}else{
				info = AdvancedUtil.getWeixinUserInfo(accessToken, openId);
				if(null!=info && info.getNickName()!=null && info.getHeadImgurl()!=null){
					wxUser.setCity(info.getCity());
					wxUser.setCountry(info.getCountry());
					wxUser.setHeadImgurl(info.getHeadImgurl());
					wxUser.setNickName(info.getNickName());
					wxUser.setProvince(info.getProvince());
					wxUser.setSex(info.getSex());
					this.shopUserService.update(wxUser);
					WXSessionHelper.setShopUser(request.getSession(), wxUser);
					attr.addAttribute("shop_id", shopid);
					attr.addAttribute("cid", cid);
					return new ModelAndView("redirect:/convenient/shop_itemlist.htm"); 
				}
			}
		}
		
		String appid = properties.weixinAppid;
		String redirect_uri = properties.ONLINE_URL+"convenient/shop_indexOauth.htm";
		String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
		String requestUrl = url.replace("APPID", appid).replace("REDIRECT_URL", urlEncode).replace("STATE", state);
		return new ModelAndView("redirect:"+requestUrl+""); 
	}
	
	@RequestMapping(value="/shop_indexOauth.htm",produces="text/html;charset=UTF-8")
	public ModelAndView indexOauth(HttpServletRequest request,HttpServletResponse response,RedirectAttributes attr){				
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		String[] split = state.split(",");
		String shopid = split[0];
		String cid = split[1];
		String openId ="";
		String token = "";
		WXUser info = null;
		if (!"authdeny".equals(code)) {
			WeiXinOauth2Token weiXinOauth2Token = AdvancedUtil
					.getOauth2AccessToken(properties.weixinAppid,
							properties.weixinAppsecret, code);
			openId = weiXinOauth2Token.getOpenId();
			AccessToken token2 = this.accessTokenService.findById(openId);
			if(null==token2){
				token2 = new AccessToken();
				token2.setId(openId);
				token2.setAccessToken(weiXinOauth2Token.getAccessToken());
				token2.setRefreshToken(weiXinOauth2Token.getRefeshToken());
				token2.setUpdatedTime(new Date());
				this.accessTokenService.insert(token2);
			}else{
				token2.setAccessToken(weiXinOauth2Token.getAccessToken());
				token2.setRefreshToken(weiXinOauth2Token.getRefeshToken());
				token2.setUpdatedTime(new Date());
				this.accessTokenService.update(token2);
			}
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
			info = AdvancedUtil.getWeixinUserInfo(token, openId);
			wxUser.setCity(info.getCity());
			wxUser.setCountry(info.getCountry());
			wxUser.setHeadImgurl(info.getHeadImgurl());
			wxUser.setNickName(info.getNickName());
			wxUser.setProvince(info.getProvince());
			wxUser.setSex(info.getSex());
			this.shopUserService.update(wxUser);
			WXSessionHelper.setShopUser(request.getSession(), wxUser);
		}
		attr.addAttribute("shop_id", shopid);
		attr.addAttribute("cid", cid);
		return new ModelAndView("redirect:/convenient/shop_itemlist.htm"); 
	}
	
	public void setdiscount(List<ShopItem> shopItems,double discount){
		if(!shopItems.isEmpty()){
			for (ShopItem shopItem : shopItems) {
				BigDecimal val = new BigDecimal(discount*shopItem.getSalePrice());
				val = val.divide(new BigDecimal(100));
				val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
				val = val.multiply(new BigDecimal(100));
				shopItem.setSalePrice(val.longValue());
			}
		}
	}

   public void setItemSaleHDMoney(List<ShopItem> shopItems,Integer itemid,Long money){
	   if(!shopItems.isEmpty()){
			for (ShopItem shopItem : shopItems) {
				if(shopItem.getItemId().equals(itemid)){
					shopItem.setSalePrice(money);
					shopItem.setIsPreferentail(1);
				}
			}
		}
   }

   public void setItemSaleHDDiscount(List<ShopItem> shopItems,Integer itemid,double discount){
	   if(!shopItems.isEmpty()){
			for (ShopItem shopItem : shopItems) {
				if(shopItem.getItemId().equals(itemid)){
					BigDecimal val = new BigDecimal(discount*shopItem.getSalePrice());
					val = val.divide(new BigDecimal(100));
					val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
					val = val.multiply(new BigDecimal(100));
					shopItem.setSalePrice(val.longValue());
					shopItem.setIsPreferentail(1);
				}
			}
		}
   }

	//前台购买页面授权更改结束。。。
	@RequestMapping(value="shop_itemlist.htm",produces="text/html;charset=UTF-8")
	public ModelAndView itemlist(HttpServletRequest request,RedirectAttributes attr,@RequestParam("shop_id")String id){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		if(null!=user){
			ModelAndView view = new ModelAndView("shop/index/items");
			view.addObject("shopuser", user);
			String cid = request.getParameter("cid");
			if(!StringUtils.isEmpty(cid)&&!cid.equals("-1")){
				Integer cat = Integer.parseInt(cid);
				ItemCategory category = this.itemCategoryService.findById(cat);
				view.addObject("cid", category.getCategoryName());
			}else{
				view.addObject("cid", cid);
			}
			if(id.equals("-1")){
				//点进来的,判断user的上次记录，有值，查找店铺信息返回
				if(user.getLastComeId()!=null){
					List<ShopItem> itemlist = shopItemService.findItemByShopId(user.getLastComeId());
					CustomerUser customerUser = this.customerService.findById(user.getLastComeId());
					try {
						DataCollect collect = new DataCollect();
						collect.setCityId(customerUser.getCityId());
						collect.setOpenid(user.getOpenid());
						collect.setShopId(customerUser.getId());
						collect.setType(1);
						collect.setCreatedTime(new Date());
						collect.setCode("D2");
						this.dataCollectService.save(collect);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					List<String> asList = null ;
					if(null!=customerUser && !StringUtils.isEmpty(customerUser.getCheckStr())){
						asList = Arrays.asList(customerUser.getCheckStr().split(","));
					}
					if(null!=customerUser && !asList.contains("1")){
						ModelAndView view1 = new ModelAndView("shop/index/error");
						view1.addObject("sign", "支付宝");
						return view1;
					}
					SeniorSet seniorSet = this.seniorSetService.findById(user.getLastComeId());
					Long money = this.tranConsumeService.findLossMoney(user.getLastComeId());
					if(null==money){
						money = 0l;
					}
					//gai
					//List<ShopItem> shopItems= new ArrayList<ShopItem>();
					//if(null!=seniorSet && null!=seniorSet.getIslayer() && 1==seniorSet.getIslayer()){
					List<ShopItem>	shopItems=this.shopItemService.findOftenBuyItemByBuyerIdAndShopIdAndSign(user.getId(),user.getLastComeId(),1);

					//}

					//jie
					if(null!=seniorSet && seniorSet.getMoney()!=null && seniorSet.getMoney()<=money){
						for (ShopItem shopItem : itemlist) {
							shopItem.setSalePrice(shopItem.getSourcingPrice());
						}
						if(!shopItems.isEmpty()){
							for (ShopItem shopItem : shopItems) {
								shopItem.setSalePrice(shopItem.getSourcingPrice());
							}
						}
						view.addObject("red", 1);
					}else if (null!=seniorSet && null!=seniorSet.getDiscount() && null !=seniorSet.getStart()) {
						try {
							
							int c = seniorSet.getStart();
							int d = 6;
							Date date2 = new Date();
							int hours = date2.getHours();
							if(c<=hours && hours<24 || 0<=hours && hours<6){
								Double discount = Double.valueOf(seniorSet.getDiscount().toString());
								for (ShopItem shopItem : itemlist) {
									BigDecimal val = new BigDecimal(discount*shopItem.getSourcingPrice());
									val = val.divide(new BigDecimal(100));
									val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
									val = val.multiply(new BigDecimal(100));
									shopItem.setSalePrice(val.longValue());
								}
								if(!shopItems.isEmpty()){
									for (ShopItem shopItem : shopItems) {
										BigDecimal val = new BigDecimal(discount*shopItem.getSourcingPrice());
										val = val.divide(new BigDecimal(100));
										val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
										val = val.multiply(new BigDecimal(100));
										shopItem.setSalePrice(val.longValue());
									}
								}
								
								view.addObject("red", 2);
								
							}
						} catch (Exception e) {
							logger.error("限时打折失败"+e.getMessage());
						}
					}else if(null!=seniorSet && null!=seniorSet.getFreeFee() && null !=seniorSet.getDayOrMonth() && 1==seniorSet.getType()){
						int dayOrMonth = seniorSet.getDayOrMonth();
						Date date = new Date();
						if(dayOrMonth==1){
							//每月
							Date query = DateUtil.getFirstDayOfMonth(date);
							Long freeFee = this.shopOrderService.findFreeFee(query,user.getId(),1);
							Long surplus = seniorSet.getFreeFee()-freeFee;
							if(surplus>0){
								view.addObject("free", 1);
								view.addObject("dayOrMonth", 1);
								view.addObject("freeFee", seniorSet.getFreeFee());
								view.addObject("surplus", surplus);
								view.addObject("usefee", freeFee);
								
							}
							
						}else if(dayOrMonth==2){
							String formatDate = DateUtil.formatDate(date, "yyyy-MM-dd 00:00:00");
							Date query = DateUtil.parseDateStr(formatDate, "yyyy-MM-dd hh:mm:ss");
							Long freeFee = this.shopOrderService.findFreeFee(query,user.getId(),1);
							Long surplus = seniorSet.getFreeFee()-freeFee;
							if(surplus>0){
								view.addObject("free", 1);
								view.addObject("dayOrMonth", 2);
								view.addObject("freeFee", seniorSet.getFreeFee());
								view.addObject("surplus", surplus);
								view.addObject("usefee", freeFee);
							}
						}
					}else if(null!=seniorSet && null!=seniorSet.getFreeFee() && null !=seniorSet.getDayOrMonth() && 3==seniorSet.getType()){
						ShopUserSubsidy subsidy = this.shopUserSubsidyService.findByUserIdAndShopId(user.getId(), user.getLastComeId());
						if(null!=subsidy){
							int dayOrMonth = seniorSet.getDayOrMonth();
							Date date = new Date();
							if(dayOrMonth==1){
								//每月
								Date query = DateUtil.getFirstDayOfMonth(date);
								Long freeFee = this.shopOrderService.findFreeFee(query,user.getId(),1);
								Long surplus = seniorSet.getFreeFee()-freeFee;
								if(surplus>0){
									view.addObject("free", 1);
									view.addObject("dayOrMonth", 1);
									view.addObject("freeFee", seniorSet.getFreeFee());
									view.addObject("surplus", surplus);
									view.addObject("usefee", freeFee);

								}

							}else if(dayOrMonth==2){
								String formatDate = DateUtil.formatDate(date, "yyyy-MM-dd 00:00:00");
								Date query = DateUtil.parseDateStr(formatDate, "yyyy-MM-dd hh:mm:ss");
								Long freeFee = this.shopOrderService.findFreeFee(query,user.getId(),1);
								Long surplus = seniorSet.getFreeFee()-freeFee;
								if(surplus>0){
									view.addObject("free", 1);
									view.addObject("dayOrMonth", 2);
									view.addObject("freeFee", seniorSet.getFreeFee());
									view.addObject("surplus", surplus);
									view.addObject("usefee", freeFee);
								}
							}
						}
					}
					


					boolean lwhd_flag = false;
					boolean xdp_flag = false;
					double xdp_disocount = 0;
					if(customerUser.getContractDate()!=null){
						List<NewCustomerActivity> lists = this.newCustomerActivityService.findByDateAndCityId(customerUser.getContractDate(),customerUser.getCityId());
						long diff = DateUtil.diff(customerUser.getContractDate(),new Date(), Calendar.DAY_OF_WEEK); 
						if(!lists.isEmpty() && diff <7){
							xdp_disocount = Double.valueOf(lists.get(0).getDiscount().toString());
							xdp_flag = true;
						}
					}

					boolean qdp_flag = false;
					double qdp_disocount = 0;
					List<ShopDiscountSetting> ShopDiscountSettings = this.shopDiscountSettingService.findByCustomerIdAndCityId(customerUser.getId(),customerUser.getCityId());
					if(ShopDiscountSettings.size()==1){
						qdp_flag = true;
						qdp_disocount = Double.valueOf(ShopDiscountSettings.get(0).getDiscount().toString());
					}

					if(xdp_flag && qdp_flag && xdp_disocount !=0 && qdp_disocount!=0){
						if(xdp_disocount<=qdp_disocount){
							this.setdiscount(itemlist, xdp_disocount);
							if(!shopItems.isEmpty()){
								this.setdiscount(shopItems, xdp_disocount);
							}
							lwhd_flag = true;
							view.addObject("new_flag", 1);
						}else{
							this.setdiscount(itemlist, qdp_disocount);
							if(!shopItems.isEmpty()){
								this.setdiscount(shopItems, qdp_disocount);
							}
							lwhd_flag = true;
							view.addObject("new_flag", 2);
						}
					}else if (xdp_flag && xdp_disocount !=0) {
						this.setdiscount(itemlist, xdp_disocount);
						if(!shopItems.isEmpty()){
							this.setdiscount(shopItems, xdp_disocount);
						}
						lwhd_flag = true;
						view.addObject("new_flag", 1);
					}else if (qdp_flag && qdp_disocount !=0) {
						this.setdiscount(itemlist, qdp_disocount);
						if(!shopItems.isEmpty()){
							this.setdiscount(shopItems, qdp_disocount);
						}
						lwhd_flag = true;
						view.addObject("new_flag", 2);
					}
					
					if(!lwhd_flag){
						List<ItemSalesPromotion> itemsales = this.itemSalesPromotionService.findStratedByCityId(customerUser.getCityId());
						if(!itemsales.isEmpty()){
							for (ItemSalesPromotion itemSalesPromotion : itemsales) {
								if(1==itemSalesPromotion.getShopType()){
									//所有店铺
									if(1==itemSalesPromotion.getType() && null!= itemSalesPromotion.getMoney()){
										//固定
										setItemSaleHDMoney(itemlist,itemSalesPromotion.getItemId(),itemSalesPromotion.getMoney());
										setItemSaleHDMoney(shopItems,itemSalesPromotion.getItemId(),itemSalesPromotion.getMoney());
									}else if (2==itemSalesPromotion.getType() && null!=itemSalesPromotion.getDiscount()) {
										//折扣
										double discount = Double.valueOf(itemSalesPromotion.getDiscount().toString());
										setItemSaleHDDiscount(itemlist,itemSalesPromotion.getItemId(),discount);
										setItemSaleHDDiscount(shopItems,itemSalesPromotion.getItemId(),discount);

									}
								}else if (2==itemSalesPromotion.getShopType()) {
									List<ItemSalesPromotion> lists = this.itemSalesPromotionService.findByItemSaleIdAndShopId(itemSalesPromotion.getId(),customerUser.getId());
									if(!lists.isEmpty()){
										if(1==itemSalesPromotion.getType() && null!= itemSalesPromotion.getMoney()){
											//固定
											setItemSaleHDMoney(itemlist,itemSalesPromotion.getItemId(),itemSalesPromotion.getMoney());
											setItemSaleHDMoney(shopItems,itemSalesPromotion.getItemId(),itemSalesPromotion.getMoney());
										}else if (2==itemSalesPromotion.getType() && null!=itemSalesPromotion.getDiscount()) {
											//折扣
											double discount = Double.valueOf(itemSalesPromotion.getDiscount().toString());
											setItemSaleHDDiscount(itemlist,itemSalesPromotion.getItemId(),discount);
											setItemSaleHDDiscount(shopItems,itemSalesPromotion.getItemId(),discount);
										}
									}
								}
							}
						}
					}
					//红包逻辑
					RedAccount account = this.redAccountService.findByUserIdAndType(user.getId(), 1);
					if(null!=account && account.getAccount()>0){
						view.addObject("red_flag", 1);
						view.addObject("redAccount", account.getAccount());
					}
					
					if(null!=seniorSet && null!=seniorSet.getShowPrice()){
						
						view.addObject("isshow", seniorSet.getShowPrice());
					}else{
						view.addObject("isshow", 1);
					}
					view.addObject("shopName", customerUser.getUserName());
					view.addObject("shop", customerUser);

					
					if(null!=seniorSet && null!=seniorSet.getIslayer() && 1==seniorSet.getIslayer()){
						if(customerUser.getId()==1385){
							view.setViewName("shop/index/waimai");
						}else{
							view.setViewName("shop/index/layerItem");
						}
						Map<Integer,List<ShopItem>> map = new TreeMap<Integer, List<ShopItem>>();
						for (ShopItem shopItem : itemlist) {
							if(map.containsKey(shopItem.getLayerId())){
								map.get(shopItem.getLayerId()).add(shopItem);
							}else{
								ArrayList<ShopItem> list = new ArrayList<ShopItem>();
								list.add(shopItem);
								map.put(shopItem.getLayerId(), list);
							}
						}
						ShopUser shopUser = this.shopUserService.findById(user.getId());
						if(null!=shopUser.getIsfirst() && 1==shopUser.getIsfirst()){
							
						}else{
							shopUser.setIsfirst(1);
							this.shopUserService.update(shopUser);
							WXSessionHelper.setShopUser(request.getSession(), shopUser);
							view.addObject("bar_flag", 1);
						}
						List<ShopLayer> shopLayers = this.shopLayerService.findByShopIdAndStatus(user.getLastComeId());
						view.addObject("shopItems",shopItems);
						view.addObject("map", map);
						view.addObject("shopLayers", shopLayers);
					}else{
						if(customerUser.getId()==1385){
							view.setViewName("shop/index/new_item");
						}
						Map<Integer,List<ShopItem>> map = new LinkedHashMap<Integer, List<ShopItem>>();
						for (ShopItem shopItem : itemlist) {
							if(map.containsKey(shopItem.getCategoryId())){
								map.get(shopItem.getCategoryId()).add(shopItem);
							}else{
								ArrayList<ShopItem> list = new ArrayList<ShopItem>();
								list.add(shopItem);
								map.put(shopItem.getCategoryId(), list);
							}
						}
						List<ItemCategory> cats = this.itemCategoryService.findCatOrderByScore();
						view.addObject("shopItems",shopItems);
						view.addObject("cats", cats);
						view.addObject("map", map);
					}

					PersonUser puser = this.userService.findByOpenId(user.getOpenid());
					if(null!=puser){
						view.addObject("isadmin", 1);
					}else{
						ShopAdmin shopAdmin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), user.getLastComeId());
						if(null!=shopAdmin){
							view.addObject("isadmin", 1);
						}
					}
					view.addObject("shopid", user.getLastComeId());
					return view;
				}else{
					//无值，提示扫码
					String timeStamp = OrderUtil.GetTimestamp();
					Random random = new Random();
					String nonceStr = DigestUtils.md5Hex(String.valueOf(random.nextInt(10000))).toUpperCase();
					logger.info(properties.weixinAppid+","+properties.weixinAppsecret);
					WholeToken wholeToken = this.wholeTokenService.findByIdOneHour(1);
					if(null==wholeToken){
						com.b2b.web.wx.util.Token token = CommonUtil.getToken(properties.weixinAppid, properties.weixinAppsecret);
						WholeToken t = this.wholeTokenService.findById(1);
						if(null==t){
							WholeToken token2 = new WholeToken();
							token2.setId(1);
							token2.setAccessToken(token.getAccessToken());
							token2.setCreatedTime(new Date());
							this.wholeTokenService.insert(token2);
						}else{
							t.setAccessToken(token.getAccessToken());
							t.setCreatedTime(new Date());
							this.wholeTokenService.update(t);
						}
						wholeToken = t;
					}
					logger.info("token"+wholeToken.getAccessToken());
					com.b2b.web.wx.util.Token ticket = CommonUtil.getJsapiTicket(wholeToken.getAccessToken());
					String string1 = "jsapi_ticket="+ticket.getAccessToken()+"&noncestr="+nonceStr+"&timestamp="+timeStamp+"&url="+request.getRequestURL();
					if(request.getQueryString()!=null) //判断请求参数是否为空
						string1+="?"+request.getQueryString();
					String sha1 = MD5Util.SHA1(string1);
					ModelAndView view2 = new ModelAndView("shop/index/scan");
					view2.addObject("appId", properties.weixinAppid);
					view2.addObject("timestamp", timeStamp);
					view2.addObject("nonceStr", nonceStr);
					view2.addObject("signature", sha1);
					return view2;
				}
			}else{
				//扫码进来的，直接根据id进店铺
				Integer shopid = Integer.valueOf(id);
				List<ShopItem> itemlist = shopItemService.findItemByShopId(Integer.valueOf(id));
				CustomerUser customerUser = this.customerService.findById(Integer.valueOf(id));
				SeniorSet seniorSet = this.seniorSetService.findById(Integer.valueOf(id));
				Long money = this.tranConsumeService.findLossMoney(Integer.valueOf(id));
				if(null==money){
					money = 0l;
				}
				//List<ShopItem> shopItems=new ArrayList<ShopItem>();
				//if(null!=seniorSet && null!=seniorSet.getIslayer() && 1==seniorSet.getIslayer()){
					List<ShopItem>	shopItems=this.shopItemService.findOftenBuyItemByBuyerIdAndShopIdAndSign(user.getId(),shopid,1);
				//}
				if(null!=seniorSet && seniorSet.getMoney()!=null && seniorSet.getMoney()<=money){
					for (ShopItem shopItem : itemlist) {
						shopItem.setSalePrice(shopItem.getSourcingPrice());
					}
					if(!shopItems.isEmpty()){
						for (ShopItem shopItem : shopItems) {
							shopItem.setSalePrice(shopItem.getSourcingPrice());
						}
					}
					view.addObject("red", 1);
				}else if (null!=seniorSet && null!=seniorSet.getDiscount() && null !=seniorSet.getStart()) {
					try {
						int c = seniorSet.getStart();
						int d = 6;
						Date date2 = new Date();
						int hours = date2.getHours();
						if(c<=hours && hours<24 || 0<=hours && hours<6){
							Double discount = Double.valueOf(seniorSet.getDiscount().toString());
							for (ShopItem shopItem : itemlist) {
								BigDecimal val = new BigDecimal(discount*shopItem.getSourcingPrice());
								val = val.divide(new BigDecimal(100));
								val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
								val = val.multiply(new BigDecimal(100));
								shopItem.setSalePrice(val.longValue());
							}
							if(!shopItems.isEmpty()){
								for (ShopItem shopItem : shopItems) {
									BigDecimal val = new BigDecimal(discount*shopItem.getSourcingPrice());
									val = val.divide(new BigDecimal(100));
									val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
									val = val.multiply(new BigDecimal(100));
									shopItem.setSalePrice(val.longValue());
								}
							}
							
							view.addObject("red", 2);
							
						}
					} catch (Exception e) {
						view.addObject("shopName", customerUser.getUserName());
						user.setLastComeId(Integer.valueOf(id));
						this.shopUserService.update(user);
						WXSessionHelper.setShopUser(request.getSession(), user);
						view.addObject("itemlist", itemlist);
						view.addObject("shopid", user.getLastComeId());
						return view;
					}
				}else if(null!=seniorSet && null!=seniorSet.getFreeFee() && null !=seniorSet.getDayOrMonth() && 1==seniorSet.getType()){
					int dayOrMonth = seniorSet.getDayOrMonth();
					Date date = new Date();
					if(dayOrMonth==1){
						//每月
						Date query = DateUtil.getFirstDayOfMonth(date);
						Long freeFee = this.shopOrderService.findFreeFee(query,user.getId(),1);
						Long surplus = seniorSet.getFreeFee()-freeFee;
						if(surplus>0){
							view.addObject("free", 1);
							view.addObject("dayOrMonth", 1);
							view.addObject("freeFee", seniorSet.getFreeFee());
							view.addObject("surplus", surplus);
							view.addObject("usefee", freeFee);
							
						}
						
					}else if(dayOrMonth==2){
						String formatDate = DateUtil.formatDate(date, "yyyy-MM-dd 00:00:00");
						Date query = DateUtil.parseDateStr(formatDate, "yyyy-MM-dd hh:mm:ss");
						Long freeFee = this.shopOrderService.findFreeFee(query,user.getId(),1);
						Long surplus = seniorSet.getFreeFee()-freeFee;
						if(surplus>0){
							view.addObject("free", 1);
							view.addObject("dayOrMonth", 2);
							view.addObject("freeFee", seniorSet.getFreeFee());
							view.addObject("surplus", surplus);
							view.addObject("usefee", freeFee);
						}
					}
				}else if(null!=seniorSet && null!=seniorSet.getFreeFee() && null !=seniorSet.getDayOrMonth() && 3==seniorSet.getType()){
					ShopUserSubsidy subsidy = this.shopUserSubsidyService.findByUserIdAndShopId(user.getId(),Integer.valueOf(id));
					if(null!=subsidy){
						int dayOrMonth = seniorSet.getDayOrMonth();
						Date date = new Date();
						if(dayOrMonth==1){
							//每月
							Date query = DateUtil.getFirstDayOfMonth(date);
							Long freeFee = this.shopOrderService.findFreeFee(query,user.getId(),1);
							Long surplus = seniorSet.getFreeFee()-freeFee;
							if(surplus>0){
								view.addObject("free", 1);
								view.addObject("dayOrMonth", 1);
								view.addObject("freeFee", seniorSet.getFreeFee());
								view.addObject("surplus", surplus);
								view.addObject("usefee", freeFee);

							}

						}else if(dayOrMonth==2){
							String formatDate = DateUtil.formatDate(date, "yyyy-MM-dd 00:00:00");
							Date query = DateUtil.parseDateStr(formatDate, "yyyy-MM-dd hh:mm:ss");
							Long freeFee = this.shopOrderService.findFreeFee(query,user.getId(),1);
							Long surplus = seniorSet.getFreeFee()-freeFee;
							if(surplus>0){
								view.addObject("free", 1);
								view.addObject("dayOrMonth", 2);
								view.addObject("freeFee", seniorSet.getFreeFee());
								view.addObject("surplus", surplus);
								view.addObject("usefee", freeFee);
							}
						}
					}
				}
				
				boolean lwhd_flag = false;
				boolean xdp_flag = false;
				double xdp_disocount = 0;
				if(customerUser.getContractDate()!=null){
					List<NewCustomerActivity> lists = this.newCustomerActivityService.findByDateAndCityId(customerUser.getContractDate(),customerUser.getCityId());
					long diff = DateUtil.diff(customerUser.getContractDate(),new Date(), Calendar.DAY_OF_WEEK); 
					if(!lists.isEmpty() && diff <7){
						xdp_disocount = Double.valueOf(lists.get(0).getDiscount().toString());
						xdp_flag = true;
					}
				}

				boolean qdp_flag = false;
				double qdp_disocount = 0;
				List<ShopDiscountSetting> ShopDiscountSettings = this.shopDiscountSettingService.findByCustomerIdAndCityId(customerUser.getId(),customerUser.getCityId());
				if(ShopDiscountSettings.size()==1){
					qdp_flag = true;
					qdp_disocount = Double.valueOf(ShopDiscountSettings.get(0).getDiscount().toString());
				}

				if(xdp_flag && qdp_flag && xdp_disocount !=0 && qdp_disocount!=0){
					if(xdp_disocount<=qdp_disocount){
						this.setdiscount(itemlist, xdp_disocount);
						if(!shopItems.isEmpty()){
							this.setdiscount(shopItems, xdp_disocount);
						}
						lwhd_flag = true;
						view.addObject("new_flag", 1);
					}else{
						this.setdiscount(itemlist, qdp_disocount);
						if(!shopItems.isEmpty()){
							this.setdiscount(shopItems, qdp_disocount);
						}
						lwhd_flag = true;
						view.addObject("new_flag", 2);
					}
				}else if (xdp_flag && xdp_disocount !=0) {
					this.setdiscount(itemlist, xdp_disocount);
					if(!shopItems.isEmpty()){
						this.setdiscount(shopItems, xdp_disocount);
					}
					lwhd_flag = true;
					view.addObject("new_flag", 1);
				}else if (qdp_flag && qdp_disocount !=0) {
					this.setdiscount(itemlist, qdp_disocount);
					if(!shopItems.isEmpty()){
						this.setdiscount(shopItems, qdp_disocount);
					}
					lwhd_flag = true;
					view.addObject("new_flag", 2);
				}

				if(!lwhd_flag){
					List<ItemSalesPromotion> itemsales = this.itemSalesPromotionService.findStratedByCityId(customerUser.getCityId());
					if(!itemsales.isEmpty()){
						for (ItemSalesPromotion itemSalesPromotion : itemsales) {
							if(1==itemSalesPromotion.getShopType()){
								//所有店铺
								if(1==itemSalesPromotion.getType() && null!= itemSalesPromotion.getMoney()){
									//固定
									setItemSaleHDMoney(itemlist,itemSalesPromotion.getItemId(),itemSalesPromotion.getMoney());
									setItemSaleHDMoney(shopItems,itemSalesPromotion.getItemId(),itemSalesPromotion.getMoney());
								}else if (2==itemSalesPromotion.getType() && null!=itemSalesPromotion.getDiscount()) {
									//折扣
									double discount = Double.valueOf(itemSalesPromotion.getDiscount().toString());
									setItemSaleHDDiscount(itemlist,itemSalesPromotion.getItemId(),discount);
									setItemSaleHDDiscount(shopItems,itemSalesPromotion.getItemId(),discount);

								}
							}else if (2==itemSalesPromotion.getShopType()) {
								List<ItemSalesPromotion> lists = this.itemSalesPromotionService.findByItemSaleIdAndShopId(itemSalesPromotion.getId(),customerUser.getId());
								if(!lists.isEmpty()){
									if(1==itemSalesPromotion.getType() && null!= itemSalesPromotion.getMoney()){
										//固定
										setItemSaleHDMoney(itemlist,itemSalesPromotion.getItemId(),itemSalesPromotion.getMoney());
										setItemSaleHDMoney(shopItems,itemSalesPromotion.getItemId(),itemSalesPromotion.getMoney());
									}else if (2==itemSalesPromotion.getType() && null!=itemSalesPromotion.getDiscount()) {
										//折扣
										double discount = Double.valueOf(itemSalesPromotion.getDiscount().toString());
										setItemSaleHDDiscount(itemlist,itemSalesPromotion.getItemId(),discount);
										setItemSaleHDDiscount(shopItems,itemSalesPromotion.getItemId(),discount);
									}
								}
							}
						}
					}
				}
				
				//红包逻辑
				RedAccount account = this.redAccountService.findByUserIdAndType(user.getId(), 1);
				if(null!=account && account.getAccount()>0){
					view.addObject("red_flag", 1);
					view.addObject("redAccount", account.getAccount());
				}
				
				if(null!=seniorSet && null!=seniorSet.getShowPrice()){
					
					view.addObject("isshow", seniorSet.getShowPrice());
				}else{
					view.addObject("isshow", 1);
				}
				view.addObject("shopName", customerUser.getUserName());
				view.addObject("shop", customerUser);
				user.setLastComeId(Integer.valueOf(id));
				this.shopUserService.update(user);
				WXSessionHelper.setShopUser(request.getSession(), user);

				

				if(null!=seniorSet && null!=seniorSet.getIslayer() && 1==seniorSet.getIslayer()){
					if(customerUser.getId()==1385){
						view.setViewName("shop/index/waimai");
					}else{
						view.setViewName("shop/index/layerItem");
					}
					Map<Integer,List<ShopItem>> map = new TreeMap<Integer, List<ShopItem>>();
					for (ShopItem shopItem : itemlist) {
						if(map.containsKey(shopItem.getLayerId())){
							map.get(shopItem.getLayerId()).add(shopItem);
						}else{
							ArrayList<ShopItem> list = new ArrayList<ShopItem>();
							list.add(shopItem);
							map.put(shopItem.getLayerId(), list);
						}
					}
					ShopUser shopUser = this.shopUserService.findById(user.getId());
					if(null!=shopUser.getIsfirst() && 1==shopUser.getIsfirst()){
						
					}else{
						shopUser.setIsfirst(1);
						this.shopUserService.update(shopUser);
						WXSessionHelper.setShopUser(request.getSession(), shopUser);
						view.addObject("bar_flag", 1);
					}
					List<ShopLayer> shopLayers = this.shopLayerService.findByShopIdAndStatus(shopid);
					view.addObject("shopItems",shopItems);
					view.addObject("map", map);
					view.addObject("shopLayers", shopLayers);
				}else{
					if(customerUser.getId()==1385){
						view.setViewName("shop/index/new_item");
					}
					Map<Integer,List<ShopItem>> map = new LinkedHashMap<Integer, List<ShopItem>>();
					for (ShopItem shopItem : itemlist) {
						if(map.containsKey(shopItem.getCategoryId())){
							map.get(shopItem.getCategoryId()).add(shopItem);
						}else{
							ArrayList<ShopItem> list = new ArrayList<ShopItem>();
							list.add(shopItem);
							map.put(shopItem.getCategoryId(), list);
						}
					}
					List<ItemCategory> cats = this.itemCategoryService.findCatOrderByScore();
					view.addObject("shopItems",shopItems);
					view.addObject("cats", cats);
					view.addObject("map", map);

				}
				view.addObject("shopid", Integer.valueOf(id));
				PersonUser puser = this.userService.findByOpenId(user.getOpenid());
				if(null!=puser){
					view.addObject("isadmin", 1);
				}else{
					ShopAdmin shopAdmin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), Integer.valueOf(id));
					if(null!=shopAdmin){
						view.addObject("isadmin", 1);
					}
				}
				return view;
			}
		}else{
			attr.addAttribute("shop_id", id);
			return new ModelAndView("redirect:/convenient/shop_item.htm"); 
		}
	}
	
	@RequestMapping("shop_baritem.json")
	@ResponseBody
	public LWResult barCodeItem(@RequestParam("shop_id")Integer shopId,@RequestParam("barcode")String barcode){
		try {
			CustomerUser user = this.customerService.findById(shopId);
			ShopItem item = this.shopItemService.findItemByShopIdAndBarCode(shopId,barcode);
			if(null!=item){
				SeniorSet seniorSet = this.seniorSetService.findById(shopId);
				Long money = this.tranConsumeService.findLossMoney(shopId);
				if(null==money){
					money = 0l;
				}
				if(null!=seniorSet && seniorSet.getMoney()!=null && seniorSet.getMoney()<=money){
					item.setSalePrice(item.getSourcingPrice());
				}else if (null!=seniorSet && null!=seniorSet.getDiscount() && null !=seniorSet.getStart()) {
					try {
						int c = seniorSet.getStart();
						int d = 6;
						Date date2 = new Date();
						int hours = date2.getHours();
						if(c<=hours && hours<24 || 0<=hours && hours<6){
							Double discount = Double.valueOf(seniorSet.getDiscount().toString());
								BigDecimal val = new BigDecimal(discount*item.getSourcingPrice());
								val = val.divide(new BigDecimal(100));
								val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
								val = val.multiply(new BigDecimal(100));
								item.setSalePrice(val.longValue());

						}
					} catch (Exception e) {

					}
				}
				boolean lwhd_flag = false;
				boolean xdp_flag = false;
				double xdp_disocount = 0;
				if(user.getContractDate()!=null){
					List<NewCustomerActivity> lists = this.newCustomerActivityService.findByDateAndCityId(user.getContractDate(),user.getCityId());
					long diff = DateUtil.diff(user.getContractDate(),new Date(), Calendar.DAY_OF_WEEK);
					if(!lists.isEmpty() && diff <7){
						xdp_disocount = Double.valueOf(lists.get(0).getDiscount().toString());
						xdp_flag = true;
					}
				}

				boolean qdp_flag = false;
				double qdp_disocount = 0;
				List<ShopDiscountSetting> ShopDiscountSettings = this.shopDiscountSettingService.findByCustomerIdAndCityId(user.getId(),user.getCityId());
				if(ShopDiscountSettings.size()==1){
					qdp_flag = true;
					qdp_disocount = Double.valueOf(ShopDiscountSettings.get(0).getDiscount().toString());
				}

				if(xdp_flag && qdp_flag && xdp_disocount !=0 && qdp_disocount!=0){
					if(xdp_disocount<=qdp_disocount){
						BigDecimal val = new BigDecimal(xdp_disocount*item.getSalePrice());
						val = val.divide(new BigDecimal(100));
						val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
						val = val.multiply(new BigDecimal(100));
						item.setSalePrice(val.longValue());
						lwhd_flag = true;
					}else{
						BigDecimal val = new BigDecimal(qdp_disocount*item.getSalePrice());
						val = val.divide(new BigDecimal(100));
						val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
						val = val.multiply(new BigDecimal(100));
						item.setSalePrice(val.longValue());
						lwhd_flag = true;
					}
				}else if (xdp_flag && xdp_disocount !=0) {
					BigDecimal val = new BigDecimal(xdp_disocount*item.getSalePrice());
					val = val.divide(new BigDecimal(100));
					val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
					val = val.multiply(new BigDecimal(100));
					item.setSalePrice(val.longValue());
					lwhd_flag = true;
				}else if (qdp_flag && qdp_disocount !=0) {
					BigDecimal val = new BigDecimal(qdp_disocount*item.getSalePrice());
					val = val.divide(new BigDecimal(100));
					val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
					val = val.multiply(new BigDecimal(100));
					item.setSalePrice(val.longValue());
					lwhd_flag = true;
				}

				if(!lwhd_flag){
					List<ItemSalesPromotion> itemsales = this.itemSalesPromotionService.findStratedByCityId(user.getCityId());
					if(!itemsales.isEmpty()){
						for (ItemSalesPromotion itemSalesPromotion : itemsales) {
							if(1==itemSalesPromotion.getShopType()){
								//所有店铺
								if(1==itemSalesPromotion.getType() && null!= itemSalesPromotion.getMoney()){
									//固定
									if(item.getItemId().equals(itemSalesPromotion.getItemId())){
										item.setSalePrice(itemSalesPromotion.getMoney());
										item.setIsPreferentail(1);
									}

								}else if (2==itemSalesPromotion.getType() && null!=itemSalesPromotion.getDiscount()) {
									//折扣
									double discount = Double.valueOf(itemSalesPromotion.getDiscount().toString());
									if(item.getItemId().equals(itemSalesPromotion.getItemId())){
										BigDecimal val = new BigDecimal(discount*item.getSalePrice());
										val = val.divide(new BigDecimal(100));
										val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
										val = val.multiply(new BigDecimal(100));
										item.setSalePrice(val.longValue());
										item.setIsPreferentail(1);
									}
								}
							}else if (2==itemSalesPromotion.getShopType()) {
								List<ItemSalesPromotion> lists = this.itemSalesPromotionService.findByItemSaleIdAndShopId(itemSalesPromotion.getId(),user.getId());
								if(!lists.isEmpty()){
									if(1==itemSalesPromotion.getType() && null!= itemSalesPromotion.getMoney()){
										//固定
										if(item.getItemId().equals(itemSalesPromotion.getItemId())){
											item.setSalePrice(itemSalesPromotion.getMoney());
											item.setIsPreferentail(1);
										}
									}else if (2==itemSalesPromotion.getType() && null!=itemSalesPromotion.getDiscount()) {
										//折扣
										double discount = Double.valueOf(itemSalesPromotion.getDiscount().toString());
										if(item.getItemId().equals(itemSalesPromotion.getItemId())){
											BigDecimal val = new BigDecimal(discount*item.getSalePrice());
											val = val.divide(new BigDecimal(100));
											val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
											val = val.multiply(new BigDecimal(100));
											item.setSalePrice(val.longValue());
											item.setIsPreferentail(1);
										}
									}
								}
							}
						}
					}
				}

				return LWResult.ok(item);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return LWResult.build(201, "无货");
	}
	
	@RequestMapping("shop_refund.do")
	@ResponseBody
	public String dorefund(HttpServletRequest request,@RequestParam("orderNo")String id){
		try {
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			if(null!=puser){
				//超级管理员,
				ShopOrder order = this.shopOrderService.findById(id);
				CustomerUser customerUser = this.customerService.findById(order.getShopId());
				Integer cityId = null;
				if(null !=customerUser){
					cityId = customerUser.getCityId();
				}
				String out_refund_no = OrderNumberGenerator.buildOrderNo(new Date(), 12);
				if(order.getTotalPrice()==0){
					this.shopOrderService.changeRefundStatus(order,user.getId());
					return "200";
				}
				if(order.getSign()==1){
					String msg = refund(out_refund_no, order.getId(), order.getTotalPrice().toString(), order.getTotalPrice().toString());
					if(null!=msg){
						Map<String, Object> map = RequestHandler.confirmWeiXinReturnResponse(msg);
						String result_code = map.get("result_code").toString();
						if(result_code.equals("SUCCESS")){
							this.shopOrderService.changeRefundStatus(order,user.getId());
							this.saveLog(request.getSession(), order, "便利店订单退款，操作人："+user.getId()+"退款人："+order.getBuyerId(),order.getShopId(),user.getNickName(),cityId);
							return "200";
						}
					}
					return "201";
				}else if (order.getSign()==2) {
					double a = order.getTotalPrice();
					Double b = a/100;
					String alirefund = alirefund(order.getId(), b.toString());
					if(alirefund.equals("200")){
						this.shopOrderService.changeRefundStatus(order,user.getId());
						this.saveLog(request.getSession(), order, "便利店订单退款，操作人："+user.getId()+"退款人："+order.getBuyerId(),order.getShopId(),user.getNickName(),cityId);
						return "200";
					}
					return "201";
				}else{
					return "203";
				}
			}else{
				ShopAdmin shopAdmin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), Integer.valueOf(id));
				if(null!=shopAdmin){
					ShopOrder order = this.shopOrderService.findById(id);
					CustomerUser customerUser = this.customerService.findById(order.getShopId());
					Integer cityId = null;
					if(null !=customerUser){
						cityId = customerUser.getCityId();
					}
					if(order.getShopId().equals(user.getCustomerUserId())){
						String out_refund_no = OrderNumberGenerator.buildOrderNo(new Date(), 12);
						if(order.getTotalPrice()==0){
							this.shopOrderService.changeRefundStatus(order,user.getId());
							return "200";
						}
						if (order.getSign()==1) {
							String msg = refund(out_refund_no, order.getId(), order.getTotalPrice().toString(), order.getTotalPrice().toString());
							if(null!=msg){
								Map<String, Object> map = RequestHandler.confirmWeiXinReturnResponse(msg);
								String result_code = map.get("result_code").toString();
								if(result_code.equals("SUCCESS")){
									this.shopOrderService.changeRefundStatus(order,user.getId());
									this.saveLog(request.getSession(), order, "便利店订单退款，操作人："+user.getId()+"退款人："+order.getBuyerId(),order.getShopId(),user.getNickName(),cityId);
									return "200";
								}
							}
							return "201";
						}else if (order.getSign()==2) {
							double a = order.getTotalPrice();
							Double b = a/100;
							String alirefund = alirefund(order.getId(), b.toString());
							if(alirefund.equals("200")){
								this.shopOrderService.changeRefundStatus(order,user.getId());
								this.saveLog(request.getSession(), order, "便利店订单退款，操作人："+user.getId()+"退款人："+order.getBuyerId(),order.getShopId(),user.getNickName(),cityId);
								return "200";
							}
							return "201";
						}else{
							return "203";
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("退款失败"+e.getMessage());
		}	
		return "203";
	}
	
	public String alirefund(String orderNo,String money){
		try {
			AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",properties.ALI_APPID,properties.ALI_PRIVATE_KEY,"json","utf-8",properties.ALIPAY_PUBLIC_KEY,"RSA2");
			AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
			request.setBizContent("{" +"\"out_trade_no\":\""+orderNo+"\"," +"\"refund_amount\":"+money+"" +"}");
			AlipayTradeRefundResponse response = alipayClient.execute(request);
			if(response.isSuccess()){
				logger.info("退款成功"+response.getBody());
				return "200";
			}
		} catch (AlipayApiException e) {
			e.printStackTrace();
		}
		return "201";
	}
	
	//退款
	public String refund(String refund_no,String out_trade_no,String total_fee,String refund_fee){
		String out_refund_no = refund_no;// 退款单号
		String nonce_str = String.valueOf(System.currentTimeMillis());// 随机字符串
		String appid = properties.weixinAppid; //微信公众号apid
		String appsecret = properties.weixinAppsecret; //微信公众号appsecret
		String mch_id = properties.WX_MCH_ID;  //微信商户id
		String op_user_id = properties.WX_MCH_ID;//就是MCHID
		String partnerkey = properties.WX_PAY_PARTNERKRY;//商户平台上的那个KEY
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		packageParams.put("out_trade_no", out_trade_no);
		packageParams.put("out_refund_no", out_refund_no);
		packageParams.put("total_fee", total_fee);
		packageParams.put("refund_fee", refund_fee);
		packageParams.put("op_user_id", op_user_id);

		RequestHandler reqHandler = new RequestHandler(
				null, null);
		reqHandler.init(appid, appsecret, partnerkey);

		String sign = reqHandler.createSign(packageParams);
		logger.warn("--------------------------"+sign);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<nonce_str>" + nonce_str
				+ "</nonce_str>" + "<sign><![CDATA[" + sign + "]]></sign>"
				+ "<out_trade_no>" + out_trade_no + "</out_trade_no>"
				+ "<out_refund_no>" + out_refund_no + "</out_refund_no>"
				+ "<total_fee>" + total_fee + "</total_fee>"
				+ "<refund_fee>" + refund_fee + "</refund_fee>"
				+ "<op_user_id>" + op_user_id + "</op_user_id>" + "</xml>";
		String createOrderURL = "https://api.mch.weixin.qq.com/secapi/pay/refund";
		try {
			logger.warn("--------------------------》退款执行");
			String s= ClientCustomSSL.doRefund(createOrderURL, xml);
			logger.warn("33333333333333333"+s);
			return s;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 微信公众号调用微信支付
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/shop_pay.do")
	@ResponseBody
	public LWResult pay(HttpServletRequest request,@RequestParam("shop_id")Integer shopId,@RequestParam("data")String data,@RequestParam("free_flag")Integer free_flag,@RequestParam("red_flag")Integer red_flag) {
		try {
			Gson gson = new Gson(); 
			List<ShopItem> datas = gson.fromJson(data,  new TypeToken<List<ShopItem>>() {}.getType());
			if(datas.isEmpty()){
				return LWResult.build(201, "请选择商品");
			}
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			if(null==user){
				return LWResult.build(202, "创建订单失败");
			}
			String orderNo = OrderUtil.GetOrderNumber();
			ShopOrder order = new ShopOrder();
			order.setBuyerId(user.getId());
			order.setBuyerName(user.getNickName());
			order.setCreatedTime(new Date());
			order.setId("BLD"+orderNo);
			order.setShopId(shopId);
			order.setSign(1);
			order.setImgPath(user.getHeadImgurl());
			CustomerUser customerUser = this.customerService.findById(shopId);
			order.setCityId(customerUser.getCityId());
			order.setShopName(customerUser.getUserName());
			order.setStatus(0);
			SeniorSet seniorSet = this.seniorSetService.findById(user.getLastComeId());
			Long lossmoney = this.tranConsumeService.findLossMoney(user.getLastComeId());
			if(null==lossmoney){
				lossmoney = 0l;
			}
			List<ShopOrderItem> orderItemList = new ArrayList<ShopOrderItem>();
			Long money = 0l;
			Long actual = 0l;
			if(null!=seniorSet && seniorSet.getMoney()!=null && seniorSet.getMoney()<=lossmoney){
				for (ShopItem item : datas) {
					ShopItem shopItem = this.shopItemService.findItemById(item.getId());
					ShopOrderItem shopOrderItem = new ShopOrderItem();
					shopOrderItem.setItemId(item.getId());
					shopOrderItem.setItemName(shopItem.getName());
					shopOrderItem.setNum(item.getNum());
					shopOrderItem.setOrderId(order.getId());
					shopOrderItem.setPrice(shopItem.getSourcingPrice());
					shopOrderItem.setActualPrice(shopItem.getSourcingPrice());
					money+=shopItem.getSourcingPrice()*item.getNum();
					actual+=shopItem.getSourcingPrice()*item.getNum();
					orderItemList.add(shopOrderItem);
				}
			}else if (null!=seniorSet && null!=seniorSet.getDiscount() && null !=seniorSet.getStart()) {
				int c = seniorSet.getStart();
				int d = 6;
				Date date2 = new Date();
				int hours = date2.getHours();
				if(c<=hours && hours<24 || 0<=hours && hours<6){
					Double discount = Double.valueOf(seniorSet.getDiscount().toString());
					
					for (ShopItem item : datas) {
						ShopItem shopItem = this.shopItemService.findItemById(item.getId());
						BigDecimal val = new BigDecimal(discount*shopItem.getSourcingPrice());
						val = val.divide(new BigDecimal(100));
						val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
						val = val.multiply(new BigDecimal(100));
						ShopOrderItem shopOrderItem = new ShopOrderItem();
						shopOrderItem.setItemId(item.getId());
						shopOrderItem.setItemName(shopItem.getName());
						shopOrderItem.setNum(item.getNum());
						shopOrderItem.setOrderId(order.getId());
						shopOrderItem.setPrice(val.longValue());
						shopOrderItem.setActualPrice(shopItem.getSourcingPrice());
						money+=val.longValue()*item.getNum();
						actual+=shopItem.getSourcingPrice()*item.getNum();
						orderItemList.add(shopOrderItem);
					}
				}else{
					for (ShopItem item : datas) {
						ShopItem shopItem = this.shopItemService.findItemById(item.getId());
						ShopOrderItem shopOrderItem = new ShopOrderItem();
						shopOrderItem.setItemId(item.getId());
						shopOrderItem.setItemName(shopItem.getName());
						shopOrderItem.setNum(item.getNum());
						shopOrderItem.setOrderId(order.getId());
						shopOrderItem.setPrice(shopItem.getSalePrice());
						shopOrderItem.setActualPrice(shopItem.getSourcingPrice());
						money+=shopItem.getSalePrice()*item.getNum();
						actual+=shopItem.getSourcingPrice()*item.getNum();
						orderItemList.add(shopOrderItem);
					}
				}
			}else{
				
				for (ShopItem item : datas) {
					ShopItem shopItem = this.shopItemService.findItemById(item.getId());
					ShopOrderItem shopOrderItem = new ShopOrderItem();
					shopOrderItem.setItemId(item.getId());
					shopOrderItem.setItemName(shopItem.getName());
					shopOrderItem.setNum(item.getNum());
					shopOrderItem.setOrderId(order.getId());
					shopOrderItem.setPrice(shopItem.getSalePrice());
					shopOrderItem.setActualPrice(shopItem.getSourcingPrice());
					money+=shopItem.getSalePrice()*item.getNum();
					actual+=shopItem.getSourcingPrice()*item.getNum();
					orderItemList.add(shopOrderItem);
				}
			}
			Long lw_fee_one = 0l;
			boolean lwhd_flag = false;
			boolean xdp_flag = false;
			double xdp_disocount = 0;
			if(customerUser.getContractDate()!=null){
				List<NewCustomerActivity> lists = this.newCustomerActivityService.findByDateAndCityId(customerUser.getContractDate(),customerUser.getCityId());
				long diff = DateUtil.diff(customerUser.getContractDate(),new Date(), Calendar.DAY_OF_WEEK); 
				if(!lists.isEmpty() && diff <7){
					xdp_disocount = Double.valueOf(lists.get(0).getDiscount().toString());
					xdp_flag = true;
				}
			}

			boolean qdp_flag = false;
			double qdp_disocount = 0;
			List<ShopDiscountSetting> ShopDiscountSettings = this.shopDiscountSettingService.findByCustomerIdAndCityId(customerUser.getId(),customerUser.getCityId());
			if(ShopDiscountSettings.size()==1){
				qdp_flag = true;
				qdp_disocount = Double.valueOf(ShopDiscountSettings.get(0).getDiscount().toString());
			}

			if(xdp_flag && qdp_flag && xdp_disocount !=0 && qdp_disocount!=0){
				if(xdp_disocount<=qdp_disocount){
					long temp_fee = money;
					money=0l;
					for (ShopOrderItem shopOrderItem : orderItemList) {
						BigDecimal val = new BigDecimal(xdp_disocount*shopOrderItem.getPrice());
						val = val.divide(new BigDecimal(100));
						val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
						val = val.multiply(new BigDecimal(100));
						shopOrderItem.setPrice(val.longValue());
						money +=shopOrderItem.getPrice()*shopOrderItem.getNum();
					}
					lwhd_flag = true;
					lw_fee_one = temp_fee - money;
					order.setLwType(1);
				}else{
					long temp_fee = money;
					money=0l;
					for (ShopOrderItem shopOrderItem : orderItemList) {
						BigDecimal val = new BigDecimal(qdp_disocount*shopOrderItem.getPrice());
						val = val.divide(new BigDecimal(100));
						val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
						val = val.multiply(new BigDecimal(100));
						shopOrderItem.setPrice(val.longValue());
						money +=shopOrderItem.getPrice()*shopOrderItem.getNum();
					}
					lwhd_flag = true;
					lw_fee_one = temp_fee - money;
					order.setLwType(3);
					order.setHdId(ShopDiscountSettings.get(0).getId());
				}
			}else if (xdp_flag && xdp_disocount !=0) {
				long temp_fee = money;
				money=0l;
				for (ShopOrderItem shopOrderItem : orderItemList) {
					BigDecimal val = new BigDecimal(xdp_disocount*shopOrderItem.getPrice());
					val = val.divide(new BigDecimal(100));
					val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
					val = val.multiply(new BigDecimal(100));
					shopOrderItem.setPrice(val.longValue());
					money +=shopOrderItem.getPrice()*shopOrderItem.getNum();
				}
				lwhd_flag = true;
				lw_fee_one = temp_fee - money;
				order.setLwType(1);
			}else if (qdp_flag && qdp_disocount !=0) {
				long temp_fee = money;
				money=0l;
				for (ShopOrderItem shopOrderItem : orderItemList) {
					BigDecimal val = new BigDecimal(qdp_disocount*shopOrderItem.getPrice());
					val = val.divide(new BigDecimal(100));
					val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
					val = val.multiply(new BigDecimal(100));
					shopOrderItem.setPrice(val.longValue());
					money +=shopOrderItem.getPrice()*shopOrderItem.getNum();
				}
				lwhd_flag = true;
				lw_fee_one = temp_fee - money;
				order.setLwType(3);
				order.setHdId(ShopDiscountSettings.get(0).getId());
			}

			if(!lwhd_flag){
				List<ItemSalesPromotion> itemsales = this.itemSalesPromotionService.findStratedByCityIdAndShopId(customerUser.getCityId(),shopId);
				if(!itemsales.isEmpty()){
					lw_fee_one = 0l;
					boolean cx_falg = false;
					for (ItemSalesPromotion itemSalesPromotion : itemsales) {
						if(1==itemSalesPromotion.getShopType()){
							//所有店铺
							if(1==itemSalesPromotion.getType() && null!= itemSalesPromotion.getMoney()){
								//固定
								for (ShopOrderItem shopOrderItem : orderItemList) {
									if(shopOrderItem.getItemId().equals(itemSalesPromotion.getShopItemId())){
										shopOrderItem.setLwyhFee(shopOrderItem.getPrice()-itemSalesPromotion.getMoney());
										lw_fee_one +=(shopOrderItem.getPrice()-itemSalesPromotion.getMoney())*shopOrderItem.getNum();
										shopOrderItem.setPrice(itemSalesPromotion.getMoney());
										shopOrderItem.setHdId(itemSalesPromotion.getId());
										cx_falg = true;
									}
								}
							}else if (2==itemSalesPromotion.getType() && null!=itemSalesPromotion.getDiscount()) {
								//折扣
								double discount = Double.valueOf(itemSalesPromotion.getDiscount().toString());

								for (ShopOrderItem shopOrderItem : orderItemList) {
									if(shopOrderItem.getItemId().equals(itemSalesPromotion.getShopItemId())){
											BigDecimal val = new BigDecimal(discount*shopOrderItem.getPrice());
											val = val.divide(new BigDecimal(100));
											val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
											val = val.multiply(new BigDecimal(100));
											lw_fee_one +=(shopOrderItem.getPrice()-val.longValue())*shopOrderItem.getNum();
											shopOrderItem.setLwyhFee(shopOrderItem.getPrice()-val.longValue());
											shopOrderItem.setPrice(val.longValue());
											shopOrderItem.setHdId(itemSalesPromotion.getId());
											cx_falg = true;
									}
								}
							}
						}else if (2==itemSalesPromotion.getShopType()) {
							List<ItemSalesPromotion> lists = this.itemSalesPromotionService.findByItemSaleIdAndShopId(itemSalesPromotion.getId(),customerUser.getId());
							if(!lists.isEmpty()){
								if(1==itemSalesPromotion.getType() && null!= itemSalesPromotion.getMoney()){
									//固定
									for (ShopOrderItem shopOrderItem : orderItemList) {
										if(shopOrderItem.getItemId().equals(itemSalesPromotion.getShopItemId())){
											lw_fee_one +=(shopOrderItem.getPrice()-itemSalesPromotion.getMoney())*shopOrderItem.getNum();
											shopOrderItem.setLwyhFee(shopOrderItem.getPrice()-itemSalesPromotion.getMoney());
											shopOrderItem.setPrice(itemSalesPromotion.getMoney());
											shopOrderItem.setHdId(itemSalesPromotion.getId());
											cx_falg = true;
										}
									}
								}else if (2==itemSalesPromotion.getType() && null!=itemSalesPromotion.getDiscount()) {
									//折扣
									double discount = Double.valueOf(itemSalesPromotion.getDiscount().toString());

									for (ShopOrderItem shopOrderItem : orderItemList) {
										if(shopOrderItem.getItemId().equals(itemSalesPromotion.getShopItemId())){
												BigDecimal val = new BigDecimal(discount*shopOrderItem.getPrice());
												val = val.divide(new BigDecimal(100));
												val=val.setScale(1, BigDecimal.ROUND_HALF_UP);
												val = val.multiply(new BigDecimal(100));
												lw_fee_one +=(shopOrderItem.getPrice()-val.longValue())*shopOrderItem.getNum();
												shopOrderItem.setLwyhFee(shopOrderItem.getPrice()-val.longValue());
												shopOrderItem.setPrice(val.longValue());
												shopOrderItem.setHdId(itemSalesPromotion.getId());
												cx_falg = true;
										}
									}
								}
							}
						}
					}
					money = money - lw_fee_one;
					if(cx_falg){
						order.setLwType(2);
					}
				}
			}
			
			if(free_flag==1){
				Long sheng = 0l;
				int dayOrMonth = seniorSet.getDayOrMonth();
				Date date = new Date();
				if(dayOrMonth==1){
					//每月
					Date query = DateUtil.getFirstDayOfMonth(date);
					Long freeFee = this.shopOrderService.findFreeFee(query,user.getId(),1);
					sheng = seniorSet.getFreeFee()-freeFee;
				}else if(dayOrMonth==2){
					String formatDate = DateUtil.formatDate(date, "yyyy-MM-dd 00:00:00");
					Date query = DateUtil.parseDateStr(formatDate, "yyyy-MM-dd hh:mm:ss");
					Long freeFee = this.shopOrderService.findFreeFee(query,user.getId(),1);
					sheng = seniorSet.getFreeFee()-freeFee;
				}
				//使用补贴
				if(money<=sheng){
					order.setTotalPrice(0l);
					order.setFreePrice(money);
					order.setStatus(1);
				}else if (0<sheng && sheng<money) {
					order.setTotalPrice(money-sheng);
					order.setFreePrice(sheng);
				}else{
					order.setTotalPrice(money);
					order.setFreePrice(0l);
				}
			}else{
				//不使用补贴
				order.setTotalPrice(money);
				order.setFreePrice(0l);
			}
			
			if(red_flag==1){
				RedAccount account = this.redAccountService.findByUserIdAndType(user.getId(), 1);
				if(null!=account && account.getAccount()>0){
					Long red_account = account.getAccount();
					Long red_totalPrice = order.getTotalPrice();
					if(red_totalPrice>0){
						//还可以使用红包抵扣
						if(red_totalPrice <= red_account){
							//不需要调起支付
							order.setTotalPrice(0l);
							order.setRedPrice(red_totalPrice);
							order.setStatus(1);
						}else if (0<red_account && red_account<red_totalPrice) {
							order.setTotalPrice(red_totalPrice-red_account);
							order.setRedPrice(red_account);
						}else{
							order.setRedPrice(0l);
						}
					}else{
						//不需要红包抵扣，补贴已全部搞定
						order.setRedPrice(0l);
					}
				}else{
					order.setRedPrice(0l);
				}
			}else{
				order.setRedPrice(0l);
			}
			order.setLwFeeOne(lw_fee_one);
			order.setActualPrice(actual);
			order.setShopOrderItems(orderItemList);
			logger.info("开始创建订单" + order);
			this.shopOrderService.create(order);
			logger.info("创建订单结束");
			if(order.getTotalPrice()>0){
				String spbill_create_ip = request.getRemoteAddr();
				WXPrepay prePay = new WXPrepay();
				prePay.setAppid(properties.weixinAppid);
				prePay.setBody("自助便利店零食");
				prePay.setPartnerKey(properties.weixinAppsecret);
				prePay.setMch_id(properties.WX_MCH_ID);
				prePay.setNotify_url(properties.SHOP_PAY_NOTIFY_URL);
				prePay.setOut_trade_no(order.getId());
				prePay.setSpbill_create_ip(spbill_create_ip);
				prePay.setTotal_fee(order.getTotalPrice().toString());
				prePay.setTrade_type("JSAPI");
				prePay.setPartnerKey(properties.WX_PAY_PARTNERKRY);
				prePay.setOpenid(user.getOpenid());
				String prepayid = wxPayUtil.submitXmlGetPrepayId(prePay);
				logger.info("获取的预支付订单是：" + prepayid);
				if (prepayid != null && prepayid.length() > 10) {
					String jsParam = wxPayUtil.createPackageValue(
							prePay.getAppid(), prePay.getPartnerKey(), prepayid);
					System.out.println("jsParam=" + jsParam);
					logger.info("生成的微信调起JS参数为：" + jsParam);
					HashMap<String, Object> map = new HashMap<String, Object>();
					JsonNode jsonNode = MAPPER.readTree(jsParam);
					map.put("appId", jsonNode.path("appId").asText());
					map.put("nonceStr", jsonNode.path("nonceStr").asText());
					map.put("package", jsonNode.path("package").asText());
					map.put("paySign", jsonNode.path("paySign").asText());
					map.put("signType", jsonNode.path("signType").asText());
					map.put("timeStamp", jsonNode.path("timeStamp").asText());
					map.put("prepayid", prepayid);
					map.put("orderId", order.getId());
					return LWResult.ok(map);  
				}
			}else{
				if(order.getRedPrice()>0){
					this.redAccountService.updateAccountMoneyByUserIdAndType(user.getId(), 1, -order.getRedPrice());
				}
				for (ShopOrderItem item : orderItemList) {
					this.shopItemService.updateNum(item.getItemId(),item.getNum());
				}
				return LWResult.build(202, "free", order.getId());
			}
		} catch (Exception e) {
			logger.error("便利店下单异常"+e);
		}
		return LWResult.build(203, "创建订单失败");

	}
	
	
	//支付回调
	@RequestMapping("/shop_notify.do")
	public void test(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = DOM4JParser.getInputStream(request);
		String xmlStr = "";
		logger.info("------map:" + map);
		if (map.get("return_code").equals("SUCCESS")) {
			NotifyState notifyState = this.notifyStateService.findById(map.get("out_trade_no").toString());
			if(null!=notifyState && notifyState.getStatus() == 1){
				xmlStr = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
			}else{
				logger.info("支付成功,订单号:" + map.get("out_trade_no").toString());
				this.shopOrderService.changeStatus(map.get("out_trade_no").toString());
				ShopOrder order = this.shopOrderService.findById(map.get("out_trade_no").toString());
				if(null!=order.getShopId()){
					CustomerCamera camera = this.customerCameraService.findByCid(order.getShopId());
					if(null!=camera && null!=camera.getNum()){
						try {
							String url = "http://120.26.56.240:9511/lw_action?did="+camera.getNum()+"&action=0&lparam=1";
							apiService.doGet(url);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
				logger.info("支付成功,订单号:" + map.get("out_trade_no").toString());
				//NotifyUtil.payMessage(map.get("openid").toString(), properties.PAY_TEMP_ID,map.get("out_trade_no").toString(),NumberTool.toYuanNumber(Long.parseLong(map.get("total_fee").toString())), map.get("time_end").toString(), properties.weixinAppid, properties.weixinAppsecret);
				xmlStr = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
			}
		}else{
			xmlStr = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
					+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
		}
		logger.info("xmlstr:" + xmlStr);
		PrintWriter writer = null;
		response.setContentType("text/xml");
		try {
			
			writer = response.getWriter();
			writer.flush();
			writer.println(xmlStr);// 告知微信已成功处理请求
		} catch (Exception e) {
			logger.error("异步回调失败"+e);
		}finally{
			writer.close();
		}
	}
	
	//采购
	@RequestMapping("shop_sourcingItem.htm")
	public ModelAndView sourcingItem(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		ModelAndView view = new ModelAndView("shop/sourcing/item");
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		CustomerUser customerUser = this.customerService.findById(shopId);
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){
			//List<ItemCategory> cates = this.itemCategoryService.findAllEXOtherByCityId(customerUser.getCityId()); 
			List<ItemCategory> cates = this.itemCategoryService.findByCityIdAndIsshow(customerUser.getCityId(),1);
			ItemCategory category = cates.get(0);
			List<ItemCategory> leaveCates = this.itemCategoryService.findCatByParentId(category.getId());
			if(null!=customerUser.getIswxvip() && 3==customerUser.getIswxvip()){
				List<Item> items = this.itemService.findItemByCateId(category.getId(),shopId,0, null);
				view.addObject("items", items);
			}else{
				List<Item> items = this.itemService.findItemByCateId(category.getId(),shopId,1, null);
				view.addObject("items", items);
			}
			view.addObject("cates", cates);
			view.addObject("leaveCates", leaveCates);
			view.addObject("shop_id", shopId);
			return view;
		}else{
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
			if(null!=admin){
				List<ItemCategory> cates = this.itemCategoryService.findByCityIdAndIsshow(customerUser.getCityId(),1);
				ItemCategory category = cates.get(0);
				List<ItemCategory> leaveCates = this.itemCategoryService.findCatByParentId(category.getId());
				
				if(null!=customerUser.getIswxvip() && 3==customerUser.getIswxvip()){
					List<Item> items = this.itemService.findItemByCateId(category.getId(),shopId,0, null);
					view.addObject("items", items);
				}else{
					List<Item> items = this.itemService.findItemByCateId(category.getId(),shopId,1, null);
					view.addObject("items", items);
				}
				view.addObject("cates", cates);
				view.addObject("leaveCates", leaveCates);
				view.addObject("shop_id", shopId);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
	}
	
	//办公用品采购
//	@RequestMapping("shop_sourcingOfficeItem.htm")
//	public ModelAndView sourcingOfficeItem(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
//		ModelAndView view = new ModelAndView("shop/sourcing/officeItem");
//		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
//		CustomerUser customerUser = this.customerService.findById(shopId);
//		ItemCategory catt = this.itemCategoryService.findOfficeCatIdByCityId(customerUser.getCityId());
//		Integer catid = 0;
//		if(catt!=null){
//			catid = catt.getId();
//		}
//		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
//		if(null!=puser){
//			List<ItemCategory> leaveCates = this.itemCategoryService.findCatByParentId(catid);
//			if(!leaveCates.isEmpty()){
//				
//				Integer id = leaveCates.get(0).getId();
//				
//				if(null!=customerUser.getIswxvip() && 3==customerUser.getIswxvip()){
//					List<Item> items = this.itemService.findItemsByLeaveCate(id,catid,shopId,0, null);
//					view.addObject("items", items);
//				}else{
//					List<Item> items = this.itemService.findItemsByLeaveCate(id,catid,shopId,1, null);
//					view.addObject("items", items);
//				}
//			}
//			view.addObject("leaveCates", leaveCates);
//			view.addObject("shop_id", shopId);
//			return view;
//		}else{
//			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
//			if(null!=admin){
//				List<ItemCategory> leaveCates = this.itemCategoryService.findCatByParentId(catid);
//				if(!leaveCates.isEmpty()){
//				Integer id = leaveCates.get(0).getId();
//				if(null!=customerUser.getIswxvip() && 3==customerUser.getIswxvip()){
//					List<Item> items = this.itemService.findItemsByLeaveCate(id,catid,shopId,0, null);
//					view.addObject("items", items);
//				}else{
//					List<Item> items = this.itemService.findItemsByLeaveCate(id,catid,shopId,1, null);
//					view.addObject("items", items);
//				}
//				}
//				view.addObject("leaveCates", leaveCates);
//				view.addObject("shop_id", shopId);
//				return view;
//			}
//		}
//		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
//	}
	
	@RequestMapping(value="shop_sourcingSearch.htm",produces="text/html;charset=UTF-8")
	public ModelAndView search(HttpServletRequest request,@RequestParam("shop_id")Integer shopId,@RequestParam("searchName")String name){
		ModelAndView view = new ModelAndView("shop/sourcing/search");
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		CustomerUser customerUser = this.customerService.findById(shopId);
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){
			if(null!=customerUser.getIswxvip() && 3==customerUser.getIswxvip()){
				List<Item> items = this.itemService.findSourcingItemByLikeName(name,shopId,0,customerUser.getCityId());
				view.addObject("items", items);
			}else{
				List<Item> items = this.itemService.findSourcingItemByLikeName(name,shopId,1,customerUser.getCityId());
				view.addObject("items", items);
			}
			view.addObject("name", name);
			view.addObject("shopId", shopId);
			return view;
		}else{
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
			if(null!=admin){
				if(null!=customerUser.getIswxvip() && 3==customerUser.getIswxvip()){
					List<Item> items = this.itemService.findSourcingItemByLikeName(name,shopId,0,customerUser.getCityId());
					view.addObject("items", items);
				}else{
					List<Item> items = this.itemService.findSourcingItemByLikeName(name,shopId,1,customerUser.getCityId());
					view.addObject("items", items);
				}
				view.addObject("name", name);
				view.addObject("shopId", shopId);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	
//	@RequestMapping(value="shop_sourcingOfficeSearch.htm",produces="text/html;charset=UTF-8")
//	public ModelAndView searchOffice(HttpServletRequest request,@RequestParam("shop_id")Integer shopId,@RequestParam("searchName")String name){
//		
//		ModelAndView view = new ModelAndView("shop/sourcing/searchOffice");
//		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
//		CustomerUser customerUser = this.customerService.findById(shopId);
//		ItemCategory catt = this.itemCategoryService.findOfficeCatIdByCityId(customerUser.getCityId());
//		Integer catid = 0;
//		if(catt!=null){
//			catid = catt.getId();
//		}
//		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
//		if(null!=puser){
//			if(null!=customerUser.getIswxvip() && 3==customerUser.getIswxvip()){
//				List<Item> items = this.itemService.findSourcingOfficeItemByLikeName(name,shopId,0,customerUser.getCityId(),catid);
//				view.addObject("items", items);
//			}else{
//				List<Item> items = this.itemService.findSourcingOfficeItemByLikeName(name,shopId,1,customerUser.getCityId(),catid);
//				view.addObject("items", items);
//			}
//			view.addObject("name", name);
//			view.addObject("shopId", shopId);
//			return view;
//		}else{
//			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
//			if(null!=admin){
//				if(null!=customerUser.getIswxvip() && 3==customerUser.getIswxvip()){
//					List<Item> items = this.itemService.findSourcingOfficeItemByLikeName(name,shopId,0,customerUser.getCityId(),catid);
//					view.addObject("items", items);
//				}else{
//					List<Item> items = this.itemService.findSourcingOfficeItemByLikeName(name,shopId,1,customerUser.getCityId(),catid);
//					view.addObject("items", items);
//				}
//				view.addObject("name", name);
//				view.addObject("shopId", shopId);
//				return view;
//			}
//		}
//		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
//	}
	
	@RequestMapping("shop_onesourcingItem.json")
	@ResponseBody
	public LWResult one(HttpServletRequest request,@RequestParam("shop_id")Integer shopId,@RequestParam("cateid")Integer cateid,
						@RequestParam("lableId")Integer lableId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){
			List<ItemCategory> leaveCates = this.itemCategoryService.findCatByParentId(cateid);
			
			Map<String, Object> map = new HashMap<String, Object>();
			CustomerUser customerUser = this.customerService.findById(shopId);
			if(null!=customerUser.getIswxvip() && 3==customerUser.getIswxvip()){
				List<Item> items = this.itemService.findItemByCateId(cateid,shopId,0, lableId);
				map.put("items", items);

			}else{
				List<Item> items = this.itemService.findItemByCateId(cateid,shopId,1, lableId);
				map.put("items", items);

			}
			map.put("leaveCates", leaveCates);
						return LWResult.ok(map);
		}else{
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
			if(null!=admin){
				List<ItemCategory> leaveCates = this.itemCategoryService.findCatByParentId(cateid);
				CustomerUser customerUser = this.customerService.findById(shopId);
				Map<String, Object> map = new HashMap<String, Object>();
				if(null!=customerUser.getIswxvip() && 3==customerUser.getIswxvip()){
					List<Item> items = this.itemService.findItemByCateId(cateid,shopId,0, lableId);
					map.put("items", items);

				}else{
					List<Item> items = this.itemService.findItemByCateId(cateid,shopId,1, lableId);
					map.put("items", items);

				}
				map.put("leaveCates", leaveCates);
				return LWResult.ok(map);
			}
		}
		return LWResult.build(201, "没有权限");
	}
	
	@RequestMapping("shop_levsourcingItem.json")
	@ResponseBody
	public LWResult level(HttpServletRequest request,@RequestParam("shop_id")Integer shopId,@RequestParam("id")Integer  id
			,@RequestParam("one")Integer  oneid, @RequestParam("lableId")Integer lableId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){
			
			CustomerUser customerUser = this.customerService.findById(shopId);
			if(null!=customerUser.getIswxvip() && 3==customerUser.getIswxvip()){
				List<Item> items = this.itemService.findItemsByLeaveCate(id,oneid,shopId,0, lableId);
				return LWResult.ok(items);
			}else{
				List<Item> items = this.itemService.findItemsByLeaveCate(id,oneid,shopId,1, lableId);

				return LWResult.ok(items);
			}
		}else{
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
			if(null!=admin){
				CustomerUser customerUser = this.customerService.findById(shopId);
				if(null!=customerUser.getIswxvip() && 3==customerUser.getIswxvip()){
					List<Item> items = this.itemService.findItemsByLeaveCate(id,oneid,shopId,0, lableId);
					return LWResult.ok(items);
				}else{
					List<Item> items = this.itemService.findItemsByLeaveCate(id,oneid,shopId,1, lableId);
					return LWResult.ok(items);
				}
				
			}
		}
		return LWResult.build(201, "没有权限");
	}

	@RequestMapping("shop_intelligentItems.json")
	@ResponseBody
	public LWResult intelligentItems(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){

			CustomerUser customerUser = this.customerService.findById(shopId);
			if(null!=customerUser.getIswxvip() && 3==customerUser.getIswxvip()){
				List<Item> items = this.itemService.findItemsByIntelligentItems(shopId, 0);
				items = removeDuplicate(items);
				return LWResult.ok(items);
			}else{
				List<Item> items = this.itemService.findItemsByIntelligentItems(shopId,1);
				items = removeDuplicate(items);
				return LWResult.ok(items);
			}
		}else{
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
			if(null!=admin){
				CustomerUser customerUser = this.customerService.findById(shopId);
				if(null!=customerUser.getIswxvip() && 3==customerUser.getIswxvip()){
					List<Item> items = this.itemService.findItemsByIntelligentItems(shopId,0);
					items = removeDuplicate(items);
					return LWResult.ok(items);
				}else{
					List<Item> items = this.itemService.findItemsByIntelligentItems(shopId,1);
					items = removeDuplicate(items);
					return LWResult.ok(items);
				}

			}
		}
		return LWResult.build(201, "没有权限");
	}

	private   static  List<Item>  removeDuplicate(List<Item> list)  {
		for  ( int  i  =   0 ; i  <  list.size()  -   1 ; i ++ )  {
			for  ( int  j  =  list.size()  -   1 ; j  >  i; j -- )  {
				if  (list.get(j).getId().equals(list.get(i).getId()))  {
					list.remove(j);
				}
			}
		}
		return list;
	}
	
	@RequestMapping("shop_sourcingsure.htm")
	public ModelAndView jiesuan(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		ModelAndView view = new ModelAndView("shop/sourcing/sure");
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		String flag = request.getParameter("flag");
		if ("2".equals(flag)) {
			view.addObject("flag", 2);
		}
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){
			view.addObject("shop_id", shopId);
			return view;
		}else{
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
			if(null!=admin){
				view.addObject("shop_id", shopId);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	
	@RequestMapping("shop_sourcingOrder.do")
	@ResponseBody
	public LWResult xiadan(HttpServletRequest request,@RequestParam("shop_id")Integer shopId,@RequestParam("data")String data){
		try {
			Gson gson = new Gson(); 
			List<SourcingItem> datas = gson.fromJson(data,  new TypeToken<List<SourcingItem>>() {}.getType());
			if(datas.isEmpty()){
				return LWResult.build(201, "请选择商品");
			}
			CustomerUser personUser = customerService.findById(shopId);
			Order order = new Order();
			order.setCityId(personUser.getCityId());
			String memo = request.getParameter("memo");
			if(null != memo && memo!=""){
				order.setMemo(memo);
			}
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			order.setDiscount(personUser.getDiscount());
			order.setAddress(personUser.getAddress());
			order.setCreatedTime(new Date());
			order.setOrderStatus(OrderStatusEnum.PAY.getId());
			order.setStatus(Constant.VALID_STATUS);
			order.setUserId(personUser.getId());
			order.setBusinessId(personUser.getBusinessId());
			order.setOrderNo(OrderNumberGenerator.buildOrderNo(order.getCreatedTime(), order.getUserId()));
			order.setComfirm(0);
			order.setSourcingId(user.getOpenid());
			if(personUser.getIswxvip()==3){
				order.setType(1);
			}else{
				order.setType(2);
			}
			Date date = new Date();
			String flag = request.getParameter("flag");
			if("2".equals(flag)){
				order.setSign(4);
			}else{
				order.setSign(3);
			}
			if("2".equals(flag)){
				date = DateUtils.addDays(date, 7);
			}else{
				int week = DateUtil.dayForWeek(date);
				String formatDate = DateUtil.formatDate(date, "yyyy-MM-dd 16:30:00");
				Date date2 = DateUtil.parseDateStr(formatDate, "yyyy-MM-dd hh:mm:ss");
				if(week==1){
					if(date.before(date2)){
						date = DateUtils.addDays(date, 1);
					}else{
						date = DateUtils.addDays(date, 2);
					}
				}else if (week==2) {
					if(date.before(date2)){
						date = DateUtils.addDays(date, 1);
					}else{
						date = DateUtils.addDays(date, 2);
					}
				}else if (week==3) {
					if(date.before(date2)){
						date = DateUtils.addDays(date, 1);
					}else{
						date = DateUtils.addDays(date, 2);
					}
				}else if (week==4) {
					if(date.before(date2)){
						date = DateUtils.addDays(date, 1);
					}else{
						date = DateUtils.addDays(date, 4);
					}
				}else if (week==5) {
					if(date.before(date2)){
						date = DateUtils.addDays(date, 3);
					}else{
						date = DateUtils.addDays(date, 4);
					}
				}else if (week==6) {
					date = DateUtils.addDays(date, 3);
				}else if (week==7) {
					date = DateUtils.addDays(date, 2);
				}
			}
			date = DateUtil.beginOfADay(date);
			order.setExecutedTime(date);
			List<OrderItem> orderItemList = new ArrayList<OrderItem>();
			Integer orderTotalNum = 0;
			Long orderTotalFee = 0l;
			Long orderTotalCost = 0l;
			Long notaxInclusiveTotalCost = 0l;
			for (SourcingItem sourcingItem : datas) {
				Item item = this.itemService.findById(sourcingItem.getId());
				OrderItem oi = new OrderItem();
				oi.setOrderNo(order.getOrderNo());
				oi.setItemId(sourcingItem.getId());
				oi.setNum(sourcingItem.getNum());
				oi.setItemSizeType("SIZE");
				oi.setConsumeStockNum(sourcingItem.getNum());
				oi.setItemSize(item.getSize());
				oi.setItemPrice(item.getPrice());
				oi.setItemCostPrice(item.getCostPrice());
				oi.setFee(oi.getItemPrice() * oi.getNum());
				oi.setItemName(item.getItemName());
				oi.setNotaxInclusiveCostPrice(item.getNotaxInclusiveCostPrice());
				orderTotalNum += oi.getNum();
				orderTotalFee += oi.getFee();
				orderTotalCost += oi.getItemCostPrice()*oi.getNum();
				notaxInclusiveTotalCost += oi.getNotaxInclusiveCostPrice()*oi.getNum();
				orderItemList.add(oi);
			}
			//BigDecimal pr=(new BigDecimal(orderTotalFee)).multiply(order.getDiscount()).setScale(2, BigDecimal.ROUND_HALF_UP);
			//orderTotalFee = pr.longValue();
			order.setTotalFee(orderTotalFee);
			order.setTotalNum(orderTotalNum);
			order.setTotalCost(orderTotalCost);
			order.setNotaxInclusiveTotalCost(notaxInclusiveTotalCost);
			order.setOrderItemList(orderItemList);
			orderService.createSourcingOrder(order);
			this.saveLog(request.getSession(), order, "便利店采购", shopId, user.getNickName(),personUser.getCityId());
			return LWResult.ok(order.getOrderNo());
		} catch (JsonSyntaxException e) {
			logger.error("添加订单失败", e);
			return LWResult.build(201, "下单失败！");
		}
	}
	
	@RequestMapping("shop_sourcingSuccess.htm")
	public ModelAndView success(@RequestParam("no")String orderNo){
		ModelAndView view = new ModelAndView("shop/sourcing/success");
		Order order = this.orderService.findOrderById(orderNo);
		view.addObject("price", order.getTotalFee());
		view.addObject("orderNo", orderNo);
		view.addObject("shop_id", order.getUserId());
		view.addObject("memo", order.getMemo());
		view.addObject("exedate", order.getExecutedTime());
		return view;
	}
	
	@RequestMapping("home/shop_accountManager.htm")
	public ModelAndView accountManager(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		ModelAndView view = new ModelAndView("shop/home/accountManager");
		CustomerUser user = this.customerService.findById(shopId);
		PersonUser personUser = userService.findByReseauId(user.getReseauId());
	    view.addObject("manage", personUser);
		return view;
	}
	
	@RequestMapping("home/shop_faq.htm")
	public ModelAndView faq(){
		ModelAndView view = new ModelAndView("shop/home/faq");
		return view;
	}
	
	@RequestMapping("shop_paySuccess.htm")
	public ModelAndView pay_success(@RequestParam("orderNo")String orderNo){
		ModelAndView view = new ModelAndView("shop/index/success");
		ShopOrder shopOrder = this.shopOrderService.findById(orderNo);
		view.addObject("pay_way", shopOrder.getSign());
		List<ShopOrderItem> items = this.shopOrderItemService.findItemByOrderId(orderNo);
		shopOrder.setShopOrderItems(items);
		view.addObject("shopOrder", shopOrder);
		try {
			List<RedPacket> packets = this.redPacketService.findStartedRedPacket(shopOrder.getCityId());
			if(1==packets.size()){
				RedPacket packet = packets.get(0);
				if(packet.getSkip()){
					Date date = new Date();
					int week = DateUtil.getWeekInt(date);
					if(0==week || 6==week){
						view.addObject("flag", 0);
						return view;
					}
				}
				if(1==packet.getType()){
					
				}else if (2==packet.getType()) {
					RedPacketCustomer redPacketCustomer = this.redPacketCustomerService.findByRedIdAndShopId(packet.getId(),shopOrder.getShopId());
					if(null==redPacketCustomer){
						//noway
						view.addObject("flag", 0);
						return view;
					}
				}else{
					view.addObject("flag", 0);
					return view;
				}
				//红包总笔数
				Integer rednum = this.redPacketTypeService.findTotalNumByRedId(packet.getId());
				//用户当天领取次数
				Integer num = this.redReceiveService.findTodayNumByBuyerIdAndTypeAndCityId(shopOrder.getBuyerId(),shopOrder.getSign(),shopOrder.getCityId());
				if(packet.getCount()==0 ||num<packet.getCount()){
					//还可以领
					RedShopOrder redShopOrder =this.redShopOrderService.findByOrderNo(shopOrder.getId());
					if(null!=redShopOrder){
						//刷新的
						if(redShopOrder.getFlag()==1){
							RedReceive redreceive = this.redReceiveService.findByOrderNo(orderNo);
							if(null==redreceive){
								view.addObject("flag", 1);
						    	view.addObject("redId", packet.getId());
						    	view.addObject("ticket", redShopOrder.getTicket());
							}else{
								view.addObject("flag", 0);
							}
						}else{
							view.addObject("flag", 0);
						}
					}else{
						Integer baseDayNum = packet.getBaseDayNum();
						int a = rednum*1000/baseDayNum;
						Random random = new Random();
					    int b = random.nextInt(1000);
					    if(b<=a){
					    	view.addObject("flag", 1);
					    	view.addObject("redId", packet.getId());
					    	RedShopOrder order = new RedShopOrder();
					    	order.setOrderNo(orderNo);
					    	String md5 = MD5Util.getStringMD5(OrderUtil.GetOrderNumber());
					    	order.setTicket(md5);
					    	order.setFlag(1);
					    	this.redShopOrderService.save(order);
					    	view.addObject("ticket", md5);
					    }else{
					    	RedShopOrder order = new RedShopOrder();
					    	order.setOrderNo(orderNo);
					    	String md5 = MD5Util.getStringMD5(OrderUtil.GetOrderNumber());
					    	order.setTicket(md5);
					    	order.setFlag(0);
					    	this.redShopOrderService.save(order);
					    	view.addObject("flag", 0);
					    }
					}
				}else{
					view.addObject("flag", 0);
				}
			}else{
				view.addObject("flag", 0);
			}
		} catch (Exception e) {
			view.addObject("flag", 0);
			logger.error("支付完成页，红包逻辑"+e.getMessage());
		}
		return view;
	}
	
	@RequestMapping("shop_getRed.do")
	@ResponseBody
	public LWResult getRed(@RequestParam("orderNo")String orderNo,@RequestParam("redId")Integer redId,@RequestParam("ticket")String ticket){
		try {
			ShopOrder shopOrder = this.shopOrderService.findById(orderNo);
			RedPacket red= this.redPacketService.findStartedRedPacketById(redId);
			Random random = new Random();
			if(null==red){
				return LWResult.build(201, "抢光了");
			}else{
				RedShopOrder redShopOrder =this.redShopOrderService.findByOrderNo(shopOrder.getId());
				if(null!=redShopOrder && ticket.equals(redShopOrder.getTicket())){
					RedReceive redreceive = this.redReceiveService.findByOrderNo(orderNo);
					if(null!=redreceive){
						//已领取
						return LWResult.ok(redreceive.getRedFee());
					}else{
						//抽中还未领取
						Integer baseDayNum = red.getBaseDayNum();
						List<RedPacketType> types= this.redPacketTypeService.findByRedId(redId);
						int b = random.nextInt(100000);
						int a1 = 0;
						int a2 = 0;
						RedPacketType redtype = null;
						for (int i = 0; i < types.size(); i++) {
							a2+=types.get(i).getRate();
							if(i==0){
								a1=0;
							}else{
								a1+=types.get(i-1).getRate();
							}
							if(b>=a1 && b<a2){
								redtype=types.get(i);
								break;
							}
						}
						if(null!=redtype){
							Integer num = this.redReceiveService.findTodayNumByRedTypeIdAndCityId(redtype.getId(),shopOrder.getCityId());
							if(num>=redtype.getNum()){
								return LWResult.build(201, "抢光了");
							}
							if(redtype.getType()==2){
								
								int upFee = redtype.getUpFee().intValue();
								int downFee = redtype.getDownFee().intValue();
								long money = random.nextInt(upFee-downFee)+redtype.getDownFee();
								RedReceive receive = new RedReceive();
								receive.setShopId(shopOrder.getShopId());
								CustomerUser customerUser = this.customerService.findById(shopOrder.getShopId());
								receive.setUserName(customerUser.getUserName());
								receive.setCityId(shopOrder.getCityId());
								receive.setCreatedTime(new Date());
								receive.setOrderNo(shopOrder.getId());
								receive.setRedFee(money);
								receive.setSign(1);//领取
								receive.setStatus(1);
								receive.setRedTypeId(redtype.getId());
								if(shopOrder.getSign()==1){
									ShopUser shopUser = this.shopUserService.findById(shopOrder.getBuyerId());
									receive.setUserId(shopUser.getId());
									receive.setNickName(shopUser.getNickName());
									receive.setSex(shopUser.getSex());
									receive.setType(1);//微信
								}else{
									ShopAliUser shopUser = this.shopAliUserService.findById(shopOrder.getBuyerId());
									receive.setUserId(shopUser.getId());
									receive.setNickName(shopUser.getNickName());
									receive.setSex(shopUser.getSex());
									receive.setType(2);//ali
								}
								this.redReceiveService.save(receive);
								return LWResult.ok(money);
							}else if (redtype.getType()==1) {
								long money = redtype.getFee();
								RedReceive receive = new RedReceive();
								receive.setShopId(shopOrder.getShopId());
								CustomerUser customerUser = this.customerService.findById(shopOrder.getShopId());
								receive.setUserName(customerUser.getUserName());
								receive.setCityId(shopOrder.getCityId());
								receive.setCreatedTime(new Date());
								receive.setOrderNo(shopOrder.getId());
								receive.setRedFee(money);
								receive.setSign(1);//领取
								receive.setStatus(1);
								receive.setRedTypeId(redtype.getId());
								if(shopOrder.getSign()==1){
									ShopUser shopUser = this.shopUserService.findById(shopOrder.getBuyerId());
									receive.setUserId(shopUser.getId());
									receive.setNickName(shopUser.getNickName());
									receive.setSex(shopUser.getSex());
									receive.setType(1);//微信
								}else{
									ShopAliUser shopUser = this.shopAliUserService.findById(shopOrder.getBuyerId());
									receive.setUserId(shopUser.getId());
									receive.setNickName(shopUser.getNickName());
									receive.setSex(shopUser.getSex());
									receive.setType(2);//ali
								}
								this.redReceiveService.save(receive);
								return LWResult.ok(money);
							}else{
								
								return LWResult.build(201, "抢光了");
							}
						}else{
							return LWResult.build(201, "抢光了");
						}
						
					}
				}else{
					return LWResult.build(201, "抢光了");
				}
			}
		} catch (Exception e) {
			logger.error("拆红包异常："+e.getMessage());
			return LWResult.build(201, "抢光了");
		}
		
	}
	
	@RequestMapping("shop_personal.htm")
	public ModelAndView pay_success(HttpServletRequest request,@RequestParam("userid")Integer userid,@RequestParam("type")Integer type){
		ModelAndView view = new ModelAndView("shop/index/personal");
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ShopAliUser Aliuser = WXSessionHelper.getShopAliUser(request.getSession());
		RedAccount redAccount = this.redAccountService.findByUserIdAndType(userid, type);
		view.addObject("redAccount", redAccount);
		view.addObject("type", type);
		if(type.equals(1)){
			if(null!=user && user.getId().equals(userid)){
				view.addObject("user", user);
				return view;
			}
		}else if (type.equals(2)) {
			if(null!=Aliuser && Aliuser.getId().equals(userid)){
				view.addObject("user", Aliuser);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	
	//个人消费记录 待修改
		@RequestMapping(value="shop_personalConsumeList.htm")
		public ModelAndView personalconsumeList(HttpServletRequest request,@RequestParam("userid")Integer userid,@RequestParam("type")Integer type){
			ModelAndView view = new ModelAndView("shop/index/personalConsume");
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			ShopAliUser Aliuser = WXSessionHelper.getShopAliUser(request.getSession());
			view.addObject("type", type);
			if(type.equals(1)){
				if(null!=user && user.getId().equals(userid)){
					view.addObject("userid", userid);
					return view;
				}
			}else if (type.equals(2)) {
				if(null!=Aliuser && Aliuser.getId().equals(userid)){
					view.addObject("userid", userid);
					return view;
				}
			}
			return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
		}
		
		@RequestMapping(value="shop_personalConsumeTest.json")
		@ResponseBody
		public LWResult personalconsumetest(HttpServletRequest request,@RequestParam("userid")Integer userid,@RequestParam("type")Integer type,@RequestParam(defaultValue="1",value="page")Integer page,@RequestParam(defaultValue="50",value="rows")Integer rows){
			PageHelper.startPage(page, rows);
			List<ShopOrder> lists = this.shopOrderService.findOrderAndItemByUseridAndtype(userid,type);
			PageInfo<ShopOrder> info = new PageInfo<ShopOrder>(lists);
			return LWResult.ok(info);
		}
		
		@RequestMapping(value="shop_refundItemList.htm")
		public ModelAndView BeRefundItemList(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			if(null!=puser){
				ModelAndView view = new ModelAndView("shop/refund/list");
				List<BeRefundItem> lists = this.beRefundItemService.findTodayRefundItemByShopId(shopId);
				view.addObject("items", lists);
				view.addObject("shopid", shopId);
				return view;
			}
			return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
			
		}
		
		@RequestMapping(value="shop_addRefund.do")
		@ResponseBody
		public LWResult addrefund(HttpServletRequest request,@RequestParam("shop_id")Integer shopId,@RequestParam("data")String data){
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			String remark = request.getParameter("remark");
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			if(null!=puser){
				Gson gson = new Gson(); 
				List<RefundItem> datas = gson.fromJson(data,  new TypeToken<List<RefundItem>>() {}.getType());
				if(datas.isEmpty()){
					return LWResult.build(201, "请添加退货商品");
				}
				long totalFeeAll = 0;
		        long totalcost = 0;
		        long totalnocost = 0;
				for (RefundItem refundItem : datas) {
					Item item = this.itemService.findById(refundItem.getItemId());
					refundItem.setItemName(item.getItemName());
					refundItem.setTotalFee(refundItem.getItemPrice()*refundItem.getNum());
					refundItem.setItemCostFee(item.getCostPrice()*refundItem.getNum());
					refundItem.setCostFee(item.getNotaxInclusiveCostPrice()*refundItem.getNum());
					refundItem.setSize("SIZE");
					refundItem.setSizeValue(item.getSize());
					totalFeeAll = totalFeeAll+refundItem.getTotalFee();
					totalcost = totalcost +refundItem.getItemCostFee();
					totalnocost = totalnocost+refundItem.getCostFee();
				}
				//PersonUser puser = this.userService.findByOpenId(user.getOpenid());
				Refund refund = new Refund();
				refund.setUserId(shopId);
				refund.setCreatedTime(new Date());
				refund.setModifiedTime(refund.getCreatedTime());
				refund.setExecutedTime(refund.getCreatedTime());
				refund.setState(1);
				refund.setRemark(remark);
				if(null!=puser.getId()){
					refund.setLastModUser(puser.getId());
				}
				CustomerUser customerUser = this.customerService.findById(shopId);
				refund.setCityId(customerUser.getCityId());
				refund.setDiscount(customerUser.getDiscount());
				refund.setTotalFee(totalFeeAll);
				refund.setCostFee(totalcost);
				refund.setNotaxinclusivecostfee(totalnocost);
				refund.setReason(0);//待入库
				refund.setRefundItemList(datas);
				this.refundService.createRefundNotUpdateStock(refund);
				this.saveLog(request.getSession(), refund, "手机端添加退货单", shopId, user.getNickName(),customerUser.getCityId());
				return LWResult.ok();
			}
			return LWResult.build(202, "没有权限");
		}
		
		@RequestMapping(value="shop_refundSearch.htm",produces="text/html;charset=UTF-8")
		public ModelAndView searchItem(HttpServletRequest request,@RequestParam("shop_id")Integer shopId,@RequestParam("itemName")String name){
			ModelAndView view = new ModelAndView("shop/refund/search");
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			CustomerUser customerUser = this.customerService.findById(shopId);
			PersonUser puser = this.userService.findByOpenId(user.getOpenid());
			if(null!=puser){
				if(!StringUtils.isEmpty(name)){
					List<Item> items = this.itemService.findByLikeName(name,customerUser.getCityId());
					view.addObject("items", items);
				}
				view.addObject("name", name);
				view.addObject("shopId", shopId);
				return view;
			}
			return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
		}
		
		@RequestMapping(value="shop_chooseRefundItem.do")
		@ResponseBody
		public LWResult chooseRefundItem(HttpServletRequest request,@RequestParam("shop_id")Integer shopId,@RequestParam("itemid")Integer itemid){
			try {
				ShopUser user = WXSessionHelper.getShopUser(request.getSession());
				PersonUser puser = this.userService.findByOpenId(user.getOpenid());
				if(null!=puser){
					Item item2 = this.itemService.findById(itemid);
					BeRefundItem item = new BeRefundItem();
					item.setCreatedDate(new Date());
					item.setImgPath(item2.getImgPath());
					item.setItemId(itemid);
					item.setItemName(item2.getItemName());
					item.setPrice(item2.getPrice());
					item.setShopId(shopId);
					item.setSize(item2.getSize());
					this.beRefundItemService.insert(item);
					return LWResult.ok();
				}
				return LWResult.build(202, "没有权限");
			} catch (Exception e) {
				e.printStackTrace();
			}
			return LWResult.build(201, "没有权限");
			
		}
	
		 private void saveLog(HttpSession session,Refund dto,String content,Integer shopId,String name,Integer cityId){
				try{
				   SysShopLog sysLog = new SysShopLog();
			       sysLog.setContent(content);
			       sysLog.setCreateTime(new Date());
			       sysLog.setUserId(WXSessionHelper.getShopUserId(session));
			       sysLog.setDataType(LogDataTypeEnum.REFUND.getName());
			       sysLog.setDataId(dto.getId().toString());
			       sysLog.setShopId(shopId);
			       sysLog.setUserName(name);
			       sysLog.setCityId(cityId);
			       String dataContent = new Gson().toJson(dto,
							new TypeToken<Refund>() {
							}.getType());

			       sysLog.setDataContent(dataContent);

			       logService.createLog(sysLog);
				}catch(Exception e){
		           logger.error("保存日志失败",e);
				}
			}	
		
	private void saveLog(HttpSession session,CustomerUser dto,String content,Integer shopId,String name,Integer cityId){
		try{
	       SysShopLog sysLog = new SysShopLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(WXSessionHelper.getShopUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.Shoper.getName());
	       sysLog.setDataId(dto.getId().toString());
	       sysLog.setShopId(shopId);
	       sysLog.setUserName(name);
	       sysLog.setCityId(cityId);
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
	
	private void saveLog(HttpSession session,Order dto,String content,Integer shopId,String name,Integer cityId){
		try{
			SysShopLog sysLog = new SysShopLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(WXSessionHelper.getShopUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.ShopOrder.getName());
	       sysLog.setDataId(dto.getOrderNo());
	       sysLog.setShopId(shopId);
	       sysLog.setUserName(name);
	       sysLog.setCityId(cityId);
	       String dataContent = new Gson().toJson(dto,
					new TypeToken<Order>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
	
	private void saveLog(HttpSession session,ShopItem dto,String content,Integer shopId,String name,Integer cityId){
		try{
			SysShopLog sysLog = new SysShopLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(WXSessionHelper.getShopUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.ShopItemUpOrDown.getName());
	       sysLog.setDataId(dto.getId().toString());
	       sysLog.setShopId(shopId);
	       sysLog.setUserName(name);
	       sysLog.setCityId(cityId);
	       String dataContent = new Gson().toJson(dto,
					new TypeToken<ShopItem>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
	
	private void saveLog(HttpSession session,SeniorSet dto,String content,Integer shopId,String name,Integer cityId){
		try{
			SysShopLog sysLog = new SysShopLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(WXSessionHelper.getShopUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.ShopSeniorSet.getName());
	       sysLog.setDataId(dto.getShopId().toString());
	       sysLog.setShopId(shopId);
	       sysLog.setUserName(name);
	       sysLog.setCityId(cityId);
	       String dataContent = new Gson().toJson(dto,
					new TypeToken<SeniorSet>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
	
	private void saveLog(HttpSession session,ShopOrder dto,String content,Integer shopId,String name,Integer cityId){
		try{
			SysShopLog sysLog = new SysShopLog();
	       sysLog.setContent(content);
	       sysLog.setCreateTime(new Date());
	       sysLog.setUserId(WXSessionHelper.getShopUserId(session));
	       sysLog.setDataType(LogDataTypeEnum.ShopOrderRefund.getName());
	       sysLog.setDataId(dto.getId());
	       sysLog.setShopId(shopId);
	       sysLog.setUserName(name);
	       sysLog.setCityId(cityId);
	       String dataContent = new Gson().toJson(dto,
					new TypeToken<ShopOrder>() {
					}.getType());

	       sysLog.setDataContent(dataContent);

	       logService.createLog(sysLog);
		}catch(Exception e){
           logger.error("保存日志失败",e);
		}
	}
	
	@RequestMapping("/home/shop_lossDetail.htm")
	public ModelAndView lossDetail(HttpServletRequest request,@RequestParam("shop_id")Integer shop_id,@RequestParam("month")String month){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/home/lossDetail");
		view.addObject("month", month);
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){
			//超级管理员
			Date nowtime = new Date();
			String monthdate = DateUtil.formatDate(nowtime, "yyyy-MM");
			Date startdate = DateUtil.parseDateStr(month, "yyyy-MM");
			Date querydate = DateUtil.dateAdd("d", -1, startdate);
			Date firstdate = DateUtil.parseDateStr("2017-01-01", "yyyy-MM-dd");
			if(startdate.before(firstdate)){
				return view;
			}
			Date enddate = null;
			if(monthdate.equals(month)){
				enddate = DateUtil.dateAdd("d", -1, nowtime);
				enddate = DateUtil.parseDateStr(DateUtil.formatDate(enddate, "yyyy-MM-dd 23:59:59"), "yyyy-MM-dd hh:mm:ss");
			}else{
				enddate = DateUtil.getLastDayOfMonth(startdate);
				enddate = DateUtil.parseDateStr(DateUtil.formatDate(enddate, "yyyy-MM-dd 23:59:59"), "yyyy-MM-dd hh:mm:ss");
			}
			List<ShopItemStock> lists = this.shopItemStockService.findlossDetail(shop_id,querydate,startdate,enddate);
			view.addObject("lists", lists);
			return view;
		}else{
			//管理员
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shop_id);
			if(null!=admin){
				Date nowtime = new Date();
				String monthdate = DateUtil.formatDate(nowtime, "yyyy-MM");
				Date startdate = DateUtil.parseDateStr(month, "yyyy-MM");
				Date querydate = DateUtil.dateAdd("d", -1, startdate);
				Date firstdate = DateUtil.parseDateStr("2017-01-01", "yyyy-MM-dd");
				if(startdate.before(firstdate)){
					return view;
				}
				Date enddate = null;
				if(monthdate.equals(month)){
					enddate = DateUtil.dateAdd("d", -1, nowtime);
					enddate = DateUtil.parseDateStr(DateUtil.formatDate(enddate, "yyyy-MM-dd 23:59:59"), "yyyy-MM-dd hh:mm:ss");
				}else{
					enddate = DateUtil.getLastDayOfMonth(startdate);
					enddate = DateUtil.parseDateStr(DateUtil.formatDate(enddate, "yyyy-MM-dd 23:59:59"), "yyyy-MM-dd hh:mm:ss");
				}
				List<ShopItemStock> lists = this.shopItemStockService.findlossDetail(shop_id,querydate,startdate,enddate);
				view.addObject("lists", lists);
				return view;
			}
		}
		//啥都不是
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
	}
	
	//网格管理后台
	@RequestMapping("reseau/shop_index.htm")
	public ModelAndView reseau_index(HttpServletRequest request,@RequestParam("reseau_id")Integer reseauId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/reseau/index");
		view.addObject("reseauId", reseauId);
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		String type = StringUtils.defaultIfEmpty(request.getParameter("type"), "3");
		view.addObject("type", type);
		if(null!=personUser){
			if(isadmin(personUser.getId()) || 3==personUser.getGread() && "客户经理".equals(personUser.getPost()) ||2==personUser.getGread() && "客户经理".equals(personUser.getPost()) ||4==personUser.getGread() && "客户经理".equals(personUser.getPost()) || personUser.getPost().equals("客户经理") && reseauId.equals(personUser.getReseauId())){
				Reseau reseau = this.reseauService.findById(reseauId);
				view.addObject("reseau", reseau);
				Date date = new Date();
				String string1 = DateUtil.formatDate(date, "yyyy-MM-dd 23:59:59");
				Date enddate = DateUtil.parseDateStr(string1, "yyyy-MM-dd HH:mm:ss");
				String string2 = DateUtil.formatDate(date, "yyyy-MM-dd 00:00:00");
				Date startdate = DateUtil.parseDateStr(string2, "yyyy-MM-dd HH:mm:ss");
				if (type.equals("3")) {
					ShopOrder order = this.shopOrderService.findConsumeMoneyByReseauIdAndDate(reseauId,startdate,enddate);
					view.addObject("model1", order);
					Long money = this.shopItemService.findTolalByReseauId(reseauId);
					view.addObject("model2", money);
					Date date2 = DateUtil.dateAdd("h", -24, date);
					ShopDailyReport shopDailyReport = this.shopDailyReportService.findByReseauIdAndDate(reseauId,date2);
					view.addObject("model3", shopDailyReport);
					ShopMonthReport shopMonthReport = this.shopMonthReportService.findByReseauIdAndDate(reseauId, date2);
					view.addObject("model5", shopMonthReport);
				} else {
					Date date2 = DateUtil.dateAdd("h", -24, date);
					FreeShopDailyReport freeShopDailyReport=this.freeShopDailyReportService.findByReseauIdAndDate(reseauId, date2);
					view.addObject("model4", freeShopDailyReport);
					FreeShopMonthReport freeShopMonthReport = this.freeShopMonthReportService.findByReseauIdAndDate(reseauId, date2);
					view.addObject("model6", freeShopMonthReport);
				}

				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	
	@RequestMapping("reseau/shop_workSheet.htm")
	public ModelAndView workSheet(HttpServletRequest request,@RequestParam("reseau_id")Integer reseauId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/reseau/workSheet");
		view.addObject("reseauId", reseauId);
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){
			if("客户经理".equals(puser.getPost()) && reseauId.equals(puser.getReseauId()) || "客户经理".equals(puser.getPost()) && 2==puser.getGread() || "客户经理".equals(puser.getPost()) && 4==puser.getGread() || "客户经理".equals(puser.getPost()) && 3==puser.getGread() || isadmin(puser.getId())){
				String query_date = request.getParameter("query_date");
				Date queryDate = null;
				if (StringUtils.isNotBlank(query_date)) {
					queryDate = DateUtil.parseDateStr(query_date, "yyyy-MM-dd");
					view.addObject("query_date", query_date);
				}else{
					queryDate = new Date();
					query_date = DateUtil.formatDate(queryDate, "yyyy-MM-dd");
					view.addObject("query_date", query_date);
				}
				List<Order> orders = this.orderService.findByReseauIdAndDate(queryDate,reseauId);
				view.addObject("orders", orders);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	
	@RequestMapping("reseau/shop_workSheet.json")
	@ResponseBody
	public LWResult workSheetjson(HttpServletRequest request,@RequestParam("reseau_id")Integer reseauId,@RequestParam("query_date")String queryDate){
		try {
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
			if(null!=personUser){
				if("客户经理".equals(personUser.getPost()) && reseauId.equals(personUser.getReseauId()) || "客户经理".equals(personUser.getPost()) && 2==personUser.getGread() || "客户经理".equals(personUser.getPost()) && 4==personUser.getGread() ||"客户经理".equals(personUser.getPost()) && 3==personUser.getGread() || isadmin(personUser.getId())){
					Date date = DateUtil.parseDateStr(queryDate, "yyyy-MM-dd");
					List<Order> orders = this.orderService.findByReseauIdAndDate(date,reseauId);
					return LWResult.ok(orders);
				}
			}
			return LWResult.build(201, "没有权限");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return LWResult.build(202, "异常");
	}
	
	@RequestMapping("reseau/shop_customerIndex.htm")
	public ModelAndView customerIndex(HttpServletRequest request,@RequestParam("reseau_id")Integer reseauId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/reseau/customerIndex");
		view.addObject("reseauId", reseauId);
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser){
			if("客户经理".equals(personUser.getPost()) && reseauId.equals(personUser.getReseauId()) || "客户经理".equals(personUser.getPost()) && 2==personUser.getGread() || "客户经理".equals(personUser.getPost()) && 4==personUser.getGread() ||"客户经理".equals(personUser.getPost()) && 3==personUser.getGread() || isadmin(personUser.getId())){
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}

	@RequestMapping("reseau/shop_warning.htm")
	public ModelAndView warning(HttpServletRequest request,@RequestParam("reseau_id")Integer reseauId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/reseau/warning");
		view.addObject("reseauId", reseauId);
		List<PersonUser> pusers = this.userService.findWarning(reseauId);
		if(null!=pusers){
			view.addObject("pusers", pusers);
			return view;
		}
		return new ModelAndView("redirect:/convenient/warning.htm");
	}
	
	@RequestMapping("reseau/shop_bianList.htm")
	public ModelAndView bianList(HttpServletRequest request,@RequestParam("reseau_id")Integer reseauId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/reseau/bianList");
		view.addObject("reseauId", reseauId);
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser){
			if("客户经理".equals(personUser.getPost()) && reseauId.equals(personUser.getReseauId()) || "客户经理".equals(personUser.getPost()) && 2==personUser.getGread() || "客户经理".equals(personUser.getPost()) && 4==personUser.getGread()  || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread() || isadmin(personUser.getId())){
				List<ShopOrder> lists = this.shopOrderService.findReseauCountInfo(reseauId);
				view.addObject("shops", lists);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	
	@RequestMapping("reseau/shop_bianDetail.htm")
	public ModelAndView bianDetail(HttpServletRequest request,@RequestParam("shopId")Integer shopId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/reseau/bianDetail");
		view.addObject("shopId", shopId);
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser){
			CustomerUser customerUser = this.customerService.findActivenessById(shopId);
			if("客户经理".equals(personUser.getPost()) && customerUser.getReseauId().equals(personUser.getReseauId()) || "客户经理".equals(personUser.getPost()) && 2==personUser.getGread() || "客户经理".equals(personUser.getPost()) && 4==personUser.getGread()  ||"客户经理".equals(personUser.getPost()) && 3==personUser.getGread() || isadmin(personUser.getId())){
				view.addObject("shopname", customerUser.getUserName());
				view.addObject("model1", customerUser.getContractDay());
				Long invoiceFee = this.debitNoteService.findBeforeTimeNotVerificationInvoiceFeeByShopId(shopId);
				Integer hasVerification = this.debitNoteService.findHasVerification(shopId);
				view.addObject("model2", invoiceFee);
				view.addObject("hasVerification", hasVerification);
				int num = this.afterSalesRecordService.findCountByUserIdNotCompelete(shopId);
				view.addObject("model3", num);
				ActualShopReport actual = this.shopOrderService.findactualShopReportByShopId(shopId);
				view.addObject("model4", actual);
				Date date = new Date();
				Date date2 = DateUtil.dateAdd("h", -24, date);
				ShopDailyReport shopDailyReport = this.shopDailyReportService.findByShopIdAndDate(shopId,date2);
				view.addObject("model5", shopDailyReport);
				ShopMonthReport shopMonthReport = this.shopMonthReportService.findByShopIdAndDate(shopId,date2);
				view.addObject("model6", shopMonthReport);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	
	@RequestMapping("reseau/shop_freeList.htm")
	public ModelAndView freeList(HttpServletRequest request,@RequestParam("reseau_id")Integer reseauId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/reseau/freeList");
		view.addObject("reseauId", reseauId);
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser){
			if("客户经理".equals(personUser.getPost()) && reseauId.equals(personUser.getReseauId()) || "客户经理".equals(personUser.getPost()) && 2==personUser.getGread() || "客户经理".equals(personUser.getPost()) && 4==personUser.getGread() ||"客户经理".equals(personUser.getPost()) && 3==personUser.getGread()|| isadmin(personUser.getId())){
				Date date = new Date();
				Date date2 = DateUtil.dateAdd("h", -26, date);
				List<FreeShopMonthReport> freeShopMonthReports = this.freeShopMonthReportService.findShopInfoByReseauIdAndDate(reseauId, date2);
				view.addObject("shops", freeShopMonthReports);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	
	@RequestMapping("reseau/shop_freeDetail.htm")
	public ModelAndView freeDetail(HttpServletRequest request,@RequestParam("shopId")Integer shopId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/reseau/freeDetail");
		view.addObject("shopId", shopId);
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser){
			CustomerUser customerUser = this.customerService.findActivenessById(shopId);
			if("客户经理".equals(personUser.getPost()) && customerUser.getReseauId().equals(personUser.getReseauId()) || "客户经理".equals(personUser.getPost()) && 2==personUser.getGread() || "客户经理".equals(personUser.getPost()) && 4==personUser.getGread() || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread() || isadmin(personUser.getId())){
				view.addObject("shopname", customerUser.getUserName());
				view.addObject("model1", customerUser);
				Long invoiceFee = this.debitNoteService.findBeforeTimeNotVerificationInvoiceFeeByShopId(shopId);
				Integer hasVerification = this.debitNoteService.findHasVerification(shopId);
				view.addObject("model2", invoiceFee);
				view.addObject("hasVerification", hasVerification);
				int num = this.afterSalesRecordService.findCountByUserIdNotCompelete(shopId);
				view.addObject("model3", num);
				Date date = new Date();
				Date date2 = DateUtil.dateAdd("h", -26, date);
				FreeShopMonthReport model4 = this.freeShopMonthReportService.findByUserIdAndSumDate(shopId, date2);
				view.addObject("model4", model4);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	
	//积分
	@RequestMapping("points/shop_index.htm")
	public ModelAndView pointsIndex(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/points/index");
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){
			//超级管理员
			view.addObject("shopId", shopId);
			MemberPoint memberPoint = this.memberPointService.findByid(shopId);
			view.addObject("point", memberPoint);
			return view;
		}else{
			//管理员
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
			if(null!=admin){
				MemberPoint memberPoint = this.memberPointService.findByid(shopId);
				view.addObject("point", memberPoint);
				view.addObject("shopId", shopId);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
	}
	
	@RequestMapping("points/shop_pointsDetail.htm")
	public ModelAndView pointsDetail(HttpServletRequest request,@RequestParam("shop_id")Integer shopId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/points/detail");
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser){
			//超级管理员
			List<MemberPointReport> reports = this.memberPointReportService.findByShopId(shopId);
			view.addObject("reports", reports);
			return view;
		}else{
			//管理员
			ShopAdmin admin = this.shopAdminService.findByAdminIdAndShopId(user.getId(), shopId);
			if(null!=admin){
				List<MemberPointReport> reports = this.memberPointReportService.findByShopId(shopId);
				view.addObject("reports", reports);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
	}
	
	@RequestMapping("points/shop_pointsMall.htm")
	public ModelAndView pointsMall(){
		ModelAndView view = new ModelAndView("shop/points/mall");
		return view;
	}
	
	/**
	 * 店铺搜索
	 * @param request
	 * @return
	 */
	@RequestMapping("manage/shop_lists.htm")
	public ModelAndView shoplist(HttpServletRequest request){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/manage/shoplist");
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		String name = request.getParameter("shop_name");
		if(null!=puser){
			if("公司".equals(name) || "网络".equals(name) || "科技".equals(name) ){
				view.addObject("shopName", name);
				view.addObject("flag", 1);
				return view;
			}
			if(isadmin(puser.getId()) || puser.getPost()!=null && puser.getPost().equals("客户经理") && puser.getGread()!=null && puser.getGread()==3 || puser.getPost()!=null && puser.getPost().equals("客服")){
				Integer cityId = puser.getCityId();
				if(!StringUtils.isEmpty(name) && null !=cityId){
					List<HashMap<String, Object>> shops = this.customerService.findAllShopByCondition(name,cityId);
					view.addObject("shops", shops);
				}
				if(null!=name && ""!=name){
					view.addObject("shopName", name);
				}
				return view;
			}
			
			if( puser.getPost()!=null && puser.getPost().equals("销售") && puser.getGread()!=null && puser.getGread()==3){
				Integer cityId = puser.getCityId();
				if(!StringUtils.isEmpty(name) && null !=cityId){
					List<Integer> ids = this.findzjIds(puser.getId());
					ids.add(puser.getId());
					List<HashMap<String, Object>> shops = this.customerService.findAllShopByConditionAndIds(name,cityId,ids);
					view.addObject("shops", shops);
				}
				if(null!=name && ""!=name){
					view.addObject("shopName", name);
				}
				return view;
			}
			if( puser.getPost()!=null && puser.getPost().equals("客户经理") && puser.getGread()!=null && puser.getGread()==2){
				Integer cityId = puser.getCityId();
				if(!StringUtils.isEmpty(name) && null !=cityId){
					List<Integer> ids = this.findjlreseauIds(puser.getId());
					
					if(null!=puser.getReseauId()){
						ids.add(puser.getReseauId());
					}
					List<HashMap<String, Object>> shops = this.customerService.findAllShopByConditionAndReseauIds(name,cityId,ids);
					view.addObject("shops", shops);
				}
				if(null!=name && ""!=name){
					view.addObject("shopName", name);
				}
				return view;
			}
			if( puser.getPost()!=null && puser.getPost().equals("客户经理") && puser.getGread()!=null && puser.getGread()==4){
				Integer cityId = puser.getCityId();
				if(!StringUtils.isEmpty(name) && null !=cityId){
					List<Integer> ids = this.findzgreseauIds(puser.getId());
					if(null!=puser.getReseauId()){
						ids.add(puser.getReseauId());
					}
					List<HashMap<String, Object>> shops = this.customerService.findAllShopByConditionAndReseauIds(name,cityId,ids);
					view.addObject("shops", shops);
				}
				if(null!=name && ""!=name){
					view.addObject("shopName", name);
				}
				return view;
			}
			if( puser.getPost()!=null && puser.getPost().equals("客户经理") && puser.getGread()!=null && puser.getGread()==1){
				Integer cityId = puser.getCityId();
				if(!StringUtils.isEmpty(name) && null !=cityId){
					if(null!=puser.getReseauId()){
						List<HashMap<String, Object>> shops = this.customerService.findAllShopByConditionAndReseauId(name,cityId,puser.getReseauId());
						view.addObject("shops", shops);
					}
				}
				if(null!=name && ""!=name){
					view.addObject("shopName", name);
				}
				return view;
			}
			
			if(puser.getPost()!=null && puser.getPost().equals("配送")){
				return new ModelAndView("peisong"); 
			}
		}else{
			List<ShopAdmin> lists = this.shopAdminService.findByAdminId(user.getId());
			if(lists.size()>1){
				List<HashMap<String, Object>> shops = this.shopAdminService.findAllShopByCondition(user.getId(),name);
				view.addObject("shops", shops);
				if(null!=name && ""!=name){
					view.addObject("shopName", name);
				}
				return view;
			}
		}
		
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
	} 
	
	//递归查找手下网格
//	public List<Integer> findreseauIds(Integer id){
//		List<Integer> list = new ArrayList<Integer>();
//		List<PersonUser> users = this.userService.findByManagerId(id);
//		if(!users.isEmpty()){
//			for (PersonUser personUser : users) {
//				if(1==personUser.getGread()){
//					if(null != personUser.getReseauId()){
//						list.add(personUser.getReseauId());
//					}
//				}else{
//					if(null != personUser.getReseauId()){
//						list.add(personUser.getReseauId());
//					}
//					List<Integer> findreseauIds = findreseauIds(personUser.getId());
//					if(!findreseauIds.isEmpty()){
//						list.addAll(findreseauIds);
//					}
//				}
//			}
//		}
//		return list;
//	}
	
	   //查找主管手下网格
		public List<Integer> findzgreseauIds(Integer id){
			List<Integer> list = new ArrayList<Integer>();
			List<PersonUser> users = this.userService.findByManagerId(id);
			if(!users.isEmpty()){
				for (PersonUser personUser : users) {
					if(null != personUser.getReseauId()){
						list.add(personUser.getReseauId());
					}
				}
			}
			return list;
		}
	
    //查找经理手下网格
	public List<Integer> findjlreseauIds(Integer id){
		List<Integer> list = new ArrayList<Integer>();
		List<PersonUser> users = this.userService.findByManagerId(id);
		if(!users.isEmpty()){
			for (PersonUser personUser : users) {
				if(null != personUser.getReseauId()){
					list.add(personUser.getReseauId());
				}
				List<PersonUser> list2 = this.userService.findByManagerId(personUser.getId());
				if(!list2.isEmpty()){
				for (PersonUser personUser2 : list2) {
					if(null != personUser2.getReseauId()){
						list.add(personUser2.getReseauId());
					}
				}
				}
			}
		}
		return list;
	}
	
	public List<Integer> findzgIds(Integer id){
		List<Integer> list = new ArrayList<Integer>();
		List<PersonUser> users = this.userService.findByManagerId(id);
		if(!users.isEmpty()){
			for (PersonUser personUser : users) {
					list.add(personUser.getId());
			}
		}
		return list;
	}
	public List<Integer> findjlIds(Integer id){
		List<Integer> list = new ArrayList<Integer>();
		List<PersonUser> users = this.userService.findByManagerId(id);
		if(!users.isEmpty()){
			for (PersonUser personUser : users) {
				list.add(personUser.getId());
				List<PersonUser> list2 = this.userService.findByManagerId(personUser.getId());
				if(!list2.isEmpty()){
				for (PersonUser personUser2 : list2) {
						list.add(personUser2.getId());
				}
				}
			}
		}
		return list;
	}
	public List<Integer> findzjIds(Integer id){
		List<Integer> list = new ArrayList<Integer>();
		List<PersonUser> users = this.userService.findByManagerId(id);
		if(!users.isEmpty()){
			for (PersonUser personUser : users) {
				list.add(personUser.getId());
				List<PersonUser> list2 = this.userService.findByManagerId(personUser.getId());
				if(!list2.isEmpty()){
					for (PersonUser personUser2 : list2) {
							list.add(personUser2.getId());
							List<PersonUser> list3 = this.userService.findByManagerId(personUser2.getId());
							if(!list3.isEmpty()){
								for (PersonUser personUser3 : list3) {
									list.add(personUser3.getId());
								}
							}
					}
				}
			}
		}
		return list;
	}
	
	/**
	 * 客户经理 经理层
	 * @param request
	 * @return
	 */
	@RequestMapping("manage/shop_jlclientmanager.htm")
	public ModelAndView jl_clientmanager(HttpServletRequest request){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		
		if(null!=puser && null!=puser.getCityId()){
			//超级管理员
			boolean b = isadmin(puser.getId());
			if(isadmin(puser.getId()) || null!=puser.getGread() && puser.getGread().equals(3) && null!=puser.getPost() && puser.getPost().equals("客户经理")){
		    	ModelAndView view = new ModelAndView("shop/manage/jl_clientmanager");
		    	Date date = new Date();
				String string1 = DateUtil.formatDate(date, "yyyy-MM-dd 23:59:59");
				Date enddate = DateUtil.parseDateStr(string1, "yyyy-MM-dd HH:mm:ss");
				String string2 = DateUtil.formatDate(date, "yyyy-MM-dd 00:00:00");
				Date startdate = DateUtil.parseDateStr(string2, "yyyy-MM-dd HH:mm:ss");
		    	ShopOrder order = this.shopOrderService.findConsumeMoneyByCityIdAndDate(puser.getCityId(),startdate,enddate);
		    	
		    	Integer newnum = this.customerService.findCurrentMonthNewNumByCityId(puser.getCityId());
		    	Integer contractnum = this.customerService.findContractnumNumByCityId(puser.getCityId());
				view.addObject("data", order);
				view.addObject("newnum", newnum);
				view.addObject("contractnum", contractnum);
				List<City> citys = this.userCityService.findCityByUserId(puser.getId());
		    	City city = this.cityService.findById(puser.getCityId());
				puser.setCitys(citys);
				puser.setCityName(city.getCityName());
				view.addObject("citys", citys);
				view.addObject("puser", puser);
		    	return view;
		    }
			if(null!=puser.getGread() && puser.getGread().equals(2) && null!=puser.getPost() && puser.getPost().equals("客户经理") || "客户经理".equals(puser.getPost()) && 4==puser.getGread()){
				//
				ModelAndView view = new ModelAndView("shop/manage/pqjl_clientmanager");
				List<Integer> ids = null;
				if(2==puser.getGread()){
					ids = this.findjlreseauIds(puser.getId());
				}
				if(4==puser.getGread()){
					ids = this.findzgreseauIds(puser.getId());
				}
				if(null!=puser.getReseauId()){
					ids.add(puser.getReseauId());
				}
				if(!ids.isEmpty()){
					Date date = new Date();
					String string1 = DateUtil.formatDate(date, "yyyy-MM-dd 23:59:59");
					Date enddate = DateUtil.parseDateStr(string1, "yyyy-MM-dd HH:mm:ss");
					String string2 = DateUtil.formatDate(date, "yyyy-MM-dd 00:00:00");
					Date startdate = DateUtil.parseDateStr(string2, "yyyy-MM-dd HH:mm:ss");
					ShopOrder order = this.shopOrderService.findConsumeMoneyByCityIdAndReseauIdsAndDate(puser.getCityId(),ids,startdate,enddate);
					view.addObject("data", order);
				}
				if(4==puser.getGread()){
					view.addObject("puls", 1);
				}else{
					view.addObject("puls", 2);
				}
		    	return view;

			}
			
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
	}
	
	/**
	 * 销售 经理层
	 * @param request
	 * @return
	 */
	@RequestMapping("manage/shop_jlsaleman.htm")
	public ModelAndView jl_saleman(HttpServletRequest request){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser && null!=puser.getCityId()){
			//超级管理员
			if(isadmin(puser.getId())){
		    	ModelAndView view = new ModelAndView("shop/manage/zj_saleman");
		    	int num = this.deliveryReceitpService.findTodayNumByCityId(puser.getCityId());
		    	Integer newnum = this.customerService.findCurrentMonthNewNumByCityId(puser.getCityId());
				Integer contractnum = this.customerService.findContractnumNumByCityId(puser.getCityId());
		    	List<City> citys = this.userCityService.findCityByUserId(puser.getId());
		    	City city = this.cityService.findById(puser.getCityId());
				puser.setCitys(citys);
				puser.setCityName(city.getCityName());
				view.addObject("citys", citys);
				view.addObject("puser", puser);
				view.addObject("num", num);
		    	view.addObject("newnum", newnum);
		    	view.addObject("contractnum", contractnum);
		    	return view;
		    }
			if(null!=puser.getGread() && puser.getGread().equals(3) && null!=puser.getPost() && puser.getPost().equals("销售")){
				ModelAndView view = new ModelAndView("shop/manage/zj_saleman");
				List<Integer> ids = this.findzjIds(puser.getId());
				ids.add(puser.getId());
				int num = this.deliveryReceitpService.findTodayNumByCityIdAndInterfaceIds(puser.getCityId(),ids);
				Integer newnum = this.customerService.findCurrentMonthNewNumByCityIdAndInterfaceIds(puser.getCityId(),ids);
				Integer contractnum = this.customerService.findContractnumNumByCityId(puser.getCityId());
				List<City> citys = this.userCityService.findCityByUserId(puser.getId());
		    	City city = this.cityService.findById(puser.getCityId());
				puser.setCitys(citys);
				puser.setCityName(city.getCityName());
				view.addObject("citys", citys);
				view.addObject("puser", puser);
				view.addObject("num", num);
				view.addObject("newnum", newnum);
				view.addObject("contractnum", contractnum);
				return view;
			}
			if(null!=puser.getGread() && puser.getGread().equals(2) && null!=puser.getPost() && puser.getPost().equals("销售")){
				ModelAndView view = new ModelAndView("shop/manage/jl_saleman");
				List<Integer> ids = this.findjlIds(puser.getId());
				ids.add(puser.getId());
				int num = this.deliveryReceitpService.findTodayNumByCityIdAndInterfaceIds(puser.getCityId(),ids);
				Integer newnum = this.customerService.findCurrentMonthNewNumByCityIdAndInterfaceIds(puser.getCityId(),ids);
				view.addObject("num", num);
				view.addObject("newnum", newnum);
		    	view.addObject("flag", 1);
		    	return view;
			}
			if(null!=puser.getGread() && puser.getGread().equals(4) && null!=puser.getPost() && puser.getPost().equals("销售")){
				ModelAndView view = new ModelAndView("shop/manage/jl_saleman");
				List<Integer> ids = this.findzgIds(puser.getId());
				ids.add(puser.getId());
				int num = this.deliveryReceitpService.findTodayNumByCityIdAndInterfaceIds(puser.getCityId(),ids);
				Integer newnum = this.customerService.findCurrentMonthNewNumByCityIdAndInterfaceIds(puser.getCityId(),ids);
				view.addObject("num", num);
				view.addObject("newnum", newnum);
		    	view.addObject("flag", 2);
		    	return view;
			}
			
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm"); 
	}
	
	
	@RequestMapping(value="shop_changeDefaultCity.do", method = RequestMethod.POST,produces="text/html;charset=UTF-8")
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
			return "200";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "201";
	}

	
	@RequestMapping("manage/shop_allreport.htm")
	public ModelAndView allreport(HttpServletRequest request){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/manage/allreport");
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		String type = StringUtils.defaultIfEmpty(request.getParameter("type"), "3");
		view.addObject("type", type);
		if(null!=personUser && personUser.getCityId() !=null){
			if( isadmin(personUser.getId()) || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread()){
				if (type.equals("3")) {
					ShopOrder order = this.shopOrderService.findTodayAllConsumeMoneyByCityId(personUser.getCityId());
					view.addObject("model1", order);
					Long money = this.shopItemService.findTolalByCityId(personUser.getCityId());
					view.addObject("model2", money);
					Date date = new Date();
					Date date2 = DateUtil.dateAdd("h", -24, date);
					ShopDailyReport shopDailyReport = this.shopDailyReportService.findByCityIdAndDate(personUser.getCityId(), date2);
					view.addObject("model3", shopDailyReport);
					ShopMonthReport shopMonthReport = this.shopMonthReportService.findByCityIdAndDate(personUser.getCityId(), date2);
					view.addObject("model5", shopMonthReport);
				} else {
					Date date = new Date();
					Date date2 = DateUtil.dateAdd("h", -24, date);
					FreeShopDailyReport freeShopDailyReport = this.freeShopDailyReportService.findByCityIdAndDate(personUser.getCityId(), date2);
					view.addObject("model4", freeShopDailyReport);
					FreeShopMonthReport freeShopMonthReport = this.freeShopMonthReportService.findBycityIdAndDate(personUser.getCityId(), date2);
					view.addObject("model6", freeShopMonthReport);
				}
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	
	@RequestMapping("manage/shop_reseaulist.htm")
	public ModelAndView reseaulist(HttpServletRequest request){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/manage/reseaulist");
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser && personUser.getCityId() !=null){
			if(isadmin(personUser.getId()) || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread()){
				Date date = new Date();
				String string1 = DateUtil.formatDate(date, "yyyy-MM-dd 23:59:59");
				Date enddate = DateUtil.parseDateStr(string1, "yyyy-MM-dd HH:mm:ss");
				String string2 = DateUtil.formatDate(date, "yyyy-MM-dd 00:00:00");
				Date startdate = DateUtil.parseDateStr(string2, "yyyy-MM-dd HH:mm:ss");
				Date date2 = DateUtil.dateAdd("h", -24, date);
				List<ShopOrder> lists =this.shopOrderService.findConsumeMoneyByDateAndCityId(personUser.getCityId(),startdate,enddate,date2);
				view.addObject("lists", lists);
				return view;
			}
			if("客户经理".equals(personUser.getPost()) && 2==personUser.getGread() || "客户经理".equals(personUser.getPost()) && 4==personUser.getGread()){
				List<Integer> ids = null;
				if(2==personUser.getGread()){
					ids = this.findjlreseauIds(personUser.getId());
				}
				if(4==personUser.getGread()){
					ids = this.findzgreseauIds(personUser.getId());
				}
				if(null!=personUser.getReseauId()){
					ids.add(personUser.getReseauId());
				}

				if(!ids.isEmpty()){
					Date date = new Date();
					String string1 = DateUtil.formatDate(date, "yyyy-MM-dd 23:59:59");
					Date enddate = DateUtil.parseDateStr(string1, "yyyy-MM-dd HH:mm:ss");
					String string2 = DateUtil.formatDate(date, "yyyy-MM-dd 00:00:00");
					Date startdate = DateUtil.parseDateStr(string2, "yyyy-MM-dd HH:mm:ss");
					Date date2 = DateUtil.dateAdd("h", -24, date);
					List<ShopOrder> lists =this.shopOrderService.findConsumeMoneyByReseauIdsAndDateAndCityId(ids,startdate,enddate,date2,personUser.getCityId());
					view.addObject("lists", lists);
				}

				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	
	@RequestMapping("manage/shop_contractlist.htm")
	public ModelAndView contractlist(HttpServletRequest request,@RequestParam("interfaceId")Integer interfaceId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/manage/contractlist");
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser && null!=personUser.getCityId()){
			if(isadmin(personUser.getId()) || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread() || "销售".equals(personUser.getPost()) && 3==personUser.getGread() || "销售".equals(personUser.getPost()) && 2==personUser.getGread() || "销售".equals(personUser.getPost()) && 1==personUser.getGread()){
				List<CustomerUser> shops = this.customerService.findCurrentMonthOpenByInterfaceIdAndCityId(interfaceId,personUser.getCityId());
				view.addObject("shops", shops);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	
	@RequestMapping("manage/shop_contractList.htm")
	public ModelAndView contractList(HttpServletRequest request,@RequestParam(defaultValue="0",value="interid")Integer interid,@RequestParam(defaultValue="0",value="type")Integer type){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/manage/contractlist");
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser && null!=personUser.getCityId()){
			if(interid>0 && type>0){
				if(type==2){
					List<Integer> ids = this.findjlIds(interid);
					ids.add(interid);
					List<CustomerUser> shops = this.customerService.findCurrentMonthOpenByCityIdAndInterfaceIds(personUser.getCityId(),ids);
					view.addObject("shops", shops);
					return view;
				}
				if(type==4){
					List<Integer> ids = this.findzgIds(interid);
					ids.add(interid);
					List<CustomerUser> shops = this.customerService.findCurrentMonthOpenByCityIdAndInterfaceIds(personUser.getCityId(),ids);
					view.addObject("shops", shops);
					return view;
				}
				if(type==1){
					List<CustomerUser> shops = this.customerService.findCurrentMonthOpenByInterfaceIdAndCityId(interid,personUser.getCityId());
					view.addObject("shops", shops);
					return view;
				}
			}else{
				if(isadmin(personUser.getId()) || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread()){
					List<CustomerUser> shops = this.customerService.findCurrentMonthOpenByCityId(personUser.getCityId());
					view.addObject("shops", shops);
					return view;
				}
				if("销售".equals(personUser.getPost()) && 3==personUser.getGread()){
					List<Integer> ids = this.findzjIds(personUser.getId());
					ids.add(personUser.getId());
					List<CustomerUser> shops = this.customerService.findCurrentMonthOpenByCityIdAndInterfaceIds(personUser.getCityId(),ids);
					view.addObject("shops", shops);
					return view;
				}
				if("销售".equals(personUser.getPost()) && 2==personUser.getGread()){
					List<Integer> ids = this.findjlIds(personUser.getId());
					ids.add(personUser.getId());
					List<CustomerUser> shops = this.customerService.findCurrentMonthOpenByCityIdAndInterfaceIds(personUser.getCityId(),ids);
					view.addObject("shops", shops);
					return view;
				}
				if("销售".equals(personUser.getPost()) && 4==personUser.getGread()){
					List<Integer> ids = this.findzgIds(personUser.getId());
					ids.add(personUser.getId());
					List<CustomerUser> shops = this.customerService.findCurrentMonthOpenByCityIdAndInterfaceIds(personUser.getCityId(),ids);
					view.addObject("shops", shops);
					return view;
				}
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	
	@RequestMapping("manage/shop_ygsaleman.htm")
	public ModelAndView ygsaleman(HttpServletRequest request,@RequestParam("saleId")Integer saleId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/manage/yg_saleman");
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser){
			if(isadmin(personUser.getId()) || "销售".equals(personUser.getPost()) && 3==personUser.getGread() || "销售".equals(personUser.getPost()) && 2==personUser.getGread() || "销售".equals(personUser.getPost()) && 4==personUser.getGread() || "销售".equals(personUser.getPost()) && 1==personUser.getGread()){
				PersonUser user2 = this.userService.findById(saleId);
				view.addObject("user", user2);
            	Integer newnum = this.customerService.findCurrentMonthShopNumByInterfaceAndCityId(user2.getId(),user2.getCityId());
				Integer contractnum = this.customerService.findContractnumNumByIdAndCityId(user2.getId(), user2.getCityId());
            	view.addObject("newnum", newnum);
            	view.addObject("contractnum", contractnum);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	@RequestMapping("manage/shop_dgsaleman.htm")
	public ModelAndView dgsaleman(HttpServletRequest request,@RequestParam("saleId")Integer saleId,@RequestParam("type")Integer type){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser){
			if(2==type){
				if(isadmin(personUser.getId()) || "销售".equals(personUser.getPost()) && 3==personUser.getGread()){
					ModelAndView view1 = new ModelAndView("shop/manage/dg_saleman");
					List<Integer> ids = this.findjlIds(saleId);
					ids.add(saleId);
					int num = this.deliveryReceitpService.findTodayNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
					Integer newnum = this.customerService.findCurrentMonthNewNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
					Integer submit = this.customerService.findCurrentMonthSubmitByCityIdAndInterfaceIds(personUser.getCityId(),ids);
					view1.addObject("num", num);
					view1.addObject("submit", submit);
					view1.addObject("newnum", newnum);
			    	view1.addObject("flag", 1);
			    	view1.addObject("saleid", saleId);
			    	view1.addObject("type", 2);
			    	return view1;
				}
			}
			if(4==type){
				if(isadmin(personUser.getId()) || "销售".equals(personUser.getPost()) && 3==personUser.getGread() || "销售".equals(personUser.getPost()) && 2==personUser.getGread()){
					ModelAndView view1 = new ModelAndView("shop/manage/dg_saleman");
					List<Integer> ids = this.findzgIds(saleId);
					ids.add(saleId);
					int num = this.deliveryReceitpService.findTodayNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
					Integer newnum = this.customerService.findCurrentMonthNewNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
					view1.addObject("num", num);
					view1.addObject("newnum", newnum);
			    	view1.addObject("flag", 2);
			    	view1.addObject("saleid", saleId);
			    	view1.addObject("type", 4);
			    	return view1;
				}
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	
	@RequestMapping("manage/shop_salemanlist.htm")
	public ModelAndView salemanlist(HttpServletRequest request,@RequestParam(defaultValue="0",value="saleId")Integer saleId){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/manage/salemanlist");
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		view.addObject("myId", personUser.getId());
		if(saleId>0){
			PersonUser user2 = this.userService.findById(saleId);
			if(null!=user2){
				if("销售".equals(user2.getPost()) && 2==user2.getGread()){
//					HashMap<String, Object> map = this.customerService.findSaleByCityIdAndId(personUser.getCityId(),user2.getId());
					HashMap<String, Object> map = this.deliveryReceitpService.findSaleNumByCityIdAndIds(personUser.getCityId(), user2.getId());
					view.addObject("map", map);



					List<PersonUser> users = this.userService.findSalesByCityIdAndGreadAndManageId(personUser.getCityId(),4,user2.getId());
					if(!users.isEmpty()){
						for (PersonUser personUser2 : users) {
							List<Integer> ids = this.findjlIds(personUser2.getId());
							ids.add(personUser2.getId());
//							int num = this.deliveryReceitpService.findTodayNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//							int contractnum = this.deliveryReceitpService.findMonthNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//							personUser2.setStatus(num);
//							personUser2.setContractnum(contractnum);x`
							CustomerUser cu = this.customerService.findNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);

							personUser2.setNum(cu.getNum());
							personUser2.setSevenNum(cu.getSevenNum());
							personUser2.setPassNum(cu.getPassNum());
							personUser2.setUnpassNum(cu.getUnpassNum());
//							Integer newnum = this.customerService.findCurrentMonthNewNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//							personUser2.setGread(newnum);
						}
					}
					view.addObject("lists", users);
					view.addObject("type", 4);
					return view;
				}
				if("销售".equals(user2.getPost()) && 4==user2.getGread()){
//					HashMap<String, Object> map = this.customerService.findSaleByCityIdAndId(personUser.getCityId(),user2.getId());

					HashMap<String, Object> map = this.deliveryReceitpService.findSaleNumByCityIdAndIds(personUser.getCityId(), user2.getId());
					view.addObject("map", map);
					List<PersonUser> users = this.userService.findSalesByCityIdAndGreadAndManageId(personUser.getCityId(),1,user2.getId());
					if(!users.isEmpty()){
						for (PersonUser personUser2 : users) {
							List<Integer> ids = this.findjlIds(personUser2.getId());
							ids.add(personUser2.getId());
//							int num = this.deliveryReceitpService.findTodayNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//							personUser2.setStatus(num);
//							int contractnum = this.deliveryReceitpService.findMonthNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//							personUser2.setContractnum(contractnum);
//							Integer newnum = this.customerService.findCurrentMonthNewNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//							personUser2.setGread(newnum);

							CustomerUser cu = this.customerService.findNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);

							personUser2.setNum(cu.getNum());
							personUser2.setSevenNum(cu.getSevenNum());
							personUser2.setPassNum(cu.getPassNum());
							personUser2.setUnpassNum(cu.getUnpassNum());
						}
					}
					view.addObject("lists", users);
					view.addObject("type", 1);
					return view;
				}
				
			}
			
		}else{
			if(null!=personUser && null != personUser.getCityId()){
				if(isadmin(personUser.getId())){
//					List<HashMap<String, Object>> maps = this.customerService.findSalesByCityIdAndPostAndGread(personUser.getCityId(),"销售",3);

					List<HashMap<String, Object>> maps = this.customerService.findSaleNumByCityIdAndPostAndGread(personUser.getCityId(), "销售", 3);
					view.addObject("maps", maps);
					List<PersonUser> users = this.userService.findSalesByCityIdAndPostAndGread(personUser.getCityId(),"销售",2);
					if(!users.isEmpty()){
						for (PersonUser personUser2 : users) {
							List<Integer> ids = this.findjlIds(personUser2.getId());
							ids.add(personUser2.getId());
//							int num = this.deliveryReceitpService.findTodayNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//							int contractnum = this.deliveryReceitpService.findMonthNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//							personUser2.setStatus(num);
//							personUser2.setContractnum(contractnum);
//							Integer newnum = this.customerService.findCurrentMonthNewNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//							personUser2.setGread(newnum);

							CustomerUser cu = this.customerService.findNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);

							personUser2.setNum(cu.getNum());
							personUser2.setSevenNum(cu.getSevenNum());
							personUser2.setPassNum(cu.getPassNum());
							personUser2.setUnpassNum(cu.getUnpassNum());
						}
					}
					view.addObject("lists", users);
					view.addObject("type", 2);
					return view;
				}
				if("销售".equals(personUser.getPost()) && 3==personUser.getGread()){
//					HashMap<String, Object> map = this.customerService.findSaleByCityIdAndId(personUser.getCityId(),personUser.getId());
					HashMap<String, Object> map = this.deliveryReceitpService.findSaleNumByCityIdAndIds(personUser.getCityId(), personUser.getId());
					view.addObject("map", map);
					List<PersonUser> users = this.userService.findSalesByCityIdAndGreadAndManageId(personUser.getCityId(),2,personUser.getId());
					if(!users.isEmpty()){
						for (PersonUser personUser2 : users) {
							List<Integer> ids = this.findjlIds(personUser2.getId());
							ids.add(personUser2.getId());
//							int num = this.deliveryReceitpService.findTodayNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//							int contractnum = this.deliveryReceitpService.findMonthNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//							personUser2.setStatus(num);
//							personUser2.setContractnum(contractnum);
//							Integer newnum = this.customerService.findCurrentMonthNewNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//							personUser2.setGread(newnum);

							CustomerUser cu = this.customerService.findNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);

							personUser2.setNum(cu.getNum());
							personUser2.setSevenNum(cu.getSevenNum());
							personUser2.setPassNum(cu.getPassNum());
							personUser2.setUnpassNum(cu.getUnpassNum());
						}
					}
					view.addObject("lists", users);
					view.addObject("type", 2);
					return view;
				}
				
				if("销售".equals(personUser.getPost()) && 2==personUser.getGread()){
//					HashMap<String, Object> map = this.customerService.findSaleByCityIdAndId(personUser.getCityId(),personUser.getId());
					HashMap<String, Object> map = this.deliveryReceitpService.findSaleNumByCityIdAndIds(personUser.getCityId(), personUser.getId());
					view.addObject("map", map);
					List<PersonUser> users = this.userService.findSalesByCityIdAndGreadAndManageId(personUser.getCityId(),4,personUser.getId());
					if(!users.isEmpty()){
						for (PersonUser personUser2 : users) {
							List<Integer> ids = this.findjlIds(personUser2.getId());
							ids.add(personUser2.getId());
//							int num = this.deliveryReceitpService.findTodayNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//							int contractnum = this.deliveryReceitpService.findMonthNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//							personUser2.setStatus(num);
//							personUser2.setContractnum(contractnum);
//							Integer newnum = this.customerService.findCurrentMonthNewNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//							personUser2.setGread(newnum);

							CustomerUser cu = this.customerService.findNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);

							personUser2.setNum(cu.getNum());
							personUser2.setSevenNum(cu.getSevenNum());
							personUser2.setPassNum(cu.getPassNum());
							personUser2.setUnpassNum(cu.getUnpassNum());
						}
					}
					view.addObject("lists", users);
					view.addObject("type", 4);
					return view;
				}
				if("销售".equals(personUser.getPost()) && 4==personUser.getGread()){
//					HashMap<String, Object> map = this.customerService.findSaleByCityIdAndId(personUser.getCityId(),personUser.getId());
					HashMap<String, Object> map = this.deliveryReceitpService.findSaleNumByCityIdAndIds(personUser.getCityId(), personUser.getId());
					view.addObject("map", map);
					List<PersonUser> users = this.userService.findSalesByCityIdAndGreadAndManageId(personUser.getCityId(),1,personUser.getId());
					if(!users.isEmpty()){
						for (PersonUser personUser2 : users) {
							List<Integer> ids = this.findjlIds(personUser2.getId());
							ids.add(personUser2.getId());
//							int num = this.deliveryReceitpService.findTodayNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//							int contractnum = this.deliveryReceitpService.findMonthNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//							personUser2.setStatus(num);
//							personUser2.setContractnum(contractnum);
//							Integer newnum = this.customerService.findCurrentMonthNewNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//							personUser2.setGread(newnum);

							CustomerUser cu = this.customerService.findNumByCityIdAndInterfaceIds(personUser.getCityId(),ids);

							personUser2.setNum(cu.getNum());
							personUser2.setSevenNum(cu.getSevenNum());
							personUser2.setPassNum(cu.getPassNum());
							personUser2.setUnpassNum(cu.getUnpassNum());
						}
					}
					view.addObject("lists", users);
					view.addObject("type", 1);
					return view;
				}
			}
			
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	
	@RequestMapping("manage/shop_deliverylist.htm")
	public ModelAndView deliverylist(HttpServletRequest request,@RequestParam(defaultValue="0",value="interid")Integer interid,@RequestParam(defaultValue="0",value="type")Integer type){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/manage/deliverylist");
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser && null != personUser.getCityId()){
			if(interid>0 && type>0){
				if(type==2){
					List<Integer> ids = this.findjlIds(interid);
					ids.add(interid);
					List<DeliveryReceitp> lists = this.deliveryReceitpService.findByCityIdAndInterfaceIds(personUser.getCityId(),ids);
					view.addObject("lists", lists);
					return view;
				}
				if(type==4){
					List<Integer> ids = this.findzgIds(interid);
					ids.add(interid);
					List<DeliveryReceitp> lists = this.deliveryReceitpService.findByCityIdAndInterfaceIds(personUser.getCityId(),ids);
					view.addObject("lists", lists);
					return view;
				}
				if(type==1){
					List<DeliveryReceitp> lists = this.deliveryReceitpService.findByCityIdAndInterfaceId(personUser.getCityId(),interid);
					view.addObject("lists", lists);
					return view;
				}
			}else{
				if(isadmin(personUser.getId()) ||"客户经理".equals(personUser.getPost()) && 3==personUser.getGread()){
					//运营总监 管理员 查看所有交接单
						List<DeliveryReceitp> lists = this.deliveryReceitpService.findByCityId(personUser.getCityId());
						view.addObject("lists", lists);
					return view;
				}
				if("销售".equals(personUser.getPost()) && 3==personUser.getGread()){
					List<Integer> ids = this.findzjIds(personUser.getId());
					ids.add(personUser.getId());
						List<DeliveryReceitp> lists = this.deliveryReceitpService.findByCityIdAndInterfaceIds(personUser.getCityId(),ids);
						view.addObject("lists", lists);
					return view;
				}
				if("销售".equals(personUser.getPost()) && 2==personUser.getGread()){
					List<Integer> ids = this.userService.findIdsByManagerId(personUser.getId());
					ids.add(personUser.getId());
						List<DeliveryReceitp> lists = this.deliveryReceitpService.findByCityIdAndInterfaceIds(personUser.getCityId(),ids);
						view.addObject("lists", lists);
					return view;
				}
				if("销售".equals(personUser.getPost()) && 4==personUser.getGread()){
					List<Integer> ids = this.userService.findIdsByManagerId(personUser.getId());
					ids.add(personUser.getId());
						List<DeliveryReceitp> lists = this.deliveryReceitpService.findByCityIdAndInterfaceIds(personUser.getCityId(),ids);
						view.addObject("lists", lists);
					return view;
				}
				if("销售".equals(personUser.getPost()) && 1==personUser.getGread()){
					//销售 员工 查看个人的交接单
					List<DeliveryReceitp> lists = this.deliveryReceitpService.findByCityIdAndInterfaceId(personUser.getCityId(),personUser.getId());
					view.addObject("lists", lists);
					return view;
				}
			}
			
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	
	@RequestMapping("manage/shop_adddelivery.htm")
	public ModelAndView adddelivery(HttpServletRequest request){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser && null != personUser.getCityId()){
			if(isadmin(personUser.getId()) || "销售".equals(personUser.getPost()) && 3==personUser.getGread() || "销售".equals(personUser.getPost()) && 4==personUser.getGread() || "销售".equals(personUser.getPost()) && 2==personUser.getGread() || "销售".equals(personUser.getPost()) && 1==personUser.getGread() || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread()){
				ModelAndView view = new ModelAndView("shop/manage/adddelivery");
				List<CityRegion> region = this.cityRegionService.findByCityId(personUser.getCityId());
				view.addObject("regions", region);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	
	
	@RequestMapping("manage/shop_updatedelivery.htm")
	public ModelAndView updatedelivery(HttpServletRequest request,@RequestParam("id")Integer id){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser && null != personUser.getCityId()){
			if(isadmin(personUser.getId()) || "销售".equals(personUser.getPost()) && 3==personUser.getGread() || "销售".equals(personUser.getPost()) && 2==personUser.getGread() || "销售".equals(personUser.getPost()) && 4==personUser.getGread() || "销售".equals(personUser.getPost()) && 1==personUser.getGread() || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread()){
				ModelAndView view = new ModelAndView("shop/manage/adddelivery");
				DeliveryReceitp dto =this.deliveryReceitpService.findById(id);
				List<CityRegion> region = this.cityRegionService.findByCityId(personUser.getCityId());
				view.addObject("regions", region);
				view.addObject("dto", dto);
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}
	
	@RequestMapping(value="manage/shop_adddelivery.do",method = RequestMethod.POST)
	@ResponseBody
	public String adddeliverydo(HttpServletRequest request,DeliveryReceitp dto){
		try {
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
			if(null!=personUser && null != personUser.getCityId()){
				if(isadmin(personUser.getId()) || "销售".equals(personUser.getPost()) && 3==personUser.getGread() || "销售".equals(personUser.getPost()) && 2==personUser.getGread() || "销售".equals(personUser.getPost()) && 4==personUser.getGread() || "销售".equals(personUser.getPost()) && 1==personUser.getGread() || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread()){
						if(null!=dto.getId() && dto.getId()>0){
						//gengxin
						dto.setUpdatedTime(new Date());
						if (dto.getIscommit() != null && dto.getIscommit().equals(2)){
							dto.setSubmitTime(new Date());
						}
						this.deliveryReceitpService.update(dto);
						return "200";
					}else{
						//xinjian
						if(dto.getShopNum()==null){
							dto.setShopNum(1);
						}
						dto.setStatus(1);
						dto.setInterfaceId(personUser.getId());
						dto.setCityId(personUser.getCityId());
						dto.setCreatedTime(new Date());
						dto.setUpdatedTime(dto.getCreatedTime());
						if (dto.getIscommit() != null && dto.getIscommit().equals(2)){
							dto.setSubmitTime(new Date());
						}
						this.deliveryReceitpService.insert(dto);
						return "200";
					}
				}
			}
		} catch (Exception e) {
			logger.error("交接单保存失败"+e);
		}
		return "201";
	}
	
	@RequestMapping(value="manage/shop_deletedelivery.do",method = RequestMethod.POST)
	@ResponseBody
	public String deletedelivery(HttpServletRequest request,@RequestParam("id")Integer id){
		try {
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
			if(null!=personUser && null != personUser.getCityId()){
				if(isadmin(personUser.getId()) || "销售".equals(personUser.getPost()) && 3==personUser.getGread() || "销售".equals(personUser.getPost()) && 2==personUser.getGread() || "销售".equals(personUser.getPost()) && 3==personUser.getGread() || "销售".equals(personUser.getPost()) && 1==personUser.getGread() || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread()){
					this.deliveryReceitpService.delete(id);
					return "200";
				}
			}
		} catch (Exception e) {
			logger.error("交接单删除失败"+e);
		}
		return "201";
	}

	@RequestMapping("manage/shop_achievements.htm")
	public ModelAndView achievement(HttpServletRequest request,@RequestParam(defaultValue="0",value="interid")Integer interid,@RequestParam(defaultValue="0",value="type")Integer type){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/manage/achievement");
		String grade2 = request.getParameter("grade2");
		view.addObject("grade2", grade2);
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser && null != personUser.getCityId()){
			if(interid>0 && type>0){
				if(type==2){
					List<Integer> ids = this.findjlIds(interid);
					ids.add(interid);
					CustomerUser now = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 0);
					view.addObject("now", now);
					CustomerUser yesterday = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 1);
					view.addObject("yesterday", yesterday);
					CustomerUser month = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 2);
					view.addObject("month", month);

					CustomerUser monthNew = this.customerService.findCustomerUserInfoByCityIdAndInterfaceIds(personUser.getCityId(), ids);
					view.addObject("monthNew", monthNew);

					CustomerUser seven = this.customerService.findSevenInfoByCityIdAndInterfaceIds(personUser.getCityId(), ids);
					view.addObject("seven", seven);

					return view;
				}
				if(type==4){
					List<Integer> ids = this.findzgIds(interid);
					ids.add(interid);
//					List<DeliveryReceitp> lists = this.deliveryReceitpService.findByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//					view.addObject("lists", lists);

					CustomerUser now = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 0);
					view.addObject("now", now);
					CustomerUser yesterday = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 1);
					view.addObject("yesterday", yesterday);
					CustomerUser month = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 2);
					view.addObject("month", month);

					CustomerUser monthNew = this.customerService.findCustomerUserInfoByCityIdAndInterfaceIds(personUser.getCityId(), ids);
					view.addObject("monthNew", monthNew);

					CustomerUser seven = this.customerService.findSevenInfoByCityIdAndInterfaceIds(personUser.getCityId(), ids);
					view.addObject("seven", seven);

					return view;
				}
				if(type==1){
					List<Integer> ids = new ArrayList<Integer>();
					ids.add(interid);
					CustomerUser now = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 0);
					view.addObject("now", now);
					CustomerUser yesterday = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 1);
					view.addObject("yesterday", yesterday);
					CustomerUser month = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 2);
					view.addObject("month", month);

					CustomerUser monthNew = this.customerService.findCustomerUserInfoByCityIdAndInterfaceIds(personUser.getCityId(), ids);
					view.addObject("monthNew", monthNew);

					CustomerUser seven = this.customerService.findSevenInfoByCityIdAndInterfaceIds(personUser.getCityId(), ids);
					view.addObject("seven", seven);

//					List<DeliveryReceitp> lists = this.deliveryReceitpService.findByCityIdAndInterfaceId(personUser.getCityId(),interid);
//					view.addObject("lists", lists);
					return view;
				}
			}else{
				if(isadmin(personUser.getId()) ||"客户经理".equals(personUser.getPost()) && 3==personUser.getGread()){
					//运营总监 管理员 查看所有交接单
//					List<DeliveryReceitp> lists = this.deliveryReceitpService.findByCityId(personUser.getCityId());
//					view.addObject("lists", lists);

					CustomerUser now = this.customerService.findDeliveryInfoByCityIdAndType(personUser.getCityId(), 0);
					view.addObject("now", now);
					CustomerUser yesterday = this.customerService.findDeliveryInfoByCityIdAndType(personUser.getCityId(), 1);
					view.addObject("yesterday", yesterday);
					CustomerUser month = this.customerService.findDeliveryInfoByCityIdAndType(personUser.getCityId(), 2);
					view.addObject("month", month);

					CustomerUser monthNew = this.customerService.findCustomerUserInfoByCityId(personUser.getCityId());
					view.addObject("monthNew", monthNew);

					CustomerUser seven = this.customerService.findSevenInfoByCityId(personUser.getCityId());
					view.addObject("seven", seven);

					return view;
				}
				if("销售".equals(personUser.getPost()) && 3==personUser.getGread()){
					List<Integer> ids = this.findzjIds(personUser.getId());
					ids.add(personUser.getId());
//					List<DeliveryReceitp> lists = this.deliveryReceitpService.findByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//					view.addObject("lists", lists);


					CustomerUser now = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 0);
					view.addObject("now", now);
					CustomerUser yesterday = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 1);
					view.addObject("yesterday", yesterday);
					CustomerUser month = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 2);
					view.addObject("month", month);

					CustomerUser monthNew = this.customerService.findCustomerUserInfoByCityIdAndInterfaceIds(personUser.getCityId(), ids);
					view.addObject("monthNew", monthNew);

					CustomerUser seven = this.customerService.findSevenInfoByCityIdAndInterfaceIds(personUser.getCityId(), ids);
					view.addObject("seven", seven);


					return view;
				}
				if("销售".equals(personUser.getPost()) && 2==personUser.getGread()){
					List<Integer> ids = this.userService.findIdsByManagerId(personUser.getId());
					ids.add(personUser.getId());
//					List<DeliveryReceitp> lists = this.deliveryReceitpService.findByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//					view.addObject("lists", lists);

					CustomerUser now = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 0);
					view.addObject("now", now);
					CustomerUser yesterday = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 1);
					view.addObject("yesterday", yesterday);
					CustomerUser month = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 2);
					view.addObject("month", month);
					CustomerUser monthNew = this.customerService.findCustomerUserInfoByCityIdAndInterfaceIds(personUser.getCityId(), ids);
					view.addObject("monthNew", monthNew);

					CustomerUser seven = this.customerService.findSevenInfoByCityIdAndInterfaceIds(personUser.getCityId(), ids);
					view.addObject("seven", seven);

					return view;
				}
				if("销售".equals(personUser.getPost()) && 4==personUser.getGread()){
					List<Integer> ids = this.userService.findIdsByManagerId(personUser.getId());
					ids.add(personUser.getId());
//					List<DeliveryReceitp> lists = this.deliveryReceitpService.findByCityIdAndInterfaceIds(personUser.getCityId(),ids);
//					view.addObject("lists", lists);

					CustomerUser now = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 0);
					view.addObject("now", now);
					CustomerUser yesterday = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 1);
					view.addObject("yesterday", yesterday);
					CustomerUser month = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 2);
					view.addObject("month", month);

					CustomerUser monthNew = this.customerService.findCustomerUserInfoByCityIdAndInterfaceIds(personUser.getCityId(), ids);
					view.addObject("monthNew", monthNew);

					CustomerUser seven = this.customerService.findSevenInfoByCityIdAndInterfaceIds(personUser.getCityId(), ids);
					view.addObject("seven", seven);
					return view;
				}
				if("销售".equals(personUser.getPost()) && 1==personUser.getGread()){
					//销售 员工 查看个人的交接单
//					List<DeliveryReceitp> lists = this.deliveryReceitpService.findByCityIdAndInterfaceId(personUser.getCityId(),personUser.getId());
//
//					view.addObject("lists", lists);


					List<Integer> ids = new ArrayList<Integer>();
					ids.add(personUser.getId());
					CustomerUser now = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 0);
					view.addObject("now", now);
					CustomerUser yesterday = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 1);
					view.addObject("yesterday", yesterday);
					CustomerUser month = this.customerService.findDeliveryInfoByCityIdAndInterfaceIdsAndType(personUser.getCityId(), ids, 2);
					view.addObject("month", month);

					CustomerUser monthNew = this.customerService.findCustomerUserInfoByCityIdAndInterfaceIds(personUser.getCityId(), ids);
					view.addObject("monthNew", monthNew);

					CustomerUser seven = this.customerService.findSevenInfoByCityIdAndInterfaceIds(personUser.getCityId(), ids);
					view.addObject("seven", seven);
					return view;
				}
			}

		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}

	//财务
	@RequestMapping("debitNote/shop_index.htm")
	public ModelAndView debit_index(HttpServletRequest request){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/debit/index");
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser && personUser.getCityId() !=null){
			if(isadmin(personUser.getId()) || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread()){
			    DebitNoteDemo debit = this.debitNoteService.findDebitCusNumAndFeeByCityIdAndReseauId(personUser.getCityId(),null);
			    DebitNoteDemo invo = this.debitNoteService.findInvoiceCusNumAndFeeByCityIdAndReseauId(personUser.getCityId(),null);
			    DebitNoteDemo invoage = this.debitNoteService.findInvoAgeByCityIdAndReseauId(personUser.getCityId(),null);
			    DebitNoteDemo debitage = this.debitNoteService.findDebitAgeByCityIdAndReseauId(personUser.getCityId(),null);
			    DebitNoteDemo invinfo = this.debitNoteService.findInvoiceListByCityIdAndReseauId(personUser.getCityId(),null);
			    DebitNoteDemo debitinfo = this.debitNoteService.findDebitListByCityIdAndReseauId(personUser.getCityId(),null);
			    Date date = new Date();
			    Date date2 = DateUtil.dateAdd("m", -1, date);
			    Long money1 = this.debitNoteService.findMoneyByDateAndCityId(date,personUser.getCityId());
			    Long money2 = this.debitNoteService.findMoneyByDateAndCityId(date2,personUser.getCityId());
			    view.addObject("debit", debit);
			    view.addObject("invo", invo);
			    view.addObject("invoage", invoage);
			    view.addObject("debitage", debitage);
			    view.addObject("invinfo", invinfo);
			    view.addObject("debitinfo", debitinfo);
			    view.addObject("money1", money1);
			    view.addObject("money2", money2);
			    return view;
			}

            if("客户经理".equals(personUser.getPost()) && 1==personUser.getGread() && null != personUser.getReseauId()){
            	 DebitNoteDemo debit = this.debitNoteService.findDebitCusNumAndFeeByCityIdAndReseauId(personUser.getCityId(),personUser.getReseauId());
 			    DebitNoteDemo invo = this.debitNoteService.findInvoiceCusNumAndFeeByCityIdAndReseauId(personUser.getCityId(),personUser.getReseauId());
 			    DebitNoteDemo invoage = this.debitNoteService.findInvoAgeByCityIdAndReseauId(personUser.getCityId(),personUser.getReseauId());
 			    DebitNoteDemo debitage = this.debitNoteService.findDebitAgeByCityIdAndReseauId(personUser.getCityId(),personUser.getReseauId());
 			    DebitNoteDemo invinfo = this.debitNoteService.findInvoiceListByCityIdAndReseauId(personUser.getCityId(),personUser.getReseauId());
 			    DebitNoteDemo debitinfo = this.debitNoteService.findDebitListByCityIdAndReseauId(personUser.getCityId(),personUser.getReseauId());
 			    Date date = new Date();
			    Date date2 = DateUtil.dateAdd("m", -1, date);
			    Long money1 = this.debitNoteService.findMoneyByDateAndCityIdAndReseauId(date,personUser.getCityId(),personUser.getReseauId());
			    Long money2 = this.debitNoteService.findMoneyByDateAndCityIdAndReseauId(date2,personUser.getCityId(),personUser.getReseauId());
 			    view.addObject("debit", debit);
			    view.addObject("invo", invo);
			    view.addObject("invoage", invoage);
			    view.addObject("debitage", debitage);
			    view.addObject("invinfo", invinfo);
			    view.addObject("debitinfo", debitinfo);
			    view.addObject("money1", money1);
			    view.addObject("money2", money2);
			    return view;
            }
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}

	@RequestMapping("debitNote/shop_invoiceList.htm")
	public ModelAndView invoiceList(HttpServletRequest request){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/debit/invoiceList");
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser && personUser.getCityId() !=null){
			if(isadmin(personUser.getId()) || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread()){
				PageHelper.startPage(1, 50);
				List<DebitNoteDemo> lists = this.debitNoteService.findInvoicesByCityIdAndReseauId(personUser.getCityId(),null);
				PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(lists);
				view.addObject("lists", info.getList());
				view.addObject("pagenum", info.getPageNum());
				view.addObject("isHasNextPage", info.isHasNextPage());
				return view;
			}
			if("客户经理".equals(personUser.getPost()) && 1==personUser.getGread() && null != personUser.getReseauId()){
				PageHelper.startPage(1, 50);
				List<DebitNoteDemo> lists = this.debitNoteService.findInvoicesByCityIdAndReseauId(personUser.getCityId(),personUser.getReseauId());
				PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(lists);
				view.addObject("lists", info.getList());
				view.addObject("pagenum", info.getPageNum());
				view.addObject("isHasNextPage", info.isHasNextPage());
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}

	@RequestMapping(value="debitNote/shop_invoiceList.json")
	@ResponseBody
	public LWResult invoiceList(HttpServletRequest request,@RequestParam(value="page")Integer page,@RequestParam(value="rows")Integer rows){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser && personUser.getCityId() !=null){
			if(isadmin(personUser.getId()) || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread()){
				PageHelper.startPage(page, rows);
				List<DebitNoteDemo> lists = this.debitNoteService.findInvoicesByCityIdAndReseauId(personUser.getCityId(),null);
				PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(lists);
				return LWResult.ok(info);
			}
			if("客户经理".equals(personUser.getPost()) && 1==personUser.getGread() && null != personUser.getReseauId()){
				PageHelper.startPage(page, rows);
				List<DebitNoteDemo> lists = this.debitNoteService.findInvoicesByCityIdAndReseauId(personUser.getCityId(),personUser.getReseauId());
				PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(lists);
				return LWResult.ok(info);
			}
		}
		return LWResult.build(201, "权限不足");
	}

	@RequestMapping("debitNote/shop_debitList.htm")
	public ModelAndView debitList(HttpServletRequest request){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/debit/debitList");
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser && personUser.getCityId() !=null){
			if(isadmin(personUser.getId()) || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread()){
				PageHelper.startPage(1, 50);
				List<DebitNoteDemo> lists = this.debitNoteService.findDebitsByCityIdAndReseauId(personUser.getCityId(),null);
				PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(lists);
				view.addObject("lists", info.getList());
				view.addObject("pagenum", info.getPageNum());
				view.addObject("isHasNextPage", info.isHasNextPage());
				return view;
			}
			if("客户经理".equals(personUser.getPost()) && 1==personUser.getGread() && null != personUser.getReseauId()){
				PageHelper.startPage(1, 50);
				List<DebitNoteDemo> lists = this.debitNoteService.findDebitsByCityIdAndReseauId(personUser.getCityId(),personUser.getReseauId());
				PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(lists);
				view.addObject("lists", info.getList());
				view.addObject("pagenum", info.getPageNum());
				view.addObject("isHasNextPage", info.isHasNextPage());
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}

	@RequestMapping(value="debitNote/shop_debitList.json")
	@ResponseBody
	public LWResult debitList(HttpServletRequest request,@RequestParam(value="page")Integer page,@RequestParam(value="rows")Integer rows){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser && personUser.getCityId() !=null){
			if(isadmin(personUser.getId()) || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread()){
				PageHelper.startPage(page, rows);
				List<DebitNoteDemo> lists = this.debitNoteService.findDebitsByCityIdAndReseauId(personUser.getCityId(),null);
				PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(lists);
				return LWResult.ok(info);
			}
			if("客户经理".equals(personUser.getPost()) && 1==personUser.getGread() && null != personUser.getReseauId()){
				PageHelper.startPage(page, rows);
				List<DebitNoteDemo> lists = this.debitNoteService.findDebitsByCityIdAndReseauId(personUser.getCityId(),personUser.getReseauId());
				PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(lists);
				return LWResult.ok(info);
			}
		}
		return LWResult.build(201, "权限不足");
	}

	@RequestMapping("debitNote/shop_debitageList.htm")
	public ModelAndView debitageList(HttpServletRequest request,@RequestParam(defaultValue="1",value="flag")Integer flag,@RequestParam(defaultValue="1",value="param")Integer param){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/debit/debitageList");
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		view.addObject("flag", flag);
		view.addObject("param", param);
		if(null!=personUser && personUser.getCityId() !=null){
			if(isadmin(personUser.getId()) || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread()){
				PageHelper.startPage(1, 50);
				List<DebitNoteDemo> lists = this.debitNoteService.findDebitagesByCityIdAndReseauIdAndflag(personUser.getCityId(),null,flag,param);
				PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(lists);
				view.addObject("lists", info.getList());
				view.addObject("pagenum", info.getPageNum());
				view.addObject("isHasNextPage", info.isHasNextPage());
				return view;
			}
			if("客户经理".equals(personUser.getPost()) && 1==personUser.getGread() && null != personUser.getReseauId()){
				PageHelper.startPage(1, 50);
				List<DebitNoteDemo> lists = this.debitNoteService.findDebitagesByCityIdAndReseauIdAndflag(personUser.getCityId(),personUser.getReseauId(),flag,param);
				PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(lists);
				view.addObject("lists", info.getList());
				view.addObject("pagenum", info.getPageNum());
				view.addObject("isHasNextPage", info.isHasNextPage());
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}

	@RequestMapping(value="debitNote/shop_debitageList.json")
	@ResponseBody
	public LWResult shop_debitageList(HttpServletRequest request,@RequestParam(value="page")Integer page,@RequestParam(value="rows")Integer rows,@RequestParam(defaultValue="1",value="flag")Integer flag,@RequestParam(defaultValue="1",value="param")Integer param){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser && personUser.getCityId() !=null){
			if(isadmin(personUser.getId()) || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread()){
				PageHelper.startPage(page, rows);
				List<DebitNoteDemo> lists = this.debitNoteService.findDebitagesByCityIdAndReseauIdAndflag(personUser.getCityId(),null,flag,param);
				PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(lists);
				return LWResult.ok(info);
			}
			if("客户经理".equals(personUser.getPost()) && 1==personUser.getGread() && null != personUser.getReseauId()){
				PageHelper.startPage(page, rows);
				List<DebitNoteDemo> lists = this.debitNoteService.findDebitagesByCityIdAndReseauIdAndflag(personUser.getCityId(),personUser.getReseauId(),flag,param);
				PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(lists);
				return LWResult.ok(info);
			}
		}
		return LWResult.build(201, "权限不足");
	}

	@RequestMapping("debitNote/shop_invoiceageList.htm")
	public ModelAndView invoiceageList(HttpServletRequest request,@RequestParam(defaultValue="1",value="flag")Integer flag,@RequestParam(defaultValue="1",value="param")Integer param){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		ModelAndView view = new ModelAndView("shop/debit/invoiceageList");
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		view.addObject("flag", flag);
		view.addObject("param", param);
		if(null!=personUser && personUser.getCityId() !=null){
			if(isadmin(personUser.getId()) || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread()){
				PageHelper.startPage(1, 50);
				List<DebitNoteDemo> lists = this.debitNoteService.findInvoiceagesByCityIdAndReseauIdAndflag(personUser.getCityId(),null,flag,param);
				PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(lists);
				view.addObject("lists", info.getList());
				view.addObject("pagenum", info.getPageNum());
				view.addObject("isHasNextPage", info.isHasNextPage());
				return view;
			}
			if("客户经理".equals(personUser.getPost()) && 1==personUser.getGread() && null != personUser.getReseauId()){
				PageHelper.startPage(1, 50);
				List<DebitNoteDemo> lists = this.debitNoteService.findInvoiceagesByCityIdAndReseauIdAndflag(personUser.getCityId(),personUser.getReseauId(),flag,param);
				PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(lists);
				view.addObject("lists", info.getList());
				view.addObject("pagenum", info.getPageNum());
				view.addObject("isHasNextPage", info.isHasNextPage());
				return view;
			}
		}
		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}

	@RequestMapping(value="debitNote/shop_invoiceageList.json")
	@ResponseBody
	public LWResult invoiceageList(HttpServletRequest request,@RequestParam(value="page")Integer page,@RequestParam(value="rows")Integer rows,@RequestParam(defaultValue="1",value="flag")Integer flag,@RequestParam(defaultValue="1",value="param")Integer param){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
		if(null!=personUser && personUser.getCityId() !=null){
			if(isadmin(personUser.getId()) || "客户经理".equals(personUser.getPost()) && 3==personUser.getGread()){
				PageHelper.startPage(page, rows);
				List<DebitNoteDemo> lists = this.debitNoteService.findInvoiceagesByCityIdAndReseauIdAndflag(personUser.getCityId(),null,flag,param);
				PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(lists);
				return LWResult.ok(info);
			}
			if("客户经理".equals(personUser.getPost()) && 1==personUser.getGread() && null != personUser.getReseauId()){
				PageHelper.startPage(page, rows);
				List<DebitNoteDemo> lists = this.debitNoteService.findInvoiceagesByCityIdAndReseauIdAndflag(personUser.getCityId(),personUser.getReseauId(),flag,param);
				PageInfo<DebitNoteDemo> info = new PageInfo<DebitNoteDemo>(lists);
				return LWResult.ok(info);
			}
		}
		return LWResult.build(201, "权限不足");
	}
	@RequestMapping(value="manage/shop_select.htm")
	public ModelAndView selectShop(HttpServletRequest request){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		String supplierName = request.getParameter("supplierName");
		if(null!=puser && null!=puser.getCityId()) {
			if (isadmin(puser.getId()) || puser.getPost().equals("仓管")) {
				ModelAndView view = new ModelAndView("shop/manage/selectShop");
				view.addObject("supplierName", supplierName);

				if (supplierName != null && !supplierName.equals("")) {
					List<Purchase> purchases = this.purchaseService.findPurchasesBySupplierName(supplierName, puser.getCityId());

					view.addObject("purchases", purchases);
				} else {
					view.addObject("purchases", null);
				}
				return view;
			}
		}

		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}

	@RequestMapping(value="manage/shop_purchaseStock.htm")
	public ModelAndView shopPurchaseStock(HttpServletRequest request, @RequestParam("id")String id){
		ShopUser user = WXSessionHelper.getShopUser(request.getSession());
		PersonUser puser = this.userService.findByOpenId(user.getOpenid());
		if(null!=puser && null!=puser.getCityId()) {
			if (isadmin(puser.getId()) || puser.getPost().equals("仓管")) {
				ModelAndView view = new ModelAndView("shop/manage/purchaseStock");

				if (id != null && !id.equals("")) {
					List<PurchaseItem> list = this.purchaseService.findPurchaseItem(id);

					view.addObject("list", list);
				} else {
					view.addObject("list", null);
				}
				view.addObject("id", id);
				return view;
			}
		}

		return new ModelAndView("redirect:/convenient/shop_noPeivilege.htm");
	}

	@RequestMapping(value = "/manage/shop_purchaseStock.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String purchaseStockAdd(HttpServletRequest request) {

		try {
			ShopUser user = WXSessionHelper.getShopUser(request.getSession());
			PersonUser personUser = this.userService.findByOpenId(user.getOpenid());
			if (null == personUser.getCityId()) {
				return "你还未设置默认城市，联系管理员设置";
			}

			String purchaseId = request.getParameter("purchaseId");
			Purchase purchaseOld = this.purchaseService.findPurchaseById(purchaseId);
			if (null == purchaseOld || !personUser.getCityId().equals(purchaseOld.getCityId())) {
				return "采购订单不存在";
			}

			String[] itemIds = request.getParameter("itemIds").split(",");
			String[] purchasedNums = request.getParameter("purchasedNums").split(",");
			String[] ids = request.getParameter("ids").split(",");
			String idsStr = request.getParameter("ids") + "0";
			boolean end = false;
			for (int i = 0; itemIds.length > i; i++) {
				PurchaseItem oldPurchaseItem = this.purchaseItemService.findbyId(Integer.valueOf(ids[i]));
				if (oldPurchaseItem == null) {
					return "采购订单不存在";
				}
				PurchaseItem purchaseItem = new PurchaseItem();
				purchaseItem.setId(Integer.valueOf(ids[i]));
				Integer oldPurchasedNum = oldPurchaseItem.getPurchasedNum() == null ? 0 : oldPurchaseItem.getPurchasedNum();
				purchaseItem.setPurchasedNum(Integer.valueOf(purchasedNums[i]) + oldPurchasedNum);
				purchaseItem.setUseNum(Integer.valueOf(purchasedNums[i]));
				if (oldPurchaseItem.getPurchaseNum() > purchaseItem.getPurchasedNum()) {
					purchaseItem.setStockFlag(1);
				} else {
					purchaseItem.setStockFlag(2);
					purchaseItem.setStatus(1);
				}

				this.purchaseItemService.update(purchaseItem);
			}
			Integer endCount = this.purchaseItemService.findEndCountByPurchaseId(purchaseId);
			if (endCount == 0) {
				purchaseOld.setStatus(1);
			} else {
				purchaseOld.setStatus(6);
			}

			purchaseService.updateByPrimaryKeySelective(purchaseOld);

			List<PurchaseItem> purchaseItemList = new ArrayList<PurchaseItem>();
			for (String id : ids) {
				List<PurchaseItem> purchaseItem = this.purchaseItemService.findInfoByStock(id);
				if (purchaseItem!=null) {
					purchaseItemList.add(purchaseItem.get(0));
				}
			}
			addStorage(personUser, purchaseId, purchaseItemList, request);
		} catch (Throwable e) {
			logger.error("采购订单入库失败", e);
			return "采购订单入库失败";
		}

		return "200";
	}

	private String addStorage(PersonUser personUser, String number, List<PurchaseItem> purchaseItemList, HttpServletRequest request) {
		try {
			Storage storage = new Storage();
			storage.setCityId(personUser.getCityId());
			List<StorageItem> skuList = new ArrayList<StorageItem>();
			String numbers = request.getParameter("numbers");
			storage.setNumber("From purchase:" + numbers + ":" + number);
			long totalFeeAll = 0;

			for (PurchaseItem purchaseItem : purchaseItemList) {
				int itemId = purchaseItem.getItemId();
				Item item = itemService.findById(itemId);

				String size = ItemSizeEnum.BUY_SIZE.getName();

				long buyPrice = item.calPrice(size);

				int buyNum = purchaseItem.getUseNum();
				int num = item.calNum(buyNum, size);


				long totalFee = buyNum * buyPrice;
				String sizeValue = item.calSize(size);
				StorageItem sku = new StorageItem();
				// sku_id
				sku.setItemId(Integer.valueOf(itemId));

				// sku_name
				sku.setItemName(item.getItemName());
				sku.setTotalFee(totalFee);
				sku.setNum(num);
				sku.setBuyNum(buyNum);
				sku.setBuyPrice(buyPrice);
				sku.setSize(size);
				sku.setSizeValue(sizeValue);

				skuList.add(sku);

				totalFeeAll = totalFeeAll + totalFee;
			}

			storage.setUserId(personUser.getId());
			storage.setLastModUser(personUser.getId());

			storage.setExecutedTime(new Date());

			Integer supplierId = purchaseItemList.get(0).getSupplierId();
			if (supplierId != null) {
				storage.setSupplierId(supplierId);
			}
			storage.setTotalFee(totalFeeAll);
			storage.setStorageItemList(skuList);
			storageService.create(storage);

			this.saveLog(request.getSession(), storage, "新增入库单，id:" + storage.getId(), storage.getId(), personUser.getUserName(), personUser.getCityId());
			return "添加成功";
		} catch (Exception e) {
			logger.error("添加入库单失败", e);
			return e.getMessage();
		}
	}

	private void saveLog(HttpSession session,Storage dto,String content,Integer shopId,String name,Integer cityId){
		try{
			SysShopLog sysLog = new SysShopLog();
			sysLog.setContent(content);
			sysLog.setCreateTime(new Date());
			sysLog.setUserId(WXSessionHelper.getShopUserId(session));
			sysLog.setDataType(LogDataTypeEnum.STORAGE.getName());
			sysLog.setDataId(dto.getId().toString());
			sysLog.setShopId(shopId);
			sysLog.setUserName(name);
			sysLog.setCityId(cityId);
			String dataContent = new Gson().toJson(dto,
					new TypeToken<Storage>() {
					}.getType());

			sysLog.setDataContent(dataContent);

			logService.createLog(sysLog);
		}catch(Exception e){
			logger.error("保存日志失败",e);
		}
	}

}
