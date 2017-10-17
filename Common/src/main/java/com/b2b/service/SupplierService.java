package com.b2b.service;

import java.util.HashMap;
import java.util.List;

import com.b2b.common.domain.ItemSupplier;
import com.b2b.common.domain.Supplier;
import com.b2b.page.Page;

/**
 * 供应商管理
 * */
public interface SupplierService {
	public Page<Supplier> findPage(Supplier item , int currentPage ,int pageSize);
	
	public void create(Supplier dto);
	
	public void update(Supplier dto);
	
	public void delete(int id);
	
	public Supplier findById(int id);
	
	public List<Supplier> getSupplierList();

	public List<Supplier> findByItemId(int id);

	public List<HashMap<String, Object>> findBySupplierId(int id);

	public List<HashMap<String, Object>> findItemBySupplierId(
			Integer supplier_id);

	public List<Supplier> findByCityId(Integer cityId);

    List<HashMap<String,Object>> findByLikeUserNameAndCityId(String userName, Integer cityId);
}
