package com.project.demo.service.Impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.project.demo.DO.*;
import com.project.demo.VO.*;
import com.project.demo.dao.*;
import com.project.demo.error.BusinessException;
import com.project.demo.error.EmBusinessErr;
import com.project.demo.service.ProductService;
import com.project.demo.utils.MySessionUtil;
import com.project.demo.utils.PageUtil;
import org.mockito.internal.matchers.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: YZL
 * @time: 2020/3/2 14:19
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductDOMapper productDOMapper;
    private final ProductTypeDOMapper productTypeDOMapper;
    private final ProductTypeGroupDOMapper productTypeGroupDOMapper;
    private final ProductImageDOMapper productImageDOMapper;
    private final CommentDOMapper commentDOMapper;
    private final OrderDOMapper orderDOMapper;
    private final ChildOrderDOMapper childOrderDOMapper;

    public ProductServiceImpl(ProductDOMapper productDOMapper, ProductTypeDOMapper productTypeDOMapper, ProductTypeGroupDOMapper productTypeGroupDOMapper, ProductImageDOMapper productImageDOMapper, CommentDOMapper commentDOMapper, OrderDOMapper orderDOMapper, ChildOrderDOMapper childOrderDOMapper) {
        this.productDOMapper = productDOMapper;
        this.productTypeDOMapper = productTypeDOMapper;
        this.productTypeGroupDOMapper = productTypeGroupDOMapper;
        this.productImageDOMapper = productImageDOMapper;
        this.commentDOMapper = commentDOMapper;
        this.orderDOMapper = orderDOMapper;
        this.childOrderDOMapper = childOrderDOMapper;
    }

    @Override
    public List getProductList(Integer shopId, Integer typeId, Integer pageNo, Integer pageSize) throws BusinessException {
        try {
            return productDOMapper.getProductList(shopId, typeId, (pageNo - 1) * pageSize, pageSize);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.GET_PRODUCT_LIST_ERROR);
        }
    }

    @Override
    public List<ProductTypeVO> getProductTypes() throws BusinessException {
        try {
            List<ProductTypeVO> voList = new ArrayList<>();
            List<ProductTypeGroupDO> list = productTypeGroupDOMapper.selectAll();
            for (ProductTypeGroupDO groupDO : list) {
                List<ProductTypeDO> doList = productTypeDOMapper.selectByGroupId(groupDO.getId());
                ProductTypeVO productTypeVO = new ProductTypeVO();
                productTypeVO.setId(groupDO.getId());
                productTypeVO.setTitle(groupDO.getName());
                productTypeVO.setChild(doList);
                voList.add(productTypeVO);
            }
            return voList;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.GET_PRODUCT_TYPE_ERROR);
        }
    }

    @Override
    public List<ProductImageDO> randomSelectImg() throws BusinessException {
        try {
            return productImageDOMapper.randomSelectImg();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.GET_PRODUCT_IMAGE_ERROR);
        }
    }

    @Override
    public ProDuctDetailVO getProductDetail(Integer productId) throws BusinessException {
        try {
            ProDuctDetailVO productDetail = productDOMapper.getProductDetail(productId);
            List<ProductImageDO> imageDOS = productImageDOMapper.selectByProductId(productId);
            Page page = PageHelper.startPage(1, 10);
            List<CommentVO> commentDOS = commentDOMapper.selectByProductId(productId);
            Map<String, Object> map = PageUtil.getListWithPageInfo(commentDOS, page);
            productDetail.setComments(map);
            productDetail.setImages(imageDOS);
            return productDetail;
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.GET_PRODUCT_DETAIL_ERROR);
        }
    }

    @Override
    @Transactional
    public void buyProduct(Integer productId, Integer buyCnt) throws BusinessException {
        try {
            int i = productDOMapper.buyProduct(productId, buyCnt);
            if (i == 1) {
                ProDuctDetailVO productDetail = getProductDetail(productId);
                OrderDO orderDO = new OrderDO();
                orderDO.setUserId(((UserDO) MySessionUtil.getSession().getAttribute(MySessionUtil.USER_ID)).getId());
                BigDecimal total = productDetail.getPrice().multiply(new BigDecimal(buyCnt));
                orderDO.setTotal(total);
                orderDOMapper.createOrder(orderDO);
                int orderId = orderDO.getId();
                ChildOrderDO childOrderDO = new ChildOrderDO();
                childOrderDO.setOrderId(orderId);
                childOrderDO.setProductId(productDetail.getId());
                childOrderDO.setQuantity(buyCnt);
                childOrderDOMapper.createOrder(childOrderDO);
            } else {
                throw new BusinessException(EmBusinessErr.STOCK_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof BusinessException) {
                throw e;
            }
            throw new BusinessException(EmBusinessErr.BUY_PRODUCT_ERROR);
        }
    }

    @Override
    public Map getCommentByProductId(Integer productId, Integer pageNo, Integer pageSize) throws BusinessException {
        try {
            Page page = PageHelper.startPage(pageNo, pageSize);
            List<CommentVO> commentVOS = commentDOMapper.selectByProductId(productId);
            return PageUtil.getListWithPageInfo(commentVOS, page);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.GET_COMMENT_OF_PRODUCT_ERROR);
        }
    }

    @Override
    public Map getMyOrders(Integer pageNo, Integer pageSize) throws BusinessException {
        try {
            Page page = PageHelper.startPage(pageNo, pageSize);
            Integer userId = ((UserDO) MySessionUtil.getSession().getAttribute(MySessionUtil.USER_ID)).getId();
            List<OrderFirstVO> list = childOrderDOMapper.getMyOrders(userId);
            return PageUtil.getListWithPageInfo(list, page);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.GET_ORDER_ERROR);
        }
    }

    @Override
    public ProductFirstVO getProduct(Integer productId) throws BusinessException {
        try {
            return productDOMapper.getProduct(productId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.GET_PRODUCT_LIST_ERROR);
        }
    }

    @Override
    public Map searchProduct(String searchKey, Integer pageNo, Integer pageSize) throws BusinessException {
        try {
            Page page = PageHelper.startPage(pageNo, pageSize);
            List<ProductFirstVO> list = productDOMapper.searchProduct(searchKey);
            return PageUtil.getListWithPageInfo(list, page);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.SEARCH_PRODUCT_ERROR);
        }
    }

    @Override
    public void writeComment(String comment, Integer productId) throws BusinessException {
        try {
            Integer userId = ((UserDO) MySessionUtil.getSession().getAttribute(MySessionUtil.USER_ID)).getId();
            commentDOMapper.writeComment(userId, comment, productId);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException(EmBusinessErr.WRITE_COMMENT_ERROR);
        }
    }
}
