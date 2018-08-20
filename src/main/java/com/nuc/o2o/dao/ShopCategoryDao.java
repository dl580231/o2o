package com.nuc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nuc.o2o.entity.ShopCategory;

public interface ShopCategoryDao {
	public List<ShopCategory> queryShopCategory(@Param("shopCategoryCondition")ShopCategory shopCategory);
}
