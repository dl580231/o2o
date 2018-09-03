package com.nuc.o2o.exceptions;

public class ProductCategoryOperationException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8685937516686900541L;

	/**
	 * 封装了RuntimeException，可以从封装的异常名字得知是对ProductCategory操作时出现异常
	 */
	public ProductCategoryOperationException(String msg) {
		super(msg);
	}
}
