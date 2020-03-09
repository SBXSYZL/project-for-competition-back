package com.project.demo.dao;

import com.project.demo.DO.ProductTypeDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductTypeDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductTypeDO record);

    int insertSelective(ProductTypeDO record);

    ProductTypeDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductTypeDO record);

    int updateByPrimaryKey(ProductTypeDO record);

    List<ProductTypeDO> selectByGroupId(@Param("GroupId") Integer GroupId);

}