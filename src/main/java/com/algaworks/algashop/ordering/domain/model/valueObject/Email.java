package com.algaworks.algashop.ordering.domain.model.valueObject;

import com.algaworks.algashop.ordering.domain.model.validator.FieldValidations;

public record Email(String value) {
    public Email {
        FieldValidations.requiresValidEmail(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
