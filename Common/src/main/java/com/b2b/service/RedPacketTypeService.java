package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.RedPacketType;

public interface RedPacketTypeService {

	void save(RedPacketType redPacketType);

	Integer findTotalNumByRedId(Integer id);

	List<RedPacketType> findByRedId(Integer redId);

}
