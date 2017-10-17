package com.b2b.service.impl;

import com.b2b.common.dao.ShopLayerMapper;
import com.b2b.common.domain.ShopLayer;
import com.b2b.common.domain.ShopLayerExample;
import com.b2b.service.ShopItemService;
import com.b2b.service.ShopLayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by shenhui on 2017/8/7.
 */
@Service
public class ShopLayerServiceImpl implements ShopLayerService {

    @Autowired
    private ShopLayerMapper shopLayerMapper;

    @Autowired
    private ShopItemService shopItemService;

    @Override
    public List<ShopLayer> findByShopId(Integer shopId) {
        ShopLayerExample example = new ShopLayerExample();
        ShopLayerExample.Criteria criteria = example.createCriteria();
        criteria.andShopIdEqualTo(shopId);
        return this.shopLayerMapper.selectByExample(example);
    }

    @Override
    public void insert(ShopLayer layer) {
        this.shopLayerMapper.insert(layer);
    }

    @Override
    public List<ShopLayer> findByShopIdAndStatus(Integer shopId) {
        ShopLayerExample example = new ShopLayerExample();
        ShopLayerExample.Criteria criteria = example.createCriteria();
        criteria.andShopIdEqualTo(shopId);
        criteria.andStatusEqualTo(1);
        return this.shopLayerMapper.selectByExample(example);
    }

    @Override
    public void update(ShopLayer layer) {
        this.shopLayerMapper.updateByPrimaryKeySelective(layer);
    }

    @Override
    public void delete(Integer layerId) {
        ShopLayer shopLayer = new ShopLayer();
        shopLayer.setId(layerId);
        shopLayer.setStatus(0);
        this.shopLayerMapper.updateByPrimaryKeySelective(shopLayer);
        this.shopItemService.updateLayerIdToNull(layerId);
    }

    @Override
    public ShopLayer findById(Integer layerId) {
        return this.shopLayerMapper.selectByPrimaryKey(layerId);
    }
}
