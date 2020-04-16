package com.store.web.local;

import com.store.dto.LocalAuthExecution;
import com.store.entity.LocalAuth;
import com.store.entity.PersonInfo;
import com.store.enums.OperationStateEnum;
import com.store.enums.PersonInfoStateEnum;
import com.store.enums.PersonInfoTypeEnum;
import com.store.service.LocalAuthService;
import com.store.service.PersonInfoService;
import com.store.util.VerifyCodeUtil;
import com.store.util.HttpServletRequestUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class LocalAuthController {
	@Autowired
	private LocalAuthService localAuthService;
	@Autowired
	private PersonInfoService personInfoService;

	@PostMapping(value = "/register")
	@ResponseBody
	public Map<String, Object> register(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		if (!VerifyCodeUtil.checkVerificationCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", OperationStateEnum.VERIFYCODE_ERROR.getStateInfo());
			return modelMap;
		}
		String username = HttpServletRequestUtil.getString(request, "username");
		String password = HttpServletRequestUtil.getString(request, "password");
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			LocalAuth localAuth = new LocalAuth();
			localAuth.setUsername(username);
			localAuth.setPassword(password);
			LocalAuthExecution e = localAuthService.saveLocalAuth(localAuth);
			if (e.getState() == OperationStateEnum.SUCCESS.getState()) {
				// 同时创建用户信息
				PersonInfo personInfo = new PersonInfo();
				personInfo.setLocalAuthId(localAuth.getLocalAuthId());
				personInfo.setName(username);
				personInfo.setUserType(PersonInfoTypeEnum.CUSTOMER.getState());
				personInfo.setEnableStatus(PersonInfoStateEnum.ALLOW.getState());
				personInfo.setCreateTime(new Date());
				personInfoService.insertPersonInfo(personInfo);
				modelMap.put("success", true);
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户名和密码均不能为空");
			return modelMap;
		}
		return modelMap;
	}

	/**
	 * 检查注册的用户名是否已注册
	 */
	@PostMapping(value = "/checkusername")
	@ResponseBody
	public Map<String, Object> checkUsername(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		String username = HttpServletRequestUtil.getString(request, "username");
		if (StringUtils.isNotBlank(username)) {
			LocalAuth localAuth = localAuthService.getLocalAuthByUsername(username);
			// 当前用户名的用户已存在
			if (localAuth != null) {
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户名已存在，请重新输入！");
			} else {
				modelMap.put("success", true);
			}
		}
		return modelMap;
	}

	@PostMapping(value = "login")
	@ResponseBody
	public Map<String, Object> login(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 1、验证码，前端判断3次输入后需要验证码
		boolean needVerify = HttpServletRequestUtil.getBoolean(request, "needVerify");
		if (needVerify && !VerifyCodeUtil.checkVerificationCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", OperationStateEnum.VERIFYCODE_ERROR.getStateInfo());
			return modelMap;
		}

		String username = HttpServletRequestUtil.getString(request, "username");
		String password = HttpServletRequestUtil.getString(request, "password");
		String userType = HttpServletRequestUtil.getString(request, "userType");
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)) {
			LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(username, password);
			// 账号信息正确
			if (localAuth != null) {
				// 账号允许登录
				if (localAuth.getPersonInfo().getEnableStatus().equals(PersonInfoStateEnum.ALLOW.getState())) {
					// 后台店家或管理员允许登录
					System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"+localAuth.getPersonInfo().getUserType());
					if (StringUtils.isNotEmpty(userType) && userType.equals("back")) {
						request.getSession().setAttribute("user", localAuth.getPersonInfo());
						if (localAuth.getPersonInfo().getUserType() != 1) {  // StringUtils.isEmpty(userType) &&
							modelMap.put("success", true);
						} else {
							// 没有店家和管理员权限则登录前台
							modelMap.put("success", true);
							modelMap.put("errMsg", "非店家或管理员没有权限访问后台，访问店铺首页");
						}
					} else {
						// 前台所有角色都可以登录
						modelMap.put("success", true);
					}
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", "当前用户禁止登录商城，请联系管理员");
				}
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户名或密码错误");
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户名和密码均不能为空");
		}
		return modelMap;
	}

	@PostMapping(value = "changepwd")
	@ResponseBody
	public Map<String, Object> changePwd(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<>();
		// 1、验证码
		if (!VerifyCodeUtil.checkVerificationCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", OperationStateEnum.VERIFYCODE_ERROR.getStateInfo());
			return modelMap;
		}

		// 2、获取参数
		String username = HttpServletRequestUtil.getString(request, "username");
		String password = HttpServletRequestUtil.getString(request, "password");
		String newPassword = HttpServletRequestUtil.getString(request, "newPassword");
		// 空值验证
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)
				&& StringUtils.isNotBlank(newPassword)) {
			// 修改密码
			LocalAuthExecution lae = localAuthService.modifyLocalAuth(username, password, newPassword);
			// 操作成功
			if (lae.getState() == OperationStateEnum.SUCCESS.getState()) {
				modelMap.put("success", true);
			} else {
				modelMap.put("success", false);
				modelMap.put("errMsg", lae.getStateInfo());
			}
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入有效信息");
		}
		return modelMap;
	}

	@PostMapping(value = "logout")
	@ResponseBody
	public Map<String, Object> logout(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		request.getSession().setAttribute("user", null);
		request.getSession().setAttribute("shopList", null);
		request.getSession().setAttribute("currentShop", null);
		modelMap.put("success", true);
		return modelMap;
	}
}
