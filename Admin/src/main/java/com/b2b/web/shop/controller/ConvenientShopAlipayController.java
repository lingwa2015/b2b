package com.b2b.web.shop.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.b2b.common.domain.*;
import com.b2b.service.*;
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

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserUserinfoShareRequest;
import com.alipay.api.request.ZhimaMerchantSingleDataUploadRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserUserinfoShareResponse;
import com.alipay.api.response.ZhimaMerchantSingleDataUploadResponse;
import com.b2b.common.util.DateUtil;
import com.b2b.web.alipay.constants.AlipayServiceEnvConstants;
import com.b2b.web.alipay.factory.AlipayAPIClientFactory;
import com.b2b.web.alipay.pay.AlipayConfig;
import com.b2b.web.alipay.pay.AlipayNotify;
import com.b2b.web.alipay.pay.AlipaySubmit;
import com.b2b.web.alipay.util.RequestUtil;
import com.b2b.web.util.ApiService;
import com.b2b.web.util.MD5Util;
import com.b2b.web.util.NumberTool;
import com.b2b.web.wx.util.CommonUtil;
import com.b2b.web.wx.util.Properties;
import com.b2b.web.wx.util.WXSessionHelper;
import com.b2b.web.wx.util.pay.OrderUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@RequestMapping("convenient/ali")
@Controller
public class ConvenientShopAlipayController {
	private static final Logger logger = LoggerFactory
			.getLogger(ConvenientShopAlipayController.class);

	private static String url = "https://openauth.alipay.com/oauth2/publicAppAuthorize.htm?app_id=APPID&scope=auth_userinfo&redirect_uri=ENCODED_URL&state=STATE";

	private static final ObjectMapper MAPPER = new ObjectMapper();

	@Autowired
	private Properties properties;

	@Autowired
	private ShopAliUserService shopAliUserService;

	@Autowired
	private ShopItemService shopItemService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private SeniorSetService seniorSetService;

	@Autowired
	private TranConsumeService tranConsumeService;

	@Autowired
	private ShopOrderService shopOrderService;

	@Autowired
	private NotifyStateService notifyStateService;

	@Autowired
	private ShopOrderItemService shopOrderItemService;

	@Autowired
	private ItemCategoryService itemCategoryService;

	@Autowired
	ApiService apiService;

	@Autowired
	CustomerCameraService customerCameraService;

	@Autowired
    RedReceiveService redReceiveService;

    @Autowired
    RedPacketService redPacketService;

    @Autowired
    RedPacketTypeService redPacketTypeService;

    @Autowired
    RedShopOrderService redShopOrderService;

    @Autowired
    RedAccountService redAccountService;

    @Autowired
    RedPacketCustomerService redPacketCustomerService;

    @Autowired
	ShopLayerService shopLayerService;
    
    @Autowired
    NewCustomerActivityService newCustomerActivityService;
    
    @Autowired
    ItemSalesPromotionService itemSalesPromotionService;
    
    @Autowired
    ShopDiscountSettingService shopDiscountSettingService;

	//前台购买页面
	@RequestMapping(value="shop_item.htm")
	public ModelAndView item(HttpServletRequest request,RedirectAttributes attr,@RequestParam(value="cid",required=false)String cId){
		ShopAliUser user = WXSessionHelper.getShopAliUser(request.getSession());
		//ShopAliUser user = this.shopAliUserService.findById(2);
		Object shopId = request.getParameter("shop_id");
		String shopid="-1";
		if(null!=shopId){
			shopid=shopId.toString();
		}
		String cid = "-1";
		if(null!=cId){
			cid = cId.toString();
		}
		if(null!=user){
			attr.addAttribute("shop_id", shopid);
			return new ModelAndView("redirect:/convenient/ali/shop_itemlist.htm");
		}
		String state = shopid +","+cid;
		String appid = properties.ALI_APPID;
		String redirect_uri = properties.ONLINE_URL+"convenient/ali/shop_indexOauth.htm";
		String urlEncode = CommonUtil.urlEncodeUTF8(redirect_uri);
		String requestUrl = url.replace("APPID", appid).replace("ENCODED_URL", urlEncode).replace("STATE", state);
		return new ModelAndView("redirect:"+requestUrl+"");
	}

