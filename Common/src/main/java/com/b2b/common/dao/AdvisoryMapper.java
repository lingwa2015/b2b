package com.b2b.common.dao;

import com.b2b.common.domain.Advisory;
import com.b2b.common.domain.AdvisoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AdvisoryMapper {
    int countByExample(AdvisoryExample example);

    int deleteByExample(AdvisoryExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Advisory record);

    int insertSelective(Advisory record);

    List<Advisory> selectByExample(AdvisoryExample example);

    Advisory selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Advisory record, @Param("example") AdvisoryExample example);

    int updateByExample(@Param("record") Advisory record, @Param("example") AdvisoryExample example);

    int updateByPrimaryKeySelective(Advisory record);

    int updateByPrimaryKey(Advisory record);
}