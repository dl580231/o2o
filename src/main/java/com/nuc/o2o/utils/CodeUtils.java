package com.nuc.o2o.utils;

import javax.servlet.http.HttpServletRequest;

public class CodeUtils {
	public static boolean checkVerifyCode(HttpServletRequest request) {
		String verifyCodeImg = (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String verifyCode = request.getParameter("verifyCode");
		if(verifyCode.length()!=0&&verifyCode.equals(verifyCodeImg)) {
			return true;
		}
		return false;
	}
}