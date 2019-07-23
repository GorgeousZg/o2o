package com.imooc.o2o.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest{
 @Autowired
 private ShopDao shopDao;
 @Test
 public void testInsertShop(){
	 Shop shop=new Shop();
	 PersonInfo owner=new PersonInfo();
	 Area area=new Area();
	 ShopCategory shopCategory=new ShopCategory();
	 owner.setUserid(1l);
	 area.setAreaId(2);
	 shopCategory.setShopCategoryId(1l);
	 shop.setOwner(owner);
	 shop.setArea(area);
	 System.out.println("shopCategory-->"+shopCategory.toString());
	 shop.setShopCategory(shopCategory);
	 shop.setShopName("≤‚ ‘µƒµÍ∆Ã");
	 shop.setShopDesc("test");
	 shop.setShopAddr("test");
	 shop.setPhone("test");
	 shop.setShopImg("test");
	 shop.setCreateTime(new Date());
	 shop.setEnableStatus(1);
	 shop.setAdvice("…Û∫À÷–");
	 int effectedNum=shopDao.insertShop(shop);
	 assertEquals(1,effectedNum);
 }
}
