package com.nuc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nuc.o2o.entity.Shop;

public interface ShopDao {
	/**
	 * 查询shop的总数
	 * @return
	 */
	public Long queryShopCount();
	
	/**
	 * 根据传来的信息模糊查询店铺列表
	 * @param shop
	 * @param rowStart
	 * @param rowEnd
	 * @return
	 */
	public List<Shop> queryShopList(@Param("shop") Shop shop, @Param("rowIndex") int rowStart,
			@Param("rowSize") int rowNum);

	/**
	 * 增加店铺
	 * 
	 * @return店铺注册成功与否的结果：1.成功；-1.失败
	 */
	public int insertShop(Shop shop);

	/**
	 * 修改店铺信息
	 * 
	 * @param shop
	 * @return 店铺修改成功与否的结果：1.成功；-1.失败
	 */
	public int updateShop(Shop shop);

	/**
	 * 根据id返回店铺信息
	 * 
	 * @param shopId
	 * @return Shop
	 */
	public Shop queryShopById(Long shopId);
}
