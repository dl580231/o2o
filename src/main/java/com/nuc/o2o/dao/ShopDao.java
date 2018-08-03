package com.nuc.o2o.dao;

import com.nuc.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * 增加店铺
	 * @return店铺注册成功与否的结果：1.成功；-1.失败
	 */
	public int insertShop(Shop shop);
	/**
	 * 修改店铺信息
	 * @param shop
	 * @return 店铺修改成功与否的结果：1.成功；-1.失败
	 */
	public int updateShop(Shop shop);
}
