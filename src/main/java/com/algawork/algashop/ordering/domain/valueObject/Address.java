package com.algawork.algashop.ordering.domain.valueObject;

import com.algawork.algashop.ordering.domain.validator.FieldValidations;
import lombok.Builder;

import java.util.Objects;

public record Address(
        String street,
        String complement,
        String neighborhood,
        String number,
        String city,
        String state,
        ZipCode zipCode
) {
    @Builder(toBuilder = true)
    public Address {
        FieldValidations.requiredNonBlank(street);
        FieldValidations.requiredNonBlank(neighborhood);
        FieldValidations.requiredNonBlank(number);
        FieldValidations.requiredNonBlank(city);
        FieldValidations.requiredNonBlank(state);
        Objects.requireNonNull(zipCode);
    }
}
