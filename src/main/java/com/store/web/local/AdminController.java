package com.store.web.local;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
	@RequestMapping(value = "register")
	public String register() {
		return "admin/register";
	}

	@RequestMapping(value = "login")
	public String login() {
		return "admin/login";
	}

	@RequestMapping(value = "changepwd")
	public String changePwd() {
		return "admin/changepwd";
	}

}
