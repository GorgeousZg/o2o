package com.imooc.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
/**
 * 主要用来解析路由并转发到相应的html中
 * @author Gorgeous
 *
 */
@Controller
@RequestMapping(value = "shopadmin", method = RequestMethod.GET)
public class ShopAdminController {
    @RequestMapping(value="/shopoperation")
	public String shopOperation() {
    	//转发至店铺注册/编辑页面
		return "shop/shopoperation";
	}
    
    @RequestMapping(value="/shoplist")
   	public String shopList() {
    	//转发至店铺列表页面
   		return "shop/shoplist";
   	}
    @RequestMapping(value="/shopmanagent")
   	public String shopManagement() {
    	//转发至店铺管理页面
   		return "shop/shopmanagent";
   	}
    @RequestMapping(value="/productcategorymanagement",method=RequestMethod.GET)
    private String productCategoryManage(){
    	//转发至商品添加/编辑页面
    	return "shop/productcategorymanagement";
    }
    @RequestMapping(value = "/productmanagement", method = RequestMethod.GET)
	private String productManage() {
    	//转发至商品管理页面
		return "shop/productmanagement";
	}
    
    @RequestMapping(value="/productoperation")
    public String productOperation(){
    	//转发至商品添加/编辑页面
    	return "shop/productoperation";
    }
}
