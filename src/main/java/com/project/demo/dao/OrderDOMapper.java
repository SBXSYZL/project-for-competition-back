package com.project.demo.dao;

import com.project.demo.DO.OrderDO;

public interface OrderDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(OrderDO record);

    int insertSelective(OrderDO record);

    OrderDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(OrderDO record);

    int updateByPrimaryKey(OrderDO record);

    int createOrder(OrderDO orderDO);
}