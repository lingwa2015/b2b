package com.b2b.common.dao;

import com.b2b.common.domain.ItemScore;
import com.b2b.common.domain.ItemScoreExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ItemScoreMapper {
    int countByExample(ItemScoreExample example);

    int deleteByExample(ItemScoreExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemScore record);

    int insertSelective(ItemScore record);

    List<ItemScore> selectByExample(ItemScoreExample example);

    ItemScore selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ItemScore record, @Param("example") ItemScoreExample example);

    int updateByExample(@Param("record") ItemScore record, @Param("example") ItemScoreExample example);

    int updateByPrimaryKeySelective(ItemScore record);

    int updateByPrimaryKey(ItemScore record);

	List<ItemScore> findAvgGroupByItemId();

	List<ItemScore> findByItemId(Integer itemId);
}