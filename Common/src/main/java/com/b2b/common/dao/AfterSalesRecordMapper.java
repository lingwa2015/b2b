package com.b2b.common.dao;

import com.b2b.common.domain.AfterSalesRecord;
import com.b2b.common.domain.AfterSalesRecordExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface AfterSalesRecordMapper {
    int countByExample(AfterSalesRecordExample example);

    int deleteByExample(AfterSalesRecordExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(AfterSalesRecord record);

    int insertSelective(AfterSalesRecord record);

    List<AfterSalesRecord> selectByExample(AfterSalesRecordExample example);

    AfterSalesRecord selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") AfterSalesRecord record, @Param("example") AfterSalesRecordExample example);

    int updateByExample(@Param("record") AfterSalesRecord record, @Param("example") AfterSalesRecordExample example);

    int updateByPrimaryKeySelective(AfterSalesRecord record);

    int updateByPrimaryKey(AfterSalesRecord record);

	List<AfterSalesRecord> findByCondition(@Param("userName")String userName, @Param("recordMan")String recordMan,@Param("fuzeMan")String fuzeMan,
			@Param("recordType")String recordType, @Param("doState")String doState, @Param("regionId")Integer regionId,  @Param("reseauId")Integer reseauId, @Param("cityId")Integer cityId);

	void delete(Integer id);

	List<AfterSalesRecord> findByUserId(Integer shopId);

	List<AfterSalesRecord> findByUserIdAndFlag(@Param("shopId")Integer shopId, @Param("flag")int flag);

	List<AfterSalesRecord> findByUserIdLimitTwo(Integer shopId);

	List<AfterSalesRecord> findByUserIdNotCompelete(@Param("customerId")Integer customerId);

	int findCountByUserIdNotCompelete(@Param("shopId")Integer shopId);
}