package com.b2b.common.dao;

import com.b2b.common.domain.Token;
import com.b2b.common.domain.TokenExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TokenMapper {
    int countByExample(TokenExample example);

    int deleteByExample(TokenExample example);

    int deleteByPrimaryKey(Integer shopId);

    int insert(Token record);

    int insertSelective(Token record);

    List<Token> selectByExample(TokenExample example);

    Token selectByPrimaryKey(Integer shopId);

    int updateByExampleSelective(@Param("record") Token record, @Param("example") TokenExample example);

    int updateByExample(@Param("record") Token record, @Param("example") TokenExample example);

    int updateByPrimaryKeySelective(Token record);

    int updateByPrimaryKey(Token record);

	Token findByIdAndTime(Integer shop_id);
}