package com.b2b.common.dao;

import com.b2b.common.domain.NotifyState;
import com.b2b.common.domain.NotifyStateExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NotifyStateMapper {
    int countByExample(NotifyStateExample example);

    int deleteByExample(NotifyStateExample example);

    int deleteByPrimaryKey(String id);

    int insert(NotifyState record);

    int insertSelective(NotifyState record);

    List<NotifyState> selectByExample(NotifyStateExample example);

    NotifyState selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") NotifyState record, @Param("example") NotifyStateExample example);

    int updateByExample(@Param("record") NotifyState record, @Param("example") NotifyStateExample example);

    int updateByPrimaryKeySelective(NotifyState record);

    int updateByPrimaryKey(NotifyState record);
}