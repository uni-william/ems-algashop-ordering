package com.algawork.algashop.ordering.domain.factory;

import com.algawork.algashop.ordering.domain.entity.Order;
import com.algawork.algashop.ordering.domain.entity.PaymentMethod;
import com.algawork.algashop.ordering.domain.valueObject.Billing;
import com.algawork.algashop.ordering.domain.valueObject.Product;
import com.algawork.algashop.ordering.domain.valueObject.Quantity;
import com.algawork.algashop.ordering.domain.valueObject.Shipping;
import com.algawork.algashop.ordering.domain.valueObject.id.CustomerId;

import java.util.Objects;

public class OrderFactory {

    private OrderFactory() {

    }

    public static Order filled(
            CustomerId customerId,
            Shipping shipping,
            Billing billing,
            PaymentMethod paymentMethod,
            Product product,
            Quantity productQuantity
    ) {
        Objects.requireNonNull(customerId);
        Objects.requireNonNull(shipping);
        Objects.requireNonNull(billing);
        Objects.requireNonNull(paymentMethod);
        Objects.requireNonNull(product);
        Objects.requireNonNull(productQuantity);

        Order order = Order.draft(customerId);

        order.changeBilling(billing);
        order.changeShipping(shipping);
        order.changePaymentMethod(paymentMethod);
        order.addItem(product, productQuantity);

        return order;
    }

}