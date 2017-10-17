package com.b2b.web.wx.util;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.b2b.common.domain.WXUser;
import com.b2b.web.filter.EmojiFilter;
/**
 * 微信网页授权
 * @author aaa
 *
 */
public class AdvancedUtil {
	private static final Logger logger = LoggerFactory
			.getLogger(AdvancedUtil.class);
	public static String oauth2Url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
	
	public static String infoUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID";
	
	public static String userinfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
	
	public static String validAccessTokenUrl = "https://api.weixin.qq.com/sns/auth?access_token=ACCESS_TOKEN&openid=OPENID";
	
	public static String refreshTokenUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid=APPID&grant_type=refresh_token&refresh_token=REFRESH_TOKEN";
	
	public static WeiXinOauth2Token getOauth2AccessToken(String appId, String appSecret, String code) {
        WeiXinOauth2Token wat = new WeiXinOauth2Token();
        String requestUrl = oauth2Url.replace("APPID", appId).replace("SECRET", appSecret).replace("CODE", code);
        JSONObject jsonObject = CommonUtil
                        .httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
                try {
                        wat = new WeiXinOauth2Token();
                        wat.setAccessToken(jsonObject.getString("access_token"));
                        wat.setExpiresIn(jsonObject.getInt("expires_in"));
                        wat.setRefeshToken(jsonObject.getString("refresh_token"));
                        wat.setOpenId(jsonObject.getString("openid"));
                        wat.setScope(jsonObject.getString("scope"));
                } catch (Exception e) {
                        wat = null;
                        String errorCode = jsonObject.getString("errcode");
                        String errorMsg = jsonObject.getString("errmsg");
                        logger.error("获取网页授权凭证失败 errcode{},errMsg", errorCode, errorMsg);
                }

        }
        return wat;
	}
	
	/**
	 * 网页授权获取用户信息
	 * @param token
	 * @param openid
	 * @return
	 */
	public static WXUser getWeixinUserInfo(String token,String openid){
		WXUser userInfo = null;
		String requestUrl = infoUrl.replace("ACCESS_TOKEN", token).replace("OPENID", openid);
		JSONObject jsonObject = CommonUtil
                .httpsRequest(requestUrl, "POST", null);
		if (null != jsonObject) {
			try {
				userInfo = new WXUser();
				userInfo.setCity(jsonObject.getString("city"));
				userInfo.setCountry(jsonObject.getString("country"));
				userInfo.setHeadImgurl(jsonObject.getString("headimgurl"));
				userInfo.setNickName(EmojiFilter.filterEmoji(jsonObject.getString("nickname")));
				userInfo.setProvince(jsonObject.getString("province"));
				userInfo.setSex(Integer.valueOf(jsonObject.getString("sex")));
				userInfo.setOpenid(jsonObject.getString("openid"));
			} catch (Exception e) {
				String errorCode = jsonObject.getString("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                logger.error("获取用户基本信息失败 errcode{},errMsg", errorCode, errorMsg);
			}
		}
		return userInfo;
	}
	
	public static WXUser getWeixinInfo(String token,String openid){
		WXUser userInfo = null;
		String requestUrl = userinfoUrl.replace("ACCESS_TOKEN", token).replace("OPENID", openid);
		JSONObject jsonObject = CommonUtil
                .httpsRequest(requestUrl, "POST", null);
		if (null != jsonObject) {
			try {
				userInfo = new WXUser();
				userInfo.setCity(jsonObject.getString("city"));
				userInfo.setCountry(jsonObject.getString("country"));
				userInfo.setHeadImgurl(jsonObject.getString("headimgurl"));
				userInfo.setNickName(EmojiFilter.filterEmoji(jsonObject.getString("nickname")));
				userInfo.setProvince(jsonObject.getString("province"));
				userInfo.setSex(Integer.valueOf(jsonObject.getString("sex")));
				userInfo.setOpenid(jsonObject.getString("openid"));
			} catch (Exception e) {
				String errorCode = jsonObject.getString("errcode");
                String errorMsg = jsonObject.getString("errmsg");
                logger.error("获取用户基本信息失败 errcode{},errMsg", errorCode, errorMsg);
			}
		}
		return userInfo;
	}
	
	public static boolean validAccessToken(String token,String openid){
		String requestUrl = validAccessTokenUrl.replace("ACCESS_TOKEN", token).replace("OPENID", openid);
		JSONObject jsonObject = CommonUtil
                .httpsRequest(requestUrl, "POST", null);
		if (null != jsonObject) {
			if(jsonObject.getString("errcode").equals("0")){
				return true;
			}
		}
		return false;
	}
	
	public static WeiXinOauth2Token refreshToken(String appId,String token){
		WeiXinOauth2Token wat = new WeiXinOauth2Token();
        String requestUrl = refreshTokenUrl.replace("APPID", appId).replace("REFRESH_TOKEN", token);
        JSONObject jsonObject = CommonUtil
                        .httpsRequest(requestUrl, "GET", null);
        if (null != jsonObject) {
                try {
                        wat = new WeiXinOauth2Token();
                        wat.setAccessToken(jsonObject.getString("access_token"));
                        wat.setExpiresIn(jsonObject.getInt("expires_in"));
                        wat.setRefeshToken(jsonObject.getString("refresh_token"));
                        wat.setOpenId(jsonObject.getString("openid"));
                        wat.setScope(jsonObject.getString("scope"));
                } catch (Exception e) {
                        wat = null;
                        String errorCode = jsonObject.getString("errcode");
                        String errorMsg = jsonObject.getString("errmsg");
                        logger.error("刷新授权凭证失败 errcode{},errMsg", errorCode, errorMsg);
                }

        }
        return wat;
	}
}
