package com.b2b.common.dao;

import com.b2b.common.domain.WeiXinUser;
import com.b2b.common.domain.WeiXinUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WeiXinUserMapper {
    int countByExample(WeiXinUserExample example);

    int deleteByExample(WeiXinUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WeiXinUser record);

    int insertSelective(WeiXinUser record);

    List<WeiXinUser> selectByExample(WeiXinUserExample example);

    WeiXinUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WeiXinUser record, @Param("example") WeiXinUserExample example);

    int updateByExample(@Param("record") WeiXinUser record, @Param("example") WeiXinUserExample example);

    int updateByPrimaryKeySelective(WeiXinUser record);

    int updateByPrimaryKey(WeiXinUser record);
}