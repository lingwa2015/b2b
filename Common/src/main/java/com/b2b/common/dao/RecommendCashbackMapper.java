package com.b2b.common.dao;

import com.b2b.common.domain.RecommendCashback;
import com.b2b.common.domain.RecommendCashbackExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecommendCashbackMapper {
    int countByExample(RecommendCashbackExample example);

    int deleteByExample(RecommendCashbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RecommendCashback record);

    int insertSelective(RecommendCashback record);

    List<RecommendCashback> selectByExample(RecommendCashbackExample example);

    RecommendCashback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RecommendCashback record, @Param("example") RecommendCashbackExample example);

    int updateByExample(@Param("record") RecommendCashback record, @Param("example") RecommendCashbackExample example);

    int updateByPrimaryKeySelective(RecommendCashback record);

    int updateByPrimaryKey(RecommendCashback record);

    List<RecommendCashback> findByCondition(@Param("companyName")String companyName, @Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("cityId")Integer cityId);
}