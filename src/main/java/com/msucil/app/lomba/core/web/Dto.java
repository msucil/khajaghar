package com.msucil.app.lomba.core.web;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dto<K> {

	private K id;
	
	private int version;
	
	private Instant createdAt;
	
	private Instant updatedAt;
}
