package com.msucil.app.lomba.core.persistance;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public abstract class AbstractAuditableEntity<K, U> extends AbstractEntity<K> {

	protected AbstractAuditableEntity() {
		// empty constructor
	}

	@ManyToOne
	@JoinColumn(name = "created_by")
	private U createdBy;

	@ManyToOne
	@JoinColumn(name = "updated_by")
	private U updatedBy;
}
