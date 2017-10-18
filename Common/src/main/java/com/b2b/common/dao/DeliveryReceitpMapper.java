package com.b2b.common.dao;

import com.b2b.common.domain.DeliveryReceitp;
import com.b2b.common.domain.DeliveryReceitpExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface DeliveryReceitpMapper {
    int countByExample(DeliveryReceitpExample example);

    int deleteByExample(DeliveryReceitpExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DeliveryReceitp record);

    int insertSelective(DeliveryReceitp record);

    List<DeliveryReceitp> selectByExample(DeliveryReceitpExample example);

    DeliveryReceitp selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DeliveryReceitp record, @Param("example") DeliveryReceitpExample example);

    int updateByExample(@Param("record") DeliveryReceitp record, @Param("example") DeliveryReceitpExample example);

    int updateByPrimaryKeySelective(DeliveryReceitp record);

    int updateByPrimaryKey(DeliveryReceitp record);

	List<DeliveryReceitp> findByCityId(Integer cityId);

	List<DeliveryReceitp> findByCityIdAndInterfaceId(@Param("cityId")Integer cityId, @Param("id") Integer id);

	List<DeliveryReceitp> findByCondition(@Param("cityId")Integer cityId, @Param("startTime")Date startTime,
                                          @Param("endTime")Date endTime, @Param("linkName")String linkName,
                                          @Param("userName")String userName, @Param("status")String status,
                                          @Param("tagStatus")String tagStatus);

	void delete(Integer id);

	int findTodayNumByCityId(Integer cityId);

	void updateIswxvipNull(Integer id);

	int findTodayNumByCityIdAndInterfaceIds(@Param("cityId")Integer cityId, @Param("ids")List<Integer> ids);

	List<DeliveryReceitp> findByCityIdAndInterfaceIds(@Param("cityId")Integer cityId, @Param("ids")List<Integer> ids);

    int findMonthNumByCityIdAndInterfaceIds(@Param("cityId")Integer cityId, @Param("ids")List<Integer> ids);

    HashMap<String,Object> findSaleNumByCityIdAndIds(@Param("cityId")Integer cityId, @Param("id")Integer id);

    Map<String,String> findSumDate(@Param("cityId")Integer cityId, @Param("startTime")Date startTime,
                                   @Param("endTime")Date endTime, @Param("linkName")String linkName,
                                   @Param("userName")String userName, @Param("status")String status,
                                   @Param("tagStatus")String tagStatus);
}