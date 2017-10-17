package com.b2b.common.dao;

import com.b2b.common.domain.Receipt;
import com.b2b.common.domain.ReceiptExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ReceiptMapper {
    int countByExample(ReceiptExample example);

    int deleteByExample(ReceiptExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Receipt record);

    int insertSelective(Receipt record);

    List<Receipt> selectByExample(ReceiptExample example);

    Receipt selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Receipt record, @Param("example") ReceiptExample example);

    int updateByExample(@Param("record") Receipt record, @Param("example") ReceiptExample example);

    int updateByPrimaryKeySelective(Receipt record);

    int updateByPrimaryKey(Receipt record);

    List<Receipt> findList(@Param("starttime") Date starttime, @Param("querytime") Date querytime,
                           @Param("crestdate") Date crestdate, @Param("creenddate") Date creenddate,
                           @Param("supplierName") String supplierName, @Param("cityId") Integer cityId);

    Long totalPrice(@Param("starttime") Date starttime, @Param("querytime") Date querytime,
                    @Param("crestdate") Date crestdate, @Param("creenddate") Date creenddate,
                    @Param("supplierName") String supplierName, @Param("cityId") Integer cityId);
}