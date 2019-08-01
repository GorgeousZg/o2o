package com.imooc.o2o.web.shopadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imooc.o2o.dto.ProductExecution;
import com.imooc.o2o.entity.Product;
import com.imooc.o2o.entity.ProductCategory;
import com.imooc.o2o.entity.Shop;
import com.imooc.o2o.enums.ProductStateEnum;
import com.imooc.o2o.service.ProductCategoryService;
import com.imooc.o2o.service.ProductService;
import com.imooc.o2o.util.CodeUtil;
import com.imooc.o2o.util.HttpServletRequestUtil;

@Controller
@RequestMapping("/shopadmin")
public class ProductManagementController {
	@Autowired
	private ProductService productService;
	@Autowired
	private ProductCategoryService productCategoryService;

	private static final int IMAGEMAXCOUNT = 6;
	
	@RequestMapping(value = "/getproductlistbyshop", method = RequestMethod.GET)
	@ResponseBody
	private Map<String, Object> listProductsByShop(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//获取前台传过来的页码
		int pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		//获取前台传过来的每页要求返回的商品数上限
		int pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
		//从当前session中获取店铺信息，主要是获取shopId
		Shop currentShop = (Shop) request.getSession().getAttribute(
				"currentShop");
		//空值判断
		if ((pageIndex > -1) && (pageSize > -1) && (currentShop != null)
				&& (currentShop.getShopId() != null)) {
			//获取传入的需要检索的条件，包括是否需要从某个商品类别以及模糊查询商品名去筛选某个店铺下的商品列表
			//筛选的条件可以进行排列组合
			long productCategoryId = HttpServletRequestUtil.getLong(request,
					"productCategoryId");
			String productName = HttpServletRequestUtil.getString(request,
					"productName");
			Product productCondition = compactProductCondition(
					currentShop.getShopId(), productCategoryId, productName);
			//传入查询条件以及分页信息进行查询，返回相应商品列表
			ProductExecution pe = productService.getProductList(
					productCondition, pageIndex, pageSize);
			modelMap.put("productList", pe.getProductList());
			modelMap.put("count", pe.getCount());
			modelMap.put("success", true);
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "empty pageSize or pageIndex or shopId");
		}
		return modelMap;
	}
	private Product compactProductCondition(long shopId,
			long productCategoryId, String productName) {
		Product productCondition = new Product();
		Shop shop = new Shop();
		shop.setShopId(shopId);
		productCondition.setShop(shop);
		//若有指定类别的要求则添加进去
		if (productCategoryId != -1L) {
			ProductCategory productCategory = new ProductCategory();
			productCategory.setProductCategoryId(productCategoryId);
			productCondition.setProductCategory(productCategory);
		}
		//若有商品名模糊查询的要求则添加进去
		if (productName != null) {
			productCondition.setProductName(productName);
		}
		return productCondition;
	}
}
