package com.b2b.web.wx.controller;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.b2b.common.domain.NotifyState;
import com.b2b.common.domain.WXUser;
import com.b2b.common.domain.WXUserOrder;
import com.b2b.service.NotifyStateService;
import com.b2b.service.WXUserOrderService;
import com.b2b.web.util.LWResult;
import com.b2b.web.util.NumberTool;
import com.b2b.web.wx.util.NotifyUtil;
import com.b2b.web.wx.util.Properties;
import com.b2b.web.wx.util.WXSessionHelper;
import com.b2b.web.wx.util.pay.DOM4JParser;
import com.b2b.web.wx.util.pay.OrderUtil;
import com.b2b.web.wx.util.pay.WXPayUtil;
import com.b2b.web.wx.util.pay.WXPrepay;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class WXPayController {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(WXPayController.class);
	private static final ObjectMapper MAPPER = new ObjectMapper();
	@Autowired
	private Properties properties;
	
	@Autowired
	private WXPayUtil wxPayUtil;
	
	@Autowired
	private WXUserOrderService wxUserOrderService;
	
	@Autowired
	private NotifyStateService notifyStateService;

	/**
	 * 微信公众号调用微信支付
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping("/wxpay.do")
	@ResponseBody
	public LWResult pay(HttpServletRequest request) {
		String id = request.getParameter("id");
		String num = request.getParameter("num");
		String totalfee = request.getParameter("totalfee");
		String wxname = request.getParameter("wxname");
		String wxphone = request.getParameter("wxphone");
		String wxadress = request.getParameter("wxadress");
		String invoiceid = request.getParameter("invoiceid");
		String invoiceName = request.getParameter("invoiceName");
		WXUser wxUser = WXSessionHelper.getWxUser(request.getSession());
		if(null==wxUser){
			return LWResult.build(201, "创建订单失败");
		}
		String orderNo = OrderUtil.GetOrderNumber();
		WXUserOrder result = this.wxUserOrderService.save(id,num,totalfee,wxname,wxphone,wxadress,invoiceid,invoiceName,orderNo,wxUser.getId(),0);
		if(result!=null){
			String spbill_create_ip = request.getRemoteAddr();
			WXPrepay prePay = new WXPrepay();
			prePay.setAppid(properties.weixinAppid);
			prePay.setBody("零食包");
			prePay.setPartnerKey(properties.weixinAppsecret);
			prePay.setMch_id(properties.WX_MCH_ID);
			prePay.setNotify_url(properties.WX_PAY_NOTIFY_URL);
			//时间戳生成的订单号
			prePay.setOut_trade_no(result.getWxorderNum());
			prePay.setSpbill_create_ip(spbill_create_ip);
			prePay.setTotal_fee(totalfee);
			prePay.setTrade_type("JSAPI");
			prePay.setPartnerKey(properties.WX_PAY_PARTNERKRY);
			prePay.setOpenid(wxUser.getOpenid());
			// 此处添加获取openid的方法，获取预支付订单需要此参数！！！！！！！！！！！
			// 获取预支付订单号
			String prepayid = wxPayUtil.submitXmlGetPrepayId(prePay);
			LOGGER.info("获取的预支付订单是：" + prepayid);
			if (prepayid != null && prepayid.length() > 10) {
				// 生成微信支付参数，此处拼接为完整的JSON格式，符合支付调起传入格式
				String jsParam = wxPayUtil.createPackageValue(
						prePay.getAppid(), prePay.getPartnerKey(), prepayid);
				System.out.println("jsParam=" + jsParam);
				// 此处可以添加订单的处理逻辑
				// model.addAttribute("jsParam", jsParam);
				LOGGER.info("生成的微信调起JS参数为：" + jsParam);
				HashMap<String, Object> map = new HashMap<String, Object>();
				JsonNode jsonNode;
				try {
					jsonNode = MAPPER.readTree(jsParam);
					map.put("appId", jsonNode.path("appId").asText());
					map.put("nonceStr", jsonNode.path("nonceStr").asText());
					map.put("package", jsonNode.path("package").asText());
					map.put("paySign", jsonNode.path("paySign").asText());
					map.put("signType", jsonNode.path("signType").asText());
					map.put("timeStamp", jsonNode.path("timeStamp").asText());
					map.put("prepayid", prepayid);
					return LWResult.ok(map);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return LWResult.build(201, "创建订单失败");

	}

	@RequestMapping("/wxpay/wxnotify.do")
	public void test(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> map = DOM4JParser.getInputStream(request);
		String xmlStr = "";
		LOGGER.info("------map:" + map);
		if (map.get("return_code").equals("SUCCESS")) {
			NotifyState notifyState = this.notifyStateService.findById(map.get("out_trade_no").toString());
			if(null!=notifyState && notifyState.getStatus() == 1){
				xmlStr = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
			}else{
				this.wxUserOrderService.changePayStatus(map.get("out_trade_no").toString());
				LOGGER.info("支付成功,订单号:" + map.get("out_trade_no").toString());
				NotifyUtil.payMessage(map.get("openid").toString(), properties.PAY_TEMP_ID,map.get("out_trade_no").toString(),NumberTool.toYuanNumber(Long.parseLong(map.get("total_fee").toString())), map.get("time_end").toString(), properties.weixinAppid, properties.weixinAppsecret);
				NotifyUtil.haveMessage("o8PPCviieoTTIY9OGFrFPvUIXIVs", properties.PAY_TEMP_ID,map.get("out_trade_no").toString(),NumberTool.toYuanNumber(Long.parseLong(map.get("total_fee").toString())), map.get("time_end").toString(), properties.weixinAppid, properties.weixinAppsecret);
				xmlStr = "<xml><return_code><![CDATA[SUCCESS]]></return_code><return_msg><![CDATA[OK]]></return_msg></xml>";
			}
		}else{
			xmlStr = "<xml>" + "<return_code><![CDATA[FAIL]]></return_code>"
					+ "<return_msg><![CDATA[报文为空]]></return_msg>" + "</xml> ";
		}
		LOGGER.info("xmlstr:" + xmlStr);
		PrintWriter writer = null;
		response.setContentType("text/xml");
		try {
			writer = response.getWriter();
			writer.flush();
			writer.println(xmlStr);// 告知微信已成功处理请求
		} catch (Exception e) {
			LOGGER.error("异步回调失败"+e);
		}finally{
			writer.close();
		}
	}
	
	@RequestMapping("wxsuccess.htm")
	public ModelAndView success(){
	    return new ModelAndView("wx/pay/success");
	}
	
}
