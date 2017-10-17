package com.b2b.common.dao;

import com.b2b.common.domain.SeniorSet;
import com.b2b.common.domain.SeniorSetExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface SeniorSetMapper {
    int countByExample(SeniorSetExample example);

    int deleteByExample(SeniorSetExample example);

    int deleteByPrimaryKey(Integer shopId);

    int insert(SeniorSet record);

    int insertSelective(SeniorSet record);

    List<SeniorSet> selectByExample(SeniorSetExample example);

    SeniorSet selectByPrimaryKey(Integer shopId);

    int updateByExampleSelective(@Param("record") SeniorSet record, @Param("example") SeniorSetExample example);

    int updateByExample(@Param("record") SeniorSet record, @Param("example") SeniorSetExample example);

    int updateByPrimaryKeySelective(SeniorSet record);

    int updateByPrimaryKey(SeniorSet record);
}