package com.nuc.o2o.exceptions;

public class ShopOperationException extends RuntimeException {

	/**
	 * 封装了RuntimeException，可以从封装的异常名字得知是对shop操作时出现异常
	 */
	private static final long serialVersionUID = 1L;
	
	public ShopOperationException(String msg) {
		super(msg);
	}
}
