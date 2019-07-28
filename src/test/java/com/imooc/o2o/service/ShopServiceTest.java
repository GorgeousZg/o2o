package com.imooc.o2o.service;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.imooc.o2o.BaseTest;
import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Area;
import com.imooc.o2o.entity.PersonInfo;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.entity.ShopCategory;
import com.imooc.o2o.enums.ShopStateEnum;

public class ShopServiceTest extends BaseTest{
	@Autowired
	private ShopService shopService;
	@Test
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
		 shop.setShopName("≤‚ ‘µƒµÍ∆Ã3");
		 shop.setShopDesc("test3");
		 shop.setShopAddr("test3");
		 shop.setPhone("test3");
		 shop.setCreateTime(new Date());
		 shop.setEnableStatus(ShopStateEnum.CHECK.getState());
		 shop.setAdvice("…Û∫À÷–");
		 File showImg=new File("C:/Users/Gorgeous/Desktop/win.jpg");
		 InputStream is=new FileInputStream(showImg);
		 ShopExecution se=shopService.addShop(shop, is,showImg.getName());
		 assertEquals(ShopStateEnum.CHECK.getState(),se.getState());
	}

}
