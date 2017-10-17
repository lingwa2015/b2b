package com.b2b.common.util;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.b2b.common.domain.OrderItem;

import net.sf.json.JSONObject;


public class Utils {
	public static String pingdanrefundout(String tempId,String openId,String orderNo,String total,String remark,String appId,String appsecret) {
		WxTemplate t = new WxTemplate();  
        t.setUrl("");  
        t.setTouser(openId);  
        t.setTopcolor("#000000");  
        t.setTemplate_id(tempId);  
        Map<String,TemplateData> m = new HashMap<String,TemplateData>();  
        TemplateData first = new TemplateData();  
        first.setColor("#000000");  
        first.setValue("真遗憾，筹款订单失败");  
        m.put("first", first);  
        TemplateData keyword1 = new TemplateData();  
        keyword1.setColor("#000000");  
        keyword1.setValue(orderNo);  
        m.put("keyword1", keyword1);  
        TemplateData keyword2 = new TemplateData();  
        keyword2.setColor("#000000");  
        keyword2.setValue("￥"+total);  
        m.put("keyword2", keyword2);
        TemplateData keyword3 = new TemplateData();  
        keyword3.setColor("#000000");  
        keyword3.setValue("零食礼包");  
        m.put("keyword3", keyword3);
        TemplateData Remark = new TemplateData();  
        Remark.setColor("#000000");  
        Remark.setValue("通知信息："+remark);  
        m.put("remark", Remark);
        t.setData(m);
        Token token = CommonUtil.getToken(appId, appsecret);
        JSONObject object = CommonUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token.getAccessToken(), "POST", JSONObject.fromObject(t).toString());
        return object.toString();
	}
	
	public static String pingdanrefundin(String url,String tempId,String openId,String orderNo,String total,String remark,String appId,String appsecret) {
		WxTemplate t = new WxTemplate();  
        t.setUrl(url);  
        t.setTouser(openId);  
        t.setTopcolor("#000000");  
        t.setTemplate_id(tempId);  
        Map<String,TemplateData> m = new HashMap<String,TemplateData>();  
        TemplateData first = new TemplateData();  
        first.setColor("#000000");  
        first.setValue("真遗憾，筹款订单失败");  
        m.put("first", first);  
        TemplateData keyword1 = new TemplateData();  
        keyword1.setColor("#000000");  
        keyword1.setValue(orderNo);  
        m.put("keyword1", keyword1);  
        TemplateData keyword2 = new TemplateData();  
        keyword2.setColor("#000000");  
        keyword2.setValue("￥"+total);  
        m.put("keyword2", keyword2);
        TemplateData keyword3 = new TemplateData();  
        keyword3.setColor("#000000");  
        keyword3.setValue("零食礼包");  
        m.put("keyword3", keyword3);
        TemplateData Remark = new TemplateData();  
        Remark.setColor("#000000");  
        Remark.setValue("通知信息："+remark);  
        m.put("remark", Remark);
        t.setData(m);
        Token token = CommonUtil.getToken(appId, appsecret);
        JSONObject object = CommonUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token.getAccessToken(), "POST", JSONObject.fromObject(t).toString());
        return object.toString();
	}
	
	public static String toYuanNumber(Object value){
		if(value==null)
			return "0.00";
		long count=0;
		boolean flag=false;
		if(value instanceof OrderItem[]){
			OrderItem[] orderItems=(OrderItem[])value;
			for (OrderItem orderItem : orderItems) {
				count+=orderItem.getFee();
			}
			flag=true;
		}else if(value instanceof Collection){
			@SuppressWarnings("unchecked")
			Collection<OrderItem> collection=(Collection<OrderItem>)value;

			for (OrderItem orderItem : collection) {
				count+=orderItem.getFee();
			}
			flag=true;
		}
		if(count==0 && flag)
			count=0;
		else {
			count=count==0?(Long)value:count;
		}
			BigDecimal val = new BigDecimal(count);
			val = val.divide(new BigDecimal(100));
			val=val.setScale(2, BigDecimal.ROUND_HALF_UP);
			return val.toString();
	}
}
