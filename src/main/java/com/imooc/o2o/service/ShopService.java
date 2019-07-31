package com.imooc.o2o.service;



import java.io.InputStream;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;

public interface ShopService {
	/**
	 * 根据shopCondition分页返回相应店铺列表
	 * @param shopCondition
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ShopExecution getShopList(Shop shopCondition,int pageIndex,int pageSize);
	/**
	 * 注册店铺信息，包括图片处理
	 * @param shop 前端传入店铺信息
	 * @param showImgInputSteam
	 * @param fileName
	 * @return
	 */
	ShopExecution addShop(Shop shop,InputStream showImgInputSteam,String fileName);
	/**
	 * 更新店铺信息，包括对图片的处理
	 * 
	 * @param areaId
	 * @param shopAddr
	 * @param phone
	 * @param shopImg
	 * @param shopDesc
	 * @return
	 * @throws RuntimeException
	 */
	ShopExecution modifyShop(Shop shop, InputStream shopImgInputStream,String fileName) throws RuntimeException;
	/**
	 * 查询指定店铺信息
	 * 
	 * @param long
	 *            shopId
	 * @return Shop shop
	 */
	Shop getByShopId(long shopId);
}
