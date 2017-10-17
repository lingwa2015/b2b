package com.b2b.common.dao;

import com.b2b.common.domain.Storage;
import com.b2b.common.domain.StorageExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface StorageMapper {
    int countByExample(StorageExample example);

    int deleteByExample(StorageExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Storage record);

    int insertSelective(Storage record);

    List<Storage> selectByExample(StorageExample example);

    Storage selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Storage record, @Param("example") StorageExample example);

    int updateByExample(@Param("record") Storage record, @Param("example") StorageExample example);

    int updateByPrimaryKeySelective(Storage record);

    int updateByPrimaryKey(Storage record);

	List<Storage> findByTime(@Param("start") Date start,@Param("end") Date end);

	int countByCondition(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("numbers")String numbers,
			@Param("supplierName")String supplierName, @Param("cityId")Integer cityId,  @Param("itemName")String itemName);

	List<Storage> selectByCondition(@Param("startTime")Date startTime, @Param("endTime")Date endTime,
			@Param("numbers")String numbers, @Param("supplierName")String supplierName,@Param("cityId") Integer cityId,@Param("itemName") String itemName, @Param("start")int start, @Param("pageSize")int pageSize);

	Storage selectById(int id);

	List<Storage> findByTimeAndCityId(@Param("start")Date start, @Param("end")Date end, @Param("cityId")Integer cityId);

    List<Storage> findPurchaseBysupplierIdAndSumdate(@Param("supplierId")Integer supplierId, @Param("sumdate")Date sumdate);
}