package com.msucil.app.lomba.persistance.security.user;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.msucil.app.lomba.core.persistance.BaseRepository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {
	
	Optional<User> findByEmail(String email);
	
	Optional<User> findByUsername(String username);

}
