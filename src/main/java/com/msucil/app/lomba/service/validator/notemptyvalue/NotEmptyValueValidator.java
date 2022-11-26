package com.msucil.app.lomba.service.validator.notemptyvalue;


import org.apache.commons.lang3.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NotEmptyValueValidator implements ConstraintValidator<NotEmptyValue, String> {

    @Override
    public void initialize(NotEmptyValue constraintAnnotation) {
      //default
    }

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return StringUtils.isNotEmpty(value);
	}
}
