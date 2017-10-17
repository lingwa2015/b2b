package com.b2b.common.dao;

import com.b2b.common.domain.ConsumeRank;
import com.b2b.common.domain.ConsumeRankExample;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ConsumeRankMapper {
    int countByExample(ConsumeRankExample example);

    int deleteByExample(ConsumeRankExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ConsumeRank record);

    int insertSelective(ConsumeRank record);

    List<ConsumeRank> selectByExample(ConsumeRankExample example);

    ConsumeRank selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ConsumeRank record, @Param("example") ConsumeRankExample example);

    int updateByExample(@Param("record") ConsumeRank record, @Param("example") ConsumeRankExample example);

    int updateByPrimaryKeySelective(ConsumeRank record);

    int updateByPrimaryKey(ConsumeRank record);

	List<ConsumeRank> rank(@Param("id")Integer id, @Param("startDate")Date startDate, @Param("endDate")Date endDate);

	List<ConsumeRank> findByShopId(Integer shopId);

	Long findTotalByShopId(Integer shopId);

	List<Integer> findShopIdByWeekFeeAndKinds();

	List<ConsumeRank> findItemIdByShopId(Integer id);
}