package com.nuc.o2o.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.nuc.o2o.entity.ProductCategory;

public interface ProductCategoryDao {
	/**
	 * 根据shopId查询商品类别信息
	 * 
	 * @param shopId
	 * @return
	 */
	public List<ProductCategory> queryProductCategoryList(Long shopId);

	/**
	 * 批量插入商品类别信息
	 * 
	 * @param productCategories
	 * @return
	 */
	public int batchInsertProductCategory(List<ProductCategory> productCategories);

	/**
	 * 为了防止删除其他店铺的商品类别，在这里通过productCategoryId和shopId共同决定删除的商品类别
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	public int deleteProductCategory(@Param("productCategoryId") Long productCategoryId, @Param("shopId") Long shopId);
}
