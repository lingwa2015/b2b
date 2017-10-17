package com.b2b.service;

import com.b2b.common.domain.ShopLayer;

import java.util.List;

/**
 * Created by shenhui on 2017/8/7.
 */
public interface ShopLayerService {

    List<ShopLayer> findByShopId(Integer shopId);

    void insert(ShopLayer layer);

    List<ShopLayer> findByShopIdAndStatus(Integer shopId);

    void update(ShopLayer layer);

    void delete(Integer layerId);

    ShopLayer findById(Integer layerId);
}
