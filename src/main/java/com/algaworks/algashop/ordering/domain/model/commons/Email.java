package com.algaworks.algashop.ordering.domain.model.commons;

import com.algaworks.algashop.ordering.domain.model.FieldValidations;

public record Email(String value) {
	public Email {
		FieldValidations.requiresValidEmail(value);
	}

	@Override
	public String toString() {
		return value;
	}
}