package com.b2b.common.dao;

import com.b2b.common.domain.AccessToken;
import com.b2b.common.domain.AccessTokenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface AccessTokenMapper {
    int countByExample(AccessTokenExample example);

    int deleteByExample(AccessTokenExample example);

    int deleteByPrimaryKey(String id);

    int insert(AccessToken record);

    int insertSelective(AccessToken record);

    List<AccessToken> selectByExample(AccessTokenExample example);

    AccessToken selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") AccessToken record, @Param("example") AccessTokenExample example);

    int updateByExample(@Param("record") AccessToken record, @Param("example") AccessTokenExample example);

    int updateByPrimaryKeySelective(AccessToken record);

    int updateByPrimaryKey(AccessToken record);
}