package com.msucil.app.lomba.service.security.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.msucil.app.lomba.core.persistance.CrudService;
import com.msucil.app.lomba.persistance.security.user.User;
import com.msucil.app.lomba.persistance.security.user.UserQueryRepository;

@Service
public class SystemUserServiceImpl implements SystemUserService {

	private CrudService<User, Long> userService;
	private UserQueryRepository queryRepo;
	private PasswordEncoder passwordEncoder;

	public SystemUserServiceImpl(@Autowired CrudService<User, Long> userService,
			@Autowired UserQueryRepository queryRepo, @Autowired PasswordEncoder passwordEncoder) {

		this.userService = userService;
		this.queryRepo = queryRepo;
		this.passwordEncoder = passwordEncoder;

	}

	@Override
	public User registerSystemUser(User user) {
		
		var rawPassword = user.getPassword();
		
		user.setPassword(passwordEncoder.encode(rawPassword));
		
		return userService.save(user);
	}

	@Override
	public boolean canRegisterSystemUser() {
		return queryRepo.totalCount() == 0;
	}

}
