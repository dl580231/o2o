package com.nuc.o2o.utils;

public class PageCalculatorUtils {
	public static int rowIndexCalculate(int pageIndex, int rowSize) {
		return pageIndex > 0 ? (pageIndex - 1) * rowSize : 0;
	}
}
