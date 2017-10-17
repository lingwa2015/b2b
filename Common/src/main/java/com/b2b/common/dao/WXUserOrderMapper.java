package com.b2b.common.dao;

import com.b2b.common.domain.WXUserOrder;
import com.b2b.common.domain.WXUserOrderExample;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface WXUserOrderMapper {
    int countByExample(WXUserOrderExample example);

    int deleteByExample(WXUserOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WXUserOrder record);

    int insertSelective(WXUserOrder record);

    List<WXUserOrder> selectByExample(WXUserOrderExample example);

    WXUserOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WXUserOrder record, @Param("example") WXUserOrderExample example);

    int updateByExample(@Param("record") WXUserOrder record, @Param("example") WXUserOrderExample example);

    int updateByPrimaryKeySelective(WXUserOrder record);

    int updateByPrimaryKey(WXUserOrder record);

	int findCountByCondition(@Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("orderNum")String orderNum,
			@Param("userName")String userName, @Param("param")String param);

	List<WXUserOrder> findOrderByCondition(@Param("startTime")Date startTime, @Param("endTime")Date endTime,
			@Param("orderNum")String orderNum, @Param("userName")String userName,@Param("param") String param, @Param("start")int start,
			@Param("pageSize")int pageSize);

	WXUserOrder findById(Integer id);

	List<WXUserOrder> findByWXUserId(Integer id);

	WXUserOrder findOrderDetailById(String id);

	void updatePayfeeStatusByOrderNo(@Param("id")Integer id);

	List<WXUserOrder> findAllwxOrderByCondition(@Param("startTime")Date startTime, @Param("endTime")Date endTime,
			@Param("orderNum")String orderNum, @Param("userName")String userName,@Param("param") String param, @Param("start")int start,
			@Param("pageSize")int pageSize);

	void deleteFastNo(@Param("id")Integer id);

	void modifyFastNo(@Param("id")Integer id,@Param("fastNO") String fastNO);

	HashMap<String, Object> findpingdanInfoById(Integer id);

	List<WXUserOrder> findExpirePingDanOrder();
}