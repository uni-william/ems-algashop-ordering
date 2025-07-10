package com.algawork.algashop.ordering.domain.valueObject;

import com.algawork.algashop.ordering.domain.validator.FieldValidations;

public record Email(String value) {
    public Email {
        FieldValidations.requiresValidEmail(value);
    }

    @Override
    public String toString() {
        return value;
    }
}
