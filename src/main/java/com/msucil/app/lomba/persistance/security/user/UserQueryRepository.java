package com.msucil.app.lomba.persistance.security.user;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

@org.springframework.stereotype.Repository
public interface UserQueryRepository extends Repository<User, Long> {

	@Query(value = """
			SELECT COALESCE(COUNT(id), 0) FROM users
			""",
			nativeQuery = true)
	long totalCount();
}
