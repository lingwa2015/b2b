package com.b2b.common.dao;

import com.b2b.common.domain.WXUserInvoice;
import com.b2b.common.domain.WXUserInvoiceExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WXUserInvoiceMapper {
    int countByExample(WXUserInvoiceExample example);

    int deleteByExample(WXUserInvoiceExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WXUserInvoice record);

    int insertSelective(WXUserInvoice record);

    List<WXUserInvoice> selectByExample(WXUserInvoiceExample example);

    WXUserInvoice selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WXUserInvoice record, @Param("example") WXUserInvoiceExample example);

    int updateByExample(@Param("record") WXUserInvoice record, @Param("example") WXUserInvoiceExample example);

    int updateByPrimaryKeySelective(WXUserInvoice record);

    int updateByPrimaryKey(WXUserInvoice record);
    
    List<WXUserInvoice> findByWXUserId(@Param("wxuserId")int wxuserId);
}