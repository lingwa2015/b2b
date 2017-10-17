package com.b2b.common.dao;

import com.b2b.common.domain.ItemTaste;
import com.b2b.common.domain.ItemTasteExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemTasteMapper {
    int countByExample(ItemTasteExample example);

    int deleteByExample(ItemTasteExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ItemTaste record);

    int insertSelective(ItemTaste record);

    List<ItemTaste> selectByExample(ItemTasteExample example);

    ItemTaste selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ItemTaste record, @Param("example") ItemTasteExample example);

    int updateByExample(@Param("record") ItemTaste record, @Param("example") ItemTasteExample example);

    int updateByPrimaryKeySelective(ItemTaste record);

    int updateByPrimaryKey(ItemTaste record);

}