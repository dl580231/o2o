package com.nuc.o2o.dto;

import java.util.List;

import com.nuc.o2o.entity.Shop;
import com.nuc.o2o.enums.ShopStateEnum;

public class ShopExecution {
	// 店铺状态枚举对象
	private ShopStateEnum shopStateEnum;
	// shop对象（增删查改时用到）
	private Shop shop;
	// 查询多个店铺信息时用到
	private List<Shop> shopList;
	// 店铺数量
	private long shopCounts;

	public ShopExecution() {
	};

	/**
	 * 店铺操作失败时用到的构造器
	 * 
	 * @param stateEnum
	 */
	public ShopExecution(ShopStateEnum shopStateEnum) {
		this.shopStateEnum = shopStateEnum;
	}

	/**
	 * 店铺操作成功时执行的构造器
	 * 
	 * @param shopStateEnum
	 * @param shop
	 */
	public ShopExecution(ShopStateEnum shopStateEnum, Shop shop) {
		this.shopStateEnum = shopStateEnum;
		this.shop = shop;
	}

	/**
	 * 店铺操作成功时执行的构造器
	 * 
	 * @param shopStateEnum
	 * @param shopList
	 */
	public ShopExecution(ShopStateEnum shopStateEnum, List<Shop> shopList) {
		this.shopStateEnum = shopStateEnum;
		this.shopList = shopList;
	}

	public ShopStateEnum getShopStateEnum() {
		return shopStateEnum;
	}

	public void setShopStateEnum(ShopStateEnum shopStateEnum) {
		this.shopStateEnum = shopStateEnum;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public List<Shop> getShopList() {
		return shopList;
	}

	public void setShopList(List<Shop> shopList) {
		this.shopList = shopList;
	}

	public long getShopCounts() {
		return shopCounts;
	}

	public void setShopCounts(long shopCounts) {
		this.shopCounts = shopCounts;
	}
}
