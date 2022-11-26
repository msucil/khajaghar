package com.msucil.app.lomba.service.security.auth;

import org.hibernate.validator.internal.constraintvalidators.bv.EmailValidator;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.msucil.app.lomba.persistance.security.user.UserRepository;

public class UserDetailServiceImpl implements UserDetailsService {
	
	private final UserRepository repository;
	
	public UserDetailServiceImpl(UserRepository repository) {
		this.repository = repository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		var emailValidator = new EmailValidator();
		if (emailValidator.isValid(username, null)) {
			return repository.findByEmail(username).orElseThrow(() ->  new UsernameNotFoundException(username));
		}
		
		return repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
	}

}
