package com.msucil.app.lomba.persistance.security.user;

import org.springframework.stereotype.Repository;

import com.msucil.app.lomba.core.persistance.BaseRepository;

@Repository
public interface UserRepository extends BaseRepository<User, Long> {

}
