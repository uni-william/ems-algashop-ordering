package com.algaworks.algashop.ordering.domain.model.order;

import com.algaworks.algashop.ordering.domain.model.product.ProductTestDataBuilder;
import com.algaworks.algashop.ordering.domain.model.commons.Money;
import com.algaworks.algashop.ordering.domain.model.product.Product;
import com.algaworks.algashop.ordering.domain.model.commons.Quantity;
import com.algaworks.algashop.ordering.domain.model.customer.CustomerId;
import com.algaworks.algashop.ordering.domain.model.product.ProductOutOfStockException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
class BuyNowServiceTest {

    private final BuyNowService buyNowService = new BuyNowService();

    @Test
    void givenValidProductAndDetails_whenBuyNow_shouldReturnPlacedOrder() {
        Product product = ProductTestDataBuilder.aProduct().build();
        CustomerId customerId = new CustomerId();
        Billing billingInfo = OrderTestDataBuilder.aBilling();
        Shipping shippingInfo = OrderTestDataBuilder.aShipping();
        Quantity quantity = new Quantity(3);
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;

        Order order = buyNowService.buyNow(product, customerId, billingInfo, shippingInfo, quantity, paymentMethod);

        assertThat(order).isNotNull();
        assertThat(order.id()).isNotNull();
        assertThat(order.customerId()).isEqualTo(customerId);
        assertThat(order.billing()).isEqualTo(billingInfo);
        assertThat(order.shipping()).isEqualTo(shippingInfo);
        assertThat(order.paymentMethod()).isEqualTo(paymentMethod);
        assertThat(order.isPlaced()).isTrue();

        assertThat(order.items()).hasSize(1);
        assertThat(order.items().iterator().next().productId()).isEqualTo(product.id());
        assertThat(order.items().iterator().next().quantity()).isEqualTo(quantity);
        assertThat(order.items().iterator().next().price()).isEqualTo(product.price());

        Money expectedTotalAmount = product.price().multiply(quantity).add(shippingInfo.cost());
        assertThat(order.totalAmount()).isEqualTo(expectedTotalAmount);
        assertThat(order.totalItems()).isEqualTo(quantity);
    }

    @Test
    void givenOutOfStockProduct_whenBuyNow_shouldThrowProductOutOfStockException() {
        Product product = ProductTestDataBuilder.aProductUnavailable().build();
        CustomerId customerId = new CustomerId();
        Billing billingInfo = OrderTestDataBuilder.aBilling();
        Shipping shippingInfo = OrderTestDataBuilder.aShipping();
        Quantity quantity = new Quantity(1);
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;

        assertThatExceptionOfType(ProductOutOfStockException.class)
                .isThrownBy(() -> buyNowService.buyNow(product, customerId, billingInfo, shippingInfo, quantity, paymentMethod));
    }

    @Test
    void givenInvalidQuantity_whenBuyNow_shouldThrowIllegalArgumentException() {
        Product product = ProductTestDataBuilder.aProduct().build();
        CustomerId customerId = new CustomerId();
        Billing billingInfo = OrderTestDataBuilder.aBilling();
        Shipping shippingInfo = OrderTestDataBuilder.aShipping();
        Quantity quantity = new Quantity(0);
        PaymentMethod paymentMethod = PaymentMethod.CREDIT_CARD;

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> buyNowService.buyNow(product, customerId, billingInfo, shippingInfo, quantity, paymentMethod));
    }

}