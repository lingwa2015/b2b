package com.b2b.common.dao;

import com.b2b.common.domain.Refund;
import com.b2b.common.domain.RefundExample;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface RefundMapper {
    int countByExample(RefundExample example);

    int deleteByExample(RefundExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Refund record);

    int insertSelective(Refund record);

    List<Refund> selectByExample(RefundExample example);

    Refund selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Refund record, @Param("example") RefundExample example);

    int updateByExample(@Param("record") Refund record, @Param("example") RefundExample example);

    int updateByPrimaryKeySelective(Refund record);

    int updateByPrimaryKey(Refund record);

	int countMoneyByUserAndTime(@Param("id")Integer id, @Param("firstDate")Date firstDate, @Param("sumDate")Date sumDate);

	int countNumByUserAndTime(@Param("id")Integer id, @Param("firstDate")Date firstDate, @Param("sumDate")Date sumDate);

	List<Refund> findRefundByShopId(Integer shopId);

	List<HashMap<String, Object>> findItemInfoByOrderNo(Integer id);

	int findRefundNumByMonth(@Param("id")Integer id, @Param("date")Date date);

	Refund findCurrentMonthFreeShopRefundFee(@Param("id")Integer id, @Param("start")Date date2, @Param("end")Date date1);

	Long findTotal(@Param("userName")String userName, @Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("param")String param, @Param("reseauId")int reseauId, @Param("cityId")Integer cityId, @Param("itemName")String itemName);

	Integer findBeConfirmRefundByCityId(@Param("cityId")Integer cityId);

	List<Refund> findByCondition(@Param("userName")String userName, @Param("startTime")Date startTime, @Param("endTime")Date endTime,
			@Param("param")String param, @Param("reseauId")int reseauId, @Param("cityId")Integer cityId, @Param("itemName")String itemName);

	List<Refund> findByTimeAndCityId(@Param("start")Date start,@Param("end") Date end,@Param("cityId") Integer cityId);

	Refund findRefundFeeAndRefundNumByDayAndUserId(@Param("userId")Integer userId, @Param("date")Date date);
}