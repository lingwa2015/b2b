package com.b2b.common.dao;

import com.b2b.common.domain.BlackWhiteListItem;
import com.b2b.common.domain.BlackWhiteListItemExample;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BlackWhiteListItemMapper {
    int countByExample(BlackWhiteListItemExample example);

    int deleteByExample(BlackWhiteListItemExample example);

    int deleteByPrimaryKey(Integer bwliId);

    int insert(BlackWhiteListItem record);

    int insertSelective(BlackWhiteListItem record);

    List<BlackWhiteListItem> selectByExample(BlackWhiteListItemExample example);

    BlackWhiteListItem selectByPrimaryKey(Integer bwliId);

    int updateByExampleSelective(@Param("record") BlackWhiteListItem record, @Param("example") BlackWhiteListItemExample example);

    int updateByExample(@Param("record") BlackWhiteListItem record, @Param("example") BlackWhiteListItemExample example);

    int updateByPrimaryKeySelective(BlackWhiteListItem record);

    int updateByPrimaryKey(BlackWhiteListItem record);

	BlackWhiteListItem findByItemId(@Param("customerId")Integer customerId, @Param("flag")int flag, @Param("itemId")Integer itemId);

	List<HashMap<String, Object>> findAll(@Param("id")Integer id, @Param("i")int i);
}