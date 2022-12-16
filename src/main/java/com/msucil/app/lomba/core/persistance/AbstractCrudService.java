package com.msucil.app.lomba.core.persistance;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

public abstract class AbstractCrudService<T, K> implements CrudService<T, K> {

	protected final BaseRepository<T, K> repository;

	protected AbstractCrudService(BaseRepository<T, K> repository) {
		this.repository = repository;
	}

	@Override
	public List<T> findAll() {

		return repository.findAll();
	}

	@Override
	public List<T> findAllByIds(Iterable<K> ids) {

		return repository.findAllById(ids);
	}

	@Override
	public List<T> findAll(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public Optional<T> findById(K id) {

		return repository.findById(id);
	}

	@Override
	public boolean existsById(K id) {

		return repository.existsById(id);
	}

	@Override
	public T save(T entity) {

		return repository.save(entity);
	}

	@Override
	public void deleteById(K id) {
		repository.deleteById(id);
	}
	
	public long count() {
		return repository.count();
	}

}
