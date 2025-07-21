package com.algawork.algashop.ordering.domain.exception;

import com.algawork.algashop.ordering.domain.entity.OrderStatus;
import com.algawork.algashop.ordering.domain.valueObject.id.OrderId;

import static com.algawork.algashop.ordering.domain.exception.ErrorMessages.ERROR_ORDER_STATUS_CANNOT_BE_CHANGED;

public class OrderStatusCannotBeChangedException extends DomainException {

    public OrderStatusCannotBeChangedException(OrderId id, OrderStatus status, OrderStatus newStatus) {
        super(String.format(ERROR_ORDER_STATUS_CANNOT_BE_CHANGED, id, status, newStatus));
    }

}
