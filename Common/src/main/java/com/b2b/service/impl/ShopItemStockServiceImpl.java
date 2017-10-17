package com.b2b.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.b2b.common.dao.ShopItemStockMapper;
import com.b2b.common.domain.DayShopStockExample;
import com.b2b.common.domain.ShopItem;
import com.b2b.common.domain.ShopItemStock;
import com.b2b.common.domain.ShopItemStockExample;
import com.b2b.common.domain.ShopItemStockExample.Criteria;
import com.b2b.service.CustomerService;
import com.b2b.service.ShopItemService;
import com.b2b.service.ShopItemStockService;

@Service
public class ShopItemStockServiceImpl implements ShopItemStockService {
	@Autowired
	ShopItemStockMapper shopItemStockMapper;
	@Autowired
	CustomerService customerService;
	
	@Autowired
	ShopItemService shopItemService;
	
	@Override
	public void create(Date endDate) {
		this.delete(endDate);
		List<HashMap<String,Object>> shop = this.customerService.findAllShop();
		for (HashMap<String, Object> map : shop) {
			List<ShopItem> shopItems = this.shopItemService.findItemByShopId(Integer.valueOf(map.get("id").toString()));
			for (ShopItem shopItem : shopItems) {
				ShopItemStock stock = new ShopItemStock();
				stock.setSumDate(endDate);
				stock.setCreatedTime(new Date());
				stock.setItemId(shopItem.getItemId());
				stock.setShopId(shopItem.getShopId());
				stock.setSalePrice(shopItem.getSalePrice());
				stock.setSourcingPrice(shopItem.getSourcingPrice());
				stock.setNum(shopItem.getNum());
				this.shopItemStockMapper.insert(stock);
			}
		}
	}
	
	private void delete(Date endDate) {
		ShopItemStockExample example = new ShopItemStockExample();
		Criteria criteria = example.createCriteria();
		criteria.andSumDateEqualTo(endDate);
		this.shopItemStockMapper.deleteByExample(example);
	}

	@Override
	public List<ShopItemStock> findlossDetail(Integer shop_id, Date querydate,
			Date startdate, Date enddate) {
		return this.shopItemStockMapper.findlossDetail(shop_id,querydate,startdate,enddate);
	}

	@Override
	public Integer findByItemIdAndSumdateAndSaleId(Integer itemId, Date sumdate, Integer id) {
		return this.shopItemStockMapper.findByItemIdAndSumdateAndSaleId(itemId,sumdate,id);
	}

	@Override
	public Integer findByItemIdAndSumdate(Integer itemId, Date sumdate) {
		return this.shopItemStockMapper.findByItemIdAndSumdate(itemId,sumdate);
	}
}
