package com.b2b.common.dao;

import com.b2b.common.domain.ItemWeekVolume;
import com.b2b.common.domain.ItemWeekVolumeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemWeekVolumeMapper {
    int countByExample(ItemWeekVolumeExample example);

    int deleteByExample(ItemWeekVolumeExample example);

    int deleteByPrimaryKey(Integer itemId);

    int insert(ItemWeekVolume record);

    int insertSelective(ItemWeekVolume record);

    List<ItemWeekVolume> selectByExample(ItemWeekVolumeExample example);

    ItemWeekVolume selectByPrimaryKey(Integer itemId);

    int updateByExampleSelective(@Param("record") ItemWeekVolume record, @Param("example") ItemWeekVolumeExample example);

    int updateByExample(@Param("record") ItemWeekVolume record, @Param("example") ItemWeekVolumeExample example);

    int updateByPrimaryKeySelective(ItemWeekVolume record);

    int updateByPrimaryKey(ItemWeekVolume record);

    List<ItemWeekVolume> findVolume();

    Integer deleteAll();
}