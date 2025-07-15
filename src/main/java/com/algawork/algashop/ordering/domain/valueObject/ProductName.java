package com.algawork.algashop.ordering.domain.valueObject;

import com.algawork.algashop.ordering.domain.validator.FieldValidations;

public record ProductName(String value) {

    public ProductName {
        FieldValidations.requiresNonBlank(value);
    }

    @Override
    public String toString() {
        return value;
    }

}