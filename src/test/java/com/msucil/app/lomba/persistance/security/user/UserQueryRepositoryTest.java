package com.msucil.app.lomba.persistance.security.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.msucil.app.lomba.persistance.AbstractJpaTest;

import lombok.extern.slf4j.Slf4j;

@Slf4j
class UserQueryRepositoryTest extends AbstractJpaTest {

	@Autowired
	private UserQueryRepository queryRepo;

	@Test
	@DatabaseSetup("/datasets/user/empty.xml")
	void testTotalCountShouldReturnZero() throws SQLException {
		
		assertThat(queryRepo.totalCount()).isZero();
	}

	
	@Test
	@DatabaseSetup("/datasets/user/user.xml")
	void testTotalCountShouldReturn() {

		var totalCount = queryRepo.totalCount();
		
		assertThat(totalCount).isNotZero().isEqualTo(1);
	}
}
