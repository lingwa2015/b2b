package com.b2b.common.dao;

import com.b2b.common.domain.WeightCoefficient;
import com.b2b.common.domain.WeightCoefficientExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeightCoefficientMapper {
    int countByExample(WeightCoefficientExample example);

    int deleteByExample(WeightCoefficientExample example);

    int deleteByPrimaryKey(Integer weightId);

    int insert(WeightCoefficient record);

    int insertSelective(WeightCoefficient record);

    List<WeightCoefficient> selectByExample(WeightCoefficientExample example);

    WeightCoefficient selectByPrimaryKey(Integer weightId);

    int updateByExampleSelective(@Param("record") WeightCoefficient record, @Param("example") WeightCoefficientExample example);

    int updateByExample(@Param("record") WeightCoefficient record, @Param("example") WeightCoefficientExample example);

    int updateByPrimaryKeySelective(WeightCoefficient record);

    int updateByPrimaryKey(WeightCoefficient record);
}