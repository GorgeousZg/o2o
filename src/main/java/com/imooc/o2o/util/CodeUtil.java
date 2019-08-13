package com.imooc.o2o.util;

import javax.servlet.http.HttpServletRequest;

public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request){
		String verifyCodeExpected=(String)request.getSession().getAttribute(
				com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String verifyCodeActual=HttpServletRequestUtil.getString(request, "verifyCodeActual");
		System.out.println("verifyCodeActualfont-->"+verifyCodeActual);
		System.out.println("verifyCodeExpected-->"+verifyCodeExpected);
		if(verifyCodeActual==null||!verifyCodeActual.equals(verifyCodeExpected)){
			return false;
		}else{
			return true;
		}
	}
}
