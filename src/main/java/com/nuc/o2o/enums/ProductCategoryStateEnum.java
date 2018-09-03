package com.nuc.o2o.enums;

public enum ProductCategoryStateEnum {
	SUCCESS(1, "操作商品类别成功"), NULL_PRODUCT_CATEGORY(-1001, "productCategory信息为空"), INNER_ERROR(-1002, "内部错误");

	private Integer state;
	private String stateInfo;

	private ProductCategoryStateEnum(Integer state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public static ProductCategoryStateEnum stateOf(Integer state) {
		for (ProductCategoryStateEnum productCategoryStateEnum : values()) {
			if (productCategoryStateEnum.state == state) {
				return productCategoryStateEnum;
			}
		}
		return null;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

}
