package com.b2b.common.dao;

import com.b2b.common.domain.WXConsumRecords;
import com.b2b.common.domain.WXConsumRecordsExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WXConsumRecordsMapper {
    int countByExample(WXConsumRecordsExample example);

    int deleteByExample(WXConsumRecordsExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WXConsumRecords record);

    int insertSelective(WXConsumRecords record);

    List<WXConsumRecords> selectByExample(WXConsumRecordsExample example);

    WXConsumRecords selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WXConsumRecords record, @Param("example") WXConsumRecordsExample example);

    int updateByExample(@Param("record") WXConsumRecords record, @Param("example") WXConsumRecordsExample example);

    int updateByPrimaryKeySelective(WXConsumRecords record);

    int updateByPrimaryKey(WXConsumRecords record);
}