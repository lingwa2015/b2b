package com.b2b.common.dao;

import com.b2b.common.domain.WarehouseSync;
import com.b2b.common.domain.WarehouseSyncExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WarehouseSyncMapper {
    int countByExample(WarehouseSyncExample example);

    int deleteByExample(WarehouseSyncExample example);

    int deleteByPrimaryKey(Long id);

    int insert(WarehouseSync record);

    int insertSelective(WarehouseSync record);

    List<WarehouseSync> selectByExample(WarehouseSyncExample example);

    WarehouseSync selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") WarehouseSync record, @Param("example") WarehouseSyncExample example);

    int updateByExample(@Param("record") WarehouseSync record, @Param("example") WarehouseSyncExample example);

    int updateByPrimaryKeySelective(WarehouseSync record);

    int updateByPrimaryKey(WarehouseSync record);
}