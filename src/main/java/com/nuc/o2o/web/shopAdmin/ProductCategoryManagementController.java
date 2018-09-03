package com.nuc.o2o.web.shopAdmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.nuc.o2o.dto.ProductCategoryExecution;
import com.nuc.o2o.entity.ProductCategory;
import com.nuc.o2o.entity.Shop;
import com.nuc.o2o.enums.ProductCategoryStateEnum;
import com.nuc.o2o.exceptions.ProductCategoryOperationException;
import com.nuc.o2o.service.ProductCategoryService;
import com.nuc.o2o.utils.HttpServletRequestUtils;

@Controller
@RequestMapping("/shopadmin")
public class ProductCategoryManagementController {
	@Autowired
	private ProductCategoryService productCategoryService;

	public Map<String, Object> removeProductCategory(@RequestParam("productCategoryId") Long productCategoryId,
			HttpServletRequest request) {
		HashMap<String, Object> model = new HashMap<>();
		if (productCategoryId == null) {
			model.put("success", false);
			model.put("errorMsg", "productCategoryId为空");
			return model;
		} else {
			Shop shop = (Shop) HttpServletRequestUtils.getSessionAttr(request, "currentShop");
			try {
				ProductCategoryExecution execution = productCategoryService.removeProductCategory(productCategoryId,
						shop.getShopId());
				if (execution.getProductCategoryStateEnum().getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					model.put("success", true);
				} else {
					model.put("success", false);
					model.put("errorMsg", "删除失败");
				}
			} catch (ProductCategoryOperationException e) {
				model.put("success", false);
				model.put("errorMsg", e.toString());
			}
			return model;
		}
	}

	/**
	 * 批量增加商品类别
	 * 
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/addproductcategories", method = RequestMethod.POST)
	public Map<String, Object> addProductCategories(@RequestBody List<ProductCategory> productCategories,
			HttpServletRequest request) {
		HashMap<String, Object> model = new HashMap<>();
		Shop shop = (Shop) HttpServletRequestUtils.getSessionAttr(request, "currentShop");
		if (productCategories == null || productCategories.size() == 0) {
			model.put("success", false);
			model.put("errorMsg", "商品类别为空");
			return model;
		} else {
			for (ProductCategory productCategory : productCategories) {
				productCategory.setShopId(shop.getShopId());
			}
			try {
				ProductCategoryExecution execution = productCategoryService.batchAddProductCategory(productCategories);
				if (execution.getProductCategoryStateEnum().getState() == ProductCategoryStateEnum.SUCCESS.getState()) {
					model.put("success", true);
				} else {
					model.put("success", false);
					model.put("errorMsg", "批量存入失败");
				}
			} catch (ProductCategoryOperationException e) {
				model.put("success", false);
				model.put("errorMsg", e.toString());
			}
			return model;
		}
	}

	/**
	 * 根据shopId获取商品类别列表
	 * 
	 * @param shopId
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "getproductcategorylist", method = RequestMethod.GET)
	public Map<String, Object> getProductCategoryList(HttpServletRequest request) {
		Map<String, Object> model = new HashMap<>();
		Shop shop = (Shop) HttpServletRequestUtils.getSessionAttr(request, "currentShop");
		List<ProductCategory> productCategoryList = null;
		if (shop == null) {
			model.put("success", false);
			model.put("errorMsg", "shopId为空");
			return model;
		}
		try {
			productCategoryList = productCategoryService.getProductCatrgoryList(shop.getShopId());
			model.put("success", true);
			model.put("productCategoryList", productCategoryList);
		} catch (Exception e) {
			model.put("success", false);
			model.put("errorMsg", "产品类别查询失败" + e.getMessage().toString());
		}
		return model;
	}
}
