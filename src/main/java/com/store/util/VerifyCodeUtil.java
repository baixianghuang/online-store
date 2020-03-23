package com.store.util;

import com.google.code.kaptcha.Constants;

import javax.servlet.http.HttpServletRequest;

public class VerifyCodeUtil {
    public static boolean checkVerificationCode(HttpServletRequest request) {
        String verificationCodeExpected = (String) request.getSession().getAttribute(Constants.KAPTCHA_SESSION_KEY);
        String verificationCodeActual = HttpServletRequestUtil.getString(request, "verificationCodeActual");
        return verificationCodeActual != null && verificationCodeActual.equals(verificationCodeExpected);
    }
}
