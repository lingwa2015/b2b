package com.b2b.common.dao;

import com.b2b.common.domain.ItemVariety;
import com.b2b.common.domain.ItemVarietyExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemVarietyMapper {
    int countByExample(ItemVarietyExample example);

    int deleteByExample(ItemVarietyExample example);

    int deleteByPrimaryKey(Integer itemvarietyId);

    int insert(ItemVariety record);

    int insertSelective(ItemVariety record);

    List<ItemVariety> selectByExample(ItemVarietyExample example);

    ItemVariety selectByPrimaryKey(Integer itemvarietyId);

    int updateByExampleSelective(@Param("record") ItemVariety record, @Param("example") ItemVarietyExample example);

    int updateByExample(@Param("record") ItemVariety record, @Param("example") ItemVarietyExample example);

    int updateByPrimaryKeySelective(ItemVariety record);

    int updateByPrimaryKey(ItemVariety record);
}