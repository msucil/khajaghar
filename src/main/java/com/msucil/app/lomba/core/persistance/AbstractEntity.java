package com.msucil.app.lomba.core.persistance;

import java.io.Serializable;
import java.time.Instant;

import org.hibernate.annotations.GenericGenerator;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Version;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@MappedSuperclass
public abstract class AbstractEntity<K> implements Serializable {

	protected AbstractEntity() {
		//empty constructor
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected K id;

	@Version
	protected int version;

	protected Instant createdAt = Instant.now();
	protected Instant updatedAt = Instant.now();
}
