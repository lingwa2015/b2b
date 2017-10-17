package com.b2b.service;

import java.util.List;

import com.b2b.common.domain.CategoryNum;
import com.b2b.common.domain.CategoryNumExample;
import com.b2b.common.domain.ItemVariety;
import com.b2b.common.domain.Refund;
import com.b2b.common.domain.SnackPackageType;
import com.b2b.page.Page;

/**
 * 零食包类型
 * */
public interface SnackPackageTypeService {
//	void delete(int id);

	void insert(SnackPackageType snackPackageType);
	
    List<SnackPackageType> selectAll();
    
    void updateSnackPackageType(SnackPackageType snackPackageType);
    
    void deleteSnackPackageType(SnackPackageType snackPackageType);
    
    SnackPackageType findById(int id);
    
    Page<SnackPackageType> findPage();
}
