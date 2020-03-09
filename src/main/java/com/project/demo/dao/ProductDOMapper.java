package com.project.demo.dao;

import com.project.demo.DO.ProductDO;
import com.project.demo.VO.ProDuctDetailVO;
import com.project.demo.VO.ProductFirstVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductDOMapper {

    List<ProductFirstVO> getProductList(@Param("shopId") Integer shopId, @Param("typeId") Integer typeId, @Param("startIndex") Integer startIndex, @Param("pageSize") Integer pageSize);

    ProDuctDetailVO getProductDetail(@Param("productId") Integer productId);

    int buyProduct(@Param("productId") Integer productId, @Param("buyCnt") Integer buyCnt);

    ProductFirstVO getProduct(Integer productId);

    List<ProductFirstVO> searchProduct(@Param("searchKey") String searchKey);

}