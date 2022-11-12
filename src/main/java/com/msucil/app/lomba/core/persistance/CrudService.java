package com.msucil.app.lomba.core.persistance;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

public interface CrudService<T, PK> {

	List<T> findAll();
	
	List<T> findAll(Sort sort);
	
	List<T> findAllByIds(Iterable<PK> ids);
	
	Optional<T> findById(PK id);
	
	boolean existsById(PK id);
	
	T save(T entity);
	
	void deleteById(PK id);
	
}
