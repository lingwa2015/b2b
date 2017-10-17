package com.b2b.service.impl;

import com.b2b.common.dao.PurchaseItemMapper;
import com.b2b.common.dao.PurchaseMapper;
import com.b2b.common.domain.*;
import com.b2b.service.*;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class PurchaseServiceImpl implements PurchaseService {

	@Autowired
	PurchaseMapper purchaseMapper;

	@Autowired
	PurchaseItemMapper purchaseItemMapper;

	@Autowired
	ItemService itemService;


	@Override
	public int insert(Purchase purchase) {
		return this.purchaseMapper.insert(purchase);
	}

	@Override
	public List<Purchase> findPurchasesAndPurchasesItemByCondition(Date startTime, Date endTime, String purchaseId,
																   String supplierName, String itemName, Integer cityId) {
		return this.purchaseMapper.findPurchasesAndPurchasesItemByCondition(startTime,
				endTime, purchaseId, supplierName, itemName, cityId);
	}

	@Override
	public Pair<Purchase, List<PurchaseItem>> findByPurchaseId(String purchaseId) {
		Purchase purchase = this.purchaseMapper.selectByPrimaryKey(purchaseId);

		List<PurchaseItem> itemList = this.purchaseItemMapper.findItemsByPurchaseId(purchaseId);
		return Pair.of(purchase, itemList);
	}

	@Override
	public List<PurchaseItem> findPurchaseItem(String purchaseId) {
		return this.purchaseItemMapper.findItemsByPurchaseId(purchaseId);

	}

	@Override
	public int updatePurchasePrintStatus(String id) {
		return this.purchaseMapper.updatePurchasePrintStatus(id);
	}

	@Override
	public Purchase findPurchaseById(String id) {
		return this.purchaseMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updatePurchaseStatus(String purchaseId, Integer status) {
		Purchase purchase = new Purchase();
		purchase.setId(purchaseId);
		purchase.setStatus(status);
		return this.purchaseMapper.updateByPrimaryKeySelective(purchase);
	}

	@Override
	public int updateByPrimaryKeySelective (Purchase purchase) {
		return this.purchaseMapper.updateByPrimaryKeySelective(purchase);
	}

	@Override
	public void createPurchase(Purchase purchase) {
		// 如果执行日期为空，则默认为当前日期
		if (purchase.getExecutedTime() == null) {
			purchase.setExecutedTime(new Date());
		}
		this.purchaseMapper.insertSelective(purchase);

		for (PurchaseItem purchaseItem : purchase.getPurchaseItemList()) {
			this.purchaseItemMapper.insert(purchaseItem);
		}
	}

	@Override
	public Pair<Purchase, List<HashMap<String, Object>>> findInfoByPurchaseId(String purchaseId) {
		Purchase purchase = purchaseMapper.selectByPrimaryKey(purchaseId);

		List<HashMap<String, Object>> itemList = purchaseItemMapper.findOrderItemByPurchaseId(purchaseId);

		return Pair.of(purchase, itemList);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED, readOnly = false)
	public void updatePurchaseAndItems(Purchase purchase, List<PurchaseItem> updateItem) {
		String purchaseId = purchase.getId();
		Pair<Purchase, List<PurchaseItem>>  purchasePair = this.findByPurchaseId(purchaseId);
		Purchase oldPurchase = purchasePair.getLeft();
		List<PurchaseItem> oldItemList = purchasePair.getRight();
		List<PurchaseItem> itemList = purchase.getPurchaseItemList();


		purchase.setStatus(0);
		purchase.setCreatedTime(oldPurchase.getCreatedTime());
		this.updateByPrimaryKeySelective(purchase);
		// 删掉以前的PurchaseItem，然后重新插入
		this.deletePurchaseItem(purchase.getId());

		// 新插入item数据
		for (PurchaseItem oi : updateItem) {
			oi.setPurchaseId(purchaseId);


			int num = oi.getPurchaseNum();
			Item item = this.itemService.findById(oi.getItemId());

			Integer coefficient = (item.getConvertNum()==null)?0:item.getConvertNum();
//			num = num * coefficient;
//
//			oi.setConvertNum(num);
			purchaseItemMapper.insert(oi);
		}
	}

	public void deletePurchaseItem(String purchaseId) {
		PurchaseItemExample itemExample = new PurchaseItemExample();
		itemExample.createCriteria().andPurchaseIdEqualTo(purchaseId);

		purchaseItemMapper.deleteByExample(itemExample);
	}

	@Override
	public HashMap<String, Object> autoGetPaymenPrice(Integer supplierId, Date dateTime) {
		return purchaseItemMapper.autoGetPaymenPrice(supplierId, dateTime);
	}

	@Override
	public List<Purchase> findPurchasesBySupplierName(String supplierName, Integer cityId) {
		return purchaseMapper.findPurchasesBySupplierName(supplierName, cityId);
	}


}
