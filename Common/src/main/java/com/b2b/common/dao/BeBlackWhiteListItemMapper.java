package com.b2b.common.dao;

import com.b2b.common.domain.BeBlackWhiteListItem;
import com.b2b.common.domain.BeBlackWhiteListItemExample;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface BeBlackWhiteListItemMapper {
    int countByExample(BeBlackWhiteListItemExample example);

    int deleteByExample(BeBlackWhiteListItemExample example);

    int deleteByPrimaryKey(Integer bbwliId);

    int insert(BeBlackWhiteListItem record);

    int insertSelective(BeBlackWhiteListItem record);

    List<BeBlackWhiteListItem> selectByExample(BeBlackWhiteListItemExample example);

    BeBlackWhiteListItem selectByPrimaryKey(Integer bbwliId);

    int updateByExampleSelective(@Param("record") BeBlackWhiteListItem record, @Param("example") BeBlackWhiteListItemExample example);

    int updateByExample(@Param("record") BeBlackWhiteListItem record, @Param("example") BeBlackWhiteListItemExample example);

    int updateByPrimaryKeySelective(BeBlackWhiteListItem record);

    int updateByPrimaryKey(BeBlackWhiteListItem record);

	BeBlackWhiteListItem findByItemId(@Param("customerId")int customerId, @Param("flag")int flag, @Param("itemId")Integer itemId);

	List<HashMap<String, Object>> findAll(@Param("id")Integer id, @Param("flag")int flag);
}