package com.project.demo.dao;

import com.project.demo.DO.ShopTypeDO;

public interface ShopTypeDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopTypeDO record);

    int insertSelective(ShopTypeDO record);

    ShopTypeDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShopTypeDO record);

    int updateByPrimaryKey(ShopTypeDO record);
}