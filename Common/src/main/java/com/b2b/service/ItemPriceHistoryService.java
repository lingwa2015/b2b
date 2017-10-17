package com.b2b.service;

import com.b2b.common.domain.ItemPriceHistory;

import java.util.List;

/**
 * Created by a. on 2017/8/29.
 */
public interface ItemPriceHistoryService {

    int inset(ItemPriceHistory itemPriceHistory);

    List<ItemPriceHistory> findByItemName(String itemName, Integer cityId);

    int updataById(ItemPriceHistory itemPriceHistory);
}
