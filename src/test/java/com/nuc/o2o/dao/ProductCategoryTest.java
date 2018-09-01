package com.nuc.o2o.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.nuc.o2o.BaseTest;
import com.nuc.o2o.entity.ProductCategory;

public class ProductCategoryTest extends BaseTest{
	@Autowired
	ProductCategoryDao dao;
	
	@Test
	public void queryProductCategoryListTest() {
		List<ProductCategory> list = dao.queryProductCategoryList(28L);
		System.out.println(list.size());
	}
}
