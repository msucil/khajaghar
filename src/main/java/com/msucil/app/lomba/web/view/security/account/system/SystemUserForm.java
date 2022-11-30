package com.msucil.app.lomba.web.view.security.account.system;

import com.msucil.app.lomba.core.web.Dto;
import com.msucil.app.lomba.service.validator.notemptyvalue.NotEmptyValue;
import com.msucil.app.lomba.service.validator.verifyproperty.VerifyProperty;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@VerifyProperty(property = "password", verifyWith = "verifyPassword", message = "Password does not match")
public class SystemUserForm extends Dto<Long> {

	@NotEmptyValue
	private String fullName;
	
	@Email
	@NotEmptyValue
	private String email;
	
	@NotEmptyValue
	private String username;
	
	@NotEmptyValue
	private String password;
	
	@NotEmptyValue
	private String verifyPassword;
}
