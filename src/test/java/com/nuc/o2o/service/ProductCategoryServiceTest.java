package com.nuc.o2o.service;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.o2o.BaseTest;
import com.nuc.o2o.entity.ProductCategory;
import com.nuc.o2o.service.serviceImpl.ProductCategoryServiceImpl;

public class ProductCategoryServiceTest extends BaseTest{
	@Autowired
	private ProductCategoryServiceImpl serviceImpl;
	
	@Test
	public void getProductCatrgoryListTest() {
		List<ProductCategory> list = serviceImpl.getProductCatrgoryList(28l);
		System.out.println(list);
	}
}
