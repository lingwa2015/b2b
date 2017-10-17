package com.b2b.service.impl;

import com.b2b.common.dao.ItemPriceHistoryMapper;
import com.b2b.common.domain.ItemPriceHistory;
import com.b2b.service.ItemPriceHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by a. on 2017/8/29.
 */
@Service
public class ItemPriceHistoryServiceImpl implements ItemPriceHistoryService {

    @Autowired
    private ItemPriceHistoryMapper itemPriceHistoryMapper;

    @Override
    public int inset(ItemPriceHistory itemPriceHistory) {
        return itemPriceHistoryMapper.insert(itemPriceHistory);
    }

    @Override
    public List<ItemPriceHistory> findByItemName(String itemName, Integer cityId) {
        return itemPriceHistoryMapper.findByItemName(itemName, cityId);
    }

    @Override
    public int updataById(ItemPriceHistory itemPriceHistory) {
        return itemPriceHistoryMapper.updateByPrimaryKeySelective(itemPriceHistory);
    }
}
