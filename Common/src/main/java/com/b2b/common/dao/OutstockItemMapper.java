package com.b2b.common.dao;

import com.b2b.common.domain.OutstockItem;
import com.b2b.common.domain.OutstockItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OutstockItemMapper {
    int countByExample(OutstockItemExample example);

    int deleteByExample(OutstockItemExample example);

    int deleteByPrimaryKey(Long id);

    int insert(OutstockItem record);

    int insertSelective(OutstockItem record);

    List<OutstockItem> selectByExample(OutstockItemExample example);

    OutstockItem selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") OutstockItem record, @Param("example") OutstockItemExample example);

    int updateByExample(@Param("record") OutstockItem record, @Param("example") OutstockItemExample example);

    int updateByPrimaryKeySelective(OutstockItem record);

    int updateByPrimaryKey(OutstockItem record);

    List<OutstockItem> findByOutstockId(Long outstockId);
}