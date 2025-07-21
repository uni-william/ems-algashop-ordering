package com.algaworks.algashop.ordering.domain.model.entity;

import java.util.Arrays;
import java.util.List;

public enum OrderStatus {

    DRAFT,
    PLACED(DRAFT),
    PAID(PLACED),
    READY(PAID),
    CANCELED(PAID, READY, PLACED, DRAFT);

    OrderStatus(OrderStatus... previousStatus) {
        this.previousStatus = Arrays.asList(previousStatus);
    }

    private final List<OrderStatus> previousStatus;

    public boolean canChangeTo(OrderStatus newStatus) {
        OrderStatus currentStatus = this;
        return  newStatus.previousStatus.contains(currentStatus);
    }

    public boolean canNotChangeTo(OrderStatus newStatus) {
        return !canChangeTo(newStatus);
    }

}
