package com.msucil.app.lomba.core.web;

import java.util.List;

import com.msucil.app.lomba.core.persistance.AbstractEntity;

public interface AbstractMapper<E extends AbstractEntity<I>, D extends Dto<I>, I> {

	D mapToDto(E entity);

	E maptoEntity(D dto);

	List<D> mapToDtos(List<E> entites);

	List<E> mapToEntites(List<D> entites);

}
