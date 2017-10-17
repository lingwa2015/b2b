package com.b2b.common.dao;

import com.b2b.common.domain.RecommendRedback;
import com.b2b.common.domain.RecommendRedbackExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RecommendRedbackMapper {
    int countByExample(RecommendRedbackExample example);

    int deleteByExample(RecommendRedbackExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RecommendRedback record);

    int insertSelective(RecommendRedback record);

    List<RecommendRedback> selectByExample(RecommendRedbackExample example);

    RecommendRedback selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RecommendRedback record, @Param("example") RecommendRedbackExample example);

    int updateByExample(@Param("record") RecommendRedback record, @Param("example") RecommendRedbackExample example);

    int updateByPrimaryKeySelective(RecommendRedback record);

    int updateByPrimaryKey(RecommendRedback record);

    List<RecommendRedback> findByCondition(@Param("companyName")String companyName, @Param("startTime")Date startTime, @Param("endTime")Date endTime, @Param("cityId")Integer cityId);
}