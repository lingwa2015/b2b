package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.ItemTaste;

public interface ItemTasteService {

	ItemTaste findByNameAndItemId(Integer id, String taste, String barcode);

	void created(ItemTaste itemtaste);

	void deleteAllByItemId(Integer id);

	List<ItemTaste> findByItemId(int id);

	List<ItemTaste> findAll();

}
