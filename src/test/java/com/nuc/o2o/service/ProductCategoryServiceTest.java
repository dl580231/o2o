package com.nuc.o2o.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.o2o.BaseTest;
import com.nuc.o2o.dto.ProductCategoryExecution;
import com.nuc.o2o.entity.ProductCategory;

public class ProductCategoryServiceTest extends BaseTest{
	@Autowired
	private ProductCategoryService service;
	
	@Test
	@Ignore
	public void getProductCatrgoryListTest() {
		List<ProductCategory> list = service.getProductCatrgoryList(28l);
		System.out.println(list);
	}
	
	@Test
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
		ProductCategoryExecution execution = service.batchAddProductCategory(list);
		System.out.println(execution.getProductCategoryStateEnum().getStateInfo());
	}
}
