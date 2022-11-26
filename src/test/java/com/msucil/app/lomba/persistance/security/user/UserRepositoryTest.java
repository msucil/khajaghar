package com.msucil.app.lomba.persistance.security.user;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.msucil.app.lomba.persistance.AbstractJpaTest;

class UserRepositoryTest extends AbstractJpaTest {

	@Autowired
	private UserRepository repository;

	@Test
	void findByUserNameShouldReuturnEmpty() {

		assertEquals(true, repository.findByUsername("username").isEmpty());
	}
}
