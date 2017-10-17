package com.b2b.common.dao;

import com.b2b.common.domain.WarehouseSyncList;
import com.b2b.common.domain.WarehouseSyncListExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WarehouseSyncListMapper {
    int countByExample(WarehouseSyncListExample example);

    int deleteByExample(WarehouseSyncListExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WarehouseSyncList record);

    int insertSelective(WarehouseSyncList record);

    List<WarehouseSyncList> selectByExample(WarehouseSyncListExample example);

    WarehouseSyncList selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WarehouseSyncList record, @Param("example") WarehouseSyncListExample example);

    int updateByExample(@Param("record") WarehouseSyncList record, @Param("example") WarehouseSyncListExample example);

    int updateByPrimaryKeySelective(WarehouseSyncList record);

    int updateByPrimaryKey(WarehouseSyncList record);
}