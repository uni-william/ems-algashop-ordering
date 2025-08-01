package com.algaworks.algashop.ordering.domain.model.exception;

import com.algaworks.algashop.ordering.domain.model.valueObject.id.OrderId;
import com.algaworks.algashop.ordering.domain.model.valueObject.id.OrderItemId;

public class OrderDoesNotContainOrderItemException extends  DomainException{


    public OrderDoesNotContainOrderItemException(OrderId id, OrderItemId orderItemId) {
        super(String.format(ErrorMessages.ERROR_ORDER_DOES_NOT_CONTAIN_ITEM, id, orderItemId));
    }
}
