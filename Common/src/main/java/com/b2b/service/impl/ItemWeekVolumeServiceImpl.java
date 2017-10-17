package com.b2b.service.impl;

import com.b2b.common.dao.ItemWeekVolumeMapper;
import com.b2b.common.dao.PaymentAccountLockMapper;
import com.b2b.common.domain.ItemWeekVolume;
import com.b2b.service.ItemWeekVolumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by a. on 2017/8/29.
 */
@Service
public class ItemWeekVolumeServiceImpl implements ItemWeekVolumeService {

    @Autowired
    private ItemWeekVolumeMapper itemWeekVolumeMapper;


    @Override
    public List<ItemWeekVolume> findVolume() {
        return itemWeekVolumeMapper.findVolume();
    }

    @Override
    public int insert(ItemWeekVolume itemWeekVolume) {
        return itemWeekVolumeMapper.insert(itemWeekVolume);
    }

    @Override
    public Integer deleteAll() {
        return itemWeekVolumeMapper.deleteAll();
    }
}
