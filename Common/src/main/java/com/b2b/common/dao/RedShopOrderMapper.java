package com.b2b.common.dao;

import com.b2b.common.domain.RedShopOrder;
import com.b2b.common.domain.RedShopOrderExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RedShopOrderMapper {
    int countByExample(RedShopOrderExample example);

    int deleteByExample(RedShopOrderExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(RedShopOrder record);

    int insertSelective(RedShopOrder record);

    List<RedShopOrder> selectByExample(RedShopOrderExample example);

    RedShopOrder selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") RedShopOrder record, @Param("example") RedShopOrderExample example);

    int updateByExample(@Param("record") RedShopOrder record, @Param("example") RedShopOrderExample example);

    int updateByPrimaryKeySelective(RedShopOrder record);

    int updateByPrimaryKey(RedShopOrder record);

	RedShopOrder findByOrderNo(String id);
}