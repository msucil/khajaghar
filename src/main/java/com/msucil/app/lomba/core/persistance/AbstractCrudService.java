package com.msucil.app.lomba.core.persistance;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

public abstract class AbstractCrudService<T, PK> implements CrudService<T, PK> {

	protected final BaseRepository<T, PK> repository;

	protected AbstractCrudService(BaseRepository<T, PK> repository) {
		this.repository = repository;
	}

	@Override
	public List<T> findAll() {

		return repository.findAll();
	}

	@Override
	public List<T> findAllByIds(Iterable<PK> ids) {

		return repository.findAllById(ids);
	}

	@Override
	public List<T> findAll(Sort sort) {
		return repository.findAll(sort);
	}

	@Override
	public Optional<T> findById(PK id) {

		return repository.findById(id);
	}

	@Override
	public boolean existsById(PK id) {

		return repository.existsById(id);
	}

	@Override
	public T save(T entity) {

		return repository.save(entity);
	}

	@Override
	public void deleteById(PK id) {
		repository.deleteById(id);
	}

}
