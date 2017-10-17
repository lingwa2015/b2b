package com.b2b.common.dao;

import com.b2b.common.domain.ShopSales;
import com.b2b.common.domain.ShopSalesExample;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ShopSalesMapper {
    int countByExample(ShopSalesExample example);

    int deleteByExample(ShopSalesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopSales record);

    int insertSelective(ShopSales record);

    List<ShopSales> selectByExample(ShopSalesExample example);

    ShopSales selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopSales record, @Param("example") ShopSalesExample example);

    int updateByExample(@Param("record") ShopSales record, @Param("example") ShopSalesExample example);

    int updateByPrimaryKeySelective(ShopSales record);

    int updateByPrimaryKey(ShopSales record);

	void deleteBySumDateAndCityId(@Param("cityId")Integer cityId, @Param("sumDate")Date sumdate);

	List<ShopSales> findByCondition(@Param("date") String date, @Param("interfaceId") int interfaceId, @Param("cityId") Integer cityId,  @Param("dateStart")Date dateStart,  @Param("dateEnd")Date dateEnd);

	List<ShopSales> findDetailByInterfaceIdAndDateAndCityId(@Param("sumdate") String sumdate, @Param("interfaceId") Integer interfaceId, @Param("cityId") Integer cityId, @Param("dateEnd")Date dateEnd);

	List<ShopSales> findCurrentMonthDetailByInterfaceIdAndCityId(@Param("interfaceId")Integer interfaceId, @Param("cityId")Integer cityId);
}