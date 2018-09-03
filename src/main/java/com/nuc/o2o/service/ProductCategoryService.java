package com.nuc.o2o.service;

import java.util.List;

import com.nuc.o2o.dto.ProductCategoryExecution;
import com.nuc.o2o.entity.ProductCategory;
import com.nuc.o2o.exceptions.ProductCategoryOperationException;

public interface ProductCategoryService {
	/**
	 * 根据传来的shopId查询出店铺拥有的商品类别
	 * 
	 * @param shopId
	 * @return
	 */
	public List<ProductCategory> getProductCatrgoryList(Long shopId);

	/**
	 * 批量插入商品类别信息
	 * 
	 * @param productCategories
	 * @return
	 */
	public ProductCategoryExecution batchAddProductCategory(List<ProductCategory> productCategories)
			throws ProductCategoryOperationException;

	/**
	 * 删除商品类别
	 * 
	 * @param productCategoryId
	 * @param shopId
	 * @return
	 */
	public ProductCategoryExecution removeProductCategory(Long productCategoryId, Long shopId)
			throws ProductCategoryOperationException;
}
