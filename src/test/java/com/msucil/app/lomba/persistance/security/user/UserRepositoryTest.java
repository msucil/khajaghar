package com.msucil.app.lomba.persistance.security.user;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.msucil.app.lomba.persistance.AbstractJpaTest;

class UserRepositoryTest extends AbstractJpaTest {

	@Autowired
	private UserRepository repository;

	@Test
	void findByUsernameShouldReuturnEmpty() {

		assertThat(repository.findByUsername("username")).isEmpty();
	}

	@Test
	@DatabaseSetup("/datasets/user/user.xml")
	void findUserByUsernameShouldReturnUser() {

		var username = "admin";

		var optional = repository.findByUsername(username);

		assertThat(optional).isPresent();

		var user = optional.get();

		assertThat(user.getFullName()).isEqualTo("admin");
		assertThat(user.getUsername()).isEqualTo(username);
		assertThat(user.getPassword()).isEqualTo("password");
		assertThat(user.getEmail()).isEqualTo("email@gmail.com");
		assertThat(user.getAuthorities()).isEmpty();
	}

	@Test
	@DatabaseSetup("/datasets/user/user.xml")
	void findUserByEmailShouldReturnUser() {

		var email = "email@gmail.com";

		var optional = repository.findByEmail(email);

		assertThat(optional).isPresent();

		var user = optional.get();

		assertThat(user.getFullName()).isEqualTo("admin");
		assertThat(user.getUsername()).isEqualTo("admin");
		assertThat(user.getPassword()).isEqualTo("password");
		assertThat(user.getEmail()).isEqualTo(email);
		assertThat(user.getAuthorities()).isEmpty();
	}
}
