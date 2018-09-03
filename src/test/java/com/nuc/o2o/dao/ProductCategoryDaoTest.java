package com.nuc.o2o.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.o2o.BaseTest;
import com.nuc.o2o.entity.ProductCategory;

public class ProductCategoryDaoTest extends BaseTest {
	@Autowired
	ProductCategoryDao dao;

	@Test
	@Ignore
	public void queryProductCategoryListTest() {
		List<ProductCategory> list = dao.queryProductCategoryList(28L);
		System.out.println(list);
	}

	@Test
	@Ignore
	public void batchInsertProductCategoryTest() {
		ProductCategory productCategory01 = new ProductCategory();
		productCategory01.setCreateTime(new Date());
		productCategory01.setPriority(3);
		productCategory01.setProductCategoryName("商品类别测试01");
		productCategory01.setShopId(29l);
		ProductCategory productCategory02 = new ProductCategory();
		productCategory02.setCreateTime(new Date());
		productCategory02.setPriority(5);
		productCategory02.setProductCategoryName("商品类别测试02");
		productCategory02.setShopId(29l);
		List<ProductCategory> list = new ArrayList<>();
		list.add(productCategory01);
		list.add(productCategory02);
		int i = dao.batchInsertProductCategory(list);
		System.out.println(i);
	}
	
	@Test
	public void deleteProductCategory() {
		int i = dao.deleteProductCategory(48l, 29l);
		System.out.println(i);
	}
}
