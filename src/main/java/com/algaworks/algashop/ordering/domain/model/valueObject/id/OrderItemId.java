package com.algaworks.algashop.ordering.domain.model.valueObject.id;

import com.algaworks.algashop.ordering.domain.model.utility.IdGenerator;
import io.hypersistence.tsid.TSID;

import java.util.Objects;

public record OrderItemId(TSID value) {

    public OrderItemId {
        Objects.requireNonNull(value);
    }

    public OrderItemId() {
        this(IdGenerator.gererateTSID());
    }

    public OrderItemId(Long value) {
        this(TSID.from(value));
    }

    public OrderItemId(String value) {
        this(TSID.from(value));
    }

    @Override
    public String toString() {
        return value.toString();
    }
}