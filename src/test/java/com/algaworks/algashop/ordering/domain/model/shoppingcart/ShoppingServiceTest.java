package com.algaworks.algashop.ordering.domain.model.shoppingcart;

import com.algaworks.algashop.ordering.domain.model.commons.Money;
import com.algaworks.algashop.ordering.domain.model.commons.Quantity;
import com.algaworks.algashop.ordering.domain.model.customer.CustomerTestDataBuilder;
import com.algaworks.algashop.ordering.domain.model.customer.CustomerAlreadyHaveShoppingCartException;
import com.algaworks.algashop.ordering.domain.model.customer.CustomerNotFoundException;
import com.algaworks.algashop.ordering.domain.model.customer.Customers;
import com.algaworks.algashop.ordering.domain.model.customer.CustomerId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith; // Added import
import org.mockito.InjectMocks; // Added import
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension; // Added import

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ShoppingServiceTest {

    @InjectMocks
    private ShoppingService shoppingService;

    @Mock
    private ShoppingCarts shoppingCarts;

    @Mock
    private Customers customers;

    @Test
    void givenExistingCustomerAndNoShoppingCart_whenStartShopping_shouldReturnNewShoppingCart() {
        CustomerId customerId = CustomerTestDataBuilder.DEFAULT_CUSTOMER_ID;

        when(customers.exists(customerId)).thenReturn(true);
        when(shoppingCarts.ofCustomer(customerId)).thenReturn(Optional.empty());

        ShoppingCart newShoppingCart = shoppingService.startShopping(customerId);

        assertThat(newShoppingCart).isNotNull();
        assertThat(newShoppingCart.customerId()).isEqualTo(customerId);
        assertThat(newShoppingCart.isEmpty()).isTrue();
        assertThat(newShoppingCart.totalAmount()).isEqualTo(Money.ZERO);
        assertThat(newShoppingCart.totalItems()).isEqualTo(Quantity.ZERO);

        verify(customers).exists(customerId);
        verify(shoppingCarts).ofCustomer(customerId);
    }

    @Test
    void givenNonExistingCustomer_whenStartShopping_shouldThrowCustomerNotFoundException() {
        CustomerId customerId = new CustomerId();

        when(customers.exists(customerId)).thenReturn(false);

        assertThatExceptionOfType(CustomerNotFoundException.class)
                .isThrownBy(() -> shoppingService.startShopping(customerId));

        verify(customers).exists(customerId);
        verify(shoppingCarts, never()).ofCustomer(any());
    }

    @Test
    void givenExistingCustomerAndExistingShoppingCart_whenStartShopping_shouldThrowCustomerAlreadyHaveShoppingCartException() {
        CustomerId customerId = CustomerTestDataBuilder.DEFAULT_CUSTOMER_ID;
        ShoppingCart existingCart = ShoppingCartTestDataBuilder.aShoppingCart().customerId(customerId).build();

        when(customers.exists(customerId)).thenReturn(true);
        when(shoppingCarts.ofCustomer(customerId)).thenReturn(Optional.of(existingCart));

        assertThatExceptionOfType(CustomerAlreadyHaveShoppingCartException.class)
                .isThrownBy(() -> shoppingService.startShopping(customerId));

        verify(customers).exists(customerId);
        verify(shoppingCarts).ofCustomer(customerId);
    }
}