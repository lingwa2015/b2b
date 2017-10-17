package com.b2b.common.dao;

import com.b2b.common.domain.WXUserAddress;
import com.b2b.common.domain.WXUserAddressExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface WXUserAddressMapper {
    int countByExample(WXUserAddressExample example);

    int deleteByExample(WXUserAddressExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(WXUserAddress record);

    int insertSelective(WXUserAddress record);

    List<WXUserAddress> selectByExample(WXUserAddressExample example);

    WXUserAddress selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") WXUserAddress record, @Param("example") WXUserAddressExample example);

    int updateByExample(@Param("record") WXUserAddress record, @Param("example") WXUserAddressExample example);

    int updateByPrimaryKeySelective(WXUserAddress record);

    int updateByPrimaryKey(WXUserAddress record);
    
    int updateDefaultStatusByWXUserId(@Param("wxuserId")int wxuserId);
    
    List<WXUserAddress> findByWXUserId(@Param("wxuserId")int wxuserId);
}