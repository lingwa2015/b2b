package com.b2b.common.dao;

import com.b2b.common.domain.RedPacketCustomer;
import com.b2b.common.domain.RedPacketCustomerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RedPacketCustomerMapper {
    int countByExample(RedPacketCustomerExample example);

    int deleteByExample(RedPacketCustomerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RedPacketCustomer record);

    int insertSelective(RedPacketCustomer record);

    List<RedPacketCustomer> selectByExample(RedPacketCustomerExample example);

    RedPacketCustomer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RedPacketCustomer record, @Param("example") RedPacketCustomerExample example);

    int updateByExample(@Param("record") RedPacketCustomer record, @Param("example") RedPacketCustomerExample example);

    int updateByPrimaryKeySelective(RedPacketCustomer record);

    int updateByPrimaryKey(RedPacketCustomer record);

	RedPacketCustomer findByRedIdAndShopId(@Param("redId")Integer redId, @Param("shopId")Integer shopId);
}