package com.algaworks.algashop.ordering.domain.model.shoppingcart;

import com.algaworks.algashop.ordering.domain.model.customer.CustomerId;

import java.time.OffsetDateTime;

public record ShoppingCartCreatedEvent(
        ShoppingCartId shoppingCartId,
        CustomerId customerId,
        OffsetDateTime createdAt
) {}
