package com.b2b.common.dao;

import com.b2b.common.domain.StorageItem;
import com.b2b.common.domain.StorageItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface StorageItemMapper {
    int countByExample(StorageItemExample example);

    int deleteByExample(StorageItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(StorageItem record);

    int insertSelective(StorageItem record);

    List<StorageItem> selectByExample(StorageItemExample example);

    StorageItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") StorageItem record, @Param("example") StorageItemExample example);

    int updateByExample(@Param("record") StorageItem record, @Param("example") StorageItemExample example);

    int updateByPrimaryKeySelective(StorageItem record);

    int updateByPrimaryKey(StorageItem record);
    
    StorageItem selectStorageItemTotal(HashMap<String, Object> paramMap);
    
    int countByStorageItemSql(HashMap<String, Object> paramMap);
    
    List<StorageItem> selectStorageItemByDateTime(HashMap<String, Object> paramMap);

	List<StorageItem> findByStorageId(int id);

    List<StorageItem> findOutstockItem(@Param("supplierId")Integer supplierId, @Param("sumdate")Date sumdate);

    List<StorageItem> selectBySupplierIdAndSumdate(@Param("supplierId")Integer supplierId, @Param("sumdate")Date sumdate);
}