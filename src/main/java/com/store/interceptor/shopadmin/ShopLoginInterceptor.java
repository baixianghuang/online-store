package com.store.interceptor.shopadmin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.store.enums.EnableStateEnum;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.store.entity.PersonInfo;
import com.store.enums.PersonInfoTypeEnum;

public class ShopLoginInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println(">>>ShopLoginInterceptor>>>>>>>在请求处理之前进行调用（Controller方法调用之前）");
		Object userObj = request.getSession().getAttribute("user");
		if (userObj != null) {
			PersonInfo user = (PersonInfo) userObj;
			// 用户存在且可用，并且用户类型为店家或者管理员
			if (user != null && user.getUserId() != null && user.getUserId() > 0
					&& user.getEnableStatus().equals(EnableStateEnum.AVAILABLE.getState())
					&& (user.getUserType().equals(PersonInfoTypeEnum.OWNER.getState()) || user.getUserType().equals(PersonInfoTypeEnum.ADMIN.getState()))) {
				return true;
			}
		}
		// 不满足登录条件，则直接跳转后台用户登录页面
		String loginUrl = request.getContextPath() + "/admin/login?userType=back";
		response.sendRedirect(loginUrl);
		return false;
	}

}
