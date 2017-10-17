package com.b2b.common.dao;

import com.b2b.common.domain.WholeToken;
import com.b2b.common.domain.WholeTokenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WholeTokenMapper {
    int countByExample(WholeTokenExample example);

    int deleteByExample(WholeTokenExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WholeToken record);

    int insertSelective(WholeToken record);

    List<WholeToken> selectByExample(WholeTokenExample example);

    WholeToken selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WholeToken record, @Param("example") WholeTokenExample example);

    int updateByExample(@Param("record") WholeToken record, @Param("example") WholeTokenExample example);

    int updateByPrimaryKeySelective(WholeToken record);

    int updateByPrimaryKey(WholeToken record);

	WholeToken findByIdOneHour(int id);
}