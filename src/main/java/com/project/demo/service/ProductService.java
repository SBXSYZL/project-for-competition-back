package com.project.demo.service;

import com.project.demo.DO.ProductImageDO;
import com.project.demo.VO.ProDuctDetailVO;
import com.project.demo.VO.ProductFirstVO;
import com.project.demo.VO.ProductTypeVO;
import com.project.demo.error.BusinessException;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: YZL
 * @time: 2020/3/2 14:12
 */
@Service
public interface ProductService {
    List getProductList(Integer shopId, Integer typeId, Integer pageNo, Integer pageSize) throws BusinessException;

    List<ProductTypeVO> getProductTypes() throws BusinessException;

    List<ProductImageDO> randomSelectImg() throws BusinessException;

    ProDuctDetailVO getProductDetail(Integer productId) throws BusinessException;

    void buyProduct(Integer productId, Integer buyCnt) throws BusinessException;

    Map getCommentByProductId(Integer productId, Integer pageNo, Integer pageSize) throws BusinessException;

    Map getMyOrders(Integer pageNo, Integer pageSize) throws BusinessException;

    ProductFirstVO getProduct(Integer productId) throws BusinessException;

    Map searchProduct(String searchKey, Integer pageNo, Integer pageSize) throws BusinessException;

    void writeComment(String comment, Integer productId) throws BusinessException;
}
