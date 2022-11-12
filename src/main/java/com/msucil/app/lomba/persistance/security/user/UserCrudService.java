package com.msucil.app.lomba.persistance.security.user;

import org.springframework.stereotype.Service;

import com.msucil.app.lomba.core.persistance.AbstractCrudService;

@Service
public class UserCrudService extends AbstractCrudService<User, Long> {

	public UserCrudService(UserRepository repository) {
		super(repository);
	}
}
