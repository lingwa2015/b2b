package com.b2b.common.dao;

import com.b2b.common.domain.RedReceive;
import com.b2b.common.domain.RedReceiveExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RedReceiveMapper {
    int countByExample(RedReceiveExample example);

    int deleteByExample(RedReceiveExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RedReceive record);

    int insertSelective(RedReceive record);

    List<RedReceive> selectByExample(RedReceiveExample example);

    RedReceive selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RedReceive record, @Param("example") RedReceiveExample example);

    int updateByExample(@Param("record") RedReceive record, @Param("example") RedReceiveExample example);

    int updateByPrimaryKeySelective(RedReceive record);

    int updateByPrimaryKey(RedReceive record);

	Integer findTodayNumByBuyerIdAndTypeAndCityId(@Param("buyerId")Integer buyerId, @Param("sign")int sign, @Param("cityId")Integer cityId);

	RedReceive findByOrderNo(String orderNo);

	Integer findTodayNumByRedTypeIdAndCityId(@Param("redTypeId")Integer id, @Param("cityId")Integer cityId);

	List<RedReceive> findByCondition(@Param("redId")Integer redId, @Param("cityId")Integer cityId);

	Long findTotalFeeByCityId(Integer cityId);
}