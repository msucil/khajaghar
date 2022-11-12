package com.msucil.app.lomba.core.web;

import java.util.List;

import com.msucil.app.lomba.core.persistance.AbstractEntity;

public abstract interface AbstractMapper<E extends AbstractEntity<I>, D extends Dto<I>, I> {
	
	public abstract D mapToDto(E entity);
	public abstract E maptoEntity(D dto);
	
	public abstract List<D> mapToDtos(List<E> entites);
	public abstract List<E> mapToEntites(List<D> entites);

}
