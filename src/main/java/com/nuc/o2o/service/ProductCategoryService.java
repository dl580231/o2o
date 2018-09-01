package com.nuc.o2o.service;

import java.util.List;

import com.nuc.o2o.entity.ProductCategory;

public interface ProductCategoryService {	
	/**
	 * 根据传来的shopId查询出店铺拥有的商品类别
	 * @param shopId
	 * @return
	 */
	public List<ProductCategory> getProductCatrgoryList(Long shopId);
}
