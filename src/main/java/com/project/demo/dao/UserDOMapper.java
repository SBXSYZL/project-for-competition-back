package com.project.demo.dao;

import com.project.demo.DO.UserDO;
import org.apache.ibatis.annotations.Param;

public interface UserDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(UserDO record);

    int insertSelective(UserDO record);

    UserDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserDO record);

    int updateByPrimaryKey(UserDO record);

    UserDO selectByTel(@Param("tel") String tel);

    void registered(@Param("userName") String userName, @Param("tel") String tel, @Param("realPsd") String realPsd);

}