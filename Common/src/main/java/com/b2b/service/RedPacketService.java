package com.b2b.service;

import java.util.ArrayList;
import java.util.List;

import com.b2b.common.domain.RedPacket;
import com.b2b.common.domain.RedPacketCustomer;
import com.b2b.common.domain.RedPacketType;

public interface RedPacketService {

	void save(RedPacket red, ArrayList<RedPacketType> list);

	List<RedPacket> findAllByCityId(Integer cityId);
	
	List<RedPacket> findStartedRedPacket(Integer cityId);

	RedPacket findStartedRedPacketById(Integer redId);

	void changeType(RedPacket packet);

	void changeTypeAndSaveRedPacketCustomer(Integer redId, List<RedPacketCustomer> datas);
}
