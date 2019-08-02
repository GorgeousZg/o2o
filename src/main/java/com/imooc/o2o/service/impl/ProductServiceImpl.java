package com.imooc.o2o.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imooc.o2o.dao.ProductDao;
import com.imooc.o2o.dao.ProductImgDao;
import com.imooc.o2o.dto.ImageHolder;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductImg;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.exceptions.ProductCategoryOperationException;
import com.imooc.o2o.exceptions.ProductOperationException;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.FileUtil;
import com.imooc.o2o.util.ImageUtil;
import com.imooc.o2o.util.PageCalculator;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;
	@Autowired
	private ProductImgDao productImgDao;

	@Override
	@Transactional
	public ProductExecution addProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
			throws ProductCategoryOperationException {
		// 1.处理缩略图，获取缩略图相对路径并赋值给product
		// 2.往tb_product写入商品信息，获取product
		// 3.结合productId批量处理商品详情图
		// 4.将商品详情图列表批量插入tb_prodduct_img中
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			product.setCreateTime(new Date());
			product.setLastEditTime(new Date());
			// 默认为上架的状态
			product.setEnableStatus(1);
			// 若缩略图不为空则添加
			if (thumbnail != null) {
				addThumbnail(product, thumbnail);
			}
			try {
				// 创建商品信息
				int effectedNum = productDao.insertProduct(product);
				if (effectedNum <= 0) {
					throw new RuntimeException("创建商品失败");
				}
			} catch (Exception e) {
				throw new RuntimeException("创建商品失败:" + e.toString());
			}
			// 若商品详情图不为空则添加
			if (productImgHolderList != null && productImgHolderList.size() > 0) {
				addProductImgList(product, productImgHolderList);
			}
			return new ProductExecution(ProductStateEnum.SUCCESS, product);
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}

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
		return productDao.queryProductById(productId);
	}
	
	private void addProductImgList(Product product, List<ImageHolder> productImgHolderList) {
		String dest = FileUtil.getShopImagePath(product.getShop().getShopId());
		List<ProductImg> productImgList = new ArrayList<ProductImg>();
		for (ImageHolder productImageHolder : productImgHolderList) {
			System.out.println(productImageHolder.getImageName());
			String imgAddr = ImageUtil.generateNormalImgs(productImageHolder, dest);
			ProductImg productImg = new ProductImg();
			productImg.setImgAddr(imgAddr);
			productImg.setProductId(product.getProductId());
			productImg.setCreateTime(new Date());
			productImgList.add(productImg);
		}
		//如果确实是有图片需要添加的，就执行批量添加操作
		if (productImgList.size() > 0) {
			try {
				int effectedNum = productImgDao.batchInsertProductImg(productImgList);
				if (effectedNum <= 0) {
					throw new RuntimeException("创建商品详情图片失败");
				}
			} catch (Exception e) {
				throw new RuntimeException("创建商品详情图片失败:" + e.toString());
			}
		}
	}

	private void addThumbnail(Product product, ImageHolder thumbnail) {
		String dest = FileUtil.getShopImagePath(product.getShop().getShopId());
		System.out.println(product.toString());
		System.out.println(thumbnail.getImageName());
		System.out.println(thumbnail.getImage());
		String thumbnailAddr = ImageUtil.generateThumbnail(thumbnail, dest);
		product.setImgAddr(thumbnailAddr);
	}
	
	@Override
	@Transactional
	public ProductExecution modifyProduct(Product product, ImageHolder thumbnail, List<ImageHolder> productImgHolderList)
			throws ProductOperationException {
		//1.若缩略图有值，则处理缩略图
		//若原先存在缩略图则先删除再添加新图，之后获取缩略图相对路径并赋值给peoduct
		//2.若商品详情列表参数有值，对商品详情列表进行同样的操作
		//3.将tb_product_img下面的该商品原先的商品详情图记录全部清除
		//4.更新tb_product的信息
		
		//空值判断
		if (product != null && product.getShop() != null && product.getShop().getShopId() != null) {
			//给商品设置上默认属性
			product.setLastEditTime(new Date());
			//若缩略图不为空且有原缩略图不为空则删除原有缩略图并添加
			if (thumbnail != null) {
				//先获取一遍原有信息，因为原来的信息里有原图片地址
				Product tempProduct = productDao.queryProductByProductId(product.getProductId());
				if (tempProduct.getImgAddr() != null) {
					FileUtil.deleteFile(tempProduct.getImgAddr());
				}
				addThumbnail(product, thumbnail);
			}
			//如果有新存入的商品详情图，则将原先的删除，并添加新的图片
			if (productImgHolderList != null && productImgHolderList.size() > 0) {
				deleteProductImgs(product.getProductId());
				addProductImgList(product, productImgHolderList);
			}
			try {
				//更新商品信息
				int effectedNum = productDao.updateProduct(product);
				if (effectedNum <= 0) {
					throw new RuntimeException("更新商品信息失败");
				}
				return new ProductExecution(ProductStateEnum.SUCCESS, product);
			} catch (Exception e) {
				throw new RuntimeException("更新商品信息失败:" + e.toString());
			}
		} else {
			return new ProductExecution(ProductStateEnum.EMPTY);
		}
	}
	
	/**
	 * 删除某个商品下的所有详情图
	 * @param productId
	 */
	private void deleteProductImgs(long productId) {
		//根据product获取原来的图片
		List<ProductImg> productImgList = productImgDao.queryProductImgList(productId);
		//干掉原来的图片
		for (ProductImg productImg : productImgList) {
//			FileUtil.deleteFile(productImg.getImgAddr());
			ImageUtil.deleteFileOrPath(productImg.getImgAddr());
		}
		//删除数据库里原有图片的信息
		productImgDao.deleteProductImgByProductId(productId);
	}

}
