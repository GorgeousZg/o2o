package com.imooc.o2o.service;

import java.util.List;

import com.imooc.o2o.dto.ProductCategoryException;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {
	/**
	 * 查询指定某个店铺下的所有商品类别信息
	 * @param long shopId
	 * @return List<ProductCategory>
	 */
	List<ProductCategory> getProductCategoryList(long shopId);
	/**
	 * 
	 * @param productCategories
	 * @return
	 * @throws ProductCategoryException
	 */
	ProductCategoryException batchAddProductCategory(List<ProductCategory> productCategoryList) throws ProductCategoryOperationException;
	/**
	 * 将此类别下的商品类别id置为空，再删除掉该商品类别
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 * @throws RuntimeException
	 */
	ProductCategoryException deleteProductCategory(long productCategoryId,
			long shopId) throws RuntimeException;
}
