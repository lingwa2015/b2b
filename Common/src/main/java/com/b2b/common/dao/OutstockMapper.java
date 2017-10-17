package com.b2b.common.dao;

import com.b2b.common.domain.Outstock;
import com.b2b.common.domain.OutstockExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OutstockMapper {
    int countByExample(OutstockExample example);

    int deleteByExample(OutstockExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Outstock record);

    int insertSelective(Outstock record);

    List<Outstock> selectByExample(OutstockExample example);

    Outstock selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Outstock record, @Param("example") OutstockExample example);

    int updateByExample(@Param("record") Outstock record, @Param("example") OutstockExample example);

    int updateByPrimaryKeySelective(Outstock record);

    int updateByPrimaryKey(Outstock record);

    Long getMaxOutstockId();

    List<Outstock> findOutstocksAndOutstocksItemByCondition(@Param("startTime")Date startTime, @Param("endTime")Date endTime,
                                                            @Param("supplierName")String supplierName, @Param("itemName")String itemName,
                                                            @Param("param")String param, @Param("cityId")Integer cityId);

    Integer findPreparationNum(@Param("cityId")Integer cityId);

    Long findTotalPrice(@Param("startTime")Date startTime, @Param("endTime")Date endTime,
                        @Param("supplierName")String supplierName, @Param("itemName")String itemName,
                        @Param("param")String param, @Param("cityId")Integer cityId);
}