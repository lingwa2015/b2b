package com.b2b.web.wx.util;

import java.util.HashMap;
import java.util.Map;


import net.sf.json.JSONObject;


public class NotifyUtil {
	
	public static String payMessage(String openId,String tempId,String orderNo,String fee,String time,String appId,String appsecret) {
		WxTemplate t = new WxTemplate();  
        t.setUrl("");  
        t.setTouser(openId);  
        t.setTopcolor("#000000");  
        t.setTemplate_id(tempId);  
        Map<String,TemplateData> m = new HashMap<String,TemplateData>();  
        TemplateData first = new TemplateData();  
        first.setColor("#000000");  
        first.setValue("我们已收到您的货款，开始为您打包商品，请耐心等待: )");  
        m.put("first", first);  
        TemplateData keyword1 = new TemplateData();  
        keyword1.setColor("#000000");  
        keyword1.setValue(orderNo);  
        m.put("keyword1", keyword1);  
        TemplateData keyword2 = new TemplateData();  
        keyword2.setColor("#000000");  
        keyword2.setValue(time);  
        m.put("keyword2", keyword2); 
        TemplateData keyword3 = new TemplateData();  
        keyword3.setColor("#000000");  
        keyword3.setValue("¥"+fee);  
        m.put("keyword3", keyword3);
        TemplateData Remark = new TemplateData();  
        Remark.setColor("#000000");  
        Remark.setValue("如有问题请致电400-8801-703或直接在微信留言，小蛙将第一时间为您服务！");  
        m.put("remark", Remark);
        t.setData(m);
        Token token = CommonUtil.getToken(appId, appsecret);
        JSONObject object = CommonUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token.getAccessToken(), "POST", JSONObject.fromObject(t).toString());
        return object.toString();
	}
	
	public static String haveMessage(String openId,String tempId,String orderNo,String fee,String time,String appId,String appsecret) {
		WxTemplate t = new WxTemplate();  
        t.setUrl("");  
        t.setTouser(openId);  
        t.setTopcolor("#000000");  
        t.setTemplate_id(tempId);  
        Map<String,TemplateData> m = new HashMap<String,TemplateData>();  
        TemplateData first = new TemplateData();  
        first.setColor("#000000");  
        first.setValue("你有一个新订单，请及时查收");  
        m.put("first", first);  
        TemplateData keyword1 = new TemplateData();  
        keyword1.setColor("#000000");  
        keyword1.setValue(orderNo);  
        m.put("keyword1", keyword1);  
        TemplateData keyword2 = new TemplateData();  
        keyword2.setColor("#000000");  
        keyword2.setValue(time);  
        m.put("keyword2", keyword2); 
        TemplateData keyword3 = new TemplateData();  
        keyword3.setColor("#000000");  
        keyword3.setValue("¥"+fee);  
        m.put("keyword3", keyword3);
        TemplateData Remark = new TemplateData();  
        Remark.setColor("#000000");  
        Remark.setValue("请及时发货！");  
        m.put("Remark", Remark);
        t.setData(m);
        Token token = CommonUtil.getToken(appId, appsecret);
        JSONObject object = CommonUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token.getAccessToken(), "POST", JSONObject.fromObject(t).toString());
        return object.toString();
	}
	
	
	public static String deliveryMessage(String tempId,String openId,String orderNo,String fastName,String fastNO,String addr,String appId,String appsecret) {
		WxTemplate t = new WxTemplate();  
        t.setUrl("");  
        t.setTouser(openId);  
        t.setTopcolor("#000000");  
        t.setTemplate_id(tempId);  
        Map<String,TemplateData> m = new HashMap<String,TemplateData>();  
        TemplateData first = new TemplateData();  
        first.setColor("#000000");  
        first.setValue("您的订单已发货，如有问题请致电400-8801-703或直接微信留言");  
        m.put("first", first);  
        TemplateData keyword1 = new TemplateData();  
        keyword1.setColor("#000000");  
        keyword1.setValue(orderNo);  
        m.put("keyword1", keyword1);  
        TemplateData keyword2 = new TemplateData();  
        keyword2.setColor("#000000");  
        keyword2.setValue(fastName);  
        m.put("keyword2", keyword2);
        TemplateData keyword3 = new TemplateData();  
        keyword3.setColor("#000000");  
        keyword3.setValue(fastNO);  
        m.put("keyword3", keyword3);
        TemplateData Remark = new TemplateData();  
        Remark.setColor("#000000");  
        Remark.setValue("收货信息："+addr);  
        m.put("remark", Remark);
        t.setData(m);
        Token token = CommonUtil.getToken(appId, appsecret);
        JSONObject object = CommonUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token.getAccessToken(), "POST", JSONObject.fromObject(t).toString());
        return object.toString();
	}
	
	public static String pingdanin(String url,String tempId,String openId,String orderNo,String total,String remark,String appId,String appsecret) {
		WxTemplate t = new WxTemplate();  
        t.setUrl(url);  
        t.setTouser(openId);  
        t.setTopcolor("#000000");  
        t.setTemplate_id(tempId);  
        Map<String,TemplateData> m = new HashMap<String,TemplateData>();  
        TemplateData first = new TemplateData();  
        first.setColor("#000000");  
        first.setValue("恭喜您的订单获得一笔筹款");  
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
	
	public static String pingdanout(String url,String tempId,String openId,String orderNo,String total,String remark,String appId,String appsecret) {
		WxTemplate t = new WxTemplate();  
        t.setUrl(url);  
        t.setTouser(openId);  
        t.setTopcolor("#000000");  
        t.setTemplate_id(tempId);  
        Map<String,TemplateData> m = new HashMap<String,TemplateData>();  
        TemplateData first = new TemplateData();  
        first.setColor("#000000");  
        first.setValue("成功向好友支持一笔筹款");  
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
	
	
	public static String sourcingPass(String tempId,String openId,String orderNo,String total,String createdTime,String executeTime,String token) {
		WxTemplate t = new WxTemplate();  
        t.setUrl("");  
        t.setTouser(openId);  
        t.setTopcolor("#000000");  
        t.setTemplate_id(tempId);  
        Map<String,TemplateData> m = new HashMap<String,TemplateData>();  
        TemplateData first = new TemplateData();  
        first.setColor("#000000");  
        first.setValue("订单已确认通过！");  
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
        keyword3.setValue(createdTime);  
        m.put("keyword3", keyword3);
        TemplateData Remark = new TemplateData();  
        Remark.setColor("#000000");  
        Remark.setValue("配送日期："+executeTime);  
        m.put("remark", Remark);
        t.setData(m);
        JSONObject object = CommonUtil.httpsRequest("https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+token, "POST", JSONObject.fromObject(t).toString());
        return object.toString();
	}
}
