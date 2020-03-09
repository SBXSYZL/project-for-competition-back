package com.project.demo.dao;

import com.project.demo.DO.CommentDO;
import com.project.demo.VO.CommentVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommentDOMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CommentDO record);

    int insertSelective(CommentDO record);

    CommentDO selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CommentDO record);

    int updateByPrimaryKeyWithBLOBs(CommentDO record);

    int updateByPrimaryKey(CommentDO record);

    List<CommentVO> selectByProductId(@Param("productId") Integer productId);

    void writeComment(@Param("userId") Integer userId, @Param("comment") String comment, @Param("productId") Integer productId);

}