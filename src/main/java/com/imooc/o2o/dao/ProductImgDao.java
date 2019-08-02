package com.imooc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.imooc.o2o.entity.ProductImg;

public interface ProductImgDao {
	/**
	 * 删除指定商品下所有详情图
	 * 
	 * @param productId
	 * @return
	 */
	int deleteProductImgByProductId(@Param("productId") long productId);
	
	List<ProductImg> queryProductImgList(long productId);
	/**
	 * 批量添加商品详情图片
	 * @param productImgList
	 * @return
	 */
	int batchInsertProductImg(List<ProductImg> productImgList);
}
