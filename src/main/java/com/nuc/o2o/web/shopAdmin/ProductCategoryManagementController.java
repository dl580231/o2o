package com.nuc.o2o.web.shopAdmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuc.o2o.entity.ProductCategory;
import com.nuc.o2o.service.ProductCategoryService;

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {
	@Autowired
	private ProductCategoryService productCategoryService;
	@ResponseBody
	@RequestMapping(value = "getproductcategorylist", method = RequestMethod.GET)
	public Map<String, Object> getProductCategoryList(@Param("shopId") Long shopId) {
		Map<String, Object> model = new HashMap<>();
		List<ProductCategory> productCategoryList = null;
		if(shopId==null) {
			model.put("success", false);
			model.put("errorMsg", "shopId为空");
			return model;
		}
		try {
			productCategoryList = productCategoryService.getProductCatrgoryList(shopId);
			model.put("success", true);
			model.put("productCategoryList", productCategoryList);
		} catch (Exception e) {
			model.put("success", false);
			model.put("errorMsg", "产品类别查询失败" + e.getMessage().toString());
		}
		return model;
	}
}
