package com.msucil.app.lomba.web.view.security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.msucil.app.lomba.core.web.CrudController;
import com.msucil.app.lomba.persistance.security.user.User;
import com.msucil.app.lomba.persistance.security.user.UserCrudService;

@Controller
@RequestMapping(UserCrudController.URL)
public class UserCrudController extends CrudController<User, UserDto, Long> {
	static final String URL = "/security/account/users";

	public UserCrudController(UserCrudService crudService, UserMapper mapper) {

		super(crudService, mapper, URL, "pages/security/account/users/");

	}
}
