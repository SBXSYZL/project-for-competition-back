package com.project.demo.dao;

import com.project.demo.DO.ShopDO;

public interface ShopDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ShopDO record);

    int insertSelective(ShopDO record);

    ShopDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ShopDO record);

    int updateByPrimaryKey(ShopDO record);
}