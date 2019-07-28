package com.imooc.o2o.service;



import java.io.File;
import java.io.InputStream;

import com.imooc.o2o.dto.ShopExecution;
import com.imooc.o2o.entity.Shop;

public interface ShopService {
	ShopExecution addShop(Shop shop,InputStream showImgInputSteam,String fileName);;
}
