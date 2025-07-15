package com.algawork.algashop.ordering.domain.entity;

import com.algawork.algashop.ordering.domain.valueObject.Money;
import com.algawork.algashop.ordering.domain.valueObject.ProductName;
import com.algawork.algashop.ordering.domain.valueObject.Quantity;
import com.algawork.algashop.ordering.domain.valueObject.id.OrderId;
import com.algawork.algashop.ordering.domain.valueObject.id.OrderItemId;
import com.algawork.algashop.ordering.domain.valueObject.id.ProductId;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderItemTest {

    @Test
    public void shouldGenerate() {
        OrderItem.brandNew()
                .productId(new ProductId())
                .quantity(new Quantity(1))
                .orderId(new OrderId())
                .productName(new ProductName("Mouse pad"))
                .price(new Money("100"))
                .build();
    }

}