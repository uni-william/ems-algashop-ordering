package com.algawork.algashop.ordering.domain.valueObject;

import java.util.Objects;

public record Phone(String value) {
    public Phone {
        Objects.requireNonNull(value);
        if (value.isBlank()) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public String toString() {
        return value;
    }
}
