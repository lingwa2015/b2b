package com.b2b.web.wx.util.pay;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.apache.commons.codec.digest.DigestUtils;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.b2b.web.util.ApiService;
import com.b2b.web.wx.util.Properties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class WXPayUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(ApiService.class);
	private static String oauth2Url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	private static String unifiedorder = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	private static final ObjectMapper MAPPER = new ObjectMapper();
	@Autowired
	private ApiService apiService;
	
	@Autowired
	private Properties properties;
	
	/**
	 * 生成预支付订单
	 * 
	 * @return
	 */
	public String submitXmlGetPrepayId(WXPrepay prepay) {
		// 创建HttpClientBuilder
		String xml = getPackage(prepay);
		try {
			String result = apiService.doPostXml(unifiedorder, xml);
			LOGGER.info(result);
			result = result.replaceAll("<![CDATA[|]]>", "");
			String prepay_id = Jsoup.parse(result).select("prepay_id").html();
			return prepay_id;
		} catch (Exception e) {
			LOGGER.error("生成预支付订单失败",e);
		}
		return null;
	}
	
	public String getPackage(WXPrepay prepay) {
		TreeMap<String, String> treeMap = new TreeMap<String, String>();
		treeMap.put("appid", prepay.getAppid());
		treeMap.put("mch_id", prepay.getMch_id());
		treeMap.put("nonce_str", prepay.getNonce_str());
		treeMap.put("body", prepay.getBody());
		treeMap.put("out_trade_no", prepay.getOut_trade_no());
		treeMap.put("total_fee", prepay.getTotal_fee());
		treeMap.put("spbill_create_ip", prepay.getSpbill_create_ip());
		treeMap.put("trade_type", prepay.getTrade_type());
		treeMap.put("notify_url", prepay.getNotify_url());
		if(null!=prepay.getOpenid()){
			treeMap.put("openid", prepay.getOpenid());
		}
		StringBuilder sb = new StringBuilder();
		for (String key : treeMap.keySet()) {
			sb.append(key).append("=").append(treeMap.get(key)).append("&");
		}
		sb.append("key=" + prepay.getPartnerKey());
		//sign = MD5Util.MD5Encode(sb.toString(), "utf-8").toUpperCase();
		String sign = DigestUtils.md5Hex(sb.toString()).toUpperCase();
		treeMap.put("sign", sign);
		StringBuilder xml = new StringBuilder();
		xml.append("<xml>\n");
		for (Map.Entry<String, String> entry : treeMap.entrySet()) {
			if ("body".equals(entry.getKey()) || "sign".equals(entry.getKey())) {
				xml.append("<" + entry.getKey() + "><![CDATA[").append(entry.getValue()).append("]]></" + entry.getKey() + ">\n");
			} else {
				xml.append("<" + entry.getKey() + ">").append(entry.getValue()).append("</" + entry.getKey() + ">\n");
			}
		}
		xml.append("</xml>");
		System.out.println(xml.toString());
		return xml.toString();
	}
	
	public String createPackageValue(String appid, String appKey, String prepay_id)  {
		try {
			SortedMap<String, String> nativeObj = new TreeMap<String, String>();
			nativeObj.put("appId", appid);
			nativeObj.put("timeStamp", OrderUtil.GetTimestamp());
			Random random = new Random();
			//String randomStr = MD5.GetMD5String(String.valueOf(random.nextInt(10000)));
			String randomStr = DigestUtils.md5Hex(String.valueOf(random.nextInt(10000))).toUpperCase();
			nativeObj.put("nonceStr", DigestUtils.md5Hex(randomStr).toLowerCase());
			nativeObj.put("package", "prepay_id=" + prepay_id);
			nativeObj.put("signType", "MD5");
			nativeObj.put("paySign", createSign(nativeObj, appKey));
			System.out.println(MAPPER.writeValueAsString(nativeObj));
			return MAPPER.writeValueAsString(nativeObj);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 创建md5摘要,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
	 */
	public String createSign(SortedMap<String, String> packageParams, String AppKey) {
		StringBuffer sb = new StringBuffer();
		Set es = packageParams.entrySet();
		Iterator it = es.iterator();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String k = (String) entry.getKey();
			String v = (String) entry.getValue();
			if (null != v && !"".equals(v) && !"sign".equals(k) && !"key".equals(k)) {
				sb.append(k + "=" + v + "&");
			}
		}
		sb.append("key=" + AppKey);
		String sign = DigestUtils.md5Hex(sb.toString()).toUpperCase();
		return sign;
	}
	
}
