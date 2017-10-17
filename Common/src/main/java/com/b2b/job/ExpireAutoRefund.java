package com.b2b.job;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.domain.PayFriends;
import com.b2b.common.domain.RefundFriends;
import com.b2b.common.domain.WXUser;
import com.b2b.common.domain.WXUserOrder;
import com.b2b.common.util.ClientCustomSSL;
import com.b2b.common.util.OrderNumberGenerator;
import com.b2b.common.util.RequestHandler;
import com.b2b.common.util.Utils;
import com.b2b.service.PayFriendsService;
import com.b2b.service.RefundFriendsService;
import com.b2b.service.WXUserOrderService;
import com.b2b.service.WXUserService;

@Service
public class ExpireAutoRefund {
	private static final Logger logger = LoggerFactory
			.getLogger(ExpireAutoRefund.class);
	@Autowired
	WXUserOrderService wxUserOrderService;
	
	@Autowired
	PayFriendsService payFriendsService;
	
	@Autowired
	WXUserService wxUserService;
	
	@Autowired
	RefundFriendsService refundFriendsService;
	
	public void scenExpireOrder(){
		logger.warn("开始扫描拼单订单");
		List<WXUserOrder> wxUserOrders = this.wxUserOrderService.findExpirePingDanOrder();
		if(!wxUserOrders.isEmpty()){
			for (WXUserOrder wxUserOrder : wxUserOrders) {
				List<PayFriends> pays = this.payFriendsService.findByOrderId(wxUserOrder.getId());
				long money = 0;
				logger.warn("2222222222222222222222222222222"+pays);
				
				
				if (!pays.isEmpty()) {
					for (PayFriends payFriends : pays) {
						String out_refund_no = OrderNumberGenerator.buildOrderNo(new Date(), 12);// 退款单号
						money +=payFriends.getFee();
						logger.warn("2222222222222222222222222222222"+payFriends.getId());
						String msg = this.refund(out_refund_no,payFriends.getId(), payFriends.getFee().toString(), payFriends.getFee().toString());
						if(null!=msg){
							Map<String, Object> map = RequestHandler.confirmWeiXinReturnResponse(msg);
							String result_code = map.get("result_code").toString();
							if(result_code.equals("SUCCESS")){
								RefundFriends friends = new RefundFriends();
								friends.setId(out_refund_no);
								friends.setPayId(payFriends.getId());
								friends.setFee(payFriends.getFee());
								friends.setCreateTime(new Date());
								this.refundFriendsService.save(friends);
								//退款成功修改状态
								this.payFriendsService.changePayStatusToRefund(payFriends.getId(), 2);
								WXUser user = this.wxUserService.findById(payFriends.getUserId());
								String remark = "您好友发起的筹款订单已超时或取消，您支持的"+Utils.toYuanNumber(payFriends.getFee())+"元将原路退回到您的账户";
								Utils.pingdanrefundout("FILfxwAJnzXZO4R4SxJNr467uVlnxhgRB5MJnSWga2Q", user.getOpenid(), wxUserOrder.getWxorderNum(), Utils.toYuanNumber(wxUserOrder.getSnackpackageTotal()), remark, "wx48b259464a05af2f", "4117062cd63f2e65c511de0456caee5d");
							}
						}
					}
				}
				wxUserOrder.setPayfeeStatus(-2);
				this.wxUserOrderService.changePayfeeStatus(wxUserOrder);
				WXUser user = this.wxUserService.findById(wxUserOrder.getWxuserId());
				String remark = "您发起的好友筹款订单已超时或取消，已筹得的"+Utils.toYuanNumber(money)+"元将原路退还给好友们";
				String url = "http://lingwa.com.cn/weixin/wxgroup.htm?id="+wxUserOrder.getId().toString();
				System.out.println(Utils.toYuanNumber(wxUserOrder.getSnackpackageTotal()));
				Utils.pingdanrefundin(url, "FILfxwAJnzXZO4R4SxJNr467uVlnxhgRB5MJnSWga2Q", user.getOpenid(), wxUserOrder.getWxorderNum(), Utils.toYuanNumber(wxUserOrder.getSnackpackageTotal()), remark, "wx48b259464a05af2f", "4117062cd63f2e65c511de0456caee5d");
			}
		}
	}
	
	public String refund(String refund_no,String out_trade_no,String total_fee,String refund_fee){
		String out_refund_no = refund_no;// 退款单号
		String nonce_str = String.valueOf(System.currentTimeMillis());// 随机字符串
		String appid = "wx48b259464a05af2f"; //微信公众号apid
		String appsecret = "4117062cd63f2e65c511de0456caee5d"; //微信公众号appsecret
		String mch_id = "1295115701";  //微信商户id
		String op_user_id = "1295115701";//就是MCHID
		String partnerkey = "LINGWA12345678900987654321AWGNIL";//商户平台上的那个KEY
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
	
}
