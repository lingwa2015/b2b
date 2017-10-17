package com.b2b.service;

import com.b2b.common.domain.Purchase;
import com.b2b.common.domain.PurchaseItem;
import org.apache.commons.lang3.tuple.Pair;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface PurchaseService {

	int insert(Purchase purchase);

	List<Purchase> findPurchasesAndPurchasesItemByCondition(Date startTime, Date endTime, String purchaseId,
															String supplierName, String itemName, Integer cityId);

	Pair<Purchase, List<PurchaseItem>> findByPurchaseId(String purchaseId);

	int updatePurchasePrintStatus(String purchaseId);

	int updatePurchaseStatus(String purchaseId, Integer status);

	Purchase findPurchaseById(String id);

	int updateByPrimaryKeySelective (Purchase purchase);

    void createPurchase(Purchase purchase);

	Pair<Purchase,List<HashMap<String,Object>>> findInfoByPurchaseId(String purchaseId);

	void updatePurchaseAndItems(Purchase purchase, List<PurchaseItem> updateItem);

    HashMap<String,Object> autoGetPaymenPrice(Integer supplierId, Date dateTime);

    List<Purchase> findPurchasesBySupplierName(String supplierName, Integer cityId);

	List<PurchaseItem> findPurchaseItem(String purchaseId);
}
