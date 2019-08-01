package com.imooc.o2o.service;

import java.io.InputStream;
import java.util.List;

import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.exceptions.ProductCategoryOperationException;

public interface ProductService {
	/**
	 * 查询商品列表，可输入的条件有:商品名(模糊)，商品状态，店铺Id，商品类别
	 * @param productCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize);
	/**
	 * 通过商品Id查询唯一的商品信息
	 * @param productId
	 * @return
	 */
	Product getProductById(long productId);
	/**
	 * 添加商品信息以及图片处理
	 * @return
	 * @throws ProductCategoryOperationException
	 */
	ProductExecution addProduct(Product product,ImageHolder thumbnail,List<ImageHolder> productImgNameList) throws ProductCategoryOperationException;

	/*ProductExecution modifyProduct(Product product, CommonsMultipartFile thumbnail,
			List<CommonsMultipartFile> productImgs) throws RuntimeException;*/
}
