package com.msucil.app.lomba.web.view.security.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(AuthController.URL)
public class AuthController {
	static final String URL = "/security/auth";

	@GetMapping("/login")
	public String login() {
		return "pages/security/auth/login";
	}
}
