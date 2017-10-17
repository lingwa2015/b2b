package com.b2b.service;

import com.b2b.common.domain.ItemWeekVolume;

import java.util.List;

public interface ItemWeekVolumeService {


	List<ItemWeekVolume> findVolume();

	int insert(ItemWeekVolume itemWeekVolume);

	Integer deleteAll();
}
