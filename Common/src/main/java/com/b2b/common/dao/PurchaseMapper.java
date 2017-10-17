package com.b2b.common.dao;

import com.b2b.common.domain.Purchase;
import com.b2b.common.domain.PurchaseExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PurchaseMapper {
    int countByExample(PurchaseExample example);

    int deleteByExample(PurchaseExample example);

    int deleteByPrimaryKey(String id);

    int insert(Purchase record);

    int insertSelective(Purchase record);

    List<Purchase> selectByExample(PurchaseExample example);

    Purchase selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Purchase record, @Param("example") PurchaseExample example);

    int updateByExample(@Param("record") Purchase record, @Param("example") PurchaseExample example);

    int updateByPrimaryKeySelective(Purchase record);

    int updateByPrimaryKey(Purchase record);

    List<Purchase> findPurchasesAndPurchasesItemByCondition(@Param("startTime")Date startTime, @Param("endTime")Date endTime,
                                                            @Param("purchaseId")String purchaseId, @Param("supplierName")String supplierName,
                                                            @Param("itemName")String itemName, @Param("cityId")Integer cityId);

    int updatePurchasePrintStatus(@Param("id")String id);

    List<Purchase> findPurchasesBySupplierName(@Param("supplierName")String supplierName, @Param("cityId")Integer cityId);

}