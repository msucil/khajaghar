package com.msucil.app.lomba.core.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseRepository<T, K> extends JpaRepository<T, K> {

}
