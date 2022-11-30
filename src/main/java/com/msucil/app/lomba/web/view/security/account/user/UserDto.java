package com.msucil.app.lomba.web.view.security.account.user;

import com.msucil.app.lomba.core.web.Dto;
import com.msucil.app.lomba.service.validator.verifyproperty.VerifyProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@VerifyProperty(property = "password", verifyWith = "verifyPassword", message = "Password does not match")
public class UserDto extends Dto<Long> {

	private String fullName;
	private String email;
	private String username;
	private String password;
	private String verifyPassword;
}
