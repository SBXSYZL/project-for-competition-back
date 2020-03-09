package com.project.demo.dao;

import com.project.demo.DO.ChildOrderDO;
import com.project.demo.VO.OrderFirstVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChildOrderDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ChildOrderDO record);

    int insertSelective(ChildOrderDO record);

    ChildOrderDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ChildOrderDO record);

    int updateByPrimaryKey(ChildOrderDO record);

    void createOrder(ChildOrderDO childOrderDO);

    List<OrderFirstVO> getMyOrders(@Param("userId") Integer userId);
}