package com.b2b.web.wx.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Properties {
	@Value("${weixin_appid}")
	public String weixinAppid;
	@Value("${weixin_appsecret}")
	public String weixinAppsecret;
	@Value("${WX_MCH_ID}")
	public String WX_MCH_ID;
	@Value("${WX_PAY_PARTNERKRY}")
	public String WX_PAY_PARTNERKRY;
	@Value("${WX_PAY_NOTIFY_URL}")
	public String WX_PAY_NOTIFY_URL;
	@Value("${WX_PAY_CZ_NOTIFY_URL}")
	public String WX_PAY_CZ_NOTIFY_URL;
	@Value("${WX_PAY_PD_NOTIFY_URL}")
	public String WX_PAY_PD_NOTIFY_URL;
	@Value("${PAY_TEMP_ID}")
	public String PAY_TEMP_ID;
	@Value("${DELIVERY_TEMP_ID}")
	public String DELIVERY_TEMP_ID;
	@Value("${SHOP_PAY_NOTIFY_URL}")
	public String SHOP_PAY_NOTIFY_URL;
	@Value("${ALI_APPID}")
	public String ALI_APPID;
	@Value("${PASS_TEMP_ID}")
	public String PASS_TEMP_ID;
	@Value("${ONLINE_URL}")
	public String ONLINE_URL;
	@Value("${ALI_PRIVATE_KEY}")
	public String ALI_PRIVATE_KEY;
	@Value("${ALIPAY_PUBLIC_KEY}")
	public String ALIPAY_PUBLIC_KEY;
}
