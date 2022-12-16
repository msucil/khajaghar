package com.msucil.app.lomba.web.view.security.account.system;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.msucil.app.lomba.persistance.security.user.User;
import com.msucil.app.lomba.service.security.system.SystemUserService;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/security/account/system")
public class SystemUserController {
	private static final String FORM = "pages/security/account/system/system-user-form";

	private final SystemUserService systemUserService;

	public SystemUserController(SystemUserService systemUserService) {
		this.systemUserService = systemUserService;
	}

	@GetMapping("/register-user")
	public String registerUser(@ModelAttribute("form") SystemUserForm form, Model model) {

		canAccessPage();

		return FORM;
	}

	@PostMapping("/register-user")
	public String registerUser(@ModelAttribute("form") @Valid SystemUserForm form, BindingResult result, Model model,
			RedirectAttributes redirect) {
		
		canAccessPage();
		
		if(result.hasErrors()) {
			return FORM;
		}

		var user = new User();

		user.setFullName(form.getFullName());
		user.setUsername(form.getUsername());
		user.setEmail(form.getEmail());
		user.setPassword(form.getPassword());

		try {
			systemUserService.registerSystemUser(user);
			
			
			redirect.addFlashAttribute("success", "System User Registration Successful");
			
			return "redirect:/security/account/system/success";
		}
		catch(RuntimeException ex) {
			redirect.addFlashAttribute("error", "Error occurred while saving system user");
		}
		
		return FORM;
	}

	@GetMapping("/success")
	public String success() {
		return "pages/security/account/system/registration-success";
	}

	private void canAccessPage() {
		if (!systemUserService.canRegisterSystemUser()) {
			throw new ResponseStatusException(HttpStatus.METHOD_NOT_ALLOWED, "Page can not be accessed");
		}
	}
}
