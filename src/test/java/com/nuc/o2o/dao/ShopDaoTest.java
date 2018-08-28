package com.nuc.o2o.dao;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.nuc.o2o.BaseTest;
import com.nuc.o2o.entity.Area;
import com.nuc.o2o.entity.PersonInfo;
import com.nuc.o2o.entity.Shop;
import com.nuc.o2o.entity.ShopCategory;

public class ShopDaoTest extends BaseTest{
	@Autowired
	private ShopDao shopDao;
	@Test
	@Ignore
	public void insertShopTest() {
		Shop shop = new Shop();
		Area area=new Area();
		area.setAreaId(2);
		PersonInfo personInfo=new PersonInfo();
		personInfo.setUserId(1l);
		ShopCategory shopCategory = new ShopCategory();
		shopCategory.setShopCategoryId(12l);
		shop.setArea(area);
		shop.setOwner(personInfo);
		shop.setShopCategory(shopCategory);
		shop.setShopName("来一杯咖啡");
		shop.setShopDesc("一杯喝了，还有三杯");
		shop.setShopImg("test");
		shop.setPhone("18235105722");
		shop.setPriority(10);
		shop.setEnableStatus(1);
		shop.setShopAddr("文瀛13");
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setAdvice("审核中");
		int result = shopDao.insertShop(shop);
		System.out.println(shop.getShopId());
		Assert.assertEquals(1, result);
	}
	
	@Test
	@Ignore
	public void updateShopTest() {
		Shop shop = new Shop();
		shop.setPhone("18434926968");
		shop.setShopId(36l);
		int result = shopDao.updateShop(shop);
		assertEquals(1,result);
	}
	
	@Test
	public void queryShoById() {
		Shop shop = shopDao.queryShopById(29L);
		System.out.println(shop.getArea().getAreaName());
		System.out.println(shop.getShopCategory().getShopCategoryName());
	}
}
