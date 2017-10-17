package com.b2b.common.dao;

import com.b2b.common.domain.ShopDiscountSetting;
import com.b2b.common.domain.ShopDiscountSettingExample;

import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ShopDiscountSettingMapper {
    int countByExample(ShopDiscountSettingExample example);

    int deleteByExample(ShopDiscountSettingExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(ShopDiscountSetting record);

    int insertSelective(ShopDiscountSetting record);

    List<ShopDiscountSetting> selectByExample(ShopDiscountSettingExample example);

    ShopDiscountSetting selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") ShopDiscountSetting record, @Param("example") ShopDiscountSettingExample example);

    int updateByExample(@Param("record") ShopDiscountSetting record, @Param("example") ShopDiscountSettingExample example);

    int updateByPrimaryKeySelective(ShopDiscountSetting record);

    int updateByPrimaryKey(ShopDiscountSetting record);

	List<ShopDiscountSetting> findAllByCityId(Integer cityId);

	List<ShopDiscountSetting> findByCustomerIdAndCityId(@Param("id")Integer id, @Param("cityId")Integer cityId);

	List<ShopDiscountSetting> findPreferentialDetailsByConditions(@Param("startTime")Date startTime, @Param("endTime")Date endTime,@Param("userName") String userName,
			@Param("reseauId")Integer reseauId, @Param("cityId")Integer cityId);

	Long findCountByConditions(@Param("startTime")Date startTime, @Param("endTime")Date endTime,@Param("userName") String userName,
			@Param("reseauId")Integer reseauId, @Param("cityId")Integer cityId);
}