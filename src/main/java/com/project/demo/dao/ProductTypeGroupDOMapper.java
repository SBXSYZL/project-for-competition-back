package com.project.demo.dao;

import com.project.demo.DO.ProductTypeGroupDO;

import java.util.List;

public interface ProductTypeGroupDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductTypeGroupDO record);

    int insertSelective(ProductTypeGroupDO record);

    ProductTypeGroupDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductTypeGroupDO record);

    int updateByPrimaryKey(ProductTypeGroupDO record);

    List<ProductTypeGroupDO> selectAll();

}