package com.nuc.o2o.web.shopAdmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value="/shop/", method=RequestMethod.GET)
public class ShopAdminController {	
	
	@RequestMapping("/productcategorymanagement") 
	public String productCategoryManagement() {
		return "/shop/productcategorymanagement";
	}
	
	@RequestMapping("/shopoperation")
	public String shopOperation() {
		return "/shop/shopoperation";
	}
	
	@RequestMapping("/shoplist")
	public String shopList() {
		return "/shop/shoplist";
	}
	
	@RequestMapping("shopmanagement")
	public String shopManagement() {
		return "/shop/shopmanagement";
	}
}
