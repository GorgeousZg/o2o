package com.imooc.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;
import com.imooc.o2o.exceptions.ShopOperationException;

public class ShopServiceTest extends BaseTest{
	@Autowired
	private ShopService shopService;
	
	@Test
	public void testGetShopList(){
		 Shop shopCondition=new Shop();
		 ShopCategory sc=new ShopCategory();
		 sc.setShopCategoryId(3L);
		 shopCondition.setShopCategory(sc);
		 ShopExecution se=shopService.getShopList(shopCondition, 1, 2);
		 System.out.println(se);
		 System.out.println("店铺列表数为:"+se.getShopList().size());
		 System.out.println("店铺总数为:"+se.getCount());
	}
	
	@Test
	@Ignore
	public void testModifyShop() throws ShopOperationException,FileNotFoundException{
		Shop shop=new Shop();
		shop.setShopId(45L);
		shop.setShopName("修改后的店铺名称");
		File shopImg=new File("C:/Users/Gorgeous/Desktop/win.jpg");
		InputStream is=new FileInputStream(shopImg);
		ShopExecution shopExecution=shopService.modifyShop(shop, is, "dabai.jpg");
		System.out.println("新的图片id为:"+shopExecution.getShop().getShopImg());
	}
	
	@Test
	@Ignore
	public void testAddShop() throws FileNotFoundException{
		Shop shop=new Shop();
		 PersonInfo owner=new PersonInfo();
		 Area area=new Area();
		 ShopCategory shopCategory=new ShopCategory();
		 owner.setUserid(1l);
		 area.setAreaId(2);
		 shopCategory.setShopCategoryId(1l);
		 shop.setOwner(owner);
		 shop.setArea(area);
		 shop.setShopCategory(shopCategory);
		 shop.setShopName("测试的店铺3");
		 shop.setShopDesc("test3");
		 shop.setShopAddr("test3");
		 shop.setPhone("test3");
		 shop.setCreateTime(new Date());
		 shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		 shop.setAdvice("审核中");
		 File showImg=new File("C:/Users/Gorgeous/Desktop/win.jpg");
		 InputStream is=new FileInputStream(showImg);
		 ShopExecution se=shopService.addShop(shop, is,showImg.getName());
		 assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
	}

}
