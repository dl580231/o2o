package com.nuc.o2o.dao;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.o2o.BaseTest;
import com.nuc.o2o.entity.ShopCategory;

public class ShopCategoryTest extends BaseTest{
	@Autowired
	private ShopCategoryDao dao;
	@Test
	public void queryShopCategory(){
		ShopCategory shopCategory = new ShopCategory();
		ShopCategory parent = new ShopCategory();
		parent.setShopCategoryId(12l);
		shopCategory.setParent(parent);
		List<ShopCategory> list = dao.queryShopCategory(shopCategory);
		System.out.println(list.get(0).getCreateTime());
	}
}
