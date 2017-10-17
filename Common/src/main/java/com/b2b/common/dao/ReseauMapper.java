package com.b2b.common.dao;

import com.b2b.common.domain.Reseau;
import com.b2b.common.domain.ReseauExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ReseauMapper {
    int countByExample(ReseauExample example);

    int deleteByExample(ReseauExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Reseau record);

    int insertSelective(Reseau record);

    List<Reseau> selectByExample(ReseauExample example);

    Reseau selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Reseau record, @Param("example") ReseauExample example);

    int updateByExample(@Param("record") Reseau record, @Param("example") ReseauExample example);

    int updateByPrimaryKeySelective(Reseau record);

    int updateByPrimaryKey(Reseau record);

	List<Reseau> findAllByCityId(@Param("cityId")Integer cityId);

	Reseau findById(Integer id);

	List<Reseau> findByCityIdAndIds(@Param("cityId")Integer cityId, @Param("ids")List<Integer> ids);

	List<Reseau> findAllWithManageNameByCityId(Integer cityId);
}