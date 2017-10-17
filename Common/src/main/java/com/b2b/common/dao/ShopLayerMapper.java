package com.b2b.common.dao;

import com.b2b.common.domain.ShopLayer;
import com.b2b.common.domain.ShopLayerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopLayerMapper {
    int countByExample(ShopLayerExample example);

    int deleteByExample(ShopLayerExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopLayer record);

    int insertSelective(ShopLayer record);

    List<ShopLayer> selectByExample(ShopLayerExample example);

    ShopLayer selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopLayer record, @Param("example") ShopLayerExample example);

    int updateByExample(@Param("record") ShopLayer record, @Param("example") ShopLayerExample example);

    int updateByPrimaryKeySelective(ShopLayer record);

    int updateByPrimaryKey(ShopLayer record);
}