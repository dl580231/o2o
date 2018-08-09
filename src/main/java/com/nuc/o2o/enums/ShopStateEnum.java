package com.nuc.o2o.enums;

public enum ShopStateEnum {
	ILLEGAL(-1, "非法店铺"), CHECK(0, "审核中"), SUCCESS(1, "合法店铺"),
	NULL_SHOP(-1001,"shop信息为空");

	private Integer state;
	private String stateInfo;

	private ShopStateEnum(Integer state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	/**
	 * 传入状态值返回对应的枚举对象
	 * 
	 * @param state
	 * @return
	 */
	public static ShopStateEnum getShopStateNumByState(Integer state) {
		for (ShopStateEnum shopStateEnum : values()) {
			if (shopStateEnum.getState() == state)
				return shopStateEnum;
		}
		return null;
	}

	public Integer getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

}
