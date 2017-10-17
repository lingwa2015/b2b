package com.b2b.common.dao;

import com.b2b.common.domain.DataCollect;
import com.b2b.common.domain.DataCollectExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DataCollectMapper {
    int countByExample(DataCollectExample example);

    int deleteByExample(DataCollectExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(DataCollect record);

    int insertSelective(DataCollect record);

    List<DataCollect> selectByExample(DataCollectExample example);

    DataCollect selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") DataCollect record, @Param("example") DataCollectExample example);

    int updateByExample(@Param("record") DataCollect record, @Param("example") DataCollectExample example);

    int updateByPrimaryKeySelective(DataCollect record);

    int updateByPrimaryKey(DataCollect record);
}