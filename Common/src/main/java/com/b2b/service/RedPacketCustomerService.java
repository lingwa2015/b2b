package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.RedPacketCustomer;

public interface RedPacketCustomerService {
	
	void save(List<RedPacketCustomer> datas);

	void delete(List<RedPacketCustomer> datas);

	RedPacketCustomer findByRedIdAndShopId(Integer id, Integer shopId);

}
