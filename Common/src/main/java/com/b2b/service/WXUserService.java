package com.b2b.service;

import com.b2b.common.domain.WXUser;

public interface WXUserService {

	WXUser findByOpenId(String openId);

	void create(WXUser weiXinUser);

	WXUser findById(Integer wxuserId);

	void updateCID(Integer id, Integer id2);

	void upadte(WXUser wxUser);

}
