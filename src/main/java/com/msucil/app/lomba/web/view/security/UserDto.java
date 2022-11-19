package com.msucil.app.lomba.web.view.security;

import com.msucil.app.lomba.core.web.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UserDto extends Dto<Long> {

	private String fullName;
}
