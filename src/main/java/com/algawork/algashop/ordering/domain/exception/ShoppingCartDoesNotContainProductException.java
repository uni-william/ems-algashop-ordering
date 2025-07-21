package com.algawork.algashop.ordering.domain.exception;

import com.algawork.algashop.ordering.domain.valueObject.id.ProductId;
import com.algawork.algashop.ordering.domain.valueObject.id.ShoppingCartId;

public class ShoppingCartDoesNotContainProductException extends DomainException {
    public ShoppingCartDoesNotContainProductException(ShoppingCartId id, ProductId productId) {
        super(String.format(ErrorMessages.ERROR_SHOPPING_CART_DOES_NOT_CONTAIN_PRODUCT, id, productId));
    }
}
