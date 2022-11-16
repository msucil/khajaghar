package com.msucil.app.lomba.core.persistance;

import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class AbstractAuditableEntity<K, U> extends AbstractEntity<K> {

	protected AbstractAuditableEntity() {
		//empty constructor
	}

	@ManyToOne
	private U createdBy;

	@ManyToOne
	private U updatedBy;
}
