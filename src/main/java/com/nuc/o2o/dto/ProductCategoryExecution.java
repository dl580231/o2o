package com.nuc.o2o.dto;

import java.util.List;

import com.nuc.o2o.entity.ProductCategory;
import com.nuc.o2o.enums.ProductCategoryStateEnum;

public class ProductCategoryExecution {
	private ProductCategoryStateEnum productCategoryStateEnum;
	private List<ProductCategory> productCategories;

	/**
	 * 默认构造方法
	 */
	public ProductCategoryExecution() {

	}

	/**
	 * 执行失败的构造方法
	 * 
	 * @param productCategoryStateEnum
	 */
	public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum) {
		this.productCategoryStateEnum = productCategoryStateEnum;
	}

	public ProductCategoryExecution(ProductCategoryStateEnum productCategoryStateEnum,
			List<ProductCategory> productCategories) {
		this.productCategoryStateEnum = productCategoryStateEnum;
		this.productCategories = productCategories;
	}

	public ProductCategoryStateEnum getProductCategoryStateEnum() {
		return productCategoryStateEnum;
	}

	public void setProductCategoryStateEnum(ProductCategoryStateEnum productCategoryStateEnum) {
		this.productCategoryStateEnum = productCategoryStateEnum;
	}

	public List<ProductCategory> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(List<ProductCategory> productCategories) {
		this.productCategories = productCategories;
	}

}
