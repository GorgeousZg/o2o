package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.HeadLine;

/**
 * 头条轮播
 * 
 * @author Gorgeous
 *
 */
public interface HeadLineDao {
	List<HeadLine> queryHeadLine(@Param("headLineCondition") HeadLine headLineCondition);
}
