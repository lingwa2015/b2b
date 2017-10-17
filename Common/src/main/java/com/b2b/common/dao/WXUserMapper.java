package com.b2b.common.dao;

import com.b2b.common.domain.WXUser;
import com.b2b.common.domain.WXUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WXUserMapper {
    int countByExample(WXUserExample example);

    int deleteByExample(WXUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WXUser record);

    int insertSelective(WXUser record);

    List<WXUser> selectByExample(WXUserExample example);

    WXUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WXUser record, @Param("example") WXUserExample example);

    int updateByExample(@Param("record") WXUser record, @Param("example") WXUserExample example);

    int updateByPrimaryKeySelective(WXUser record);

    int updateByPrimaryKey(WXUser record);
}