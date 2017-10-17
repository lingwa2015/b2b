package com.b2b.common.dao;

import com.b2b.common.domain.PurchaseItem;
import com.b2b.common.domain.PurchaseItemExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface PurchaseItemMapper {
    int countByExample(PurchaseItemExample example);

    int deleteByExample(PurchaseItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PurchaseItem record);

    int insertSelective(PurchaseItem record);

    List<PurchaseItem> selectByExample(PurchaseItemExample example);

    PurchaseItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PurchaseItem record, @Param("example") PurchaseItemExample example);

    int updateByExample(@Param("record") PurchaseItem record, @Param("example") PurchaseItemExample example);

    int updateByPrimaryKeySelective(PurchaseItem record);

    int updateByPrimaryKey(PurchaseItem record);

    List<PurchaseItem> findItemPlan(@Param("isDown") String isDown, @Param("cate") int cate
            , @Param("itemName") String itemName, @Param("cityId") Integer cityId, @Param("grade") String grade, @Param("newUserNum")Integer newUserNum);

    List<PurchaseItem> findbyItemId(@Param("itemId")Integer itemId);

    String findbySupplierId(@Param("supplierId")Integer supplierId);


    int deleteByItemId(@Param("itemId")Integer itemId);

    String findMaxPurchaseId();

    List<PurchaseItem> findItemPlans(@Param("cityId")Integer cityId);

    List<PurchaseItem> findPlans(@Param("cityId")Integer cityId);

    Integer findUseNumByItemId(@Param("itemId")Integer itemId);

    List<PurchaseItem> findItemsByPurchaseId(@Param("purchaseId")String purchaseId);

    PurchaseItem findItemsByItemIdAndCity(@Param("itemId")Integer itemId, @Param("cityId")Integer cityId);

    List<HashMap<String,Object>> findOrderItemByPurchaseId(String purchaseId);

    List<PurchaseItem> findInfoByStock(@Param("ids")String ids);

    List<PurchaseItem> findbyPurchaseIdAndItemid(@Param("purchaseId")String purchaseId, @Param("itemId")Integer itemId);

    Integer findEndCountByPurchaseId(String purchaseId);

    HashMap<String,Object> autoGetPaymenPrice(@Param("supplierId")Integer supplierId, @Param("dateTime")Date dateTime );

    int updatePurchaseItemStatus(@Param("purchaseId")String purchaseId, @Param("status")Integer status);
}