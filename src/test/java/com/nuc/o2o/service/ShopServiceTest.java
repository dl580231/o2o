package com.nuc.o2o.service;

import java.io.File;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.o2o.BaseTest;
import com.nuc.o2o.dto.ShopExecution;
import com.nuc.o2o.entity.Area;
import com.nuc.o2o.entity.PersonInfo;
import com.nuc.o2o.entity.Shop;
import com.nuc.o2o.entity.ShopCategory;
import com.nuc.o2o.enums.ShopStateEnum;

public class ShopServiceTest extends BaseTest {
	@Autowired
	private ShopService shopService;

	@Test
	public void addShopTest() {
		// 编辑shop和image文件基础信息
		Shop shop = new Shop();
		Area area = new Area();
		area.setAreaId(2);
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(1l);
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(12l);
		shop.setArea(area);
		shop.setOwner(personInfo);
		shop.setShopCategory(shopCategory);
		shop.setShopName("来一杯橙汁");
		shop.setShopDesc("一杯喝了，永葆青春");
		shop.setShopImg("test橙汁");
		shop.setPhone("18235105722");
		shop.setPriority(11);
		shop.setShopAddr("文瀛13");
		shop.setAdvice("审核中");
		File imageFile = new File("C:\\Users\\lenovo\\Desktop\\img\\family.jpg");
		// 调用service
		ShopExecution shopExecution = shopService.addShop(shop, imageFile);
		Assert.assertEquals(ShopStateEnum.CHECK.getState(), shopExecution.getShopStateEnum().getState());
	}
}
