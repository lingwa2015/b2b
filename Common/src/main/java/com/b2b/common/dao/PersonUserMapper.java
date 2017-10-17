package com.b2b.common.dao;

import com.b2b.common.domain.PersonUser;
import com.b2b.common.domain.PersonUserExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PersonUserMapper {
    int countByExample(PersonUserExample example);

    int deleteByExample(PersonUserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(PersonUser record);

    int insertSelective(PersonUser record);

    List<PersonUser> selectByExample(PersonUserExample example);

    PersonUser selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") PersonUser record, @Param("example") PersonUserExample example);

    int updateByExample(@Param("record") PersonUser record, @Param("example") PersonUserExample example);

    int updateByPrimaryKeySelective(PersonUser record);

    int updateByPrimaryKey(PersonUser record);

	List<PersonUser> findUserkfFirst();

	List<PersonUser> findUsercgFirst();

	List<PersonUser> findUserpsFirst();

	void deleteReseauId(Integer id);

	PersonUser findByReseauId(Integer reseauId);

	PersonUser findByOpenId(String openid);

	List<PersonUser> findAll();

	List<PersonUser> findUserkfFirstByCityId(@Param("cityId")Integer cityId);

	List<PersonUser> findAllByCityId(@Param("cityId")Integer cityId);

	List<PersonUser> findUsercgFirstByCityId(@Param("cityId")Integer cityId);

	List<PersonUser> findUserpsFirstByCityId(@Param("cityId")Integer cityId);

	List<PersonUser> findUsershFirstByCityId(@Param("cityId")Integer cityId);

	List<PersonUser> findSHByCityId(Integer cityId);

    List<PersonUser> findWarning(@Param("reseauId")Integer reseauId);

	List<PersonUser> findUserkhjlFirstByCityId(@Param("cityId")Integer cityId);

	List<Integer> findIdsByManagerId(Integer id);

	List<Integer> findReseauIdsByManagerId(Integer id);

	List<PersonUser> findByPost(@Param("post")String post, @Param("cityId")Integer cityId);

    List<PersonUser> findAllByCityIdUserNameTelStatus(@Param("cityId")Integer cityId, @Param("userName")String userName,
													  @Param("mobilePhone")String mobilePhone, @Param("validStatus")Integer validStatus);

	List<PersonUser> findByManagerId(Integer id);

	List<PersonUser> findSalesByCityIdAndGreadAndManageId(@Param("cityId")Integer cityId, @Param("gread")Integer gread, @Param("id")Integer id);

	List<PersonUser> findSalesByCityIdAndPostAndGread(@Param("cityId")Integer cityId, @Param("post")String post, @Param("gread")Integer gread);
}