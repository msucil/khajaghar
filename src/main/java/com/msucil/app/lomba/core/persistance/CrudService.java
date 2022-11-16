package com.msucil.app.lomba.core.persistance;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

public interface CrudService<T, K> {

	List<T> findAll();
	
	List<T> findAll(Sort sort);
	
	List<T> findAllByIds(Iterable<K> ids);
	
	Optional<T> findById(K id);
	
	boolean existsById(K id);
	
	T save(T entity);
	
	void deleteById(K id);
	
}
