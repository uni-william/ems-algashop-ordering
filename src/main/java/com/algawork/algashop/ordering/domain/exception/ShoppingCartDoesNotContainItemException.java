package com.algawork.algashop.ordering.domain.exception;

import com.algawork.algashop.ordering.domain.valueObject.id.ShoppingCartId;
import com.algawork.algashop.ordering.domain.valueObject.id.ShoppingCartItemId;

public class ShoppingCartDoesNotContainItemException extends DomainException {
    public ShoppingCartDoesNotContainItemException(ShoppingCartId id, ShoppingCartItemId shoppingCartItemId) {
        super(String.format(ErrorMessages.ERROR_SHOPPING_CART_DOES_NOT_CONTAIN_ITEM, id, shoppingCartItemId));
    }
}
