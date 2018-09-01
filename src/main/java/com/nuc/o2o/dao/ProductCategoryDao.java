package com.nuc.o2o.dao;

import java.util.List;

import com.nuc.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	public List<ProductCategory> queryProductCategoryList(Long shopId);
}
