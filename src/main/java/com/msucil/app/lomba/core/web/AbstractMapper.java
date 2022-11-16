package com.msucil.app.lomba.core.web;

import java.util.List;

import com.msucil.app.lomba.core.persistance.AbstractEntity;

public interface AbstractMapper<E extends AbstractEntity<I>, D extends Dto<I>, I> {

	public D mapToDto(E entity);

	public E maptoEntity(D dto);

	public List<D> mapToDtos(List<E> entites);

	public List<E> mapToEntites(List<D> entites);

}
