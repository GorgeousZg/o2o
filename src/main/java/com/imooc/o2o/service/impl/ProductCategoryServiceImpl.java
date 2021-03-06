package com.imooc.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.ProductCategoryDao;
import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dto.ProductCategoryException;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.enums.ProductCategoryStateEnum;
import com.imooc.o2o.exceptions.ProductCategoryOperationException;
import com.imooc.o2o.service.ProductCategoryService;
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryDao productCategoryDao;
	@Autowired
	private ProductDao productDao;
	@Override
	public List<ProductCategory> getProductCategoryList(long shopId) {
		
		return productCategoryDao.queryProductCategoryList(shopId);
	}

	@Override
	@Transactional
	public ProductCategoryException batchAddProductCategory(List<ProductCategory> productCategoryList)
			throws ProductCategoryOperationException {
		if (productCategoryList != null && productCategoryList.size() > 0) {
			try {
				int effectedNum = productCategoryDao
						.batchInsertProductCategory(productCategoryList);
				if (effectedNum <= 0) {
					throw new RuntimeException("店铺类别失败");
				} else {

					return new ProductCategoryException(
							ProductCategoryStateEnum.SUCCESS);
				}

			} catch (Exception e) {
				throw new RuntimeException("batchAddProductCategory error: "
						+ e.getMessage());
			}
		} else {
			return new ProductCategoryException(
					ProductCategoryStateEnum.INNER_ERROR);
		}
	}

	@Override
	@Transactional
	public ProductCategoryException deleteProductCategory(long productCategoryId, long shopId) throws ProductCategoryOperationException {
		try{
			int effectedNum=productDao.updateProductCategoryToNull(productCategoryId);
			if(effectedNum<0){
				throw new ProductCategoryOperationException("商品类别更新失败");
			}
		}catch (Exception e) {
			throw new ProductCategoryOperationException("updateProductCategoryToNull error:"+e.getMessage());
		}
		try{
			int effectedNum=productCategoryDao.deleteProductCategory(productCategoryId, shopId);
			if(effectedNum<0){
				throw new ProductCategoryOperationException("商品类别删除失败");
			}else{
				return new ProductCategoryException(ProductCategoryStateEnum.SUCCESS);
			}
		}catch (Exception e) {
			throw new ProductCategoryOperationException("deleteProductCategory error:"+e.getMessage());
		}
	}

}
