package com.b2b.common.dao;

import com.b2b.common.domain.Preferential;
import com.b2b.common.domain.PreferentialExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PreferentialMapper {
    int countByExample(PreferentialExample example);

    int deleteByExample(PreferentialExample example);

    int deleteByPrimaryKey(String id);

    int insert(Preferential record);

    int insertSelective(Preferential record);

    List<Preferential> selectByExample(PreferentialExample example);

    Preferential selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") Preferential record, @Param("example") PreferentialExample example);

    int updateByExample(@Param("record") Preferential record, @Param("example") PreferentialExample example);

    int updateByPrimaryKeySelective(Preferential record);

    int updateByPrimaryKey(Preferential record);
}