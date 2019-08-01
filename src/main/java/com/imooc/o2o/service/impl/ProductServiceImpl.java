package com.imooc.o2o.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dao.ProductImgDao;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.PageCalculator;


@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;
	
	@Override
	public ProductExecution getProductList(Product productCondition, int pageIndex, int pageSize) {
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
		List<Product> productList = productDao.queryProductList(productCondition, rowIndex, pageSize);
		int count = productDao.queryProductCount(productCondition);
		ProductExecution pe = new ProductExecution();
		pe.setProductList(productList);
		pe.setCount(count);
		return pe;
	}

	@Override
	public Product getProductById(long productId) {
		return productDao.queryProductByProductId(productId);
	}

	
}