	@RequestMapping("/shop_indexOauth.htm")
	public ModelAndView indexOauth(HttpServletRequest request,HttpServletResponse response,RedirectAttributes attr){
		Map<String, String> params = RequestUtil.getRequestParams(request);
        //2. 获得authCode
        String authCode = params.get("auth_code");
        String state = request.getParameter("state");
        String[] split = state.split(",");
		String shopid = split[0];
		String cid = split[1];
        try {
            //3. 利用authCode获得authToken
            AlipaySystemOauthTokenRequest oauthTokenRequest = new AlipaySystemOauthTokenRequest();
            oauthTokenRequest.setCode(authCode);
            oauthTokenRequest.setGrantType(AlipayServiceEnvConstants.GRANT_TYPE);
            AlipayClient alipayClient = AlipayAPIClientFactory.getAlipayClient();
            AlipaySystemOauthTokenResponse oauthTokenResponse = alipayClient
                .execute(oauthTokenRequest);

            //成功获得authToken
            if (null != oauthTokenResponse && oauthTokenResponse.isSuccess()) {

                //4. 利用authToken获取用户信息
                AlipayUserUserinfoShareRequest userinfoShareRequest = new AlipayUserUserinfoShareRequest();
                AlipayUserUserinfoShareResponse userinfoShareResponse = alipayClient.execute(
                    userinfoShareRequest, oauthTokenResponse.getAccessToken());

                //成功获得用户信息
                if (null != userinfoShareResponse && userinfoShareResponse.isSuccess()) {
                    //这里仅是简单打印， 请开发者按实际情况自行进行处理
                    ShopAliUser user = this.shopAliUserService.findByOpenId(userinfoShareResponse.getAlipayUserId());
                    if(null == user){
                    	user = new ShopAliUser();
                    	user.setCity(userinfoShareResponse.getCity());
                    	user.setHeadImgurl(userinfoShareResponse.getAvatar());
                    	user.setCreated(new Date());
                    	user.setNickName(userinfoShareResponse.getNickName());
                    	user.setOpenid(userinfoShareResponse.getAlipayUserId());
                    	user.setProvince(userinfoShareResponse.getProvince());
                    	Integer sex = null ;
                    	if("M".equalsIgnoreCase(userinfoShareResponse.getGender())){
                    		sex = 1;
                    	}else if ("F".equalsIgnoreCase(userinfoShareResponse.getGender())) {
                    		sex = 2;
						}
                    	user.setSex(sex);
                    	this.shopAliUserService.save(user);
                    	WXSessionHelper.setShopAliUser(request.getSession(), user);
                    }else{
                    	user.setCity(userinfoShareResponse.getCity());
                    	user.setHeadImgurl(userinfoShareResponse.getAvatar());
                    	user.setCreated(new Date());
                    	user.setNickName(userinfoShareResponse.getNickName());
                    	user.setOpenid(userinfoShareResponse.getAlipayUserId());
                    	user.setProvince(userinfoShareResponse.getProvince());
                    	Integer sex = null ;
                    	if("M".equalsIgnoreCase(userinfoShareResponse.getGender())){
                    		sex = 1;
                    	}else if ("F".equalsIgnoreCase(userinfoShareResponse.getGender())) {
                    		sex = 2;
						}
                    	user.setSex(sex);
                    	this.shopAliUserService.update(user);
                    	WXSessionHelper.setShopAliUser(request.getSession(), user);
                    }
                    attr.addAttribute("shop_id", shopid);
                    attr.addAttribute("cid", cid);
            		return new ModelAndView("redirect:/convenient/ali/shop_itemlist.htm");

                } else {
                    //这里仅是简单打印， 请开发者按实际情况自行进行处理
                    System.out.println("获取用户信息失败");

                }
            } else {
                //这里仅是简单打印， 请开发者按实际情况自行进行处理
                System.out.println("authCode换取authToken失败");
            }
        } catch (AlipayApiException alipayApiException) {
            //自行处理异常
            alipayApiException.printStackTrace();
        }
        attr.addAttribute("shop_id", shopid);
        attr.addAttribute("cid", cid);
		return new ModelAndView("redirect:/convenient/shop_items.htm");
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

	@RequestMapping("shop_itemlist.htm")
	public ModelAndView itemlist(HttpServletRequest request,RedirectAttributes attr,@RequestParam("shop_id")String id){
		ShopAliUser user = WXSessionHelper.getShopAliUser(request.getSession());
		//ShopAliUser user = this.shopAliUserService.findById(2);
		if(null!=user){
			ModelAndView view = new ModelAndView("shop/ali/items");
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
					List<String> asList = null ;
					if(null!=customerUser && !StringUtils.isEmpty(customerUser.getCheckStr())){
						asList = Arrays.asList(customerUser.getCheckStr().split(","));
					}
					if(null!=customerUser && !asList.contains("2")){
						ModelAndView view1 = new ModelAndView("shop/index/error");
						view1.addObject("sign", "微信");
						return view1;
					}
					SeniorSet seniorSet = this.seniorSetService.findById(user.getLastComeId());
					Long money = this.tranConsumeService.findLossMoney(user.getLastComeId());
					if(null==money){
						money = 0l;
					}
					//List<ShopItem> shopItems= new ArrayList<ShopItem>();
					//if(null!=seniorSet && null!=seniorSet.getIslayer() && 1==seniorSet.getIslayer()){
					List<ShopItem>	shopItems=this.shopItemService.findOftenBuyItemByBuyerIdAndShopIdAndSign(user.getId(),user.getLastComeId(),2);

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
							logger.error("限时打折失败"+e.getMessage());
						}
					}else if(null!=seniorSet && null!=seniorSet.getFreeFee() && null !=seniorSet.getDayOrMonth() && 2==seniorSet.getType()){
						int dayOrMonth = seniorSet.getDayOrMonth();
						Date date = new Date();
						if(dayOrMonth==1){
							//每月
							Date query = DateUtil.getFirstDayOfMonth(date);
							Long freeFee = this.shopOrderService.findFreeFee(query,user.getId(),2);
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
							Long freeFee = this.shopOrderService.findFreeFee(query,user.getId(),2);
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
					RedAccount account = this.redAccountService.findByUserIdAndType(user.getId(), 2);
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
							view.setViewName("shop/ali/waimai");
						}else{
							view.setViewName("shop/ali/layerItem");
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
						
						ShopAliUser aliUser = this.shopAliUserService.findById(user.getId());
						if(null!=aliUser.getIsfirst() && 1==aliUser.getIsfirst()){
							
						}else{
							aliUser.setIsfirst(1);
							this.shopAliUserService.update(aliUser);
							WXSessionHelper.setShopAliUser(request.getSession(), aliUser);
							view.addObject("bar_flag", 1);
						}
						List<ShopLayer> shopLayers = this.shopLayerService.findByShopIdAndStatus(user.getLastComeId());
						view.addObject("shopItems",shopItems);
						view.addObject("map", map);
						view.addObject("shopLayers", shopLayers);
					}else{
						if(customerUser.getId()==1385){
							view.setViewName("shop/ali/new_item");
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
					view.addObject("shopid", user.getLastComeId());
					return view;
				}else{
					//无值，提示扫码
					 ModelAndView view2 = new ModelAndView("shop/ali/scan");
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
					List<ShopItem>	shopItems=this.shopItemService.findOftenBuyItemByBuyerIdAndShopIdAndSign(user.getId(),shopid,2);

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
						logger.error("限时打折失败"+e.getMessage());
					}
				}else if(null!=seniorSet && null!=seniorSet.getFreeFee() && null !=seniorSet.getDayOrMonth() && 2==seniorSet.getType()){
					int dayOrMonth = seniorSet.getDayOrMonth();
					Date date = new Date();
					if(dayOrMonth==1){
						//每月
						Date query = DateUtil.getFirstDayOfMonth(date);
						Long freeFee = this.shopOrderService.findFreeFee(query,user.getId(),2);
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
						Long freeFee = this.shopOrderService.findFreeFee(query,user.getId(),2);
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
				RedAccount account = this.redAccountService.findByUserIdAndType(user.getId(), 2);
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
				this.shopAliUserService.update(user);
				WXSessionHelper.setShopAliUser(request.getSession(), user);
				
				if(null!=seniorSet && null!=seniorSet.getIslayer() && 1==seniorSet.getIslayer()){
					if(customerUser.getId()==1385){
						view.setViewName("shop/ali/waimai");
					}else{
						view.setViewName("shop/ali/layerItem");
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
					ShopAliUser aliUser = this.shopAliUserService.findById(user.getId());
					if(null!=aliUser.getIsfirst() && 1==aliUser.getIsfirst()){
						
					}else{
						aliUser.setIsfirst(1);
						this.shopAliUserService.update(aliUser);
						WXSessionHelper.setShopAliUser(request.getSession(), aliUser);
						view.addObject("bar_flag", 1);
					}
					List<ShopLayer> shopLayers = this.shopLayerService.findByShopIdAndStatus(shopid);
					view.addObject("shopItems",shopItems);
					view.addObject("map", map);
					view.addObject("shopLayers", shopLayers);
				}else{
					if(customerUser.getId()==1385){
						view.setViewName("shop/ali/new_item");
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
				return view;
			}
		}else{
			attr.addAttribute("shop_id", id);
			return new ModelAndView("redirect:/convenient/shop_items.htm");
		}
	}

	@RequestMapping(value="shop_pay.do", method = RequestMethod.POST, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String pay(HttpServletRequest request,HttpServletResponse response,RedirectAttributes attr,@RequestParam("shop_id")Integer shopId,@RequestParam("data")String data,@RequestParam("free_flag")Integer free_flag,@RequestParam("redd_flag")Integer red_flag){
			Gson gson = new Gson();
			logger.info(data);
			List<ShopItem> datas = gson.fromJson(data,  new TypeToken<List<ShopItem>>() {}.getType());
			if(datas.isEmpty()){
				return "201";
			}
			ShopAliUser user = WXSessionHelper.getShopAliUser(request.getSession());
			//ShopAliUser user = this.shopAliUserService.findById(2);
			if(null==user){
				return "202";
			}
			String orderNo = OrderUtil.GetOrderNumber();
			ShopOrder order = new ShopOrder();
			order.setBuyerId(user.getId());
			if(null!=user.getNickName()){
				order.setBuyerName(user.getNickName());
			}else{
				order.setBuyerName("无昵称");
			}
			order.setCreatedTime(new Date());
			order.setId("BLD"+orderNo);
			order.setShopId(shopId);
			order.setSign(2);
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
					Long freeFee = this.shopOrderService.findFreeFee(query,user.getId(),2);
					sheng = seniorSet.getFreeFee()-freeFee;
				}else if(dayOrMonth==2){
					String formatDate = DateUtil.formatDate(date, "yyyy-MM-dd 00:00:00");
					Date query = DateUtil.parseDateStr(formatDate, "yyyy-MM-dd hh:mm:ss");
					Long freeFee = this.shopOrderService.findFreeFee(query,user.getId(),2);
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
				RedAccount account = this.redAccountService.findByUserIdAndType(user.getId(), 2);
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
			logger.info("开始创建订单1" + order.getId());
			this.shopOrderService.create(order);
			logger.info("创建订单结束1"+ order.getId());
			if(order.getTotalPrice()>0){
				Map<String, String> sParaTemp = new HashMap<String, String>();
				sParaTemp.put("service", AlipayConfig.service);
				sParaTemp.put("partner", AlipayConfig.partner);
				sParaTemp.put("seller_id", AlipayConfig.seller_id);
				sParaTemp.put("_input_charset", AlipayConfig.input_charset);
				sParaTemp.put("payment_type", AlipayConfig.payment_type);
				sParaTemp.put("notify_url", AlipayConfig.notify_url);
				sParaTemp.put("return_url", AlipayConfig.return_url);
				sParaTemp.put("out_trade_no", order.getId());
				sParaTemp.put("subject", "自助便利店零食");
				sParaTemp.put("total_fee", NumberTool.toYuanNumber(order.getTotalPrice()));
				sParaTemp.put("show_url", "");
				//sParaTemp.put("app_pay","Y");//启用此参数可唤起钱包APP支付。
				sParaTemp.put("body", "");
				String sHtmlText = AlipaySubmit.buildRequest(sParaTemp,"get","确认");
				return sHtmlText;

			}else{
				if(null!=order.getRedPrice() && order.getRedPrice()>0){
					this.redAccountService.updateAccountMoneyByUserIdAndType(user.getId(), 2, -order.getRedPrice());
				}
				for (ShopOrderItem item : orderItemList) {
					this.shopItemService.updateNum(item.getItemId(),item.getNum());
				}
				StringBuffer sbHtml = new StringBuffer();
				String order_no = "BLD"+orderNo;
		        sbHtml.append("<form id=\"myform\" name=\"myform\" action=\"/convenient/shop_paySuccess.htm\" method=\"get\">");
		        sbHtml.append("<input type=\"hidden\" name=\"orderNo\" value=\"" + order_no + "\"/>");
		        //submit按钮控件请不要含有name属性
		        sbHtml.append("<input type=\"submit\" value=\"确认\" style=\"display:none;\"></form>");
		        sbHtml.append("<script>document.forms['myform'].submit();</script>");
		        return sbHtml.toString();
			}

	}

	@RequestMapping(value="shop_notify.do")
	@ResponseBody
	public void notify_url(HttpServletRequest request,HttpServletResponse response){
		try {
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "gbk");
				params.put(name, valueStr);
			}

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			logger.info("回掉进来" + trade_no+"----------------->"+trade_status);
			if(AlipayNotify.verify(params)){//验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				//请在这里加上商户的业务逻辑程序代码

				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				logger.info("回掉进来验证成功" + trade_no+"----------------->"+trade_status);
				if(trade_status.equals("TRADE_FINISHED")){
					//判断该笔订单是否在商户网站中已经做过处理
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
						//如果有做过处理，不执行商户的业务程序

					//注意：
					//退款日期超过可退款期限后（如三个月可退款），支付宝系统发送该交易状态通知
				} else if (trade_status.equals("TRADE_SUCCESS")){
					//判断该笔订单是否在商户网站中已经做过处理
					logger.info("处理回调" + trade_status);
					NotifyState notifyState = this.notifyStateService.findById(out_trade_no);
					if(null==notifyState){
						this.shopOrderService.changeStatus(out_trade_no);
						ShopOrder order = this.shopOrderService.findById(out_trade_no);
						if(null!=order.getShopId()){
							
							try {
								ShopAliUser shopAliUser = this.shopAliUserService.findById(order.getBuyerId());
								AlipayClient alipayClient = new DefaultAlipayClient("https://openapi.alipay.com/gateway.do",properties.ALI_APPID,properties.ALI_PRIVATE_KEY,"json","GBK",properties.ALIPAY_PUBLIC_KEY,"RSA2");
								ZhimaMerchantSingleDataUploadRequest alirequest = new ZhimaMerchantSingleDataUploadRequest();
								alirequest.setBizContent("{" +
								"\"data\":\"{'biz_date':'"+DateUtil.formatDate(order.getCreatedTime(), "yyyy-MM-dd")+"','user_credentials_type':'W','user_credentials_no':'"+shopAliUser.getOpenid()+"','user_name':'','order_no':'"+out_trade_no+"','order_start_date':'"+DateUtil.formatDate(order.getCreatedTime(), "yyyy-MM-dd")+"','order_status':'2','merchant_name':'','bill_type':'100','bill_desc':'','bill_amt':'"+NumberTool.toYuanNumber(order.getTotalPrice())+"','bill_last_date':'"+DateUtil.formatDate(order.getCreatedTime(), "yyyy-MM-dd")+"','bill_payoff_date':'"+DateUtil.formatDate(order.getCreatedTime(), "yyyy-MM-dd hh:mm:ss")+"','memo':''}\"," +
								"\"primary_keys\":\""+out_trade_no+"\"," +
								"\"scene_code\":\"8\"" +
								"  }");
								ZhimaMerchantSingleDataUploadResponse aliresponse = alipayClient.execute(alirequest);
							} catch (AlipayApiException e1) {
								logger.error("添加信用足迹失败"+e1.getMessage());
							}
							
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
					}
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//请务必判断请求时的total_fee、seller_id与通知时获取的total_fee、seller_id为一致的
						//如果有做过处理，不执行商户的业务程序

					//注意：
					//付款完成后，支付宝系统发送该交易状态通知
				}

				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——

				response.getWriter().print("success");	//请不要修改或删除

				//////////////////////////////////////////////////////////////////////////////////////////
			}else{//验证失败
				response.getWriter().print("fail");
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@RequestMapping(value="shop_return.htm", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	public ModelAndView return_url(HttpServletRequest request,HttpServletResponse response){
		//获取支付宝GET过来反馈信息
		ModelAndView view = new ModelAndView("shop/index/success");
		view.addObject("pay_way", 2);
		try {
			Map<String,String> params = new HashMap<String,String>();
			Map requestParams = request.getParameterMap();
			for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
				String name = (String) iter.next();
				String[] values = (String[]) requestParams.get(name);
				String valueStr = "";
				for (int i = 0; i < values.length; i++) {
					valueStr = (i == values.length - 1) ? valueStr + values[i]
							: valueStr + values[i] + ",";
				}
				//乱码解决，这段代码在出现乱码时使用。如果mysign和sign不相等也可以使用这段代码转化
				//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
				params.put(name, valueStr);
			}

			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以下仅供参考)//
			//商户订单号

			String out_trade_no = new String(request.getParameter("out_trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//支付宝交易号

			String trade_no = new String(request.getParameter("trade_no").getBytes("ISO-8859-1"),"UTF-8");

			//交易状态
			String trade_status = new String(request.getParameter("trade_status").getBytes("ISO-8859-1"),"UTF-8");

			String total_fee = new String(request.getParameter("total_fee").getBytes("ISO-8859-1"),"UTF-8");
			//获取支付宝的通知返回参数，可参考技术文档中页面跳转同步通知参数列表(以上仅供参考)//
			String notify_time = new String(request.getParameter("notify_time").getBytes("ISO-8859-1"),"UTF-8");


			//计算得出通知验证结果
			boolean verify_result = AlipayNotify.verify(params);

			if(verify_result){//验证成功
				//////////////////////////////////////////////////////////////////////////////////////////
				//请在这里加上商户的业务逻辑程序代码

				//——请根据您的业务逻辑来编写程序（以下代码仅作参考）——
				if(trade_status.equals("TRADE_FINISHED") || trade_status.equals("TRADE_SUCCESS")){
					//判断该笔订单是否在商户网站中已经做过处理
						//如果没有做过处理，根据订单号（out_trade_no）在商户网站的订单系统中查到该笔订单的详细，并执行商户的业务程序
						//如果有做过处理，不执行商户的业务程序
				}
				//该页面可做页面美工编辑
				ShopOrder shopOrder = this.shopOrderService.findById(out_trade_no);
				List<ShopOrderItem> items = this.shopOrderItemService.findItemByOrderId(out_trade_no);
				shopOrder.setShopOrderItems(items);
				view.addObject("shopOrder", shopOrder);
				//——请根据您的业务逻辑来编写程序（以上代码仅作参考）——
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
						Integer num = this.redReceiveService.findTodayNumByBuyerIdAndTypeAndCityId(shopOrder.getBuyerId(),2,shopOrder.getCityId());
						if(packet.getCount()==0 ||num<packet.getCount()){
							//还可以领
							RedShopOrder redShopOrder =this.redShopOrderService.findByOrderNo(shopOrder.getId());
							if(null!=redShopOrder){
								//刷新的
								if(redShopOrder.getFlag()==1){
									RedReceive redreceive = this.redReceiveService.findByOrderNo(shopOrder.getId());
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
							    	order.setOrderNo(shopOrder.getId());
							    	String md5 = MD5Util.getStringMD5(OrderUtil.GetOrderNumber());
							    	order.setTicket(md5);
							    	order.setFlag(1);
							    	this.redShopOrderService.save(order);
							    	view.addObject("ticket", md5);
							    }else{
							    	RedShopOrder order = new RedShopOrder();
							    	order.setOrderNo(shopOrder.getId());
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
				//////////////////////////////////////////////////////////////////////////////////////////
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		view.addObject("flag", 0);
		return view;

	}

}
