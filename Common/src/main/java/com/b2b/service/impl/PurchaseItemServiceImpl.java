package com.b2b.service.impl;

import com.b2b.common.dao.PurchaseItemMapper;
import com.b2b.common.domain.PurchaseItem;
import com.b2b.service.PurchaseItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PurchaseItemServiceImpl implements PurchaseItemService {

	@Autowired
	PurchaseItemMapper purchaseItemMapper;


	@Override
	public List<PurchaseItem> findItemPlan(String isDown, int cate, String itemName, Integer cityId, String grade, Integer newUserNum) {
		return this.purchaseItemMapper.findItemPlan(isDown, cate, itemName, cityId, grade, newUserNum);
	}

	@Override
	public PurchaseItem findbyItemId(Integer itemId) {
		List<PurchaseItem> purchaseItemList = this.purchaseItemMapper.findbyItemId(itemId);
		if (purchaseItemList.size() >=1 ){
			return purchaseItemList.get(0);
		}
		return null;
	}

	@Override
	public String findbySupplierId(Integer supplierId) {
		return  this.purchaseItemMapper.findbySupplierId(supplierId);
	}

	@Override
	public int insert(PurchaseItem purchaseItem) {
		return this.purchaseItemMapper.insert(purchaseItem);
	}

	@Override
	public int update(PurchaseItem purchaseItem) {
		return  this.purchaseItemMapper.updateByPrimaryKeySelective(purchaseItem);
	}

	@Override
	public int deleteByItemId(Integer itemId) {
		return this.purchaseItemMapper.deleteByItemId(itemId);
	}

	@Override
	public String findMaxPurchaseId() {
		return this.purchaseItemMapper.findMaxPurchaseId();
	}

	@Override
	public List<PurchaseItem> findItemPlans(Integer cityId) {
		return this.purchaseItemMapper.findItemPlans(cityId);
	}

	@Override
	public List<PurchaseItem> findPlans(Integer cityId) {
		return this.purchaseItemMapper.findPlans(cityId);
	}

	@Override
	public Integer findUseNumByItemId(Integer itemId) {
		return this.purchaseItemMapper.findUseNumByItemId(itemId);
	}

	@Override
	public PurchaseItem findItemsByItemIdAndCity(Integer itemId, Integer cityId) {
		return this.purchaseItemMapper.findItemsByItemIdAndCity(itemId, cityId);
	}

	@Override
	public PurchaseItem findbyId(Integer id) {
		return this.purchaseItemMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<PurchaseItem> findInfoByStock(String ids) {
		return this.purchaseItemMapper.findInfoByStock(ids);
	}

	@Override
	public PurchaseItem findbyPurchaseIdAndItemid(String purchaseId, Integer itemId) {
		List<PurchaseItem> purchaseItemList = this.purchaseItemMapper.findbyPurchaseIdAndItemid(purchaseId, itemId);
		if (purchaseItemList.size() >=1 ){
			return purchaseItemList.get(0);
		}
		return null;
	}

	@Override
	public Integer findEndCountByPurchaseId(String purchaseId) {
		return this.purchaseItemMapper.findEndCountByPurchaseId(purchaseId);
	}

	@Override
	public int updatePurchaseItemStatus(String purchaseId, Integer status) {
		return this.purchaseItemMapper.updatePurchaseItemStatus(purchaseId, status);
	}
}
