package com.project.demo.dao;

import com.project.demo.DO.ProductImageDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductImageDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ProductImageDO record);

    int insertSelective(ProductImageDO record);

    ProductImageDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ProductImageDO record);

    int updateByPrimaryKey(ProductImageDO record);

    List<ProductImageDO> randomSelectImg();

    List<ProductImageDO> selectByProductId(@Param("productId") Integer productId);

}