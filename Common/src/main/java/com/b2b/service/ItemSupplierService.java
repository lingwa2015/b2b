package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.ItemSupplier;

public interface ItemSupplierService {

	void deleteByItemId(Integer id);

	void save(ItemSupplier itemSupplier);

	Integer save(List<ItemSupplier> itemSupplier, Integer suppilerId);

}
