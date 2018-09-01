package com.nuc.o2o.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.o2o.dao.ProductCategoryDao;
import com.nuc.o2o.entity.ProductCategory;
import com.nuc.o2o.service.ProductCategoryService;

public class ProductCategoryServiceImpl implements ProductCategoryService {
	@Autowired
	private ProductCategoryDao dao;
	
	@Override
	public List<ProductCategory> getProductCatrgoryList(Long shopId) {
		return dao.queryProductCategoryList(shopId);
	}
	
}
